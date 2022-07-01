/**
 * @author Niclas Berger 
 * @version 3.9.2021
 */

import sum.komponenten.*;
import sum.werkzeuge.*;
import sum.ereignis.*;
import sum.kern.*;

public class Flughafensimulation2 extends EBAnwendung
{
    // Objekte
    private Etikett hatEtikettNachname;
    private Etikett hatEtikettFluggesellschaft;
    private Textfeld hatTextfeld1;
    private Textfeld hatTextfeld2;
    private Etikett hatEtikettWartebereich;
    private Etikett hatEtikettTicketschalter;
    private Knopf hatKnopfAnwendungBeenden;
    private Knopf hatKnopfPassagierAnmelden;
    private Knopf hatKnopfPassagierAnmeldenTicket;
    private Knopf hatKnopfPassagierAbfertigen;
    private Knopf hatKnopfAusgabeTickets;

    // Instanzen
    private Schlange2 hatSchlange;
    private Schlange2 hatTicketausgabe;
    private SaveFile hatDatensicherung;
    
    /**
     * Konstruktor
     */
    public Flughafensimulation2()
    {
        //Initialisierung der Oberklasse
        super(600, 325);

        hatEtikettNachname = new Etikett(50, 75, 100, 25, "Nachname");
        // Ausrichtung
        hatEtikettNachname.setzeAusrichtung(Ausrichtung.RECHTS);
        hatEtikettFluggesellschaft = new Etikett(50, 125, 100, 25, "Fluggesellschaft");
        // Ausrichtung
        hatEtikettFluggesellschaft.setzeAusrichtung(Ausrichtung.RECHTS);
        hatTextfeld1 = new Textfeld(160, 75, 150, 25, "");
        // Ausrichtung
        hatTextfeld1.setzeAusrichtung(Ausrichtung.LINKS);
        hatTextfeld2 = new Textfeld(160, 125, 150, 25, "");
        // Ausrichtung
        hatTextfeld2.setzeAusrichtung(Ausrichtung.LINKS);
        hatEtikettWartebereich = new Etikett(55, 180, 255, 25, "Wartebereich ist leer...");
        // Ausrichtung
        hatEtikettWartebereich.setzeAusrichtung(Ausrichtung.LINKS);
        hatEtikettTicketschalter = new Etikett(55, 215, 255, 25, "Ticketschalter ist leer...");
        // Ausrichtung
        hatEtikettTicketschalter.setzeAusrichtung(Ausrichtung.LINKS);
        hatKnopfAnwendungBeenden = new Knopf(50, 275, 100, 27, "Beenden");
        hatKnopfAnwendungBeenden.setzeBearbeiterGeklickt("hatKnopfAnwendungBeendenGeklickt");
        hatKnopfPassagierAnmelden = new Knopf(350, 74, 200, 27, "Passagier ohne Tickets anmelden");
        hatKnopfPassagierAnmelden.setzeBearbeiterGeklickt("hatKnopfPassagierAnmeldenGeklickt");
        hatKnopfPassagierAnmeldenTicket = new Knopf(350, 124, 200, 27, "Passagier mit Tickets anmelden");
        hatKnopfPassagierAnmeldenTicket.setzeBearbeiterGeklickt("hatKnopfPassagierAnmeldenTicketGeklickt");
        hatKnopfPassagierAbfertigen = new Knopf(350, 179, 150, 27, "Passagier abfertigen");
        hatKnopfPassagierAbfertigen.setzeBearbeiterGeklickt("hatKnopfPassagierAbfertigenGeklickt");
        hatKnopfAusgabeTickets = new Knopf(350, 214, 150, 27, "Ausgabe der Tickets");
        hatKnopfAusgabeTickets.setzeBearbeiterGeklickt("hatKnopfAusgabeTicketsGeklickt");

        // Instanziierung des Wartebereichs
        hatSchlange = new Schlange2();
        hatTicketausgabe = new Schlange2();
        
        hatDatensicherung = new SaveFile("data.txt");
        String daten = hatDatensicherung.read();
        
        /*
         * Falls Daten vorhanden sind,
         * werden sie ausgelesen und den Schlagen hinzugefügt
         */
        if (daten != null) {
            // Auslesen der Schlangen (hatSchlange hatTicketausgabe)
            String[] schlangen = daten.split(" ");
            
            // Aufteilung der Passagiere der Schlangen (Passagier;Passagier;Passagier;...)
            String[] tmpSchlange = schlangen[0].split(";");
             // Versuch die zweite Schlange auszulesen (Falls vorhanden)
            String[] tmpTicketausgabe = new String[]{};
            if (schlangen.length > 1) { tmpTicketausgabe = schlangen[1].split(";"); }
            
            // Einfügen der Passagiere in die Schlangen
            for (int i = 0; i < tmpSchlange.length; i++) {
                // Auslesen der Passagierinformationen (Name-Fluggesellschaft)
                String[] passagier = tmpSchlange[i].split("-");
                Passagier tmpPassagier = new Passagier(passagier[0],passagier[1]);
                
                // Prüfen, ob Passagier ein Ticket besitzt oder nicht
                for (int j = 0; j < tmpTicketausgabe.length; j++) {
                    if (tmpSchlange[i].equals(tmpTicketausgabe[j])) {
                        hatTicketausgabe.haengeAn(tmpPassagier);
                        
                        tmpTicketausgabe[j] = null; // Passagier aus Schlamge entfernen
                        break;
                    }
                }
                
                hatSchlange.haengeAn(tmpPassagier);
            }
        }
        
        this.zeigeWartebereich();
        this.zeigeTicketschalter();
    }

    /**
     * Vorher: Ereignis GeklicktvonhatKnopfAnwendungBeenden fand statt.
     * Nachher: (schreiben Sie, was in dieser Methode ausgefuehrt wird)
     */
    public void hatKnopfAnwendungBeendenGeklickt()
    {
        /*
         * Speicherung der "wartenden" Passagiere
         * bzw. Speicherung der Schlangen
         */
        if (!hatSchlange.istLeer()) {
            String data = "";
            // Durchlauf jeder einzelnen Schlange
            for (Schlange2 schlange : new Schlange2[]{hatSchlange,hatTicketausgabe}) {
                // Jeden Passagier der Schlange durchlaufen
                while (!schlange.istLeer()) {
                    // Passagier bekommen und in String umwandeln ("Name-Fluggesellschaft;")
                    Passagier tmpPassagier = (Passagier) schlange.erstes();
                    data += (String.join("-", tmpPassagier.getName(), tmpPassagier.getFluggesellschaft()) + ";");
                    
                    schlange.entferneErstes(); // Passagier entfernen
                }
                
                // Letztes Trennzeichen entfernen und Leerzeichen als Schlangentrenner einfügen
                data = data.substring(0, data.length() - 1);
                data += " ";
            }
            
            hatDatensicherung.write(data); // Daten speichern
        }
        else { hatDatensicherung.deleteFile(); }
        
        System.exit(0); 
    }

    /**
     * Vorher: Ereignis GeklicktvonhatKnopfPassagierAnmelden fand statt.
     * Nachher: (schreiben Sie, was in dieser Methode ausgefuehrt wird)
     */
    public void hatKnopfPassagierAnmeldenGeklickt()
    {
        // Eingabe entnehmen und Textfelder leeren
        String lName = hatTextfeld1.inhaltAlsText();
        String lFluggesellschaft = hatTextfeld2.inhaltAlsText(); 
        
        hatTextfeld1.loescheAlles();
        hatTextfeld2.loescheAlles();
        
        // Prüfen, ob die Eingabe gültig ist
        if (this.pruefeEingabe(lName, lFluggesellschaft)) {
            // Passagier erstellen und den beiden Schlangen anfügen
            Passagier tmpPassagier = new Passagier(lName, lFluggesellschaft);
            
            hatTicketausgabe.haengeAn(tmpPassagier);
            hatSchlange.haengeAn(tmpPassagier);
            
            this.zeigeWartebereich();
            this.zeigeTicketschalter();
        }
    }
    
    /**
     * Vorher: Ereignis GeklicktvonhatKnopfPassagierAbfertigenTicket fand statt.
     * Nachher: (schreiben Sie, was in dieser Methode ausgefuehrt wird)
     */
    public void hatKnopfPassagierAnmeldenTicketGeklickt() 
    {
        // Eingabe entnehmen und Textfelder leeren
        String lName = hatTextfeld1.inhaltAlsText();
        String lFluggesellschaft = hatTextfeld2.inhaltAlsText(); 
        
        hatTextfeld1.loescheAlles();
        hatTextfeld2.loescheAlles();

        // Prüfen, ob Eingabe gültig ist
        if (this.pruefeEingabe(lName, lFluggesellschaft)) {
            // Neuen Passagier dem Wartbereich hinzufügen
            hatSchlange.haengeAn(new Passagier(lName, lFluggesellschaft));
            this.zeigeWartebereich();
        }
    }

    /**
     * Vorher: Ereignis GeklicktvonhatKnopfPassagierAbfertigen fand statt.
     * Nachher: (schreiben Sie, was in dieser Methode ausgefuehrt wird)
     */
    public void hatKnopfPassagierAbfertigenGeklickt()
    {
        // Prüfen, ob Passagier bereits ein Ticket erhalten hat
        if (hatTicketausgabe.istLeer() || !(hatTicketausgabe.contains(hatSchlange.erstes()))) {
            // Passagier aus Schlange entfernen
            hatSchlange.entferneErstes();
            this.zeigeWartebereich();
        }
        // Passagier hat noch kein Ticket erhalten
        else {
            // Fehlermeldung
            hatEtikettWartebereich.setzeInhalt("Dieser Passagier hat noch kein Ticket erhalten!");
            hatEtikettTicketschalter.setzeInhalt("");
            
            try { Thread.sleep(3500); }
            catch (InterruptedException ie) {}
            
            this.zeigeWartebereich();
            this.zeigeTicketschalter();
        }
    }
    
    /**
     * Vorher: Ereignis GeklicktvonhatKnopfPassagierAbfertigen fand statt.
     * Nachher: (schreiben Sie, was in dieser Methode ausgefuehrt wird)
     */
    public void hatKnopfAusgabeTicketsGeklickt() 
    {
        // Passagier aus Ticketschalter entfernen
        hatTicketausgabe.entferneErstes();
        this.zeigeTicketschalter();
    }
    
    private void zeigeWartebereich() {
        if (hatSchlange.erstes() != null) {
            Passagier tmpPassagier = (Passagier) hatSchlange.erstes();
            hatEtikettWartebereich.setzeInhalt(tmpPassagier.getName() + " " + tmpPassagier.getFluggesellschaft() + " (" + hatSchlange.length() + ")");
        }
        else { hatEtikettWartebereich.setzeInhalt("Der Wartebereich ist leer..."); }
    }
    
    private void zeigeTicketschalter() {
        if (hatTicketausgabe.erstes() != null) {
            Passagier tmpPassagier = (Passagier) hatTicketausgabe.erstes();
            hatEtikettTicketschalter.setzeInhalt(tmpPassagier.getName() + " " + tmpPassagier.getFluggesellschaft() + " (" + hatTicketausgabe.length() + ")");
        }
        else { hatEtikettTicketschalter.setzeInhalt("Der Ticketschalter ist leer..."); }
    }
    
    /** Prüfung, ob die Eingabe des Users gültig war
     *  @return true, wenn eine gültige Eingabe vorliegt
     */
    private boolean pruefeEingabe(String pName, String pFluggesellschaft) {
        if (pName.equals("") || pFluggesellschaft.equals("") || pName.matches(".*\\d.*")) {
            hatEtikettWartebereich.setzeInhalt("Bitte Namen und Fluggesellschaft überprüfen!");
            hatEtikettTicketschalter.setzeInhalt("");
            
            try { Thread.sleep(3500); }
            catch (InterruptedException ie) {}
            
            this.zeigeWartebereich();
            this.zeigeTicketschalter();
            
            return false;
        }
        else { return true; }
    }
    
    /** Prüfung, ob der Passagier aus dem Wartebereich entfernt werden kann
     * @param pPassagier abzufertigender Passagier
     * @return true, wenn der Passagier ein Ticket erhalen und abgefertigt werden kann
     */
    public boolean pruefeAbfertigung(Passagier pPassagier) {
        if (hatTicketausgabe.istLeer()) { return true; }
        
        Passagier tmpPassagier = (Passagier) hatTicketausgabe.erstes();
        do {
            if (tmpPassagier == pPassagier) { return false; }
            tmpPassagier = tmpPassagier.getNachfolger();
        } while (tmpPassagier != null && tmpPassagier.getNachfolger() != null);
        
        return true;
    }
    
    public static void main(String[] args) {
        new Flughafensimulation2();
    }
}
