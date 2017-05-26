/*
 * The MIT License
 *
 * Copyright (c) 2017 Tyler Lucas <tyblu@live.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package Chapter05;

/**
 * Class:       StatCalc.java
 * Purpose:     An object of class StatCalc can be used to compute several
 *              simple statistics for a set of numbers. Numbers are entered
 *              into the dataset using the enter(double) method.  Methods are
 *              provided to return the following statistics for the set of
 *              numbers that have been entered: The number of items, the sum
 *              of the items, the average, and the standard deviation.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 26, 2017
 * Version     1.1
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014: Chapter 5, Exercise 2
 */
public class StatCalc {

    private int count;   // Number of numbers that have been entered.
    private double sum;  // The sum of all the items that have been entered.
    private double squareSum;  // The sum of the squares of all the items.
    
    private double max = Double.NaN;    // Largest number entered.
    private double min = Double.NaN;    // Smallest number entered.

    /**
     * Add a number to the dataset.  The statistics will be computed for all
     * the numbers that have been added to the dataset using this method.
     * @param num Number to enter.
     */
    public void enter(double num) {
        count++;
        sum += num;
        squareSum += num*num;
        
        if ( Double.isNaN(min) || num < min) { min = num; }
        if ( Double.isNaN(max) || num > max) { max = num; }
    }

    // Getters
    /**
     * Getter method to return the maximum number entered so far.
     * @return the maximum number entered so far.
     */
    public double getMax()
    {
        return max;
    }
    
    /**
     * Getter method to return the minimum number entered so far.
     * @return the minimum number entered so far.
     */
    public double getMin()
    {
        return min;
    }
    
    /**
     * Return the number of items that have been entered into the dataset.
     * @return the number of items that have been entered into the dataset.
     */
    public int getCount() {
        return count;
    }

    /**
     * Return the sum of all the numbers that have been entered.
     * @return the sum of all the numbers that have been entered.
     */
    public double getSum() {
        return sum;
    }

    /**
     * Return the average of all the items that have been entered.
     * The return value is Double.NaN if no numbers have been entered.
     * @return the average of all the items that have been entered.
     */
    public double getMean() {
        return sum / count;  
    }

    /**
     * Return the standard deviation of all the items that have been entered.
     * The return value is Double.NaN if no numbers have been entered.
     * @return the standard deviation of all the items that have been entered.
     */
    public double getStandardDeviation() {  
        double mean = getMean();
        return Math.sqrt( squareSum/count - mean*mean );
    }
    
    // Other Methods
    /**
     * Print out all the statistics by default. No newline at end.
     * @return String of statistics separated by newline. No newline at end.
     */
    @Override
    public String toString()
    {
        String s = "";
        
        s += "Count:     " + getCount() + "\n";
        s += "Sum:       " + getSum() + "\n";
        s += "Maximum:   " + getMax() + "\n";
        s += "Minimum:   " + getMin() + "\n";
        s += "Average:   " + getMean() + "\n";
        s += "Std. Dev.: " + getStandardDeviation();
        
        return s;
    }

}  // end class StatCalc