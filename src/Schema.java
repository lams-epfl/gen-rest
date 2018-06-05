public class Schema {

    private String name = "";
    private String properties = "";

    public Schema(String name, String properties) {
        this.name = name;
        this.properties = properties;
    }

    public String name() { return name; }

    public String properties(int n) {
        String result = "";

        String[] p = properties.split(", \\\\n|,\\\\n|, | \\\\n|\\\\n");

        for (int i = 0; i < p.length; i++) {
            if (!p[i].isEmpty()) {
                String s[] = p[i].split(": ");
                if (s.length != 2) {
                    throw new IllegalArgumentException("The schema " + name() + "properties do not satisfy the required syntax.");
                } else {
                    if (s[1].equals("dateTime")) {
                        s[1] = "string" + Parser.line() + Parser.tab(n+1) + "format: date-time";
                    }
                    result = result + Parser.line() +
                            Parser.tab(n) + s[0] + ":" + Parser.line() +
                            Parser.tab(n + 1) + "type: " + s[1];
                }
            }
        }

        return result;
    }

    public String toString(int n) {
        String result =  Parser.line() +
                Parser.tab(n+1) + name() + ":" + Parser.line() +
                Parser.tab(n+2) + "type: object" + Parser.line() +
                Parser.tab(n+2) + "properties:" + properties(n+3);
        return result;

    }
}
