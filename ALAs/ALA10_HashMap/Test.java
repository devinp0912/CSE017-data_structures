/***
 * Class to model the class functionallity and benefits of various search algorithms of various data structures
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: April 20, 2023
 * Last Date Modified: April 24, 2023
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Test {
    public static void main(String[] args) {
        HashMap<String, String> hashWords = new HashMap<>(50000); //hashtable with capacity 65,536
        BST<String> bstWords = new BST<>();
        LinkedList<String> llWords = new LinkedList<>();
        ArrayList <HashMapEntry<String,String>> words = new ArrayList<>();
        readFile(words, "dictionary.txt");
        java.util.Collections.shuffle(words);
        for(HashMapEntry<String,String> entry : words){
            hashWords.put(entry.getKey(), entry.getValue()); //store word and its definition
            bstWords.add(entry.getKey()); //store the word only
            llWords.add(entry.getKey());
        }
        int totalHash = 0, totalBST = 0, totalLL = 0;
        System.out.printf("%-20s\t%-15s\t%-15s\t%-15s\n", "Word", "LL search", "BST search", "HashMap search");
        for(int i = 0; i < 1000; i++){
            int randomIndex = (int)(Math.random() * words.size());
            String word = words.get(randomIndex).getKey();
            hashWords.get(word);
            bstWords.contains(word);
            llWords.contains(word);
            totalHash += HashMap.iterations;
            totalBST += BST.iterations;
            totalLL += LinkedList.containsIter;
            if(i % 50 == 0){
                System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", word, LinkedList.containsIter, BST.iterations, HashMap.iterations);
            }
        }
        System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", "Average", totalLL/1000, totalBST/1000, totalHash/1000);

        System.out.println("\nMaximum number of collisions: " + hashWords.collisions());
    /*

    The linkedList is the least efficient data structure to use when searching your data. It requires the most iterations out of all of them
    by far -- requiring around 19,000 iterations everytime or greater. This is largly due to the fact that nodes can only be accepted sequentially

    The BST is much more efficient but is much more because of its organization it is easier to know which direction the requested data is in. The
    BST has 17 iterations per search which is dramatically better than the linkedList but not 

    Lastly the the Hash table is the most ideal data structure for holding large amounts of data that also need to be added, deleted, or
    search for very quickly - at O(1). This is due to its hash functioning and therefore makes it the best data structure for the purposes of
    this ALA. It on average has 1 iteration per search


    ////// THE max number of collisions means the longest possible linkedList at a given index in the HashMap has a length
    of 5, meaning that our worst case scenerio for search is O(5) = 0(1)... constant time. this is why we can consider this
    function in the hashtable to have Big O of 1 because here we have a data set with nearly 55,000 data members and we are
    able to find anyone at constant time. 
    */
    }
    /***
     * Method to read the items from the dictionary.txt file and initalze various data structures with its elements
     * @param   al holds an arraylist
     * @param   filename holds the name of the file
     * no return value
     */
    public static void readFile(ArrayList<HashMapEntry<String, String>> al, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] items = line.split("\\|");
                String word = items[0];
                String definition = items[1];
                HashMapEntry<String,String> pair = new HashMapEntry<>(word, definition);
                al.add(pair);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
