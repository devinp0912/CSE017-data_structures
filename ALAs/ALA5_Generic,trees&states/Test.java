import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test{
    public static <E1,E2> int search(ArrayList<Pair<E1,E2>> list, E1 key){
        for(int i = 0; i < list.size(); i++){
            Pair<E1,E2> pair = list.get(i);//get pair at index i
            E1 first = pair.getFirst();//get 1st of pair
            if(first.equals(key)){
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args){
        //validate command-line arguments
        if(args.length ==0){
            System.out.println("No argument provided. (trees or states)");
            System.exit(0); // == return; - terminate program
        }
        String type = args[0];
        if(!type.equals("states") && !type.equals("trees")){
            System.out.println("Invalid data type. states and trees are the only types available");
            System.exit(0);
        }
        switch(type){
            case "states":
                statesOperations();
                break;
            case "trees":
                treesOperations();
                break;
        }
    }
    public static void statesOperations(){
        ArrayList<Pair<String,String>> states = new ArrayList<>(50);
        readStates(states, "states.txt");
        Scanner keyboard = new Scanner(System.in);
        int selection; 
        do{
            printStatesMenu();
            selection = Integer.parseInt(keyboard.nextLine());
            switch(selection){
                case 1: //view
                    print(states);
                    break;
                case 2:
                    System.out.println("Enter a state:");
                    String name = keyboard.nextLine();
                    int index = search(states, name);
                    if(index == -1){
                        System.out.println("State not found");
                    }else{
                        System.out.println("State found: " + states.get(index));
                    }
                    break;
                case 3:
                    states.sort(new ComparatorByFirst());
                    //print(states);
                    break;
                case 4:
                    states.sort(new ComparatorBySecond());
                    //print(states);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid operation (1-5)");

            }
        }while(selection != 5);

    }
    public static <E> void print(ArrayList<E> list){
        for(E element : list){
            System.out.println(element);
        }
    }
    public static void printStatesMenu(){
        System.out.println("Select an operation:");
        System.out.println("1. view all states");
        System.out.println("2. search states by name");
        System.out.println("3. sort states by name");
        System.out.println("4. sort states by capital");
        System.out.println("5. exit");
    }
    public static void readStates(ArrayList<Pair<String,String>> list, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNext()){
                String line = read.nextLine();
                String[] items = line.split("\\|");
                String name = items[0];
                String capital = items[1];
                Pair<String,String> state = new Pair<>(name, capital);
                list.add(state);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        
    }

    public static void treesOperations(){
        ArrayList<Pair<String,Integer>> trees = new ArrayList<>();
        readTrees(trees, "trees.txt");
        Scanner keyboard = new Scanner(System.in);
        int selection;

        do{
            printTreesMenu();
            selection = Integer.parseInt(keyboard.nextLine());
            switch(selection){
                case 1:
                    print(trees);
                    break;
                case 2:
                    System.out.println("Enter a tree:");
                    String name = keyboard.nextLine();
                    int index = search(trees, name);
                    if(index == -1){
                        System.out.println("Tree not found");
                    }else{
                        System.out.println("Tree found: " + trees.get(index));
                    }
                    break;
                case 3:
                    trees.sort(new ComparatorByFirst());
                    //print(trees);
                    break;
                case 4:
                    trees.sort(new ComparatorBySecond());
                    //print(trees);
                    break;
                case 5:
                    break;
                default:
            }

        }while(selection != 5);
    }
    public static void readTrees(ArrayList<Pair<String,Integer>> list, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            while(read.hasNext()){
                String line = read.nextLine();
                String[] items = line.split("\\|");
                String type = items[0];
                int height = Integer.parseInt(items[1]);
                Pair<String,Integer> tree = new Pair<>(type, height);
                list.add(tree);
            }
            read.close();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found.");
        }
    }
    public static void printTreesMenu(){
        System.out.println("Select an operation:");
        System.out.println("1. view all trees");
        System.out.println("2. search trees by name");
        System.out.println("3. sort trees by name");
        System.out.println("4. sort trees by height");
        System.out.println("5. exit");
    }
}