/***
 * Test class to model the classes from this assignment
 * @author GIVEN TO US FROM COURSESITE;;; I created readNotes and readContacts methods...
 * @version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 8, 2023 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Organizer<Contact> contacts = new Organizer<>(20);
		Organizer<Note> notes = new Organizer<>(20);

		// Testing Organizer for type Note
		readNotes(notes, "notes.txt");
		System.out.println("List of notes:\n" + notes);
		try{
			Date d = new Date("02/22/2021");
			Note n = new Note(d, "Medicine", "Pick up at the pharmacy");
			notes.addElement(n);
			System.out.println("Note: (" + n + ") added successfully.");
			System.out.println("\nList of notes:\n" + notes);
			d = new Date("01/26/2021");
			n = new Note(d, "", "");
			n = notes.findElement(n);
			if (n == null)
				System.out.println("Note not found.");
			else {
				System.out.println("Note found: " + n);
				notes.removeElement(n);
				System.out.println("Note (" + n + ") removed successfully.");
			}
		}
		catch(InvalidDateTimeException e){
			System.out.println(e.getMessage());
		}

		
		System.out.println("\nList of notes:\n" + notes);
		notes.setComparator(new ComparatorByTitle());
		System.out.println("\nList of notes sorted by title:\n" + notes);
		
		// Testing Organizer for type Contact
		readContacts(contacts, "contacts.txt");
		System.out.println("\nList of contacts:\n" + contacts);
		Contact c = new Contact("Floss Albert", "610-222-2434", "afloss@lehigh.edu");
		contacts.addElement(c);
		System.out.println("Contact (" + c + ") added successfully.");
		System.out.println("\nList of contacts:\n" + contacts);
		
		c = new Contact("Philip Mensen", "", "");
		
		c = contacts.findElement(c);
		
		if (c == null)
			System.out.println("Contact not found.");
		else {
			System.out.println("Contact found: " + c);
			contacts.removeElement(c);
			System.out.println("Contact (" + c + ") removed successfully.");
		}
		
		c = new Contact("Albares Cammy", "", "");
		c = contacts.findElement(c);
		if (c == null)
			System.out.println("Contact not found.");
		else {
			System.out.println("Contact found: " + c);
			contacts.removeElement(c);
			System.out.println("Contact(" + c + ") removed successfully.");
		}
		System.out.println("\nList of contacts:\n" + contacts);
		contacts.setComparator(new ComparatorByEmail());
		System.out.println("\nList of contacts sorted by email:\n" + contacts);
		
	}
	/***
	 * Method to get the information from a text file and store it in an organizer object intended to hold note objects
	 * @param	notes is an organizer object
	 * @param	filename is a string that holds the name of the file
	 * no return value
	 */
	// Definition of readNotes
	public static void readNotes(Organizer<Note> notes, String filename) {
		File file = new File(filename);
		try{
			Scanner scnr = new Scanner(file);
			while(scnr.hasNext()){
				Date date = new Date (scnr.nextLine());
				String title = scnr.nextLine();
				String descr = scnr.nextLine();
				notes.addElement(new Note(date, title, descr));
			}
			scnr.close();
		}
		catch(FileNotFoundException e){
			e.getMessage();
		}
		catch(InvalidDateTimeException e){
			e.getMessage();
		}
	}
	/***
	 * Method to get the information from a text file and store it in an organizer object intended to hold note Contacts
	 * @param	contacts is an organizer object intended to hold contact objects
	 * @param	filename is a string that holds the name of the file
	 * no return value
	 */
	// Definition of readContacts
	public static void readContacts(Organizer<Contact> contacts, String filename) {
		File file = new File(filename);
		try{
			Scanner scnr = new Scanner(file);
			while(scnr.hasNext()){
				String contactInfo = scnr.nextLine();
				String [] information = contactInfo.split(" ");
				String name = information[0].concat(" " + information[1]);
				String phone = information[2];
				String email = information [3];
				contacts.addElement(new Contact(name, phone, email));
			}
			scnr.close();
		}
		catch(FileNotFoundException e){
			e.getMessage();
		}
	}

}