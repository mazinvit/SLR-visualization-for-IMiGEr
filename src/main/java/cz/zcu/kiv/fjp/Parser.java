package cz.zcu.kiv.fjp;

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

public class Parser {

    File inputFile, outputFile;
    GraphParser parser;
    List<EdgeArchetype> edgeArchetypes;
    List<VertexArchetype> vertexArchetypes;
    List<AttributeType> attributeTypes;

    public Parser(String inputFile, String outputFile) {
        try {
            parser = new GraphParser(new FileInputStream(new File(inputFile)));
            defineEdgeArchetypes();
            defineVertexArchetypes();
            defineAttributeTypes();
        }

        catch (FileNotFoundException fe) {
            System.out.println("Input file not found!");
        }
    }

    public List<Node> parseNodes() {
        List<Node> parsed = new ArrayList<Node>();
        Map<String, GraphNode> nodes = parser.getNodes();

        for(GraphNode node : nodes.values()) {
            int id;

            id = getNodeID(node);

            //TODO atributes
            String label = (String) node.getAttribute("label");
            if (label.equals("Acc")) {
                parsed.add(new Node(id, (String) node.getAttribute("label"), "", 1));
            }
            else  if(label.startsWith("R")) {
                parsed.add(new Node(id, (String) node.getAttribute("label"), "", 2));
            }
            else {
                parsed.add(new Node(id, (String) node.getAttribute("label"), "", 0));
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
            int id = Integer.parseInt(edge.getId());
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
        }

        return((int) Integer.parseInt(node.getId()));
    }

    private void defineAttributeTypes() {
        attributeTypes = new ArrayList<AttributeType>();
        attributeTypes.add(new AttributeType("string", "label", ""));
    }

    private void defineEdgeArchetypes() {
        this.edgeArchetypes = new ArrayList<EdgeArchetype>();
        edgeArchetypes.add(new EdgeArchetype("reduce", ""));
        edgeArchetypes.add(new EdgeArchetype("shift", ""));
    }

    private void defineVertexArchetypes() {
        this.vertexArchetypes = new ArrayList<VertexArchetype>();
        vertexArchetypes.add(new VertexArchetype("", "state", ""));
        vertexArchetypes.add(new VertexArchetype("", "acc", ""));
        vertexArchetypes.add(new VertexArchetype("", "reduce", ""));
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
