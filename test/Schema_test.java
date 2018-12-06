import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static org.junit.jupiter.api.Assertions.*;


public class Schema_test {

    @Test
    public void constructorAndGetterTest(){
        Schema s = new Schema("name", "prop");

        assertEquals("name", s.name());
    }

    @Test
    public void emptyPropertiesTest(){
        String empty = "";

        Schema s = new Schema("name", empty);

        assertEquals("", s.properties(0));
    }

    @Test
    public void propertiesTestWrongSize3(){
        String wrongCondition = "element: string1 string2";

        Schema s = new Schema("name", wrongCondition);

        try {
            s.properties(0);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void propertiesTestWrongSize1(){
        String wrongCondition = "condition: ";

        Schema s = new Schema("name", wrongCondition);

        try {
            s.properties(0);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void propertiesTestSingleCondition(){
        String singleCondition1 = "element: string";
        String singleCondition2 = "day: date-time";

        Schema s1 = new Schema("name", singleCondition1);
        Schema s2 = new Schema("name", singleCondition2);

        String expected1 = "\nelement:\n  type: string";
        String expected2 = "\nday:\n  type: date-time";

        assertEquals(expected1,  s1.properties(0));
        assertEquals(expected2,  s2.properties(0));
    }

    @Test
    public void propertiesContainsArrayTest(){
        String arrayCondition = "element: array(arrayElement)";

        Schema s1 = new Schema("name", arrayCondition);

        String expected1 = "\nelement:\n  type: array\n  items:\n    $ref: '#/components/schemas/arrayElement'";

        assertEquals(expected1, s1.properties(0));
    }

    @Test
    public void propertiesContainsEnumTest(){
        String enumCondition = "element: enum(e1,e2,e3)";

        Schema s1 = new Schema("name", enumCondition);

        String expected1 = "\nelement:\n  type: string\n  enum:\n    - e1\n    - e2\n    - e3";

        assertEquals(expected1, s1.properties(0));
    }

    //TODO: Test with enum with element seperated by other stuff

    @Test
    public void propertiesContainsDateTime(){
        String dateTimeCondition = "time: dateTime";

        Schema s1 = new Schema("name", dateTimeCondition);

        String expected1 = "\ntime:\n  type: string\n  format: date-time";

        assertEquals(expected1, s1.properties(0));
    }

    //TODO: test property with different kind of split
    
}
