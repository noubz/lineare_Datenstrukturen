
/**
 * @author Niclas Berger
 */
public class Schlange2
{
    // innere Klasse ------------------------
    private class Knoten
    {
        // Bezugsobjekte
        private Object	        kenntObject;
        private Knoten 		kenntNachfolger;
        
        // Konstruktor
        public Knoten(Object pInhalt)
        {
            kenntObject = pInhalt;
            kenntNachfolger = null;
        }
        
        // Methoden
        public Object getObject()
        {
            return kenntObject;
        }
        
        public Knoten getNachfolger()
        {
            return kenntNachfolger;
        }
        
        public void setNachfolger(Knoten pNachfolger)
        {
            kenntNachfolger = pNachfolger;
        }
    }
    // Ende der inneren Klasse ----------------------------
    
    // Bezugsobjekte
    /**
     * Das erste Schlangenelement
     */
    private Knoten hatKopf;
    private Knoten hatEnde;

    // Attribute
    /**
     * Die Anzahl der Schlangenelemente
     */
    private int zAnzahl;

    // Konstruktor
    /**
     * Eine neue leere Schlange wird erzeugt.
     */
    public Schlange2()
    {
        hatKopf = null;
        hatEnde = null;
    }

    // Methoden
    /**
     * Es wird zurueckgegeben, ob die Schlange leer ist.
     * @return true, wenn die Schlange leer ist
     */
    public boolean istLeer()
    {
        if (zAnzahl == 0) { return true; }
        return false;
    }
    
    public int length() { return zAnzahl; }

    /**
     * Das erste Object der Schlange wird zurueckgegeben.
     * @return das erste Object der Schlange
     */
    public Object erstes()
    {
        if (zAnzahl == 0) { return null; }
        return hatKopf.getObject();
    }
    
    /**
     * Das letzte Object der Schlange wird zurueckgegeben.
     * @return das letzte Object der Schlange
     */
    public Object letztes()
    {
        if (zAnzahl == 0) { return null; }
        return hatEnde.getObject();
    }

    /**
     * Ein neuer Passagier wird an das Ende der Schlange 	
     * angehaengt.
     * @param pPassagier der neue Passagier
     */
    public void haengeAn(Object pInhalt)
    {
        Knoten tmpKnoten = new Knoten(pInhalt);
        
        if (this.istLeer()) {
            hatKopf = tmpKnoten;
        }
        else {
            hatEnde.setNachfolger(tmpKnoten);
        }
        
        hatEnde = tmpKnoten;
        zAnzahl++;
    }
    
    /**
     * Das erste Element der Schlange wird entfernt.
     */
    public void entferneErstes()
    {
        if (!this.istLeer())
        {
            if (zAnzahl == 1)
            {
                hatKopf = null;
                hatEnde = null;
            }
            else {
                hatKopf = hatKopf.getNachfolger();
            }
            zAnzahl--;
        }
    }
    
    /**
     * Es wird geprüft, ob ein Object in der Liste vorhanden ist
     * @param pObject zu prüfendes Object
     * @return true, wenn Object in Liste enthalten ist
     */
    public boolean contains(Object pObject) {
        Knoten lKnoten = hatKopf;
        
        do {
            if (lKnoten.getObject() == pObject) { return true; }
            lKnoten = lKnoten.getNachfolger();
        } while (lKnoten != null); 
        
        return false;
    }
}
