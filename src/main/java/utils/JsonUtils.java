package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.Reader;

/**
 * @author chao
 */
public class JsonUtils {
    private static final ObjectMapper om = new ObjectMapper();

    public static String encode(Object o) throws IOException {
        return om.writeValueAsString(o);
    }

    public static <T> T decode(String s, Class<T> cls) throws IOException {
        return om.readValue(s, cls);
    }

    public static <T> T decode(Reader reader, Class<T> cls) throws IOException {
        return om.readValue(reader, cls);
    }

    public static JsonNode decode(String s) throws IOException {
        return om.readTree(s);
    }

}
