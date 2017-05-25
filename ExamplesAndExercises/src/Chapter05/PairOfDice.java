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

/**
 *              Textbook Example Program, Chapter 5
 * Class:       Dice.java
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
 *      2014, p183: chapter 4, exercise 3 and 4
 * 
 */

package Chapter05;

public class PairOfDice
{
    public int die1;    // Number showing on 1st die
    public int die2;    // Number showing on 2nd die

    // Constructors
    /**
     * Creates a pair of dice with random values.
     */
    public PairOfDice()
    {
        roll();
    }
    
    /**
     * Creates a pair of dice with initial values val1 and val2.
     * 
     * @param val1  Initial value for 1st die.
     * @param val2  Initial value for 2nd die.
     */
    public PairOfDice(int val1, int val2)
    {
        die1 = val1;
        die2 = val2;
    }

    /**
     * Roll the dice by setting each of the dice to be a random number
     * between 1 and 6, inclusive.
     */
    public void roll()
    {
        die1 = (int)( Math.random()*6 ) + 1;
        die2 = (int)( Math.random()*6 ) + 1;
    }
}