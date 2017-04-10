/**
 *             Textbook Example Program
 * Class:      Interest.java
 * Purpose:    Compute interest earned and final investment value.
 *
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       April 1, 2017
 * Version     1.2
 * 
 * Based on:   Eck, pp 28-29
 *             https://stackoverflow.com/questions/2496239/how-do-i-keep-a-scanner-from-throwing-exceptions-when-the-wrong-type-is-entered
 */

import java.util.Scanner;

public class Interest {

    public static void main(String[] args) {
        
        double principal = 0;   // Value of investment.
        double rate = 0;        // Annual interest rate.
        double t;           // Accrual time, in years.
        double n;           // Number of compound periods per year.
        double interest;    // Interest earned. future value = P*(1+r/n)^(n*t)
        
        Scanner sc = new Scanner( System.in );
        
        System.out.print("\nStarting investment value/principal: $");
        while( !sc.hasNextDouble() ){ sc.next(); }
        principal = sc.nextDouble();
        sc.nextLine();  // what is this line for?
        System.out.println("The starting principal has been set to $"
                + principal);
        System.out.println();
        
        System.out.print("Annual interest rate [%]: ");
        while( !sc.hasNextDouble() ){ sc.next(); }
        rate = sc.nextDouble()/100;
        sc.nextLine();  // what is this line for?
        System.out.println("The annual interest rate has been set to "
                + rate*100 + "%");
        System.out.println();
        
        System.out.print("Accrual time, in years: ");
        while( !sc.hasNextDouble() ){ sc.next(); }
        t = sc.nextDouble();
        sc.nextLine();  // what is this line for?
        System.out.println("The accrual time has been set to "
                + t + " years");
        System.out.println();
        
        System.out.print("Number of compound periods per year: ");
        while( !sc.hasNextDouble() ){ sc.next(); }
        n = sc.nextDouble();
        sc.nextLine();  // what is this line for?
        System.out.println("The number of compound periods per year has been "
                + "set to " + n);
        System.out.println();
        
        interest = principal * ( Math.pow(1 + rate/n, n*t) - 1 );
        
        System.out.println("\nThe interest earned on $" + principal + " initial "
                + "principal with " + rate*100 + "% annual interest over " + t
                + " years with " + n + " compounding periods per year is $"
                + interest + ", for a final investment value of $"
                + ( principal + interest ) + ".");
    }
}