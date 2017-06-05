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
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
 *              panel, a blue oval is drawn. Otherwise, when the user clicks, a
 *              red rectangle is drawn. The contents of the panel are not
 *              persistent. For example, they might disappear if the panel is
 *              resized.
 *              
 *              This class has a main()'ish routine, call(), to allow it to run
 *              as an application.
 * 
 *      v1.1    Implemented paintComponent() and related methods instead of
 *              drawing in mousePressed() and needing to fetch the graphics
 *              component.
 *              Implemented nested classes CustomMouseListener and
 *              CustomComponentListener to better organize code and to detect
 *              and redraw window when the user resizes it.
 *              Implemented private methods for constructor to reference
 *              instead of over-rideable public ones from JPanel.
 *              Implemented setters and getters.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 2, 2017
 * Version      1.1
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014: Chapter 6: Introduction to GUI Programming, pp275-278
 * 
 */
public class SimpleStamper extends JPanel
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
    
    /**
     * Maximum number of shapes (Components) to display at a time.
     */
    private static final int MAX_SHAPES = 25;
    private Component[] shapes = new Component[MAX_SHAPES];
    
    private enum NextDraw { RECT, OVAL, CLEAR }
    private NextDraw nextDraw = NextDraw.CLEAR;
    
    private Color backgroundColor = Color.BLACK;
    private Color fillColor;
    private Color outlineColor = Color.BLACK;
    private int mouseLocationX;
    private int mouseLocationY;
    
    private final CustomMouseListener mouseListener = 
            new CustomMouseListener();
    private final CustomComponentListener componentListener = 
            new CustomComponentListener();
    
    // Constructors
    /**
     * Sets the background colour of the panel to be black, and connects a new
     * MouseListener and ComponentListener.
     */
    public SimpleStamper()
    {
        setBackgroundColor(Color.BLACK);
        addMouseListener();
        addComponentListener();
    }
    
    // Constructor helpers.
    private void addMouseListener()
    {
        addMouseListener(new CustomMouseListener());
    }
    
    private void addComponentListener()
    {
        addComponentListener(new CustomComponentListener());
    }
    
    /**
     * Nested class to detect left- (and middle-), right-, and Shift-click
     * mouse clicks, and do different stuff for each case.
     */
    public class CustomMouseListener implements MouseListener
    {   // inline anonymous class
        /**
         * Since this panel has been set to listen for mouse event on itself,
         * this method will be called when the use clicks the mouse on the panel.
         * This method is part of the MouseListener interface.
         * @param evt   MouseEvent
         */
        @Override
        public void mousePressed(MouseEvent evt)
        {
            setMouseLocationX(evt.getX());
            setMouseLocationY(evt.getY());
            
            if ( evt.isShiftDown() )
            {
                /* The user was holding down the Shift key. Just repaint the panel.
                *   Since this class does not define a paintComponent() method, the
                *   method from the superclass JPanel is called. That method fills the
                *   panel with its background colour (black). The effect is to clear
                *   the panel.
                */
                setNextDraw(NextDraw.CLEAR);
            }
            else if ( evt.isMetaDown())
            {
                /* User right-clicked at (x,y). Draw a blue oval centered at (x,y).
                *   A black outline around the oval will make it more distinct when
                *   shapes overlap.
                */
                setFillColor(Color.BLUE);
//                setOutlineColor(Color.BLACK);
                setNextDraw(NextDraw.OVAL);
            }
            else
            {
                /* User left- or middle-clicked at (x,y). Draw a red rectangle
                *   centered at (x,y).
                */
                setFillColor(Color.RED);
//                setOutlineColor(Color.BLACK);
                setNextDraw(NextDraw.RECT);
            }

            repaint();    // Let system know that it needs to draw something.
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
    
    public class CustomComponentListener implements ComponentListener
    {
        @Override
        public void componentResized(ComponentEvent e)
        {
            setNextDraw(NextDraw.CLEAR);
            repaint();
        }

        @Override
        public void componentMoved(ComponentEvent e) {}
        @Override
        public void componentShown(ComponentEvent e) {}
        @Override
        public void componentHidden(ComponentEvent e) {}
    }
    
    private static final int SHAPE_W = 60*4;
    private static final int SHAPE_H = SHAPE_W/2;
    @Override
    public void paintComponent(Graphics g)
    {
        switch (getNextDraw()) {
            case OVAL:
                g.setColor(getFillColor());
                g.fillOval(
                        getMouseLocationX() - SHAPE_W/2,
                        getMouseLocationY() - SHAPE_H/2,
                        SHAPE_W,
                        SHAPE_H
                );
                g.setColor(getOutlineColor());
                g.drawOval(
                        getMouseLocationX() - SHAPE_W/2,
                        getMouseLocationY() - SHAPE_H/2,
                        SHAPE_W,
                        SHAPE_H
                );
                break;
            case RECT:
                g.setColor(getFillColor());
                g.fillRect(
                        getMouseLocationX() - SHAPE_W/2,
                        getMouseLocationY() - SHAPE_H/2,
                        SHAPE_W,
                        SHAPE_H
                );
                g.setColor(getOutlineColor());
                g.drawRect(
                        getMouseLocationX() - SHAPE_W/2,
                        getMouseLocationY() - SHAPE_H/2,
                        SHAPE_W,
                        SHAPE_H
                );
                break;
            case CLEAR:
            default:
                super.paintComponent(g);
                break;
        }
    }
    
    // Getters
    private Color getFillColor()
    {
        return this.fillColor;
    }
    
    private Color getOutlineColor()
    {
        return this.outlineColor;
    }
    
    private Color getBackgroundColor()
    {
        return this.backgroundColor;
    }
    
    private int getMouseLocationX()
    {
        return this.mouseLocationX;
    }
    
    private int getMouseLocationY()
    {
        return this.mouseLocationY;
    }
    
    private NextDraw getNextDraw()
    {
        return this.nextDraw;
    }
    
    // Setters
    private void setNextDraw(NextDraw nextDraw)
    {
        this.nextDraw = nextDraw;
    }
    
    private void setFillColor(Color fillColor)
    {
        this.fillColor = fillColor;
    }
    
    private void setOutlineColor(Color outlineColor)
    {
        this.outlineColor = outlineColor;
    }
    
    private void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
        this.setBackground(backgroundColor);
    }
    
    private void setMouseLocationX(int mouseLocationX)
    {
        this.mouseLocationX = mouseLocationX;
    }
    
    private void setMouseLocationY(int mouseLocationY)
    {
        this.mouseLocationY = mouseLocationY;
    }
}
