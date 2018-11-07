package cz.zcu.kiv.fjp.parser;

import com.paypal.digraph.parser.GraphNode;
import com.paypal.digraph.parser.GraphParser;
import cz.zcu.kiv.fjp.entities.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public abstract class DotParser {

    protected File inputFile, outputFile;
    protected GraphParser parser;
    protected List<EdgeArchetype> edgeArchetypes;
    protected List<VertexArchetype> vertexArchetypes;
    protected List<AttributeType> attributeTypes;

    int edgeId;

    public DotParser(String inputFile, String outputFile) {
        try {
            System.out.println("Parser Initialization.");
            parser = new GraphParser(new FileInputStream(new File(inputFile)));
            defineEdgeArchetypes();
            defineVertexArchetypes();
            defineAttributeTypes();
            edgeId = 0;
        }

        catch (FileNotFoundException fe) {
            System.out.println("Input file not found!");
        }
    }

    protected final int getNodeID(GraphNode node) {
        return node.getId().hashCode();
    }

    protected void defineAttributeTypes() {
        attributeTypes = new ArrayList<AttributeType>();
        attributeTypes.add(new AttributeType("string", "label", ""));
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

    public abstract List<Vertex> parseNodes();

    public abstract List<Edge> parseEdges();

    protected abstract void defineEdgeArchetypes();

    protected abstract void defineVertexArchetypes();

}
