/**
 *              Textbook Example Program
 * Class:       MathSequences.java
 * Purpose:     Gets user input to test subroutine print3NSequence().
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 18, 2017
 * Version     1.0 (Nearly identical to textbook implementation.)
 * 
 * Based on:   Eck pp 146-147
 *             ThreeN1.java (Eck pp 77-80, and my own changes)
 * 
 * References:
 * 
 */

public class MathSequences {

    public static void main(String[] args) {
        System.out.println("This program will print out 3N+1 sequences");
        System.out.println("for starting values that you specify.");
        System.out.println();
        
        int K;
        
        do {
            System.out.println("Enter a starting value.");
            System.out.println("To end the program, enter 0.");
            System.out.print("> ");
            
            K = TextIO.getlnInt();
            
            if ( K > 0 )
                print3NSequence(K);
        } while ( K > 0 );
    }
    
    /**
     * This subroutine prints a 3N+1 sequence to standard output, using
     * startingValue as the initial value of N. It also prints the number of
     * terms in the sequence. The value of the parameter, startingValue, must
     * be a positive integer.
     */
    static void print3NSequence( int startingValue ) {
        
        int N;
        int count;
        
        N = startingValue;
        count = 1;
        
        System.out.println("The 3N+1 sequence starting from " + N);
        System.out.println();
        System.out.println(N);
        
        while ( N > 1 ) {
            
            if ( N % 2 == 1 )
                N = 3 * N + 1;
            else
                N = N / 2;
            
            count++;
            
            System.out.println(N);
        }
        
        System.out.println();
        System.out.println("There were " + count + " terms in the sequence.");
    }
}
