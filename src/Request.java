public class Request {

    private String tags, description, in, out, path;
    private Verbs verb;

    public Request() {
        tags = description = in = out = path = "";
        verb = Verbs.NO;
    }

    public String tags() {return tags;}
    public String description() {return description;}
    public Verbs verb() {return verb;}
    public String in() {return in;}
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

    public boolean isVerb(String s) {
        for (int i = 0; i < Verbs.values().length-1; i++) { // length-1 because the last verb is NO which is not a verb
            if (Verbs.values()[i].toString().equals(s)) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        System.out.println("Tag: " + tags +
                "\nDescription: " + description +
                "\nVerb: " + verb() +
                "\nIn: " + in +
                "\nOut: " + out +
                "\nPath: " + path + "\n");
    }


}
