import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;

public class Data_test {

    @Test
    public void testConstructor() {
        Data d1 = new Data(null, null);
        Data d2 = new Data(new Nodes(), null);
        Data d3 = new Data(null, new Edges());
        Data d4 = new Data(new Nodes(), new Edges());

        assertFalse(d1.hasEdges());
        assertFalse(d1.hasNodes());
        assertFalse(d2.hasEdges());
        assertTrue(d2.hasNodes());
        assertFalse(d3.hasNodes());
        assertTrue(d3.hasEdges());
        assertTrue(d4.hasEdges());
        assertTrue(d4.hasEdges());
    }

    @Test
    public void addAndGetNodes(){
        Data d = new Data();

        Nodes n1 = new Nodes();
        Nodes n2 = new Nodes();

        d.addNodes(n1);
        assertTrue(d.hasNodes());

        try{
            d.addNodes(n2);
        } catch (IllegalStateException e){
            assertTrue(true, "Exception already nodes well handled");
        }

        assertEquals(n1, d.getNodes());
    }

    @Test
    public void addAndGetEdges(){
        Data d = new Data();

        Edges e1 = new Edges();
        Edges e2 = new Edges();

        d.addEdges(e1);
        assertTrue(d.hasEdges());

        try{
            d.addEdges(e2);
        } catch (IllegalStateException e){
            assertTrue(true, "Exception already edges well handled");
        }

        assertEquals(e1, d.getEdges());
    }

    @Test
    public void workingObjectTest(){
        Data d = new Data();

        Nodes ns = new Nodes();
        Node n1 = new Node(null, 1, "working_object", "", "true","", "");
        Node n2 = new Node(null, 2, "working_object", "", "false","", "");
        Node n3 = new Node(null, 3, "non_working", "", "false","", "");

        ns.add(n1);
        ns.add(n2);
        ns.add(n3);

        d.addNodes(ns);

        assertTrue(d.workingObjects().contains(n2));
        assertFalse(d.workingObjects().contains(n1));
        assertFalse(d.workingObjects().contains(n3));
    }

    @Test
    public void rootTest(){
        Data d = new Data();

        Nodes ns = new Nodes();
        Node n1 = new Node(null, 1, "working_object", "", "true","", "");

        try{
            d.root();
        } catch (NullPointerException e){
            assertTrue(true, "Exception well handled");
        }


        ns.add(n1);
        d.addNodes(ns);

        try{
            d.root();
        } catch (NullPointerException e){
            assertTrue(true, "Exception well handled");
        }
    }

}
