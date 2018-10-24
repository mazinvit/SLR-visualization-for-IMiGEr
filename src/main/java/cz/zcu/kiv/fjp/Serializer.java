package cz.zcu.kiv.fjp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cz.zcu.kiv.fjp.entities.*;
import cz.zcu.kiv.fjp.utils.NodeSerializer;
import cz.zcu.kiv.fjp.utils.SLREdgeSerializer;
import sun.security.provider.certpath.Vertex;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Serializer {

    Parser parser;

    public Serializer(Parser parser) {
        this.parser = parser;
    }

    public void serializeAutomaton(String outpurFile) {

        ArrayList<Vertex> vertices = new ArrayList<Vertex>();

        ArrayList<Node> nodes = (ArrayList<Node>)parser.parseNodesSLR();
        ArrayList<Edge> edges = (ArrayList<Edge>)parser.parseEdgesSLR();
        ArrayList<AttributeType> attributeTypes = (ArrayList<AttributeType>) parser.getAttributeTypes();
        ArrayList<VertexArchetype> vertexArchetypes = (ArrayList<VertexArchetype>) parser.getVertexArchetypes();
        ArrayList<EdgeArchetype> edgeArchetypes = (ArrayList<EdgeArchetype>) parser.getEdgeArchetypes();

        File nodeJson = new File("graph.json");

        try {
            ObjectMapper om = new ObjectMapper();

            SimpleModule module = new SimpleModule();
            module.addSerializer(Node.class, new NodeSerializer());
            module.addSerializer(Edge.class, new SLREdgeSerializer());
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

            sb.append("\"verticies\": \n");
            String nodesJson = om.writeValueAsString(nodes);
            nodesJson = nodesJson.replace("\"dummy\" : \"dummy\"", " ");
            sb.append(nodesJson);
            sb.append(",\n");

            sb.append("\"edges\": \n");
            String edgesJson = om.writeValueAsString(edges);
            edgesJson = edgesJson.replace("attrstring", "0");
            sb.append(edgesJson);
            sb.append("\n");

            sb.append("}");

            OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(nodeJson));
            ow.write(sb.toString());
            ow.flush();
            ow.close();
        }

        catch (Exception e) {
            System.out.println("Cannot create JSON.");
        }
    }
}
