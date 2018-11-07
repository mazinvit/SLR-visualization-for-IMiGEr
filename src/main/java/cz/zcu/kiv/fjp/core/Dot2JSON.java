package cz.zcu.kiv.fjp.core;

import cz.zcu.kiv.fjp.core.entities.Edge;
import cz.zcu.kiv.fjp.core.entities.Vertex;
import cz.zcu.kiv.fjp.core.parser.DotParser;
import cz.zcu.kiv.fjp.core.parser.GeneralParser;
import cz.zcu.kiv.fjp.core.parser.SLRParser;
import cz.zcu.kiv.fjp.core.serializer.DotSerializer;

import java.util.List;

public class Dot2JSON {

    /**
     * Parsing verticies from dot file
     *
     * @param graphFile File with dot graph
     * @param slr If graph is general false, if SLR true
     * @return List of vertices
     */
    public static List<Vertex> getVertices(String graphFile, boolean slr) {
        DotParser parser = null;

        if(slr) {
            parser = new SLRParser(graphFile);
        }

        else {
            parser = new GeneralParser(graphFile);
        }
        return parser.parseNodes();
    }

    /**
     * Parsing edges from dot file
     *
     * @param graphFile File with dot graph
     * @param slr If graph is general false, if SLR true
     * @return List of edges
     */
    public static List<Edge> getSlrEsges(String graphFile, boolean slr) {
        DotParser parser = null;

        if(slr) {
            parser = new SLRParser(graphFile);
        }

        else {
            parser = new GeneralParser(graphFile);
        }

        return parser.parseEdges();
    }

    /**
     * Serializing graph from dot file to IMiGEr raw JSON
     * @param graphFile File with dot graph
     * @param outputFile Output JSON file
     * @param slr If graph is general false, if SLR true
     */
    public static void serialize(String graphFile, String outputFile, boolean slr) {
        DotParser parser = null;

        if(slr) {
            parser = new SLRParser(graphFile);
        }

        else {
            parser = new GeneralParser(graphFile);
        }

        DotSerializer serializer = new DotSerializer(parser);
        serializer.serialize(outputFile);
    }
}
