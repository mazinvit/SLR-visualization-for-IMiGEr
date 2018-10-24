package cz.zcu.kiv.fjp;

public class Main {

    public static void main(String[] args) {

        Parser parser;

        if (args.length >= 1) {
            if (args[0] == "-h") {
                System.out.println("Printign help:");
            }

            else {
                String graphFile = args[0];
                String outputFile = args[1];

                parser = new Parser(graphFile, outputFile);
            }
        }

        else {
            System.out.println("Wrong params");
        }
    }
}
