package OpenAPIToSEAM;

import OpenAPIToSEAM.parser.*;

public class Data {

    /**
     * Generate the basic structure for data
     * @return a string defining data to be filled
     */
    public static String fillData() {
        return Utils.createSpace(1) + Utils.STRING_DATAOPEN
                + Utils.createSpace(2) + Utils.STRING_NODESOPEN
                + "%s"
                + Utils.createSpace(2) + Utils.STRING_NODESCLOSE
                + Utils.createSpace(2) + Utils.STRING_EDGESOPEN
                + "%s"
                + Utils.createSpace(2) + Utils.STRING_EDGESCLOSE
                + Utils.createSpace(1) + Utils.STRING_DATACLOSE;
    }
}
