/***
 * Class that utilizes various File functions and explores uses for Recursion
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: February 16, 2023
 * Last Date Modified: February 16, 2023 
 */

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Recursion{
    public static void main(String[]args){
        
        //PART ONE OF ALA
        //testing findFile
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter folder name:");
        String folder = keyboard.nextLine();
        System.out.println("Enter a filename: ");
        String fileName = keyboard.nextLine();
        String found = findFile(folder, fileName);
        if(found.equals("")){
            System.out.printf("File %s not found in \n%s\n", fileName, folder);
        }else{
            System.out.printf("File %s found at \n%s\n", fileName, found);
        }
        
        //PART TWO OF ALA
        //testing getSize
        System.out.println("Enter folder name:");
        folder = keyboard.nextLine();
        long size = getSize(folder);
        if(size == -1){
            System.out.println("Folder "  + folder + " not found.");
        }else if(size > 0){
            //FORMAT SIZE into MB or KB
            String stringByte = convertByte(size);
            System.out.println("Size of " + folder + ": " + stringByte);
        }else{
            System.out.println("Folder "  + folder + " is empty.");
        }

        //Part THREE of ALA
        //testing findWord
        System.out.println("Enter folder name:");
        folder = keyboard.nextLine();
        System.out.println("Enter a word:");
        String word = keyboard.nextLine();
        findWord(folder, word);
    }
    



    /***
     * Method takes a directory path to a folder and searches through recursion for a file 
     * in that folder and all subfolders through recursion
     * @param   folder holds directory path/folder name
     * @param   filename holds the name of the folder that was searched for
     * @return  an empty string if the filename doesn't exist or the folder was actually a file
     *          or return the path to the file if a match was found
     */
    public static String findFile (String folder, String filename){
        //System.out.println("Looking for " + filename + " in " + folder);
        File file = new File(folder);
        if(!file.exists()){
            return "";
        }
        if(file.isFile()){
            return "";
        }
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files){
                if(f.isFile()){
                    if(f.getName().equals(filename)){//file was found
                        return f.getAbsolutePath();
                    }
                }
                if(f.isDirectory()){
                    String found = findFile(f.getAbsolutePath(), filename);
                    if(!found.equals("")){
                        return found;
                    }
                }
            }
        }
        return "";
    }
    /***
     * Method for converting the long value of bytes to a formatted Giga,Mega,or Kilo - byte String
     * @param   s holds digit value of bytes
     * @return  appropriately formatted string of the size of the file in the correct units
     */
    public static String convertByte(long s){
        double size = (double)s;
        if(s >= 1000000000){
            size = size/1000000000;
            return String.format("%.2f GBytes", size);
        }else if(s >= 1000000){
            size /= 1000000;
            return String.format("%.2f MBytes", size);
        }else if(s >= 1000){
            size /= 1000;
            return String.format("%.2f KBytes", size);
        }else{
            return String.format("%d Bytes", size);
        }
    }
    /***
     * Method for getting the size of a file in bytes
     * @param   folder holds directory path/folder name
     * @return  a long of the size in bytes of a file if folder == file... or a long equal to the total of 
     *          size in bytes of all the files in that folder an subfolders
     */
    public static long getSize(String folder){
        long size = 0;
        File file = new File(folder);
        if(!file.exists()){
            return -1;
        }
        if(file.isFile()){
            return file.length();//returns size of file in bytes
        }
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files){
                if(f.isFile()){
                    size += f.length();
                }
                if(f.isDirectory()){
                    size += getSize(f.getAbsolutePath());
                }
            }
        }
        return size;
    }
    /***
     * Method for searching the for a word in a folder (and all its subfolders) or a file
     * @param   folder holds directory path/folder name
     * @param   word holds the word that you want to search for
     * no return value.... just print statements: Prints the folder wasnt found or the total number of 
     * occurances of that word in each file 
     */
    public static void findWord(String folder, String word){
        File file = new File(folder);
        if(!file.exists()){
            System.out.println("Folder not found.");
        }
        if(file.isFile()){
            int count = readFile(file.getAbsolutePath(), word);// look for word in file
            if(count > 0){
                System.out.println(word + " appears " + count + " times in " + file.getAbsolutePath());
            }
        }
        if(file.isDirectory()){
            File[] files = file.listFiles();
            for(File f : files){
                findWord(f.getAbsolutePath(), word);
            }
        }
    }
    /***
     * Method for reading the individual files and searching for key word
     * @param   filename holds the file's name
     * @param   word holds the key search word
     * @return  number of occurances of the word in that file
     */
    public static int readFile(String filename, String word){
        int count = 0;
        int j = 0; 
        File file = new File(filename);
        try{
            Scanner readFile = new Scanner(file);
        while(readFile.hasNext()){
            String line = readFile.nextLine();
            for(int i = 0; i < line.length(); i++){
                if(j == word.length()){// if j is ever incremented to word.length then we have a match... reset j
                    count++;
                    j = 0;
                }
                if(line.charAt(i) == word.charAt(j)){// 1.) if the char in line == word increment j
                    j++; 
                }else{
                    j = 0; // 2.) if the char doesn't then reset the counter
                }
            }
        }
        readFile.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        
        return count;
        
    }
}