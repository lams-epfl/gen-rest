public class Edge {

    private int id = 0;
    private String kind = "";
    private String source = "";
    private String target = "";
    private String stereotype = "";


    public Edge(int id, String kind, String source, String target, String stereotype) {
        this.id = id;
        this.kind = kind;
        this.source = source;
        this.target = target;
        this.stereotype = stereotype;
    }

    public int getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public String getSource() {
        return source;
    }


    public String getTarget() {
        return target;
    }


    public String getStereotype() {
        return stereotype;
    }

    public void print() {
        System.out.println("Node: " + id);
        System.out.println("Kind: " + kind);
        System.out.println("Source: " + source);
        System.out.println("Target: " + target);
        System.out.println("Stereotype: " + stereotype + "\n");

    }


}

