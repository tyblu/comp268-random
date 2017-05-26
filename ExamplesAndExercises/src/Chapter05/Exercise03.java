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
 *              Textbook Chapter 5 Exercise 3
 * Class:       Exercise03.java
 * Purpose:     Lists the average number of rolls it takes to get a given total,
 *              plus a number of related statistics.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 26, 2017
 * Version     1.0
 * 
 * Based on and References:
 * @see Chapter04Exercise04.java
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014: Chapter 5, Exercise 3
 */
public class Exercise03 {
    
    public static void callExercise03()
    {
        System.out.println(intro());
        System.out.println();
        
        int dataPoints = 10000; // Number of repetitions. Set < (2^31-1)/6^dice
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
                        /*
                        * 6^dice * dataPoints, the maximum return value of
                        * countDiceRollsUntil(int,int), will probably never 
                        * overflow int due to processing limitations on dice and
                        * statistical suitability of large numbers of 
                        * data points. It is still possible to 'Jimmy' it, 
                        * however, so I will keep this huge comment block here.
                        * 
                        * Set dataPoints < (2^31-1)/(6^dice), ~276k for 5 dice.
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
        
        System.out.print(tableRowLine());
    }
    
    /**
     * Provides introductory spiel. Less than 80-char wide, no newline at end.
     * @return String of introductory text. Less than 80-char wide, no newline
     *          at the very end.
     */
    private static String intro()
    {
        String s = "";
        s += "Let\'s roll some dice an unnecessarily" + "\n";
        s += "gargantuan number of times in order to" + "\n";
        s += "figure out, on average, how many rolls" + "\n";
        s += "it takes a given number of dice to get" + "\n";
        s += "a certain total.";
        
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
    private static String tableHeader()
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
    private static String tableRowLine()
    {
        return "|-------|--------|--------|--------|--------|--------|" + "\n";
    }
    
    /**
     * Provides String, a table row to space out values. Appended with newline.
     * @return String, \n at end.
     */
    private static String tableRowBlank()
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
    private static String tableRowFormat()
    {
        //      |  XXX  | XXXX.X | XXXX.X | XXXX.X | XXXX.X | XXXX.X |
        return "|  %03d  | %6.1f | %6.1f | %6.1f | %6.1f | %6.1f |%n";
    }
}
