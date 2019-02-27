package OpenAPIToSEAM.parser.query.attributesPut;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Response {

    private String description = "";

    public Response(Object responses) {
        Map<String, Map<String, String>> map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, responses);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        description = map.get("200").get("description");
    }

    public String getDescription() {
        return description;
    }

}
