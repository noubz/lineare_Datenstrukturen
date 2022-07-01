
/**
 * Beschreiben Sie hier die Klasse ExtendedStack.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class ExtendedStack<ContentType> extends Stack<ContentType>
{
    /**
     * 
     */
    public boolean contains(ContentType pObject) { 
        StackNode lNode = top;
        
        while (lNode != null) {
            if (lNode.getContent() == pObject) {
                return true;
            }
            lNode = lNode.getNext();
        }

        return false;
    }
    
    /**
     * Gibt eine Kopie des Stack zurück.
     * 
     * @return Stack in umgekehrter Reihenfolge
     */
    public ExtendedStack<ContentType> copy() {
        ExtendedStack<ContentType> copyStack = new ExtendedStack<ContentType>();

        StackNode lNode = top;
        while (lNode != null) {
            copyStack.push(lNode.getContent());
            lNode = lNode.getNext();
        }
        
        return copyStack;
    }
}
