/*
 * Codigo original de Stackoverflow usuario Jomoos
 * @see <a href="https://stackoverflow.com/questions/8345529/java-parsing-xml-using-dom/8346867#8346867">
 * Java - Parsing xml using DOM</a>
 */

package xml.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.DOMException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Jose Fermin Athie Campollo
 */
public class XmlNode implements Serializable{

    private Element node;
    private XmlDocument parent;

    /**
     * Inicializador por nodo
     * @param node 
     */
    XmlNode(Element node) {
        this.node = node;
        this.parent = null;
    }

    /**
     * Inicializador por Document and Element
     * @param parent
     * @param node 
     */
    XmlNode(XmlDocument parent, Element node) {
        this.node = node;
        this.parent = parent;
    }

    /**
     * Pbtener el nodo
     * @return 
     */
    Node getNode() {
        return node;
    }
    
    /**
     * Obtiene el valor del nodo
     * @return 
     */
    public String getNodeValue() {
        return node.getTextContent();
    }

    /**
     * Obtiene el tag padre
     * @return 
     */
    public XmlDocument getParent() {
        return parent;
    }

    /**
     * Setear el padre
     * @param parent 
     */
    public void setParent(XmlDocument parent) {
        this.parent = parent;
    }

    /**
     * Obtener la lista de nodos hijos
     * @return 
     */
    public List<XmlNode> getChildNodes() {
        List<XmlNode> list = new ArrayList<>();
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                list.add(new XmlNode((Element) n));
            }
        }

        return list;
    }

    /**
     * Obtener el primer hijo
     * @return 
     */
    public XmlNode getFirstChild() {
        return getChildNodes().get(0);
    }
    
    /**
     * Obtener el ultimo hijo
     * @return 
     */
    public XmlNode getLastChild() {
        List<XmlNode> childs = getChildNodes();
        if (childs.isEmpty()) {
            return null;
        }

        return childs.get(childs.size() - 1);
    }

    /**
     * Obtener el valor del nodo por su tag
     * @param tagName
     * @return 
     */
    public List<XmlNode> getNodesByTagName(String tagName) {
        List<XmlNode> list = new ArrayList<>();
        NodeList nodeList = node.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node n = nodeList.item(i);
            if (n.getNodeType() == Node.ELEMENT_NODE) {
                list.add(new XmlNode((Element) n));
            }
        }

        return list;
    }

    /**
     * Obtener el primer nodo mediante el nombre
     * @param tagName
     * @return 
     */
    public XmlNode getFirstNodeByTagName(String tagName) {
        return getNodesByTagName(tagName).get(0);
    }

    /**
     * Obtener el valor string del tag
     * @param tagName
     * @return
     * @throws XmlException 
     */
    public String getTagValue(String tagName) throws XmlException {
        NodeList tagList = node.getElementsByTagName(tagName);
        if (tagList.getLength() == 0) {
            throw new XmlException("Tag: '" + tagName + "' not present");
        }

        NodeList nlList = tagList.item(0).getChildNodes();
        Node nValue = (Node) nlList.item(0);
        if (nValue != null) {
            return nValue.getNodeValue().isEmpty() ? "" : nValue.getNodeValue();
        } else {
            return "";
        }
    }

    /**
     * Obtener el valor int del tag
     * @param tagName
     * @return
     * @throws XmlException
     * @throws NumberFormatException
     * @throws DOMException 
     */
    public int getIntTagValue(String tagName) throws XmlException, NumberFormatException, DOMException {
        String nValue = getTagValue(tagName);

        if (nValue != null && esNumerico(nValue)) {
            return nValue.isEmpty() ? 0 : Integer.parseInt(nValue);
        } else {
            return 0;
        }
    }

    /**
     * Obtiene el valor el valor Double del tag
     * @param tagName
     * @return
     * @throws XmlException 
     */
    public double getDoubleTagValue(String tagName) throws XmlException {

        String nValue = getTagValue(tagName);

        if (nValue != null && esNumerico(nValue)) {
            return (double) (nValue.isEmpty() ? 0 : Double.parseDouble(nValue));
        } else {
            return (double) 0;
        }
    }

    /**
     * Verifica si es numerico, debido a que se muestra un error al convertir
     * de String a int obtiene un numero como "0" el cual es incorrecto.
     * @param cadena
     * @return 
     */
    private boolean esNumerico(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    /**
     * Obtiene los atributos del tag
     * @param attributeName
     * @return 
     */
    public String getAttributeValue(String attributeName) {
        return node.getAttribute(attributeName);
    }

    /**
     * Obtiene el nombre del nodo
     * @return 
     */
    public String getNodeName() {
        return node.getTagName();
    }

    /**
     * Establecer los atributos del nodo
     * @param name
     * @param value
     * @throws XmlException 
     */
    public void setAttribute(String name, String value) throws XmlException {
        if (parent == null) {
            throw new XmlException("Parent node not present.");
        }

        node.setAttribute(name, value);
    }

    /**
     * Establecer el nombre del tag
     * @param name
     * @param value
     * @throws XmlException 
     */
    public void setTag(String name, String value) throws XmlException {
        if (parent == null) {
            throw new XmlException("Parent node not present.");
        }

        XmlNode xmlNode = parent.createNode(name, value);
        node.appendChild(xmlNode.node);
    }

    /**
     * Agregar un nodo hijo a la secuencia actual
     * @param xmlNode
     * @throws XmlException 
     */
    public void addChildNode(XmlNode xmlNode) throws XmlException {
        if (parent == null) {
            throw new XmlException("Parent node not present.");
        }

        node.appendChild(xmlNode.node);
    }

    /**
     * Agregar nodo hijo despues del padre
     * @param nodeName
     * @return
     * @throws XmlException 
     */
    public XmlNode addChildNode(String nodeName) throws XmlException {
        if (parent == null) {
            throw new XmlException("Parent node not present.");
        }

        XmlNode child = parent.createNode(nodeName);
        node.appendChild(child.getNode());

        return child;
    }
}
