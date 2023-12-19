/***
 * Class to test BST and Heap
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: April 13 2023
 * Last Date Modified: April 13 2023
 */
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/***
 * QUESTION 5E.)
 * the heap has the following complexities for contains, add, and remove respectively
 * O(n), O(logn), O(logn)
 * the BST has the following complexities likewise
 * O(n), O(logn) to O(n), O(logn) to O(n)
 * 
 * ADD method analysis:     The average for the BST is usually higher than the average for the heap. This is because
 * the heap is always balanced it makes adding more efficient
 * 
 * Contains method analysis:    The average for the BST is dramatically lower than the average for the heap with the 
 * contains method. Because the contains method is a for loop or linear search this makes it very inefficient for
 * large data sets like the one in this example. The order of the BST makes it more efficient for finding contains instances
 * 
 * Remove method analysis:      The average for both data structures are simular however, the BST's method tends to be
 * slightly less efficient (usually 1-3 extra iterations to achieve the same out come as the heap). Because the
 * BST is an ordered data structure, opposed to heap, removing an element in the BST requires making sure it is also
 * still ordered after the removal, where the Heap does not have the worry about this.     
 * 
 * 
 * Conclusion, if order matters use the BST. If you need add and remove methods with O(logn) and order doesn't matter
 * use the heap. 
 */



/***
 * QUESTION 5h.)
 *      Part f
 * The bst is not always balanced because it is order. Therefore the height is greater than the minimum possibl
 * height for this data set which is 9. The heap however is always balanced by its nature. Consquentially its height 
 * is only 9.
 *      Part g (sorted)
 * Because the bst is sorted throughout it has essentially become a linkedList with every node only having 1 child
 * It is should clearly not balanced then in this case which is isn't. We get false. Moving on to the heap, because 
 * it always must be balanced its height is 9 as expected. 
 */
public class Test{
    public static void main(String[]args){
        BST<String> animalBST = new BST<>();
        Heap<String> animalHeap = new Heap<>();
        ArrayList<String> animalAL = new ArrayList<>();
        System.out.println("TESTING add methods:");
        readAnimals(animalBST, animalHeap, animalAL, "animals.txt");
        System.out.println("TESTING contain methods:");
        testContains(animalBST, animalHeap, animalAL);
        System.out.println("TESTING remove methods:");
        testRemove(animalBST, animalHeap, animalAL);


        System.out.println("BST height: " + animalBST.height());
        System.out.println("BST is balanced? " + animalBST.isBalanced());

        System.out.println("Heap height: " + animalHeap.height());
        System.out.println("Heap is balanced? " + animalHeap.isBalanced());

        animalBST.clear();
        animalHeap.clear();
        java.util.Collections.sort(animalAL);
        for(String animal : animalAL){
            animalBST.add(animal);
            animalHeap.add(animal);
        }
        System.out.println("\nSORTED STRUCTURES:");
        System.out.println("BST height: " + animalBST.height());
        System.out.println("BST is balanced? " + animalBST.isBalanced());

        System.out.println("Heap height: " + animalHeap.height());
        System.out.println("Heap is balanced? " + animalHeap.isBalanced());
    }

    /***
     * Method to test the remove function of various data structures
     * @param   bst is a binary search tree
     * @param   heap is a heap
     * @param   al is an arraylist
     */
    public static void testRemove(BST<String> bst, Heap<String> heap, ArrayList<String> al){
        int totalBST = 0, totalHeap = 0;
            System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal", "BST(iters)", "Heap(iters)");
        for(int i = 0; i < 20; i++){
            int randomIndex = (int)(Math.random() * bst.size());
            String animal = al.get(randomIndex);
            bst.remove(animal);
            heap.remove();
            totalBST += BST.iterations;
            totalHeap += Heap.iterations;
            System.out.printf("%-30s\t%-15d\t%-15d\n", animal, BST.iterations, Heap.iterations);
        }
        System.out.printf("%-30s\t%-15d\t%-15d\n\n", "Average", totalBST/20, totalHeap/20);
    }
    /***
     * Method to test the contains method for various data structures
     * @param   bst is a binary search tree
     * @param   heap is a heap
     * @param   al is an arraylist
     */
    public static void testContains(BST<String> bst, Heap<String> heap, ArrayList<String> al){
        int totalBST = 0, totalHeap = 0;
            System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal", "BST(iters)", "Heap(iters)");
        for(int i = 0; i < 20; i++){
            int randomIndex = (int)(Math.random() * bst.size());
            String animal = al.get(randomIndex);
            bst.contains(animal);
            heap.contains(animal);
            totalBST += BST.iterations;
            totalHeap += Heap.iterations;
            System.out.printf("%-30s\t%-15d\t%-15d\n", animal, BST.iterations, Heap.iterations);
        }
        System.out.printf("%-30s\t%-15d\t%-15d\n\n", "Average", totalBST/20, totalHeap/20);
    }
    /***
     * Method to read the values in a file and initalize various data strutures with that data
     * @param   bst is a binary search tree
     * @param   heap is a heap
     * @param   al is an arraylist
     */
    public static void readAnimals(BST<String> bst,
                                   Heap<String> heap,
                                   ArrayList<String> al,
                                   String filename){         
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            int index = 0;
            int totalBST = 0, totalHeap = 0;
            System.out.printf("%-30s\t%-15s\t%-15s\n", "Animal", "BST(iters)", "Heap(iters)");
            while(read.hasNextLine()){
                String animal = read.nextLine();
                bst.add(animal);
                heap.add(animal);
                totalBST += BST.iterations;
                totalHeap += Heap.iterations;
                al.add(animal);
                if(index % 24 == 0){
                    System.out.printf("%-30s\t%-15d\t%-15d\n", animal, BST.iterations, Heap.iterations);
                }
                index++;
            }
            System.out.printf("%-30s\t%-15d\t%-15d\n\n", "Average", totalBST/al.size(), totalHeap/al.size());
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}