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
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
 * Version      0.2
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
        window.requestFocusInWindow();
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
            setLocation(
                    (getScreenSize().width - getWidth()) / 2,
                    (getScreenSize().height - getHeight()) / 2
            );
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
        private int shapeSpacing;
        private Dimension shapeSize;
        
        // Constructors.
        /**
         * Constructor.
         */
        public ComplexStamper()
        {
            setWindow();
            resetPanelSize();
            resetPanelContent();
            
            Dimension screenDim = getScreenSize();
            setShapeSize( new Dimension(     // Based on 195x345mm monitor.
                    (int)(screenDim.width * 20.0/345.0),    // 20mm wide
                    (int)(screenDim.height * 15.0/195.0)    // 15mm high
            ));
            setShapeSpacing( (int)(screenDim.width * 5.0/345.0) ); // 5mm
            
            PanelMouseListener l = new PanelMouseListener();
            addMouseListener(l);
            addMouseMotionListener(l);
        }
        
        // Methods.
        /**
         * Sets attributes back to initial values. Can be used to initialize
         * panel from constructor.
         */
        private void resetPanelContent()
        {
            setBackground(Color.WHITE);
            
            setLastMouseXY(null);
            setLastShapeXY(null);
            setNextDraw(NextDraw.CLEAR);
        }
        
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
            Point pointXY = getLastMouseXY();
            
//            if (!this.contains(pointXY))
//                setNextDraw(NextDraw.NONE);

//            if (isMouseinWindow())
//                setNextDraw(NextDraw.NONE);
            
            Dimension size = getShapeSize();
            
            switch (getNextDraw())
            {
            case OVAL:
                g.setColor(Color.CYAN);
                g.fillOval(
                        pointXY.x - size.width/2,
                        pointXY.y - size.height/2,
                        size.width,
                        size.height
                );
                g.setColor(Color.BLACK);
                g.drawOval(
                        pointXY.x - size.width/2,
                        pointXY.y - size.height/2,
                        size.width,
                        size.height
                );
                setLastShapeXY(pointXY);
                break;
            case RECT:
                g.setColor(Color.ORANGE);
                g.fillRect(
                        pointXY.x - size.width/2,
                        pointXY.y - size.height/2,
                        size.width,
                        size.height
                );
                g.setColor(Color.BLACK);
                g.drawRect(
                        pointXY.x - size.width/2,
                        pointXY.y - size.height/2,
                        size.width,
                        size.height
                );
                setLastShapeXY(pointXY);
                break;
            case CLEAR:
                super.paintComponent(g);
                setLastShapeXY(null);
                setNextDraw(NextDraw.NONE);
                break;
            default: case NONE:
                break;
            }
        }
        
        // Getters.
        /**
         * Get the parent window container (JFrame).
         */
        public Container getWindow()
        {
            return this.window;
        }
        /**
         * Gets the mouse's location as of the most recent MouseEvent.
         */
        public Point getLastMouseXY()
        { 
            if (this.lastMouseXY == null)
                return new Point(0,0);
            else
                return this.lastMouseXY;
        }
        
        /**
         * Gets the location of the last shape drawn on the screen.
         */
        public Point getLastShapeXY() 
        { 
            if (this.lastShapeXY == null)
                return new Point(0,0);
            else
                return this.lastShapeXY;
        }
        
        /**
         * Gets the next draw action to take, using NextDraw enum.
         */
        public NextDraw getNextDraw() { return this.nextDraw; }
        
        /**
         * Gets the shape Dimensions.
         */
        public Dimension getShapeSize() { return this.shapeSize; }
        
        /**
         * Gets the minimum pixel spacing between shape centers.
         */
        public int getShapeSpacing() { return this.shapeSpacing; }
        
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
         * Set the location of the last shape.
         */
        public void setLastShapeXY(Point lastShapeXY)
        {
            this.lastShapeXY = lastShapeXY;
        }
        
        /**
         * Sets the shape Dimensions.
         */
        private void setShapeSize(Dimension shapeSize)
        {
            this.shapeSize = shapeSize;
        }
        
        /**
         * Sets the minimum pixel spacing between shape centers.
         */
        private void setShapeSpacing(int shapeSpacing)
        {
            this.shapeSpacing = shapeSpacing;
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
        private Component listenToMe;
        private Point startPoint;
        private boolean stopDrag;
        
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
            listenToMe = (Component)evt.getSource();
            startPoint = evt.getPoint();
            stopDrag = false;
            if (!(this.listenToMe instanceof JPanel))
                return;
            
            ComplexStamper panel = (ComplexStamper)listenToMe;

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
         * Draw shapes as you drag. Keep them some minimum distance away from
         * each other.
         * @param evt 
         */
        @Override
        public void mouseDragged(MouseEvent evt)
        {
            Point location = evt.getPoint();
            
            // Debugging.
//            System.out.println("START POS: " + startPoint + " | DRAG POSITION: " + location + " | INSIDE?: " + listenToMe.contains(location));
            
            ComplexStamper panel = (ComplexStamper)listenToMe;
            
            panel.setLastMouseXY(location);
            
            double distance = location.distance(panel.getLastShapeXY());
            
            if (!listenToMe.contains(location) || stopDrag)
            {
                panel.setNextDraw(NextDraw.NONE);
                stopDrag = true;
            }
            else if (distance < panel.getShapeSpacing())
                panel.setNextDraw(NextDraw.NONE);
            else if (evt.isShiftDown())
                panel.setNextDraw(NextDraw.CLEAR);
            else if (evt.isMetaDown())
                panel.setNextDraw(NextDraw.OVAL);
            else
                panel.setNextDraw(NextDraw.RECT);
            
            panel.repaint();
        }
        
        /**
         * 
         */
        @Override
        public void mouseReleased(MouseEvent evt)
        {
            startPoint = null;
            listenToMe = null;
        }
    }
    
    private static Dimension getScreenSize()
    {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
