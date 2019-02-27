package OpenAPIToSEAM.parser.Schema.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Schema {

    private String ref = "";
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

        ref = map.getOrDefault("$ref", "").toString();
        type = map.getOrDefault("type", "").toString();
        if(map.get("properties") != null){
            properties = new Properties(map.get("properties"));
        }
    }

    public String getRef() {
        return ref;
    }

    public String getType() {
        return type;
    }

    public Properties getProperties() {
        return properties;
    }
}
