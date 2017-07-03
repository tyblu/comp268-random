package PrintSquare;

/**
 *             Textbook Example Program
 * Class:      PrintSquare.java
 * Purpose:    Reads an integer that is typed in by the user and computes and 
 *             prints the square of that integer.
 *
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       April 10, 2017
 * Version     1.0
 * 
 * Based on:   Eck, pp 40-41
 */

public class PrintSquare {

    public static void main(String[] args) {
        int userInput;      // The number input by the user.
        int square;         // The userInput, multiplied by itself.
        
        System.out.print("Please type a number: ");
        userInput = TextIO.getlnInt();
        square = userInput * userInput;
        
        System.out.println();
        System.out.println("The number that you entered was " + userInput);
        System.out.println("The square of that number is " + square);
        System.out.println();
    }
}
