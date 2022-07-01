public class Mergesort
{
    public static void mergesort(List<Vokabel> list) {
        list.toFirst();
        list.next();
        if (!list.hasAccess())
            return;
        
        list.toFirst();
        int i = 0;
        while (list.hasAccess()) {
            list.next();
            i++;
        }
        int mitte = i / 2;
        
        list.toFirst();
        List<Vokabel> links = new List<Vokabel>();
        List<Vokabel> rechts = new List<Vokabel>();
        
        for (int j = 0; j < mitte; j++) {
            links.append(list.getContent());
            list.remove();
        }
        while (list.hasAccess()) {
            rechts.append(list.getContent());
            list.remove();
        }
        
        mergesort(links);
        mergesort(rechts);
        
        list.concat(merge(links, rechts));
    }
    
    private static List<Vokabel> merge(List<Vokabel> links, List<Vokabel> rechts) {
        List<Vokabel> merged = new List<Vokabel>();
        
        links.toFirst();
        rechts.toFirst();
        while (links.hasAccess() || rechts.hasAccess()) {
            if (links.hasAccess() && links.getContent().compareTo(rechts.getContent()) < 0) {
                merged.append(links.getContent());
                links.remove();
            }
            else if (rechts.hasAccess()){
                merged.append(rechts.getContent());
                rechts.remove();
            }
        }
        
        return merged;
    }
}