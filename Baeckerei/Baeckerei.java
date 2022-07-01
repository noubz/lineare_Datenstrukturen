/**
 * Die Klasse Baeckerei wurde automatisch erstellt: 
 * 
 * @author 
 * @version 22.9.2021
 */

import sum.komponenten.*;
import sum.werkzeuge.*;
import sum.ereignis.*;
import GLOOP.GLLicht;
import GLOOP.GLKamera;

public class Baeckerei extends EBAnwendung
{
    // Objekte
    private Etikett hatEtikettFehler;
    private Etikett hatEtikettKeksfarbe;
    private Etikett hatEtikettKeksbelag;
    private Textfeld hatTextfeldFarbe;
    private Textfeld hatTextfeldBelag;
    private Knopf hatKnopfBacken;
    private Knopf hatKnopfSuchen;
    private Etikett hatEtikettKeksglas1;
    private Etikett hatEtikettKeksglas2;
    private Etikett hatEtikettGlas1;
    private Etikett hatEtikettGlas2;
    private Fortschrittsbalken hatFortschrittsbalkenGlas1;
    private Fortschrittsbalken hatFortschrittsbalkenGlas2;
    private Auswahl hatAuswahlKeksglas;
    private Knopf hatKnopfEssen;
    private Knopf hatKnopfBeenden;

    // Deklaration der Attribute
    private VisualStack<Cookie> hatKeksglas1;
    private VisualStack<Cookie> hatKeksglas2;
    private Stack<Cookie> markedKekse;
    private int currentKeksglas;
    private int glasGroesse;
    
    private SaveFile hatDatenspeicherung;
    
/**
 * Konstruktor
 */
    public Baeckerei()
    {
        //Initialisierung der Oberklasse
        super(455, 300);
        
        new GLLicht();
        new GLKamera(500,400);
        
        glasGroesse = 5;
        
        hatEtikettFehler = new Etikett(0, 7, 455, 25, "");
        // Ausrichtung
        hatEtikettFehler.setzeAusrichtung(Ausrichtung.MITTE);
        hatEtikettKeksfarbe = new Etikett(40, 40, 70, 25, "Keksfarbe:");
        // Ausrichtung
        hatEtikettKeksfarbe.setzeAusrichtung(Ausrichtung.RECHTS);
        hatEtikettKeksbelag = new Etikett(41, 86, 70, 25, "Keksbelag:");
        // Ausrichtung
        hatEtikettKeksbelag.setzeAusrichtung(Ausrichtung.RECHTS);
        hatTextfeldFarbe = new Textfeld(120, 39, 200, 27, "");
        // Ausrichtung
        hatTextfeldFarbe.setzeAusrichtung(Ausrichtung.LINKS);
        hatTextfeldBelag = new Textfeld(120, 84, 200, 27, "");
        // Ausrichtung
        hatTextfeldBelag.setzeAusrichtung(Ausrichtung.LINKS);
        hatKnopfBacken = new Knopf(340, 39, 75, 27, "Backen");
        hatKnopfBacken.setzeBearbeiterGeklickt("hatKnopfBackenGeklickt");
        hatKnopfSuchen = new Knopf(340, 85, 75, 27, "Suchen");
        hatKnopfSuchen.setzeBearbeiterGeklickt("hatKnopfSuchenGeklickt");
        hatEtikettKeksglas1 = new Etikett(70, 140, 60, 25, "Keksglas 1");
        // Ausrichtung
        hatEtikettKeksglas1.setzeAusrichtung(Ausrichtung.MITTE);
        hatEtikettKeksglas2 = new Etikett(200, 140, 60, 25, "Keksglas 2");
        // Ausrichtung
        hatEtikettKeksglas2.setzeAusrichtung(Ausrichtung.MITTE);
        hatEtikettGlas1 = new Etikett(40, 170, 120, 25, "Leer...");
        // Ausrichtung
        hatEtikettGlas1.setzeAusrichtung(Ausrichtung.MITTE);
        hatEtikettGlas2 = new Etikett(170, 170, 120, 25, "Leer...");
        // Ausrichtung
        hatEtikettGlas2.setzeAusrichtung(Ausrichtung.MITTE);
        hatFortschrittsbalkenGlas1 = new Fortschrittsbalken(40, 200, 120, 15, 0, glasGroesse);
        hatFortschrittsbalkenGlas2 = new Fortschrittsbalken(170, 200, 120, 15, 0, glasGroesse);
        hatAuswahlKeksglas = new Auswahl(310, 130, 105, 25);
        hatAuswahlKeksglas.setzeBearbeiterGeaendert("hatAuswahlKeksglasGeaendert");
        hatAuswahlKeksglas.haengeAn("Keksglas 1");
        hatAuswahlKeksglas.haengeAn("Keksglas 2");
        hatKnopfEssen = new Knopf(100, 230, 130, 30, "Essen");
        hatKnopfEssen.setzeBearbeiterGeklickt("hatKnopfEssenGeklickt");
        hatKnopfBeenden = new Knopf(335, 235, 80, 25, "Beenden");
        hatKnopfBeenden.setzeBearbeiterGeklickt("hatKnopfBeendenGeklickt");
        
        // Instanziierung der Keksgläser
        hatKeksglas1 = new VisualStack<Cookie>(-125,-150);
        hatKeksglas2 = new VisualStack<Cookie>(125,-150);
        markedKekse = new Stack<Cookie>();
        currentKeksglas = 1;
        
        hatDatenspeicherung = new SaveFile("data.txt");
        String daten = hatDatenspeicherung.read();
        
        if (daten != null) {
            String[] glaeserDaten = daten.split(" ");
            
            for (String cookie : glaeserDaten[0].split(";")) {
                String[] cookieDaten = cookie.split("-");
                
                hatKeksglas1.push(new Cookie(cookieDaten[0], cookieDaten[1]));
                hatFortschrittsbalkenGlas1.setzeWert(hatFortschrittsbalkenGlas1.wert() + 1);
            }
            for (String cookie : glaeserDaten[1].split(";")) {
                String[] cookieDaten = cookie.split("-");
                
                hatKeksglas2.push(new Cookie(cookieDaten[0], cookieDaten[1]));
                hatFortschrittsbalkenGlas2.setzeWert(hatFortschrittsbalkenGlas2.wert() + 1);
            }
        }
        
        this.update();
    }

/**
 * Vorher: Ereignis GeklicktvonhatKnopfBacken fand statt.
 * Nachher: (schreiben Sie, was in dieser Methode ausgefuehrt wird)
 */
    public void hatKnopfBackenGeklickt()
    {
        String lFarbe = hatTextfeldFarbe.inhaltAlsText();
        String lBelag = hatTextfeldBelag.inhaltAlsText();
        
        hatTextfeldFarbe.loescheAlles();
        hatTextfeldBelag.loescheAlles();
        
        if (!(lFarbe.equals("") || lBelag.equals("") || lFarbe.matches(".*\\d.*") || lBelag.matches(".*\\d.*"))) {
            Cookie lKeks = new Cookie(lFarbe, lBelag);
            if (currentKeksglas == 1 && hatFortschrittsbalkenGlas1.wert() < glasGroesse) {
                hatKeksglas1.push(lKeks);
                hatFortschrittsbalkenGlas1.setzeWert(hatFortschrittsbalkenGlas1.wert() + 1);
            }
            else if (currentKeksglas == 2 && hatFortschrittsbalkenGlas2.wert() < glasGroesse) {
                hatKeksglas2.push(lKeks);
                hatFortschrittsbalkenGlas2.setzeWert(hatFortschrittsbalkenGlas2.wert() + 1);
            }

            this.update();
        }
        else { this.fehler(); }
    }
    
/**
 * Vorher: Ereignis GeklicktvonhatKnopfBacken fand statt.
 * Nachher: (schreiben Sie, was in dieser Methode ausgefuehrt wird)
 */
    public void hatKnopfSuchenGeklickt() 
    {
        if (!markedKekse.isEmpty()) {  
            while (!markedKekse.isEmpty()) {
                if (hatKeksglas1.contains(markedKekse.peek())) { hatKeksglas1.mark(markedKekse.peek(), false); }
                else { hatKeksglas2.mark(markedKekse.peek(), false); }
                markedKekse.pop();
            }
            hatKnopfSuchen.setzeInhalt("Suchen");
        } 
        else {
            String lFarbe = hatTextfeldFarbe.inhaltAlsText();
            String lBelag = hatTextfeldBelag.inhaltAlsText();
            
            hatTextfeldFarbe.loescheAlles();
            hatTextfeldBelag.loescheAlles();
            
            for (Object[] instances : new Object[][] {
                {hatKeksglas1, hatFortschrittsbalkenGlas1.wert()}, 
                {hatKeksglas2, hatFortschrittsbalkenGlas2.wert()}}) 
            {
                VisualStack<Cookie> keksglas = (VisualStack<Cookie>) instances[0];
                for (Cookie keks : this.toArray(keksglas.copy(), (int) instances[1])) {
                    if ((keks.getColor().equals(lFarbe) || lFarbe.equals("")) &&
                        (keks.getTopping().equals(lBelag) || lBelag.equals(""))) {
                        keksglas.mark(keks, true);
                        markedKekse.push(keks);
                    }
                }
            }
            
            if (markedKekse.isEmpty()) { this.fehler(); }
            else { hatKnopfSuchen.setzeInhalt("Stop"); }
        }
    }

/**
 * Vorher: Ereignis GeaendertvonhatAuswahlKeksglas fand statt.
 * Nachher: (schreiben Sie, was in dieser Methode ausgefuehrt wird)
 */
    public void hatAuswahlKeksglasGeaendert()
    {
        currentKeksglas = hatAuswahlKeksglas.index();
    }

/**
 * Vorher: Ereignis GeklicktvonhatKnopfEssen fand statt.
 * Nachher: (schreiben Sie, was in dieser Methode ausgefuehrt wird)
 */
    public void hatKnopfEssenGeklickt()
    {
        if (currentKeksglas == 1 && !hatKeksglas1.isEmpty()) {
            hatKeksglas1.pop();
            hatFortschrittsbalkenGlas1.setzeWert(hatFortschrittsbalkenGlas1.wert() - 1);
        }
        else if (currentKeksglas == 2 && !hatKeksglas2.isEmpty()) {
            hatKeksglas2.pop();
            hatFortschrittsbalkenGlas2.setzeWert(hatFortschrittsbalkenGlas2.wert() - 1);
        }
        
        this.update();
    }

/**
 * Vorher: Ereignis GeklicktvonhatKnopfBeenden fand statt.
 * Nachher: (schreiben Sie, was in dieser Methode ausgefuehrt wird)
 */
    public void hatKnopfBeendenGeklickt()
    {
        if (hatKeksglas1.isEmpty() && hatKeksglas2.isEmpty()) { 
            hatDatenspeicherung.deleteFile();
            System.exit(0); 
        }
        
        String daten = "";
        for (Object[] instances : new Object[][] {{hatKeksglas1,hatFortschrittsbalkenGlas1}, {hatKeksglas2,hatFortschrittsbalkenGlas2}}) {
            VisualStack<Cookie> keksglas = (VisualStack<Cookie>) instances[0];
            Fortschrittsbalken fortschrittsbalken = (Fortschrittsbalken) instances[1];
            
            for (Cookie keks : this.toArray(keksglas, fortschrittsbalken.wert())) {
                daten += keks.getColor() + "-" + keks.getTopping();
                daten += ";";
            }
            
            if (keksglas.isEmpty()) { daten += ";"; }
            daten += " ";
        }

        hatDatenspeicherung.write(daten);
        System.exit(0);
    }

/**
 * Aktuelisiert, die Anzeige/den Inhalt der Keksgläser
 */
    private void update() 
    {
        if (hatKeksglas1.isEmpty()) {
            hatEtikettGlas1.setzeInhalt("Leer...");
        } else {
            Cookie lKeks = hatKeksglas1.peek();
            hatEtikettGlas1.setzeInhalt(lKeks.getColor() + " " + lKeks.getTopping());
        }
        
        if (hatKeksglas2.isEmpty()) {
            hatEtikettGlas2.setzeInhalt("Leer...");
        } else {
            Cookie lKeks = hatKeksglas2.peek();
            hatEtikettGlas2.setzeInhalt(lKeks.getColor() + " " + lKeks.getTopping());
        }
    }
    
/**
 * Gibt einen Fehler in Form eines Etiketts aus
 */
    private void fehler() 
    {
        hatEtikettFehler.setzeInhalt("!!! Bitte Eingaben und Auswahlen überprüfen !!!");
        
        try { Thread.sleep(3500); }
        catch (InterruptedException ie) {}
        
        hatEtikettFehler.setzeInhalt("");
    }
   
/**
 * Erstellt einen Array mit allen Cookies eines Glases.
 */
    public Cookie[] toArray(ExtendedStack<Cookie> pStack, int pLength) { 
        Cookie[] array = new Cookie[pLength];
        ExtendedStack<Cookie> lStack = pStack.copy();

        for (int i = 0; i < array.length; i++) {
            array[i] = lStack.peek();
            lStack.pop();
        }

        return array;
    }
    
    public static void main(String[] args) {
        new Baeckerei();
    }
}