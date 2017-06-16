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

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.geom.Path2D;

/**
 * <h1>Chapter 6 - Exercise 1</h1>
 * 
 * Implements Eck's <em>SimpleStamper</em>, but leaves a trail of shapes when
 * they are dragged with the mouse. Minimum spacing between shapes is enforced.
 * 
 * <h2>Problem Statement</h2>
 * <p>In the SimpleStamper example from Subsection 6.3.3, a rectangle or oval is
 * drawn on the panel when the user clicks the mouse. Except, when the user
 * shift-clicks, the panel is cleared instead. Modify this class so that the
 * modified version will continue to draw figures as the user drags the mouse.
 * That is, the mouse will leave a trail of figures as the user drags. However,
 * if the user shift-clicks, the panel should simply be cleared and no figures
 * should be drawn even if the user drags the mouse after shift-clicking.
 * <p>The source code for the original program is <em>SimpleStamper.java</em>. 
 * See the discussion of dragging in Subsection 6.3.4. (Note that the original
 * version uses a black background, with a black border around each shape. That
 * didn't work well with a lot of closely spaced shapes, so the new version 
 * uses a white background.)
 * <p>If you want to make the problem a little more challenging, when drawing
 * shapes during a drag operation, make sure that the shapes that are drawn are
 * at least, say, 5 pixels apart. To implement this, you have to keep track of
 * the position of the last shape that was drawn.
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
 * @see SimpleStamper
 */
public class Exercise1
{
/* -------------------------------------------------------------------------- */
    public static void call() { call( new String[] { "" } ); }
    
    public static void call(String[] args)
    {
        new Exercise1();
    }
/* -------------------------------------------------------------------------- */
    
    /**
     * Describes the next shape to be drawn by paintComponent(Graphics).
     */
    private enum NextDraw { RECT, OVAL, CLEAR, NONE }
    
    /**
     * Constructor. Just runs the program from a non-static context.
     */
    public Exercise1()
    {
        ComplexStamperWindow window = new ComplexStamperWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    
    private class ComplexStamperWindow extends JFrame
    {
        /**
         * Constructor.
         */
        public ComplexStamperWindow()
        {
            super("Chapter 6 - Exercise #1: ComplexStamper");
            setContentPane(new ComplexStamper());
            pack();
            resetLocation();
        }
        
        // Methods
        private void resetLocation()
        {
            boolean wasVisible = isVisible();
            setVisible(true);
            setLocation(
                    (getScreenSize().width - getWidth()) / 2,
                    (getScreenSize().height - getHeight()) / 2
            );
            setVisible(wasVisible);
        }
    }
    
    private class ComplexStamper extends JPanel
    {
        // Constants.
        
        // Instance variables.
        /**
         * Top-most JFrame (window) object.
         */
        private JFrame window;
        private Point lastShapeXY, lastMouseXY;
        private NextDraw nextDraw = NextDraw.NONE;
        
        // Constructors.
        /**
         * Constructor.
         */
        public ComplexStamper()
        {
            setWindow();
            resetPanelSize();
            resetPanelContent();
        }
        
        // Methods.
        /**
         * Sets attributes back to initial values. Can be used to initialize
         * panel from constructor.
         */
        private void resetPanelContent()
        {
            setBackground(Color.WHITE);
        }
        
        /**
         * Sets JPanel size, which affects the total window size, to defaults.
         */
        private void resetPanelSize()
        {
            setPreferredSize( new Dimension(
                    (int)(getScreenSize().width * 0.75),
                    (int)(getScreenSize().height * 0.75) ));
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            // TODO
        }
        
        // Getters.
        
        // Setters.
        /**
         * Retrieves top-most JFrame (window) object and sets it.
         */
        private void setWindow()
        {
            this.window = (JFrame)SwingUtilities.getRoot(this);
        }
        
        /**
         * Set the mouse's location as of the most recent MouseEvent.
         */
        public void setLastMouseXY(Point lastMouseXY)
        {
            this.lastMouseXY = lastMouseXY;
        }
        
        /**
         * Set the next draw operation type: a rectangle, an ellipse, nothing,
         * or clear the panel?
         */
        public void setNextDraw(NextDraw nextDraw)
        {
            this.nextDraw = nextDraw;
        }
    }
    
    /**
     * Mouse action for the whole panel. Just keep track of the shapes to see
     * if you're interacting with them.
     */
    private class PanelMouseListener extends MouseAdapter
    {
        /**
         * 
         * Make new shape if a certain distance away from others.
         * Different border color (pressed in?) to show that it's active.
         * Bring shape into focus if clicked on it.
         * @param evt MouseEvent
         */
        @Override
        public void mousePressed(MouseEvent evt)
        {
            if (!(evt.getComponent() instanceof JPanel))
                return;
            
            ComplexStamper panel = (ComplexStamper)evt.getComponent();
            
            panel.setLastMouseXY(evt.getPoint());
            
            if (evt.isShiftDown())
                panel.setNextDraw(NextDraw.CLEAR);
            else if (evt.isMetaDown())
                panel.setNextDraw(NextDraw.OVAL);
            else
                panel.setNextDraw(NextDraw.RECT);
            
            panel.repaint();
        }
        
        /**
         * Drop and get rid of shape if dragged outside of context.
         * @param evt MouseEvent
         */
        @Override
        public void mouseExited(MouseEvent evt)
        {
            // TODO
        }
        
        /**
         * Draw shapes as you drag. Keep them some minimum distance away from
         * each other.
         * @param evt 
         */
        @Override
        public void mouseDragged(MouseEvent evt)
        {
            // TODO
        }
    }
    
    private static Dimension getScreenSize()
    {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
