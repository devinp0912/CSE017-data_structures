/***
 * Test Class for Perosn Faculty Employee and Student entities
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: January 26, 2023
 * Last Date Modified: January 27, 2023
 */
public class Test{
    public static void main(String[]args){
        Person[] people = new Person[4];
        people[0] = new Person("Helen Brown", "22 10th Street Bethlehem", "610-334-2288", "hbrown@gmail.com");
        people[1] = new Student("Paul Leister", "972 4th Street Allentown", "610-331-7177", "pleister@gmail.com", 12345, "CSE");
        people[2] = new Employee("Beth Down", "234 Main Street Philadelphia","484-222-4433", "bdown@gmail.com", 33442, "Systems Administrator", 75000.00);
        people[3] = new Faculty("Mark Jones", "21 Orchid Street Bethlehem", "610-333-2211", "mjones@gmail.com", 22222, "Faculty", 100000.00, "Associate professor");
        System.out.println("Unsorted List:");
        printArray(people);
        sortArray(people);
        System.out.println("Sorted List:");
        printArray(people);
    }
    /***
     * Method to print the data members in the array list
     * @param   list array of type person 
     * no return value
     */
    public static void printArray(Person[]list){
        for(Person p : list){
            System.out.println(p);
        }
    }
    /***
     * Method that sorts the data members in the array list by name
     * @param   list arry of type person
     * no return value
     */
    public static void sortArray(Person[]list){
        for(int i=0; i<list.length; i++){
      		int minIndex = i;
      		for(int j=i+1; j<list.length; j++){
                String name1 = list[j].getName();
                String name2 = list[minIndex].getName();
                 
          		if(name1.compareTo(name2) < 0)
                 		minIndex=j;
            }
      		Person temp = list[i];
      		list[i] = list[minIndex];
      		list[minIndex] = temp;
    		}
    }
}