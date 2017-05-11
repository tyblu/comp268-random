/**
 *             Textbook Example Program
 * Class:      ListLetters.java
 * Purpose:    This program reads a line of text entered by the user. It prints
 *             a list of the letters that occur in the text, and I reports how
 *             many different letters were found.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 11, 2017
 * Version     1.0 (Nearly identical to textbook implementation.)
 * 
 * Based on:   Eck, pp 95-96
 * 
 * References: 
 * 
 */

public class ListLetters {

    public static void main(String[] args) {
        
        String strInput;
        int countDifferentLetters;
        char letter;
        
        System.out.println( "Please type in a line of text." );
        strInput = TextIO.getln();
        strInput = strInput.toUpperCase();
        
        System.out.println( "Your input contains the following letters:" );
        System.out.println();
        System.out.write('\t');
        
        countDifferentLetters = 0;
        for ( letter = 'A'; letter <= 'Z'; letter++ ) {
            int i;
            for ( i=0; i<strInput.length(); i++ ) {
                if ( letter == strInput.charAt(i) ) {
                    System.out.print( letter );
                    System.out.print(' ');
                    countDifferentLetters++;
                    break;  // Out of for(i) loop on 1st match.
                }
            }
        }
        
        System.out.println();
        System.out.println();
        System.out.printf( "There were %d different letters.%n", 
                countDifferentLetters );
    }
}
