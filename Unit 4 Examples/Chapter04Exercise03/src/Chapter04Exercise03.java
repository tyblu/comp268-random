/**
 *              Textbook Exercise Program
 * Class:       Chapter04Exercise03.java
 * Purpose:     
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 23, 2017
 * Version     1.1
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014, p183: chapter 4, exercise 3
 * 
 */

// All imports for debug/test/validation only
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class Chapter04Exercise03
{
    private static final boolean TEST_MODE_ENABLE = true;
    
    public static void main(String[] args)
    {
        // Debugging, testing, validating
        if (TEST_MODE_ENABLE) { test(); System.exit(0); }
        
        // Introduction.
        {
            String strIntro = "Welcome to the magical dice rolling game!";
            strIntro += "\nLet\'s roll a pair until the total we\'re ";
            strIntro += "looking for comes up.";
            strIntro += "\n";
            strIntro += "\nWhat total are you looking for? Enter 2-12: ";
            System.out.print(strIntro);
        }
        
        // Get user to enter dice total to look for.
        int userTotal = getIntInRange(2,12);
        
        System.out.println();
        System.out.println("Rolling...");
        
        // Count dice rolls until total is found.
        // try..catch not req'd, as input already sanitized by getIntInRange()
        int rollsCounted = countDiceRollsUntil(userTotal, 2);
        
        // Conclusion.
        {
            String strConc = "It took " + rollsCounted + " rolls to come up ";
            strConc += "with a sum total value of " + userTotal + ".";
            System.out.println(strConc);
        }
    }
    
    /**
     * Gets an integer input from standard input using TextIO in a certain
     * range, repeating the request for proper input until it is provided.
     * 
     * Preconditions:   Standard input and output are properly configured.
     *                  Expects user input from standard input.
     * 
     * Postconditions:  Return value is between n1 and n2, inclusive. Will print
     *                  lines to standard output if user enters an input that is
     *                  not an integer in the requested range.
     * 
     * @param n1    Inclusive range limit. Can be more, less, or equal to n2.
     * @param n2    Inclusive range limit. Can be more, less, or equal to n1.
     * @return      Integer between (and including) n1 and n2.
     */
    static int getIntInRange(int n1, int n2)
    {
        int n = TextIO.getlnInt();
        
        // Repeat until n is within range or user enters bad input 3 times
        while (!( n >= Math.min(n1, n2) && n <= Math.max(n1, n2) ))
        {
            System.out.print(" Please enter an integer between "
                    + Math.min(n1, n2) + " and " + Math.max(n1, n2) + ": ");
            n = TextIO.getlnInt();
        }
        
        return n;
    }
    
    /**
     * Simulates a dice roll, returning a random number from 1 to 6.
     * @return Random integer (int) from 1 to 6, inclusive.
     */
    static int getDiceRoll()
    {
        return (int)( Math.random()*6 + 1 );
    }
    
    /**
     * Simulates rolling several dice, returning the sum of their values.
     * 
     * @param m Number of dice to roll at once. Minimum 1.
     * @return Integer (int) from m to m*6, inclusive, where m is the number of
     *          dice.
     * @throws  IllegalArgumentException if m is less than 1.
     */
    static int getDiceRollSum(int m) throws IllegalArgumentException
    {
        // Exceptions
        if ( m < 1 )
        {
            String s = "Number of dice must be greater than 1. Number given: ";
            s += m;
            throw new IllegalArgumentException(s);
        }
        
        int sum = 0;
        for (int i=0; i<m; i++)
        {
            sum += getDiceRoll();
        }
        
        return sum;
    }
    
    /**
     * Counts the number of rolls taken to achieve a certain total, using
     * several dice.
     * 
     * @param total Sum total of dice values at which to stop counting rolls.
     *              Should be in the range 1*dice to 6*dice, inclusive.
     * @param dice  Number of dice. Minimum 1; can use
     *      {@code countDiceRollsUntil(total)} in this case, as well.
     * @return      Number of rolls taken to achieve a certain total.
     * @throws  IllegalArgumentException if {@code total} is not in the range of
     *              possible dice totals: 1*dice to 6*dice, inclusive.
     * @throws  IllegalArgumentException if {@code dice} is less than 1.
     */
    static int countDiceRollsUntil(int total, int dice)
            throws IllegalArgumentException
    {
        // Exceptions
        if ( dice < 1 )
        {
            String s = "Number of dice must be at least 1. Number given: ";
            s += dice;
            throw new IllegalArgumentException(s);
        }
        
        if ( total < 1*dice || total > 6*dice )
        {
            String s = "Sum total value must be possible with " + dice;
            s += " dice, from " + (1*dice) + " to " + (6*dice);
            s += ", inclusive.";
            s += "\nTotal given: " + total;
            throw new IllegalArgumentException(s);
        }
        
        int count = 0;
        do {
            count++;
        } while ( getDiceRollSum(dice) != total );
        
        return count;
    }
    
    /**
     * Counts the number of rolls taken to achieve a certain value. Also acts
     * to set the default dice value of 
     * {@code countDiceRollsUntil(int,int)} to 1.
     * 
     * @param value Must be between 1 and 6, inclusive.
     * @return      Number of rolls taken to achieve {@code value}. Will be a 
     *              minimum of 1.
     * @throws IllegalArgumentException if {@code value} is not between 1 and 6,
     *              inclusive.
     */
    static int countDiceRollsUntil(int value) throws IllegalArgumentException
    {
        // Exceptions
        if ( value < 1 )
        {
            String s = "Value must be from 1 to 6, inclusive.";
            s += "\nValue given: " + value;
            throw new IllegalArgumentException(s);
        }
        
        return countDiceRollsUntil(value, 1);
    }
    
    static void test()
    {
        System.out.println("Debugging, Testing, and Verification Mode Enabled");
        System.out.println();
        
        // Test parameters
        int dataPoints = 10000;
        
        // Create test file
        String strDateTime = new SimpleDateFormat("yyyyMMddHHmm")
                .format(Calendar.getInstance().getTime());
        File dataFile = new File("testdata" + strDateTime + ".txt");
        // Write intro
        String strDateTimeNice = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
                .format(Calendar.getInstance().getTime());
        String s = "Test Data for Chapter 4 Exercise 3 Solution Program "
                + "(Class) on " +  strDateTimeNice + "\n";
        s = appendLineAndClear(dataFile, s + "\n");
        
        // static int getIntInRange(int n1, int n2)
        System.out.print(".");
        // Requires user input. Skip for now.
        
        // static int getDiceRoll()
        System.out.print(".");
        /*  Input: none
        *   Expected output: Gaussian random distribution from 1-6
        */
        int[] data_getDiceRoll = new int[dataPoints];
        for (int i=0; i<dataPoints; i++)
        {
            data_getDiceRoll[i] = getDiceRoll();
            s += data_getDiceRoll[i] + ",";
        }
        s = appendLineAndClear(dataFile, s + "\n");
        
        // static int getDiceRollSum(int m) throws IllegalArgumentException
        System.out.print(".");
        /*  Input: 1,2,3,4,5,10,20,50,100+/-1,1000+/-1
        *   Expected output: Random distribution with averages at N*3.5
        */
        int[] inputs_getDiceRollSum = new int[]{
            1,2,3,4,5,10};
        int[][] data_getDiceRollSum = 
                new int[inputs_getDiceRollSum.length][dataPoints];
        for (int j=0; j<inputs_getDiceRollSum.length; j++)
        {
            for (int i=0; i<dataPoints; i++)
            {
                data_getDiceRollSum[j][i] = 
                        getDiceRollSum(inputs_getDiceRollSum[j]);
                s += data_getDiceRollSum[j][i] + ",";
            }
            s += "\n";
        }
        s = appendLineAndClear(dataFile, s + "\n");
        
        // static int countDiceRollsUntil(int total, int dice) throws IllegalArgumentException
        System.out.print(".");
        /*  Input: dice 1,2,3,4,5,10,20,50,100+/-1,1000+/-1, totals N*1,2,3,4,5,6
        *   Expected output: Complicated probabilities (averages) -- use Excel.
        */
        int[] inputs_countDiceRollsUntil_dice = new int[]{
            1,2,3,4,5};
        int[] inputs_countDiceRollUntil_total = new int[]{1,2,3,4,5,6};
        int[][][] data_countDiceRollsUntil_1 = 
                new int[6][inputs_countDiceRollsUntil_dice.length][dataPoints];
        for (int k=0; k<inputs_countDiceRollUntil_total.length; k++)
        {
            for (int j=0; j<inputs_countDiceRollsUntil_dice.length; j++)
            {
                for (int i=0; i<dataPoints; i++)
                {
                    int total = inputs_countDiceRollUntil_total[k] 
                            * inputs_countDiceRollsUntil_dice[j];
                    data_countDiceRollsUntil_1[k][j][i] =
                            countDiceRollsUntil(total,
                                    inputs_countDiceRollsUntil_dice[j]
                            );
                    s += data_countDiceRollsUntil_1[k][j][i] + ",";
                }
                s += "\n";
            }
            s += "\n";
        }
        s = appendLineAndClear(dataFile, s + "\n");
        
        // static int countDiceRollsUntil(int value) throws IllegalArgumentException
        System.out.print(".");
        /* Should be the same as countDiceRollsUntil(int total, int dice=1)
        *   Input: 1,2,3,4,5,6
        *   Expected output: Random distribution (flat) with average 6.
        */
//        int[] inputs_countDiceRollUntil_total = new int[]{1,2,3,4,5,6}; // already initialized
        int[][] data_countDiceRollsUntil_2 = 
                new int[6][dataPoints];
        for (int k=0; k<inputs_countDiceRollUntil_total.length; k++)
        {
            for (int i=0; i<dataPoints; i++)
            {
                data_countDiceRollsUntil_2[k][i] = 
                        countDiceRollsUntil(inputs_countDiceRollUntil_total[k]);
                s += data_countDiceRollsUntil_2[k][i] + ",";
            }
            s += "\n";
        }
        s = appendLineAndClear(dataFile, s + "\n");
        
        System.out.println();
        System.out.println("Done.");
    }
    
    static void writeToFile( File f, String s )
    {
        // Automatically closes (java.io.Closeable)
        try ( BufferedWriter writer = 
                new BufferedWriter(new FileWriter(f, true)) )
        {
            writer.write(s);
        } catch (IOException e) {
            System.out.println("File exception: " + e);
        }
        
//        BufferedWriter writer = new BufferedWriter(new FileWriter(f, true));
//        writer.write(s);
//        writer.close();
    }
    
    static String appendLineAndClear( File f, String s )
    {
        s += "\n";
        writeToFile(f,s);
        return "";
    }

}
