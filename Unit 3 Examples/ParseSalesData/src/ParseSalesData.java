
import java.text.ParseException;

/**
 *              Textbook Exercise Program
 * Class:       ParseSalesData.java
 * Purpose:     Parses sales data from file sales.dat. Outputs total sales and
 *              number of cities for which data was not available.
 * 
 *      v0.1    Outputs a bunch of values and text to tell you what's going on.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 17, 2017
 * Version     0.1 Alpha version.
 * 
 * Based on:    AverageNumbersFromFile (Eck, p113)
 * 
 * References:  Eck p130, Unit 3 Exercise 5
 *              https://stackoverflow.com/questions/3681242/java-how-to-parse-double-from-regex
 * 
 */

public class ParseSalesData {

    public static void main(String[] args) {
        // Open file
        try {
            TextIO.readFile( "sales.dat" );
        } catch ( IllegalArgumentException e ) {
            System.out.println( "Error reading \"sales.dat\"." );
            System.out.println( "Quitting." );
            System.exit(1);
        }
        
        double sum = 0;
        int skippedCount = 0;
        
        // Loop over lines
        while( !TextIO.eof() ) {
            // Read line
            String strLine = TextIO.getln();
            
            System.out.println();
            System.out.printf("Sum before:   %f%nSkips before: %d%n",sum,skippedCount);
            System.out.print("Line: ");
            System.out.println( strLine );
            
            // Parse line
            try {   // .substring(1) is to allow 1st .substring(':') to throw exception
                strLine = strLine.substring(strLine.indexOf(':')).substring(1).trim();
            } catch (IndexOutOfBoundsException e) {
                // No ':'. Skip to next line
                System.out.print("No : in \"");
                System.out.print(strLine);
                System.out.println("\"");
                System.out.println("Skipping to next line.");
                continue;
            }
            
            try {   // .substring(1) is to allow 1st .substring('$') to throw exception
                strLine = strLine.substring(strLine.indexOf('$')).substring(1).trim();
            } catch (IndexOutOfBoundsException e) {
                // No '$'. Do nothing.
                System.out.print("No $ in \"");
                System.out.print(strLine);
                System.out.println("\"");
                System.out.println("Going on with my life.");
            }
            
            // Add to sum or add to skipped tally
            try {
                sum += Double.parseDouble(strLine);
                System.out.println("Added "+Double.parseDouble(strLine)+" to sum.");
            } catch (NumberFormatException e) {
                if ( strLine.length() > 2 ) { skippedCount++; }
                System.out.println("Added +1 to skippedCount ("+skippedCount+")");
            }
        }
            
        // Output sum and skipped count.
        System.out.printf(
                  "Total sales: $% ,.2f%n"
                + "Number of cities skipped: %d%n",
                sum, skippedCount
        );
    }
    
}
