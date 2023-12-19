import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Test{
    public static void main(String[]args){
        TreeMap tm = new TreeMap(new StringComparator());
        HashMapSC sc = new HashMapSC(100000);
        HashMapLP lp = new HashMapLP(100000);
        //add everything to each data structure
        readFile(tm, sc, lp, "emails.txt");
        
        //search the three data structures for 100 email addresses in the file
        HashMapEntry[] list = new HashMapEntry[100];
        readFile(list, "mailingList.txt");
        //reset iterations back to 0;
        tm.iterations = 0;
        sc.iterations = 0;
        lp.iterations = 0;
        int tmAVERAGE = 0;
        int scAVERAGE = 0;
        int lpAVERAGE = 0;
        System.out.println("Testing get()");
        System.out.printf("%-20s\t%-15s\t%-15s\t%-15s\n", "Username", "TreeMap", "HashMapSC", "HashMapLP");
        for(int i = 0; i < 20; i++){
            int randomIndex = (int)(Math.random() * 100); //generates random number from 0-99;
            //use the randomIndex to pull a random email from list
            String key = (String)list[randomIndex].getKey();
            /***
             * 7. D. ) Discussing the results obtained from the BST and hashmaps, the hashmaps
             * offer a constant time get method on average requiring 1 iteration to find
             * any item in a list. The get for the BST varies depending on where the node
             * is situated in the tree. The further down, or the lower the level the node is situated
             * at it requires more iterations to find the go down through the list. thus its
             * takes on average 22 iterations to find items in this case. Still not bad considering there
             * are 100,000 elements in this data structure. 
             */
            
            tm.get(key);
            sc.get(key);
            lp.get(key);

            int tmIters = tm.iterations;
            int scIters = sc.iterations;
            int lpIters = lp.iterations;
            tmAVERAGE += tmIters;
            scAVERAGE += scIters;
            lpAVERAGE += lpIters;
            System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n", key, tmIters, scIters, lpIters);
            //reset iterations back to 0;
            tm.iterations = 0;
            sc.iterations = 0;
            lp.iterations = 0;
        }
        System.out.printf("%-20s\t%-15d\t%-15d\t%-15d\n\n\n", "average", tmAVERAGE/20, scAVERAGE/20, lpAVERAGE/20);
        // reset averages
        tmAVERAGE = 0;
        scAVERAGE = 0;
        lpAVERAGE = 0;
        tm.iterations = 0;
        sc.iterations = 0;
        lp.iterations = 0;


        /***
         * The time complexity I expected from the treeMap in the worst case scenario was O(n).
         * However with n = 100,000 the number of iterations reported from the method was nearly 
         * twice that amount. By analysising the algorithm however we can see that this result 
         * of 199467 iterations does in fact make sense. Imagine all the elements in the bst
         * were perfectly aligned such that all the elements were to the left of eachother. 
         * The BST acts as a LinkedList in this way, and in order to sort this tree the method would have to traverse all the way from the root
         * to the last node and then work its way back up. Essentially touching every node twice
         * except for the bottom node. So in the very worst case scenario like this described above, the big O notation 
         * would first be written down as O(n + n - 1)... we substract one because that bottom
         * node is only touched once and everyone is touched twice. Combining like terms...
         * O(2n - 1) which equals O(n) when you drop the coefficients. But using this O(2n-1) we
         * see our top bound is actually 199,999 for when n = 100,000. and 199,999 > 199,467 so it makes sense.
         * 
         * For HashMapLP and HashMapSC we expected the big O notation for the merge sort algorithms
         * and sortKey algorithms combined to be O(nlogn). For a data set of 100,000 like we have here
         * nLogn would equate to 500,000. Our answer is much greater than this in both circumstances at 
         * around 4,000,000 iteration for each HashMap data structure. By dividing it into parts once again
         * we can see that the big O while it says it is nlogn, it actually much larger because we are 
         * obmitting the coefficients. The sortedKeys method first copies all the elements over from the hashTable
         * data structures in puts them in a generic list of HashMapEntrys which has n complexity, but
         * then before being returned it puts all the sorted elements in order into an arraylist which
         * also has n complexity. 2n = 200,000 iterations in this case. The mergeSort algorithm calls itself recursively twice result in
         * in every call of itself having 2n complexity or 200,000 iterations. In the mergeSort algorithm the system.arraycopy function is also being 
         * executed several times which is further increasing the number of iterations dramatically. With 
         * each call it is copying half of the orginial array into 2 new arrays. This complexity can best be described as
         * O(nlogn) in the worst case or 1,000,000 iterations. In this case they are call twice as well in each
         * recursive call so that equates for 2,000,000 iterations in the worst case from these lines for all the
         * copying. Finally, because mergeSort was called 2n times merge is being called twice as much and its complexity
         * is higher than expected. In the end we see that the true complexity or number of iterations
         * for this method is much higher because all the obmittances of these coefficients; 
         * 
         */
        tm.sortedKeys();
        sc.sortedKeys(new StringComparator());
        lp.sortedKeys(new StringComparator());
        System.out.println("Testing sort() - number of iterations");
        System.out.printf("%-20s\t%-15d\n", "TreeMap", tm.iterations);
        System.out.printf("%-20s\t%-15d\n", "HashMapSC", sc.iterations);
        System.out.printf("%-20s\t%-15d\n\n\n", "HashMapLP", lp.iterations);



        sc.iterations = 0;
        lp.iterations = 0;
        sc.collisions = 0;
        lp.collisions = 0;
        System.out.println("Testing put() - number of collisions");
        System.out.printf("%-15s\t%-15s\t%-15s\n", "Size", "HashMapSC", "HashMapLP");
        HashMapSC sc1 = new HashMapSC(50000);
        HashMapLP lp1 = new HashMapLP(50000);
        sc.iterations = 0;
        lp.iterations = 0;
        sc.collisions = 0;
        lp.collisions = 0;
        readFile(sc1, lp1, "emails.txt");
        System.out.printf("%-15s\t%-15d\t%-15d\n", "50000", sc.collisions, lp.collisions);
        HashMapSC sc2 = new HashMapSC(100000);
        HashMapLP lp2 = new HashMapLP(100000);
        sc.iterations = 0;
        lp.iterations = 0;
        sc.collisions = 0;
        lp.collisions = 0;
        readFile(sc2, lp2, "emails.txt");
        System.out.printf("%-15s\t%-15d\t%-15d\n", "100000", sc.collisions, lp.collisions);
        HashMapSC sc3 = new HashMapSC(150000);
        lp = new HashMapLP(150000);
        sc.iterations = 0;
        lp.iterations = 0;
        sc.collisions = 0;
        lp.collisions = 0;
        readFile(sc3, lp, "emails.txt");
        System.out.printf("%-15s\t%-15d\t%-15d\n", "150000", sc.collisions, lp.collisions);
        HashMapSC sc4 = new HashMapSC(200000);
        lp = new HashMapLP(200000);
        sc.iterations = 0;
        lp.iterations = 0;
        sc.collisions = 0;
        lp.collisions = 0;
        readFile(sc4, lp, "emails.txt");
        System.out.printf("%-15s\t%-15d\t%-15d\n", "200000", sc.collisions, lp.collisions);
        HashMapSC sc5 = new HashMapSC(250000);
        lp = new HashMapLP(250000);
        sc.iterations = 0;
        lp.iterations = 0;
        sc.collisions = 0;
        lp.collisions = 0;
        readFile(sc5, lp, "emails.txt");
        System.out.printf("%-15s\t%-15d\t%-15d\n", "250000", sc.collisions, lp.collisions);
        HashMapSC sc6 = new HashMapSC(300000);
        lp = new HashMapLP(300000);
        sc.iterations = 0;
        lp.iterations = 0;
        sc.collisions = 0;
        lp.collisions = 0;
        readFile(sc6, lp, "emails.txt");
        System.out.printf("%-15s\t%-15d\t%-15d\n", "300000", sc.collisions, lp.collisions);
        HashMapSC sc7 = new HashMapSC(350000);
        lp = new HashMapLP(350000);
        sc.iterations = 0;
        lp.iterations = 0;
        sc.collisions = 0;
        lp.collisions = 0;
        readFile(sc7, lp, "emails.txt");
        System.out.printf("%-15s\t%-15d\t%-15d\n", "350000", sc.collisions, lp.collisions);
        HashMapSC sc8 = new HashMapSC(400000);
        lp = new HashMapLP(400000);
        sc.iterations = 0;
        lp.iterations = 0;
        sc.collisions = 0;
        lp.collisions = 0;
        readFile(sc8, lp, "emails.txt");
        System.out.printf("%-15s\t%-15d\t%-15d\n", "400000", sc.collisions, lp.collisions);
        HashMapSC sc9 = new HashMapSC(450000);
        lp = new HashMapLP(450000);
        sc.iterations = 0;
        lp.iterations = 0;
        sc.collisions = 0;
        lp.collisions = 0;
        readFile(sc9, lp, "emails.txt");
        System.out.printf("%-15s\t%-15d\t%-15d\n", "450000", sc.collisions, lp.collisions);
        HashMapSC sc10 = new HashMapSC(500000);
        lp = new HashMapLP(500000);
        sc.iterations = 0;
        lp.iterations = 0;
        sc.collisions = 0;
        lp.collisions = 0;
        readFile(sc10, lp, "emails.txt");
        System.out.printf("%-15s\t%-15d\t%-15d\n", "500000", sc.collisions, lp.collisions);
        /***
         * As the space in the table increases, there are less collisions because there are more spaces for more
         * elements. However, as you can see from the results, the number of collisions only drops when the capacity
         * in the hashTable crosses a number that is obtained from repetitvely multipling 2 together, in other words, 
         * when a new binary number has been reached on the binary scale. For example, 1.0 * 10^16th is equalivant to 65536
         * in decimal. When the capacity crossed over from 50,000 to 100,000 it cross this number and thus the Hash function
         * which is reliant on the binary operator & was able to make more space or more hashes. Simularly we see the at the 
         * binary number, 1.0 * 10^17 which is equilvanet to 131,072 in decimal, when the capacity increases from 100,000 to 150,000
         * a drop in the number of collisions occurs. Just for emphasis, when the next binary number of
         * 1.0 * 10^18th or 262,144 in decimal is reached, Just as before, inbetween the hashTables with
         * the capacity of 250,000 and 300,000 a drop in the number of collisions occured 
         * 
         * Therefore the key take away is choosing the right size for your hashTable improves its
         * efficiency and reduces its need for rehashing. 
         */
        
    }

    /***
     * Method to read the items from the dictionary.txt file and initalze various data structures with its elements
     * @param   al holds an arraylist
     * @param   filename holds the name of the file
     * no return value
     */
    public static void readFile(TreeMap tm, HashMapSC sc, HashMapLP lp, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] words = line.split(" ");
                String email = words[0];
                String password = words[1];
                tm.add(email, password);
                sc.put(email, password);
                lp.put(email, password);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    public static void readFile(HashMapSC sc, HashMapLP lp, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] words = line.split(" ");
                String email = words[0];
                String password = words[1];
                sc.put(email, password);
                lp.put(email, password);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    public static void readFile(HashMapEntry[] list, String filename){
        File file = new File(filename);
        int i = 0;
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] words = line.split(" ");
                String email = words[0];
                String password = words[1];
                list[i++] = new HashMapEntry(email, password);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found: 2nd Overloaded method");
        }
    }
}