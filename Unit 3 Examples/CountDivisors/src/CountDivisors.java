/**
 *             Textbook Example Program
 * Class:      CountDivisors.java
 * Purpose:    This program reads a positive integer from the user. It counts
 *             how many divisors that number has, and then it prints the result.
 *             Now also prints divisors (including 1 and itself).
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 11, 2017
 * Version     1.1 (Nearly identical to textbook implementation.)
 * 
 * Based on:   Eck, pp 92-93
 * 
 * References: 
 * 
 */

public class CountDivisors {

    public static void main(String[] args) {
        
        int number = 0;
        int testDivisor = 1;
        int divisorCount = 1;
        int divisorsTested = 1; // Only counts numbers below 1e6 (i.e. total%1e6).
        String strDivisors;     // CSV of divisors.
        
        /* Get a positive integer from the user. */
        
        System.out.print( "Enter a positive integer: ");
        number = TextIO.getlnInt();
        
        while ( number < 1 ) {
            System.out.println( "That number is not positive."
                    + " Please try again." );
            number = TextIO.getlnInt();
        }
        
//        do {
//            number = TextIO.getlnInt();
//            if ( number < 1 ) {
//                System.out.println( "That number is not positive."
//                        + " Please try again." );
//            }
//        } while ( number < 1 );

        /* Count the divisors, printing a "." after every 1000000 tests. */
        
        strDivisors = Integer.toString( 1 ) + ", ";
        
        for ( testDivisor = 2; testDivisor <=number; testDivisor++ ) {
            if ( number % testDivisor == 0 ) {
                divisorCount++;
                strDivisors += Integer.toString( testDivisor ) + ", ";
            }
            
            
            divisorsTested++;
            if ( divisorsTested == 1000000 ) {
                System.out.print('.');
                divisorsTested = 0;
            }
        }
        
        /* Display the result. */
        
        System.out.println();
        System.out.printf( 
                "The number of divisors of %d (including 1 and itself) is %d, as follows:%n", 
                number, 
                divisorCount );
        // Strip last comma and space.
        strDivisors = strDivisors.substring( 0,strDivisors.length()-2 );
        System.out.println( strDivisors );
    }
    
}
