package OpenAPIToSEAM.parser.query.attributesPut;

import com.fasterxml.jackson.databind.ObjectMapper;
import OpenAPIToSEAM.parser.Schema.Paths.Schema;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class RequestBody {
    private Content content;
    private Boolean required;

    public RequestBody(Object requestBody) {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, requestBody);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        content = new Content(map.get("content"));
        required = Boolean.valueOf(map.get("required").toString());
    }

    public Content getContent() {
        return content;
    }

    public Boolean getRequired() {
        return required;
    }



    public class Content {
        private Schema schema;

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

            schema = new Schema(map.get("application/json").get("schema"));

        }

        public Schema getSchema_ref() {
            return schema;
        }
    }

}
