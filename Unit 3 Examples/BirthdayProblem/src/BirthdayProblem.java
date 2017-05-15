/**
 *              Textbook Example Program
 * Class:       BirthdayProblem.java
 * Purpose:     Simulate choosing people at random and checking the day of the
 *              year they were born on. If the birthday is the same as one that
 *              as seen previously, top, and output the number of people who
 *              were checked.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 15, 2017
 * Version     1.0 (Nearly identical to textbook implementation.)
 * 
 * Based on:   Eck, pp 118
 * 
 * References:
 * 
 */

public class BirthdayProblem {

    public static void main(String[] args) {
        
        boolean[] used; /* For recording the possible birthdays that have been
                         * seen so far. A value of true in used [i] means that a
                         * person whose birthday is the i-th day of the year has
                         * been found.
                         */
        int count;      // Number of people who have been checked.
        
        used = new boolean[365];    // All entries default to false.
        
        /* Select a birthday at random, from 0 to 364. If the birthday has
         * already been used, quit. Otherwise, record the birthday as used.
         */
        count = 0;
        while ( true ) {
            
            int birthday;   // Selected birthday.
            birthday = (int)( Math.random() * 365 );
            
            count++;
            
            System.out.printf( "Person %2d has birthday number %3d%n", count, birthday );
            
            if ( used[birthday] ) {
                // This day was found before -- it's a duplicate. We are done.
                break;
            }
            
            used[birthday] = true;
        }
        
        System.out.printf( "%nA duplicate birthday was found after %d tries.%n", count );
    }
}
