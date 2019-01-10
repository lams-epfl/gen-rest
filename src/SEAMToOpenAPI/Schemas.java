package SEAMToOpenAPI;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Schemas {

    private ArrayList<Schema> list = new ArrayList<>();

    public Schemas(int size) {
        list = new ArrayList<>(size);
    }
    public Schemas() { this(0); }

    public int size() {
        return list.size();
    }

    public Schema get(int i) {
        return list.get(i);
    }

    public void add(Schema s) {
        list.add(s);
    }

    public boolean isEmpty() { return list.isEmpty(); }

    public Schemas filter(Predicate<Schema> p) {
        Schemas s = new Schemas();
        for (int i = 0; i < size(); i++) {
            if (p.test(get(i))) {
                s.add(get(i));
            }
        }
        return s;
    }

}
