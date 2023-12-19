/***
 * Class to model the class Pentagon who extends Shape
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: Febuary 10, 2023
 * Last Date Modified: Febuary 12, 2023
 */
public class Pentagon extends Shape{
    //data members
    private double side;
    /***
	 * Default constructor
	 * No parameters
	 * Initializes the side to 1 and color to "none" through the super
	 */
    public Pentagon(){
        super();
        side = 1.0;
    }
    /***
	 * Constructor with two parameters
	 * @param	color for the color of a shape
     * @param   side for side
	 */
    public Pentagon(String color, double side){
        super(color);
        this.side = side;
    }
    /***
	 * Getter for the side of a pentagon
	 * no parameters
	 * @return	the value of the data member side
	 */
    public double getSide(){ return side; }
    /***
	 * Setter for the side of a pentagon
	 * @param side holds the new value of side
	 * no return value
     */
    public void setSide(double side){ this.side = side; }
    /***
	 * Method to get the Pentagon information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        return String.format("%s\t%-18.2f\t%-10.2f\t%.2f", super.toString(), side, getArea(), getPerimeter());
    }
    /***
	 * Method for calculating the area of pentagon
	 * no parameters
	 * @return value of the Area
	 */
    public double getArea(){
        double a = 0.25 * Math.sqrt(5 * (5 + 2*Math.sqrt(5)));
        a *= side * side;
        return a;
    }
    /***
	 * Method for calculating the perimeter of pentagon
	 * no parameters
	 * @return value of the perimeter
	 */
    public double getPerimeter(){
        return side * 5;
    }
    /***
	 * Method for scaling the dimensions of pentagon
	 * @param   factor scales all the dimensions by itself
	 * no return value
	 */
    public void scale(double f){
        side *= f;
    }
    /***
	 * Method to create a deep copy of a Pentagon Object
	 * no parameters
	 * @return a deep copy of pentagon as an Object
	 */
    public Object clone(){
        return new Pentagon(getColor(), side);
    }
}