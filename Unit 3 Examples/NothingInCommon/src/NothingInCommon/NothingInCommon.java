package NothingInCommon;

/**
 *             Textbook Example Program
 * Class:      NothingInCommon.java
 * Purpose:    Experimenting with labeled breaks.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 11, 2017
 * Version     1.0
 * 
 * Based on:   Eck, p 87
 * 
 * References: 
 * 
 */

public class NothingInCommon {

    public static void main(String[] args) {
        
        String s1 = "ABCD";
//        String s2 = "EFGHI";
        String s2 = "DEFGHI";
        
        // Assume s1 and s2 have no chars in common
        boolean nothingInCommon = true;
        
        int i,j;
        i = 0;
        bigloop: while ( i < s1.length() ) {
            j = 0;
            while ( j < s2.length() ) {
                if ( s1.charAt(i) == s2.charAt(j) ) {
                    nothingInCommon = false;
                    break bigloop;
                }
                j++;
            }
            i++;
        }
        
        System.out.print ( '\"' + s1 + '\"');
        System.out.print( " and " );
        System.out.print( '\"' + s2 + '\"' );
        System.out.print( " do " );
        
        if ( nothingInCommon ) { System.out.print( "not " ); }
        
        System.out.print( "have " );
        
        if ( nothingInCommon ) { System.out.print( "anything " ); }
        else { System.out.print( "something " ); }
                
        System.out.println( "in common.");
    }
    
}
