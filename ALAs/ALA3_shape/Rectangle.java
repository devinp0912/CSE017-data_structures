/***
 * Class to model the class Rectangle who extends Shape
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: Febuary 10, 2023
 * Last Date Modified: Febuary 12, 2023
 */
public class Rectangle extends Shape{
    //data members
    private double length;
    private double width;
    /***
	 * Default constructor
	 * No parameters
	 * Initializes all the sides to 1 and color to "none" through the super
	 */
    public Rectangle(){
        super();
        length = width = 1.0;
    }
    /***
	 * Constructor with three parameters
	 * @param	color for the color of a shape
     * @param   length for setting length
     * @param   width for setting width
	 */
    public Rectangle(String color, double length, double width){    
        super(color);
        this.length = length;
        this.width = width;
    }
    /***
	 * Getter for the length of a rectangle
	 * no parameters
	 * @return	the value of the data member length
	 */
    public double getLength(){
        return length;
    }
    /***
	 * Getter for the width of a rectangle
	 * no parameters
	 * @return	the value of the data member width
	 */
    public double getWidth(){
        return width;
    }
    /***
	 * Setter for the length of a rectangle
	 * @param   l for the value to set the data member length too 
	 * no return value
	 */
    public void setLength(double l){
        length = l;
    }
    /***
	 * Setter for the width of a rectangle
	 * @param   w for the value to set the data member width too 
	 * no return value
	 */
    public void setWidth(double w){
        width = w;
    }
    /***
	 * Method to get the Rectangle information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        String dimension = String.format("%.2f * %.2f", length, width);
        return String.format("%s\t%-18s\t%-10.2f\t%.2f", super.toString(), dimension, getArea(), getPerimeter());
    }
    /***
	 * Method for calculating the area of rectangle
	 * no parameters
	 * @return value of the Area
	 */
    public double getArea(){
        return length * width;
    }
    /***
	 * Method for calculating the perimeter of rectangle
	 * no parameters
	 * @return value of the perimeter
	 */
    public double getPerimeter(){
        return 2 * (width + length);
    }
    /***
	 * Method for scaling the dimensions of Rectangle
	 * @param   factor scales all the dimensions by itself
	 * no return value
	 */
    public void scale(double factor){
        length *= factor;
        width *= width;
    }
    /***
	 * Method to create a deep copy of a Rectangle Object
	 * no parameters
	 * @return a deep copy of Rectangle as an Object
	 */
    public Object clone(){
        return new Rectangle(getColor(), length, width);
    }
}