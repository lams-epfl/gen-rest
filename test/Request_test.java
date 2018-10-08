import com.sun.org.apache.regexp.internal.RE;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Request_test {

    @Test
    public void constructSetAndGetTest(){
        Request r = new Request();

        r.setTags("tagTest");
        r.setDescription("descriptionTest");
        //Verbs are tested in different test as there are many different cases
        //In are tested in other test as well
        r.setOut("outTest");
        r.setPath("pathTest");

        assertEquals("tagTest", r.tags());
        assertEquals("descriptionTest", r.description());
        assertEquals("outTest", r.out());
        assertEquals("pathTest", r.path());
    }

    @Test
    public void constructVerbs(){
        Request r1 = new Request();
        Request r2 = new Request();
        Request r3 = new Request();
        Request r4 = new Request();

        r1.setVerb("POST");
        r2.setVerb("get");
        r3.setVerb("PuT");
        r4.setVerb("DeleTe");

        assertEquals(Verbs.POST, r1.verb());
        assertEquals(Verbs.GET, r2.verb());
        assertEquals(Verbs.PUT, r3.verb());
        assertEquals(Verbs.DELETE, r4.verb());
    }

    @Test
    public void constructTwoVerbs(){
        Request r = new Request();

        r.setVerb("POST");

        try{
            r.setVerb("GET");
        } catch(IllegalStateException e){
            assertTrue(true);
        }
    }

    @Test
    public void contructRandomVerb(){
        Request r = new Request();

        r.setVerb("RANDOM");

        assertTrue(false, "Ask how it is supposed to react");
    }

    @Test
    public void setTwoTagsImpossibleTest(){
        Request r = new Request();

        r.setTags("Tag1");
        try{
            r.setTags("Tag2");
        } catch(IllegalStateException e){
            assertTrue(true);
        }
    }

    @Test
    public void setTwoDescriptionImpossibleTest(){
        Request r = new Request();

        r.setDescription("Description1");
        try{
            r.setTags("Description2");
        } catch(IllegalStateException e){
            assertTrue(true);
        }
    }

    @Test
    public void setTwoInImpossibleTest(){
        Request r = new Request();

        r.setIn("In1");
        try{
            r.setTags("In2");
        } catch(IllegalStateException e){
            assertTrue(true);
        }
    }

    @Test
    public void setTwoOutImpossibleTest(){
        Request r = new Request();

        r.setOut("Out1");
        try{
            r.setOut("Out2");
        } catch(IllegalStateException e){
            assertTrue(true);
        }
    }

    @Test
    public void setTwoPathImpossibleTest(){
        Request r = new Request();

        r.setPath("Path1");
        try{
            r.setPath("Path2");
        } catch(IllegalStateException e){
            assertTrue(true);
        }
    }

    @Test
    public void tranformInWithNoVerbTest(){
        Request r = new Request();

        r.transformIn(0);

        //TODO: Check what is it egal to with assert equal
    }

    @Test
    public void transformInWithPutAndPostTest(){
        Request r1 = new Request();
        Request r2 = new Request();

        r1.setVerb("post");
        r2.setVerb("Put");

        r1.setIn("New1 {Stuff, OtherStuff}");
        r2.setIn("New2 {Elements}");

        String baseResult = "\nrequestBody:\n  content:\n    application/json:\n      schema:";

        String r1ResultSpe = "\n        $ref: '#/components/schemas/New1'\n  required: true";
        String r2ResultSpe = "\n        $ref: '#/components/schemas/New2'\n  required: true";


        assertEquals(baseResult + r1ResultSpe, r1.transformIn(0));
        assertEquals(baseResult + r2ResultSpe, r2.transformIn(0));
    }

    @Test
    public void transformInWithoutPutAndPostTest(){
        Request r1 = new Request();
        Request r2 = new Request();

        r1.setVerb("post");
        r2.setVerb("Put");

        r1.setIn("NewStuff: OtherStuff");
        r2.setIn("Elem: Elements, second: property");

        String baseResult = "\nrequestBody:\n  content:\n    application/json:\n      schema:\n        type: object\n        properties:";

        String r1ResultSpe = "\n          NewStuff:\n            type: OtherStuff\n  required: true";
        String r2ResultSpe = "\n          Elem:\n            type: Elements\n          second:\n            type: property\n  required: true";


        assertEquals(baseResult + r1ResultSpe, r1.transformIn(0));
        assertEquals(baseResult + r2ResultSpe, r2.transformIn(0));
    }


    @Test
    public void transformInGetErrorTest(){
        Request r = new Request();

        r.setVerb("get");

        r.setIn("first: second error");


        String result = "\nparameters:\n  - name: first\n    in: query\n    required: true\n    schema:\n      type: second";

        try {
            r.transformIn(0);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void transformInGetTest(){
        Request r = new Request();

        r.setVerb("get");

        r.setIn("first: second");

        String result = "\nparameters:\n  - name: first\n    in: query\n    required: true\n    schema:\n      type: second";

        assertEquals(result, r.transformIn(0));
    }


    @Test
    public void transformInGetDateTest(){
        Request r = new Request();

        r.setVerb("get");

        r.setIn("time: dateTime");

        String result = "\nparameters:\n  - name: time\n    in: query\n    required: true\n    schema:\n      type: string\n      format: date-time";

        assertEquals(result, r.transformIn(0));
    }

    @Test
    public void transformOutPutTest(){
        Request r = new Request();

        r.setVerb("puT");

        r.setOut("out");

        String result = "\nresponses:\n  '200':\n    description: OK";

        assertEquals(result, r.transformOut(0));
    }

    @Test
    public void transformOutPostAndGetWithTest(){
        Request r1 = new Request();
        Request r2 = new Request();

        r1.setVerb("Post");
        r2.setVerb("GET");

        r1.setOut("first {second, third}");
        r2.setOut("one {four}");

        String baseResult = "\nresponses:\n  '200':\n    description: ";

        String r1ResultSpe = "request successful\n    content:\n      application/json:\n        schema:\n          $ref: '#/components/schemas/first'";
        String r2ResultSpe = "request successful\n    content:\n      application/json:\n        schema:\n          $ref: '#/components/schemas/one'";


        assertEquals(baseResult + r1ResultSpe, r1.transformOut(0));
        assertEquals(baseResult + r2ResultSpe, r2.transformOut(0));
    }

    @Test
    public void parseWith3ElementsTest(){
        String s = "New {Elements} Other";

        Request r = new Request();

        try{
            r.parseWith(s, 0);
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        }
    }

    @Test
    public void parseWithNewSchemaTest(){
        String s = "New {Elements, Elements}";
        Request r = new Request();

        String result = "\n$ref: '#/components/schemas/New'";

        assertEquals(result, r.parseWith(s, 0));
    }

    @Test
    public void parseWithExistingSchemaTest(){
        String s1 = "New {Elements}";
        String s2 = "New {OtherElement}";
        Request r = new Request();

        r.parseWith(s1, 0);

        String result = "\ntype: array\nitems:\n  $ref: '#/components/schemas/New'";

        assertEquals(result, r.parseWith(s2, 0));
    }

    @Test
    public void parseWithout(){
        //TODO: ask : same code as Schema.java -> properties method
    }

    @Test
    public void getParametersTest(){
        Request r = new Request();

        r.setVerb("get");

        r.setIn("first: second");

        String resultIN = "\nparameters:\n  - name: first\n    in: query\n    required: true\n    schema:\n      type: second";

        String baseResult = "\nresponses:\n  '200':\n    description: ";

        String rResultSpe = "request successful\n    content:\n      application/json:\n        schema:\n          $ref: '#/components/schemas/first'";


        assertEquals(resultIN + baseResult + rResultSpe, r.getParameters(0));
    }

    @Test
    public void putPostParametersTest(){
        Request r1 = new Request();
        Request r2 = new Request();

        r1.setVerb("Post");
        r2.setVerb("put");

        r1.setOut("first {second}");
        r2.setOut("One {two}");

        r1.setIn("In: param");
        r2.setIn("In2: param2");

        String baseResultPostOut = "\nresponses:\n  '200':\n    description: ";
        String r1ResultOut = "request successful\n    content:\n      application/json:\n        schema:\n          $ref: '#/components/schemas/first'";
        String baseResultPostIn = "\nrequestBody:\n  content:\n    application/json:\n      schema:\n        type: object\n        properties:";
        String r1ResultIn = "\n          In:\n            type: param\n  required: true";

        String baseResultPutOut = "\nresponses:\n  '200':\n    description: OK";
        String baseResultPutIn = "\nrequestBody:\n  content:\n    application/json:\n      schema:\n        type: object\n        properties:";
        String r2ResultIn = "\n          In2:\n            type: param2\n  required: true";


        assertEquals(baseResultPostOut + r1ResultOut + baseResultPostIn + r1ResultIn, r1.getParameters(0));
        assertEquals(baseResultPutOut +  baseResultPutIn + r2ResultIn, r2.getParameters(0));
    }

}
