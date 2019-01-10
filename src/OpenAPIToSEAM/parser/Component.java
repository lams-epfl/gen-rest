package OpenAPIToSEAM.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import OpenAPIToSEAM.parser.Schema.Components.Schema;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Component {

    private Map<String, Schema> components = new HashMap<>();

    public Component(Object component){
        Map<String, Map<String, Object>> map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, component);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(map.get("schemas") != null){
            Map<String, Object> schemas = map.get("schemas");

            for(Map.Entry<String, Object> e : schemas.entrySet()) {
                components.put(e.getKey(), new Schema(e.getValue()));
            }
        }
    }

    public Map<String, Schema> getComponents() {
        return components;
    }
}
