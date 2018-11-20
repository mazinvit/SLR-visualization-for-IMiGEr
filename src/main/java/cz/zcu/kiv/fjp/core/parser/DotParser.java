package cz.zcu.kiv.fjp.core.parser;

import com.paypal.digraph.parser.GraphNode;
import com.paypal.digraph.parser.GraphParser;
import cz.zcu.kiv.fjp.core.entities.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for parser that extracts vertices and edges from .dot file
 * and provides methods to converts extracted objects into custom entities and methods
 * for defining their archetypes.
 */
public abstract class DotParser {


    protected GraphParser parser;
    protected List<EdgeArchetype> edgeArchetypes;
    protected List<VertexArchetype> vertexArchetypes;
    protected List<AttributeType> attributeTypes;

    int edgeId;

    /**
     * Loads graph from .dot file into the structure and defines archetypes for vertices and edges.
     *
     * @param inputFile - input file containing graph in DOT form
     */
    public DotParser(String inputFile) {
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

    /**
     * Returns node ID as a hashcode.     *
     * @param node - parsed node from graph
     *
     * @return - hashcode of node ID
     */
    protected final int getNodeID(GraphNode node) {
        return node.getId().hashCode();
    }

    /**
     * Defines list of attributes for edges.
     */
    protected void defineAttributeTypes() {
        attributeTypes = new ArrayList<AttributeType>();
        attributeTypes.add(new AttributeType("string", "label", ""));
    }

    /**
     * Returns list of archetypes for edges.
     *
     * @return list of archetypes for edges
     */
    public List<EdgeArchetype> getEdgeArchetypes() {
        return edgeArchetypes;
    }

    /**
     * Returns list of archetypes for vertices.
     *
     * @return list of archetypes for vertices
     */
    public List<VertexArchetype> getVertexArchetypes() {
        return vertexArchetypes;
    }

    /**
     * Returns list of attribute types for edges.
     *
     * @return list of attribute types for edges
     */
    public List<AttributeType> getAttributeTypes() {
        return attributeTypes;
    }

    /**
     * Method takes map of vertices from parser and stores them using object
     * {@link Vertex} into the list of vertices.
     *
     * @return list of vertices
     */
    public abstract List<Vertex> parseNodes();

    /**
     * Method takes map of edges from parser and stores them using objects
     * {@link EdgeAttribute} and {@link Edge} into the list of edges.
     *
     * @return list of edges
     */
    public abstract List<Edge> parseEdges();

    /**
     * Method defines list of archetypes for edges.
     */
    protected abstract void defineEdgeArchetypes();

    /**
     * Method defines list of archetypes for vertices.
     */
    protected abstract void defineVertexArchetypes();

}
