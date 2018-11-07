package cz.zcu.kiv.fjp;


import cz.zcu.kiv.fjp.parser.DotParser;
import cz.zcu.kiv.fjp.parser.GeneralParser;
import cz.zcu.kiv.fjp.parser.SLRParser;
import cz.zcu.kiv.fjp.serializer.DotSerializer;

public class Main {

    public static void main(String[] args) {

        if (args.length >= 1) {
            if (args[0].equals("-h")) {
                System.out.println("Printign help:");
                System.out.println("For general graph run with:");
                System.out.println("<input file> <output file>\n");
                System.out.println("For SLR graph run with:");
                System.out.println("--slr <input file> <output file>\n");
            }

            else {
                DotParser parser = null;
                String graphFile = null;
                String outputFile = null;

                if (args.length == 2) {
                    graphFile = args[0];
                    outputFile = args[1];
                    parser = new GeneralParser(graphFile);
                }

                if (args.length == 3 && args[0].equals("--slr")) {
                    graphFile = args[1];
                    outputFile = args[2];
                    parser = new SLRParser(graphFile);
                }

                if(parser != null) {
                    DotSerializer serializer = new DotSerializer(parser);
                    serializer.serialize(outputFile);
                }

                else {
                    System.out.println("Wrong params");
                    System.out.println("For help run with -h");
                }
            }
        }

        else {
            System.out.println("Wrong params");
            System.out.println("For help run with -h");
        }
    }
}
