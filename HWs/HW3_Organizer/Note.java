/***
 * Class to model the entity Note
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: March 8, 2023
 * Last Date Modified: March 8, 2023 
 */
public class Note implements Comparable<Note>{
    //data members
    private Date date;
    private String title;
    private String description;
    /***
     * constructor with 3 parameters
     * @param   date holds the date of the note
     * @param   title holds the title number of the note
     * @param   description holds the description of the note
     */
    public Note(Date date, String title, String description){
        this.date = date;
        this.title = title;
        this.description = description;
    }
    /***
	 * Getter for the date of a note
	 * @param	no parameters
	 * @return	the value of the data member date
	 */
    public Date getDate(){
        return date;
    }
    /***
	 * Getter for the title of a note
	 * @param	no parameters
	 * @return	the value of the data member title
	 */
    public String getTitle(){
        return title;
    }
    /***
	 * Getter for the description of a note
	 * @param	no parameters
	 * @return	the value of the data member description
	 */
    public String getDescr(){
        return description;
    }
    /***
	 * Setter for the date of a note
	 * @param	d holds the value of the new date
	 * no return value
	 */
    public void setDate(Date d){
        date = d;
    }
    /***
	 * Setter for the title of a note
	 * @param	t holds the value of the new title
	 * no return value
	 */
    public void setTitle(String t){
        title = t;
    }
    /***
	 * Setter for the description of a note
	 * @param	descr holds the value of the new description
	 * no return value
	 */
    public void setDescr(String descr){
        description = descr;
    }
    /***
	 * Method checks if 2 note objects have equal data members
	 * @param   o holds an object
	 * @return  boolean value if they are equal T/F
	 */
    public boolean equals(Object o){    
        Note n = (Note)o;
        if((n.date.toString()).equals(this.date.toString()) && (n.title).equals(this.title) && (n.description).equals(this.description)){
            return true;
        }
        return false;
    }
    /***
	 * Method to get the note information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        return String.format("%-20s%-20s%s", date.toString(), title, description);
    }
    /***
	 * Method to compare dates of notes so to put them in chronological order
	 * @param   n holds a note object 
	 * @return  an integer, a positive number if this note is after the param and vice versa. 0 if they are equal
	 */
    public int compareTo(Note n){
        return this.date.compareTo(n.getDate());
    }
}