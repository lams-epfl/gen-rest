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

    public Edge(Edges list, Integer id, Integer source, Integer target, String kind, String name, String stereotype, String alignment, String src_card, String src_role) {
        this.list = list;
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

    public Edge() {
        this(null, null, null, null, "", "", "", "", "", "");
    }

    public boolean hasList() { return list != null; }

    public boolean hasId() { return id != null; }

    public boolean hasSource() { return source != null; }

    public boolean hasTarget() { return target != null; }

    public boolean hasKind() { return !kind.equals(""); }

    public boolean hasName() {
        return !name.equals("");
    }

    public boolean hasStereotype() { return !stereotype.equals(""); }

    public boolean hasAlignment() { return !alignment.equals(""); }

    public boolean hasCard() {
        return !src_card.equals("");
    }

    public boolean hasRole() {
        return !src_role.equals("");
    }

    public boolean belongsTo(Edges es) {
        return list.equals(es);
    }

    public Edges getList() { return list; }

    public int getId() { return id; }

    public int getSource() { return source; }

    public int getTarget() { return target; }

    public String getKind() {
        return kind;
    }

    public String getName() {
        return name;
    }

    public String getStereotype() {
        return stereotype;
    }

    public String getAlignment() { return alignment; }

    public String getCard() {
        return src_card;
    }

    public String getRole() {
        return src_role;
    }

    public Nodes getNodes() {
        return this.list.getData().getNodes().filter(node -> node.id() == getSource() || node.id() == getTarget());
    }
    public void addList(Edges es) {
        if (hasList()) {
            throw new IllegalStateException("The Edge already has a list.");
        }
        this.list = es;
    }

    public void addId(Integer id) {
        if (hasId()) {
            throw new IllegalStateException("The Edge already has an id.");
        }
        this.id = id;
    }

    public void addSource(Integer source) {
        if (hasSource()) {
            throw new IllegalStateException("The Edge already has a source.");
        }
        this.source = source;
    }

    public void addTarget(Integer target) {
        if (hasTarget()) {
            throw new IllegalStateException("The Edge already has a target.");
        }
        this.target = target;
    }

    public void addKind(String kind) {
        if (hasKind()) {
            throw new IllegalStateException("The Edge already has a kind.");
        }
        this.kind = kind;
    }

    public void addName(String name) {
        if (hasName()) {
            throw new IllegalStateException("The Edge already has a name.");
        }
        this.name = name;
    }

    public void addStereotype(String stereotype) {
        if (hasStereotype()) {
            throw new IllegalStateException("The Edge already has a stereotype.");
        }
        this.stereotype = stereotype;
    }

    public void addAlignment(String alignment) {
        if (hasAlignment()) {
            throw new IllegalStateException("The Edge already has a stereotype.");
        }
        this.alignment = alignment;
    }

    public void addCard(String src_card) {
        if (hasCard()) {
            throw new IllegalStateException("The Edge already has a card.");
        }
        this.src_card = src_card;
    }

    public void addRole(String src_role) {
        if (hasRole()) {
            throw new IllegalStateException("The Edge already has a role.");
        }
        this.src_role = src_role;
    }

    public Edge removeList() {
        if (!hasList()) {
            throw new IllegalStateException("The Edge already has no list to remove.");
        }
        this.list = null;
        return this;
    }

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
        if (dist == 1) {
            System.out.println(dist + " step away from the original node:\n");
        } else {
            System.out.println(dist + " steps away from the original node:\n");
        }
        print();
        getNodes().getWithId(getTarget()).unfoldBelow(dist);
    }

    public void unfoldAbove(int dist) {
        if (dist == 1) {
            System.out.println(dist + " step away from the original node:\n");
        } else {
            System.out.println(dist + " steps away from the original node:\n");
        }
        print();
        getNodes().getWithId(getSource()).unfoldAbove(dist);
    }

}

