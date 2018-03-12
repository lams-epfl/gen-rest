import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;


public class Parser {
    private static Nodes nodes = new Nodes();

    public static void main(String[] args) {

        File inputFile = new File("20180123.sxm");

        parse(inputFile);

        nodes.sortById();
        nodes.filter(node -> node.getKind().startsWith("human")).print();

        //nodes.filter(node -> node.getId() == "f8ef23ca-22ca-4359-91ba-ee3ff1f7bbb0".hashCode()).printAll();

    }

    private static void parse(File inputFile) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
            System.out.println("----------------------------");

            parseNodes(doc.getElementsByTagName("node"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseNodes(NodeList list) {

        for (int temp = 0; temp < list.getLength(); temp++) {
            org.w3c.dom.Node nNode = list.item(temp);

            if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
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

                }
                nodes.add(node);
            }
        }
    }
}