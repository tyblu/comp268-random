/**
 *             Textbook Example Program
 * Class:      Interest.java
 * Purpose:    Compute interest earned.
 *
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       March 10, 2017
 * Version     1.0
 * 
 * Based on:   Eck, pp 28-29
 */

/**
 * degC    - degrees Celsius
 * degF    - degrees Fahrenheit
 */
public class Interest {

    public static void main(String[] args) {
        
        double principal;   // Value of investment.
        double rate;        // Annual interest rate.
        double t;           // Accrual time, in years.
        double n;           // Number of compound periods per year.
        double interest;    // Interest earned. future value = P*(1+r/n)^(n*t)
        
        principal = 17_000;
        rate = 0.027;
        t = 1;
        n = 12;
        interest = principal * ( Math.pow(1 + rate/n, n*t) - 1 );
    }
    
}
