/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package numberprecision;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Random;

/**
 *
 * @author Tyler Lucas <tyblu@live.com>
 */
public class NumberPrecision 
{
    public static void main(String[] args) 
    {
        /* Non-Exact Number Representations. */
        String[] numbers = new String[]
        {
            "0.01",
            "0.3333333333333333333333333333333333333333",
            "0.6666666666666666666666666666666666666666",
            "9007199254740991",     // 2^53 - 1
            "9007199254740992",     // 2^53
            "9007199254740993",     // 2^53 + 1
            "1.0000001",
            "1.00000000000000149",
            "0.9999999",
            "0.99999999",
            "0.9999999999999999",
            "0.99999999999999999"
        };
        
        for (String number : numbers)
        {
            float nFloat = Float.parseFloat(number);
            double nDouble = Double.parseDouble(number);
            BigDecimal nBigDecimal = new BigDecimal(number);
            
            System.out.printf("  exact: %s%n", nBigDecimal);
            System.out.printf("  float: %.30f%n", nFloat);
            System.out.printf(" double: %.30f%n", nDouble);
            System.out.println();
        }
        
        /* Error Propagation. */
        String r = "0.011";                     // interest per annum.
        int periodsPerYear = 365;               // periods per year
        int numPeriods = 50 * periodsPerYear;   // total number periods
        int numDailyCredits = 1000;
        
        BigDecimal amountBigDecimal = BigDecimal.ZERO;
        BigDecimal rBigDecimal = new BigDecimal(r);
        BigDecimal ppyBigDecimal = new BigDecimal(Integer.toString(periodsPerYear));
        MathContext d128 = new MathContext(100, RoundingMode.HALF_EVEN);
        
        double amountDouble = (double)0;
        double rDouble = Double.parseDouble(r);
        double ppyDouble = (double)periodsPerYear;
        
        float amountFloat = (float)0;
        float rFloat = Float.parseFloat(r);
        float ppyFloat = (float)periodsPerYear;
        
        Random random = new Random();
        
        for (int n = 0; n < numPeriods; n++)
        {
            String monthly = String.format("%.40f", 
                    (double)20 + random.nextFloat() * (double)300);
            BigDecimal monthlyBigDecimal = new BigDecimal(monthly);
            double monthlyDouble = Double.parseDouble(monthly);
            float monthlyFloat = Float.parseFloat(monthly);
            
            for (int i = 0; i < numDailyCredits; i++)
            {
                monthly = String.format("%.40f", 
                        (double)20 + random.nextFloat() * (double)300);
                monthlyBigDecimal = monthlyBigDecimal
                        .add(new BigDecimal(monthly))
                        .add(new BigDecimal(monthly)
                                .multiply(rBigDecimal
                                        .divide(ppyBigDecimal, d128)
                                        .add(BigDecimal.ONE), d128), d128);
                monthlyDouble += Double.parseDouble(monthly)
                        + Double.parseDouble(monthly) * ((double)1 + rDouble / ppyDouble);
                monthlyFloat += Float.parseFloat(monthly)
                        + Float.parseFloat(monthly) *((float)1 + rFloat / ppyFloat);
            }
            
            amountBigDecimal = monthlyBigDecimal
                    .add(amountBigDecimal
                    .multiply(rBigDecimal
                            .divide(ppyBigDecimal, d128)
                            .add(BigDecimal.ONE), d128), d128);
            
            amountDouble = monthlyDouble 
                    + amountDouble * ((double)1 + rDouble / ppyDouble);
            
            amountFloat = monthlyFloat
                    + amountFloat * ((float)1 + rFloat / ppyFloat);
        }
        
        BigDecimal errFloat = amountBigDecimal
                .subtract(new BigDecimal(String.format("%.40f", amountFloat)), d128);
        BigDecimal errDouble = amountBigDecimal
                .subtract(new BigDecimal(String.format("%.40f", amountDouble)), d128);
        
        NumberFormat f = NumberFormat.getCurrencyInstance();
        
        System.out.println("Money lost due to error accumulation.");        
        System.out.println("Showing effects on " + numDailyCredits + " daily "
                + "credits with " + rBigDecimal.multiply(new BigDecimal("100"))
                + "%/annum. interest, compounded daily for 50 years.");
        System.out.printf("Difference using doubles: %s%n", f.format(errDouble));
        System.out.printf("Difference using floats:  %s%n", f.format(errFloat));
    }
}
