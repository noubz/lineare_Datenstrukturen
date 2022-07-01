
/**
 * @author Niclas Berger
 */
public class Suchalgorithmus
{
    private int[] sortiertesArray;
    
    /**
     * Constructor for objects of class Suchalgorithmus
     */
    public Suchalgorithmus()
    {
        sortiertesArray = new int[] {0,1,2,4,5,6,7,8,9,10};
        
        schreibe();
        System.out.println("7: " + binarySort(sortiertesArray, 0, sortiertesArray.length-1, 7));
        System.out.println("3: " + binarySort(sortiertesArray, 0, sortiertesArray.length-1, 3));
    }
    
    public void schreibe() {
        for (int i = 0; i < sortiertesArray.length; i++) {
            System.out.print(sortiertesArray[i] + ", ");
        }
        System.out.println();
    }

    public boolean binarySort(int[] pArray, int first, int last, int search) {
        if (first > last) return false;
        
        int mid = (first + last) / 2;
        
        if (pArray[mid] > search)
            return this.binarySort(pArray, first, mid-1, search);
        if (pArray[mid] < search) 
            return this.binarySort(pArray, mid+1, last, search);
        
        return true;
    }
}
