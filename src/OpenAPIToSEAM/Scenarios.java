package OpenAPIToSEAM;

import OpenAPIToSEAM.parser.Utils;

public class Scenarios {

    /**
     * Generate the basic structure for a scenario
     * @return a string defining a scenario to be filled
     */
    public static String fillScenarios() {
        return Utils.createSpace(1) + Utils.STRING_SCENARIOSOPEN
                + Utils.createSpace(2) + Utils.STRING_SCENARIOOPEN
                + Utils.createSpace(3) + Utils.STRING_NODESOPEN
                + "%s"
                + Utils.createSpace(3) + Utils.STRING_NODESCLOSE
                + Utils.createSpace(3) + Utils.STRING_EDGESOPEN
                + "%s"
                + Utils.createSpace(3) + Utils.STRING_EDGESCLOSE
                + Utils.createSpace(2) + Utils.STRING_SCENARIOCLOSE
                + Utils.createSpace(1) + Utils.STRING_SCENARIOSCLOSE;
    }
}
