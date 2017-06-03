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
package Chapter06;


/**
 *              Textbook Example Program, Chapter 6
 * Class:       MainCaller.java
 * Purpose:     Used to call and test methods and classes in Chapter 6.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 2, 2017
 * Version      1.0
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014: Chapter 6: Introduction to GUI Programming
 * 
 */
public class MainCaller {
    
    // Examples
    private static final boolean ENABLE_HELLOWORLD1 = false;
    private static final boolean ENABLE_HELLOWORLD2 = false;
    private static final boolean ENABLE_RANDOMSTRINGS = false;
    private static final boolean ENABLE_RANDOMSTRINGSPANEL = false;
    private static final boolean ENABLE_CLICKABLERANDOMSTRINGS = true;
    
    // Exercises
    
    public static void main(String[] args)
    {
        // Examples
        if (ENABLE_HELLOWORLD1) { HelloWorldGUI1.call(); }
        if (ENABLE_HELLOWORLD2) { HelloWorldGUI2.call(); }
        if (ENABLE_RANDOMSTRINGS) { RandomStrings.call(); }
        if (ENABLE_RANDOMSTRINGSPANEL) { RandomStringsPanel.call(); }
        if (ENABLE_CLICKABLERANDOMSTRINGS) { ClickableRandomStrings.call(); }
        
        // Exercises

    }
}
