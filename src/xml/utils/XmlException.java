/*
 * Codigo original de Stackoverflow usuario Jomoos
 * @see <a href="https://stackoverflow.com/questions/8345529/java-parsing-xml-using-dom/8346867#8346867">
 * Java - Parsing xml using DOM</a>
 */


package xml.utils;

import java.io.Serializable;

/**
 *
 * @author Jose Fermin Athie Campollo
 */
public class XmlException extends Exception implements Serializable{
    private static final long serialVersionUID = 1L;

    public XmlException(String message, Throwable cause)  {
        super(message, cause);
    }

    public XmlException(String message)  {
        super(message);
    }

    public XmlException(Throwable cause)  {
        super(cause);
    }
    
}
