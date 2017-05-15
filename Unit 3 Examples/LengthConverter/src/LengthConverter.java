/**
 *             Textbook Example Program
 * Class:      LengthConverter.java
 * Purpose:    This program will convert measurements expressed in inches,
 *             feet, yards, miles, meters, or kilometers into each of the possible units
 *             of measure. The measurement is input by the user, followed by the
 *             unit of measure. The measurement is input by the user, followed
 *             by the unit of measure. For example: "17 feet", "1 inch", or 
 *             "2.73 mi". Abbreviations "in", "ft", "yd", and "mi" are accepted.
 *             The program will continue to read and convert measurements until
 *             the user enters an input of 0.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 12, 2017
 * Version     1.1
 * 
 * Based on:   Eck, pp 101-103
 * 
 * References: https://stackoverflow.com/questions/1813853/ifdef-ifndef-in-java
 * 
 */

// For I/O method #2.
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

// For I/O method #3.
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class LengthConverter {
    
    private static final boolean ENABLEMETHOD1 = true;
    private static final boolean ENABLEMETHOD2 = false;
    private static final boolean ENABLEMETHOD3 = false;

    public static void main(String[] args) {
        
        double measurement;
        String units;
        
        double inches, feet, yards, miles, meters, kilometers;
        
        // Method #1 to print the intro text.
        if ( ENABLEMETHOD1 ) {
            
            System.out.print(
                    "Enter measurements in inches, feet, yards, miles, meters, or kilometers.\n"
                    + "You can use abbreviations: in, ft, yd, mi, km. Plurality is also allowed.\n"
                    + "For example:\t1 inch\n"
                    + "\t\t48 inches\n"
                    + "\t\t6 in\n"
                    + "\t\t17 feet\n"
                    + "\t\t23 ft\n"
                    + "\t\t0.4 miles\n"
                    + "\t\t2.73 mi\n"
                    + "\t\t3.1 kilometers\n"
                    + "\t\t1 kilometre\n"
                    + "\t\t2 km\n"
                    + "I will convert your input into the other units of measure.\n"
            );
        }

        // Method #2 to print the intro text.
        if ( ENABLEMETHOD2 ) {
            try {
                Scanner scan = new Scanner(new File( "C:\\Users\\tyblu\\Documents\\repos\\comp268-random\\Unit 3 Examples\\LengthConverter\\src\\intro.txt"));
                while( scan.hasNextLine() ) { System.out.println( scan.nextLine() ); }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LengthConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Method #3 to print the intro text.
        if ( ENABLEMETHOD3 ) {
            try ( Stream<String> lines = Files.lines(Paths.get("C:\\Users\\tyblu\\Documents\\repos\\comp268-random\\Unit 3 Examples\\LengthConverter\\src\\intro.txt"), Charset.defaultCharset() ) ) {
                lines.forEachOrdered(System.out::println);
            } catch (IOException ex) {
                Logger.getLogger(LengthConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (true) {
            
            /* Get the user's input, and convert units to lower case. */
            
            System.out.print( "Enter your measurement, or 0 to end:\t" );
            measurement = TextIO.getDouble();
            
            if ( measurement == 0 ) { break; }
            
            units = TextIO.getlnWord();
            units = units.toLowerCase();
            
            /* Convert the input measurement. */
            
            if ( units.equals(          "inch"          ) 
                    || units.equals(    "inches"        )
                    || units.equals(    "in"            ) ) {
                inches = measurement;
            }
            else if ( units.equals(     "foot"          )
                    || units.equals(    "feet"          )
                    || units.equals(    "ft"            ) ) {
                inches = measurement * 12;
            }
            else if ( units.equals(     "yard"          )
                    || units.equals(    "yards"         )
                    || units.equals(    "yd"            ) ) {
                inches = measurement * 36;
            }
            else if ( units.equals(     "mile"          )
                    || units.equals(    "miles"         )
                    || units.equals(    "mi"            ) ) {
                inches = measurement * 12 * 5280;
            }
            else if ( units.equals(     "metre"         )
                    || units.equals(    "meter"         )
                    || units.equals(    "metres"        )
                    || units.equals(    "meters"        )
                    || units.equals(    "m"             ) ) {
                inches = measurement * 39.3701;
            }
            else if ( units.equals(     "kilometre"     )
                    || units.equals(    "kilometer"     )
                    || units.equals(    "kilometres"    )
                    || units.equals(    "kilometers"    )
                    || units.equals(    "km"            ) ) {
                inches = measurement * 1000 * 39.3701;
            }
            else {
                System.out.printf( "Sorry, but I don't understand \"%s\".%n", units );
                
                continue;   // back to start of while loop
            }
            
            /* Convert measurement in inches to everything else. */
            
            feet = inches / 12;
            yards = inches / 36;
            miles = inches / ( 12 * 5280 );
            meters = inches / 39.3701;
            kilometers = inches / ( 1000 * 39.3701 );
            
            /* Output measurement in terms of each unit of measure. */
            
            System.out.println();
            System.out.println( "That's equivalent to:" );
            System.out.printf( "%12.5g inches%n", inches );
            System.out.printf( "%12.5g feet%n", feet );
            System.out.printf( "%12.5g yards%n", yards );
            System.out.printf( "%12.5g meters%n", meters );
            System.out.printf( "%12.5g miles%n", miles );
            System.out.printf( "%12.5g kilometers%n", kilometers );
            System.out.println();
        }
        
        System.out.println();
        System.out.println( "Okay! Bye for now." );
    }
}
