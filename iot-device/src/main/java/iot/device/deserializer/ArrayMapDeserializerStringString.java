package iot.device.deserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ArrayMapDeserializerStringString extends JsonDeserializer<Map<String, String>> {

    @JsonCreator
    public ArrayMapDeserializerStringString() {
    }

    @Override
    public Map<String, String> deserialize(JsonParser jp, DeserializationContext arg1) throws IOException, JsonProcessingException {

        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        if (jp.getCurrentToken().equals(JsonToken.START_OBJECT)) {
            return mapper.readValue(jp, new TypeReference<HashMap<String, String>>() {
            });
        } else {
            mapper.readTree(jp);
            return new HashMap<String, String>();
        }
    }
}
