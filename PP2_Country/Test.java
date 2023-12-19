/***
 * Class to test the functionality of Pair,LinkedList,ArrayList,Country.java
 * @author GIVEN TO US FROM COURSESITE
 * @version 0.1
 * Date of creation: April 11, 2023
 * Last Date Modified: April 11, 2023 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ListIterator;
import java.util.Iterator;
/**
    Class to test the modified classes ArrayList and LinkedList
*/
public class Test{
    public static void main(String[] args){
        LinkedList<Country> countries = new LinkedList<>();
        readFile(countries, "emissions.txt");
        System.out.println("List read from the file: " + countries.size() + " countries");
        // Finding a country
        Country c = new Country("United States of America");
        ListIterator<Country> listIter = findCountry(countries, c);
        if(listIter == null){
            System.out.println(c.getName() + " not found.");
        }
        else{
            System.out.println(c.getName() + " found.");
        
            //moving to the next country
            if(listIter.hasNext()){
                System.out.println("Next country: " + listIter.next().getName());
            }
            //moving to the previous country
            listIter.previous();
            listIter.previous();
            listIter.previous();
            if(listIter.hasPrevious()){
                System.out.println("Previous country: " + listIter.previous().getName());
            }
            c = listIter.next();
            c = listIter.next();
            c = listIter.next();
        
            // Get carbon emission in 2015
            int year = 2015;
            ListIterator<Pair<Integer,Double>> emissionIter = c.getEmission(year);
            if(emissionIter == null){
                System.out.println("No carbon emission found for the year " + year);
            }
            else{
                System.out.println("Carbon emission of " + c.getName() + " in " + 
                               year + " = " + emissionIter.next().getSecond() + " tons");
            }
            // Get carbom emission for the 3 previous years
            emissionIter.previous();
            int index = 0;
            System.out.println("\nCarbon emission of " + c.getName() + " for the previous three years:");
            while(index < 3 && emissionIter.hasPrevious()){
                Pair<Integer,Double> pair = emissionIter.previous();
                System.out.println("\t" + pair.getFirst() + " = " + pair.getSecond() + " tons");
                index++;
            }
            // Get Carbon emission per capita in 2015
            ListIterator<Pair<Integer,Double>> capitaIter = c.getCapita(year);
            if(capitaIter == null){
                System.out.println("\nNo carbon emission per capita found for the year " + year);
            }
            else{
                System.out.println("\nCarbon emission per capita of " + c.getName() + " in " + 
                               year + " = " + capitaIter.next().getSecond() + " tons per capita");
            }
            System.out.println("\nCarbon emission per capita of " + c.getName() + " for the next three years:");
        
            // Get carbon emission per capita for the next 3 years
            index = 0;
            while(index < 3 && capitaIter.hasNext()){
                Pair<Integer,Double> pair = capitaIter.next();
                System.out.println("\t" + pair.getFirst() + " = " + pair.getSecond() + " tons per capita");
                index++;
            }
        }
        // Determine the countries with extreme carbon emissions in 2015 and 2019
        c = extremeEmission(countries, 2015, true, true);
        if(c != null)
            System.out.println("\nHighest Carbon Emission in 2015: " + c.getName() + " " + 
                                c.getEmission(2015).next().getSecond() + " tons");
        
        c = extremeEmission(countries, 2015, false, true);
        if(c != null)
            System.out.println("Lowest Carbon Emission in 2015: " + c.getName() + " " + 
                                c.getEmission(2015).next().getSecond() + " tons");

        c = extremeEmission(countries, 2019, true, true);
        if(c != null)
            System.out.println("\nHighest Carbon Emission in 2019: " + c.getName() + " " + 
                                c.getEmission(2019).next().getSecond() + " tons");
        
        c = extremeEmission(countries, 2019, false, true);
        if(c != null)
            System.out.println("Lowest Carbon Emission in 2019: " + c.getName() + " " + 
                                c.getEmission(2019).next().getSecond() + " tons");

        // Determine the countries with extreme carbon emissions per capita in 2015 and 2019
        c = extremeEmission(countries, 2015, true, false);
        if(c != null)
            System.out.println("\nHighest Carbon Emission (per capita) in 2015: " + c.getName() + " " + 
                                c.getCapita(2015).next().getSecond() + " tons");
        
        c = extremeEmission(countries, 2015, false, false);
        if(c != null)
            System.out.println("Lowest Carbon Emission (per capita) in 2015: " + c.getName() + " " + 
                                c.getCapita(2015).next().getSecond() + " tons");

        c = extremeEmission(countries, 2019, true, false);
        if(c != null)
            System.out.println("\nHighest Carbon Emission (per capita) in 2019: " + c.getName() + " " + 
                                c.getCapita(2019).next().getSecond() + " tons");
        
        c = extremeEmission(countries, 2019, false, false);
        if(c != null)
            System.out.println("Lowest Carbon Emission (per capita) in 2019: " + c.getName() + " " + 
                                c.getCapita(2019).next().getSecond() + " tons");
        
    }
    /**
        Read the carbon emission data from filename
        @param list where data will be stored
        @param filename where data will be read from
     */  
    public static void readFile(LinkedList<Country> list, String filename){
        File file = new File(filename);
        try{
            Scanner read = new Scanner(file);
            String line = read.nextLine();
            String[] tokens = line.split(",");
            String country = tokens[0];
            int year = Integer.parseInt(tokens[1]);
            double tons = Double.parseDouble(tokens[2]);
            Country c = new Country(country);
            while(read.hasNextLine()){
                c.addCarbonEmission(year, tons);
                line = read.nextLine();
                tokens = line.split(",");
                String country_new = tokens[0];
                int year_new = Integer.parseInt(tokens[1]);;
                tons = Double.parseDouble(tokens[2]);
                int index = 1;
                while(country.equals(country_new) && year!=year_new && read.hasNextLine()){
                    c.addCarbonEmission(year_new, tons);
                    index++;
                    line = read.nextLine();
                    tokens = line.split(",");
                    country_new = tokens[0];
                    year_new = Integer.parseInt(tokens[1]);;
                    tons = Double.parseDouble(tokens[2]);
                }
                if(year == year_new){
                    for(int i=0; i<index; i++){
                        c.addCarbonCapita(year_new, tons);
                        if(i<index-1){
                            line = read.nextLine();
                            tokens = line.split(",");
                            country_new = tokens[0];
                            year_new = Integer.parseInt(tokens[1]);;
                            tons = Double.parseDouble(tokens[2]);
                        }
                    }
                }
                if(read.hasNextLine()){
                    list.add(c);
                    line = read.nextLine();
                    tokens = line.split(",");
                    country_new = tokens[0];
                    year_new = Integer.parseInt(tokens[1]);;
                    tons = Double.parseDouble(tokens[2]);
                    c = new Country(country_new);
                    year = year_new;
                    country = country_new;
                }
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found.");
            System.exit(0);
        }
    }
    /**
        Search method in the linked list
        @param ll the linked list being searched
        @param c the country searching for
        @return a ListIterator object pointing to the found country, null if no country was no found
    */
    // Time complexity: O(n) - has a while loop
    public static ListIterator<Country> findCountry(LinkedList<Country> ll, Country c){
        ListIterator<Country> iter = ll.listIterator();
        while(iter.hasNext()){
            if(iter.next().equals(c)){
                return iter;
            }
        }
        return null;
    }
    /**
        Find the country with extreme carbon emission
        @param ll the linked list of countries
        @param year the year at which the extreme values are extracted
        @param minMax true for finding the highest emission, false for the lowest emission
        @param type true for carbon emissions in tons, false for carbon emission in tons per capita
        @return the country with the extreme emission at the given year
     */
    // Time complexity: o(n^2) - nested while loop 
    public static Country extremeEmission(LinkedList<Country> ll, int year,
                                          boolean minMax, boolean type){
        Country returnMe = null;
        Pair extremeEmission = null;
        Pair extremeCapita = null; 
        ListIterator<Country> iter = ll.listIterator();
        while(iter.hasNext()){
            //iterate through each country
            Country currentCountry = iter.next();
            ListIterator<Pair<Integer, Double>> subIter1 = currentCountry.getEmission(year);
            ListIterator<Pair<Integer, Double>> subIter2 = currentCountry.getCapita(year);
            //for each country iterate through all the different years
            while(subIter1.hasNext() && subIter2.hasNext()){
                Pair countryEmission = subIter1.next();
                Pair countryCapita = subIter2.next();
                int currentYear = (Integer)countryCapita.getFirst();
                //if the year == the year we want
                if(currentYear == year){
                    if(returnMe == null){
                        returnMe = currentCountry;
                        extremeEmission = countryEmission;
                        extremeCapita = countryCapita;
                    }else{
                        //check which operation to perform
                        //EMISSIONS
                        if(type == true){
                            //high
                            if(minMax == true){
                                if((Double)countryEmission.getSecond() > (Double)extremeEmission.getSecond()){
                                    returnMe = currentCountry;
                                    extremeEmission = countryEmission;
                                }
                            //low
                            }else{
                                if((Double)countryEmission.getSecond() < (Double)extremeEmission.getSecond()){
                                    returnMe = currentCountry;
                                    extremeEmission = countryEmission;
                                }
                            }
                        //PER CAPITA
                        }else{
                            //high
                            if(minMax == true){
                                if((Double)countryCapita.getSecond() > (Double)extremeCapita.getSecond()){
                                    returnMe = currentCountry;
                                    extremeCapita = countryCapita;
                                }
                            //low
                            }else{
                                if((Double)countryCapita.getSecond() < (Double)extremeCapita.getSecond()){
                                    returnMe = currentCountry;
                                    extremeCapita = countryCapita;
                                }
                            }
                        }
                    }
                }
            }
        }
        return returnMe;
    }
}