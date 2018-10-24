package cz.zcu.kiv.fjp;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cz.zcu.kiv.fjp.entities.Node;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        if (args.length >= 1) {
            if (args[0] == "-h") {
                System.out.println("Printign help:");
            }

            else {
                String graphFile = args[0];
                String outputFile = args[1];

                Parser parser = new Parser(graphFile, outputFile);
                //parser.parseNodes();
                Serializer serializer = new Serializer(parser);
                serializer.serializeAutomaton(outputFile);
                //serializer.serialize(outputFile);

                /*
                List<Node> nodes = parser.parseNodes();
                File nodeJson = new File("graph.json");

                try {
                    ObjectMapper om = new ObjectMapper();
                    om.enable(SerializationFeature.INDENT_OUTPUT);
                    StringBuilder sb = new StringBuilder();

                    for(Node node : nodes) {
                        sb.append(om.writeValueAsString(node));
                    }

                    OutputStreamWriter ow = new OutputStreamWriter(new FileOutputStream(nodeJson));
                    ow.write(sb.toString());
                    ow.flush();
                    ow.close();
                }

                catch (Exception e) {
                    System.out.println("Cannot create JSON.");
                }
                */
            }
        }

        else {
            System.out.println("Wrong params");
        }
    }
}
