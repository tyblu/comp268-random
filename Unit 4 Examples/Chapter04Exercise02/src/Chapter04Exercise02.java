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
import java.lang.ArithmeticException;

public class Chapter04Exercise02 {
    
    private static final boolean SINGLE_CONVERSION_MODE = false;
    private static final boolean FILE_CONVERSION_MODE = true;

    public static void main(String[] args) {

        if (FILE_CONVERSION_MODE) {
            // Open hex data file.
            try {
                if (!TextIO.readUserSelectedFile()) {
                    throw new IllegalArgumentException();
                }
            } catch ( IllegalArgumentException e ) {
                readErrorQuit(e);
            }

            // Create new hex data file.
            try {
                if (!TextIO.writeUserSelectedFile()) {
                    throw new IllegalArgumentException(); }
            } catch ( IllegalArgumentException e ) {
                readErrorQuit(e);
            }

            System.out.println("Translating and writing hexadecimal data...");
            System.out.println();

            // Loop over input file lines
            int lineNumber = 0, validHexLinesCount = 0;
            String strLine;
            while (true) {
                try{
                    // Get line
                    strLine = TextIO.getln();

                    lineNumber++;

                    // Print line
                    System.out.println(" Input line " + lineNumber + ": " + strLine);
                } catch ( IllegalArgumentException e ) {
                    break;  // End of file.
                }

                // Parse hex value
                int hexValue = 0;
                int hexFirstCharacterIndex = 0;
                if (strLine.length() > 1) {
                    if (strLine.substring(0,2).compareToIgnoreCase("0x") == 0) {
    //                    System.out.print("hexIndex=2");     // debugging
                        hexFirstCharacterIndex = 2;
                    }
                    else if (strLine.charAt(0) == '#') {
        //                System.out.print("hexIndex=1");     // debugging
                        hexFirstCharacterIndex = 1;
                    }
                }

                hexValue += parseHex(strLine.substring(hexFirstCharacterIndex,strLine.length()));

                // Print translation
                // Write to new file
                System.out.print("Output line " + lineNumber + ": ");
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
        else if (SINGLE_CONVERSION_MODE) {
            System.out.print(
                    "Enter hexadecimal value: 0x");
            String userInput = TextIO.getlnString();
            System.out.print(
                    "                Decimal:   " + parseHex(userInput));
            System.out.println();
        }
    }

    /**
     * Translates input characters representing hexadecimal numbers into the
     * integer values of those numbers.
     * 
     * @param s     Array of String objects.
     * @return      Array of integers, the same size as the input array, each 
     *              the decimal (integer) value of the hexadecimal value.
     *              Return array element values set to -1 for those input array
     *              elements that do not represent a valid hexadecimal value.
     */
    static int[] parseHex( String[] s ) {
        int[] hexValues = new int[s.length];
        
        for ( int i=0; i<s.length; i++ ) {
            hexValues[i] = parseHex(s[i]);
        }
        
        return hexValues;
    }
    
    /**
     * Translates input characters representing hexadecimal numbers into the
     * integer values of those numbers.
     * 
     * @param sb    Array of StringBuilder objects.
     * @return      Array of integers, the same size as the input array, each 
     *              the decimal (integer) value of the hexadecimal value.
     *              Return array element values set to -1 for those input array
     *              elements that do not represent a valid hexadecimal value.
     */
    static int[] parseHex( StringBuilder[] sb ) {
        int[] hexValues = new int[sb.length];
        
        for ( int i=0; i<sb.length; i++ ) {
            hexValues[i] = parseHex(sb[i]);
        }
        
        return hexValues;
    }
    
    /**
     * Translates input characters representing hexadecimal numbers into the
     * integer values of those numbers.
     * 
     * @param c     Array of char.
     * @return      Array of integers, the same size as the input array, each 
     *              the decimal (integer) value of the hexadecimal value.
     *              Return array element values set to -1 for those input array
     *              elements that do not represent a valid hexadecimal value.
     */
    static int[] parseHex( char[] c ) {
        int[] hexValues = new int[c.length];
        
        for ( int i=0; i<c.length; i++ ) {
            hexValues[i] = parseHex(c[i]);
        }
        
        return hexValues;
    }
    
    /**
     * Translates input characters representing hexadecimal numbers into the
     * integer values of those numbers.
     * 
     * @param s     String object.
     * @return      Decimal (integer) value of hexadecimal value. Returns -1 if
     *              input does not represent a valid hexadecimal value.
     */
    static int parseHex( String s ) {
        int hexValue = 0, hexValueOld = 0;
        int hexit = 0x1;     // like a digit
        try {
            for ( int i=s.length()-1; i>=0; i-- ) {
//                hexValue += hexit * hexValue( s.charAt(i) );
                hexValue = addIntegersWithOverflowCheck(
                        hexValue,
                        hexit * hexValue(s.charAt(i))
                );
                hexit *= 0x10L;
            }
        } catch (ParseException | ArithmeticException e) {
            return -1;
        }
        return hexValue;
    }
    
    /**
     * Translates input characters representing hexadecimal numbers into the
     * decimal (integer) values of those numbers.
     * 
     * @param sb    StringBuilder object.
     * @return      Decimal (integer) value of hexadecimal value.
     *              Returns -1 if input does not represent a valid hexadecimal
     *              value.
     */
    static int parseHex( StringBuilder sb ) {
        int hexValue = 0;
        int hexit = 0x1;  // like a digit
        try {
            for ( int i=sb.length()-1; i>=0; i-- ) {
//                hexValue += hexit * hexValue( sb.charAt(i) );
                hexValue = addIntegersWithOverflowCheck(
                        hexValue,
                        hexit * hexValue(sb.charAt(i))
                );
                hexit *= 0x10L;
            }
        } catch (ParseException | ArithmeticException e) {
            return -1;
        }
        
        return hexValue;
    }
    
    /**
     * Translates input characters representing hexadecimal numbers into the
     * integer values of those numbers. Just like {@link hexValue(char)}, but
     * returns -1 instead of throwing ParseException.
     * 
     * @param c     char input, should represent hexadecimal value [0-9a-fA-F].
     * @return      Decimal (integer) value of hexadecimal value. Returns -1 if
     *              input does not represent a valid hexadecimal value.
     */
    static int parseHex( char c ) {
        try {
            return hexValue(c);
        } catch (ParseException e) {
            return -1;
        }
    }
        
    /**
     * Translates single character input representing hexadecimal numbers into
     * its integer value.
     * 
     * @param c     char
     * @return      Decimal (integer) value of hexadecimal value.
     * @throws      ParseException  when input char does not represent a
     *              hexadecimal value ([0-9a-fA-F]+).
     */
    static int hexValue( char c ) throws ParseException {
        int hexValue;
        if ( c >= '0' && c <= '9') hexValue = c - '0';
        else if ( c >= 'a' && c <= 'f' ) hexValue = c - 'a' + 10;
        else if ( c >= 'A' && c <= 'F' ) hexValue = c - 'A' + 10;
        else throw new ParseException(
                "Input character is not a valid hexadecimal value.",0);
        return hexValue;
    }
    
    /**
     * Adds two integers (int) and checks to see if the operation overflows.
     * 
     * @param a     int to add.
     * @param b     int to add.
     * @return      int a+b, if no exception.
     * @throws      ArithmeticException if a+b overflows (is greater than)
     *              {@code Integer.MAX_VALUE}.
     */
    static int addIntegersWithOverflowCheck( int a, int b ) throws ArithmeticException {
        long result = (long)a + b;
        if (result > Integer.MAX_VALUE) {
            throw new ArithmeticException("int overflow doing " + a + "+" + b);
        }
        else return (int)result;
    }
    
    /**
     * Quits on error using a file. {@code Uses System.exit(1)} to shut down GUI
     * processes that may have been opened in addition to the main process, as
     * per documentation in {@link java.TextIO}.
     */
    static void readErrorQuit(Exception e) {
        System.out.println( "Can't read from the file." );
        System.out.println( "Exception: " + e);
        System.out.println("Quitting.");
        System.exit(1);
    }
}
