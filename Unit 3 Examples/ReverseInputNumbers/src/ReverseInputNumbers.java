/**
 *              Textbook Example Program
 * Class:       ReverseInputNumbers.java
 * Purpose:     Reads the numbers input by the user and then prints them in the
 *              reverse of the order in which they were entered. Assumes that
 *              an input value equal to zero marks the end of the data.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 15, 2017
 * Version     1.0 (Nearly identical to textbook implementation.)
 * 
 * Based on:   Eck, pp 119-120
 * 
 * References:
 * 
 */

public class ReverseInputNumbers {

    public static void main(String[] args) {
        
        int[] numbers;
        int count;
        int num;
        
        numbers = new int[100];
        count = 0;
        
        System.out.println( "Enter up to 100 positive integers; enter 0 to end." );
        
        while ( true ) {
            System.out.print( "? " );
            num = TextIO.getlnInt();
            
            if ( num > 0 ) {
                numbers[count++] = num;
            }
            else
                break;
        }
        
        System.out.println();
        System.out.println( "Your numbers in reverse order are:");
        
        for ( int i = count - 1; i>=0; i-- ) {
            System.out.println( numbers[i] );
        }
    }
}
