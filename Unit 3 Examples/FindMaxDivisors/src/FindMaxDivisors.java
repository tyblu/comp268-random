/**
 *              Textbook Exercise Program
 * Class:       FindMaxDivisors.java
 * Purpose:     Simulates rolling a pair of dice. Stops if both dice roll a ‘1’
 *              and outputs the number of rolls it took.
 * 
 *              Implemented as directed, using either a while loop or a 
 *              do… while loop, chosen in code by flipping the flags 
 *              ENABLE_WHILE and ENABLE_DOWHILE.
 * 
 *              Theoretical probability of snake eyes is 1/36 (~2.8%), meaning
 *              this program will output an average of 36 rolls.
 * 
 *              v1.1 Outputs to log file for analysis/verification.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 16, 2017
 * Version     1.0
 * 
 * Based on:    CountDivisors (Eck pp 92-93, and my own implementation)
 * 
 * References:
 * 
 */

public class FindMaxDivisors {
    
    private static final int NUMBERMAX = 10000;

    public static void main(String[] args) {
        
        /* List to keep track of which numbers we don't have to factor. */
        boolean[] canSkip = new boolean[NUMBERMAX]; // Defaults to {false}
        
        int numberCurrent, numberWithMostDivisors;
        int testDivisor;
        int currentDivisorCount, maxDivisorCount;
        String strDivisorsOfCurrent, strDivisorsOfMax;
        String strOtherNumbersWithMostDivisors;
        
        // Initialize max-divisor data.
        numberWithMostDivisors = -1;
        maxDivisorCount = 1;
        strDivisorsOfMax = "";
        strOtherNumbersWithMostDivisors = "";
        
        // Start with N = NUMBERMAX
        numberCurrent = NUMBERMAX;
        
        do {
            /* Don't factor number if we don't have to. Just skip it. */
            if ( canSkip[numberCurrent-1] ) { continue; }
            
            strDivisorsOfCurrent = Integer.toString( numberCurrent );
            currentDivisorCount = 1;

            // Factor it
            for ( testDivisor = numberCurrent-1; testDivisor>1; testDivisor-- ) {
                if ( numberCurrent % testDivisor == 0 ) {
                    currentDivisorCount++;
                    strDivisorsOfCurrent += ", " + Integer.toString( testDivisor );
                    /*  No need to factor this number, as it will have at least one
                     *  fewer divisors than currentNumber.
                     */
                    canSkip[testDivisor-1] = true;
                }
            }

            // Is highest count of factors? Set max, remember factors.
            if ( currentDivisorCount >= maxDivisorCount ) {
                
                if ( currentDivisorCount == maxDivisorCount ) {
                    if ( strOtherNumbersWithMostDivisors.length() > 2 ) {
                        strOtherNumbersWithMostDivisors += ", ";
                    }
                    strOtherNumbersWithMostDivisors += Integer.toString( numberWithMostDivisors );
                }
                else
                    strOtherNumbersWithMostDivisors = "";    // Reset.
                
                numberWithMostDivisors = numberCurrent;
                maxDivisorCount = currentDivisorCount;
                strDivisorsOfMax = strDivisorsOfCurrent;                
            }

        } while ( --numberCurrent > 1 );
        
        // Output results.
        System.out.printf( "The number under %d with the most divisors is %d."
                + "%nIt has %d divisors, including 1 and itself, as follows:%n%s, 1%n",
                NUMBERMAX,
                numberWithMostDivisors,
                maxDivisorCount+1,
                strDivisorsOfMax );
        
        if ( strOtherNumbersWithMostDivisors.length() > 2 ) {
            System.out.printf( "%nThese numbers were in the same range and had "
                    + "the same large number of divisors (though not the same "
                    + "ones): %s%n",
                    strOtherNumbersWithMostDivisors );
        }
        
//        int numSkipped = 0;
//        for ( int i=0; i<NUMBERMAX; i++ ) {
//            if ( canSkip[i] ) { numSkipped++; }
//        }
//        System.out.printf( "%nThis algorithm skipped %d of %d.%n", numSkipped, NUMBERMAX );
    }
}
