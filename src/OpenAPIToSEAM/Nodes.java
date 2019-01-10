package OpenAPIToSEAM;

import OpenAPIToSEAM.parser.Utils;

/**
 * Class containing methods to generate Strings of different nodes
 */
public class Nodes {

    public static String genNodesWorkingObject(String title){
        return Utils.createSpace(4)
                + "<kind>biz_working_object</kind>\n"
                + Utils.createSpace(4) + "<name>"
                + title
                + "</name>\n";
    }

    public static String genNodesDitributedAction(String title){
        return Utils.createSpace(4)
                + "<kind>distributed_action</kind>\n"
                + Utils.createSpace(4) + "<name>"
                + "Process of " + title
                + "</name>\n";
    }

    public static String genNodesLocalisedAction(String title){
        return Utils.createSpace(4)
                + "<kind>localised_action</kind>\n"
                + Utils.createSpace(4) + "<name>"
                + "Process of " + title
                + "</name>\n";
    }

    public static String genNodesLocalisedActionStereotype(String type, String description){
        return Utils.createSpace(4)
                + "<kind>localised_action</kind>\n"
                + Utils.createSpace(4)
                + "<stereotype>" + type.toUpperCase() + "</stereotype>\n"
                +Utils.createSpace(4)
                +"<name>" + description + "</name>\n";
    }

    public static String genNodesLocalisedProperty(String title){
        return Utils.createSpace(4)
                + "<kind>localised_property</kind>\n"
                + Utils.createSpace(4) + "<name>"
                + title
                + "</name>\n";
    }

    public static String genNodesWorkingObjectComposite(String title) {
        return genNodesWorkingObject(title)
                + Utils.createSpace(4) + "<composite>true</composite>\n";
    }

    public static String genNodeStructureData(String id){
        return Utils.createSpace(3)
                + "<node id=\"" + id + "\">\n"
                + "%s"
                + Utils.createSpace(3)
                + "</node>\n";
    }

    public static String genNodeStructureScenarios(String id){
        return "<node idRef=\"" + id + "\"/>\n";
    }
}
