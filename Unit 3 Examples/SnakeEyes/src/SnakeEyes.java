/**
 *              Textbook Exercise Program
 * Class:       SnakeEyes.java
 * Purpose:     Simulates rolling a pair of dice. Stops if both dice roll a ‘1’
 *              and outputs the number of rolls it took.
 * 
 *              Implemented as directed, using either a while loop or a 
 *              do… while loop, chosen in code by flipping the flags 
 *              ENABLE_WHILE and ENABLE_DOWHILE.
 * 
 *              Theoretical probability of snake eyes is 1/36 (~2.8%), meaning
 *              this program will output an average of 36 rolls.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 16, 2017
 * Version     1.0
 * 
 * Based on:    N/A
 * 
 * References:  N/A
 * 
 */

public class SnakeEyes {
    
    private static final boolean ENABLE_WHILE = false;   // while loop
    private static final boolean ENABLE_DOWHILE = true;  // do...while loop

    public static void main(String[] args) {
        
        int roll = 0;
        int[] diceResults = new int[2];
        
        System.out.println( "Welcome to the magical dice roller game!" );
        System.out.println( "Let's try to get snake eyes..." );
        System.out.println();
        
        if ( ENABLE_WHILE ) {
            
            /* Although array values are already initialized to zero by default,
            *   I will 'prime the loop' explicitly.
            */
            diceResults[0] = 7;
            diceResults[1] = 7;
            
            while ( !( diceResults[0] == 1 && diceResults[1] == 1 ) ) {

                diceResults[0] = (int)( Math.random() * 6 ) + 1;
                diceResults[1] = (int)( Math.random() * 6 ) + 1;
                roll++;

                System.out.printf(
                        "Roll %2d:\t%d, %d%n", 
                        roll, 
                        diceResults[0], 
                        diceResults[1] );
            }
        }
        
        if ( ENABLE_DOWHILE ) {
            
            /* Note that this loop does not need to be 'primed'. */
            
            do {
                diceResults[0] = (int)( Math.random() * 6 ) + 1;
                diceResults[1] = (int)( Math.random() * 6 ) + 1;
                roll++;

                System.out.printf(
                        "Roll %2d:\t%d, %d%n", 
                        roll, 
                        diceResults[0], 
                        diceResults[1] );
                
            } while ( !( diceResults[0] == 1 && diceResults[1] == 1 ) );
        }
        
        System.out.println();
        System.out.print( "It took " );
        System.out.print( roll );
        if ( roll > 1 )
            System.out.print( " rolls" );
        else
            System.out.print( " roll" );
        System.out.println( " to get snake eyes." );
    }
}
