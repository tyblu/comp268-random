/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter07E02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * <h1>Eck v7, Chapter 7, Exercise #2</h1>
 * 
 * Presumably a class to hold several tools for working with arrays, though at
 * the moment it holds but one: {@link ArrayTools#transpose(int[][])}. It
 * returns the transpose of the input array.
 *
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 23, 2017
 * Version      1.0
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 7: Arrays and ArrayLists</a>
 */
public class ArrayTools
{
    /**
     * Takes an array of type {@code int[][]} and returns the transpose.
     * @param arr - typical 2D array in which all the rows have the same length.
     * @return - the transpose of the input array.
     */
    public static int[][] transpose(int[][] arr)
    {
        if (arr == null)
            return null;
        
        int[][] trans = new int[arr[0].length][arr.length];
        int temp;
        
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[0].length; j++)
                trans[j][i] = arr[i][j];
        
        return trans;
    }
    
    public static void main(String[] args)
    {
        int[][] array09 = new int[][]
        {
            getRange(new int[0], 0, 9),
            getRange(new int[0], 0, 9),
            getRange(new int[0], 0, 9),
            getRange(new int[0], 0, 9),
            getRange(new int[0], 0, 9),
            getRange(new int[0], 0, 9),
            getRange(new int[0], 0, 9),
            getRange(new int[0], 0, 9),
            getRange(new int[0], 0, 9),
            getRange(new int[0], 0, 9)
        };
        
        test(array09);
        test(transpose(array09));
        
        int[][] arrayRandom = new int[][]
        {
            getRandomRange(new int[0], 11, 100, 999),
            getRandomRange(new int[0], 11, 100, 999),
            getRandomRange(new int[0], 11, 100, 999),
            getRandomRange(new int[0], 11, 100, 999),
            getRandomRange(new int[0], 11, 100, 999)
        };
        
        test(arrayRandom);
        test(transpose(arrayRandom));
        
        int[][] array1Drow = new int[][]
        {
            { 1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061 }
        };
        
        test(array1Drow);
        test(transpose(array1Drow));
        
        int[][] array1Dcol = new int[][]
        {
            { 479 }, { 487 }, { 491 }, { 499 }, { 503 }, { 509 }, { 521 }
        };
        
        test(array1Dcol);
        test(transpose(array1Dcol));
        
        System.out.println();
        System.out.println("############## TESTING COMPLETED ##############");
    }
    
    private static int[] getRandomRange(int[] arr, int size, int fromNum, int toNum)
    {
        arr = new int[size];
        Random r = new Random();
        
        for (int i = 0; i < size; i++)
            arr[i] = fromNum + r.nextInt(toNum - fromNum + 1);
        
        return arr;
    }
    
    private static void test(int[][] arr)
    {
        System.out.println();
        System.out.println("===============================================");
        System.out.println();
        
        System.out.println("Input matrix:");
        System.out.printf("COL: %d ROW: %d%n", arr[0].length, arr.length);
//        System.out.println();
        printTable(arr);
        
        System.out.println();
        System.out.println();
        
        System.out.println("Transposed matrix:");
        System.out.printf("COL: %d ROW: %d%n", arr[0].length, arr.length);
//        System.out.println();
        printTable(transpose(arr));
        
        System.out.println();
        System.out.println("===============================================");
        System.out.println();
    }
    
    private static void printTable(int[][] arr)
    {
        int cols = arr[0].length;
        int rows = arr.length;
        int maxLength = Math.max(
                Integer.toString(getMax(arr)).length(),
                Integer.toString(rows + 1).length()
        );
        
        String cellFormat = "| %" + maxLength + "d ";
        
        ArrayList<Integer> colSet = getRange(colSet = null, 1,cols + 1);
        
        System.out.printf("  %s ", repeatConcat(" ",maxLength));
        colSet.forEach(val -> System.out.printf(cellFormat, val));  // header
        System.out.println();
        System.out.printf("|-%s-", repeatConcat("-",maxLength));
        colSet.forEach(val -> System.out.printf("|-%s-", repeatConcat("-",maxLength)));
        System.out.println();
//        colSet.forEach(val -> System.out.printf("| %s ", repeatConcat(" ",maxLength)));
//        System.out.println();
        
        for (int i = 0; i < rows; i++)
        {
//            System.out.printf("| %s ", repeatConcat(" ",maxLength));
            System.out.printf(cellFormat, i + 1);
            
            for (int j = 0; j < cols; j++)
                System.out.printf(cellFormat, arr[i][j]);
            System.out.println();
            
//            System.out.printf("| %s ", repeatConcat(" ",maxLength));
//            colSet.forEach(val -> System.out.printf("| %s ", repeatConcat(" ",maxLength)));
//            System.out.println();
            System.out.printf("|-%s-", repeatConcat("-",maxLength));
            colSet.forEach(val -> System.out.printf("|-%s-", repeatConcat("-",maxLength)));
            System.out.println();
//            System.out.printf("| %s ", repeatConcat(" ",maxLength));
//            colSet.forEach(val -> System.out.printf("| %s ", repeatConcat(" ",maxLength)));
//            System.out.println();
        }
    }
    
    private static Object getRange(int fromNum, int toNum, String type)
    {
        ArrayList<Integer> range = new ArrayList<>(toNum - fromNum + 1);
        for (int i = fromNum; i < toNum - fromNum + 1; i++)
            range.add(i);
        
        switch (type)
        {
        case "ArrayList<Integer>": case "ArrayList": case "ArrayList<>":
            return range;
        case "int[]": case "int":
            int[] rangeInt = new int[range.size()];
            
            for (int i = 0; i < range.size(); i++)
                rangeInt[i] = range.get(i);
            return rangeInt;
        }
        return range;
    }
    
    private static int[] getRange(int[] arr, int fromNum, int toNum)
    {
        return (int[])getRange(fromNum, toNum, "int[]");
    }
    
    private static ArrayList<Integer> getRange(ArrayList<Integer> arr, int fromNum, int toNum)
    {
        return (ArrayList<Integer>)getRange(fromNum, toNum, "ArrayList<Integer>");
    }
    
    private static String repeatConcat(String s, int copies)
    {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 1; i < copies; i++)
            sb.append(s);
        return sb.toString();
    }
    
    /**
     * Swaps two elements, if you do it right. Not collaboration-friendly.
     * 
     * <br>Usage:
     * <br>
     * {@code int x = 5;
     * int y = 10;
     * y = swap(x, x = y);
     * // results: y == 5, x == 10;}
     */
    private static int swap(int a, int b) { return a; }

    private static int getMax(int[][] arr)
    {
        int maximum = Integer.MIN_VALUE;
        
        for (int i = 0; i < arr.length; i++)
            for (int j = 0; j < arr[0].length; j++)
                if (arr[i][j] > maximum)
                    maximum = arr[i][j];
        
        return maximum;
    }
    
    private static boolean areRowsEqualLength(int[][] arr)
    {
        if (arr == null)
            return false;
        
        int length = arr[0].length;
        
        for (int i = 0; i < arr.length; i++)
            if (length != arr[i].length)
                return false;
        
        return true;
    }
}
