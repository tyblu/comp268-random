package FindMaxDivisors;

/**
 *              Textbook Exercise Program
 * Class:       FindMaxDivisors.java
 * Purpose:     Finds number from 1 to some maximum search range that has the
 *              highest number of divisors, and its divisors. If any, other
 *              numbers in the search range with the same number of divisors
 *              are also determined.
 * 
 *      v1.1:   Lists other numbers, if any, and their divisors, within the
 *              search range that have the same number of divisors as the
 *              highest.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 16, 2017
 * Version     1.1
 * 
 * Based on:    CountDivisors (Eck pp 92-93, and my own implementation)
 * 
 * References:
 * 
 */

public class FindMaxDivisors {
    
    private static final int NUMBERMAX = 100;

    public static void main(String[] args) {
        
        /* List to keep track of which numbers we don't have to factor. */
        boolean[] canSkip = new boolean[NUMBERMAX]; // Defaults to {false}
        
        int numberCurrent, numberWithMostDivisors;
        int testDivisor;
        int currentDivisorCount, maxDivisorCount;
        String strDivisorsOfCurrent, strDivisorsOfMax;
        String strOtherNumbersWithMostDivisors;
        
        // Initialize max-divisor data.
        numberWithMostDivisors = -1;    // Initialized to -1 for error detection.
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
                        strOtherNumbersWithMostDivisors += "\n";
                    }
                    strOtherNumbersWithMostDivisors += strDivisorsOfMax + ", 1";
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
            System.out.printf( "%nThese numbers are in the same range and "
                    + "also have %d divisors:"
                    + "%n( listed as <number>, <list of number\'s divisors> )"
                    + "%n%s%n",
                    maxDivisorCount+1,
                    strOtherNumbersWithMostDivisors );
        }
        
//        int numSkipped = 0;
//        for ( int i=0; i<NUMBERMAX; i++ ) {
//            if ( canSkip[i] ) { numSkipped++; }
//        }
//        System.out.printf( "%nThis algorithm skipped %d of %d.%n", numSkipped, NUMBERMAX );
    }
}
