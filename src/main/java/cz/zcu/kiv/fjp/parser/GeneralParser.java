package cz.zcu.kiv.fjp.parser;

import com.paypal.digraph.parser.GraphEdge;
import com.paypal.digraph.parser.GraphNode;
import cz.zcu.kiv.fjp.entities.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GeneralParser extends DotParser {

    public GeneralParser(String inputFile, String outputFile) {
        super(inputFile, outputFile);
    }

    public List<Vertex> parseNodes() {
        System.out.println("Vertex parsing.");

        List<Vertex> parsed = new ArrayList<Vertex>();
        Map<String, GraphNode> nodes = parser.getNodes();

        for(GraphNode node : nodes.values()) {
            int id;

            id = getNodeID(node);

            String replacedLabel = node.getId().replaceAll("(\\\\n|\"|\\\\l)", "");
            parsed.add(new Vertex(id, replacedLabel, "", 0));
        }


        return parsed;
    }

    public List<Edge> parseEdges() {
        System.out.println("Edge parsing.");

        List<Edge> parsed = new ArrayList<Edge>();
        Map<String, GraphEdge> edges = parser.getEdges();

        ArrayList<EdgeAttribute> edgeAttributes;

        for(GraphEdge edge : edges.values()) {

            String edgeLabel = (String) edge.getAttribute("label");

            int from = getNodeID(edge.getNode1());
            int id = edgeId++;
            int to = getNodeID(edge.getNode2());

            edgeAttributes = new ArrayList<EdgeAttribute>();
            edgeAttributes.add(new EdgeAttribute("0", (edgeLabel == null) ? edge.getId() : edgeLabel));

            parsed.add(new Edge("0", edgeAttributes, from, id, "", to));

        }

        return parsed;
    }

    protected void defineEdgeArchetypes() {
        this.edgeArchetypes = new ArrayList<EdgeArchetype>();
        edgeArchetypes.add(new EdgeArchetype("defaultEdge", ""));
    }

    protected void defineVertexArchetypes() {
        this.vertexArchetypes = new ArrayList<VertexArchetype>();
        vertexArchetypes.add(new VertexArchetype("defaultVertex", ""));
    }

}
