
/**
 * Klasse zur Verwaltung von Vokabeln und deren Übersetzungen.
 *
 * @author Niclas Berger
 */
public class Vokabel
{
    private String englisch;
    private List<String> deutsch;

    /**
     * Constructor for objects of class Vokabel
     */
    public Vokabel(String pEnglisch, List<String> pDeutsch)
    {
        englisch = pEnglisch;
        deutsch = pDeutsch;
    }

    public String getEnglisch() { return englisch; }
    public List<String> getDeutsch() { 
        deutsch.toFirst();
        return deutsch; 
    }
    
    
    @Override
    public String toString() {
        deutsch.toFirst();
        String out = englisch + " - " + deutsch.getContent();
        
        deutsch.next();
        while (deutsch.hasAccess()) {
            out += ", " + deutsch.getContent();
            deutsch.next();
        }
        
        return out;
    }
    
    public int compareTo(Vokabel other) {
        if (other == null)
            return -1;
        
        return this.getEnglisch().compareTo(other.getEnglisch());
    }
    
    public String toDataString() {
        deutsch.toFirst();
        String out = englisch + "-" + deutsch.getContent();
        
        deutsch.next();
        while (deutsch.hasAccess()) {
            out += "," + deutsch.getContent();
            deutsch.next();
        }
        
        return out;
    }
}
