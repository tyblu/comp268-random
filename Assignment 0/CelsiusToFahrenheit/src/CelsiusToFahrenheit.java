/**
 *             Assignment0, COMP268
 * Class:      CelciusToFahrenheit.java
 * Purpose:    Convert degrees Celsius to degrees Fahrenheit.
 *
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       April 18, 2017
 * Version     1.3
 */

/**
 * Create a new instance of class CelsiusToFahrenheit. Initialize instance
 * degC, degF variables to values of the arguments passed through the
 * constructor parameters.
 *
 * parameter degC    - input; degrees Celsius
 * parameter degF    - output; degrees Fahrenheit
 */

import java.util.Scanner;

public class CelsiusToFahrenheit {
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner( System.in );      // Create Scanner object.
        
        int degC, degF;
        
        // get degC "Enter the temperature in Celsius: "
        System.out.print("Enter temperature in integer degrees Celsius: ");
        degC = sc.nextInt();
        sc.nextLine();
        
        // calculate degF  F=C*(9/5)+32
        degF = degC * 9 / 5 + 32;
        
        // output degF "The temperature in Fahrenheit is "
        System.out.printf("%n%d degrees Celsius is equivalent to %d degrees "
                + "Fahrenheit.", degC, degF);
        
    } //end main
 }