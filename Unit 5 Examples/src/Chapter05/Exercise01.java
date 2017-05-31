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

/**
 *              Textbook Chapter 5 Exercise 1
 * Class:       Exercise01.java
 * Purpose:     Tests PairOfDice class by counting how many times a pair of
 *              dice is rolled before the total is two.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 26, 2017
 * Version     1.0
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014: Chapter 5, Exercise 1
 */
public class Exercise01 {
    
    public static void callExercise01()
    {
        System.out.println(intro());
        
        PairOfDice dice = new PairOfDice();
        
        do {
            dice.roll();
            
            System.out.println();
            System.out.print("Roll #" + dice.getRollCount());
            System.out.print(" comes up " + dice.getTotal());
            System.out.println(" with " + dice + ".");

        } while (dice.getTotal() != 2 );
        
        // outro
        System.out.println("It took " + (dice.getRollCount()-1) + " rolls.");
        System.out.println("Did it work? I hope it did! See you next time.");
    }
    
    /**
     * Contains introductory text. Does not have newline at end.
     * @return String of introductory text. 80-char wide, 4 lines, no newline at
     *          end.
     */
    static String intro()
    {
        String s = "";
//        String s = new String();
        
        s += "Hello, welcome to my overly-introduced program solution " + "\n";
        s += "for Exercise 5.1 (Intro. to Prog. Using Java v7, by Eck)." + "\n";
        s += "We\'re testing the class PairOfDice by counting how many " + "\n";
        s += "rolls it takes to get snake-eyes. Let\'s get, uh... ROLLIN\'!";
        
        return s;
    }
    
}
