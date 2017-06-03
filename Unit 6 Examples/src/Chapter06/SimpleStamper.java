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

import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *              Textbook Example Program, Chapter 6
 * Class:       SimpleStamper.java
 * Purpose:     Demonstration of MouseEvents. Shapes are drawn on a black
 *              background when the user clicks the panel. If the user Shift-
 *              clicks, the panel is cleared. If the user right-clicks the
 *              panel, a blue oval is drawn. Otherwise, when the user licks, a
 *              red rectangle is drawn. The contents of the panel are not
 *              persistent. For example, they might disappear if the panel is
 *              resized.
 *              
 *              This class has a main()'ish routine, call(), to allow it to run
 *              as an application.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 2, 2017
 * Version      1.0
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014: Chapter 6: Introduction to GUI Programming, pp275-278
 * 
 */
public class SimpleStamper extends JPanel implements MouseListener
{
    public static void call()
    {
        JFrame window = new JFrame("Simple Stamper");
        SimpleStamper content = new SimpleStamper();
        
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(4*120,4*70);
        window.setSize(4*450,4*350);
        window.setVisible(true);
    }
    
    // Constructors
    /**
     * Sets the background colour of the panel to be black and sets the panel
     * to listen for mouse events on itself.
     */
    public SimpleStamper()
    {
        setBackground(Color.BLACK);
        addMouseListener(this);
    }
}
