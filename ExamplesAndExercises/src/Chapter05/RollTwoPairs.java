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
 *              Textbook Example Program, Chapter 5
 * Class:       RollTwoPairs.java
 * Purpose:     
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 25, 2017
 * Version     1.0
 * 
 * Based on and References:
 * @see Chapter04Exercises.Dice
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014, pp202-203
 */
public class RollTwoPairs
{
    /**
     * Call this.
     */
    public static void callRollTwoPairs()
    {
        PairOfDice firstDice;
        firstDice = new PairOfDice();
        
        PairOfDice secondDice;
        secondDice = new PairOfDice();
        
        int countRolls;
        int total1, total2;
        
        countRolls = 0;
        do {
            firstDice.roll();
            total1 = firstDice.die1 + firstDice.die2;
            System.out.println("First pair comes up " + total1);
            
            secondDice.roll();
            total2 = secondDice.die1 + secondDice.die2;
            System.out.println("Second pair comes up " + total2);
            
            countRolls++;
            
            System.out.println();
        } while (total1 != total2);
        
        System.out.println("It took " + countRolls + " rolls to match totals.");
    }
}
