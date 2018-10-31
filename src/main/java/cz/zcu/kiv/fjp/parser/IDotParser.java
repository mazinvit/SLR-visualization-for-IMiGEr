package cz.zcu.kiv.fjp.parser;

import cz.zcu.kiv.fjp.entities.Edge;
import cz.zcu.kiv.fjp.entities.Vertex;

import java.util.List;

public interface IDotParser {

    public List<Vertex> parseNodes();

    public List<Edge> parseEdges();

}
