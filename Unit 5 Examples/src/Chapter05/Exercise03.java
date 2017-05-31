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
 * Purpose:     Lists the average number of rolls it takes for a pair of dice
 *              to get a given total, plus a number of related statistics.
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
    
    private static final int DICE_SIDES = 6;
    private static final int NUMBER_OF_DICE = 2;
    private static final int TOTAL_MIN = NUMBER_OF_DICE * 1;
    private static final int TOTAL_MAX = NUMBER_OF_DICE * DICE_SIDES;
    private static final int NUMBER_OF_TOTALS = TOTAL_MAX - TOTAL_MIN;
    
    private static final int DATAPOINTS = 10000; // Set < (2^31-1)/6^2=59652323
    
    public static void callExercise03()
    {
        System.out.println(intro());
        System.out.println();
        
        /* Collect data and fill table. */
        System.out.print(tableHeader());
        for (int total=TOTAL_MIN; total<=TOTAL_MAX; total++)
        {
            StatCalc totalData = new StatCalc();
            PairOfDice dice = new PairOfDice();
            
//            System.out.println(); // blank line between table elements
            
            for (int i=0; i<DATAPOINTS; i++)
            {
                dice.setRollCount(0);  // Reset rollCount=0.
                
                do {
                    dice.roll();
                } while(dice.getTotal() != total);
                
                totalData.enter(dice.getRollCount());
            }
            
            // Print row
            System.out.printf(tableRowFormat(),
                    total,
                    totalData.getMean(),
                    (int)totalData.getMin(),
                    (int)totalData.getMax(),
                    totalData.getStandardDeviation());
        }
    }
    
    /**
     * Provides introductory spiel. Less than 80-char wide, no newline at end.
     * @return String of introductory text. Less than 80-char wide, no newline
     *          at the very end.
     */
    private static String intro()
    {
        String s = "";
        
        s += "Let\'s roll a pair of dice an unnecessarily" + "\n";
        s += "gargantuan number of times in order to" + "\n";
        s += "figure out, on average, how many rolls" + "\n";
        s += "it takes to get a certain total." + "\n";
        
        return s;
    }

    /**
     * Prints table header. Newline terminated, so println() gives 2 blank lines.
     * 
     *   Total   Average    Minimum     Maximum
     *    on      Number     Number      Number    Standard
     *   Dice    of Rolls   of Rolls   of Rolls   Deviation
     *   -----   --------   --------   --------   ---------
     *
     *     02       5.90         1       9528     -5829.92
     *
     *     03       6.10        35       XXXX     -XXXX.XX
     *
     *     XX    XXXX.XX      XXXX       XXXX     -XXXX.XX
     *    ...
     * 
     * @return String of table header. Newline terminated.
     */
    private static String tableHeader()
    {
        String s = "";
        
        s += "   Total   Average    Minimum     Maximum" + "\n";
        s += "    on      Number     Number      Number    Standard" + "\n";
        s += "   Dice    of Rolls   of Rolls   of Rolls   Deviation" + "\n";
        s += "   -----   --------   --------   --------   ---------" + "\n";
        
        return s;
    }
    
    /**
     * Provides formatting string for printf() command. Appended with newline.
     * Arguments referenced:    1   %02d    total to get
     *                          2   %7.2f   average number of rolls for total
     *                          3   %4d     minimum number of rolls for total
     *                          4   %4d     maximum number of rolls for total
     *                          5   %+8.2f  standard deviation of rolls for total
     * 
     * @return String with PrintStream (etc) formatting code, %n at end.
     */
    private static String tableRowFormat()
    {
        //     *     XX    XXXX.XX      XXXX       XXXX     -XXXX.XX
        return "     %02d    %7.2f      %4d       %4d     %7.2f%n";
    }
    
    
}
