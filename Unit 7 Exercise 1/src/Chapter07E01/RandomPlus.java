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
package Chapter07E01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Adds the method to {@link Random}
 * <p>A cell can be "dead" or "alive". If the cell is alive, and has 2 or 3
 * living neighbours, then the cell remains alive; otherwise, it dies. (It dies 
 * of loneliness if it has 0 or 1 living neighbors, and of overcrowing if it
 * has more than 3 living neighbours.) If the cell is dead, and has 3 living 
 * neighbours, then the cell becomes alive.
 *
 * @author:     Tyler Lucas
 * Student ID:  3305203
<<<<<<< HEAD
 * Date:        June 21, 2017
=======
 * Date:        June 23, 2017
>>>>>>> feature/Chapter_7_Exercise_2
 * Version      1.0
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 7: Arrays and ArrayLists</a>
 */
public class RandomPlus extends Random
{
    /**
     * Returns an ArrayList containing several different random integers in the
     * range from 1 (inclusive) up to {@code bound} (exclusive).
     * @param bound - the upper bound (exclusive). Must be greater than 1.
     * @param size - the number of values to generate and size of ArrayList.
     *      Must be less than bound as there are only {@code bound-2} unique
     *      values.
     * @return ArrayList of with {@code size} unique integers between zero and
     *      {@code bound}.
     */
    public static ArrayList<Integer> nextUniqueIntSet(int bound, int size)
    {
        if (bound < 1)
            throw new IllegalArgumentException("bound must be greater than 1");
        
        if (size > bound - 2)
            throw new IllegalArgumentException(
                    "only " + (bound-2) + " possible unique integers, size is"
                    + "\n too large (" + size + ") or bound is too small (" 
                    + bound + ")");
        
        Set<Integer> uniques = new HashSet<>();
        Random r = new Random();
        while(uniques.size() < size)
            uniques.add(1 + r.nextInt(bound-2));
        
        return new ArrayList<>(uniques);
    }
    
    /**
     * Tests RandomPlus#nextUniqueIntSet(int,int).
     */
    public static void main(String[] args)
    {
        int testNumber = 1;
        
        System.out.println("Testing RandomPlus#nextUniqueIntSet(int,int).");
        System.out.println();
        
        Random r = new Random();
        
        printSeparator();
        
        System.out.println("Checking 10 short lists for contract correctness.");
        testNumber = test10(10, testNumber);
        
        printSeparator();
        
        System.out.println("Checking 10 large lists for contract correctness.");
        testNumber = test10(100000, testNumber);
        
        printSeparator();
        
        System.out.println("Testing randomly sized lists...");
        
        for (int i=0; i<10; i++)
        {
            int size = 20 + r.nextInt(10000000);
            int bound = size + 2 + r.nextInt(size/2);
            testNumber = test(size, bound, testNumber);
        }
        
        printSeparator();
        
        System.out.println("Testing IllegalArgumentException throwing.");
        testNumber = testForManyExceptions(testNumber);
        
        System.out.println("========= ALL TESTS COMPLETED ==========");
        
    }
    
    private static int getMax(ArrayList<Integer> arr)
    {
        final int[] maximum = { Integer.MIN_VALUE };
        arr.forEach(val -> { if (val > maximum[0]) maximum[0] = val; } );
        return maximum[0];
    }
    
    private static int getMin(ArrayList<Integer> arr)
    {
        final int[] minimum = { Integer.MAX_VALUE };
        arr.forEach(val -> { if (val < minimum[0]) minimum[0] = val; } );
        return minimum[0];
    }
    
    private static boolean areUnique(ArrayList<Integer> arr)
    {
        Set uniqueValues = new HashSet(arr);
        ArrayList<Integer> arr2 = new ArrayList<>(uniqueValues);
        
        return arr2.size() == uniqueValues.size();
    }
    
    private static int test10(int approxSize, int firstTestNum)
    {
        Random r = new Random();
        
        for (int i=0; i<10; i++)
        {
            int size = approxSize/2 + r.nextInt(approxSize/2);
            int bound = size + 2 + r.nextInt(approxSize/2);
            
            firstTestNum = test(size,bound,firstTestNum);
        }
        return firstTestNum;
    }
    
    private static int test(int size, int bound, int testNum)
    {
        System.out.printf("Test #%02d - nextUniqueIntSet(bound=%d, size=%d)%n",
                testNum, bound, size);
        
        ArrayList<Integer> list = null;
        Exception exception = null;
        try {
            list = nextUniqueIntSet(bound, size);
        } catch (Exception e) { exception = e; }

        if (exception == null && list != null)
        {
            if (list.size() < 20)
            {
                System.out.print("LIST: ");
                int maxLength = Integer.toString(getMax(list)).length();
                String format = " %" + (maxLength+1) + "d";
                list.forEach(val -> System.out.printf(format,val));
                System.out.println();
            }
            System.out.printf(
                    "Maximum: %-12d Minimum: %-12d Size: %-12d Unique? %s%n",
                    getMax(list), getMin(list), list.size(),
                    (areUnique(list) ? "YES" : "NO"));
        }
        else
            System.out.println("Exception thrown: " + exception);
        
        return testNum + 1;
    }
    
    private static int testExceptions(int size, int bound, int testNum)
    {
        System.out.printf("Test #%02d - nextUniqueIntSet(bound=%d, size=%d)%n",
                testNum, bound, size);
        
        ArrayList<Integer> list = null;
        Exception exception = null;
        try {
            list = nextUniqueIntSet(bound, size);
        } catch (Exception e) { exception = e; }
        
        if (exception == null)
            System.out.println("Exception not thrown.");
        else
            System.out.println("Exception thrown: " + exception);
        
        return testNum + 1;
    }
    
    private static int testForManyExceptions(int testNum)
    {
        for (int size = -5; size < 5; size++)
        {
            for (int bound = -2; bound < 5; bound++)
            {
                testNum = testExceptions(size, bound, testNum);
            }
        }
        return testNum;
    }
    
    private static void printSeparator()
    {
        System.out.println();
        System.out.println("======================================");
        System.out.println();
    }
}
