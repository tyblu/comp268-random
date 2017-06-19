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

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *              Textbook Example Program, Chapter 6
 * Class:       HelloWorldGUI2.java
 * Purpose:     
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 2, 2017
 * Version      1.1
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 6: Introduction to GUI Programming, 
 *      pp254-255</a>
 * 
 */
public class HelloWorldGUI2
{
    /**
     * Similar to a {@code main(String[] args)} routine, is called by
     * {@link Chapter06.MainCaller}, as are all {@code call()} routines in most
     * example and exercise classes. Requires setting the appropriate boolean
     * variables in MainCaller in order to activate.
     */
    public static void call()
    {
        HelloWorldDisplay displayPanel = new HelloWorldDisplay();
        JButton okButton = new JButton("OK");
        ButtonHandler listener = new ButtonHandler();
        okButton.addActionListener(listener);
        
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(displayPanel, BorderLayout.CENTER);
        content.add(okButton, BorderLayout.SOUTH);
        
        JFrame window = new JFrame("GUI Test");
        window.setContentPane(content);
        window.setSize(4*250,4*100);
        window.setLocation(100, 100);
        window.setVisible(true);
    }
    
    public static class HelloWorldDisplay extends JPanel
    {
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.drawString("Hello, World!", 20, 30);
        }
    }
    
    private static class ButtonHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    }
}
