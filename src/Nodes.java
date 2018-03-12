import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class Nodes {

    private ArrayList<Node> list = new ArrayList<>();

    public Nodes(int size) {
        list = new ArrayList<>(size);
    }

    public Nodes() {
        this(0);
    }

    public Node get(int i) {
        return list.get(i);
    }

    public void add(Node n) {
        list.add(n);
    }

    public void addAll(Nodes ns) {
        list.addAll(ns.list);
    }

    public Node remove(int i) {
        return list.remove(i);
    }

    public int size() {
        return list.size();
    }

    public int visibleSize() {
        return visibles().size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(Node n) {
        return list.contains(n);
    }

    public int indexOf(Node n) {
        return list.indexOf(n);
    }

    public Nodes subList(int i1, int i2) {
        Nodes nn = new Nodes();
        for (int i = 0; i < i2-i1; i++) {
            nn.add(this.get(i1 + i));
        }
        return nn;
    }

    public Nodes filter(Predicate<Node> p) {
        Nodes nn = new Nodes();
        for (int i = 0; i < size(); i++) {
            if (p.test(get(i))) {
                nn.add(get(i));
            }
        }
        return nn;
    }

    public Nodes visibles() {
        return filter(n -> n.isVisible());
    }

    public void sortById() {
        list.sort(Comparator.comparing(Node::getId));
    }

    public void sortByKind() {
        list.sort(Comparator.comparing(Node::getKind));
    }

    public void sortByName() {
        list.sort(Comparator.comparing(Node::getName));
    }

    public void print() {
        visibles().printAll();
    }

    public void printAll() {
        System.out.println("List of " + size() + " nodes: \n");
        for (int i = 0; i < this.size(); i++) {
            this.get(i).print();
        }
    }






}
