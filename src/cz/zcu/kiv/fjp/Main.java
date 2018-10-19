package cz.zcu.kiv.fjp;

public class Main {

    public static void main(String[] args) {

        if (args.length >= 1) {
            if (args[0] == "-h") {
                System.out.println("Printign help:");
            }

            else {
                String graphFile = args[0];
                String outputFile = args[1];
            }
        }

        else {
            System.out.println("Wrong params");
        }
    }
}
