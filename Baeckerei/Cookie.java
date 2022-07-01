
/**
 * Beschreiben Sie hier die Klasse Cookie.
 * 
 * @author Niclas Berger
 */
public class Cookie
{
    private String zColor;
    private String zTopping;

    /**
     * Erstellen eines neuen Cookies
     */
    public Cookie(String pColor, String pTopping)
    {
        zColor = pColor;
        zTopping = pTopping;
    }
    
    /**
     * Gibt die Farbe des Cookies zurück
     * @return Farbe des Cookies
     */
    public String getColor() { return zColor; }
    
    /**
     * Gibt das Topping des Cookies zurück
     * @return Topping des Cookies
     */
    public String getTopping() { return zTopping; }
}
