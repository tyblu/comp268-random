/*
 * The MIT License
 *
 * Copyright 2017 Tyler Lucas <tyblu@live.com>.
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
 *              Textbook Exercise Program
 * Class:       Chapter04Exercise04.java
 * Purpose:     Chapter 4 exercise 4 solution. Uses Dice.java.
 *              Lists the average number of rolls it takes to get a given total.
 * 
 * @author:    Tyler Lucas <tyblu@live.com>
 * Student ID: 3305203
 * Date:       May 24, 2017
 * Version     1.0
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014, p183: chapter 4, exercise 4
 * 
 */
public class Chapter04Exercise04 {
    
    public static void main(String[] args) {
        
        System.out.println(intro());
        System.out.println(header());
        
        int dataPoints = 10000;                     // Number of repetitions.
        int maxNumDice = 5;
        int maxTotal = maxNumDice * 6;
        int[][] numberRollsToTotal = new int[maxNumDice][maxTotal];
        double[][] avgRollsToTotal = new double[maxNumDice][maxTotal];
        
        for ( int total=1; total<=maxTotal; total++ )
        {
            for ( int dice=1; dice<=maxNumDice; dice++ )
            {
                for ( int i=0; i<dataPoints; i++ )
                {
                    numberRollsToTotal[dice-1][total-1] += 
                            Dice.countDiceRollsUntil(total, dice);
                }
                avgRollsToTotal[dice-1][total-1] =
                        numberRollsToTotal[dice-1][total-1] / dataPoints;
            }
        }
        
        // Print
        
        
        
        
        
        
    }
    
    static String intro()
    {
        String s = "";
        s += "Let\'s roll some dice an unnecessarily" + "\n";
        s += "gargantuan number of times in order to" + "\n";
        s += "figure out, on average, how many rolls" + "\n";
        s += "it takes a given number of dice to get" + "\n";
        s += "a certain total.";
        
        return s;
    }
    
    static String strRepeat( String str, int repetitions )
    {
        String s = "";
        for (int i=0; i<repetitions; i++)
        {
            s += str;
        }
        return s;
    }
    
    /**
     * Prints table header.
     * 
     * |----------------------------------------------------|
     * |    Average Number of Rolls Taken To Get Total      |
     * |-------|--------------------------------------------|
     * | Total |               Number of Dice               |
     * |  on   |   1    |   2    |   3    |   4    |   5    |
     * | Dice  |--------|--------|--------|--------|--------|
     * |-------|        |        |        |        |        |
     * |    1  | XXX.XX | XXX.XX | XXX.XX | XXX.XX | XXX.XX |
     * |       |        |        |        |        |        |
     * |    2  | XXX.XX | XXX.XX | XXX.XX | XXX.XX | XXX.XX |
     * |       |        |        |        |        |        |
     * |    3  | XXX.XX | XXX.XX | XXX.XX | XXX.XX | XXX.XX |
     * |  ...  |        |        |        |        |        |
     * 
     * @return 
     */
    static String header()
    {
        String s = "";
        
        s += "|----------------------------------------------------|" + "\n";
        s += "|    Average Number of Rolls Taken To Get Total      |" + "\n";
        s += "|-------|--------------------------------------------|" + "\n";
        s += "| Total |               Number of Dice               |" + "\n";
        s += "|  on   |   1    |   2    |   3    |   4    |   5    |" + "\n";
        s += "| Dice  |--------|--------|--------|--------|--------|" + "\n";
        s += "|-------|        |        |        |        |        |" + "\n";
        
        return s;
    }
    
    static int[] getArrayFromMToN(int m, int n)
    {
        int[] array = new int[n];
        for (int i=0; m+1<=n; i++)
        {
            array[i] = m + i;
        }
        return array;
    }
    
    
}
