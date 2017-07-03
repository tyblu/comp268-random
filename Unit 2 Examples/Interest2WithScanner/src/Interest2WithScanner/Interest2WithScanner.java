package Interest2WithScanner;

/**
 *             Textbook Example Program
 * Class:      Interest2WithScanner.java
 * Purpose:    Computes interest earned on an investment over time. Initial 
 *             amount and interest rate input by the user. This uses Scanner
 *             instead of TextIO for user input, and includes improvements as in
 *             Interest.java v1.1.
 *
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       April 17, 2017
 * Version     1.0
 * 
 * Based on:   Eck, pp 46-47
 */

import java.util.Scanner;

public class Interest2WithScanner {

    public static void main(String[] args) {
        
        Scanner stdin = new Scanner( System.in );       // Create the Scanner.
        
        double principal;       // initial investment value
        double rate;            // annual interest rate, decimal
        double t;               // Accrual time, in years.
        double n;               // Number of compound periods per year.
        double interest;        // interest earned during the year
        
        System.out.print("Starting investment value/principal: $");
        principal = stdin.nextDouble();
        System.out.printf("The starting principal has been set to $%1.2f%n%n", 
                principal);
        
        System.out.print("Annual interest rate [%]: ");
        rate = stdin.nextDouble()/100;
        System.out.printf("The annual interest rate has been set to "
                + "%1.1f%%%n%n", rate*100);
        
        System.out.print("Accrual time, in years: ");
        t = stdin.nextDouble();
        System.out.printf("The accrual time has been set to %1.1f years%n%n", t);
        
        System.out.print("Number of compound periods per year: ");
        n = stdin.nextDouble();
        System.out.printf("The number of compound periods per year has been "
                + "set to %1.0f%n%n", n);
        
        interest = principal * ( Math.pow(1 + rate/n, n*t) - 1 );
        
        System.out.printf("%nThe interest earned on $%1.2f initial principal "
                + "with %1.1f%% annual interest over %1.1f years with "
                + "%1.0f compounding periods per year is $%1.2f, for a final "
                + "investment value of $%1.2f.%n", principal, rate*100, t, n, 
                interest, principal+interest);
    }
}