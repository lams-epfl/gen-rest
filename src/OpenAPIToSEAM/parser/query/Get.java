package OpenAPIToSEAM.parser.query;

import com.fasterxml.jackson.databind.ObjectMapper;
import OpenAPIToSEAM.parser.Component;
import OpenAPIToSEAM.parser.Query;
import OpenAPIToSEAM.parser.Schema.Paths.Properties;
import OpenAPIToSEAM.parser.Utils;
import OpenAPIToSEAM.parser.query.attributesGet.Parameters;
import OpenAPIToSEAM.parser.query.attributesGet.Response;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Get implements Query {

    private List<String> tags;
    private String description;
    private Parameters parameters;
    private Response schema;

    public Get(Object get){
        Map<String, Object> map = new HashMap<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, get);
            map = om.readValue(sw.toString(), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        tags = (ArrayList<String>) map.get("tags");
        description = (String) map.get("description");
        parameters = new Parameters(map.get("parameters"));
        schema = new Response(map.get("responses"));
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

    public Parameters getParameters() {
        return parameters;
    }

    public Response getSchema() {
        return schema;
    }

    @Override
    public String getType() {
        return "get";
    }


    private String genQuery(String IO) {
        String queryIn= "";
        if(IO.equals("in")){
            int sizeList = parameters.getParameters().size();
            int currSize = 0;
            for(Parameters.Parameter p : parameters.getParameters()){
                currSize++;
                queryIn += p.getName() + ": " + p.getSchema().getType() + ((currSize == sizeList) ? "" : ", ");
            }
        } else if (IO.equals("out")){
            if(schema.getContent() != null && schema.getContent().getSchema() != null && schema.getContent().getSchema().getProperties() != null){
                Map<String, OpenAPIToSEAM.parser.Schema.Paths.Properties.Property> map = schema.getContent().getSchema().getProperties().getProp();
                for(Map.Entry<String, Properties.Property> e : map.entrySet()){
                    queryIn += e.getKey() + ": " + analyseProp(e.getValue());
                }
            }

        }
        return queryIn;
    }

    private String analyseProp(OpenAPIToSEAM.parser.Schema.Paths.Properties.Property p){
        String r = "";
        if(p.getType().equals("array")){
            String[] name = p.getRef().split("/");
            String ref = name[name.length - 1];
            r = p.getType() + "(" + ref.substring(0, ref.length() - 1) + ")";
        }

        return r;
    }

}
