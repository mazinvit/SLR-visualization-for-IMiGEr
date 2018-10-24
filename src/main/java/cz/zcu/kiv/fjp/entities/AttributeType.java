package cz.zcu.kiv.fjp.entities;

public class AttributeType {
    String dataType;
    String name;
    String text;

    public AttributeType(String dataType, String name, String text) {
        this.dataType = dataType;
        this.name = name;
        this.text = text;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
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
