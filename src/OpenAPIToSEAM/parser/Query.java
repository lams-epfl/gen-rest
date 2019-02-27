package OpenAPIToSEAM.parser;

import java.util.List;

public interface Query {

    String getType();

    List<String> getTags();

    String getDescription();

    String genIO(Component c, String IO);
}

