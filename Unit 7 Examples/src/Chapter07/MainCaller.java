package Chapter07;
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

/**
 *              Textbook Example Program, Chapter 7
 * Class:       MainCaller.java
 * Purpose:     Used to call and test methods and classes in Chapter 7.
 *              Set the appropriate boolean to {@code true} to run the
 *              {@code call()} routine in the associated class. It may be
 *              necessary to set all other boolean variables to {@code false},
 *              as there may be conflicts.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 19, 2017
 * Version      1.0
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 7: Arrays and ArrayLists</a>
 * 
 */
public class MainCaller
{
    private static final boolean ENABLE_ARITY = false;
    private static final boolean ENABLE_DICEARITY = false;
    private static final boolean ENABLE_RANDOMSTRINGS = false;
    private static final boolean ENABLE_DYNAMICARRAYINT = false;
    private static final boolean ENABLE_LIFE = true;
    
    public static void main(String[] args)
    {
        if (ENABLE_ARITY) { Arity.call(args); }
        if (ENABLE_DICEARITY) { DicePairGUIVariableArity.call(args); }
        if (ENABLE_RANDOMSTRINGS) { RandomStringsParallelArrays.call(args); }
        if (ENABLE_DYNAMICARRAYINT) { DynamicArrayOfInt.call(args); }
        if (ENABLE_LIFE) { Life.call(args); }
    }
}
