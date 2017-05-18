/**
 *              Textbook Example Program
 * Class:       MathSequences.java
 * Purpose:     Gets user input to test subroutine print3NSequence().
 * 
 *      v1.1    Incorporated improvements from ThreeN1.java.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 18, 2017
 * Version     1.1
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
            
            while ( K < 0 ) {
                System.out.println("\tPositive integers only. Please try again.");
                System.out.print("> ");
                
                K = TextIO.getlnInt();
            }

            if ( K != 0 )
                print3NSequence(K);
        } while ( K != 0 );
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
        String strCurrentInteger;
        String strSequence;
        
        N = startingValue;
        count = 1;
        strCurrentInteger = Integer.toString(N);
        strSequence = strCurrentInteger;
        
        System.out.println("The 3N+1 sequence starting from " + N);
        System.out.println();
        
        while ( N > 1 ) {
            
            if ( N % 2 == 1 )
                N = 3 * N + 1;
            else
                N = N / 2;
            
            strCurrentInteger = Integer.toString(N);
            
            // Comma between numbers.
            strSequence += ", ";
            
            // Line break just before 80 characters.
            // Check if there has been a line break yet, first.
            if ( strSequence.indexOf("\n") > 0 ) {  // Has there been a \n?
                if ( strSequence.substring( strSequence.lastIndexOf("\n") ).length() 
                        + strCurrentInteger.length() + 1 > 80 ) { // Will be over 80 char?
                    strSequence += "\n";
                }
            }
            else {  // First line only. Check length directly. Include "," char (1).
                if ( strSequence.length() + strCurrentInteger.length() + 1 > 80 ) {
                    strSequence += "\n";
                }
            }
            
            strSequence += strCurrentInteger;
            
            count++;
        }
        
        System.out.println(strSequence);
        System.out.println();
        System.out.println("There were " + count + " terms in the sequence.");
    }
}
