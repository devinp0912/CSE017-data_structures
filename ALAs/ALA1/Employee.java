/***
 * Class to model the entity Employee
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: January 26, 2023
 * Last Date Modified: January 27, 2023
 */
public class Employee extends Person{
    // Data members
    private int id;
    private String position;
    private double salary;
    /***
	 * Default constructor
	 * No parameters
	 * Initializes position and data members of Person to the string "none" and id and salary to the value of 0
	 */
    public Employee(){
        super();
        id = 0;
        position = "none";
        salary = 0.0;
    }
    /***
	 * Constructor with seven parameters
	 * @param	name for the name of a person
	 * @param	address for the address of a person
	 * @param	phone for the phone number of a person
	 * @param	email for the email address of a person
     * @param   id for the id of the employee
     * @param   position for the employee's position
     * @param   salary for the employee's salary
	 */
    public Employee(String name, String address, String phone, String email, int id, String position, double salary){
        super(name,address,phone,email);
        this.id = id;
        this.position = position;
        this.salary = salary;
    }
    /***
	 * Getter for the id of a employee
	 * @param	no parameters
	 * @return	the value of the data member id
	 */
    public int getId(){return id;}
    /***
	 * Getter for the position of an employee
	 * @param	no parameters
	 * @return	the value of the data member position
	 */
    public String getPosition(){return position;}
    /***
	 * Getter for the salary of an employee
	 * @param	no parameters
	 * @return	the value of the data member salary
	 */
    public double getSalary(){return salary;}
    /***
	 * Setter for the id of an employee
	 * @param	id to set the data member id
	 * no return value
	 */
    public void setId(int id){this.id = id;}
    /***
	 * Setter for the position of an employee
	 * @param	position to set the data member position
	 * no return value
	 */
    public void setPosition(String position){this.position = position;}
    /***
	 * Setter for the salary of an employee
	 * @param	salary to set the data member salary
	 * no return value
	 */
    public void setSalary(double salary){this.salary = salary;}
    /***
	 * Method to get the Employee information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        String out = super.toString();
        return String.format("%s\nID: %d\nPosition: %s\nSalary: %.2f", out, id, position, salary);
    }
}