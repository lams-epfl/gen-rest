package OpenAPIToSEAM;

import org.yaml.snakeyaml.Yaml;
import OpenAPIToSEAM.parser.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Generator {

    private static String nodesData = "";
    private static String nodesScenarios = "";
    private static String edgesData = "";
    private static String edgesScenarios = "";
    private static String inputPath = "openAPI_input.yml";

    public static void main(String[] args) throws IOException{

        Yaml y = new Yaml();
        FileWriter fw = new FileWriter("output.sxm");

        Map<String, Object> values = (Map<String, Object>) y.load(new FileInputStream(new File(inputPath)));

        Object infos = values.get("info");
        Object tags = values.get("tags");
        Object dependencies = values.get("dependencies");
        Object paths = values.get("paths");
        Object components = values.get("components");


        if(infos != null){
            Parser.genTitle(infos);
        }

        if(tags != null && dependencies != null){
            Parser.genTags(values.get("tags"), values.get("dependencies"));
        }

        if(dependencies != null) {
            Parser.genDependencies(values.get("dependencies"));
        }

        if(paths != null && components != null) {
            Parser.genPaths(values.get("paths"), values.get("components"));
        }

        fw.write(Utils.genHeader());

        String data = Data.fillData();
        data = String.format(data, nodesData, edgesData);

        fw.write(data);

        String scenarios = Scenarios.fillScenarios();
        scenarios = String.format(scenarios, nodesScenarios, edgesScenarios);

        fw.write(scenarios);

        fw.write(Utils.genFooter());

        fw.close();

    }

    /**
     * Concatenate a string defining a node to the current nodes data
     * @param node
     */
    public static void concatNodesData(String node) {
        nodesData += node;
    }

    /**
     * Concatenate a string defining an edge to the current edges data
     * @param edge
     */
    public static void concatEdgesData(String edge){
        edgesData += edge;
    }

    /**
     * Concatenate a string defining a node to the current nodes scenarios
     * @param node
     */
    public static void concatNodesScenarios(String node) {
        nodesScenarios += node;
    }

    /**
     * Concatenate a string defining a edge to the current edges scenarios
     * @param edge
     */
    public static void concatEdgesScenarios(String edge) {
        edgesScenarios += edge;
    }


}
