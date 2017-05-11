/**
 *             Textbook Example Program
 * Class:      CountDivisors.java
 * Purpose:    This program reads a positive integer from the user. It counts
 *             how many divisors that number has, and then it prints the result.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 11, 2017
 * Version     1.0 (Nearly identical to textbook implementation.)
 * 
 * Based on:   Eck, pp 92-93
 * 
 * References: 
 * 
 */

public class CountDivisors {

    public static void main(String[] args) {
        
        int number;
        int testDivisor;
        int divisorCount;
        int divisorsTested; // Only counts numbers below 1e6 (i.e. total%1e6).
        
        /* Get a positive integer from the user. */
        
        while ( true ) {
            System.out.print( "Enter a positive integer: ");
            number = TextIO.getlnInt();
            
            if ( number > 0 )    // This 
                break;
            
            System.out.println( "That number is not positive."
                    + " Please try again." );
        }
        
        /* Count the divisors, printing a "." after every 1000000 tests. */
        
        divisorCount = 0;
        divisorsTested = 0;
        
        for ( testDivisor = 1; testDivisor <=number; testDivisor++ ) {
            if ( number % testDivisor == 0 )
                divisorCount++;
            
            divisorsTested++;
            if ( divisorsTested == 1000000 ) {
                System.out.print('.');
                divisorsTested = 0;
            }
        }
        
        /* Display the result. */
        
        System.out.println();
        System.out.printf( 
                "The number of divisors of %d is %d.%n", 
                number, 
                divisorCount );
    }
    
}
