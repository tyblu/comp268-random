package CommandLineDemo;

/**
 *              Textbook Example Program
 * Class:       CommandLineDemo.java
 * Purpose:     Prints command line arguments.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 18, 2017
 * Version     1.1 (Enhanced for loop.)
 * 
 * Based on:   Eck pp 152
 * 
 * References:
 * 
 */

public class CommandLineDemo {

    public static void main(String[] args) {
        System.out.println("You entered " + args.length + " command-line arguments.");
        
        if ( args.length > 0 ) {
            System.out.println("There were:");
            for (String arg : args) {
                System.out.println("\t" + arg);
            }
        }
    }
}
