package com.example.pathfinder.Data.BlobSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class BlobSerializer extends JsonSerializer<Blob> {
    @Override
    public void serialize(Blob blob, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (blob != null) {
            byte[] bytes = null;
            try {
                bytes = blob.getBinaryStream().readAllBytes();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String base64 = Base64.getEncoder().encodeToString(bytes);
            gen.writeString(base64);
        } else {
            gen.writeNull();
        }
    }
}
