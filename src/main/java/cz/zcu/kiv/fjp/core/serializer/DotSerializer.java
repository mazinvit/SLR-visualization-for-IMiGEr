package cz.zcu.kiv.fjp.core.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cz.zcu.kiv.fjp.core.parser.DotParser;
import cz.zcu.kiv.fjp.core.entities.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DotSerializer implements IDotSerializer {

    DotParser parser;

    public DotSerializer(DotParser parser) {
        this.parser = parser;
    }

    public void serialize(String outpurFile) {

        ArrayList<sun.security.provider.certpath.Vertex> vertices = new ArrayList<sun.security.provider.certpath.Vertex>();

        ArrayList<Vertex> nodes = (ArrayList<Vertex>) parser.parseNodes();
        ArrayList<Edge> edges = (ArrayList<Edge>) parser.parseEdges();
        ArrayList<AttributeType> attributeTypes = (ArrayList<AttributeType>) parser.getAttributeTypes();
        ArrayList<VertexArchetype> vertexArchetypes = (ArrayList<VertexArchetype>) parser.getVertexArchetypes();
        ArrayList<EdgeArchetype> edgeArchetypes = (ArrayList<EdgeArchetype>) parser.getEdgeArchetypes();

        File nodeJson = new File(outpurFile);
        System.out.println("Serializing...");

        try {
            ObjectMapper om = new ObjectMapper();

            SimpleModule module = new SimpleModule();
            module.addSerializer(Vertex.class, new VertexSerializer());
            module.addSerializer(Edge.class, new EdgeSerializer());
            om.registerModule(module);
            om.enable(SerializationFeature.INDENT_OUTPUT);

            StringBuilder sb = new StringBuilder();

            sb.append("{\n");
            sb.append("\"attributeTypes\": \n");
            sb.append(om.writeValueAsString(attributeTypes));
            sb.append(",\n");

            sb.append("\"vertexArchetypes\": \n");
            sb.append(om.writeValueAsString(vertexArchetypes));
            sb.append(",\n");

            sb.append("\"edgeArchetypes\": \n");
            sb.append(om.writeValueAsString(edgeArchetypes));
            sb.append(",\n");

            sb.append("\"vertices\": \n");
            String nodesJson = om.writeValueAsString(nodes);
            nodesJson = nodesJson.replace("\"dummy\" : \"dummy\"", " ");
            sb.append(nodesJson);
            sb.append(",\n");

            sb.append("\"edges\": \n");
            String edgesJson = om.writeValueAsString(edges);
            edgesJson = edgesJson.replace("attrstring", "0");
            sb.append(edgesJson);
            sb.append(",\n");

            sb.append("\"possibleEnumValues\": {},\n" +
                    "\n" +
                    "\"groups\": [],\n" +
                    "\n" +
                    "\"sideBar\": [],\n" +
                    "\n" +
                    "\"highlightedVertex\": \"\",\n" +
                    "\n" +
                    "\"highlightedEdge\": \"\"");

            sb.append("}");

            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(nodeJson));
            ow.write(sb.toString());
            ow.flush();
            ow.close();
        }

        catch (Exception e) {
            System.out.println("Cannot create JSON.");
        }

        finally {
            System.out.println("Done.");
        }
    }
}
