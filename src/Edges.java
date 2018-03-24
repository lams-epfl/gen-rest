import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

public class Edges {

    private ArrayList<Edge> list = new ArrayList<>();
    private Data data = null;

    public Edges(int size) {
        list = new ArrayList<>(size);
    }

    public Edges() {
        this(0);
    }

    public Data getData() { return data; }

    public boolean hasData() { return data != null; }

    public void addData(Data data) {
        if (hasData()) {
            throw new IllegalStateException("The list of Edges already belongs to data.");
        }
        this.data = data;
    }

    public Data removeData() {
        if (!hasData()) {
            throw new IllegalStateException("The list of Edges doesn't belong to data.");
        }
        Data d = data;
        this.data = null;
        return d;
    }


    public Edge get(int i) {
        return list.get(i);
    }

    public void add(Edge e) {
        list.add(e);
        e.addList(this);
    }

    public void addAll(Edges es) {
        list.addAll(es.list);
    }

    public Edge remove(int i) {
        return list.remove(i).removeList();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(Edge e) {
        return list.contains(e);
    }

    public int indexOf(Edge e) {
        return list.indexOf(e);
    }

    public Edges subList(int i1, int i2) {
        Edges es = new Edges();
        for (int i = 0; i < i2-i1; i++) {
            es.add(this.get(i1 + i));
        }
        return es;
    }

    public Edges filter(Predicate<Edge> p) {
        Edges es = new Edges();
        es.addData(data);
        for (int i = 0; i < size(); i++) {
            if (p.test(get(i))) {
                es.add(new Edge(null, get(i).getId(), get(i).getSource(), get(i).getTarget(), get(i).getKind(), get(i).getName(), get(i).getStereotype(), get(i).getAlignment(), get(i).getCard(), get(i).getRole()));
            }
        }
        return es;
    }

    public void sortById() {
        list.sort(Comparator.comparing(Edge::getId));
    }

    public void sortByKind() {
        list.sort(Comparator.comparing(Edge::getKind));
    }

    public void sortByName() {
        list.sort(Comparator.comparing(Edge::getName));
    }

    public void print() {
        System.out.println("List of " + size() + " edges: \n");
        for (int i = 0; i < this.size(); i++) {
            this.get(i).print();
        }
    }






}
