import java.util.ArrayList;

/**
 * A node.
 * @author Natalia Nessler
 */

public class Node {

    private Nodes list = null;
    private Integer id = null;
    private String kind = "";
    private String name = "";
    private String composite = "";
    private String stereotype = "";
    private String alignment = "";

    /**
     * Constructor for a node with given parameters.
     *
     * @param list a list of nodes this node belongs to.
     * @param id a unique id characterizing the node.
     * @param kind
     * @param name
     * @param composite
     * @param stereotype
     * @param alignment
     */
    public Node(Nodes list, Integer id, String kind, String name, String composite, String stereotype, String alignment) {
        this.list = list;
        this.id = id;
        this.kind = kind;
        this.name = name;
        this.composite = composite;
        this.stereotype = stereotype;
        this.alignment = alignment;
    }

    /**
     * Constructor for a default node with null list, null id and empty string parameters.
     */
    public Node() {
        this(null, null, "", "", "", "", "");
    }

    /**
     * Checks whether this node belongs to a list of nodes.
     *
     * @return true if this node belongs to a list of nodes.
     */
    public boolean hasList() { return list != null; }

    /**
     * Checks whether this node has an id.
     *
     * @return true if this node has an id.
     */
    public boolean hasId() { return id != null; }

    /**
     * Checks whether this node has a non-empty kind.
     *
     * @return true if this node has a non-empty kind.
     */
    public boolean hasKind() { return !kind.equals(""); }

    /**
     * Checks whether this node has a non-empty name.
     *
     * @return true if this node has a non-empty name.
     */
    public boolean hasName() {
        return !name.equals("");
    }

    /**
     * Checks whether this node has a non-empty composite.
     *
     * @return true if this node has a non-empty composite.
     */
    public boolean hasComposite() {
        return !composite.equals("");
    }

    /**
     * Checks whether this node has a non-empty stereotype.
     *
     * @return true if this node has a non-empty stereotype.
     */
    public boolean hasStereotype() { return !stereotype.equals(""); }

    /**
     * Checks whether this node is an API action.
     *
     * @return true if this node is a POST, PUT or GET.
     */
    public boolean isRequest() { return hasStereotype() && (stereotype.equals("POST") || stereotype.equals("GET") || stereotype.equals("PUT")); }

    /**
     * Checks whether this node has a non-empty alignment.
     *
     * @return true if this node has a non-empty alignment.
     */
    public boolean hasAlignment() { return !alignment.equals(""); }

/*    /**
     * Checks whether this node belongs to a given list of nodes.
     * @throws NullPointerException if this node has no id.
     * @return true if this node belongs to a given list of nodes.

    public boolean belongsTo(Nodes ns) {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        return list.equals(ns);
    }*/

    /**
     * @throws NullPointerException if this node has no id.
     * @throws NullPointerException if this node doesn't belong to a list nodes.
     * @return the list this node belongs to.
     */
    public Nodes list() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (!hasList()) {
            throw new NullPointerException("This Node doesn't belong to a list of nodes.");
        }

        return list;
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @return the id of this node.
     */
    public int id() {
        if (!hasId()) {
            throw new NullPointerException("This Node has no id.");
        }
        return id;
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @return the kind of this node.
     */
    public String kind() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        return kind;
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @return the name of this node.
     */
    public String name() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        return name;
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @return the composite of this node.
     */
    public String composite() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        return composite;
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @return the stereotype of this node.
     */
    public String stereotype() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        return stereotype;
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @return the alignment of this node.
     */
    public String alignment() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        return alignment;
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @throws NullPointerException if this node doesn't belong to a list of nodes.
     * @return the list of edges pointing to or out of this node.
     */
    public Edges edges() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (!hasList()) {
            throw new NullPointerException("This Node doesn't belong to a list of nodes.");
        }
        //this Node -> list of Nodes -> Data -> list of Edges -> filters them by id of this Node
        return list().getData().getEdges().filter(edge -> edge.source() == id() || edge.target() == id());
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @throws NullPointerException if this node doesn't belong to a list of nodes.
     * @return the list of edges pointing out of this node.
     */
    public Edges edgesOut() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (!hasList()) {
            throw new NullPointerException("This Node doesn't belong to a list of nodes.");
        }
        //this Node -> list of Nodes -> Data -> list of Edges -> filters them by source id corresponding to this Node
        return edges().filter(edge -> edge.nodes().source().id == id());
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @throws NullPointerException if this node doesn't belong to a list of nodes.
     * @return the list of edges pointing to this node.
     */
    public Edges edgesIn() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (!hasList()) {
            throw new NullPointerException("This Node doesn't belong to a list of nodes.");
        }
        //this Node -> list of Nodes -> Data -> list of Edges -> filters them by target id corresponding to this Node
        return edges().filter(edge -> edge.nodes().target().id == id());
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @throws NullPointerException if this node doesn't belong to a list of nodes.
     * @return the list of nodes connected to this node via an edge.
     */
    public Nodes neighbors() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (!hasList()) {
            throw new NullPointerException("This Node doesn't belong to a list of nodes.");
        }
        Edges edges = this.edges();
        Nodes nodes = new Nodes();

        //runs through all the edges of this node and adds the "other" node to the returned list
        for (int i = 0; i < edges.size(); i++) {
            Node n = edges.get(i).nodes().source().equals(this) ? edges.get(i).nodes().target() : edges.get(i).nodes().source();
//            //creates a copy of the found node because the original one already belongs to a list and cannot be added to another one
//            nodes.add(new Node(null, n.id(), n.kind(), n.name(), n.composite(), n.stereotype(), n.alignment()));
            nodes.add(n);
        }
        return nodes;
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @throws NullPointerException if this node doesn't belong to a list of nodes.
     * @return the list of nodes connected to this node via an outgoing edge.
     */
    public Nodes neighborsOut() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (!hasList()) {
            throw new NullPointerException("This Node doesn't belong to a list of nodes.");
        }
        Edges edges = this.edgesOut();
        Nodes nodes = new Nodes();
        //runs through all the outgoing edges of this node and adds the "other" node to the returned list
        for (int i = 0; i < edges.size(); i++) {
            Node n = edges.get(i).nodes().target();
            //creates a copy of the found node because the original one already belongs to a list and cannot be added to another one
            //nodes.add(new Node(null, n.id(), n.kind(), n.name(), n.composite(), n.stereotype(), n.alignment()));
            nodes.add(n);
        }

        return nodes;
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @throws NullPointerException if this node doesn't belong to a list of nodes.
     * @return the list of nodes connected to this node via an incoming edge.
     */
    public Nodes neighborsIn() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (!hasList()) {
            throw new NullPointerException("This Node doesn't belong to a list of nodes.");
        }
        Edges edges = this.edgesIn();
        Nodes nodes = new Nodes();
        //runs through all the incoming edges of this node and adds the "other" node to the returned list
        for (int i = 0; i < edges.size(); i++) {
            Node n = edges.get(i).nodes().source();
            //creates a copy of the found node because the original one already belongs to a list and cannot be added to another one
            //nodes.add(new Node(null, n.id(), n.kind(), n.name(), n.composite(), n.stereotype(), n.alignment()));
            nodes.add(n);
        }
        return nodes;
    }



    public Nodes localActions() {
        return neighborsOut().filter(node -> node.kind().contains("localised_action"));
    }

    public Nodes localProperties() {
        return neighborsOut().filter(node -> node.kind().contains("localised_property"));
    }

    public Nodes nodeComposite() {
        return neighbors().filter(node -> node.hasComposite());
    }

    public Nodes requests() {
        return neighborsOut().filter(node -> node.stereotype().contains("POST") || node.stereotype().contains("GET") || node.stereotype().contains("PUT"));
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @throws NullPointerException if this node doesn't belong to a list of nodes.
     * @return the corresponding CREATE node.
     */
    public Nodes getCreate() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (!hasList()) {
            throw new NullPointerException("This Node doesn't belong to a list of nodes.");
        }
        return this.neighbors().filter(node -> node.name().startsWith("CREATE"));
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @throws NullPointerException if this node doesn't belong to a list of nodes.
     * @return the corresponding READ node.
     */
    public Nodes getRead() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (!hasList()) {
            throw new NullPointerException("This Node doesn't belong to a list of nodes.");
        }
        return this.neighbors().filter(node -> node.name().startsWith("READ"));
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @throws NullPointerException if this node doesn't belong to a list of nodes.
     * @return the corresponding UPDATE node.
     */
    public Nodes getUpdate() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (!hasList()) {
            throw new NullPointerException("This Node doesn't belong to a list of nodes.");
        }
        return this.neighbors().filter(node -> node.name().startsWith("UPDATE"));
    }

    /**
     * @throws NullPointerException if this node has no id.
     * @throws NullPointerException if this node doesn't belong to a list of nodes.
     * @return the corresponding DELETE node.
     */
    public Nodes getDelete() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (!hasList()) {
            throw new NullPointerException("This Node doesn't belong to a list of nodes.");
        }
        return this.neighbors().filter(node -> node.name().startsWith("DELETE"));
    }

    /**
     * Makes this node part of the given list of nodes.
     * @throws NullPointerException if this node has no id.
     * @throws IllegalStateException if this node already belongs to a list of nodes.
     * @param nodes the list of nodes being added.
     */
    public void addList(Nodes nodes) {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (!hasList()) {
            //throw new IllegalStateException("The Node already belongs to a list of nodes.");
            this.list = nodes;
        }

    }

    /**
     * Adds an id to this node. Should be done before any other action on this node.
     * @throws IllegalStateException if this node already has an id.
     * @param id the id being added: can be positive, negative or zero.
     */
    public void addId(Integer id) {
        if (hasId()) {
            throw new IllegalStateException("The Node already has an id.");
        }
        this.id = id;
    }

    /**
     * Adds a kind to this node.
     * @throws NullPointerException if this node has no id.
     * @throws IllegalStateException if this node already has a non-empty kind.
     * @param kind the kind being added: can be empty.
     */
    public void addKind(String kind) {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (hasKind()) {
            throw new IllegalStateException("The Node already has a kind.");
        }
        this.kind = kind;
    }

    /**
     * Adds a name to this node.
     * @throws NullPointerException if this node has no id.
     * @throws IllegalStateException if this node already has a non-empty name.
     * @param name the name being added: can be empty.
     */
    public void addName(String name) {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (hasName()) {
            throw new IllegalStateException("The Node already has a name.");
        }
        this.name = name;
    }

    /**
     * Adds a composite to this node.
     * @throws NullPointerException if this node has no id.
     * @throws IllegalStateException if this node already has a non-empty composite.
     * @param composite the composite being added: can be empty.
     */
    public void addComposite(String composite) {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (hasComposite()) {
            throw new IllegalStateException("The Node already has a composite.");
        }
        this.composite = composite;
    }

    /**
     * Adds a stereotype to this node.
     * @throws NullPointerException if this node has no id.
     * @throws IllegalStateException if this node already has a non-empty stereotype.
     * @param stereotype the stereotype being added: can be empty.
     */
    public void addStereotype(String stereotype) {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (hasStereotype()) {
            throw new IllegalStateException("The Node already has a stereotype.");
        }
        this.stereotype = stereotype;
    }

    /**
     * Adds an alignment to this node.
     * @throws NullPointerException if this node has no id.
     * @throws IllegalStateException if this node already has a non-empty alignment.
     * @param alignment the alignment being added: can be empty.
     */
    public void addAlignment(String alignment) {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (hasAlignment()) {
            throw new IllegalStateException("The Node already has a stereotype.");
        }
        this.alignment = alignment;
    }

    /**
     * Makes this node forget the list of nodes it belongs to.
     * @throws NullPointerException if this node has no id.
     * @throws IllegalStateException if this node doesn't belong to a list of nodes.
     * @return the list of nodes being removed from the node.
     */
    public Nodes removeList() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        if (!hasList()) {
            throw new IllegalStateException("The Node doesn't belong to a list of nodes.");
        }
        Nodes nodes = list;
        this.list = null;
        return nodes;
    }

    /**
     *
     * @return
     */
    public boolean canRequest() {
        return !neighborsOut().filter(node -> node.isRequest()).isEmpty();
    }

    /**
     *
     */
    public Node clone() {
        return new Node(list(), id(), kind(), name(), composite(), stereotype(), alignment());
    }


    /**
     * Prints this node, i.e. its id and all its non-empty string parameters.
     * @throws NullPointerException if this node has no id.
     *
     */
    public void print() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
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

    /**
     * Prints this node, its outgoing edges and the nodes they are pointing to.
     * @throws NullPointerException if this node has no id.
     */
    public void printBelowStructure() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        System.out.println("            THIS NODE:");
        print();
        System.out.println("            HAS THE FOLLOWING EDGES GOING OUT OF IT:");
        edgesOut().print();
        System.out.println("            POINTING TO THE FOLLOWING NODES:");
        neighborsOut().print();

    }

    /**
     * Prints this node, its incoming edges and the nodes they are coming from.
     * @throws NullPointerException if this node has no id.
     */
    public void printAboveStructure() {
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        System.out.println("            THIS NODE:");
        print();
        System.out.println("            HAS THE FOLLOWING EDGES POINTING TO IT:");
        edgesIn().print();
        System.out.println("            COMING OUT OF THE FOLLOWING NODES:");
        neighborsIn().print();

    }

    /**
     * Prints recursively all the outgoing edges, their nodes, their outgoing edges, etc.
     * @throws NullPointerException if this node has no id.
     * @param distance the number of edges until the original node to which unfoldBelow() is applied.
     */
    public void unfoldBelow(int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("The distance must be positive.");
        }
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        print();
        for (int i = 0; i < edgesOut().size(); i++) {
            edgesOut().get(i).unfoldBelow(distance + 1);
        }
    }

    /**
     * Prints recursively all the outgoing edges, their nodes, their outgoing edges, etc.
     */
    public void unfoldBelow() { unfoldBelow(0); }

    /**
     * Prints recursively all the incoming edges, their nodes, their incoming edges, etc.
     * @throws NullPointerException if this node has no id.
     * @param distance the number of edges until the original node to which unfoldAbove() is applied.
     */
    public void unfoldAbove(int distance) {
        if (distance < 0) {
            throw new IllegalArgumentException("The distance must be positive.");
        }
        if (!hasId()) {
            throw new NullPointerException("This Node does not exist.");
        }
        print();
        for (int i = 0; i < edgesIn().size(); i++) {
            edgesIn().get(i).unfoldAbove(distance + 1);
        }
    }

    /**
     * Prints recursively all the incoming edges, their nodes, their incoming edges, etc.
     */
    public void unfoldAbove() {
        unfoldAbove(0);
    }




}

