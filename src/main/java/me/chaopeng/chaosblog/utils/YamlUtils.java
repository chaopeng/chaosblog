package me.chaopeng.chaosblog.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

/**
 * utils.YamlUtils
 *
 * @author chao
 * @version 1.0 - 2015-11-12
 */
public class YamlUtils {

    private static final ObjectMapper om = new ObjectMapper(new YAMLFactory());

    public static String encode(Object o) throws IOException {
        return om.writeValueAsString(o);
    }

    public static <T> T decode(String s, Class<T> cls) throws IOException {
        return om.readValue(s, cls);
    }

    public static <T> T decode(File file, Class<T> cls) throws IOException {
        return om.readValue(file, cls);
    }

}
