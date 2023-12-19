import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Test{
    public static void main(String[] args){
        ArrayList<String> animalAl = new ArrayList<>();
        Stack<String> animalStack = new Stack<>();
        PriorityQueue<String> animalPQ = new PriorityQueue<>();
        readAnimals(animalAl, animalStack, animalPQ, "animals.txt");
        System.out.println("Testing the contains() method");
        System.out.printf("%-30s\t%-10s\t%-10s\t%-10s\n", "Animal", "ArrayList", "Stack", "PriorityQueue");
        //Testing contains
        int alAverage = 0, sAverage = 0, pqAverage = 0;
        for(int i=0; i<20; i++){
            int randomIndex = (int)(Math.random() * animalAl.size());
            String randomAnimal = animalAl.get(randomIndex);
            animalAl.contains(randomAnimal);
            int alIter = ArrayList.containsIter;
            alAverage += alIter;
            animalStack.contains(randomAnimal);
            int stackIter = ArrayList.containsIter;
            sAverage += stackIter;
            animalPQ.contains(randomAnimal);
            int pqIter = ArrayList.containsIter;
            pqAverage += pqIter;
            System.out.printf("%-30s\t%-10d\t%-10d\t%-10d\n", randomAnimal, alIter, stackIter, pqIter);
        }
        System.out.printf("%-30s\t%-10d\t%-10d\t%-10d\n", "Average", alAverage/20, sAverage/20, pqAverage/20);

        //test sorting
        System.out.println("Contents of the three data structures");
        System.out.println("\nArraylist: " + animalAl);
        System.out.println("\n" + animalStack);
        System.out.println("\n" + animalPQ);

        animalAl.sort();
        animalStack.sort();
        animalPQ.sort();

        System.out.println("\nContents of the three data structures after sorting using the natural ordering");
        System.out.println("\nArraylist: " + animalAl);
        System.out.println("\n" + animalStack);
        System.out.println("\n" + animalPQ);

        StringComparator sc = new StringComparator();
        animalAl.sort(sc);
        animalStack.sort(sc);
        animalPQ.sort(sc);

        System.out.println("\nContents of the three data structures after sorting using a string comparator");
        System.out.println("\nArraylist: " + animalAl);
        System.out.println("\n" + animalStack);
        System.out.println("\n" + animalPQ);

        System.out.println("\nTesting sort() methods");
        System.out.printf("\n%-17s%-17s%-17s%-17s\n", "Sorting method", "ArrayList", "Stack", "PriorityQueue");
        System.out.printf("%-17s%-17s%-17s%-17s\n", "sort()", alAverage, sAverage, pqAverage);
        System.out.printf("%-17s%-17s%-17s%-17s\n", "sort(Comparator)", alAverage, sAverage, pqAverage);


    }
    //O(n)
    /***
     * Method to read from a file
     * @param   a1 is an arraylist
     * @param   s is a stack
     * @param   pq s a priority queue
     * @param   filename is the name of the file
     * no return value
     */
    public static void readAnimals(ArrayList<String> al, Stack<String> s, PriorityQueue<String> pq, String filename){
        File file = new File(filename);
        try{
            Scanner read  = new Scanner(file);
            while(read.hasNextLine()){
                String animal = read.nextLine();
                al.add(animal);
                s.push(animal);
                pq.offer(animal);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Not a valid filename");
        }
    }
}