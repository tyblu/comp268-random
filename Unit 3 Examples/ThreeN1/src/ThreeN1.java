/**
 *             Textbook Example Program
 * Class:      ThreeN1.java
 * Purpose:    Read a positive integer from the user and print out the '3N+1'
 *             sequence starting from that integer. The program should also 
 *             count and print out the number of terms in the sequence.
 * 
 *             The '3N+1' sequence is as follows:
 * 
 *             If N is an even number, then divide N by two; but if N is odd,
 *             then multiply N by 3 and add 1. Continue to generate numbers in
 *             this way until N becomes equal to 1.
 * 
 *             For example, the sequence for N=3 is 3, 10, 5, 16, 8, 4, 2, 1.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 10, 2017
 * Version     1.3
 * 
 * Based on:   Eck, pp 77-80
 * 
 * References: 
 * 
 */

public class ThreeN1 {

    public static void main(String[] args) {
        
        int N;
        
        System.out.print( "Starting integer for sequence: " );
        N = TextIO.getlnInt();
        
        while ( N <= 0 ) {
            System.out.print( "\tPositive integers only. Please try again: " );
            N = TextIO.getlnInt();
        }
        
        System.out.println( "\tThe sequence is as follows:");
        
        int counter = 0;
        String strCurrentInteger = Integer.toString( N );
        String strSequence = strCurrentInteger;
        
        while ( N != 1 ) {
            
            if ( N % 2 == 0 )
                N /= 2;
            else
                N = 3 * N + 1;
            
            strCurrentInteger = Integer.toString( N );
            
            // Comma between numbers.
            strSequence += ", ";
            
            // Line break just before 80 characters.
            // Check if there has been a line break yet, first.
            if ( strSequence.indexOf("\n") > 0 ) {  // Has there been a \n?
                if ( strSequence.substring( strSequence.lastIndexOf("\n") ).length() 
                        + strCurrentInteger.length() + 1 > 80 ) { // Will be over 80 char?
                    strSequence += "\n";
                }
            }
            else {  // First line only. Check length directly. Include "," char (1).
                if ( strSequence.length() + strCurrentInteger.length() + 1 > 80 ) {
                    strSequence += "\n";
                }
            }
            
            strSequence += strCurrentInteger;
            
            counter++;
        }
        
        System.out.println( strSequence );
        
        System.out.printf( "There were %d terms in the sequence.%n", counter );
    }
}
