package cz.zcu.kiv.fjp.entities;

public class VertexArchetype {
    String icon;
    String name;
    String text;

    public VertexArchetype(String icon, String name, String text) {
        this.icon = icon;
        this.name = name;
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
}
