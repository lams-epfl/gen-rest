package OpenAPIToSEAM.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dependencies {

    private Map<String, List<Map<String, String>>> dependencies = new HashMap<>();

    public Dependencies(Object component){
        Map<String, List<Map<String, String>>> map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, component);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        dependencies = map;

    }

    public Map<String, List<Map<String, String>>> getDependencies() {
        return dependencies;
    }
}
