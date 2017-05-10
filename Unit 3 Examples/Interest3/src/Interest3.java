/**
 *             Textbook Example Program
 * Class:      Interest3.java
 * Purpose:    Computes interest earned on an investment over time. Initial 
 *             amount and interest rate input by the user. The value of the
 *             investment at the end of each year is output. This improves on 
 *             the book's example in the same way as Interest.java v1.1.
 * 
 *             
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 10, 2017
 * Version     1.0
 * 
 * Based on:   Eck, p 70
 * 
 * References: https://stackoverflow.com/questions/10530102/java-parse-string-and-add-line-break-every-100-characters
 * 
 */

public class Interest3 {

    public static void main(String[] args) {
        
        double principal;       // initial investment value
        double rate;            // annual interest rate, decimal
        double t;               // Accrual time, in years.
        double n;               // Number of compound periods per year.
        double interest;        // interest earned during the year
        
        
        /* Get the initial investment and compounding details from user. */
        
        System.out.print( "Starting investment value/principal: $" );
        principal = TextIO.getlnDouble();
        System.out.printf( "The starting principal has been set to $%1.2f%n%n", 
                principal );
        
        System.out.print( "Annual interest rate [%]: " );
        rate = TextIO.getlnDouble()/100;
        System.out.printf( "The annual interest rate has been set to "
                + "%1.2f%%%n%n", rate*100 );
        
        System.out.print("Accrual time, in years: ");
        t = TextIO.getlnDouble();
        System.out.printf( "The accrual time has been set to %1.0f years%n%n", t );
        
        System.out.print( "Number of compound periods per year: " );
        n = TextIO.getlnDouble();
        System.out.printf( "The number of compound periods per year has been "
                + "set to %1.0f%n%n", n );
        
        System.out.printf( "The interest earned on $%1.2f initial"
                + " investment with %1.2f%% annual interest over %1.0f years"
                + " with %1.0f compounding periods per year is:",
                principal, rate*100, t, n );
        
//        // Adds line break every 80 characters. This breaks words, too.
//        String temporaryString;
//
//        temporaryString = String.format( "The interest earned on $%1.2f initial"
//                + " investment with %1.1f%% annual interest over %1.1f years"
//                + " with %1.0f compounding periods per year is:",
//                principal, rate*100, t, n );
//
//        temporaryString = temporaryString.replaceAll( "(.{80})", "$1\n");   // Ref#1
//
//        System.out.print( temporaryString );
        
        // Initialize outside of loop.
        // Total investment value for current (loop) year.
        double principal_current = principal;
        
        // Interest for current (loop) year.
        double interest_current;
        
        // Annual interest rate adjusted for compounding.
        double rate_compounded = Math.pow( 1 + rate/n, n ) - 1;
        
        int year = 1;
        while ( year <= t ) {
            
            interest_current = principal_current * rate_compounded;
            principal_current += interest_current;
            
            String s_year;             // Year in string form, 1st, 2nd, etc.
            
            s_year = Integer.toString( year );      // Convert double to String.
            
            switch ( year % 10 ) {                  // Add lexical garbage.
            case 1:
                s_year += "st";
                break;
            case 2:
                s_year += "nd";
                break;
            case 3:
                s_year += "rd";
                break;
            default:
                s_year += "th";
                break;
            }

//        // debugging
//            System.out.printf( "%n%t" );  // %t flag does not exist, it's \t
//            System.out.printf( "* $1.2f", interest_current );
//            System.out.printf( " in the " );
//            System.out.printf( "%s", s_year );
//            System.out.printf( " year ($" );
//            System.out.printf( "%1.2f", principal_current );
//            System.out.printf( " total)," );
//        // debugging

            System.out.printf( "%n\t* $%1.2f in the %s year ($%1.2f total),",
                    interest_current, s_year, principal_current);
            
            // 2nd last entry grammar
            if ( year == t-1 ) { System.out.print(" and"); }
                    
            year++;
        }
        
        System.out.printf( "%n for a final investment value of $%1.2f with"
                + " $%1.2f interest earned after %1.0f years.%n",
                principal_current,
                principal_current - principal,
                t );
    }
}
