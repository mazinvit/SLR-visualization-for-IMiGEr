package cz.zcu.kiv.fjp;


import cz.zcu.kiv.fjp.parser.DotParser;
import cz.zcu.kiv.fjp.parser.GeneralParser;
import cz.zcu.kiv.fjp.serializer.DotSerializer;

public class Main {

    public static void main(String[] args) {

        if (args.length >= 1) {
            if (args[0].equals("-h")) {
                System.out.println("Printign help:");
            }

            else {
                String graphFile = args[0];
                String outputFile = args[1];

                DotParser parser = new GeneralParser(graphFile, outputFile);
                DotSerializer serializer = new DotSerializer(parser);
                serializer.serialize(outputFile);
            }
        }

        else {
            System.out.println("Wrong params");
        }
    }
}
