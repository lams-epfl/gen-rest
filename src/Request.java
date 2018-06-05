import java.util.ArrayList;

public class Request {

    private String tags, description, in, out, path;
    private Verbs verb;
    private ArrayList<Schema> schema;

    public Request() {
        tags = description = in = out = path = "";
        verb = Verbs.NO;
    }

    public String tags() {return tags;}
    public String description() {return description;}
    public Verbs verb() {return verb;}
    public String in() {return transformIn();}
    public String out() {return transformOut();}
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
        if (!in().equals("")) {
            throw new IllegalStateException("The request already has a in: " + in());
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

    public String transformIn() {
        return transform(in);
    }

    public String transformOut() {
        return transform(out);
    }

    public String transform(String s) {
        String result = "";

        if (s.contains("{")) {
            String[] parsed = s.split("\\{|\\}");
            if (parsed.length != 2) {
                throw new IllegalArgumentException("The parameter does not satisfy the required syntax:\n" + s);
            } else {
                Schema sch = new Schema(parsed[0], parsed[1]);
                schema.add(sch);

                осталось правильно добавить съему в документ и посмотреть завивисомть от гет, пост и пут
            }

            for (int i = 0; i < parsed.length; i++) {
                //result = result + parsed[i];
                System.out.println(parsed[i]);
            }
        }



        return result;
    }

    public String getParameters(String s) {
        return null;
    }


    public void print() {
        System.out.println("Request:" +
                "\nTag: " + tags() +
                "\nDescription: " + description() +
                "\nVerb: " + verb() +
                "\nIn: " + in() +
                "\nOut: " + out() +
                "\nPath: " + path() + "\n");
    }


}
