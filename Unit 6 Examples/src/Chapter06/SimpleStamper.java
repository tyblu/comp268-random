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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
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
    private static final int WINDOW_W = 4*450;
    private static final int WINDOW_H = 4*350;
    private static final int WINDOW_OFFSET_X = 4*120;
    private static final int WINDOW_OFFSET_Y = 4*70;
    /**
     * Replaces main(), to be called from MainCaller.
     */
    public static void call()
    {
        JFrame window = new JFrame("Simple Stamper");
        SimpleStamper content = new SimpleStamper();
        
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(WINDOW_OFFSET_X, WINDOW_OFFSET_Y);
        window.setSize(WINDOW_W, WINDOW_H);
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
        addMouseListener(this);     // Leaking 'this' in constructor
    }
    
    private static final int SHAPE_W = 60*4;
    private static final int SHAPE_H = SHAPE_W/2;
    /**
     * Since this panel has been set to listen for mouse event on itself,
     * this method will be called when the use clicks the mouse on the panel.
     * This method is part of the MouseListener interface.
     * @param evt   MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent evt)
    {
        /* The user was holding down the Shift key. Just repaint the panel.
        *   Since this class does not define a paintComponent() method, the
        *   method from the superclass JPanel is called. That method fills the
        *   panel with its background colour (black). The effect is to clear
        *   the panel.
        */
        if ( evt.isShiftDown() )
        {
            repaint();
            return;
        }
        
        int x = evt.getX();
        int y = evt.getY();
        
        /* Graphics context for drawing directly.
        *   Note: This is considered to be bad style!
        */
        Graphics g = getGraphics();
        
        if ( evt.isMetaDown() )
        {
            /* User right-clicked at (x,y). Draw a blue oval centered at (x,y).
            *   A black outline around the oval will make it more distinct when
            *   shapes overlap.
            */
            g.setColor(Color.BLUE);
            g.fillOval(x - SHAPE_W/2, y - SHAPE_H/2, SHAPE_W, SHAPE_H);
            g.setColor(Color.BLACK);
            g.drawOval(x - SHAPE_W/2, y - SHAPE_H/2, SHAPE_W, SHAPE_H);
        }
        else
        {
            /* User left- or middle-clicked at (x,y). Draw a red rectangle
            *   centered at (x,y).
            */
            g.setColor(Color.RED);
            g.fillRect(x - SHAPE_W/2, y - SHAPE_H/2, SHAPE_W, SHAPE_H);
            g.setColor(Color.BLACK);
            g.drawRect(x - SHAPE_W/2, y - SHAPE_H/2, SHAPE_W, SHAPE_H);
        }
        
        /* Finished with graphics context, so dispose of it. (Good form.) */
        g.dispose();
    }
    
    @Override
    public void mouseEntered(MouseEvent evt) {}
    @Override
    public void mouseExited(MouseEvent evt) {}
    @Override
    public void mouseClicked(MouseEvent evt) {}
    @Override
    public void mouseReleased(MouseEvent evt) {}
}
