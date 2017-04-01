/**
 *             Textbook Example Program
 * Class:      Interest.java
 * Purpose:    Compute interest earned and final investment value.
 *
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       April 1, 2017
 * Version     1.1
 * 
 * Based on:   Eck, pp 28-29
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
        
        System.out.print("Starting investment value/principal: $");
        
        // no input type error tolerance
//        if (sc.hasNextDouble()) {
//            principal = sc.nextDouble();
//            System.out.println();
//            System.out.print("The starting principal has been set to $");
//            System.out.println(principal);
//        } else { System.out.println("Input error."); }
        
        // better input type error tolerance, searches for double
        // from https://stackoverflow.com/questions/2496239/how-do-i-keep-a-scanner-from-throwing-exceptions-when-the-wrong-type-is-entered
        while( !sc.hasNextDouble() ){ sc.next(); }
        principal = sc.nextDouble();
        sc.nextLine();  // what is this line for?
        System.out.println();
        System.out.print("The starting principal has been set to $");
        System.out.println(principal);
        System.out.println();
        
        System.out.print("Annual interest rate [%]: ");
        
        while( !sc.hasNextDouble() ){ sc.next(); }
        rate = sc.nextDouble()/100;
        sc.nextLine();  // what is this line for?
        System.out.println();
        System.out.print("The annual interest rate has been set to ");
        System.out.print(rate*100);
        System.out.println("%");
        System.out.println();
        
        System.out.print("Accrual time, in years: ");
        
        while( !sc.hasNextDouble() ){ sc.next(); }
        t = sc.nextDouble();
        sc.nextLine();  // what is this line for?
        System.out.println();
        System.out.print("The accrual time has been set to ");
        System.out.print(t);
        System.out.println(" years");
        System.out.println();
        
        System.out.print("Number of compound periods per year: ");
        
        while( !sc.hasNextDouble() ){ sc.next(); }
        n = sc.nextDouble();
        sc.nextLine();  // what is this line for?
        System.out.println();
        System.out.print("The number of compound periods per year has been set to ");
        System.out.println(n);
        System.out.println();
        
        interest = principal * ( Math.pow(1 + rate/n, n*t) - 1 );
        
        System.out.print("The interest earned on $");
        System.out.print(principal);
        System.out.print(" initial principal with ");
        System.out.print(rate*100);
        System.out.print("% interest over ");
        System.out.print(t);
        System.out.print(" years with ");
        System.out.print(n);
        System.out.print(" compounding periods per year is $");
        System.out.print(interest);
        System.out.print(", for a final investment value of $");
        System.out.print(principal+interest);
        System.out.println(".");
        System.out.println();
    }
    
}
