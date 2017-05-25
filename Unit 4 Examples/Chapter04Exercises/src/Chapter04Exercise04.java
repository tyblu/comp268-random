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
 * @see #10 of <a href="https://goo.gl/0BpMUv">Coding Horror: New Programming Jargon</a>
 * 
 */
public class Chapter04Exercise04 {
    
    public static void main(String[] args) {
        
        System.out.println(intro());
        System.out.println();
        
        int dataPoints = 10000;                     // Number of repetitions.
        int maxNumDice = 5;
        int maxTotal = maxNumDice * 6;
        double[][] avgRollsToTotal = new double[maxTotal][maxNumDice];
        
        /* Collect data and fill table. */
        System.out.print(tableHeader());
        int dice, total;
        int sumNumberRollsToTotal;
        for ( total=1; total<=maxTotal; total++ ) // rows
        {
            System.out.print(tableRowBlank());
            
            for ( dice=1; dice<=maxNumDice; dice++ ) // columns
            {
                sumNumberRollsToTotal = 0;
                try {
                    for ( int i=0; i<dataPoints; i++ ) // averaging
                    {
                        /* 6 * dice * dataPoints, the maximum return value of
                        * countDiceRollsUntil(int,int), will probably never 
                        * overflow int due to processing limitations on dice and
                        * statistical suitability of large numbers of 
                        * data points. It is still possible to 'Jimmy' it, 
                        * however, so I will keep this huge comment block here.
                        */
                        sumNumberRollsToTotal += 
                                Dice.countDiceRollsUntil(total, dice);
                    }
                } catch (IllegalArgumentException e) {  // total outside range
                    sumNumberRollsToTotal = 0;
                }
                avgRollsToTotal[total-1][dice-1] =
                        sumNumberRollsToTotal / (double)dataPoints;
            }
            
            System.out.printf(tableRowFormat(), 
                    total,
                    avgRollsToTotal[total-1][0],
                    avgRollsToTotal[total-1][1],
                    avgRollsToTotal[total-1][2],
                    avgRollsToTotal[total-1][3],
                    avgRollsToTotal[total-1][4]);
        }
        
//        System.out.print(tableRowBlank());
        System.out.print(tableRowLine());
        
        
        
        
        
        
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
     * |     Average Number of Rolls Taken To Get Total     |
     * | Total |--------------------------------------------|
     * |  on   |               Number of Dice               |
     * | Dice  |   1    |   2    |   3    |   4    |   5    |
     * |-------|--------|--------|--------|--------|--------|
     * |       |        |        |        |        |        |
     * |  001  |    5.9 |    0.0 |    0.0 |    0.0 |    0.0 |
     * |       |        |        |        |        |        |
     * |  002  |    6.1 |   35.5 |    0.0 |    0.0 |    0.0 |
     * |       |        |        |        |        |        |
     * |  XXX  | XXXX.X | XXXX.X | XXXX.X | XXXX.X | XXXX.X |
     * |  ...  |        |        |        |        |        |
     * 
     * @return 
     */
    static String tableHeader()
    {
        String s = "";
        
        s += "|----------------------------------------------------|" + "\n";
        s += "|     Average Number of Rolls Taken To Get Total     |" + "\n";
        s += "| Total |--------------------------------------------|" + "\n";
        s += "|  on   |               Number of Dice               |" + "\n";
        s += "| Dice  |   1    |   2    |   3    |   4    |   5    |" + "\n";
        s += tableRowLine();
//        s += tableRowBlank();
        
        return s;
    }
    
    /**
     * Prints table row with gaps filled with dashes, making a line.
     * Primarily for table header and footer.
     * 
     * |       |        |        |        |        |        |
     * |  ...  |   ...  |   ...  |   ...  |   ...  |   ...  |
     * |       |        |        |        |        |        |
     * |  XXX  | XXXX.X | XXXX.X | XXXX.X | XXXX.X | XXXX.X |
     * |       |        |        |        |        |        |
     * |-------|--------|--------|--------|--------|--------|
     * 
     * @return 
     */
    static String tableRowLine()
    {
        return "|-------|--------|--------|--------|--------|--------|" + "\n";
    }
    
    /**
     * Provides String, a table row to space out values. Appended with newline.
     * @return String, \n at end.
     */
    static String tableRowBlank()
    {   
        return "|       |        |        |        |        |        |" + "\n";
    }
    
    /**
     * Provides formatting string for printf() command. Appended with newline.
     * Arguments referenced:    1   %02d    total to get
     *                          2   %6.1f   average number of rolls with 1 die
     *                          3   %6.1f   average number of rolls with 2 dice
     *                          ...         ...
     *                          6   %6.1f   average number of rolls with 5 dice
     * 
     * @return String with PrintStream (etc) formatting code, %n at end.
     */
    static String tableRowFormat()
    {
        //      |  XXX  | XXXX.X | XXXX.X | XXXX.X | XXXX.X | XXXX.X |
        return "|  %03d  | %6.1f | %6.1f | %6.1f | %6.1f | %6.1f |%n";
    }
    
    
}
