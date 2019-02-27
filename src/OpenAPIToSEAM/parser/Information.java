package OpenAPIToSEAM.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class Information {

    private String version;
    private String titre;

    public Information(Object information){
        Map<String, Object> map = null;

        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, information);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        version = map.get("version").toString();
        titre = map.get("title").toString();

    }

    public String getTitle() {
        return titre;
    }
}
