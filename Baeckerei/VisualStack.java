import GLOOP.*;
/**
 * Beschreiben Sie hier die Klasse VisualStack.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class VisualStack<ContentType> extends ExtendedStack<ContentType>
{
    private ExtendedStack<GLZylinder> items;
    private int zPosX, zPosY;
    
    /**
     * Konstruktor für Objekte der Klasse VisualStack
     */
    public VisualStack(int pPosX, int pPosY)
    {
        super();
        
        items = new ExtendedStack<GLZylinder>();
        zPosX = pPosX;
        zPosY = pPosY;
        
        new GLQuader(zPosX,zPosY,0, 200,10,180);
        new GLQuader(zPosX-100,zPosY+130,0, 10,270,180);
        new GLQuader(zPosX+100,zPosY+130,0, 10,270,180);
    }
    
    public void mark(ContentType pContent, boolean pMark) {
        if (!super.contains(pContent)) { return; }
        
        ExtendedStack<ContentType> copyStack = this.copy();
        ExtendedStack<GLZylinder> copyItems = items.copy();
        while (!copyStack.isEmpty()) {
            if (copyStack.peek() == pContent) {
                if (pMark) { copyItems.peek().setzeFarbe(255,0,0); }
                else { copyItems.peek().setzeFarbe(1,1,1); }
            }
            copyStack.pop();
            copyItems.pop();
        }
    }
    
    @Override
    public void push(ContentType pContent) {
        super.push(pContent);

        if (items.isEmpty()) {
            items.push(new GLZylinder(zPosX,zPosY+25,0, 90,30));
        } else {
            GLZylinder top = items.peek();
            items.push(new GLZylinder(zPosX,top.gibY()+45,0, 90,30));
        }
        items.peek().setzeDrehung(90,0,0);
    }
    
    @Override
    public void pop() {
        super.pop();
        
        items.peek().loesche();
        items.pop();
    }
}
