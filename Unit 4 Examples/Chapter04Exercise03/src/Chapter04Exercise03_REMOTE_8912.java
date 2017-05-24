/**
 *              Textbook Exercise Program
 * Class:       Chapter04Exercise03.java
 * Purpose:     
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 23, 2017
 * Version     1.0
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014, p183: chapter 4, exercise 3
 * 
 */

public class Chapter04Exercise03
{
    public static void main(String[] args)
    {
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
}
