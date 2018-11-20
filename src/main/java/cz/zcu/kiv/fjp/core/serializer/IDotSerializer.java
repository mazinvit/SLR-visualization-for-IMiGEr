package cz.zcu.kiv.fjp.core.serializer;

public interface IDotSerializer {

    /**
     * Method calls parser and then saves output JSON into given output file.
     *
     * @param outputFile Path to output file
     */
    public void serialize(String outputFile);

}
