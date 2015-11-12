package me.chaopeng.chaosblog.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.cfg.ConfigFeature;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.SerializerFactoryConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author chao
 */
public class JsonUtils {
    private static final ObjectMapper om = new ObjectMapper();
    static {
        om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    }

    public static String encode(Object o) throws IOException {
        return om.writeValueAsString(o);
    }

    public static <T> T decode(String s, Class<T> cls) throws IOException {
        return om.readValue(s, cls);
    }

    public static JsonNode decode(String s) throws IOException {
        return om.readTree(s);
    }

    public static <T> T readJson(BufferedReader br, Class<T> cls) throws Exception{

        String s = "";
        StringBuilder sb = null;

        while((s=br.readLine())!=null){
            if(s.length()==0) continue;
            else if(s.equals("{")) {
                sb = new StringBuilder("{");
            }
            else if(s.equals("}")) {
                sb.append("}");
                break;
            }
            else{
                sb.append(s);
            }
        }

        return decode(sb.toString(), cls);
    }

}
