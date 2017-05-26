/*
 * The MIT License
 *
 * Copyright (c) 2017 Tyler Lucas <tyblu@live.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package Chapter05;

import TextIO.TextIO;

/**
 *              Textbook Chapter 5 Exercise 2
 * Class:       Exercise02.java
 * Purpose:     Tests modified StatCalc class by computing statistics for a set
 *              of non-zero numbers entered by the user.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 26, 2017
 * Version     1.0
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014: Chapter 5, Exercise 2
 */
public class Exercise02 {
    
    private static final double STOP_NUMBER = 0;
    
    public static void callExercise02()
    {
        // intro
        System.out.println(intro());
        
        StatCalc calc = new StatCalc();
        
        // Read numbers from user, STOP_NUMBER to stop (0)
        System.out.print(getInputText());
        double userInput = TextIO.getlnDouble();
        while ( userInput != STOP_NUMBER )
        {
            calc.enter(userInput);
            System.out.print("Got it! Please enter the next number: ");
            userInput = TextIO.getlnDouble();
        }
        
        // Print out all six stats
        System.out.println();
        System.out.println(calc);
        
        System.out.println(outro());
    }
    
    /**
     * Provides introductory spiel. Less than 80-char wide, no newline at end.
     * @return String of introductory text. Less than 80-char wide, no newline
     *          at the very end.
     */
    private static String intro()
    {
        String s = "";
        
        s += "Hello, and welcome to yet another solution program for " + "\n";
        s += "exercise #2 in chapter 5 of Eck (Intro to.. yaddayadda)." + "\n";
        s += "We\'re testing Eck\'s StatCalc class that I\'ve modifed " + "\n";
        s += "to include maximum and minimum number tracking." + "\n";
        s += "Let\'s see if it works!";
        
        return s;
    }
    
    /**
     * Provides introductory spiel. Less than 80-char wide, no newline at end.
     * @return String of introductory text. Less than 80-char wide, no newline
     *          at the very end.
     */
    private static String getInputText()
    {
        String s = "";
        
        s += "Please enter a sequence of numbers separated by [Enter]," + "\n";
        s += "and " + STOP_NUMBER + " to end the sequence:          ";
        
        return s;
    }
    
    /**
     * Provides conclusion spiel. Less than 80-char wide, 1 line, no newline
     * at the very end.
     * @return String of conclusion text. Less than 80-char wide, 1 line, 
     *          no newline at the very end.
     */
    private static String outro()
    {
        return "Did it work? I hope it did! See you later.";
    }
}
