/***
 * Class to test and compair the complexity of linkedlist and arraylist
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 30, 2023
 * Last Date Modified: March 30, 2023
 */
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AnimalList{
    public static void main(String[] args){
        ArrayList<String> animalAL = new ArrayList<>();
        LinkedList<String> animalLL = new LinkedList<>();
        readAnimals(animalAL, animalLL, "animals.txt");
        System.out.println("Testing contains(Object)");
        testContains(animalAL, animalLL);
        System.out.println("Testing add(int, E)");
        testAdd(animalAL, animalLL);
        System.out.println("Testing remove(E)");
        testRemove(animalAL, animalLL);
    }
    /***
     * Method to read from a file and initalize the array and linkedlist with the elements needed to start the ALA
     * @param al holds the arraylist
     * @param ll holds a linkedlist
     * @param filename holds the name of the file
     * no return value
     */
    public static void readAnimals(ArrayList<String> al, LinkedList<String> ll, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNextLine()){
                String animal = read.nextLine();
                al.add(animal);
                ll.add(animal);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    /*** 
     * Method to test the run time complexity of the arraylist and linkedlist method contains
     * @param al holds the reference to the arraylist
     * @param ll holds the reference to the linkedlist
     * no return value
     */
    public static void testContains(ArrayList<String> al, LinkedList<String> ll){
        int totalAL = 0 , totalLL = 0; 
        for(int i = 0; i < 20; i++){
            int randomIndex = (int)(Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            al.contains(randomAnimal);
            ll.contains(randomAnimal);
            totalAL += ArrayList.containsIter;
            totalLL += LinkedList.containsIter;
            System.out.printf("%-30s\t%-10d\t%-10d\n", randomAnimal, ArrayList.containsIter, LinkedList.containsIter);
        }
        System.out.printf("%-30s\t%-10d\t%-10d\n", "Average", totalAL/20, totalLL/20);
    }
    /*** 
     * Method to test the run time complexity of the arraylist and linkedlist method add
     * @param al holds the reference to the arraylist
     * @param ll holds the reference to the linkedlist
     * no return value
     */
    public static void testAdd(ArrayList<String> al, LinkedList<String> ll){
        int totalAL = 0 , totalLL = 0; 
        for(int i = 0; i < 20; i++){
            int randomIndex = (int)(Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            randomIndex = (int)(Math.random() * al.size());
            al.add(randomIndex, randomAnimal);
            ll.add(randomIndex, randomAnimal);
            totalAL += ArrayList.addIter;
            totalLL += LinkedList.addIter;
            System.out.printf("%-30s\t%-10d\t%-10d\n", randomAnimal, ArrayList.addIter, LinkedList.addIter);
        }
        System.out.printf("%-30s\t%-10d\t%-10d\n", "Average", totalAL/20, totalLL/20);
    }
    /*** 
     * Method to test the run time complexity of the arraylist and linkedlist method remove
     * @param al holds the reference to the arraylist
     * @param ll holds the reference to the linkedlist
     * no return value
     */
    public static void testRemove(ArrayList<String> al, LinkedList<String> ll){
        int totalAL = 0 , totalLL = 0; 
        for(int i = 0; i < 20; i++){
            int randomIndex = (int)(Math.random() * al.size());
            String randomAnimal = al.get(randomIndex);
            randomIndex = (int)(Math.random() * al.size());
            al.remove(randomAnimal);
            ll.remove(randomAnimal);
            totalAL += ArrayList.removeIter;
            totalLL += LinkedList.removeIter;
            System.out.printf("%-30s\t%-10d\t%-10d\n", randomAnimal, ArrayList.removeIter, LinkedList.removeIter);
        }
        System.out.printf("%-30s\t%-10d\t%-10d\n", "Average", totalAL/20, totalLL/20);
    }
}