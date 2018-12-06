import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

/**
 * An edge.
 * @author Natalia Nessler
 */
public class Edge {

    private Edges list = null;
    private Integer id = null;
    private Integer source = null;
    private Integer target = null;
    private String kind = "";
    private String name = "";
    private String stereotype = "";
    private String alignment = "";
    private String src_card = "";
    private String src_role = "";
    private Pair nodes = null;

    /**
     * Constructor for an edge with given parameters.
     * @throws IllegalArgumentException if the id, the source or the target are null.
     * @param list a list of edges this edge belongs to.
     * @param id a unique id characterizing the edge.
     * @param source the id of the node this edge is coming out.
     * @param target the id of the node this edge is pointing to.
     * @param kind
     * @param name
     * @param stereotype
     * @param alignment
     * @param src_card
     * @param src_role
     */
    public Edge(Edges list, @NotNull Integer id, @NotNull Integer source, @NotNull Integer target, String kind, String name, String stereotype, String alignment, String src_card, String src_role) {
        if (id == null) {
            throw new IllegalArgumentException("The Edge must have an id.");
        }
        if (source == null) {
            throw new IllegalArgumentException("The Edge must have a source.");
        }
        if (target == null) {
            throw new IllegalArgumentException("The Edge must have a target.");
        }
        this.list = list; // null at first, and added at the end of parsing
        this.id = id;
        this.source = source;
        this.target = target;
        this.kind = kind;
        this.name = name;
        this.stereotype = stereotype;
        this.alignment = alignment;
        this.src_card = src_card;
        this.src_role = src_role;
    }

    //public Edge() { this(null, null, null, null, "", "", "", "", "", ""); }

    /**
     * @return true if this edge belongs to a list of edges.
     */
    public boolean hasList() { return list != null; }

    /**
     * @return true if this edge has an id.
     */
    public boolean hasId() { return id != null; }

    /**
     * @return true if this edge has a source.
     */
    public boolean hasSource() { return source != null; }

    /**
     * @return true if this edge has a target.
     */
    public boolean hasTarget() { return target != null; }

    /**
     * @return true if this edge has a non-empty kind.
     */
    public boolean hasKind() { return !kind.equals(""); }

    /**
     * @return true if this edge has a non-empty name.
     */
    public boolean hasName() {
        return !name.equals("");
    }

    /**
     * @return true if this edge has a non-empty stereotype.
     */
    public boolean hasStereotype() { return !stereotype.equals(""); }

    /**
     * @return true if this edge has a non-empty alignment.
     */
    public boolean hasAlignment() { return !alignment.equals(""); }

    /**
     * @return true if this edge has a non-empty card.
     */
    public boolean hasCard() {
        return !src_card.equals("");
    }

    /**
     * @return true if this edge has a non-empty role.
     */
    public boolean hasRole() {
        return !src_role.equals("");
    }

    /**
     * @return true if this edge belongs to a given list.
     */
    public boolean belongsTo(Edges es) {
        if (!hasList()) {
            return false;
        }
        return list.equals(es);
    }

    /**
     * @throws NullPointerException if this edge doesn't belong to a list of edges.
     * @return the list this edge belongs to.
     */
    public Edges list() {
        if (!hasList()) {
            throw new NullPointerException("This Edge doesn't belong to a list of edges.");
        }
        return list; }

    /**
     * @return the id of this edge.
     */
    public int id() {
        return id;
    }

    /**
     * @return the source id of this edge.
     */
    public int source() {
        return source;
    }

    /**
     * @return the target id of this edge.
     */
    public int target() {
        return target;
    }

    /**
     * @return the kind of this edge.
     */
    public String kind() {
        return kind;
    }

    /**
     * @return the name of this edge.
     */
    public String name() {
        return name;
    }

    /**
     * @return the stereotype of this edge.
     */
    public String stereotype() {
        return stereotype;
    }

    /**
     * @return the alignment of this edge.
     */
    public String alignment() { return alignment; }

    /**
     * @return the card of this edge.
     */
    public String card() {
        return src_card;
    }

    /**
     * @return the role of this edge.
     */
    public String role() {
        return src_role;
    }


    /**@todo create a class of Pair
     * @throws NullPointerException if this edge doesn't belong to a list of edges.
     * @return the list of 2 nodes this edge connects.
     */
    /*public Nodes nodes() {
        if (!hasList()) {
            throw new NullPointerException("This Edge doesn't belong to a list of edges.");
        }
        //this Edge -> list of Edges -> Data -> list of Nodes -> filters them by source id or target id of this Edge
        return this.list.getData().getNodes().filter(node -> node.id() == source() || node.id() == target());
    }*/

    public Pair nodes() {
        if (!hasList()) {
            throw new NullPointerException("This Edge doesn't belong to a list of edges.");
        }
        //this Edge -> list of Edges -> Data -> list of Nodes -> filters them by source id or target id of this Edge
        return nodes;
    }

    /**
     * Makes this edge part of the given list of edges.
     * @throws if this edge already belongs to a list of edges.
     * @param edges the list of edges being added.
     */
    public void addList(Edges edges) {
        if (!hasList()) {
            //throw new IllegalStateException("This Edge already belongs to a list of edges.");
            list = edges;
            if (list.getData() != null){
                if(list.getData().getNodes() != null){
                    nodes = new Pair(list.getData().getNodes().getWithId(source), list.getData().getNodes().getWithId(target));
                }
            }
        }

    }


    /**
     * Makes this edge forget the list of edges it belongs to.
     * @throws IllegalStateException if this edge doesn't belong to a list of edges.
     * @return the list of edges being removed from the node.
     */

    public Edge removeList() {
        if (!hasList()) {
            throw new IllegalStateException("This Edge doesn't belong to a list of edges.");
        }
        this.list = null;
        return this;
    }


    /**
     *
     */
    public Edge clone() {
        return new Edge(list(), id(), source(), target(), kind(), name(), stereotype(), alignment(), card(), role());
    }

    /**
     * Prints this edge, i.e. its id, its source and target ids, and all its non-empty string parameters.
     */
    public void print() {
        System.out.println("Edge: " + id);
        System.out.println("Source: " + source);
        System.out.println("Target: " + target);

        if (hasKind()) {
            System.out.println("Kind: " + kind);
        }
        if (hasName()) {
            System.out.println("Name: " + name);
        }
        if (hasStereotype()) {
            System.out.println("Stereotype: " + stereotype);
        }
        if (hasAlignment()) {
            System.out.println("Alignment: " + alignment);
        }
        if (hasCard()) {
            System.out.println("Card: " + src_card);
        }
        if (hasRole()) {
            System.out.println("Role: " + src_role);
        }
        System.out.println();
    }

    public void unfoldBelow(int dist) {
        if (dist < 0) {
            throw new IllegalArgumentException("The distance must be positive.");
        }
        if (dist == 1) {
            System.out.println(dist + " step away from the original node:\n");
        } else {
            System.out.println(dist + " steps away from the original node:\n");
        }
        print();
        nodes().target().unfoldBelow(dist);
    }

    public void unfoldAbove(int dist) {
        if (dist < 0) {
            throw new IllegalArgumentException("The distance must be positive.");
        }
        if (dist == 1) {
            System.out.println(dist + " step away from the original node:\n");
        } else {
            System.out.println(dist + " steps away from the original node:\n");
        }
        print();
        nodes().source().unfoldAbove(dist);
    }

}

