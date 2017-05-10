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
 * Version     1.0
 * 
 * Based on:   Eck, pp 77-80
 * 
 * References: 
 * 
 */

public class ThreeN1 {

    public static void main(String[] args) {
        
        int N;
        
        System.out.print( "Starting point for sequence: " );
        N = TextIO.getlnInt();
        
        while ( N <= 0 ) {
            System.out.print( "Positive integers only. Please try again: " );
            N = TextIO.getlnInt();
        }
        
        int counter = 0;
        while ( N != 1 ) {
            
            if ( N % 2 == 0 )
                N /= 2;
            else
                N = 3 * N + 1;
            
            System.out.println( N );
            
            counter++;
        }
        
        System.out.println();
        System.out.printf( "There were %d terms in the sequence.", counter );
    }
}
