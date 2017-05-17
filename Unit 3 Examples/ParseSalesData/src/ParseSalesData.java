/**
 *              Textbook Exercise Program
 * Class:       ParseSalesData.java
 * Purpose:     Parses sales data from file sales.dat. Outputs total sales and
 *              number of cities for which data was not available.
 * 
 *      v1.0    Works! Debugging output removed.
 *      v0.1    Outputs a bunch of values and text to tell you what's going on.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 17, 2017
 * Version     1.0
 * 
 * Based on:    AverageNumbersFromFile (Eck, p113)
 * 
 * References:  Eck p130, Unit 3 Exercise 5
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
            
            // Parse line
            try {   // .substring(1) is to allow 1st .substring(':') to throw exception
                strLine = strLine.substring(strLine.indexOf(':')).substring(1).trim();
            } catch (IndexOutOfBoundsException e) {
                // No ':'. Skip to next line
                continue;
            }
            
            try {   // .substring(1) is to allow 1st .substring('$') to throw exception
                strLine = strLine.substring(strLine.indexOf('$')).substring(1).trim();
            } catch (IndexOutOfBoundsException e) {
                // No '$'. Do nothing.
            }
            
            // Add to sum or add to skipped tally
            try {
                sum += Double.parseDouble(strLine);
            } catch (NumberFormatException e) {
                if ( strLine.length() > 2 ) { skippedCount++; }
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
