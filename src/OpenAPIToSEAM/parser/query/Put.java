package OpenAPIToSEAM.parser.query;

import com.fasterxml.jackson.databind.ObjectMapper;
import OpenAPIToSEAM.parser.Component;
import OpenAPIToSEAM.parser.Query;
import OpenAPIToSEAM.parser.Schema.Paths.Properties;
import OpenAPIToSEAM.parser.Utils;
import OpenAPIToSEAM.parser.query.attributesPut.RequestBody;
import OpenAPIToSEAM.parser.query.attributesPut.Response;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Put implements Query {

    private List<String> tags;
    private String description;
    private Response response_description;
    private RequestBody schema;

    public Put(Object put){
        Map<String, Object> map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, put);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tags = (ArrayList<String>) map.get("tags");
        description = (String) map.get("description");
        response_description = new Response(map.get("responses"));
        schema = new RequestBody(map.get("requestBody"));

    }

    @Override
    public List<String> getTags() {
        return tags;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String genIO(Component c, String IO) {
        String s ="<alignment>selection.propertyKind.alignment.values.left</alignment>\n"
                + Utils.createSpace(4)
                + "<kind>localised_property</kind>\n"
                + Utils.createSpace(4)
                + "<stereotype>" + IO + "</stereotype>\n"
                + Utils.createSpace(4)
                +"<name>" + genQuery(IO) + "</name>\n";
        return s;
    }


    public Response getResponse_description() {
        return response_description;
    }

    public RequestBody getSchema() {
        return schema;
    }

    @Override
    public String getType() {
        return "put";
    }

    private String genQuery(String IO) {
        String queryIn= "";
        if(IO.equals("in")){
            Map<String, Properties.Property> map = schema.getContent().getSchema_ref().getProperties().getProp();
            int sizeMap = map.size();
            int currSize = 0;
            for(Map.Entry<String, Properties.Property> e : map.entrySet()){
                currSize++;
                queryIn += e.getKey() + ":" + e.getValue().getType() + ((currSize == sizeMap) ? "\\n" : ", \\n");
            }
        } else if (IO.equals("out")){
            return response_description.getDescription();
        }

        return queryIn;
    }
}
