package cz.zcu.kiv.fjp.entities;

public class Node {
    int id;
    String title;
    String text;
    int archetype;
    //ArrayList<String> attributes;
    //TODO empty object attributes

    public Node(int id, String title, String text, int archetype) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.archetype = archetype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
