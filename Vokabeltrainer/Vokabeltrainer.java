/**
 * Die Klasse Vokabeltrainer wurde automatisch erstellt: 
 * 
 * @author Niclas Berger
 * @version 22.11.2021
 */

import sum.komponenten.*;
import sum.werkzeuge.*;
import sum.ereignis.*;
import java.util.Random;

public class Vokabeltrainer extends EBAnwendung
{
    // Objekte
    private Etikett hatEtikettTrainingAnzeige;
    private Etikett hatEtikettVokabelHinzufuegenOderEntfernen;
    private Textfeld hatTextfeldEnglisch;
    private Etikett hatEtikettEnglisch;
    private Etikett hatEtikettDeutsch;
    private Textfeld hatTextfeldDeutsch;
    private Knopf hatKnopfVokabelHinzufuegen;
    private Knopf hatKnopfVokabelEntfernen;
    private Zeilenbereich hatZeilenbereich1;
    private Etikett hatEtikettEnglischSuche;
    private Knopf hatKnopfVokabelnAnzeigen;
    private Knopf hatKnopfVokabelSuchen;
    private Etikett hatEtikettDeutschSuche;
    private Textfeld hatTextfeldEnglischSuche;
    private Textfeld hatTextfeldDeutschSuche;
    private Etikett hatEtikettVokabelnSortieren;
    private Knopf hatKnopfSelectionsort;
    private Knopf hatKnopfBubblesort;
    private Knopf hatKnopfInsertionsort;
    private Knopf hatKnopfMergesort;
    private Knopf hatKnopfZufall;
    private Knopf hatKnopfVokabelnTrainieren;
    private Etikett hatEtikettVokabel;
    private Etikett hatEtikettVokabelEnglisch;
    private Etikett hatEtikettUebersetzung;
    private Textfeld hatTextfeldVokabeltraining;
    private Etikett hatEtikettRueckgabe;
    private Knopf hatKnopfBeenden;

    // Attribute
    private SaveFile datenverwaltung;
    private Random rand;
    private List<Vokabel> vokabelListe;
    private List<String> loesung; // Speichert, alle Übersetzungen für das Vokabeltraining
    boolean training; // Gibt an, ob gerade Vokabeln trainiert werden
    String inhalt;

    /**
     * Konstruktor
     */
    public Vokabeltrainer()
    {
        //Initialisierung der Oberklasse
        super(495, 515);

        hatEtikettTrainingAnzeige = new Etikett(0, 10, 495, 24, "");
        // Ausrichtung
        hatEtikettTrainingAnzeige.setzeAusrichtung(Ausrichtung.MITTE);
        hatEtikettVokabelHinzufuegenOderEntfernen = new Etikett(40, 40, 180, 25, "Vokabel hinzufügen oder entfernen");
        // Ausrichtung
        hatEtikettVokabelHinzufuegenOderEntfernen.setzeAusrichtung(Ausrichtung.LINKS);
        hatTextfeldEnglisch = new Textfeld(110, 74, 140, 27, "");
        // Ausrichtung
        hatTextfeldEnglisch.setzeAusrichtung(Ausrichtung.LINKS);
        hatEtikettEnglisch = new Etikett(55, 75, 50, 25, "Englisch:");
        // Ausrichtung
        hatEtikettEnglisch.setzeAusrichtung(Ausrichtung.RECHTS);
        hatEtikettDeutsch = new Etikett(55, 105, 50, 25, "Deutsch:");
        // Ausrichtung
        hatEtikettDeutsch.setzeAusrichtung(Ausrichtung.RECHTS);
        hatTextfeldDeutsch = new Textfeld(110, 104, 140, 27, "");
        // Ausrichtung
        hatTextfeldDeutsch.setzeAusrichtung(Ausrichtung.LINKS);
        hatKnopfVokabelHinzufuegen = new Knopf(270, 55, 130, 35, "Vokabel hinzufügen");
        hatKnopfVokabelHinzufuegen.setzeBearbeiterGeklickt("hatKnopfVokabelHinzufuegenGeklickt");
        hatKnopfVokabelEntfernen = new Knopf(270, 95, 130, 35, "Vokabel entfernen");
        hatKnopfVokabelEntfernen.setzeBearbeiterGeklickt("hatKnopfVokabelEntfernenGeklickt");
        hatZeilenbereich1 = new Zeilenbereich(55, 190, 180, 170, "");
        hatZeilenbereich1.setzeBearbeiterFokusErhalten("zeilenbereichFokusErhalten");
        hatZeilenbereich1.setzeBearbeiterFokusVerloren("zeilenbereichFokusVerloren");
        hatEtikettEnglischSuche = new Etikett(260, 195, 50, 25, "Englisch:");
        // Ausrichtung
        hatEtikettEnglischSuche.setzeAusrichtung(Ausrichtung.RECHTS);
        hatKnopfVokabelnAnzeigen = new Knopf(40, 160, 130, 25, "Vokabeln anzeigen");
        hatKnopfVokabelnAnzeigen.setzeBearbeiterGeklickt("hatKnopfVokabelnAnzeigenGeklickt");
        hatKnopfVokabelSuchen = new Knopf(255, 165, 110, 25, "Vokabel suchen");
        hatKnopfVokabelSuchen.setzeBearbeiterGeklickt("hatKnopfVokabelSuchenGeklickt");
        hatEtikettDeutschSuche = new Etikett(260, 225, 50, 25, "Deutsch:");
        // Ausrichtung
        hatEtikettDeutschSuche.setzeAusrichtung(Ausrichtung.RECHTS);
        hatTextfeldEnglischSuche = new Textfeld(315, 194, 140, 27, "");
        // Ausrichtung
        hatTextfeldEnglischSuche.setzeAusrichtung(Ausrichtung.LINKS);
        hatTextfeldDeutschSuche = new Textfeld(315, 224, 140, 27, "");
        // Ausrichtung
        hatTextfeldDeutschSuche.setzeAusrichtung(Ausrichtung.LINKS);
        hatEtikettVokabelnSortieren = new Etikett(255, 265, 100, 25, "Vokabeln sortieren");
        // Ausrichtung
        hatEtikettVokabelnSortieren.setzeAusrichtung(Ausrichtung.LINKS);
        hatKnopfSelectionsort = new Knopf(270, 294, 120, 25, "Selectionsort");
        hatKnopfSelectionsort.setzeBearbeiterGeklickt("hatKnopfSelectionsortGeklickt");
        hatKnopfBubblesort = new Knopf(270, 321, 120, 25, "Bubblesort");
        hatKnopfBubblesort.setzeBearbeiterGeklickt("hatKnopfBubblesortGeklickt");
        hatKnopfInsertionsort = new Knopf(270, 348, 120, 25, "Insertionsort");
        hatKnopfInsertionsort.setzeBearbeiterGeklickt("hatKnopfInsertionsortGeklickt");
        hatKnopfMergesort = new Knopf(270, 375, 120, 25, "Mergesort");
        hatKnopfMergesort.setzeBearbeiterGeklickt("hatKnopfMergesortGeklickt");
        hatKnopfZufall = new Knopf(392, 294, 40, 106, "#");
        hatKnopfZufall.setzeBearbeiterGeklickt("hatKnopfZufallGeklickt");
        hatKnopfVokabelnTrainieren = new Knopf(40, 380, 130, 25, "Vokabeln trainieren");
        hatKnopfVokabelnTrainieren.setzeBearbeiterGeklickt("hatKnopfVokabelnTrainierenGeklickt");
        hatEtikettVokabel = new Etikett(55, 410, 140, 25, "Vokabel:");
        // Ausrichtung
        hatEtikettVokabel.setzeAusrichtung(Ausrichtung.LINKS);
        hatEtikettVokabelEnglisch = new Etikett(55, 440, 140, 25, "(Vokabel)");
        // Ausrichtung
        hatEtikettVokabelEnglisch.setzeAusrichtung(Ausrichtung.MITTE);
        hatEtikettUebersetzung = new Etikett(200, 410, 100, 25, "Übersetzung:");
        // Ausrichtung
        hatEtikettUebersetzung.setzeAusrichtung(Ausrichtung.LINKS);
        hatTextfeldVokabeltraining = new Textfeld(200, 439, 140, 27, "");
        // Ausrichtung
        hatTextfeldVokabeltraining.setzeAusrichtung(Ausrichtung.LINKS);
        hatTextfeldVokabeltraining.setzeBearbeiterEingabeBestaetigt("hatTextfeldVokabeltrainingEingabeBestaetigt");
        hatEtikettRueckgabe = new Etikett(178, 380, 140, 25, "");
        // Ausrichtung
        hatEtikettRueckgabe.setzeAusrichtung(Ausrichtung.MITTE);
        hatKnopfBeenden = new Knopf(375, 435, 80, 40, "Beenden");
        hatKnopfBeenden.setzeBearbeiterGeklickt("hatKnopfBeendenGeklickt");

        // Attribute initialisieren
        datenverwaltung = new SaveFile("data.txt");
        rand = new Random();
        vokabelListe = new List<Vokabel>();
        training = false;
        loesung = new List<String>();
        inhalt = "";

        // Wenn möglich, gespeicherte Daten ablesen
        String data = datenverwaltung.read();
        if (data != null) {
            String[] vokabeln = data.split(";");
            for (String vokabel : vokabeln) {
                String[] tmp = vokabel.split("-");
                
                List<String> deutsch = new List<String>();
                for (String vok : tmp[1].split(",")) {
                    deutsch.append(vok);
                }
                
                vokabelListe.append(new Vokabel(tmp[0],deutsch));
            }
        }
        
        this.fehler("Hinzufügen von mehreren Übersetzungen erfolgt durch das Trennen\nder einzelnen deutschen Übersetzungen durch ein Komma ',' !\nDer Knopf '#' vermischt die Vokabelliste.");
    }
    
    /**
     * Eine Vokabel wird zur Vokabelliste hinzugefügt, nachdem die Eingaben überprüft wurden
     * und wenn vorhanden mehrere Übersetzungen geteilt und unnltige Leerzeichen entfernt wurden.
     */
    public void hatKnopfVokabelHinzufuegenGeklickt()
    {
        // Wenn trainiert wird, darf die Liste nicht verändert oder ausgegeben werden
        if (training) return; 

        String englisch = hatTextfeldEnglisch.inhaltAlsText();
        String deutsch = hatTextfeldDeutsch.inhaltAlsText();

        hatTextfeldEnglisch.loescheAlles();
        hatTextfeldDeutsch.loescheAlles();

        if (this.pruefeEingabe(englisch, deutsch, true)) {
            deutsch.replace(", ", ","); // Entfernen der Leerzeichen nach dem Komma, zur korrekten Speicherung
            
            List<String> list = new List<String>();
            for (String vok : deutsch.split(",")) {
                list.append(vok);
            }
            
            vokabelListe.append(new Vokabel(englisch,list));
        }
        else { this.fehler("Bitte Eingaben überprüfen"); }
    }

    /**
     * Bei korrektem Input wird die zu entfernende Vokabel nach der Englischen Übersetzung
     * und/oder einer der Deutschen Übersetzungen gesucht, angezeigt und entfernt.
     */
    public void hatKnopfVokabelEntfernenGeklickt()
    {
        // Wenn trainiert wird, darf die Liste nicht verändert oder ausgegeben werden
        if (training) return; 

        String englisch = hatTextfeldEnglisch.inhaltAlsText();
        String deutsch = hatTextfeldDeutsch.inhaltAlsText();

        hatTextfeldEnglisch.loescheAlles();
        hatTextfeldDeutsch.loescheAlles();

        if (!this.pruefeEingabe(englisch,deutsch, false)) {
            this.fehler("Bitte Eingaben überprüfen");
            return;
        }

        if(this.linearSuchen(englisch,deutsch)) vokabelListe.remove();
    }

    /**
     * Zeigt alle Vokabeln der Vokabelliste leserlich im Zeilenbereich an.
     */
    public void hatKnopfVokabelnAnzeigenGeklickt()
    {
        // Wenn trainiert wird, darf die Liste nicht verändert oder ausgegeben werden
        if (training) return; 

        hatZeilenbereich1.loescheAlles();
        vokabelListe.toFirst();

        while (vokabelListe.hasAccess()) {
            Vokabel vokabel = vokabelListe.getContent();
            this.zeigeAn(vokabel.toString(), false);
            vokabelListe.next();
        }
    }
    
    /**
     * Sollte versucht werden den Zeilenbereich abzuändern, wird der Inhalt gespeichert.
     */
    public void zeilenbereichFokusErhalten() {
        inhalt = hatZeilenbereich1.inhaltAlsText();
    }
    
    /**
     * Wird der Zeilenbereich wieder verlassen, wird der ursprüungliche Inhalt wiederhergestellt.
     */
    public void zeilenbereichFokusVerloren() {
        hatZeilenbereich1.setzeInhalt(inhalt);
    }

    /**
     * Bei korrektem Input wird die Vokabelliste nach der Englischen Vokabel
     * und/oder einer der deutschen Übersetzungen gesucht.
     */
    public void hatKnopfVokabelSuchenGeklickt()
    {
        // Wenn trainiert wird, darf die Liste nicht verändert oder ausgegeben werden
        if (training) return; 

        String englisch = hatTextfeldEnglischSuche.inhaltAlsText();
        String deutsch = hatTextfeldDeutschSuche.inhaltAlsText();

        hatTextfeldEnglischSuche.loescheAlles();
        hatTextfeldDeutschSuche.loescheAlles();

        if (!this.pruefeEingabe(englisch,deutsch, false)) {
            this.fehler("Bitte Eingaben überprüfen!");
            return;
        }

        this.linearSuchen(englisch,deutsch);
    }

    /**
     * Sortieren der Vokabelliste nach dem Sortieralgorithmus "Selectionsort".
     */
    public void hatKnopfSelectionsortGeklickt()
    {
        // Wenn trainiert wird, darf die Liste nicht verändert oder ausgegeben werden
        if (training) return; 

        Vokabel tmp;
        int index;

        vokabelListe.toFirst();
        for (int i = 0; vokabelListe.hasAccess(); i++) {
            tmp = vokabelListe.getContent();
            index = i;

            // Minimum suchen
            vokabelListe.next();
            for (int k = i+1; vokabelListe.hasAccess(); k++) {
                if (vokabelListe.getContent().getEnglisch().compareTo(tmp.getEnglisch()) < 0) { 
                    tmp = vokabelListe.getContent();
                    index = k;
                }
                vokabelListe.next();
            }

            // Minimum einsetzen & loeschen
            vokabelListe.toFirst();
            for (int j = 0; j < i; j++) { vokabelListe.next(); }
            vokabelListe.insert(tmp);

            for (int k = 0; k < index-i; k++) { vokabelListe.next(); }
            vokabelListe.remove();

            // Zeiger neu positionieren
            vokabelListe.toFirst();
            for (int j = 0; j <= i; j++) { vokabelListe.next(); }
        }

        this.hatKnopfVokabelnAnzeigenGeklickt();
    }

    /**
     * Sortieren der Vokabelliste nach dem Sortieralgorithmus "Bubblesort".
     */
    public void hatKnopfBubblesortGeklickt()
    {
        // Wenn trainiert wird, darf die Liste nicht verändert oder ausgegeben werden
        if (training) return; 

        Vokabel prev,last,tmp;

        vokabelListe.toFirst();
        last = null;

        while (vokabelListe.getContent() != last) {
            prev = vokabelListe.getContent();
            vokabelListe.next();

            while (vokabelListe.getContent() != last) {
                if (prev.getEnglisch().compareTo(vokabelListe.getContent().getEnglisch()) > 0) {
                    tmp = vokabelListe.getContent();
                    vokabelListe.setContent(prev);

                    vokabelListe.toFirst();
                    while (vokabelListe.getContent() != prev) { vokabelListe.next(); }

                    vokabelListe.setContent(tmp);
                    vokabelListe.next();
                }

                prev = vokabelListe.getContent();
                vokabelListe.next();
            }

            last = prev;
            vokabelListe.toFirst();
        }

        this.hatKnopfVokabelnAnzeigenGeklickt();
    }

    /**
     * Sortieren der Vokabelliste nach dem Sortieralgorithmus "Insertionsort".
     */
    public void hatKnopfInsertionsortGeklickt()
    {
        // Wenn trainiert wird, darf die Liste nicht verändert oder ausgegeben werden
        if (training) return;
        
        List<Vokabel> lListSortiert = new List<>();  
        while(!vokabelListe.isEmpty()){
            lListSortiert.toFirst();
            vokabelListe.toFirst();
            while(lListSortiert.hasAccess() && lListSortiert.getContent().getEnglisch().compareTo(vokabelListe.getContent().getEnglisch()) < 0){
                lListSortiert.next();
            }
            if (lListSortiert.hasAccess())
            {
                lListSortiert.insert(vokabelListe.getContent());
            }
            else
            {
                lListSortiert.append(vokabelListe.getContent());
            }
            vokabelListe.remove();
        }
        vokabelListe = lListSortiert;

        this.hatKnopfVokabelnAnzeigenGeklickt();
    }
    
    /**
     * Sortieren der Vokabelliste nach dem Sortieralgorithmus "Selectionsort".
     */
    public void hatKnopfMergesortGeklickt() {
        // Wenn trainiert wird, darf die Liste nicht verändert oder ausgegeben werden
        if (training) return;
        
        Mergesort.mergesort(vokabelListe);
        this.hatKnopfVokabelnAnzeigenGeklickt();
    }
    
    /**
     * Die Vokabeln werden in einer zufälligen Reigenfolge angeordnet
     */
    public void hatKnopfZufallGeklickt() 
    {
        // Wenn trainiert wird, darf die Liste nicht verändert oder ausgegeben werden
        if (training) return;
        
        int length = 0;
        vokabelListe.toFirst();
        
        while (vokabelListe.hasAccess()) {
            length++;
            vokabelListe.next();
        }
        
        List<Vokabel> newList = new List<Vokabel>();
        while (!vokabelListe.isEmpty()) {
            vokabelListe.toFirst();
            for (int i = 0; i < rand.nextInt(length); i++) {
                vokabelListe.next();
            }
            
            newList.append(vokabelListe.getContent());
            vokabelListe.remove();
            length--;
        }
        
        vokabelListe = newList;
        this.hatKnopfVokabelnAnzeigenGeklickt();
    }

    /**
     * Das Vokabeltraining wird gestartet!
     */
    public void hatKnopfVokabelnTrainierenGeklickt()
    {
        // Wenn trainiert wird, darf die Liste nicht verändert oder ausgegeben werden
        if (training) return;
        this.vokabelTraining();
    }

    /**
     * Die Eingabe der Übersetzung wird überprüft und bei richtiger Eingabe wird
     * die nächste Vokabel angezeigt.
     */
    public void hatTextfeldVokabeltrainingEingabeBestaetigt()
    {
        if (!training) return;

        String input = hatTextfeldVokabeltraining.inhaltAlsText();
        hatTextfeldVokabeltraining.loescheAlles();

        if (this.pruefeEingabe(" ",input, true)) {
            loesung.toFirst();
            boolean richtig = false;
            
            while (loesung.hasAccess()) {
                if (input.equals(loesung.getContent())) {
                    richtig = true;
                    break;
                }
                loesung.next();
            }
            
            if (richtig) {
                this.vokabelTraining();
            }
            else { this.fehler("Das ist leider falsch!"); }
        }
        else { this.fehler("Bitte Eingaben überprüfen!"); }
    }

    /**
     * Alle Vokabeln werden in der Datei "data.txt" gespeichert
     * und das Programm beendet.
     */
    public void hatKnopfBeendenGeklickt()
    {
        String data = "";
        vokabelListe.toFirst();

        while (vokabelListe.hasAccess()) {
            Vokabel vokabel = vokabelListe.getContent();
            data += vokabel.toDataString() + ";";
            vokabelListe.remove();
        }

        if (data != "") {
            data.substring(0, data.length()-1);
            datenverwaltung.write(data);
        }

        System.exit(1);
    }
    
    /**
     * Start des Vokabeltrainings und "Blockierung" aller anderen Funktionen.
     * Auswahl der nächsten Vokabel & Beendung des Trainings.
     */
    private void vokabelTraining() {
        if (!training) {
            training = true;
            hatEtikettTrainingAnzeige.setzeInhalt("!!! Sie befinden sich im Vokabeltraining !!!");
            hatZeilenbereich1.loescheAlles();
            vokabelListe.toFirst();
        }
        else { vokabelListe.next(); }

        if (!vokabelListe.hasAccess()) {
            hatEtikettTrainingAnzeige.setzeInhalt("");
            hatEtikettVokabelEnglisch.setzeInhalt("(Vokabel)");
            training = false;
            this.fehler("Sie haben das Vokabeltraining erfolgreich absolviert!");
            return;
        }

        Vokabel current = vokabelListe.getContent();
        hatEtikettVokabelEnglisch.setzeInhalt(current.getEnglisch());
        loesung = current.getDeutsch();
    }
    
    /**
     * Suchen einer Vokabel in der Vokabelliste.
     * @return Vokabel wurde gefunden
     */
    private boolean linearSuchen(String englisch, String deutsch) {
        boolean gefunden = false;
        vokabelListe.toFirst();

        while (vokabelListe.hasAccess()){
            Vokabel currentVokabel = vokabelListe.getContent();
            
            boolean match = false;
            if (!deutsch.equals("")) {
                List<String> lDeutsch = currentVokabel.getDeutsch();
                while (lDeutsch.hasAccess()) {
                    if (deutsch.equals(lDeutsch.getContent())) {
                        match = true;
                        break;
                    }
                    lDeutsch.next();
                }
            }
            else { match = true; }
            
            if ((englisch.equals("") || englisch.equals(currentVokabel.getEnglisch())) && match) {
                this.zeigeAn(vokabelListe.getContent().toString(), true);
                gefunden = true;
                break;
            }
            vokabelListe.next();
        }

        if (!gefunden) { this.fehler("Die eingegebene Vokabel wurde nicht gefunden"); }
        return gefunden;
    }
    
    /**
     * Überprüfung einer Eingabe.
     * @param pEnglisch Englische Vokabel als String
     * @param pDeutsch Deutsche Vokabel(n) als String
     * @param empty Soll auf leere Eingaben überprüft werden?
     * @return Eingabe gültig
     */
    private boolean pruefeEingabe(String pEnglisch, String pDeutsch, boolean empty) {
        if ((empty && pEnglisch.equals("")) || pEnglisch.matches(".*\\d.*") || pEnglisch.contains("-") || pEnglisch.contains(";") ||
        (empty && pDeutsch.equals("")) || pDeutsch.matches(".*\\d.*") || pDeutsch.contains("-") || pDeutsch.contains(";")) 
        {
            return false;
        }
        return true;
    }
    
    /**
     * Ausgabe einer Nachricht in einem Fehlerfenster.
     * @param message Anzuzeigende Nachricht
     */
    private void fehler(String message) {
        javax.swing.JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Anzeigen/Hinzufügen einer Nachricht im Zeilenbereich
     * @param message Anzuzeigende Nachricht
     * @param loeschen Restlichen Inhalt loeschen?
     */
    private void zeigeAn(String message, boolean loeschen) {
        if (loeschen) hatZeilenbereich1.loescheAlles();
        hatZeilenbereich1.fuegeEin(message, hatZeilenbereich1.anzahl());
    }
    
    public static void main(String[] args) {
        new Vokabeltrainer();
    }
}
