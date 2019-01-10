package SEAMToOpenAPI;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Pair_test {

    @Test
    public void constructorNullSourceTest(){
        Node n1 = null;
        Node n2 = new Node();

        try{
            Pair p = new Pair(n1, n2);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void constructorNullTargetTest(){
        Node n1 = new Node();
        Node n2 = null;

        try{
            Pair p = new Pair(n1, n2);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void constructorandGetterTest(){
        Node n1 = new Node();
        Node n2 = new Node();

        Pair p = new Pair(n1, n2);

        assertEquals(n1, p.source());
        assertEquals(n2, p.target());
    }
}
