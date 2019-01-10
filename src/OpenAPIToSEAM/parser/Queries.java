package OpenAPIToSEAM.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import OpenAPIToSEAM.parser.query.Get;
import OpenAPIToSEAM.parser.query.Post;
import OpenAPIToSEAM.parser.query.Put;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Queries {

    List<Query> queries = new ArrayList<>();

    public Queries(Object queries){
        Map<String, Object> map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, queries);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Map.Entry<String, Object> entryRequests : map.entrySet()){
            Query q;

            switch(entryRequests.getKey().toLowerCase()) {
                case "post":
                    q = new Post(entryRequests.getValue());
                    break;
                case "put":
                    q = new Put(entryRequests.getValue());
                    break;
                case "get":
                    q = new Get(entryRequests.getValue());
                    break;
                default:
                    throw new IllegalArgumentException("Unknown query");
            }

            this.queries.add(q);
        }
    }

    public List<Query> getQueries() {
        return queries;
    }
}
