package SEAMToOpenAPI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Requests_test {

    @Test
    public void constructorGetterSetter(){
        Requests rs = new Requests();

        Request r1 = new Request();
        Request r2 = new Request();

        assertTrue(rs.isEmpty());

        rs.add(r1);
        rs.add(r2);

        assertFalse(rs.isEmpty());
        assertEquals(2, rs.size());
        assertEquals(r1, rs.get(0));
        assertEquals(r2, rs.get(1));
    }

    @Test
    public void removeIfAndFilterTest(){
        Requests rs = new Requests();

        Request r1 = new Request();
        Request r2 = new Request();
        Request r3 = new Request();
        Request r4 = new Request();
        Request r5 = new Request();

        r1.setVerb("post");
        r2.setVerb("post");
        r3.setVerb("delete");
        r4.setVerb("get");
        r5.setVerb("get");

        r1.setOut("one: two");
        r2.setOut("three: four");
        r3.setOut("five: six");

        rs.add(r1);
        rs.add(r2);
        rs.add(r3);
        rs.add(r4);
        rs.add(r5);

        rs.removeIf(r -> r.out().matches(""));
        assertEquals(3, rs.size());
        assertEquals(r1, rs.get(0));
        assertEquals(r2, rs.get(1));
        assertEquals(r3, rs.get(2));

        rs = rs.filter(r -> (r.verb() == Verbs.POST));
        assertEquals(r1, rs.get(0));
        assertEquals(r2, rs.get(1));
        assertEquals(2, rs.size());


    }

    /*
    @Test
    public void splitTest(){
        Requests rs = new Requests();

        Request r1 = new Request();
        Request r2 = new Request();
        Request r3 = new Request();
        Request r4 = new Request();

        r1.setPath("Path1");
        r2.setPath("Path1");
        r3.setPath("Path2");
        r4.setPath("Path3");

        rs.add(r1);
        rs.add(r3);
        rs.add(r4);

        Requests[] split1 = rs.split();

        assertEquals(3, split1.length);
        assertEquals(r1, split1[0].get(0));
        assertEquals(r2, split1[1].get(0));
        assertEquals(r3, split1[2].get(0));

        rs.add(r2);

        Requests[] split2 = rs.split();

        assertEquals(3, split2.length);
        assertEquals(r1, split1[0].get(0));
        assertEquals(r2, split1[0].get(1));
        assertEquals(r3, split1[1].get(0));
        assertEquals(r4, split1[2].get(0));
    }*/

    @Test
    public void cloneTest(){
        Requests rs = new Requests();

        Request r1 = new Request();
        Request r2 = new Request();

        rs.add(r1);
        rs.add(r2);

        Requests rs2 = rs.clone();

        assertEquals(rs2.size(), rs.size());
        assertNotEquals(rs, rs2);
        assertEquals(rs.get(0), rs.get(0));
        assertEquals(rs.get(1), rs.get(1));

        //Ask : Is it normal that the elements are the same?
    }

}
