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
package Chapter07;


/**
 *              Textbook Example Program, Chapter 7
 * Class:       MainCaller.java
 * Purpose:     Used to call and test methods and classes in Chapter 7.
 *              Set the appropriate boolean to {@code true} to run the
 *              {@code call()} routine in the associated class. It may be
 *              necessary to set all other boolean variables to {@code false},
 *              as there may be conflicts.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 19, 2017
 * Version      1.0
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 7: Arrays and ArrayLists</a>
 * 
 */
public class Arity
{
    public static void call(String[] args) { call(); }
    public static void call()
    {
        double[][] aBunchOfNumbers = new double[][]
                {
                    {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                    {0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100},
                    {7, 7, 7, 7},
                    {-25151345, 12312332, 2513212.983213, 324141},
                    { 1 },
                    { }
                };
        
        System.out.println("Variable Arity Method Example:");
        
        for (double[] numberSet : aBunchOfNumbers )
        {
            System.out.print("Inputs:");
            printNumbers(numberSet);
            System.out.println();
            
            System.out.printf("Computed Average: %f%n%n", average(numberSet));
        }
        
    }
    
    public static double average(double... numbers)
    {
        if (numbers.length == 0)
            return Double.NaN;
        
        long sum = 0;
        
        for (double number : numbers)
            sum += number;
        
        return (double)(sum / numbers.length);
    }
    
    public static void printNumbers(double... numbers)
    {
        System.out.print(" (" + numbers.length + " elements) ");
        
        for (double number : numbers)
            System.out.print(" " + number);
    }
}
