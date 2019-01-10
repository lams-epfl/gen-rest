package SEAMToOpenAPI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Node_test {

    @Test
    public void constructorTest() {
        Nodes list = new Nodes();
        Node n = new Node(list, 42, "action", "SEAMToOpenAPI.Node_test", "true", "in", "left");

        assertEquals(n.list(), list);
        assertEquals(n.id(), 42);
        assertEquals(n.kind(), "action");
        assertEquals(n.name(), "SEAMToOpenAPI.Node_test");
        assertEquals(n.composite(), "true");
        assertEquals(n.stereotype(), "in");
        assertEquals(n.alignment(), "left");

    }

    @Test
    public void constructorNullTest() {
      Node n = new Node();

        assertFalse(n.hasList());
        assertFalse(n.hasId());
        assertFalse(n.hasKind());
        assertFalse(n.hasName());
        assertFalse(n.hasComposite());
        assertFalse(n.hasStereotype());
        assertFalse(n.hasAlignment());
    }

    @Test
    public void isRequestTest(){
        Node n1 = new Node(null, 42, "action", "SEAMToOpenAPI.Node_test", "true", "" , "left");
        Node n2 = new Node(null, 42, "action", "SEAMToOpenAPI.Node_test", "true", "POST", "left");
        Node n3 = new Node(null, 42, "action", "SEAMToOpenAPI.Node_test", "true", "GET", "left");
        Node n4 = new Node(null, 42, "action", "SEAMToOpenAPI.Node_test", "true", "OTHER", "left");

        assertFalse(n1.isRequest());
        assertTrue(n2.isRequest());
        assertTrue(n3.isRequest());
        assertFalse(n4.isRequest());
    }

    @Test
    public void listConstrAndAddTest() {
        Nodes list = new Nodes();
        Nodes list2 = new Nodes();


        Node n1 = new Node();
        Node n2 = new Node(null, 42, "action", "SEAMToOpenAPI.Node_test", "true", "POST", "left");
        Node n3 = new Node(list, 42, "action", "SEAMToOpenAPI.Node_test", "true", "POST", "left");


        try{
            n1.list();
        } catch (NullPointerException e) {
            assertTrue(true, "Exception well handled");
        }

        try{
            n2.list();
        } catch (NullPointerException e){
            assertTrue(true, "Exception well handled");
        }

        n2.addList(list2);
        assertEquals(list2, n2.list());
        assertEquals(list, n3.list());
    }

    @Test
    public void idConstrAndAddTest() {

        Node n1 = new Node();
        Node n2 = new Node(null, 42, "action", "SEAMToOpenAPI.Node_test", "true", "POST", "left");

        try{
            n1.id();
        } catch (NullPointerException e){
            assertTrue(true, "Exception no id well handled");
        }

        try{
            n2.addId(12);
        } catch (IllegalStateException e) {
            assertTrue(true, "Exception already id well handled");
        }

        n1.addId(21);
        assertEquals(21, n1.id());
        assertEquals(42, n2.id());
    }

    @Test
    public void kindConstrAndAddTest() {

        Node n1 = new Node();
        Node n2 = new Node(null, 42, "action", "SEAMToOpenAPI.Node_test", "true", "POST", "left");

        try{
            n1.kind();
        } catch (NullPointerException e){
            assertTrue(true, "Exception no kind well handled");
        }

        try{
            n1.addKind("SomeKind");
        } catch (NullPointerException e){
            assertTrue(true, "Exception no id add kind well handled");
        }

        try{
            n2.addKind("NewKind");
        } catch (IllegalStateException e){
            assertTrue(true, "Exception already kind well handled");
        }

        n1.addId(56);
        n1.addKind("Node");
        assertEquals("Node", n1.kind());
        assertEquals("action", n2.kind());
    }


    @Test
    public void nameConstrAndAddTest() {

        Node n1 = new Node();
        Node n2 = new Node(null, 42, "action", "SEAMToOpenAPI.Node_test", "true", "POST", "left");

        try{
            n1.name();
        } catch (NullPointerException e){
            assertTrue(true, "Exception well handled");
        }

        try{
            n1.addName("SomeName");
        } catch (NullPointerException e){
            assertTrue(true, "Exception no id add name well handled");
        }

        try{
            n2.addName("NewName");
        } catch (IllegalStateException e){
            assertTrue(true, "Exception already name well handled");
        }

        n1.addId(1);
        n1.addName("Name");
        assertEquals("Name", n1.name());
        assertEquals("SEAMToOpenAPI.Node_test", n2.name());
    }

    @Test
    public void compositeConstrAndAddTest() {

        Node n1 = new Node();
        Node n2 = new Node(null, 42, "action", "SEAMToOpenAPI.Node_test", "true", "POST", "left");

        try{
            n1.composite();
        } catch (NullPointerException e){
            assertTrue(true, "Exception well handled");
        }

        try{
            n1.addComposite("SomeCompo");
        } catch (NullPointerException e){
            assertTrue(true, "Exception no id add composite well handled");
        }

        try{
            n2.addComposite("NewComposite");
        } catch (IllegalStateException e){
            assertTrue(true, "Exception already composite well handled");
        }

        n1.addId(1);
        n1.addComposite("Comp");
        assertEquals("Comp", n1.composite());
        assertEquals("true", n2.composite());
    }


    @Test
    public void stereotypeConstrAndAddTest() {

        Node n1 = new Node();
        Node n2 = new Node(null, 42, "action", "SEAMToOpenAPI.Node_test", "true", "POST", "left");

        try{
            n1.stereotype();
        } catch (NullPointerException e){
            assertTrue(true, "Exception well handled");
        }

        try{
            n1.addStereotype("SomeStereo");
        } catch (NullPointerException e){
            assertTrue(true, "Exception no id add stereotype well handled");
        }

        try{
            n2.addStereotype("NewStereo");
        } catch (IllegalStateException e){
            assertTrue(true, "Exception already stereotype well handled");
        }

        n1.addId(1);
        n1.addStereotype("Ster");
        assertEquals("Ster", n1.stereotype());

        assertEquals("POST", n2.stereotype());
    }

    @Test
    public void alignmentConstrAndAddTest() {

        Node n1 = new Node();
        Node n2 = new Node(null, 42, "action", "SEAMToOpenAPI.Node_test", "true", "POST", "left");

        try{
            n1.alignment();
        } catch (NullPointerException e){
            assertTrue(true, "Exception well handled");
        }

        try{
            n1.addAlignment("Alignment");
        } catch (NullPointerException e){
            assertTrue(true, "Exception no id add alignment well handled");
        }

        try{
            n2.addAlignment("NewAlign");
        } catch (IllegalStateException e){
            assertTrue(true, "Exception already aligned well handled");
        }

        n1.addId(1);
        n1.addAlignment("Ali");
        assertEquals("Ali", n1.alignment());

        assertEquals("left", n2.alignment());
    }

    @Test
    public void edgesOutInAndAllTest(){
        Data d = new Data();

        Edges es = new Edges();

        Edge e1 = new Edge(null, 10, 2, 3, "", "", "", "", "", "");
        Edge e2 = new Edge(null, 11, 2, 1, "", "", "", "", "", "");
        Edge e3 = new Edge(null, 12, 1, 4, "", "", "", "", "", "");
        Edge e4 = new Edge(null, 13, 4, 2, "", "", "", "", "", "");
        Edge e5 = new Edge(null, 14, 2, 5, "", "", "", "", "", "");



        Nodes ns = new Nodes();

        Node n1 = new Node(null, 1, "", "", "","", "");
        Node n2 = new Node(null, 2, "", "", "","", "");
        Node n3 = new Node(null, 3, "", "", "","", "");
        Node n4 = new Node(null, 4, "", "", "","", "");
        Node n5 = new Node(null, 5, "", "", "","", "");

        ns.add(n1);
        ns.add(n2);
        ns.add(n3);
        ns.add(n4);
        ns.add(n5);

        d.addEdges(es);
        d.addNodes(ns);

        es.add(e1);
        es.add(e2);
        es.add(e3);
        es.add(e4);
        es.add(e5);
        assertTrue(n1.edgesOut().contains(e3));
    }


    @Test
    public void neighborsOutInAndAllTest(){
        Data d = new Data();

        Nodes ns = new Nodes();

        Node n1 = new Node(null, 1, "", "", "","", "");
        Node n2 = new Node(null, 2, "", "", "","", "");
        Node n3 = new Node(null, 3, "", "", "","", "");
        Node n4 = new Node(null, 4, "", "", "","", "");
        Node n5 = new Node(null, 5, "", "", "","", "");
        Node n6 = new Node(null, 6, "", "", "","", "");

        ns.add(n1);
        ns.add(n2);
        ns.add(n3);
        ns.add(n4);
        ns.add(n5);
        ns.add(n6);
        Edges es = new Edges();

        Edge e1 = new Edge(null, 10, 1, 5, "", "", "", "", "", "");
        Edge e2 = new Edge(null, 11, 5, 4, "", "", "", "", "", "");
        Edge e3 = new Edge(null, 12, 4, 3, "", "", "", "", "", "");
        Edge e4 = new Edge(null, 13, 3, 2, "", "", "", "", "", "");
        Edge e5 = new Edge(null, 14, 2, 1, "", "", "", "", "", "");
        Edge e6 = new Edge(null, 15, 2, 6, "", "", "", "", "", "");
        Edge e7 = new Edge(null, 16, 4, 1, "", "", "", "", "", "");



        d.addNodes(ns);
        d.addEdges(es);

        es.add(e1);
        es.add(e2);
        es.add(e3);
        es.add(e4);
        es.add(e5);
        es.add(e6);
        es.add(e7);

        assertTrue(n1.neighborsOut().contains(n5));
    }

    @Test
    public void requestAndLocalActionTest(){
        Data d = new Data();

        Nodes ns = new Nodes();
        Edges es = new Edges();

        Edge e1 = new Edge(null, 10, 1, 2, "", "", "", "", "", "");
        Edge e2 = new Edge(null, 11, 1, 3, "", "", "", "", "", "");
        Edge e3 = new Edge(null, 12, 1, 4, "", "", "", "", "", "");
        Edge e4 = new Edge(null, 13, 1, 5, "", "", "", "", "", "");



        Node n1 = new Node(null, 1, "", "", "","POST", "");
        Node n2 = new Node(null, 2, "localised_action", "", "GET","", "");
        Node n3 = new Node(null, 3, "", "", "","POST", "");
        Node n4 = new Node(null, 4, "localised_action", "", "PUT","", "");
        Node n5 = new Node(null, 5, "", "", "","OTHER", "");


        d.addEdges(es);

        ns.add(n1);
        ns.add(n2);
        ns.add(n3);
        ns.add(n4);
        ns.add(n5);

        d.addNodes(ns);

        es.add(e1);
        es.add(e2);
        es.add(e3);
        es.add(e4);

        assertTrue(n1.localActions().contains(n2));
        assertFalse(n1.localActions().contains(n3));
        assertTrue(n1.localActions().contains(n4));
        assertFalse(n1.localActions().contains(n5));

    }

    @Test
    public void removeListTest(){
        Nodes ns = new Nodes();
        Node n = new Node();

        try{
            n.removeList();
        } catch (NullPointerException e) {
            assertTrue(true, "Exception no Id well handled");
        }

        n.addId(1);

        try{
            n.removeList();
        } catch (IllegalStateException e){
            assertTrue(true, "Exception no list well handled");
        }

        n.addList(ns);

        assertTrue(n.hasList());

        n.removeList();

        assertFalse(n.hasList());
    }

    @Test
    public void cloneTest(){
        Nodes ns = new Nodes();
        Node n = new Node(ns, 56, "node1", "NodeToClone", "true", "action", "left");

        Node n2 = n.clone();

        assertNotEquals(n, n2);
        assertEquals(n.list(), n2.list());
        assertEquals(n.id(), n2.id());
        assertEquals(n.kind(), n2.kind());
        assertEquals(n.name(), n2.name());
        assertEquals(n.composite(), n2.composite());
        assertEquals(n.stereotype(), n2.stereotype());
        assertEquals(n.alignment(), n2.alignment());
    }


}
