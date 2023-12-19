/***
 * Class to model the entity Airplane
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: Febuary 2, 2023
 * Last Date Modified: Febuary 6, 2023
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Airplane{
    // Data member
    private char[][] seatMap;
    /***
	 * Default constructor
	 * No parameters
	 * Initializes the seat chart of data member seatMap as a 9x8 array with the character .
	 */
    Airplane(){
        seatMap = new char[9][8];
        for(int i=0; i<9; i++){
            for(int j=0; j<8; j++){
                seatMap[i][j] = '.';
            }
        }
    }
    /***
	 * constructor with 1 parameter, creates a new array that is 9x8
	 * @param   filename stores the name of a file that will save the seatMap to 
	 */
    Airplane(String filename){
        seatMap = new char[9][8];
        readMap(filename);
    }
    /***
	 * Checks to see if a file exists with @param filename and if so initlizes seatMap with the data
     * in @param filename . If filename does not exist it fills seatMap with '.' like the default 
     * constructor
	 * @param	filename stores the name of a file
	 * no return value
	 */
    private void readMap(String filename){
        File file = new File(filename);
        try{
            Scanner readFile = new Scanner(file);//throws FileNotFoundException
            for(int i=0; i<9; i++){
                for(int j=0; j<8; j++){
                    seatMap[i][j] = readFile.next().charAt(0);//read 1 char from file
                }
            }
            readFile.close();
            //file.close();
        }
        catch(FileNotFoundException e){
            // for when file not found: initlize it
            for(int i=0; i<9; i++){
                for(int j=0; j<8; j++){
                    seatMap[i][j] = '.';
                }
            }
        }
    }
    /***
	 * Checks if the seatNumber entered by the user is a valid input on the grid in the form of
     * (row)(col), where row must be 1-9 and col must be A-H
	 * @param	seatNumber holds the string value of the seat number
	 * @return	a boolean value if its a real seat number, or throws an InvalidSeatException
	 */
    private boolean checkSeatNumber(String seatNumber) throws InvalidSeatException{
        if(seatNumber.matches("[1-9][A-H]")){
            return true;
        }
        throw new InvalidSeatException("Invalid Seat Exception (row[1-9])column[A-H].");
    }
    /***
	 * takes the entry by the user and updates a vacant seat with X in the apporiate spot.
	 * @param	seatNumber holds the string value of the seat number
	 * @return	boolean value true when a seat was update and false when it wasn't
	 */
    public boolean reserveSeat(String seatNumber) throws InvalidSeatException{
        if(checkSeatNumber(seatNumber)){
            int row = seatNumber.charAt(0) - '1';
            int col = seatNumber.charAt(1) - 'A';
            if(seatMap[row][col] == 'X'){
                return false;
            }
            seatMap[row][col] = 'X';
            return true;
        }            
        return false;        
    }
    /***
	 * takes the entry from the user and updates a vacated seat with . in the apporiate spot
	 * @param	seatNumber holds the string value of the seat number entered by the user
	 * @return	boolean value true when a seat was update and false when it wasn't
	 */
    public boolean freeSeat(String seatNumber) throws InvalidSeatException{
        if(checkSeatNumber(seatNumber)){
            char rowChar = seatNumber.charAt(0);
            char colChar = seatNumber.charAt(1);
            int row = rowChar - '1';
            int col = colChar - 'A';
            if(seatMap[row][col] == '.'){
                return false;
            }
            seatMap[row][col] = '.';
            return true;
        }
        return false;        
    }
    /***
	 * PrintWriter saves the data in the seatMap 9*8 array to the filename in the appropriate format
	 * @param	filename holds the value of the name of the file
	 * no return value;
	 */
    public void saveMap(String filename){
        File file = new File(filename);
        try{
            PrintWriter writeFile = new PrintWriter(file);
            for(int i=0; i<9;i++){
                for(int j=0; j<8; j++){
                    writeFile.print(seatMap[i][j] + " ");
                }
                writeFile.println();// new line after each row
            }
            writeFile.close(); //close the file
        }
        catch(FileNotFoundException e){
            System.out.println("Cannot write to "+filename);
        }
    }
    /***
	 * Method to get the information saved in seatMap
	 * no parameters
	 * @return	formatted string with all the information from seatMap
	 */
    @Override
    public String toString(){
        String out = "  A B C D E F G H\n"; 
        for(int i=0; i<9;i++){
            out += (i+1) + " ";
            for(int j=0; j<8; j++){
                out += seatMap[i][j] + " ";
            }
            out += "\n";
        }
        return out;
   }
}
