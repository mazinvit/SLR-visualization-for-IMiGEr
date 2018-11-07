package cz.zcu.kiv.fjp.core.entities;

public class Vertex {
    int id;
    String name;
    String text;
    int archetype;
    //ArrayList<String> attributes;
    //TODO empty object attributes

    public Vertex(int id, String name, String text, int archetype) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.archetype = archetype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getArchetype() {
        return archetype;
    }

    public void setArchetype(int archetype) {
        this.archetype = archetype;
    }
}
