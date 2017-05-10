/**
 *             Textbook Example Program
 * Class:      EnumDemo.java
 * Purpose:    Demonstrates enums.
 *
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       April 1, 2017
 * Version     1.0
 * 
 * Based on:   Eck, pp 37
 */
public class EnumDemo {

    enum Day { SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY }
    enum Month { JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC }
    
    public static void main(String[] args) {
        
        Day tgif;       // variable of type Day
        Month libra;    // variable of type Month
        
        tgif = Day.FRIDAY;
        libra = Month.OCT;
        
        System.out.println( "My sign is libra, since I was born in " + libra );
        System.out.println( "That's the " + libra.ordinal() + "-th month of "
            + "the year. (Counting from 0, of course!)" );
        
        System.out.println( "Isn't it nice to get to " + tgif );
        
        System.out.println( tgif + " is the " + tgif.ordinal()
                + "-th day of the week." );
        
    }
    
}
