/***
 * Class to model the abstract parent class Shape
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: Febuary 10, 2023
 * Last Date Modified: Febuary 12, 2023
 */
public abstract class Shape implements Comparable<Shape>, Cloneable, Scalable{
    //data member
    private String color;
    /***
	 * Default constructor
	 * No parameters
	 * Initializes color to "none"
	 */
    protected Shape(){
        color = "none";
    }
    /***
	 * Constructor with one parameters
	 * @param	color for the color of a shape
	 */
    protected Shape(String c){
        color = c;
    }
    /***
	 * Getter for the Color of a shape
	 * no parameters
	 * @return	the value of the data member color
	 */
    public String getColor(){
        return color;
    }
    /***
	 * Setter for the color of a shape
	 * @param	c string value of color
	 * no return value
	 */
    public void setColor(String c){
        color = c;
    }
    /***
	 * Method to get the Shape information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        return String.format("%-10s", color);
    }
    /***
	 * Method to that compares shapes by their Area
	 * @param s holds a shape that needs to be compare by
	 * @return and int. 0 if they are equal, 1 if the first is greater, or -1 if the 2nd is
	 */
    @Override //compares shapes by their Area
    public int compareTo(Shape s){
        double shape1 = this.getArea();
        double shape2 = s.getArea();
        if(shape1 == shape2){
            return 0;
        }else if(shape1 > shape2){
            return 1;
        }else{
            return -1;
        }
    }
    //abstract methods that must be defined in all subclasses. You cannot be a shape without...
    //area
    public abstract double getArea();
    //perimeter
    public abstract double getPerimeter();
    //the ability to be cloned
    public abstract Object clone();
    //the ability to be scaled
    public abstract void scale(double f);
}