package cz.zcu.kiv.fjp.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import cz.zcu.kiv.fjp.core.entities.Dummy;
import cz.zcu.kiv.fjp.core.entities.Vertex;

import java.io.IOException;

public class VertexSerializer extends StdSerializer<Vertex> {

    public VertexSerializer() {
        this(null);
    }

    public VertexSerializer(Class<Vertex> t) {
        super(t);
    }

    @Override
    public void serialize(
            Vertex value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("archetype", value.getArchetype());
        jgen.writeObjectField("attributes", new Dummy());
        jgen.writeNumberField("id", value.getId());
        jgen.writeStringField("text", value.getText());
        jgen.writeStringField("name", value.getName());
        jgen.writeEndObject();
    }
}
