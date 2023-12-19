/***
 * Class to model the entity Country
 * @author Devin Pombo
 * @version 0.1
 * Date of creation: April 11, 2023
 * Last Date Modified: April 11, 2023 
 */
import java.util.Iterator;
import java.util.ListIterator;

public class Country{
    //data members
    private String name;
    private ArrayList<Pair<Integer, Double>> carbonEmission;
    private ArrayList<Pair<Integer, Double>> carbonCapita;
    /***
     * 1 arg constructor
     * @param   name holds the name of the country
     */
    public Country(String name){
        this.name = name;
        carbonEmission = new ArrayList();
        carbonCapita = new ArrayList();
    }
    /***
     * getter method for name
     * no param
     * @return the name of the country
     */
    public String getName(){
        return name;
    }
    /***
     * Setter method for name
     * @param name sets name
     * no return value
     */
    public void setName(String name){
        this.name = name;
    }
    /***
     * method that adds a CarbonEmission pair to the carbonEmission array data member
     * @param   year holds the year
     * @param   tons holds the tons of carbon emisions
     * no return value
     */
    public void addCarbonEmission(int year, double tons){
        carbonEmission.add(new Pair(year, tons));
    }
    /***
     * method that adds a CarbonCapita pair to the carbonCapita array data member
     * @param   year holds the year
     * @param   tons holds the tons of carbon emisions
     * no return value
     */
    public void addCarbonCapita(int year, double tons){
        carbonCapita.add(new Pair(year, tons));
    }
    /***
     * Method that iterates through a country's years and finds the year corresponding to the param and return an iterator at that current element
     * @param   year holds the year we are searching for
     * @return a listIterator pointing to the country's carbon emissions array, return null if the year is not found
     */
    public ListIterator<Pair<Integer, Double>> getEmission(int year){
        //create list Iterator
        ListIterator<Pair<Integer, Double>> listIter = carbonEmission.listIterator();
        while(listIter.hasNext()){
            int elementYear = (Integer)listIter.next().getFirst(); 
            if(year == elementYear){
                listIter.previous();
                return listIter;
            }
        }
        return null;
    }
    /***
     * Method that iterates through a country's years and finds the year corresponding to the param
     * @param   year holds the year we are searching for
     * @return a listIterator pointing to the country's carbon per capita array at the index respectively, returns null if not found 
     */
    public ListIterator<Pair<Integer, Double>> getCapita(int year){
        ListIterator<Pair<Integer, Double>> listIter = carbonCapita.listIterator();
        while(listIter.hasNext()){
            int elementYear = (Integer)listIter.next().getFirst(); 
            if(year == elementYear){
                listIter.previous();
                return listIter;
            }
        }
        return null;
    }
    /***
     * Method to get the information from Country
     * no param
     * @return a formatted string with all the information in country
     */
    @Override
    public String toString(){
        Iterator iter1 = carbonEmission.iterator();
        Iterator iter2 = carbonCapita.iterator();
        String returnMe = (this.name + "\n");
        
        while(iter1.hasNext() && iter2.hasNext()){
            Pair p1 = (Pair)iter1.next();
            int year = (Integer)p1.getFirst();
            double emissions = (Double)p1.getSecond();
            Pair p2 = (Pair)iter2.next();
            double perCapita = (Double) p2.getSecond(); 
            returnMe += String.format("%d\t = %.1f tons \t%.1f tons per capita", year, emissions, perCapita);
        }
        return returnMe;
    }
    /***
     * Method to see if 2 countries are equal based on name
     * @param   o holds an object
     * @return T/F if they are or aren't equal
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Country){
            Country c = (Country) o;
            return c.getName().equals(this.name);
        }
        return false;
    }

}