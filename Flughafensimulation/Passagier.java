/** 
 * @author Niclas Berger
 * @version 03.09.2021
 */
public class Passagier
{
    // Deklaration der Attribute
    private String zName;
    private String zFluggesellschaft;
    
    private Passagier kenntNachfolger;

    /**
     * Erstellen eines neuen Passagiers
     * 
     * @param pName Name des Passagiers
     * @param pFluggesellschaft Name der Fluggesellschaft
     */
    public Passagier(String pName, String pFluggesellschaft)
    {
        // Initialisierung der Attribute
        zName = pName;
        zFluggesellschaft = pFluggesellschaft;
        
        kenntNachfolger = null;
    }
    
    // Get-Funktionen für die Attribute
    public String getName() { return zName; }
    public String getFluggesellschaft() { return zFluggesellschaft; }
    
    /**
     * Setzt einen Nachfolger für den Passagier fest.
     * @param pNachfolger Der auf den Passagier folgender Passagier
     */
    public void setNachfolger(Passagier pNachfolger) { kenntNachfolger = pNachfolger; }
    
    /**
     * Gibt den Nachfolger des Passagiers zurück
     * @return Passagier/Nachfolger
     */
    public Passagier getNachfolger() { return kenntNachfolger; }
}
