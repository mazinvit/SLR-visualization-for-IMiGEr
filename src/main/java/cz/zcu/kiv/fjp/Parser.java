package cz.zcu.kiv.fjp;

import com.paypal.digraph.parser.GraphEdge;
import com.paypal.digraph.parser.GraphNode;
import com.paypal.digraph.parser.GraphParser;
import cz.zcu.kiv.fjp.entities.Edge;
import cz.zcu.kiv.fjp.entities.Node;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser {

    File inputFile, outputFile;
    GraphParser parser;

    public Parser(String inputFile, String outputFile) {
        try {
            parser = new GraphParser(new FileInputStream(new File(inputFile)));
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

            if(node.getId().contains("R")) {
                id = node.getId().hashCode();
            } else {
                id = ((int) Integer.parseInt(node.getId()));
            }

            //TODO atributes

            parsed.add(new Node(id, (String) node.getAttribute("label")));
        }


        return parsed;
    }

    public List<Edge> parseEdges() {
        List<Edge> parsed = new ArrayList<Edge>();
        Map<String, GraphEdge> edges = parser.getEdges();



        return parsed;
    }
}
