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
 *              Textbook Example Program, Chapter 5
 * Class:       Student.java
 * Purpose:     
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 25, 2017
 * Version     1.0
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014: Chapter 5
 * 
 */
public class Student
{
    private String name;
    public double test1, test2, test3;
    
    // Constructors.
    /**
     * Create Student object with blank name.
     */
    public Student()
    {
        name = "";
    }
    
    /**
     * Provides a name for the Student.
     * @param theName Student name. Cannot be null.
     * @throws IllegalArgumentException if theName is null.
     */
    public Student(String theName)
    {
        if ( theName == null || theName.matches("") )
            throw new IllegalArgumentException("Name can\'t be null or empty.");
        
        name = theName;
    }
    
    // Getters.
    /**
     * Getter method for reading the value of name.
     * @return String Student name (private instance variable).
     */
    public String getName()
    {
        return name;
    }
    
    // Setters.
    /**
     * Setter method for setting the value of name.
     */
    public void setName(String theName)
    {
        name = theName;
    }
    
    // Methods
    /**
     * Compute average test grade.
     */
    public double getAverage()
    {
        return (test1 + test2 + test3) / (double)3;
    }
}
