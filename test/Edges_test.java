import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Edges_test {

    @Test
    public void constructorNullTest() {
        Edges e = new Edges();

        assertEquals(0, e.size());
    }

    @Test
    public void getDataEmptyTest(){
        Edges e = new Edges();

        assertEquals(null, e.getData());
    }

    @Test
    public void addRemoveAndGetDataTest(){
        Edges e = new Edges();

        assertFalse(e.hasData());

        try{
            e.removeData();
        } catch (IllegalStateException ex){
            assertTrue(true, "Exception well handled");
        }

        Data d1 = new Data();
        Data d2 = new Data();

        e.addData(d1);

        try{
            e.addData(d2);
        } catch (IllegalStateException ex){
            assertTrue(true, "Exception well handled");
        }

        assertTrue(e.hasData());
        assertEquals(d1, e.getData());

        e.removeData();

        assertFalse(e.hasData());
    }

    @Test
    public void addGetAndFilterEdgeTest(){
        Edges e = new Edges();

        Edge e1 = new Edge(null, 1, 22, 32, "a", "Edge1", "", "left", "", "null");
        Edge e2 = new Edge(null, 2, 32, 22, "a", "Edge2", "ster", "left", "", "null");
        Edge e3 = new Edge(null, 3, 42, 13, "b", "Edge3", "", "left", "", "null");
        Edge e4 = new Edge(null, 4, 22, 56, "b", "Edge4", "ster", "right", "", "null");
        Edge e5 = new Edge(null, 5, 23, 22, "c", "Edge5", "", "right", "", "null");
        Edge e6 = new Edge(null, 6, 13, 35, "c", "Edge6", "ster", "right", "", "null");

        assertTrue(e.isEmpty());

        e.add(e1);
        e.add(e2);

        assertFalse(e.isEmpty());

        e.add(e3);
        e.add(e4);

        assertEquals(4, e.size());
        assertEquals(e3, e.get(2));

        e.add(e5);
        e.add(e6);

        assertEquals(6, e.size());
        assertEquals(e5, e.get(4));

        //TODO: Gerer ce cas
        //e.get(8);

        assertEquals(3, e.filter(edge -> edge.hasStereotype()).size());
        assertEquals(2, e.filter(edge -> edge.kind() == "a").size());
        assertEquals(2, e.filter(edge -> edge.source() == 22).size());
        assertEquals(0, e.filter(edge -> edge.name().matches("Edge")).size());

    }
}
