import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
//import org.w3c.dom.Node;
import org.w3c.dom.Element;


public class AirplaneParser {
    //private static ArrayList<Node> nodes = new ArrayList<Node>();
    private static Nodes nodes = new Nodes();

    public static void main(String[] args) {

        try {
            File inputFile = new File("20180123.sxm");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
            System.out.println("----------------------------");

            NodeList second = doc.getChildNodes().item(0).getChildNodes();
            /*for (int temp = 0; temp < second.getLength(); temp++) {
                Node nNode = second.item(temp);

                if (!nNode.getNodeName().equals("#text")) {

                    System.out.println("\nCurrent Element : " + nNode.getNodeName());

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;

                        if (eElement.hasAttributes()) {
                            Node n = eElement.getAttributes().item(0);

                            System.out.println(n.getNodeName() + " : "
                                    + n.getNodeValue());
                        }

                        parseNodes(eElement.getChildNodes());
                        //parseNodes(eElement.getElementsByTagName("edge"));
                        //parseNodes(eElement.getElementsByTagName("edge"));

                    }
                }
            }*/
            //parseNodes(second);
//System.out.println(doc.getDocumentElement().getFirstChild().getNextSibling().getNextSibling().getNextSibling().getNextSibling().getNextSibling());
            parseNodes(doc.getElementsByTagName("node"));
            //Collections.sort(nodes, Comparator.comparing(Node::getKind));
            //nodes.get(nodes.size()-1).print();
            //printing(doc.getDocumentElement());
            nodes.sortById();
            nodes.filter(node -> node.getKind().startsWith("human")).print();

            //nodes.filter(node -> node.getId() == "f8ef23ca-22ca-4359-91ba-ee3ff1f7bbb0".hashCode()).printAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void parseNodes(NodeList list) {

        for (int temp = 0; temp < list.getLength(); temp++) {
            org.w3c.dom.Node nNode = list.item(temp);
            //System.out.println("\nCurrent Element : " + nNode.getNodeName());


            if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                Node node = new Node();


                if (eElement.hasAttributes()) {
                    //System.out.println(n.getNodeName() + " : " + n.getNodeValue());
                    node.addId(eElement.getAttributes().item(0).getNodeValue().hashCode());
                }

//                kind = eElement.getElementsByTagName("kind").item(0).getTextContent();
 //               name = eElement.getElementsByTagName("name").item(0).getTextContent();
                //composite = eElement.getElementsByTagName("composite").item(0).getNodeValue();
                //stereotype = eElement.getElementsByTagName("stereotype").item(0).getNodeValue();



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

                    /*NodeList children = eElement.getChildNodes();
                    for (int i = 0; i < children.getLength(); i++) {
                    if (!children.item(i).getNodeName().equals("#text")) {
                        String childName = children.item(i).getNodeName();
                        org.w3c.dom.Node e = eElement.getElementsByTagName(name).item(0);
                        String s = "";
                        if (e.hasAttributes()) {
                            org.w3c.dom.Node a = e.getAttributes().item(0);
                            s = a.getNodeName() + " : " + a.getNodeValue() + " ";
                        }

                        System.out.println(name + " : " + s + e.getTextContent());
                    }
                }*/


            }
        }
    }

/*
    private static boolean printing(Node n) {
        if (!n.hasChildNodes()) {
            return true;
        } else {
            for (int i = 0; i < n.getChildNodes().getLength(); i++) {
                if (!n.getChildNodes().item(i).getNodeName().equals("#text")) {
                    System.out.println(n.getChildNodes().item(i).getNodeName() + " " + n.getChildNodes().item(i).getChildNodes().getLength());
                    return printing(n.getChildNodes().item(i));
                }
            }
        }
        return false;
    }*/

}