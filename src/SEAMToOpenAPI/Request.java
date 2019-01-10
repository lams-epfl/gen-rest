package SEAMToOpenAPI;

import java.util.ArrayList;

public class Request {

    private String tags, description, in, out, path;
    private Verbs verb;
    private ArrayList<Schema> schema = new ArrayList<>();
    private Schemas schemas = new Schemas();

    public Request(Schemas schemas) {
        tags = description = in = out = path = "";
        verb = Verbs.NO;
        this.schemas = schemas;
    }

    public Request() {
        this(new Schemas());
    }

    public String tags() {return tags;}
    public String description() {return description;}
    public Verbs verb() {return verb;}
    public String in(int n) {return transformIn(n);}
    public String out() {return out;}
    public String path() {return path;}

    public void setTags(String tags) {
        if (!tags().equals("")) {
            throw new IllegalStateException("The request already has a tag: " + tags());
        }
        this.tags = tags;
    }

    public void setDescription(String description) {
        if (!description().equals("")) {
            throw new IllegalStateException("The request already has a description: " + description());
        }
        this.description = description;
    }

    public void setVerb(String verb) {
        if (!verb().equals(Verbs.NO)) {
            throw new IllegalStateException("The request already has a verb: " + verb());
        }
        switch (verb.toLowerCase()) {
            case "post":
                this.verb = Verbs.POST;
                break;
            case "delete":
                this.verb = Verbs.DELETE;
                break;
            case "get":
                this.verb = Verbs.GET;
                break;
            case "put":
                this.verb = Verbs.PUT;
                break;
        }

    }

    public void setIn(String in) {
        if (!this.in.equals("")) {
            throw new IllegalStateException("The request already has a in: " + this.in);
        }
        this.in = in;
    }

    public void setOut(String out) {
        if (!out().equals("")) {
            throw new IllegalStateException("The request already has a out: " + out());
        }
        this.out = out;
    }

    public void setPath(String path) {
        if (!path().equals("")) {
            throw new IllegalStateException("The request already has a path: " + path());
        }
        this.path = path;
    }

    public String transformIn(int n) {
        String s = in;

        String result = "";

        if (verb.isPOST() || verb.isPUT()) {
            result = Parser.line() +
                    Parser.tab(n) + "requestBody:" + Parser.line() +
                    Parser.tab(n+1) + "content:" + Parser.line() +
                    Parser.tab(n+2) + "application/json:" + Parser.line() +
                    Parser.tab(n+3) + "schema:";


            result = result + getObject(s, n+4) + Parser.line() + Parser.tab(n+1)+ "required: true";


        } else if (verb.isGET()){
            String intermediate = Parser.line() +
                    Parser.tab(n) + "parameters:" + Parser.line() +
                    Parser.tab(n+1) + "- name: %s" + Parser.line() +
                    Parser.tab(n+2) + "in: query" + Parser.line() +
                    Parser.tab(n+2) + "required: true" + Parser.line() +
                    Parser.tab(n+2) + "schema:" + Parser.line() +
                    Parser.tab(n+3) + "type: %s";

            String[] p = s.split(", \\\\n|,\\\\n|, | \\\\n|\\\\n");

            for (int i = 0; i < p.length; i++) {
                if (!p[i].isEmpty()) {
                    String properties[] = p[i].split(": ");
                    if (properties.length != 2) {
                        throw new IllegalArgumentException("The properties do not satisfy the required syntax:\n" + s);
                    } else {
                        if (properties[1].equals("dateTime")) {
                            properties[1] = "string" + Parser.line() + Parser.tab(n + 3) + "format: date-time";
                        }

                        intermediate = String.format(intermediate, properties[0], properties[1]);
                        result = result + intermediate;
                    }
                }
            }
        }

        return result;
    }

    public String transformOut(int n) {
        String result = Parser.line() +
                Parser.tab(n) + "responses:" + Parser.line() +
                Parser.tab(n+1) + "'200':" + Parser.line() +
                Parser.tab(n+2) + "description: ";
        String s = out;

        if (verb.isPUT()) {
            result = result + "OK";
        } else if (verb.isPOST() || verb.isGET()) {
            result = result + "request successful" + Parser.line() +
                    Parser.tab(n+2) + "content:" + Parser.line() +
                    Parser.tab(n+3) + "application/json:" + Parser.line() +
                    Parser.tab(n+4) + "schema:" + getObject(s, n+5);
        }

        return result;
    }

    public String parseWith(String s, int n) {
        String result = Parser.line() +
                Parser.tab(n);
        String[] parsed = s.split(" \\{|\\{|\\}");
        if (parsed.length != 2) {
            throw new IllegalArgumentException("The parameter does not satisfy the required syntax:\n" + s);
        } else {
            if (schemas.filter(schem -> schem.name().equals(parsed[0])).isEmpty()) {
                Schema sch = new Schema(parsed[0], parsed[1]);
                schemas.add(sch);
                result = result + "$ref: '#/components/schemas/" + sch.name() + "'";
            } else {
                result = result + "type: array" + Parser.line() +
                        Parser.tab(n) + "items:" + Parser.line() +
                        Parser.tab(n+1) + "$ref: '#/components/schemas/" + parsed[0] + "'";
            }
        }
        return result;
    }

    public String parseWithout(String s, int n) {

        String result = Parser.line() +
                Parser.tab(n) +  "type: object" + Parser.line() +
                Parser.tab(n) + "properties:";

        String[] p = s.split(", \\\\n|,\\\\n|, | \\\\n|\\\\n");

        for (int i = 0; i < p.length; i++) {
            if (!p[i].isEmpty()) {
                String properties[] = p[i].split(": ");
                if (properties.length != 2) {
                    throw new IllegalArgumentException("The properties do not satisfy the required syntax:\n" + s);
                } else {
                    String type = Parser.line() +
                            Parser.tab(n+2) + "type: ";
                    if (properties[1].contains("array")) {
                        String elem = properties[1].split("array\\(|\\)")[1];
                        if (!schemas.filter(schema1 -> schema1.name().equals(elem)).isEmpty()) {
                            type = type + "array" + Parser.line() +
                                    Parser.tab(n+2) + "items:" + Parser.line() +
                                    Parser.tab(n+3) + "$ref: '#/components/schemas/" + elem + "'";
                        } else {
                            type = type + "array" + Parser.line() +
                                    Parser.tab(n + 2) + "items:" + Parser.line() +
                                    Parser.tab(n + 3) + "type: " + elem;
                        }
                    } else if (properties[1].contains("enum")) {
                        type = type + "string"  + Parser.line() +
                                Parser.tab(n+2) + "enum:";
                        String[] enumeration = properties[1].split("\\(|\\)|,");
                        for (int j = 1; j < enumeration.length; j++) {
                            type = type + Parser.line() +
                                    Parser.tab(n+3) + "- " + enumeration[j];
                        }
                    } else if (properties[1].equals("dateTime")) {
                        type = type + "string" + Parser.line() +
                                Parser.tab(n+2) + "format: date-time";
                    } else {
                        type = type + properties[1];
                    }
                    result = result + Parser.line() +
                            Parser.tab(n+1) + properties[0] + ":" + type;

                }
            }
        }
        return result;
    }

    public String getObject(String s, int n) {
        String result = "";
        if (s.contains("{")) {
            result = result + parseWith(s, n);
        } else {
            result = result + parseWithout(s, n);
        }
        return result;
    }

    public String getParameters(int n) {

        String result = "";

        if (verb.isGET()) {
            result = transformIn(n) + transformOut(n);
        } else if (verb.isPUT() || verb.isPOST()) {
            result = transformOut(n) + transformIn(n);
        }

        return result;
    }


    public void print() {
        System.out.println("Request:" +
                "\nTag: " + tags() +
                "\nDescription: " + description() +
                "\nVerb: " + verb() +
                "\nIn: " + in +
                "\nOut: " + out() +
                "\nPath: " + path() + "\n");
    }


}
