package OpenAPIToSEAM.parser;

import java.util.UUID;

public interface Utils {
    String STRING_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    String STRING_MODELOPEN = "<model schemaVersion=\"2.1\" toolVersion=\"4.0.29\" xmlns=\"http://seamcad.lams.epfl.ch\">\n";
    String STRING_MODELCLOSE = "</model>\n";
    String STRING_DATAOPEN = "<data>\n";
    String STRING_DATACLOSE = "</data>\n";
    String STRING_NODESOPEN = "<nodes>\n";
    String STRING_NODESCLOSE = "</nodes>\n";
    String STRING_SCENARIOSOPEN = "<scenarios>\n";
    String STRING_SCENARIOSCLOSE = "</scenarios>\n";
    String STRING_SCENARIOOPEN = "<scenario name=\"Layout 1\">\n";
    String STRING_SCENARIOCLOSE = "</scenario>\n";
    String STRING_CONFIG = "<config/>\n";
    String STRING_EDGESOPEN = "<edges>\n";
    String STRING_EDGESCLOSE = "</edges>\n";

    static String genID(){
        return UUID.randomUUID().toString();
    }

    static String createSpace(int i){
        assert(i >= 0);

        String result = "";
        for (int j = 0; j < i; j++) {
            result += "  ";
        }

        return result;
    }


    static String genHeader(){
        return Utils.STRING_HEADER + Utils.STRING_MODELOPEN;
    }

    static String genFooter() {
        return Utils.createSpace(1) + Utils.STRING_CONFIG + Utils.STRING_MODELCLOSE;
    }
}
