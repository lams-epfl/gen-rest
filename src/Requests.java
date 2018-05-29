import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

public class Requests {

    private ArrayList<Request> list = new ArrayList<>();

    public Requests(int size) {
        list = new ArrayList<>(size);
    }
    public Requests() { this(0); }

    public int size() {
        return list.size();
    }

    public Request get(int i) {
        return list.get(i);
    }

    public void add(Request r) {
        list.add(r);
    }

    public boolean isEmpty() { return list.isEmpty(); }

    public void removeIf(Predicate<Request> p) {
        list.removeIf(p);
    }


    public Requests filter(Predicate<Request> p) {
        Requests rs = new Requests();
        for (int i = 0; i < size(); i++) {
            if (p.test(get(i))) {
                rs.add(get(i));
            }
        }
        return rs;
    }

    public Requests[] split() {
        ArrayList<String> paths = new ArrayList<>();
        Requests requests = clone();

        while (!requests.isEmpty()) {
            paths.add(get(0).path());
            requests.removeIf(req -> req.path().equals(get(0).path()));
        }

        Requests[] r = new Requests[paths.size()];

        for (int i = 0; i < paths.size(); i++) {
            final int j = i;
            r[i] = filter(request -> paths.get(j).equals(request.path()));
        }

        return r;
    }

    public Requests clone() {
        Requests requests = new Requests(size());
        for (int i = 0; i < size(); i++) {
            requests.add(get(i));
        }
        return requests;
    }
}
