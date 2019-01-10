package OpenAPIToSEAM.parser.query;

import com.fasterxml.jackson.databind.ObjectMapper;
import OpenAPIToSEAM.parser.Component;
import OpenAPIToSEAM.parser.Query;
import OpenAPIToSEAM.parser.Utils;
import OpenAPIToSEAM.parser.query.attributesPost.RequestBody;
import OpenAPIToSEAM.parser.query.attributesPost.Response;
import OpenAPIToSEAM.parser.Schema.Components.Properties;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

public class Post implements Query {

    private List<String> tags;
    private String description;
    private Response response_description;
    private RequestBody schema;

    public Post(Object post) {
        Map<String, Object> map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, post);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tags = (ArrayList<String>) map.get("tags");
        description = (String) map.get("description");
        response_description = new Response(map.get("responses"));
        schema = new RequestBody(map.get("requestBody"));
    }

    //Getters
    @Override
    public List<String> getTags() {
        return tags;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public Response getResponse_description() {
        return response_description;
    }

    public RequestBody getSchema() {
        return schema;
    }


    @Override
    public String genIO(Component c, String IO) {
        String s ="<alignment>selection.propertyKind.alignment.values.left</alignment>\n"
                + Utils.createSpace(4)
                + "<kind>localised_property</kind>\n"
                + Utils.createSpace(4)
                + "<stereotype>" + IO + "</stereotype>\n"
                + Utils.createSpace(4)
                +"<name>" + genQuery(c, IO) + "</name>\n";
        return s;
    }

    @Override
    public String getType() {
        return "post";
    }


    private String genQuery(Component c, String IO) {
        String queryIn= "";
        String[] splited_Key = {};
        if(IO.equals("in")){
            if(schema != null && schema.getContent() != null &&schema.getContent().getSchema_ref() != null && schema.getContent().getSchema_ref().getRef() != null){
                splited_Key = schema.getContent().getSchema_ref().getRef().split("/");
            }
        } else if (IO.equals("out")){
            if(response_description != null && response_description.getContent() != null && response_description.getContent().getSchema_ref() != null) {
                splited_Key = response_description.getContent().getSchema_ref().split("/");
            }
        }

        if(splited_Key.length > 0){
            String key = splited_Key[splited_Key.length - 1];
            if (c.getComponents().containsKey(key)){
                queryIn = key + " {\\n" ;
                Map<String, Properties.Property> map = c.getComponents().get(key).getProperties().getProp();
                int sizeMap = map.size();
                int currSize = 0;
                for(Map.Entry<String, Properties.Property> e : map.entrySet()){
                    currSize++;
                    queryIn += e.getKey() + ":" + analyseProp(e.getValue()) + ((currSize == sizeMap) ? "\\n" : ", \\n");
                }
                queryIn += "}";
            }
        }

        return queryIn;
    }

    private String analyseProp(Properties.Property p){
        if(p.getFormat().equals("date-time")){
            return "dateTime";
        }

        if(p.getEnumeration() != ""){
            return "enum(" + p.getEnumeration().substring(1, p.getEnumeration().length() - 1) + ")";
        }
        else return p.getType();
    }
}
