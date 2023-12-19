/***
 * Class to test the functionality of the Stack obj, PriorityQueue obj, and the PrintRequest.java class
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 9, 2023
 * Last Date Modified: March 12, 2023
 */
import java.util.Stack;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.io.File;
import java.io.FileNotFoundException;

public class Test{
    public static void main(String[]args){
        Stack<Integer> postfixStack = new Stack<>();
        boolean keepGoing = true;
        Scanner keyboard = new Scanner(System.in);
        System.out.printf("Evaulating postfix expressions\n--------------------------------------------------------------\n");
        //THIS IS USING THE STACK DATA STRUCTURE ----- POSTFIX EXPRESSIONS PRACTICE
        do{
            System.out.println("Enter a postfix expression");
            String postfix = keyboard.nextLine();
            String[] tokens = postfix.split(" ");
            try{
                for(String token: tokens){
                    if(token.matches("\\d{1,}")){// if true, token is a number
                        int operand = Integer.parseInt(token);
                        postfixStack.push(operand);
                    }else{
                        int operand1 = postfixStack.pop();
                        int operand2 = postfixStack.pop();
                        switch(token){
                            case "+":
                                postfixStack.push(operand1 + operand2);
                                break;
                            case "-":
                                postfixStack.push(operand2 - operand1);
                                break;
                            case "/":
                                postfixStack.push(operand2 / operand1);
                                break;
                            case "*":
                                postfixStack.push(operand1 * operand2);
                                break;
                            default:
                                throw new NoSuchElementException();
                        }
                    }
                }
                int result = postfixStack.pop();
                if(postfixStack.isEmpty()){
                    System.out.println("Result: " + result);
                }else{
                    System.out.println("Malformed postfix expression");
                }
            }
            catch(NoSuchElementException e){
                System.out.println("Malformed postfix expression");
            }
            System.out.println("Do you want to evaluate another postifx expression? (yes/no) : ");
            String responce = keyboard.nextLine();
            switch(responce){
                case "yes":
                case "Yes":
                    break;
                case "No":
                case "no":
                    keepGoing = false;
                    break;
                default:
                    System.out.println("Invalid responce. Skipping to PrintRequest part of assignment");
            }
        }while(keepGoing);
        System.out.printf("\n\nSummary of printed requests\n--------------------------------------------------------------\n");
        //THIS IS USING THE PRIORITYQUEUE -- PRINT REQUEST SECTION STARTS BELOW
        PriorityQueue<PrintRequest> pQueue = new PriorityQueue<>();
        File file = new File("requests.txt"); //read from file
        try{
            Scanner read = new Scanner(file);
            while(read.hasNext()){
                int id = read.nextInt();
                String group = read.next();
                long size = read.nextLong();
                PrintRequest pr = new PrintRequest(id, group, size);
                pQueue.offer(pr);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
        //process the print requests and print formatted information from the priority queue
        System.out.printf("%-10s\t%-10s\t%-10s\t%-10s\n", "User ID", "Group", "Size", "Completion Time");
        long totalSize = 0;
        while(!pQueue.isEmpty()){
            PrintRequest pr = pQueue.poll();    
            System.out.println(pr);
            totalSize += pr.getSize();
        }
        PrintRequest pr = new PrintRequest();
        System.out.printf("\n\nTotal Printing time: %s\n", pr.calculateCompletionTime(totalSize));
        
    }
}