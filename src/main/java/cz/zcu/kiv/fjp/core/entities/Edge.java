package cz.zcu.kiv.fjp.core.entities;

import java.util.ArrayList;

public class Edge {
    String archetype;
    ArrayList<EdgeAttribute> attributes;
    int from;
    int id;
    String text;
    int to;

    public Edge(String archetype, ArrayList<EdgeAttribute> attributes, int from, int id, String text, int to) {
        this.archetype = archetype;
        this.attributes = attributes;
        this.from = from;
        this.id = id;
        this.text = text;
        this.to = to;
    }

    public String getArchetype() {
        return archetype;
    }

    public void setArchetype(String archetype) {
        this.archetype = archetype;
    }

    public ArrayList<EdgeAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<EdgeAttribute> attributes) {
        this.attributes = attributes;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
