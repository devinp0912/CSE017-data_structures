/***
 * Class to model the entity Student
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: January 26, 2023
 * Last Date Modified: January 27, 2023
 */
public class Student extends Person{
    // Data members
    private int id;
    private String major;
    /***
	 * Default constructor
	 * No parameters
	 * Initializes data fields of person through super() to the string "none" and id to 0 and major to the string "none"
	 */
    public Student(){
        super();
        id = 0;
        major = "none";
    }
    /***
	 * Constructor with six parameters
	 * @param	name for the name of a person
	 * @param	address for the address of a person
	 * @param	phone for the phone number of a person
	 * @param	email for the email address of a person
     * @param   id for the Student's ID
     * @param   major for the Student's major
	 */
    public Student(String name, String address, String phone, String email, int id, String major){
        super(name,address,phone,email);
        this.id = id;
        this.major = major;
    }
    /***
	 * Getter for the id of a student
	 * @param	no parameters
	 * @return	the value of the data member id
	 */
    public int getId(){ return id;}
    /***
	 * Getter for the major of a student
	 * @param	no parameters
	 * @return	the value of the data member major
	 */
    public String getMajor(){ return major;}

    /***
	 * Setter for the id of a student
	 * @param	id to set the data member id
	 * no return value
	 */
    public void setId(int id){this.id = id;}
    /***
	 * Setter for the major of a student
	 * @param	major to set the data member major
	 * no return value
	 */
    public void setMajor(String major){this.major = major;}
    /***
	 * Method to get the Student information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        return String.format("%s\nID: %d\nMajor: %s", super.toString(), id, major);
    }

}