package OpenAPIToSEAM.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Paths {

    Map<String, Queries> paths = new HashMap<>();

    public Paths(Object paths){
        Map<String, Object> map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, paths);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Map.Entry<String, Object> entryRequests : map.entrySet()){
            this.paths.put(entryRequests.getKey(), new Queries(entryRequests.getValue()));
        }
    }

    public Map<String, Queries> getPaths() {
        return paths;
    }

}
