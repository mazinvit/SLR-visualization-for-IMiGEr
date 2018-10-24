package cz.zcu.kiv.fjp;

import cz.zcu.kiv.fjp.entities.EdgeArchetype;
import cz.zcu.kiv.fjp.entities.VertexArchetype;

import java.util.ArrayList;

public class Serializer {

    Parser parser;

    public Serializer(Parser parser) {
        this.parser = parser;
    }

    public void serializeAutomaton(String outpurFile) {
        ArrayList<VertexArchetype> vertexArchetypes = new ArrayList<VertexArchetype>();
        vertexArchetypes.add(new VertexArchetype("", "state", ""));
        vertexArchetypes.add(new VertexArchetype("", "acc", ""));
        vertexArchetypes.add(new VertexArchetype("", "reduce", ""));
        ArrayList<EdgeArchetype> edgeArchetypes = new ArrayList<EdgeArchetype>();
        edgeArchetypes.add(new EdgeArchetype("reduce", ""));
        edgeArchetypes.add(new EdgeArchetype("shift", ""));


    }
}
