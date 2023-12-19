/***
 * Class to model the class TestShapes to test the functionality of the classes and interfaces in this ALA
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: Febuary 10, 2023
 * Last Date Modified: Febuary 12, 2023
 */
import java.util.Arrays;

public class TestShapes{
    public static void main(String[]args){
        //create array of shapes with length 8 and initlized the elements below
        Shape[] shapes = new Shape[8];
        shapes[0] = new Circle("Black", 2.5);
        shapes[1] = new Triangle("Green", 6.0, 6.0, 6.0);
        shapes[2] = new Rectangle("Red", 5.0, 3.0);
        shapes[3] = new Pentagon("Yellow", 7.0);
        for(int i = 0; i < 4; i++){
            shapes[i+4] = (Shape) shapes[i].clone();
        }
        shapes[4].scale(2.0);
        shapes[5].setColor("Orange");
        ((Rectangle)shapes[6]).setLength(10.0);
        ((Pentagon)shapes[7]).setSide(4.0);
        System.out.println("Before sorting");
        //print shapes
        printArray(shapes);
        //sort 
        java.util.Arrays.sort(shapes);
        ///print again
        System.out.printf("\nAfter sorting\n");
        printArray(shapes);
        //calculate avg perimeter
        double avgPerimeter = getAveragePerimeter(shapes);
        System.out.printf("\n\nAverage Perimeter = %.2f\n", avgPerimeter);

    }
    /***
	 * Method for calculating the average perimeter of all the shapes in an array
	 * @param   list is an array of type shape
	 * @return  double value of the average perimeter of all the sames 
	 */
    public static double getAveragePerimeter(Shape[] list){
        double total = 0;
        for(Shape e : list){
            total += e.getPerimeter();
        }
        return total/(list.length);
    }
    /***
	 * Method for printing all the information in an array of shapes in a formatted easy to read way
	 * @param   list is an array of type shape
	 * no return value
	 */
    public static void printArray(Shape[] list){
        System.out.printf("%-10s\t%-10s\t%-10s\t\t%-10s\t%-10s\n\n", "Shape", "Color", "Dimensions", "Area", "Perimeter");
        for(Shape e : list){
            String typeOfShape;
            if(e instanceof Circle){
                typeOfShape = String.format("%-10s\t", "Circle");
            }else if(e instanceof Triangle){
                typeOfShape = String.format("%-10s\t", "Triangle");
            }else if(e instanceof Rectangle){
                typeOfShape = String.format("%-10s\t", "Rectangle");
            }else{
                typeOfShape = String.format("%-10s\t", "Pentagon");
            }
            System.out.print(typeOfShape);
            System.out.println(e);
        }
    }
}