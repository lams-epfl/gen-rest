public class Node {

    private int id = 0;
    private String kind = "";
    private String name = "";
    private String composite = "";
    private String stereotype = "";
    private boolean visible = false;

    public Node(int id, String kind, String name, String composite, String stereotype) {
        this.id = id;
        this.kind = kind;
        this.name = name;
        this.composite = composite;
        this.stereotype = stereotype;

        if (!kind.equals("") || !name.equals("") || !composite.equals("") || !stereotype.equals("")) {
            this.visible = true;
        }
    }

    public Node(int id) {
        this(id, "", "", "", "");
    }

    public Node(int id, String kind) {
        this(id, kind, "", "", "");
    }

    public Node() {
        this(0, "", "", "", "");
    }

    public int getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public String getName() {
        return name;
    }

    public String getComposite() {
        return composite;
    }


    public String getStereotype() {
        return stereotype;
    }

    public boolean isVisible() {
        return visible;
    }

    public void addId(int id) {
        if (this.id!=0) {
            throw new IllegalStateException("The Node already has an id.");
        }
        this.id = id;
    }

    public void addKind(String kind) {
        if (!this.kind.equals("")) {
            throw new IllegalStateException("The Node already has a kind.");
        }
        this.kind = kind;
        makeVisible();
    }

    public void addName(String name) {
        if (!this.name.equals("")) {
            throw new IllegalStateException("The Node already has a name.");
        }
        this.name = name;
        makeVisible();
    }

    public void addComposite(String composite) {
        if (!this.composite.equals("")) {
            throw new IllegalStateException("The Node already has a composite.");
        }
        this.composite = composite;
        makeVisible();
    }

    public void addStereotype(String stereotype) {
        if (!this.stereotype.equals("")) {
            throw new IllegalStateException("The Node already has a stereotype.");
        }
        this.stereotype = stereotype;
        makeVisible();
    }

    public void makeVisible() {
        visible = true;
    }

    public void makeInvisible() {
        visible = false;
    }

    public void print() {
        System.out.println("Node: " + id);
        System.out.println("Kind: " + kind);
        System.out.println("Name: " + name);
        System.out.println("Composite: " + composite);
        System.out.println("Stereotype: " + stereotype + "\n");
    }

}

