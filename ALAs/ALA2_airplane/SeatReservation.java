/***
 * Test Class for InvalidSeatException and Airplane titled SeatReservation 
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: Febuary 2, 2023
 * Last Date Modified: Febuary 6, 2023
 */
import java.util.Scanner;
import java.util.InputMismatchException;

public class SeatReservation{
    public static void main(String[]args){
        Airplane airplane = new Airplane("seatmap.txt");
        
        Scanner scnr = new Scanner(System.in);
        boolean keepGoing = true;

        do{
            //print menu
            System.out.println(airplane.toString());
            int choice;
            System.out.println("Please select an operation:");
            System.out.println("1: Reserve a seat");
            System.out.println("2: Free a seat");
            System.out.println("3: Quit");
            
            try{
                //get choice
                choice = scnr.nextInt(); //throws InputMismatchException
                if(choice == 1){//reserve a seat
                    System.out.println("Enter a seat number (Row#/Col):");
                    scnr.nextLine();
                    String seatNumber = scnr.nextLine();
                    if(airplane.reserveSeat(seatNumber)){//throws  InvalidSeatException
                        System.out.println("Seat " + seatNumber + " successfully reserved.");
                    }else{
                        System.out.println("Seat " + seatNumber + " already reserved.");
                    }
                }else if(choice == 2){//unreserve a seat
                    System.out.println("Enter a seat number (Row#/Col)");
                    scnr.nextLine();
                    String seatNumber = scnr.nextLine();
                    if(airplane.freeSeat(seatNumber)){//throws InvalidSeatException
                        System.out.println("Seat " + seatNumber + " successfully freed.");
                    }else{
                        System.out.println("Seat " + seatNumber + " already freed.");
                    }
                }else if(choice == 3){
                    System.out.println("Thank you for using my Airplane seat reservation program");
                    keepGoing = false;
                }else{
                    throw new InputMismatchException("Number entered is out of bounds");
                }
            }
            catch(InvalidSeatException e){
                System.out.println(e.getMessage());
            }
            catch(InputMismatchException e){
                System.out.println("invalid input operation");
            }
        }while(keepGoing);
        airplane.saveMap("seatmap.txt");
    }
}