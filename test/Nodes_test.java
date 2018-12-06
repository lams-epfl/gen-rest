import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Nodes_test {

    @Test
    public void constructorNullTest() {
        Nodes n = new Nodes();

        assertEquals(0, n.size());
    }

    @Test
    public void addGetAndRemoveDataTest(){
        Nodes n = new Nodes();
        Data d =  new Data();

        assertFalse(n.hasData());

        n.addData(d);
        assertTrue(n.hasData());
        assertEquals(d, n.getData());

        n.removeData();

        assertFalse(n.hasData());
    }

    @Test
    public void addAndGetNodeTest(){
        Nodes ns = new Nodes();
        Node n1 = new Node();
        Node n2 = new Node();

        assertTrue(ns.isEmpty());
        assertEquals(0, ns.size());

        n1.addId(1);
        n2.addId(2);

        ns.add(n1);
        ns.add(n2);

        assertFalse(ns.isEmpty());

        assertEquals(n1, ns.get(0));
        assertEquals(n2, ns.get(1));

        assertEquals(n1, ns.getWithId(1));
    }

    @Test
    public void filterNodeTest(){
        Nodes ns = new Nodes();
        Node n1 = new Node();
        Node n2 = new Node();
        Node n3 = new Node();
        Node n4 = new Node();

        n1.addId(1);
        n2.addId(2);
        n3.addId(3);
        n4.addId(4);

        n1.addKind("Kind1");
        n2.addKind("Kind2");
        n3.addKind("Kind3");
        n4.addKind("Kind1");

        ns.add(n1);
        ns.add(n2);
        ns.add(n3);
        ns.add(n4);

        assertEquals(2, ns.filter(n -> n.kind() == "Kind1").size());
        assertEquals(1, ns.filter(n -> n.kind() == "Kind2").size());
    }



}
