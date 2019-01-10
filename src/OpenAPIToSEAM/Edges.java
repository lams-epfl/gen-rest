package OpenAPIToSEAM;

import OpenAPIToSEAM.parser.Utils;

/**
 * Class containing methods to generate Strings of different edges
 */
public class Edges {

    public static String genEdgesBetweenParameters(String id1, String id2, String name){
        String e = Utils.createSpace(4)
                + "<source idRef=\"" + id1 + "\"/>\n"
                + Utils.createSpace(4)
                + "<target idRef=\"" + id2 + "\"/>\n"
                + Utils.createSpace(4)
                +"<kind>sequence</kind>\n"
                + Utils.createSpace(4)
                + "<src_card>" + name + "</src_card>\n";
        return e;
    }

    public static String genEdgesInclude(String id1, String id2){
        String e = Utils.createSpace(4)
                + "<source idRef=\"" + id1 + "\"/>\n"
                + Utils.createSpace(4)
                + "<target idRef=\"" + id2 + "\"/>\n"
                + Utils.createSpace(4)
                +"<kind>include</kind>\n";
        return e;
    }

    public static String genEdgesExchange(String id1, String id2){
        String e = Utils.createSpace(4)
                + "<source idRef=\"" + id1 + "\"/>\n"
                + Utils.createSpace(4)
                + "<target idRef=\"" + id2 + "\"/>\n"
                + Utils.createSpace(4)
                +"<kind>exchange</kind>\n";
        return e;
    }

    public static String genEdgesLink(String id1, String id2){
        String e = Utils.createSpace(4)
                + "<source idRef=\"" + id1 + "\"/>\n"
                + Utils.createSpace(4)
                + "<target idRef=\"" + id2 + "\"/>\n"
                + Utils.createSpace(4)
                +"<kind>linkage</kind>\n";
        return e;
    }

    public static String genEdgeStructureData(String id){
        return Utils.createSpace(3)
                + "<edge id=\"" + id + "\">\n"
                + "%s"
                + Utils.createSpace(3)
                + "</edge>\n";
    }

    public static String genEdgeStructureScenario(String id){
        return "<edge idRef=\"" + id + "\"/>\n";
    }
}
