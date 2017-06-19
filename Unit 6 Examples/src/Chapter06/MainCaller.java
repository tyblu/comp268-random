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
 *              Set the appropriate boolean to {@code true} to run the
 *              {@code call()} routine in the associated class. It may be
 *              necessary to set all other boolean variables to {@code false},
 *              as there may be conflicts.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 2, 2017
 * Version      1.0
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 6: Introduction to GUI Programming</a>
 * 
 */
public class MainCaller {
    
    // Examples
    private static final boolean ENABLE_HELLOWORLD1 = false;
    private static final boolean ENABLE_HELLOWORLD2 = false;
    private static final boolean ENABLE_RANDOMSTRINGS = false;
    private static final boolean ENABLE_RANDOMSTRINGSPANEL = false;
    private static final boolean ENABLE_CLICKABLERANDOMSTRINGS = false;
    private static final boolean ENABLE_SIMPLESTAMPER = false;
    private static final boolean ENABLE_SIMPLETRACKMOUSE = false;
    private static final boolean ENABLE_SIMPLEPAINT = false;
    private static final boolean ENABLE_CLICKABLERANDOMSTRINGS2 = false;
    private static final boolean ENABLE_RANDOMART = false;
    private static final boolean ENABLE_KEYBOARDANDFOCUSDEMO = false;
    private static final boolean ENABLE_SUBKILLER = false;
    private static final boolean ENABLE_SIMPLECALC = false;
    private static final boolean ENABLE_HIGHLOWGUI = false;
    private static final boolean ENABLE_PRACTICEGUI = false;
    private static final boolean ENABLE_BORDERDEMO = false;
    private static final boolean ENABLE_PRACTICEMENU = false;
    
    // Exercises
    private static final boolean ENABLE_EXERCISE_1 = false;
    private static final boolean ENABLE_EXERCISE_2 = false;
    private static final boolean ENABLE_EXERCISE_3 = true;
    
    public static void main(String[] args)
    {
        // Examples
        if (ENABLE_HELLOWORLD1) { HelloWorldGUI1.call(); }
        if (ENABLE_HELLOWORLD2) { HelloWorldGUI2.call(); }
        if (ENABLE_RANDOMSTRINGS) { RandomStrings.call(); }
        if (ENABLE_RANDOMSTRINGSPANEL) { RandomStringsPanel.call(); }
        if (ENABLE_CLICKABLERANDOMSTRINGS) { ClickableRandomStrings.call(); }
        if (ENABLE_SIMPLESTAMPER) { SimpleStamper.call(); }
        if (ENABLE_SIMPLETRACKMOUSE) { SimpleTrackMouse.call(); }
        if (ENABLE_SIMPLEPAINT) { SimplePaint.call(); }
        if (ENABLE_CLICKABLERANDOMSTRINGS2) { ClickableRandomStrings2.call(); }
        if (ENABLE_RANDOMART) { RandomArt.call(); }
        if (ENABLE_KEYBOARDANDFOCUSDEMO) { KeyboardAndFocusDemo.call(); }
        if (ENABLE_SUBKILLER) { SubKiller.call(); }
        if (ENABLE_SIMPLECALC) { SimpleCalc.call(); }
        if (ENABLE_HIGHLOWGUI) { HighLowGUI.call(); }
        if (ENABLE_PRACTICEGUI) { PracticeGUI.call(args); }
        if (ENABLE_BORDERDEMO) { BorderDemo.call(args); }
        if (ENABLE_PRACTICEMENU) { PracticeMenu.call(args); }
        
        // Exercises
        if (ENABLE_EXERCISE_1) { Exercise1.call(args); }
        if (ENABLE_EXERCISE_2) { Exercise2.call(args); }
        if (ENABLE_EXERCISE_3) { Exercise3.call(args); }
    }
}
