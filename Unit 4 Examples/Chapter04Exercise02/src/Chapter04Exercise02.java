/**
 *              Textbook Example Program
 * Class:       Chapter04Exercise02.java
 * Purpose:     Converts a character sequence representing a hexadecimal value
 *              into that value.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 19, 2017
 * Version     1.0
 * 
 * Based on:
 * @see Intro. to Prog. Using Java v7, Eck, David J., 2014, p183: ch4, ex 2
 * 
 * References:
 * 
 */

import java.text.ParseException;

public class Chapter04Exercise02 {

    public static void main(String[] args) {
        
        // Open hex data file.
        try {
            TextIO.readUserSelectedFile();
        } catch ( IllegalArgumentException e ) {
            readErrorQuit();
        }
        
        // Create new hex data file.
        try {
            TextIO.writeUserSelectedFile();
        } catch ( IllegalArgumentException e ) {
            readErrorQuit();
        }
        
        System.out.println("Translating and writing hexadecimal data...");
        
        // Loop over input file lines
        int lineNumber = 0, validHexLinesCount = 0;
        String strLine;
        while (true) {
            try{
                // Get line
                strLine = TextIO.getln();
                
                lineNumber++;
                
                // Print line
                System.out.println("Input line " + lineNumber + ": " + strLine);
            } catch ( IllegalArgumentException e ) {
                break;  // End of file.
            }
            
            // Parse hex value
            int hexValue = 0;
            int hexFirstCharacterIndex;
            if (strLine.substring(0,2).compareToIgnoreCase("0x") == 0) {
                hexFirstCharacterIndex = 2;
            }
            else if (strLine.charAt(0) == '#') {
                hexFirstCharacterIndex = 1;
            }
            else
                hexFirstCharacterIndex = 0;
            
            for ( int i=hexFirstCharacterIndex; i<strLine.length(); i++ ) {
                try {
                    hexValue += parseHex(strLine.charAt(i));
                } catch ( ParseException e ) {
                    hexValue = -1;
                }
            }
            
            // Print translation
            // Write to new file
            System.out.print("Output line + " + lineNumber + ": ");
            if (hexValue > -1) {
                System.out.println(hexValue);
                TextIO.putln(hexValue);
                validHexLinesCount++;
            }
            else {
                System.out.println("[invalid hex data]");
                TextIO.putln("[invalid hex data]");
            }
        }

        // Print stats about hex translation
        System.out.println();
        System.out.println(validHexLinesCount + " of " + lineNumber
                + " lines translated from hexadecimal to decimal integers.");
    }
    
    /**
     * Translates input characters representing hexadecimal numbers into the
     * integer values of those numbers.
     * 
     * @param s     Array of String objects.
     * @return      Array of integers, the same size as the input array, each 
     *              the decimal (integer) value of the hexadecimal value.
     * @throws      IllegalArgumentException when input is not String[].
     */
    static int[] parseHex( String[] s ) {
        int[] hexValues = new int[s.length];
        return hexValues;
    }
    
    /**
     * Translates input characters representing hexadecimal numbers into the
     * integer values of those numbers.
     * 
     * @param sb    Array of StringBuilder objects.
     * @return      Array of integers, the same size as the input array, each 
     *              the decimal (integer) value of the hexadecimal value.
     * @throws      IllegalArgumentException when input is not StringBuilder[].
     */
    static int[] parseHex( StringBuilder[] sb ) {
        int[] hexValues = new int[sb.length];
        return hexValues;
    }
    
    /**
     * Translates input characters representing hexadecimal numbers into the
     * integer values of those numbers.
     * 
     * @param c     Array of char.
     * @return      Array of integers, the same size as the input array, each 
     *              the decimal (integer) value of the hexadecimal value.
     * @throws      IllegalArgumentException when input is not char[].
     */
    static int[] parseHex( char[] c ) {
        int[] hexValues = new int[c.length];
        return hexValues;
    }
    
    /**
     * Translates input characters representing hexadecimal numbers into the
     * integer values of those numbers.
     * 
     * @param s     String object.
     * @return      Decimal (integer) value of hexadecimal value.
     * @throws      IllegalArgumentException when input is not String.
     */
    static int parseHex( String s ) {
        int hexValue = 0;
        return hexValue;
    }
    
    /**
     * Translates input characters representing hexadecimal numbers into the
     * integer values of those numbers.
     * 
     * @param sb    StringBuilder object.
     * @return      Decimal (integer) value of hexadecimal value.
     * @throws      IllegalArgumentException when input is not StringBuilder.
     */
    static int parseHex( StringBuilder sb ) {
        int hexValue = 0;
        return hexValue;
    }
    
    /**
     * Translates input characters representing hexadecimal numbers into the
     * integer values of those numbers.
     * 
     * @param c     char
     * @return      Decimal (integer) value of hexadecimal value.
     * @throws      IllegalArgumentException when input is not char.
     */
    static int parseHex( char c ) {
        return hexValue(c);
    }
        
    /**
     * Translates single character input representing hexadecimal numbers into
     * its integer value.
     * 
     * @param c     char
     * @return      Decimal (integer) value of hexadecimal value.
     * @throws      IllegalArgumentException when input is not char.
     * @throws      ParseException  when input char does not represent a
     *              hexadecimal value ([0-9a-fA-F]+).
     */
    static int hexValue( char c ) throws ParseException {
        int hexValue;
        if ( c >= '0' && c <= '9') hexValue = c - '0';
        else if ( c >= 'a' && c <= 'f' ) hexValue = c - 'a' + 9;
        else if ( c >= 'A' && c <= 'F' ) hexValue = c - 'A' + 9;
        else throw new ParseException(
                "Input character is not a valid hexadecimal value.",0);
        return hexValue;
    }
    
    /**
     * Quits on error using a file. {@code Uses System.exit(1)} to shut down GUI
     * processes that may have been opened in addition to the main process, as
     * per documentation in {@
     */
    static void readErrorQuit() {
        System.out.println( "Can't read from the file." );
        System.out.println( "Exiting." );
        System.exit(1);
    }
}
