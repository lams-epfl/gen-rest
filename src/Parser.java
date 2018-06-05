import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;


public class Parser {
    private static Nodes nodes = new Nodes();
    private static Edges edges = new Edges();
    private static Data data = new Data(nodes, edges);
    private static File inputFile = new File("struggle.sxm");
    private static File outputFile = new File("openapi.yml");

    public static void main(String[] args) {

        parse(inputFile);


        //data.workingObjects().filter(node -> !node.localActions().isEmpty()).print();
        //data.workingObjects().get(1).localActions().get(0).neighborsOut().print();

        //nodes.get(21).list().getData().print();
        //nodes.get(21).list().print();
        //nodes.get(21).print();
        //nodes.filter(node -> node.kind().contains("object")).print();
        //data.print();
        //data.print();



        generateApi(outputFile, data);
/*

        String[] s = nodes.getWithId("f7dac2b3-f68d-4950-bc84-a1f13e37fda6".hashCode()).name().split("\\{|\\}");
        for (int i = 0; i < s.length; i++) {
            System.out.println(i + " " + s[i]);
        }
        Schema sch = new Schema(s[0], s[1]);
        System.out.println(sch.properties(3));
*/

        //data.getEdges().get(0).nodes().print();

        //edges.filter(edge -> edge.hasRole()).get(0).unfoldBelow(0);
        //data.print();
        //nodes.get(0).edges().get(0).list().print();

        //nodes.get(0).unfoldBelow();

        //nodes.getWithId("36f483ca-98ff-4bc0-8b24-d0f95f987e76".hashCode()).unfoldAbove();
        //System.out.println(nodes.get(0).list().hasData());
        //nodes.sortById();
        //nodes.filter(node -> node.id() == 1421618084).print();
        //edges.filter(node -> node.source()  == 1421618084).print();
        //edges.filter(node -> node.target()  == 1421618084).print();


        //nodes.filter(node -> node.id() == "f8ef23ca-22ca-4359-91ba-ee3ff1f7bbb0".hashCode()).printAll();



    }

    private static void parse(File inputFile) {
        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            //System.out.println("Root element : " + doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            //System.out.println("----------------------------");

            parseNodes(doc.getDocumentElement().getChildNodes().item(1).getChildNodes().item(1).getChildNodes());
            parseEdges(doc.getDocumentElement().getChildNodes().item(1).getChildNodes().item(3).getChildNodes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseNodes(NodeList list) {

        for (int temp = 0; temp < list.getLength(); temp++) {
            org.w3c.dom.Node n = list.item(temp);

            if (n.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element eElement = (Element) n;
                Node node = new Node();

                if (eElement.hasAttributes()) {
                    node.addId(eElement.getAttributes().item(0).getNodeValue().hashCode());
                }

                NodeList children = eElement.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    if (children.item(i).getNodeName().equals("kind")) {
                        node.addKind(children.item(i).getTextContent());
                    }
                    if (children.item(i).getNodeName().equals("name")) {
                        node.addName(children.item(i).getTextContent());
                    }
                    if (children.item(i).getNodeName().equals("composite")) {
                        node.addComposite(children.item(i).getTextContent());
                    }
                    if (children.item(i).getNodeName().equals("stereotype")) {
                        node.addStereotype(children.item(i).getTextContent());
                    }
                    if (children.item(i).getNodeName().equals("alignment")) {
                        node.addAlignment(children.item(i).getTextContent());
                    }

                }
                nodes.add(node);
            }
        }
    }


    private static void parseEdges(NodeList list) {

        for (int temp = 0; temp < list.getLength(); temp++) {
            org.w3c.dom.Node e = list.item(temp);

            if (e.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element eElement = (Element) e;
                //Edge edge = new Edge();
                Integer id = null;
                Integer source = null;
                Integer target = null;
                String kind = "";
                String name = "";
                String stereotype = "";
                String alignment = "";
                String src_card = "";
                String src_role = "";


                if (eElement.hasAttributes()) {
                    //edge.addId(eElement.getAttributes().item(0).getNodeValue().hashCode());
                    id = eElement.getAttributes().item(0).getNodeValue().hashCode();
                }

                NodeList children = eElement.getChildNodes();
                for (int i = 0; i < children.getLength(); i++) {
                    if (children.item(i).getNodeName().equals("source")) {
                        //edge.addSource(children.item(i).getAttributes().item(0).getNodeValue().hashCode());
                        source = children.item(i).getAttributes().item(0).getNodeValue().hashCode();
                    }
                    if (children.item(i).getNodeName().equals("target")) {
                        //edge.addTarget(children.item(i).getAttributes().item(0).getNodeValue().hashCode());
                        target = children.item(i).getAttributes().item(0).getNodeValue().hashCode();
                    }
                    if (children.item(i).getNodeName().equals("kind")) {
                        //edge.addKind(children.item(i).getTextContent());
                        kind = children.item(i).getTextContent();
                    }
                    if (children.item(i).getNodeName().equals("name")) {
                        //edge.addName(children.item(i).getTextContent());
                        name = children.item(i).getTextContent();
                    }
                    if (children.item(i).getNodeName().equals("stereotype")) {
                        //edge.addStereotype(children.item(i).getTextContent());
                        stereotype = children.item(i).getTextContent();
                    }
                    if (children.item(i).getNodeName().equals("alignment")) {
                        //edge.addAlignment(children.item(i).getTextContent());
                        alignment = children.item(i).getTextContent();
                    }
                    if (children.item(i).getNodeName().equals("src_card")) {
                        //edge.addCard(children.item(i).getTextContent());
                        src_card = children.item(i).getTextContent();
                    }
                    if (children.item(i).getNodeName().equals("src_role")) {
                        //edge.addRole(children.item(i).getTextContent());
                        src_role = children.item(i).getTextContent();
                    }

                }
                //edges.add(edge);
                edges.add(new Edge(null, id, source, target, kind, name, stereotype, alignment, src_card, src_role));
            }
        }
    }

    public static void writing(File file, Writer w, String s) {
        try {
            w.write(s);
        } catch (IOException e) {
            System.err.println("Problem writing to the file " + file.getName());
        }
    }

    public static void fillApi(Writer w, String s) {
        writing(outputFile, w, s);
    }



    public static void generateApi(File outputFile, Data data) {
        try {
            String s = "openapi: 3.0.0" + line() +
                    "servers: []" + line() +
                    "info:" + line() +
                    tab(1) + "version: 1.0.0" + line() +
                    tab(1) + "title: %s" + line() +
                    "tags: %s" + line() +
                    "paths: %s" + line() +
                    "components:" + line() +
                    tab(1) + "schemas: %s";

            Node root = data.root();
            String title = root.name();
            Nodes objects = data.workingObjects();

            String tag = "";
            String paths = "";
            String tags = "";


            for (int i = 0; i < objects.size(); i++) {
                tag = objects.get(i).name();
                tags = tags + line() + tab(1) + "- name: " + tag;

                Nodes actions = objects.get(i).localActions();
                for (int j = 0; j < actions.size(); j++) {
                    Nodes requests = actions.get(j).requests();
                    if (!requests.isEmpty()) {
                        Requests r = new Requests(requests.size());
                        for (int k = 0; k < requests.size(); k++) {
                            r.add(new Request());
                            r.get(k).setTags(tag);
                            r.get(k).setDescription(requests.get(k).name());
                            r.get(k).setVerb(requests.get(k).stereotype());
                            Nodes parameters = requests.get(k).neighborsOut();
                            for (int l = 0; l < parameters.size(); l++) {
                                if (parameters.get(l).stereotype().contains("in")) {
                                    r.get(k).setIn(parameters.get(l).name());
                                    r.get(k).setPath(parameters.get(l).edgesOut().get(0).card()); //assuming there is always exactly one edge out
                                } else if (parameters.get(l).stereotype().contains("out")) {
                                    r.get(k).setOut(parameters.get(l).name());
                                }
                            }
                            r.get(k).print();
                        }


                        Requests[] requestsByPath = r.split();
                        String[] verbsByPath = new String[requestsByPath.length];

                        for (int n = 0; n < requestsByPath.length; n++) {
                            verbsByPath[n] = line() + tab(1) + requestsByPath[n].get(0).path() + ":";
                            for (int m = 0; m < requestsByPath[n].size(); m++) {
                                verbsByPath[n] = verbsByPath[n] + line() +
                                        tab(2) + requestsByPath[n].get(m).verb() + ":" + line() + //verb
                                        tab(3) + "tags: " + line() +
                                        tab (4) + "- " + requestsByPath[n].get(m).tags() + line() + //tag
                                        tab (3) + "description: " + requestsByPath[n].get(m).description() + line() + //title

                                        tab (3) + "responses: " + line() +
                                        tab (4) + "'200':" + line() +
                                        tab (5) + "description: request successful" + line() + //action
                                        tab (5) + "content:" + line() +
                                        tab (6) + "'application/json':" + line() +
                                        tab (7) + "schema:" + line() +
                                        tab (8) + "$ref: '#/components/schemas/%s'" + line() + //out

                                        tab (3) + "requestBody:" + line() +
                                        tab (4) + "content:" + line() +
                                        tab (5) + "application/json:" + line() +
                                        tab (6) + "schema:" + line() +
                                        tab (7) + "$ref: '#/components/schemas/%s'" + line() +  //in
                                        tab (4) + "required: true";

                                /*verbsByPath[n] = verbsByPath[n] + "\n    " + requestsByPath[n].get(m).verb() + ":" + //verb
                                        "\n      tags: " +
                                        "\n        - " + requestsByPath[n].get(m).tags() + //tag
                                        "\n      description: " + requestsByPath[n].get(m).description() + //title
                                        "\n      responses: " +
                                        "\n        '200':" +
                                        "\n          description: request successful" + //action
                                        "\n          content:" +
                                        "\n            'application/json':" +
                                        "\n              schema:" +
                                        "\n                $ref: '#/components/schemas/%s'" + //out
                                        "\n      requestBody:" +
                                        "\n        content:" +
                                        "\n          application/json:" +
                                        "\n            schema:" +
                                        "\n              $ref: '#/components/schemas/%s'" + //in
                                        "\n        required: true";*/
                                // HOW to know whether it will be a schema or not?
                            }
                            paths = paths + verbsByPath[n];
                        }
                    }

                }
            }
//
//            Node owner = root.edgesOut().get(0).nodes().target();
//            String tag = owner.name();
//
//            Node create = owner.edgesOut().get(owner.edgesOut().size()-1).nodes().target();
//            String description = create.name();
//
//            Node level3 = create.edgesOut().get(0).nodes().target();
//            String paths = "\n  " + level3.name().substring(8) + ":" +
//                    "\n    %s:" + //verb
//                    "\n      description: %s" + //title
//                    "\n      tag: %s" + //tag
//                    "\n      responses: " +
//                    "\n        '200':" +
//                    "\n          description: successful %s request" + //action
//                    "\n          content:" +
//                    "\n            '*/*':" +
//                    "\n              schema:" +
//                    "\n                $ref: '#/components/schemas/%s'" + //out
//                    "\n      requestBody:" +
//                    "\n        content:" +
//                    "\n          application/json:" +
//                    "\n            schema:" +
//                    "\n              $ref: '#/components/schemas/%s'" + //in
//                    "\n        required: true";
//
//            Node level4 = level3.edgesOut().get(0).nodes().target();
//            String verb = level4.name().substring(8);
//
//            paths = String.format(paths, verb, description, "\n        - " + tag, description, "", "");

            String schemas = "";



            FileOutputStream is = new FileOutputStream(outputFile);
        OutputStreamWriter osw = new OutputStreamWriter(is);
        Writer w = new BufferedWriter(osw);

        s = String.format(s, title, tags, paths, schemas);

        fillApi(w, s);


        w.close();


    } catch (IOException e) {
        System.err.println("Problem writing to the file " + outputFile.getName());
    }



    }

    public static String tab(int n) {
        String tab = "";
        for (int i = 0; i < n; i++) {
            tab = tab + "  ";
        }
        return tab;
    }

    public static String line() {
        return "\n";
    }

}