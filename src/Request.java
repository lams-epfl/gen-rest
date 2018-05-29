public class Request {

    private String tags, description, verb, in, out, path;

    public Request() {
        tags = description = verb = in = out = path = "";
    }

    public String tags() {return tags;}
    public String description() {return description;}
    public String verb() {return verb;}
    public String in() {return in;}
    public String out() {return out;}
    public String path() {return path;}

    public void setTags(String tags) {
        if (tags.equals("")) {
            throw new IllegalStateException("The request already has a tag: " + tags);
        }
        this.tags = tags;
    }

    public void setDescription(String description) {
        if (description.equals("")) {
            throw new IllegalStateException("The request already has a description.");
        }
        this.description = description;
    }

    public void setVerb(String verb) {
        if (verb.equals("")) {
            throw new IllegalStateException("The request already has a verb.");
        }
        this.verb = verb;
    }

    public void setIn(String in) {
        if (in.equals("")) {
            throw new IllegalStateException("The request already has a in.");
        }
        this.in = in;
    }

    public void setOut(String out) {
        if (out.equals("")) {
            throw new IllegalStateException("The request already has a out.");
        }
        this.out = out;
    }

    public void setPath(String path) {
        if (path.equals("")) {
            throw new IllegalStateException("The request already has a path.");
        }
        this.path = path;
    }

    public void print() {
        System.out.println("Tag: " + tags +
                "\nDescription: " + description +
                "\nVerb: " + verb +
                "\nIn: " + in +
                "\nOut: " + out +
                "\nPath: " + path + "\n");
    }


}
