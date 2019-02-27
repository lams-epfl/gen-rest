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
                es.add(get(i));
                //es.add(new Edge(null, get(i).id(), get(i).source(), get(i).target(), get(i).kind(), get(i).name(), get(i).stereotype(), get(i).alignment(), get(i).card(), get(i).role()));
            }
        }
        return es;
    }

    public void sortById() {
        list.sort(Comparator.comparing(Edge::id));
    }

    public void sortByKind() {
        list.sort(Comparator.comparing(Edge::kind));
    }

    public void sortByName() {
        list.sort(Comparator.comparing(Edge::name));
    }


    /**
     *
     */
 /*   public Edges clone() {
        Edges edges = new Edges();
        edges.addData(getData());
        for (int i = 0; i < size(); i++) {
            Edge edge = get(i);
            System.out.println(edge + " " + get(i));
            edges.add(edge);
        }
        return edges;
    }
*/

    public void print() {
        System.out.println("List of " + size() + " edges: \n");
        for (int i = 0; i < this.size(); i++) {
            this.get(i).print();
        }
    }






}
