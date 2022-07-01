import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.util.List;
/**
 * Klasse, zum Lesen und Schreiben von Daten in .txt Datein
 * @author Niclas Berger
 */
public class SaveFile
{
    // Zu verwaltende Datei
    private File zDatei;
    
    /**
     * Erstellt/Lokalisiert ein Savefile an dem angegebenen Path
     */
    public SaveFile(String pPath)
    {
        // Instanziierung der Datei anhand des Path
        zDatei = new File(pPath);
    }
    
    /**
     * Liest die Daten der Datei aus, sofern sie existiert
     * @return Inhalt der Datei als String & null falls nicht vorhanden
     */
    public String read() {
        if (!zDatei.exists()) { return null; }
        
        String[] content = null;
        try {
            content = Files.readAllLines(zDatei.toPath()).toArray(new String[]{});
        } catch (IOException e) { e.printStackTrace(); }
        
        return content[0];
    }
    
    /**
     * Schreibt Daten in die Datei
     * @param pContent Text, der gespeichert werden soll
     */
    public void write(String pContent) {
        try {
            Files.write(zDatei.toPath(), pContent.getBytes());
        } catch (IOException e) { e.printStackTrace(); }
    }
    
    /**
     * Löscht die Datei, wenn sie existiert
     */
    public void deleteFile() {
        try {
            Files.deleteIfExists(zDatei.toPath());
        } catch (IOException e) { e.printStackTrace(); }
    }
}
