/***
 * Class to model the class Triangle who extends Shape
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: Febuary 10, 2023
 * Last Date Modified: Febuary 12, 2023
 */
public class Triangle extends Shape{
    //data members
    private double side1;
    private double side2;
    private double side3;
    /***
	 * Default constructor
	 * No parameters
	 * Initializes all the sides to 1 and color to "none" through the super
	 */
    public Triangle(){
        super();
        side1 = 1.0;
        side2 = 1.0;
        side3 = 1.0;
    }
    /***
	 * Constructor with four parameters
	 * @param	c for the color of a shape
     * @param   s1 for setting side1
     * @param   s2 for setting side2
     * @param   s3 for setting side3
	 */
    public Triangle(String c, double s1, double s2, double s3){
        super(c);
        side1 = s1;
        side2 = s2;
        side3 = s3;
    }
    /***
	 * Getter for the side1 of a triangle
	 * no parameters
	 * @return	the value of the data member side1
	 */
    public double getSide1(){return side1;}
    /***
	 * Getter for the side2 of a triangle
	 * no parameters
	 * @return	the value of the data member side2
	 */
    public double getSide2(){return side2;}
    /***
	 * Getter for the side3 of a triangle
	 * no parameters
	 * @return	the value of the data member side3
	 */
    public double getSide3(){return side3;}
    /***
	 * Setter for the side1 of a triangle
	 * @param   s1 for the value to set the data member side1 too 
	 * no return value
	 */
    public void setSide1(double s1){side1 = s1;}
    /***
	 * Setter for the side2 of a triangle
	 * @param   s2 for the value to set the data member side2 too 
	 * no return value
	 */
    public void setSide2(double s2){side2 = s2;}
    /***
	 * Setter for the side3 of a triangle
	 * @param   s3 for the value to set the data member side3 too 
	 * no return value
	 */
    public void setSide3(double s3){side3 = s3;}
    /***
	 * Method to get the Triangle information
	 * no parameters
	 * @return formatted string containing the value of the data members
	 */
    @Override
    public String toString(){
        String color = super.toString();
        String dimension = String.format("%.2f * %.2f * %.2f", side1, side2, side3);
        return String.format("%s\t%-18s\t%-10.2f\t%.2f", color, dimension, getArea(), getPerimeter());
    }
    /***
	 * Method to get the area of Triangle
	 * no parameters
	 * @return value of the area calculation for triangle
	 */
    public double getArea(){
        //sqrt(s(s-a)(s-b)(s-c)) = area... s = semiperimter
        double p = getPerimeter() / 2;
        return Math.sqrt(p * (p-side1) * (p-side2) * (p-side3));
    }
    /***
	 * Method to get the perimeter of Triangle 
	 * no parameters
	 * @return value of the perimeter
	 */
    public double getPerimeter(){
        return side1+side2+side3;
    }
    /***
	 * Method to scale the dimensions of triangle by a scale factor
	 * @param   factor multiples all the sides by itself
	 * no return value
	 */
    public void scale(double factor){
        side1 *= factor;
        side2 *= factor;
        side3 *= factor;
    }
    /***
	 * Method to create a deep copy of a Triangle Object
	 * no parameters
	 * @return a deep copy of Triangle as an Object
	 */
    public Object clone(){
        return new Triangle(getColor(), side1, side2, side3);
    }
}