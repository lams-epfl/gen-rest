package OpenAPIToSEAM.parser.query.attributesPost;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Response {

    private String description = "";
    private Content content;

    public Response(Object responses) {
        Map<String, Map<String, Object>> map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, responses);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(map.get("200") != null && map.get("200").get("description") != null){
            description = map.get("200").get("description").toString();
        }

        if(map.get("200") != null && map.get("200").get("content") != null) {
            content = new Content(map.get("200").get("content"));
        }
    }

    public String getDescription() {
        return description;
    }

    public Content getContent() {
        return content;
    }


    public class Content {
        private String schema_ref = "";

        public Content(Object content) {
            Map<String, Map<String, Map<String, String>>> map = new HashMap<>();
            ObjectMapper om = new ObjectMapper();
            StringWriter sw = new StringWriter();
            try {
                om.writeValue(sw, content);
                map = om.readValue(sw.toString(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            schema_ref = map.get("application/json").get("schema").get("$ref");

        }

        public String getSchema_ref(){
            return schema_ref;
        }

    }


}
