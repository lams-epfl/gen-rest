import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;


public class Parser {
    private static Nodes nodes = new Nodes();
    private static Edges edges = new Edges();

    private static Data data = new Data(nodes, edges);

    public static void main(String[] args) {

        File inputFile = new File("20180123.sxm");

        parse(inputFile);

        //data.print();
        //nodes.get(0).edges().get(0).list().print();

        nodes.getWithId("36f483ca-98ff-4bc0-8b24-d0f95f987e76".hashCode()).unfoldAbove();
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
            System.out.println("Root element : " + doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            System.out.println("----------------------------");

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
}