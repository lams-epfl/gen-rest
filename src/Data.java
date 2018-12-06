public class Data {

    private Nodes nodes = null;
    private Edges edges = null;

    public Data(Nodes nodes, Edges edges) {
        this.nodes = nodes;
        this.edges = edges;

        if(nodes != null) {
            nodes.addData(this);
        }

        if(edges != null) {
            edges.addData(this);
        }
    }

    public Data() {
        this(null, null);
    }

    public boolean hasNodes() { return nodes != null; }

    public boolean hasEdges() { return edges != null; }

    public Nodes getNodes() {
        return nodes;
    }

    public Edges getEdges() {
        return edges;
    }

    public Node root() {
        if (!hasNodes()) {
            throw new NullPointerException("The Data has no Nodes");
        }
        Node n = getNodes().get(0);
        if (!n.neighborsIn().isEmpty()) {
            throw new NullPointerException("The first Node in the list is not the root... :( At least you tried!");
        }
        return n;
    }

    public Nodes workingObjects() {
        return nodes.filter(node -> node.kind().contains("working_object") && !node.composite().equals("true"));
    }

    public void addNodes(Nodes nodes) {
        if (hasNodes()) {
            throw new IllegalStateException("The Data already has nodes.");
        }
        this.nodes = nodes;
        nodes.addData(this);
    }

    public void addEdges(Edges edges) {
        if (hasEdges()) {
            throw new IllegalStateException("The Data already has edges.");
        }
        this.edges = edges;
        edges.addData(this);
    }

    public Nodes removeNodes() {
        if (!hasNodes()) {
            throw new IllegalStateException("The Data has no nodes to remove.");
        }
        Nodes ns = nodes;
        nodes.removeData();
        this.nodes = null;
        return ns;
    }

    public Edges removeEdges() {
        if (!hasEdges()) {
            throw new IllegalStateException("The Data has no edges to remove.");
        }
        Edges es = edges;
        edges.removeData();
        this.edges = null;
        return es;
    }

    public void print() {
        nodes.print();
        edges.print();
    }


}
