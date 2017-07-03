package ReverseInputNumbers;

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
 * Version     1.3
 * 
 * Based on:   Eck, pp 119-120
 * 
 * References: https://stackoverflow.com/a/15352657/165266
 * 
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Iterator;

public class ReverseInputNumbers {

    public static void main(String[] args) {
        
        List<Integer> numbers = new ArrayList<>();
        
        Scanner stdin = new Scanner( System.in );
        stdin.useDelimiter( ",|\\n" );
        
        System.out.println( "Enter a list of comma-separated integers." );
        System.out.println( "Press [Enter] twice to finish." );
        System.out.print( "> " );
        
        while ( stdin.hasNextInt() ) {
            numbers.add( stdin.nextInt() );
        }
        
        System.out.println();
        System.out.println( "Your numbers in reverse order are:");
        
        Collections.reverse( numbers );
        
        Iterator<Integer> iter = numbers.iterator();
        
        while ( iter.hasNext() ) {
            System.out.printf( "%d", iter.next() );
            if ( iter.hasNext() ) { System.out.print( ", " ); }
        }
        
        System.out.println();
    }
}
