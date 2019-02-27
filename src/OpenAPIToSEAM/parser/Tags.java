package OpenAPIToSEAM.parser;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tags {

    List<String> tags = new ArrayList<>();

    public Tags(Object tags){

        List<Map<String, String>> map = new ArrayList<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, tags);
            map = om.readValue(sw.toString(), List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < map.size(); i++){
            String tag = map.get(i).get("name");
            if(tag != null){
                this.tags.add(tag);
            }
        }
    }

    public List<String> getTags() {
        return tags;
    }
}
