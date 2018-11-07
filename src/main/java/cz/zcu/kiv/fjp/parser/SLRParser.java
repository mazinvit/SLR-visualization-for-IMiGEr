package cz.zcu.kiv.fjp.parser;

import com.paypal.digraph.parser.GraphEdge;
import com.paypal.digraph.parser.GraphNode;

import cz.zcu.kiv.fjp.entities.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SLRParser extends DotParser {

    public SLRParser(String inputFile) {
        super(inputFile);
    }

    public List<Vertex> parseNodes() {
        System.out.println("Vertex parsing.");

        List<Vertex> parsed = new ArrayList<Vertex>();
        Map<String, GraphNode> nodes = parser.getNodes();

        for(GraphNode node : nodes.values()) {
            int id;

            id = getNodeID(node);

            //TODO atributes
            String label = (String) node.getAttribute("label");
            String replacedLabel = label.replaceAll("(\\\\n\\\\l)", ": ");
            replacedLabel = replacedLabel.replaceAll("\\\\n|\\\\l", "");
            if (label.equals("Acc")) {
                parsed.add(new Vertex(id, replacedLabel, "", 1));
            }
            else  if(label.startsWith("R")) {
                parsed.add(new Vertex(id, replacedLabel, "", 2));
            }
            else {
                parsed.add(new Vertex(id, replacedLabel, "", 0));
            }
        }


        return parsed;
    }

    public List<Edge> parseEdges() {
        System.out.println("Edge parsing.");

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

    protected void defineEdgeArchetypes() {
        this.edgeArchetypes = new ArrayList<EdgeArchetype>();
        edgeArchetypes.add(new EdgeArchetype("reduce", ""));
        edgeArchetypes.add(new EdgeArchetype("shift", ""));
    }

    protected void defineVertexArchetypes() {
        this.vertexArchetypes = new ArrayList<VertexArchetype>();
        vertexArchetypes.add(new VertexArchetype("state", ""));
        vertexArchetypes.add(new VertexArchetype( "acc", ""));
        vertexArchetypes.add(new VertexArchetype( "reduce", ""));
    }

}
