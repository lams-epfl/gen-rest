package OpenAPIToSEAM.parser.Schema.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Properties {

    private Map<String , Property> prop = new HashMap<>();

    public Map<String, Property> getProp() {
        return prop;
    }

    public Properties(Object o){

        Map<String, Object> map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, o);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Map.Entry<String, Object> e : map.entrySet()){
            prop.put(e.getKey(), new Property(e.getValue()));
        }



    }


    public class Property{

        private String type = "";
        private String ref = "";

        public Property(Object i){
            Map<String, Object> map = new HashMap<>();
            ObjectMapper om = new ObjectMapper();
            StringWriter sw = new StringWriter();
            try {
                om.writeValue(sw, i);
                map = om.readValue(sw.toString(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            type = map.getOrDefault("type", "").toString();
            ref = map.getOrDefault("items", "").toString();
        }

        public String getRef() {
            return ref;
        }

        public String getType() {
            return type;
        }
    }

}
