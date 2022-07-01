/**
 * <p>
 * Materialien zu den zentralen NRW-Abiturpruefungen im Fach Informatik ab 2017
 * </p>
 * <p>
 * Generische Klasse Queue<ContentType>
 * </p>
 * <p>
 * Objekte der generischen Klasse Queue (Warteschlange) verwalten beliebige
 * Objekte vom Typ ContentType nach dem First-In-First-Out-Prinzip, d.h., das
 * zuerst abgelegte Objekt wird als erstes wieder entnommen. Alle Methoden haben
 * eine konstante Laufzeit, unabhaengig von der Anzahl der verwalteten Objekte.
 * </p>
 * 
 * @author Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule
 * @version Generisch_02 2014-02-21
 */
public class Stack<ContentType> {

    /* --------- Anfang der privaten inneren Klasse -------------- */

    protected class StackNode {

        private ContentType content = null;
        private StackNode nextNode = null;

        /**
         * Ein neues Objekt vom Typ StackNode<ContentType> wird erschaffen. 
         * Der Inhalt wird per Parameter gesetzt. Der Verweis ist leer.
         * 
         * @param pContent das Inhaltselement des Knotens vom Typ ContentType
         */
        public StackNode(ContentType pContent) {
            content = pContent;
            nextNode = null;
        }

        /**
         * Der Verweis wird auf das Objekt, das als Parameter uebergeben wird,
         * gesetzt.
         * 
         * @param pNext der Nachfolger des Knotens
         */
        public void setNext(StackNode pNext) {
            nextNode = pNext;
        }

        /**
         * Liefert das naechste Element des aktuellen Knotens.
         * 
         * @return das Objekt vom Typ QueueNode, auf das der aktuelle Verweis zeigt
         */
        public StackNode getNext() {
            return nextNode;
        }

        /**
         * Liefert das Inhaltsobjekt des Knotens vom Typ ContentType.
         * 
         * @return das Inhaltsobjekt des Knotens
         */
        public ContentType getContent() {
            return content;
        }

    }

    /* ----------- Ende der privaten inneren Klasse -------------- */

    protected StackNode top;

    /**
     * Eine leerer Stack wird erzeugt. 
     * Objekte, die in diesem Stack verwaltet werden, muessen vom Typ
     * ContentType sein.
     */
    public Stack() {
        top = null;
    }

    /**
     * Die Anfrage liefert den Wert true, wenn der Stack keine Objekte enthaelt, 
     * sonst liefert sie den Wert false.
     * 
     * @return true, falls die Schlange leer ist, sonst false
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Das Objekt pContentType wird an den Stack angehaengt. 
     * Falls pContentType gleich null ist, bleibt die Schlange unveraendert.
     * 
     * @param pContent
     *            das anzuhaengende Objekt vom Typ ContentType
     */
    public void push(ContentType pContent) {
        if (pContent != null) {
            StackNode newNode = new StackNode(pContent);
            newNode.setNext(top);
            top = newNode;
        }
    }

    /**
     * Das erste Objekt wird aus dem Stack entfernt. 
     * Falls der Stack leer ist, wird sie nicht veraendert.
     */
    public void pop() {
        if (!this.isEmpty()) { top = top.getNext(); }
    }

    /**
     * Die Anfrage liefert das erste Objekt des Stacks. 
     * Der Stack bleibt unveraendert. 
     * Falls der Stack leer ist, wird null zurueckgegeben.
     *
     * @return das erste Objekt des Stack vom Typ ContentType oder null,
     *         falls der Stack leer ist
     */
    public ContentType peek() {
        if (this.isEmpty()) {
            return null;
        }
        return top.getContent();
    }
}
