package cz.zcu.kiv.fjp.parser;

import com.paypal.digraph.parser.GraphEdge;
import com.paypal.digraph.parser.GraphNode;
import com.paypal.digraph.parser.GraphParser;
import cz.zcu.kiv.fjp.entities.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SLRParser implements IDotParser {

    File inputFile, outputFile;
    GraphParser parser;
    List<EdgeArchetype> edgeArchetypes;
    List<VertexArchetype> vertexArchetypes;
    List<AttributeType> attributeTypes;
    int edgeId;
    int count;

    public SLRParser(String inputFile, String outputFile) {
        try {
            parser = new GraphParser(new FileInputStream(new File(inputFile)));
            defineEdgeArchetypesSLR();
            defineVertexArchetypesSLR();
            defineAttributeTypesSLR();
            edgeId = 0;
            count = 8;
        }

        catch (FileNotFoundException fe) {
            System.out.println("Input file not found!");
        }
    }

    public List<Vertex> parseNodes() {
        List<Vertex> parsed = new ArrayList<Vertex>();
        Map<String, GraphNode> nodes = parser.getNodes();

        for(GraphNode node : nodes.values()) {
            int id;

            id = getNodeID(node);

            //TODO atributes
            String label = (String) node.getAttribute("label");
            if (label.equals("Acc")) {
                parsed.add(new Vertex(id, (String) node.getAttribute("label"), "", 1));
            }
            else  if(label.startsWith("R")) {
                parsed.add(new Vertex(id, (String) node.getAttribute("label"), "", 2));
            }
            else {
                parsed.add(new Vertex(id, (String) node.getAttribute("label"), "", 0));
            }
        }


        return parsed;
    }

    public List<Edge> parseEdges() {
        List<Edge> parsed = new ArrayList<Edge>();
        Map<String, GraphEdge> edges = parser.getEdges();

        ArrayList<EdgeAttribute> edgeAttributes;

        for(GraphEdge edge : edges.values()) {
            String edgeType = (String) edge.getAttribute("style");
            String edgeLabel = (String) edge.getAttribute("label");
            int from = getNodeID(edge.getNode1());
            int id = edgeId++;
            int to = getNodeID(edge.getNode2());

            edgeAttributes = new ArrayList<EdgeAttribute>();
            edgeAttributes.add(new EdgeAttribute("0", (edgeLabel == null) ? "" : edgeLabel));

            if (edgeType.equals("solid")) {
                parsed.add(new Edge("0", edgeAttributes, from, id, "", to));
            }
            else {
                parsed.add(new Edge("1", edgeAttributes, from, id, "", to));
            }
        }

        return parsed;
    }

    private int getNodeID(GraphNode node) {
        if(node.getId().contains("R")) {
            return node.getId().hashCode();
            //return count++;
        }

        return((int) Integer.parseInt(node.getId()));
    }

    private void defineAttributeTypesSLR() {
        attributeTypes = new ArrayList<AttributeType>();
        attributeTypes.add(new AttributeType("string", "label", ""));
    }

    private void defineEdgeArchetypesSLR() {
        this.edgeArchetypes = new ArrayList<EdgeArchetype>();
        edgeArchetypes.add(new EdgeArchetype("reduce", ""));
        edgeArchetypes.add(new EdgeArchetype("shift", ""));
    }

    private void defineVertexArchetypesSLR() {
        this.vertexArchetypes = new ArrayList<VertexArchetype>();
        vertexArchetypes.add(new VertexArchetype("state", ""));
        vertexArchetypes.add(new VertexArchetype( "acc", ""));
        vertexArchetypes.add(new VertexArchetype( "reduce", ""));
    }

    public List<EdgeArchetype> getEdgeArchetypes() {
        return edgeArchetypes;
    }

    public List<VertexArchetype> getVertexArchetypes() {
        return vertexArchetypes;
    }

    public List<AttributeType> getAttributeTypes() {
        return attributeTypes;
    }
}
