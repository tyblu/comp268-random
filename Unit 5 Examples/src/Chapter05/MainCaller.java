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
 * Class:       MainCaller.java
 * Purpose:     Used and abused to call and test methods and classes in Chapter 5.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 25, 2017
 * Version     1.1
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014: Chapter 5
 * 
 */
public class MainCaller {
    
    // Examples
    private static final boolean ENABLE_PAIROFDICE = false;
    private static final boolean ENABLE_STUDENTTEST = false;
    private static final boolean ENABLE_HIGHLOWGAME = false;
    
    // Exercises
    private static final boolean ENABLE_EXERCISE_1 = false;
    private static final boolean ENABLE_EXERCISE_2 = false;
    private static final boolean ENABLE_EXERCISE_3 = false;
    private static final boolean ENABLE_EXERCISE_4 = true;
    
    
    public static void main(String[] args)
    {
        // Examples
        if (ENABLE_PAIROFDICE) { RollTwoPairs.callRollTwoPairs(); }
        if (ENABLE_STUDENTTEST) { StudentTest.callStudentTest(); }
        if (ENABLE_HIGHLOWGAME) { HighLow.callHighLow(); }
        
        // Exercises
        if (ENABLE_EXERCISE_1) { Exercise01.callExercise01(); }
        if (ENABLE_EXERCISE_2) { Exercise02.callExercise02(); }
        if (ENABLE_EXERCISE_3) { Exercise03.callExercise03(); }
        if (ENABLE_EXERCISE_4) { Exercise04.callExercise04(); }
    }
}
