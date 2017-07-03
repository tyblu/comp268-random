package ComputeAverage2;

/**
 *              Textbook Example Program
 * Class:       ComputeAverage2.java
 * Purpose:     This program reads a sequence of positive integers input by the 
 *              user, and it will print out the average of those integers. The
 *              user is prompted to enter one integer at time. The user must
 *              enter a 0 to mark the end of the data. (The zero is not counted 
 *              as part of the data to be averaged.) The program does not check 
 *              whether the user’s input is positive, so it will actually add up
 *              both positive and negative input values.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 15, 2017
 * Version     1.0
 * 
 * Based on:   Eck, pp 111-112
 * 
 * References:
 * 
 */

public class ComputeAverage2 {

    public static void main(String[] args) {
        
        String inputString;
        double inputNumber;
        double sum;
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
                
                inputString = TextIO.getln();
                
                try {
                    inputNumber = Double.parseDouble( inputString );
                    
                    sum += inputNumber;
                    count++;
                } catch (NumberFormatException e) {
                    System.out.println( "Not a legal number. Try again." );
                }

            }
        }
        
        /* Display the result. */
        
        if ( count == 0 ) {
            System.out.println( "You didn't enter any data." );
        }
        else {
            average = sum / count;
            
            System.out.println();
            System.out.printf( "You entered %d integers.%n", count );
            System.out.printf( "Their average is %1.3f.%n", average );
        }
    }
}