/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NumberPrecision;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Tyler Lucas <tyblu@live.com>
 */
public class NumberPrecision 
{
    public static void main(String[] args)
    {
        new NumberPrecision();
    }
    
    public NumberPrecision()
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
        BigDecimal rAcct = new BigDecimal("0.009");     // account interest
        BigDecimal rBank = new BigDecimal("0.005");     // bank interest
        BigDecimal ppy = new BigDecimal("365");         // periods per year
        BigDecimal yrs = new BigDecimal("50");          // years
        BigDecimal numberAccounts = new BigDecimal("100"); // number of accts.
        MathContext mc = MathContext.DECIMAL128;
        Random random = new Random();
        ArrayList<Account> accounts = new ArrayList<>(numberAccounts.intValue());
        for (int i = 0; i < numberAccounts.intValue(); i++)
            accounts.add(new Account());
        Account bank = new Account();
        
        for (int period = 0; period < yrs.multiply(ppy).longValue(); period++)
        {
            for (Account account : accounts)
            {
                if (random.nextFloat() < 0.2)
                {
                    BigDecimal amount = new BigDecimal(String.format("%.2f", 
                            (random.nextDouble() * 72.81 - 72.81 / 2.0001)));

                    if (amount.compareTo(BigDecimal.ZERO) > 0)
                    {
                        account.credit(amount);
                        bank.credit(amount);
                    }
                    else
                    {
                        account.debit(amount.negate());
                        bank.debit(amount.negate());
                    }
                }
                
                account.applyInterest(rAcct.divide(ppy, mc));
                account.addBankInterest(bank, rBank.divide(ppy, mc)
                        .divide(numberAccounts, mc)
                        .multiply(new BigDecimal("0.001"), mc));
                bank.applyInterest(rBank.divide(ppy, mc)
                        .multiply(new BigDecimal("0.999"), mc));
                
            }
        }
                
        BigDecimal errF = bank.amtBD.subtract(new BigDecimal(bank.amtF), mc);
        BigDecimal errD = bank.amtBD.subtract(new BigDecimal(bank.amtD), mc);
        
        NumberFormat f = NumberFormat.getCurrencyInstance();
        System.out.println("     Total bank value: " 
                + f.format(bank.amtBD.doubleValue()));
        System.out.println("Average account value: " 
                + f.format(bank.amtBD.divide(numberAccounts, mc).doubleValue()));
        System.out.println();
        System.out.printf("Difference using doubles: %s%n", f.format(errD));
        System.out.printf("Difference using floats:  %s%n", f.format(errF));
    }
    
    private class Account
    {
        MathContext mc;
        BigDecimal amtBD;
        double amtD;
        float amtF;
        
        public Account()
        {
            this.amtBD = BigDecimal.ZERO;
            this.amtD = 0;
            this.amtF = 0;
            this.mc = MathContext.DECIMAL128;
        }
        
        void credit(BigDecimal amount)
        {
            amtBD = amtBD.add(amount, mc);
            amtD += amount.doubleValue();
            amtF += amount.floatValue();
        }
        
        void debit(BigDecimal amount)
        {
            amtBD = amtBD.subtract(amount, mc);
            amtD -= amount.doubleValue();
            amtF -= amount.floatValue();
        }
        
        void applyInterest(BigDecimal interest)
        {
            amtBD = amtBD.add(amtBD.multiply(interest, mc), mc);
            amtD = amtD + amtD * interest.doubleValue();
            amtF = amtF + amtF * interest.floatValue();
        }
        
        void addBankInterest(Account bank, BigDecimal interest)
        {
            amtBD = amtBD.add(bank.amtBD.multiply(interest, mc), mc);
            amtD = amtD + bank.amtD * interest.doubleValue();
            amtF = amtF + bank.amtF * interest.floatValue();
        }
    }
}
