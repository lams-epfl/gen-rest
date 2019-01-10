package OpenAPIToSEAM.parser.query.attributesGet;

import com.fasterxml.jackson.databind.ObjectMapper;
import OpenAPIToSEAM.parser.Schema.Paths.Schema;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Response {

    private String description = "";
    private Content content = null;


    public Response(Object o) {
        Map<String, Map<String, Object>> map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, o);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        description = map.get("200").get("description").toString();
        content = new Content(map.get("200").get("content"));
    }

    public String getDescription() {
        return description;
    }

    public Content getContent() {
        return content;
    }




    public class Content {
        private Schema schema = null;

        public Content(Object content) {
            Map<String, Map<String, Object>> map = new HashMap<>();
            ObjectMapper om = new ObjectMapper();
            StringWriter sw = new StringWriter();
            try {
                om.writeValue(sw, content);
                map = om.readValue(sw.toString(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            schema = new Schema(map.get("application/json").get("schema"));

        }

        public Schema getSchema() {
            return schema;
        }
    }
}
