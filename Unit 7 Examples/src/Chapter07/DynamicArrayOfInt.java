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
package Chapter07;

import java.util.Arrays;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Random;
import java.lang.Integer;
import java.util.ArrayList;

/**
 * Represents a list of int values that can grow and shrink.
 * Example on pp345-346.
 *
 *  <ul>    <li>v1.1 - Implemented using ArrayList. Reduced run time.</li>
 *          <li>v1.0 - Initial commit. (Forgot to set version and date.)</li>
 *  </ul>
 *
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 21, 2017
 * Version      1.1
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 7: Arrays and ArrayLists</a>
 */
public class DynamicArrayOfInt
{
    private int[] items = new int[16];
    private int itemCount;
    
    /**
     * Return the item at a given index in the array.
     * @throws ArrayIndexOutOfBoundsException if the index is not valid.
     */
    public int get(int index)
    {
        if (index < 0 || index >= itemCount)
            throw new ArrayIndexOutOfBoundsException("Illegal index: " + index);
        
        return items[index];
    }
    
    /**
     * Set the value of the array element at a given index.
     * @throws ArrayIndexOutOfBoundsException if the index is not valid.
     */
    public void set(int index, int item)
    {
        if (index < 0 || index >= itemCount)
            throw new ArrayIndexOutOfBoundsException("Illegal index: " + index);
        
        items[index] = item;
    }
    
    /**
     * Returns the number of items currently in the array.
     */
    public int size() { return itemCount; }
    
    /**
     * Adds a new item to the end of the array. The size increases by one.
     */
    public void add(int item)
    {
        if (itemCount == items.length)
            items = Arrays.copyOf(items, 2 * items.length);
        
        items[itemCount] = item;
        itemCount++;
    }
    
    /**
     * Removes the item at a given index in the array. The size decreases by
     * one. Items following the removed item are moved up one space.
     * @throws ArrayIndexOutOfBoundsException if the index is not valid.
     */
    public void remove(int index)
    {
        if (index < 0 || index >= itemCount)
            throw new ArrayIndexOutOfBoundsException("Illegal index: " + index);
        
        for (int i = index + 1; i < itemCount; i++)
            items[i-1] = items[i];
        
        itemCount--;
    }
    
    public int getMax()
    {
        int maxItem = Integer.MIN_VALUE;
        for (int i=0; i<itemCount; i++)
            if (items[i] > maxItem)
                maxItem = items[i];
        
        return maxItem;
    }
    
    public int getMin()
    {
        int minItem = Integer.MAX_VALUE;
        for (int i=0; i<itemCount; i++)
            if (items[i] < minItem)
                minItem = items[i];
        
        return minItem;
    }
    
    public int pull(int index)
    {
        if (index < 0 || index >= itemCount)
            throw new ArrayIndexOutOfBoundsException("Illegal index: " + index);
        
        int value = items[index];
        
        remove(index);
        
        return value;
    }
    
    private static final int CONSOLE_WIDTH = 80;
    
    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        
        output.append("Chapter07.DynamicArrayOfInt [");
        output.append(itemCount);
        output.append("/");
        output.append(items.length);
        output.append("]\n");
        
        int numberWidth = Math.max(
                Integer.toString(getMax()).length(),
                Integer.toString(getMin()).length()
        );
        
        StringBuilder line = new StringBuilder(CONSOLE_WIDTH);
        line.append(" >");
        for (int i=0; i<itemCount; i++)
        {
            line.append(' ');
            line.append(prepend(Integer.toString(get(i)), ' ', numberWidth));
            if (i + 1 < itemCount) { line.append(','); }
            
            if (line.length() + numberWidth + 2 > CONSOLE_WIDTH)
            {
                output.append(line);
                output.append("\n");
                line = new StringBuilder(CONSOLE_WIDTH);
                line.append(" >");
            }
        }
        output.append(line);
        
        return output.toString();
    }
    
    private static String prepend(String s, char c, int width)
    {
        if (s == null || width < 1) { return null; }
        if (s.length() == width) { return s; }
        if (s.length() > width) { return s.substring(0, width-1); }
        
        StringBuilder sb = (new StringBuilder(s)).reverse();
        
        while ( sb.length() < width)
            sb.append(c);
        
        return sb.reverse().toString();
    }
    
    /** ------------------------------------------------------------------------
     *                        ONLY FOR TESTING
     * -------------------------------------------------------------------------
     */
    public static void call(String[] args)
    {
        System.out.println("Testing DynamicArrayOfInt");
        
//        DynamicArrayOfInt arr = new DynamicArrayOfInt();
        ArrayList<Integer> arr = new ArrayList<>();
        
        System.out.println();
        System.out.println("ADDING");
        for (int i=1; i < Integer.MAX_VALUE / 2 && i > Integer.MIN_VALUE / 2; i *= -2)
        {
            System.out.println(arr);
            
            arr.add(i);
        }
        System.out.println(arr);
        
        System.out.println();
        System.out.println("REMOVING");
        for (int i = arr.size() - 1; i>0; i--)
        {
            System.out.println(arr);
            
            int indexToRemove = (new Random()).nextInt(arr.size());
            
            System.out.println("Removing element at index " + indexToRemove);
            
            arr.remove(indexToRemove);
        }
        System.out.println(arr);
        
        /* Next test. Reset array. */
        
//        arr = new DynamicArrayOfInt();
        arr = new ArrayList<>();
        for (int i=0; i<10; i++) { arr.add(1); }    // fill with 1's
        
        System.out.println();
        System.out.println("CHANGING");
        for (int i=0; i<10; i++)
        {
            System.out.println(arr);
            
            int indexToChange = (new Random()).nextInt(arr.size());
            
            System.out.println(
                "Changing element at index " + indexToChange + " to -1");
            
            arr.set(indexToChange, -1);             // replace with -1's
        }
        System.out.println(arr);
        
        /* Next test. Array reset in for(;;;) loop. */
        
        System.out.println();
        System.out.println("LARGE ARRAYS");
        int maxArraySize = (int)Math.pow(2,20) + 1;
        long timestampA, timestampB;
        for (int i=16; i < maxArraySize; i *= 2)
        {   
            Random r = new Random();
            
            System.out.printf(" testing max size [%11d]", i);
            System.out.println();
            
//            arr = new DynamicArrayOfInt();          // reset array
            arr = new ArrayList<>();
            
            // fill with randoms
            timestampA = System.currentTimeMillis();
            for (int j=0; j<i; j++)
            {
                if (j % (i / 16) == 0) { System.out.print("."); }   // ........
                
                arr.add(r.nextInt());
            }
            timestampB = System.currentTimeMillis();
            System.out.println(" filled in " + (timestampB - timestampA) + "ms");
            
            // change items
            DynamicArrayOfInt iArr = new DynamicArrayOfInt();
            for (int k=0; k<arr.size(); k++) { iArr.add(k); }
            
            timestampA = System.currentTimeMillis();
            for (int j=0; j<i; j++)
            {
                if (j % (i / 16) == 0) { System.out.print("."); }   // ........
                
                arr.set(iArr.pull(r.nextInt(iArr.size())), r.nextInt());
            }
            timestampB = System.currentTimeMillis();
            System.out.println(" changed in " + (timestampB - timestampA) + "ms");
                
            // remove items
            timestampA = System.currentTimeMillis();
            for (int j=0; j<i; j++)
            {
                if (j % (i / 16) == 0) { System.out.print("."); }   // ........
                
                arr.remove(r.nextInt(arr.size()));
            }
            timestampB = System.currentTimeMillis();
            System.out.println(" removed in " + (timestampB - timestampA) + "ms");
        }
    }
/* -------------------------------------------------------------------------- */
}
