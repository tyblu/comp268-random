/**
 *             Textbook Example Program
 * Class:      ComputeAverage.java
 * Purpose:    This program reads a sequence of integers, including zero and
 *             negative integers, and prints out the average of those integers.
 *             The user is prompted to enter one integer at time, separated by
 *             the [Enter] key (carriage return). The user must enter two
 *             carriage returns in a row to mark the end of the data.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 11, 2017
 * Version     1.1
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
        int count, countSequentialCarriageReturns;
        double average;
        
        /* Initializ the summation and counting variables. */
        
        sum = 0;
        count = 0;
        countSequentialCarriageReturns = 0;
        
        /* Read and process the user's input. */
        
        System.out.println( "Enter integers (including 0 and negative"
                + " integers) separated by a carriage return [enter key], and"
                + " end with two carriage returns." );
        
        while ( countSequentialCarriageReturns < 2 ) {
            
            // Count sequential carriage returns, clear buffer each iteration.
            while ( TextIO.eoln() && ++countSequentialCarriageReturns < 2) {
                TextIO.getln();
            }
            
            if ( countSequentialCarriageReturns < 2 ) {
                countSequentialCarriageReturns = 0;         // Reset.
                
                inputNumber = TextIO.getInt();
            
                sum += inputNumber;
                count++;
            }
        }
        
        /* Display the result. */
        
        if ( count == 0 ) {
            System.out.println( "You didn't enter any data." );
        }
        else {
            average = ( (double)sum ) / count;
            
            System.out.println();
            System.out.printf( "You entered %d integers.%n", count );
            System.out.printf( "Their average is %1.3f.%n", average );
        }
    }
}
