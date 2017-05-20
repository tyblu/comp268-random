/**
 *              Textbook Example Program
 * Class:       Chapter04Exercise01.java
 * Purpose:     Gets string input from user and echoes it with each word 
 *              capitalized.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 19, 2017
 * Version     1.0
 * 
 * Based on:   Eck p183
 * 
 * References:
 * 
 */

public class Chapter04Exercise01 {

    public static void main(String[] args) {
        // get input
        System.out.println();
        System.out.print("Enter phrase to be capitalized: ");
        
        String strInput = TextIO.getlnString();
        
        System.out.print("Capitalized version: ");
        
        printCapitalized( strInput );
        
        System.out.println();
    }
    
    /**
     * Outputs a phrase (String) with first letter of each word capitalized.
     * All letters preceeded by non-letter characters capitalized.
     * 
     * Preconditions:   Standard output is set.
     * Postconditions:  Prints to standard output. No newlines before or after.
     * @param str   Input phrase. Any string.
     */
    static void printCapitalized( String str ) {
        
        StringBuilder strCapitalized = new StringBuilder(str);
        char thisChar;
        char lastChar = ' ';
        boolean isThisCharLetter;
        boolean isLastCharLetter = false;
        
        for ( int i=0; i<str.length(); i++ ) {
            
            thisChar = str.charAt(i);
            isThisCharLetter = Character.isLetter(thisChar);
            
            if ( isThisCharLetter && !isLastCharLetter ) {
                strCapitalized.setCharAt(i, Character.toUpperCase(thisChar));
            }
            
            lastChar = thisChar;
            isLastCharLetter = isThisCharLetter;
        }
        
        System.out.print(strCapitalized);
    }
}
