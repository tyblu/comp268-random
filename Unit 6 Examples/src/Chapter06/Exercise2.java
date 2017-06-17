package Chapter06;
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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <h1>Chapter 6 - Exercise 2</h1>
 * 
 * Presents a window with a red square and a blue square, either of which can
 * be moved around with the mouse. Dragging it outside of the window makes it
 * inaccessible.
 * 
 * <h2>Problem Statement</h2>
 * <p>Write a program that shows a small red square and a small blue square.
 * The user should be able to drag either square with the mouse. (You'll need
 * an instance variable to remember which square the user is dragging.) The
 * user can drag the square out of the panel if she wants; if she does this, 
 * there is no way to get it back.
 * <p>Note that for this exercise, you should do all the drawing in the
 * {@code paintComponent()} method (as indeed you should whenever possible).
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 15, 2017
 * Version      0.1
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 6: Introduction to GUI Programming</a>
 */
public class Exercise2
{
/* -------------------------------------------------------------------------- */
    public static void call() { call( new String[] { "" } ); }
    
    public static void call(String[] args)
    {
        new Exercise2();
    }
/* -------------------------------------------------------------------------- */
    
    /**
     * Constructor. Just runs the program from a non-static context.
     */
    public Exercise2()
    {
        CenteredWindow window = new CenteredWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.requestFocusInWindow();
    }
    
    // Nested classes.
    private class CenteredWindow extends JFrame
    {
        /**
         * Constructor.
         */
        public CenteredWindow()
        {
            super("Chapter 6 - Exercise #2: Dragging Squares Around");
            setContentPane(new DraggingSquaresPanel());
            pack();
            resetLocation();
        }
        
        // Methods
        private void resetLocation()
        {
            setLocation(
                    (getScreenSize().width - getWidth()) / 2,
                    (getScreenSize().height - getHeight()) / 2
            );
        }
    }
    
    private class DraggingSquaresPanel extends JPanel
    {
        // Instance variables.
        Shape[] shapes;
        
        /**
         * Constructor.
         */
        public DraggingSquaresPanel()
        {
            resetPanelSize();
            
            setBackground(Color.WHITE);
            
            Dimension screenDim = getScreenSize();
            
            this.shapes = new Shape[]{
                    new CustomRectangle(),
                    new CustomRectangle()
            };
        }
        
        // Methods
        /**
         * Sets JPanel size, which affects the total window size, to defaults.
         */
        private void resetPanelSize()
        {
            Dimension screenDim = getScreenSize();
            setPreferredSize( new Dimension(
                    (int)(screenDim.width * 0.75),
                    (int)(screenDim.height * 0.75) ));
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            // ToDo
        }
    }
    
    private class CustomRectangle extends Rectangle
    {
        // Instance variables.
        Point center;
        Color outlineColor, fillColor;
        
        /**
         * Constructor.
         */
        public CustomRectangle(Point center, Dimension d)
        {
            this.center = center;
            this.fillColor = fillColor;
            this.outlineColor = outlineColor;
            
            setSize(d);
            setLocation(center.x - getSize().width / 2,
                    center.y - getSize().height / 2);
        }
                
        public CustomRectangle()
        {
            java.util.Random r = new java.util.Random();
            this.center = new Point(
                    super.getLocation().x + getSize().width / 2,
                    super.getLocation().y + getSize().height / 2
            );
            this.outlineColor = Color.BLACK;
            this.fillColor = new Color(
                    r.nextInt(255), r.nextInt(255), r.nextInt(255));
        }
        
        public Point getCenter() { return this.center; }
        public Color getOutlineColor() { return this.outlineColor; }
        public Color getFillColor() { return this.fillColor; }
    }
    
    // Static methods.
    private static Dimension getScreenSize()
    {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
