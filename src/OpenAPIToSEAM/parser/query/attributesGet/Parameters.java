package OpenAPIToSEAM.parser.query.attributesGet;

import com.fasterxml.jackson.databind.ObjectMapper;
import OpenAPIToSEAM.parser.Schema.Paths.Schema;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parameters {

    List<Parameter> parameters = new ArrayList<>();

    public Parameters(Object o){
        List<Object> map = new ArrayList<>();
        ObjectMapper om = new ObjectMapper();
        StringWriter sw = new StringWriter();
        try {
            om.writeValue(sw, o);
            map = om.readValue(sw.toString(), List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Object p : map){
            parameters.add(new Parameter(p));
        }
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public class Parameter{
        private String name = "";
        private String in = "";
        private Boolean required;
        private Schema schema;

        public Parameter(Object o){
            Map<String, Object> map = new HashMap<>();
            ObjectMapper om = new ObjectMapper();
            StringWriter sw = new StringWriter();
            try {
                om.writeValue(sw, o);
                map = om.readValue(sw.toString(), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            name = map.get("name").toString();
            in = map.get("in").toString();
            required = Boolean.valueOf(map.get("required").toString());
            schema = new Schema(map.get("schema"));
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public String getIn() {
            return in;
        }

        public void setId(String in) {
            this.in = in;
        }

        public Boolean getRequired() {
            return required;
        }

        public void setRequired(Boolean required) {
            this.required = required;
        }

        public Schema getSchema() {
            return schema;
        }

        public void setSchema(Schema schema) {
            this.schema = schema;
        }
    }
}
