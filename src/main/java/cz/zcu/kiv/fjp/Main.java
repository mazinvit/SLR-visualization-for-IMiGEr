package cz.zcu.kiv.fjp;


import cz.zcu.kiv.fjp.parser.SLRParser;
import cz.zcu.kiv.fjp.serializer.SLRSerializer;

public class Main {

    public static void main(String[] args) {

        if (args.length >= 1) {
            if (args[0].equals("-h")) {
                System.out.println("Printign help:");
            }

            else {
                String graphFile = args[0];
                String outputFile = args[1];

                SLRParser parser = new SLRParser(graphFile, outputFile);
                //parser.parseNodes();
                SLRSerializer serializer = new SLRSerializer(parser);
                serializer.serialize(outputFile);
                //serializer.serialize(outputFile);

                /*
                List<Vertex> nodes = parser.parseNodes();
                File nodeJson = new File("graph.json");

                try {
                    ObjectMapper om = new ObjectMapper();
                    om.enable(SerializationFeature.INDENT_OUTPUT);
                    StringBuilder sb = new StringBuilder();

                    for(Vertex node : nodes) {
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
