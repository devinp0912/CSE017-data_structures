/***
 * Class to model the entity Faculty
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: January 26, 2023
 * Last Date Modified: January 27, 2023
 */
public class Faculty extends Employee{
    // Data member
    private String rank;
    /***
	 * Default constructor
	 * No parameters
	 * Initializes rank and data members for Person and Employee to the string "none" or value 0 respectively
	 */
    public Faculty(){
        super();
        rank = "none";
    }
    /***
	 * Constructor with eight parameters
	 * @param	name for the name of a person
	 * @param	address for the address of a person
	 * @param	phone for the phone number of a person
	 * @param	email for the email address of a person
     * @param   id for the ID of faculty
     * @param   position for the name of the position held by the faculty
     * @param   salary for the faculty's salary
     * @param   rank for the faculty's rank
	 */
    public Faculty(String name, String address, String phone, String email, int id, String position, double salary, String rank){
        super(name, address, phone, email, id, position, salary);
        this.rank = rank;
    }
    /***
	 * Getter for the rank of a Faculty
	 * @param	no parameters
	 * @return	the value of the data member rank
	 */
    public String getRank(){ return rank;}
    /***
	 * Setter for the rank of a Faculty
	 * @param	rank to set the data member rank
	 * no return value
	 */
    public void setRank(String rank){this.rank = rank;}
    /***
	 * Method to get the Faculty information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        String out = super.toString();
        return String.format("%s\nRank: %s", out, rank);
    }
}