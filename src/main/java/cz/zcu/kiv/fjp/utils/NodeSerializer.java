package cz.zcu.kiv.fjp.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import cz.zcu.kiv.fjp.entities.Node;

import java.io.IOException;

public class NodeSerializer extends StdSerializer<Node> {

    public NodeSerializer() {
        this(null);
    }

    public NodeSerializer(Class<Node> t) {
        super(t);
    }

    @Override
    public void serialize(
            Node value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("title", value.getTitle());
        jgen.writeStringField("text", value.getText());
        jgen.writeNumberField("archetype", value.getArchetype());
        jgen.writeStringField("attributes", "{}");
        jgen.writeEndObject();
    }
}
