/**
 *             Textbook Example Program
 * Class:      ComputeAverage.java
 * Purpose:    This program reads a sequence of positive integers input by the
 *             user, and it will print out the average of those integers. The
 *             user is prompted to enter one integer at time. The user must
 *             enter a 0 to mark the end of the data. (The zero is not counted
 *             as part of the data to be averaged.) The program does not check
 *             whether the user’s input is positive, so it will actually add up
 *             both positive and negative input values.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 11, 2017
 * Version     1.0 (Nearly identical to textbook example.)
 * 
 * Based on:   Eck, pp 83-84
 * 
 * References: 
 * 
 */

public class ComputeAverage {

    public static void main(String[] args) {
        
        int inputNumber;
        int sum;
        int count;
        double average;
        
        /* Initializ the summation and counting variables. */
        
        sum = 0;
        count = 0;
        
        /* Read and process the user's input. */
        
        System.out.print( "Enter your first integer: ");
        inputNumber = TextIO.getlnInt();
        
        while ( inputNumber != 0 ) {
            sum += inputNumber;
            count++;
            
            System.out.print( "Enter your next integer, or 0 to end: " );
            inputNumber = TextIO.getlnInt();
        }
        
        /* Display the result. */
        
        if ( count == 0 ) { System.out.println( "You didn't enter any data." ); }
        else {
            average = ( (double)sum ) / count;
            
            System.out.println();
            System.out.printf( "You entered %d integers.%n", count );
            System.out.printf( "Their average is %1.3f.%n", average );
        }
    }
}
