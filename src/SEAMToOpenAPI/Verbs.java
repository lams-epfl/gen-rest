package SEAMToOpenAPI;

public enum Verbs {
    POST, PUT, GET, DELETE, NO;

    public String toString() {
        switch (this) {
            case GET:
                return "get";
            case POST:
                return "post";
            case PUT:
                return "put";
            case DELETE:
                return "delete";
        }
        return null;
    }

    public boolean isPOST() {
        return toString().equals("post");
    }
    public boolean isGET() {
        return toString().equals("get");
    }
    public boolean isPUT() {
        return toString().equals("put");
    }
    public boolean isDELETE() {
        return toString().equals("delete");
    }

}
