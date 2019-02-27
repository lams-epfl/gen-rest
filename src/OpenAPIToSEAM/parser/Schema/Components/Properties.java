package OpenAPIToSEAM.parser.Schema.Components;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Properties {

    private Map<String , Property> prop = new HashMap<>();

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


    public Map<String, Property> getProp() {
        return prop;
    }

    public class Property{

        private String type = "";
        private String format = "";
        private String enumeration = "";

        public Property(Object i){
            Map<String, Object> map;
            ObjectMapper om = new ObjectMapper();
            StringWriter sw = new StringWriter();
            try {
                om.writeValue(sw, i);
                map = om.readValue(sw.toString(), Map.class);

                type = map.getOrDefault("type", "").toString();
                format = map.getOrDefault("format", "").toString();
                enumeration = map.getOrDefault("enum", "").toString();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public String getFormat() {
            return format;
        }

        public String getType() {
            return type;
        }

        public String getEnumeration() {
            return enumeration;
        }
    }

}
