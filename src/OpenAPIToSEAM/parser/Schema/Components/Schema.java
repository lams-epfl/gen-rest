package OpenAPIToSEAM.parser.Schema.Components;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Schema {

    private String type = "";
    private Properties properties = null;

    public Schema(Object o){
        Map<String, Object> map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, o);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        type = map.getOrDefault("type", "").toString();
        if(map.get("properties") != null){
            properties = new Properties(map.get("properties"));
        }
    }

    public String getType() {
        return type;
    }

    public Properties getProperties() {
        return properties;
    }
}
