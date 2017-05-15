/**
 *              Textbook Example Program
 * Class:       ComputeAverage2.java
 * Purpose:     This program reads numbers from a file. It computes the sum and
 *              the average of the numbers that it reads. The file should
 *              contain nothing but umbers of type double; if this is not the
 *              case, the output will be the sum and average of however many
 *              numbers were successfully read from the file. The name of the
 *              file will be input by the user.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 15, 2017
 * Version     1.0 (Nearly identical to textbook implementation.)
 * 
 * Based on:   Eck, pp 113
 * 
 * References:
 * 
 */

public class AverageNumbersFromFile {

    public static void main(String[] args) {
        
        while ( true ) {
            String fileName;
            System.out.print( "Enter the name of the file: " );
            fileName = TextIO.getln();
            try {
                TextIO.readFile( fileName );
                break;
            } catch ( IllegalArgumentException e ) {
                System.out.println( "Can't read from the file \"" + fileName + "\"." );
                System.out.println( "Please try again." );
            }
        }
        
        /* At this point, TextIO is reading from the file. */
        
        double number;
        double sum;
        int count;
        
        sum = 0;
        count = 0;
        
        try {
            while ( true ) { // Continue until exception.
                number = TextIO.getDouble();
                
                // Below is skipped if there is an exception in getDouble().
                count++;
                sum += number;
            }
        } catch ( IllegalArgumentException e ) { }  // Do nothing, just proceed.
        
        /* At this point, we've read the entire file. */
        
        System.out.println();
        System.out.println( "Number of data values read: " + count );
        System.out.println( "The sum of the data values: " + sum );
        
        if ( count > 0 ) {
            System.out.println( "The average of the values: " + sum / count );
        }
        else {
            System.out.println( "Can't compute average." );
        }
        
    }
    
}
