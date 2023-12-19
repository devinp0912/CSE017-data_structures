/***
 * Class to model the class Circle who extends Shape
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: Febuary 10, 2023
 * Last Date Modified: Febuary 12, 2023
 */
public class Circle extends Shape{
    //data members
    private double radius;
    /***
	 * Default constructor
	 * No parameters
	 * Initializes the radius to 1 and color to "none" through the super
	 */
    public Circle(){
        super();
        radius = 1.0;
    }
    /***
	 * Constructor with two parameters
	 * @param	color for the color of a shape
     * @param   radius for radius
	 */
    public Circle(String color, double radius){
        super(color);
        this.radius = radius;
    }
    /***
	 * Getter for the radius of a circle
	 * no parameters
	 * @return	the value of the data member radius
	 */
    public double getRadius(){
        return radius;
    }
    /***
	 * Setter for the radius of a circle
	 * @param r holds the new value of radius
	 * no return value
	 */
    public void setRadius(double r){
        radius = r;
    }
    /***
	 * Method to get the Circle information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        return String.format("%s\t%-18.2f\t%-10.2f\t%.2f", super.toString(), radius, getArea(), getPerimeter());
    }
    /***
	 * Method for calculating the area of circle
	 * no parameters
	 * @return value of the Area
	 */
    public double getArea(){
        return Math.PI * radius * radius;
    }
    /***
	 * Method for calculating the perimeter of circle
	 * no parameters
	 * @return value of the perimeter
	 */
    public double getPerimeter(){
        return Math.PI * 2 * radius;
    }
    /***
	 * Method for scaling the dimensions of circle
	 * @param   factor scales all the dimensions by itself
	 * no return value
	 */
    public void scale(double factor){
        radius = radius * factor;
    }
    /***
	 * Method to create a deep copy of a Circle Object
	 * no parameters
	 * @return a deep copy of Circle as an Object
	 */
    public Object clone(){
        return new Circle(getColor(), radius);
    }
}