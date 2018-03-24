public class Node {

    private Nodes list = null;
    private Integer id = null;
    private String kind = "";
    private String name = "";
    private String composite = "";
    private String stereotype = "";
    private String alignment = "";

    public Node(Nodes list, Integer id, String kind, String name, String composite, String stereotype, String alignment) {
        this.list = list;
        this.id = id;
        this.kind = kind;
        this.name = name;
        this.composite = composite;
        this.stereotype = stereotype;
        this.alignment = alignment;
    }

    public Node() {
        this(null, null, "", "", "", "", "");
    }

    public boolean hasList() { return list != null; }

    public boolean hasId() { return id != null; }

    public boolean hasKind() { return !kind.equals(""); }

    public boolean hasName() {
        return !name.equals("");
    }

    public boolean hasComposite() {
        return !composite.equals("");
    }

    public boolean hasStereotype() { return !stereotype.equals(""); }

    public boolean hasAlignment() { return !alignment.equals(""); }

    public boolean belongsTo(Nodes ns) {
        return list.equals(ns);
    }

    public Nodes getList() { return list; }

    public int getId() { return id; }

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

    public String getAlignment() { return alignment; }

    public Edges getEdges() {
        return this.list.getData().getEdges().filter(edge -> edge.getSource() == getId() || edge.getTarget() == getId());
    }

    public Edges getEdgesOut() {
        return this.list.getData().getEdges().filter(edge -> edge.getSource() == getId());
    }

    public Edges getEdgesIn() {
        return this.list.getData().getEdges().filter(edge -> edge.getTarget() == getId());
    }

    public Nodes getNeighbors() {
        Edges es = this.getEdges();
        Nodes ns = new Nodes();
        for (int i = 0; i < es.size(); i++) {
            Node n = (es.get(i).getNodes().get(0).equals(this)) ? es.get(i).getNodes().get(1) : es.get(i).getNodes().get(0);
            ns.add(new Node(null, n.getId(), n.getKind(), n.getName(), n.getComposite(), n.getStereotype(), n.getAlignment()));
        }
        return ns;
    }

    public Nodes getNeighborsOut() {
        Edges es = this.getEdgesOut();
        Nodes ns = new Nodes();
        for (int i = 0; i < es.size(); i++) {
            Node n = (es.get(i).getNodes().get(0).equals(this)) ? es.get(i).getNodes().get(1) : es.get(i).getNodes().get(0);
            ns.add(new Node(null, n.getId(), n.getKind(), n.getName(), n.getComposite(), n.getStereotype(), n.getAlignment()));
        }
        return ns;
    }

    public Nodes getNeighborsIn() {
        Edges es = this.getEdgesIn();
        Nodes ns = new Nodes();
        for (int i = 0; i < es.size(); i++) {
            Node n = (es.get(i).getNodes().get(0).equals(this)) ? es.get(i).getNodes().get(1) : es.get(i).getNodes().get(0);
            ns.add(new Node(null, n.getId(), n.getKind(), n.getName(), n.getComposite(), n.getStereotype(), n.getAlignment()));
        }
        return ns;
    }



    public Nodes getCreate() {
        return this.getNeighbors().filter(node -> node.getName().startsWith("CREATE"));
    }

    public Nodes getRead() {
        return this.getNeighbors().filter(node -> node.getName().startsWith("READ"));
    }

    public Nodes getUpdate() {
        return this.getNeighbors().filter(node -> node.getName().startsWith("UPDATE"));
    }

    public Nodes getDelete() {
        return this.getNeighbors().filter(node -> node.getName().startsWith("DELETE"));
    }

    public void addList(Nodes ns) {
        if (hasList()) {
            throw new IllegalStateException("The Node already has a list.");
        }
        this.list = ns;
    }

    public void addId(Integer id) {
        if (hasId()) {
            throw new IllegalStateException("The Node already has an id.");
        }
        this.id = id;
    }

    public void addKind(String kind) {
        if (hasKind()) {
            throw new IllegalStateException("The Node already has a kind.");
        }
        this.kind = kind;
    }

    public void addName(String name) {
        if (hasName()) {
            throw new IllegalStateException("The Node already has a name.");
        }
        this.name = name;
    }

    public void addComposite(String composite) {
        if (hasComposite()) {
            throw new IllegalStateException("The Node already has a composite.");
        }
        this.composite = composite;
    }

    public void addStereotype(String stereotype) {
        if (hasStereotype()) {
            throw new IllegalStateException("The Node already has a stereotype.");
        }
        this.stereotype = stereotype;
    }

    public void addAlignment(String alignment) {
        if (hasAlignment()) {
            throw new IllegalStateException("The Node already has a stereotype.");
        }
        this.alignment = alignment;
    }

    public Nodes removeList() {
        if (!hasList()) {
            throw new IllegalStateException("The Node has no list to remove.");
        }
        Nodes ns = list;
        this.list = null;
        return ns;
    }

    public void print() {
        System.out.println("Node: " + id);

        if (hasKind()) {
            System.out.println("Kind: " + kind);
        }
        if (hasName()) {
            System.out.println("Name: " + name);
        }
        if (hasComposite()) {
            System.out.println("Composite: " + composite);
        }
        if (hasStereotype()) {
            System.out.println("Stereotype: " + stereotype);
        }
        if (hasAlignment()) {
            System.out.println("Alignment: " + alignment);
        }
        System.out.println();
    }

    public void printBelowStructure() {
        System.out.println("            THIS NODE:");
        print();
        System.out.println("            HAS THE FOLLOWING EDGES GOING FROM HIM:");
        getEdgesOut().print();
        System.out.println("            POINTING TO THE FOLLOWING NODES:");
        getNeighborsOut().print();

    }

    public void printAboveStructure() {
        System.out.println("            THIS NODE:");
        print();
        System.out.println("            HAS THE FOLLOWING EDGES GOING TO HIM:");
        getEdgesIn().print();
        System.out.println("            POINTING FROM THE FOLLOWING NODES:");
        getNeighborsIn().print();

    }

    public void unfoldBelow(int dist) {
        print();
        for (int i = 0; i < getEdgesOut().size(); i++) {
            getEdgesOut().get(i).unfoldBelow(dist + 1);
        }
    }

    public void unfoldBelow() {
        unfoldBelow(0);
    }

    public void unfoldAbove(int dist) {
        print();
        for (int i = 0; i < getEdgesIn().size(); i++) {
            getEdgesIn().get(i).unfoldAbove(dist + 1);
        }
    }

    public void unfoldAbove() {
        unfoldBelow(0);
    }



}

