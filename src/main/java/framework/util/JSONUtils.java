package framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public final class JSONUtils {

    private JSONUtils() {
    }

    public static Map<String, String> convertJSONToObj(String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(json, new TypeReference<Map<String, String>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON to obj\n" + e);
        }
    }

    public static <T> T convertJSONToObj(File file, Class<T> obj) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(file, obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON to obj\n" + e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON\n" + e);
        }
    }

    public static <T> T convertJSONToObj(String json, Class<T> obj) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(json, obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert JSON to obj\n" + e);
        }
    }

    public static void convertObjToJSON(File file, Object value) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, value);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert obj to JSON\n" + e);
        }
    }

    public static String convertObjToJSON(Object value) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringWriter stringWriter = new StringWriter();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(stringWriter, value);
        } catch (IOException e) {
            throw new RuntimeException("Failed to convert obj to JSON\n" + e);
        }

        return stringWriter.toString();
    }
}
