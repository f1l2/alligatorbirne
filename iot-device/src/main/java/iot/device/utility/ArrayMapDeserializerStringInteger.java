package iot.device.utility;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ArrayMapDeserializerStringInteger extends JsonDeserializer<Map<String, Integer>> {

    public ArrayMapDeserializerStringInteger() {
    }

    @Override
    public Map<String, Integer> deserialize(JsonParser jp, DeserializationContext arg1) throws IOException, JsonProcessingException {

        ObjectMapper mapper = (ObjectMapper) jp.getCodec();
        if (jp.getCurrentToken().equals(JsonToken.START_OBJECT)) {
            return mapper.readValue(jp, new TypeReference<HashMap<String, Integer>>() {
            });
        } else {
            mapper.readTree(jp);
            return new HashMap<String, Integer>();
        }
    }
}
