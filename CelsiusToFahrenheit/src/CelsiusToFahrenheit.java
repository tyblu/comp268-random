/**
 *             Assignment0, COMP268
 * Class:      CelciusToFahrenheit.java
 * Purpose:    Convert degrees Celsius to degrees Fahrenheit.
 *
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       April 17, 2017
 * Version     1.2
 */

/**
 * Create a new instance of class CelsiusToFahrenheit. Initialize instance
 * degC, degF variables to values of the arguments passed through the
 * constructor parameters.
 * parameter degC    - degrees Celsius
 * parameter degF    - degrees Fahrenheit
 */

import java.util.Scanner;

public class CelsiusToFahrenheit {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner( System.in );      // Create Scanner object.
        
        int degC, degF;
        
//        startText();
        
        // get degC "Enter the temperature in Celsius: "
        System.out.print("Enter temperature in integer degrees Celsius: ");
        degC = sc.nextInt();
        sc.nextLine();
//        degC = getIntFromInput();
        
        // error handling
//        if ( degC < -273 ) {
//            System.out.println("\nError. Temperature too low. Adjusting.");
//            degC = -273;
//        }
        
//        System.out.printf("%nThe temperature has been set to %d \u00b0C.",degC);
//        System.out.println();
        
        // calculate degF  F=C*(9/5)+32
        degF = degC * 9 / 5 + 32;
        
        // output degF "The temperature in Fahrenheit is "
        System.out.printf("%n%d degrees Celsius is equivalent to %d degrees "
                + "Fahrenheit.", degC, degF);
    }
    
//    private static void print3Dots() {
//        int i;
//        
//        for ( i=0; i<3; i++ ) {
//            Thread.sleep(250);
//            System.out.print(".");
//        }
//    }
    
//    private static void startText() {
//        System.out.print("\nStarting temperature conversionator");
//        print3Dots();
//        System.out.println();
//    }
    
//    private static int getIntFromInput() {
//        int inputInt, k = 0;
//
//        while( !sc.hasNextInt() ) { // catch and skip non-numerical input chars
//            if ( k++ < 1 ) { // give user error feedback
//                System.out.println("Error. Skipping non-integer input. ");
//            }
//            sc.next();
//        }
//        
//        inputInt = sc.nextInt();
//        
//        return inputInt;
//    }
 }      




/**                               THIS LINE IS 100 CHARACTERS WIDE                               **/