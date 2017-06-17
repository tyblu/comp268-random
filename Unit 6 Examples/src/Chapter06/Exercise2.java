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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
            setSize(0.75, 0.75);
            
            setBackground(Color.WHITE);
            
            Dimension shapeSize = new Dimension(
                    (int)(getWidth() * 0.1),
                    (int)(getWidth() * 0.1)
            );
            this.shapes = new Shape[]{
                    new CustomRectangle(
                            new Point(
                                    (int)(getWidth() * 0.2),
                                    (int)(getHeight() * 0.5)
                            ),
                            shapeSize
                    ),
                    new CustomRectangle(
                            new Point(
                                    (int)(getWidth() * 0.8),
                                    (int)(getHeight() * 0.5)
                            ),
                            shapeSize
                    )
            };
            
            int i = 0;
            for (Shape s : shapes)
            {
                if (s instanceof CustomRectangle)
                {
                    if (i++ % 2 == 0)
                        ((CustomRectangle)s).setFillColor(Color.RED);
                    else
                        ((CustomRectangle)s).setFillColor(Color.BLUE);
                    ((CustomRectangle)s).setOutlineColor(Color.BLACK);
                }
            }
            
            PanelMouseListener l = new PanelMouseListener();
            addMouseListener(l);
            addMouseMotionListener(l);
        }
        
        // Methods
        /**
         * Sets JPanel size, which affects the total window size, to defaults.
         */
        private void setSize(double xRatio, double yRatio)
        {
            Dimension screenDim = getScreenSize();
            setPreferredSize( new Dimension(
                    (int)(screenDim.width * xRatio),
                    (int)(screenDim.height * yRatio) ));
        }
        
        public Shape getShapeAt(Point p)
        {
            for (Shape s : shapes)
            {
                if (s.contains(p))
                    return s;
            }
            return null;
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            for (Shape s : shapes)
                if (s instanceof CustomRectangle)
                    drawRect((CustomRectangle)s, g);
        }
        
        private void drawRect(CustomRectangle r, Graphics g)
        {
            g.setColor(r.getFillColor());
            g.fillRect(
                    r.getLocation().x, 
                    r.getLocation().y, 
                    (int)r.getWidth(), 
                    (int)r.getHeight()
            );
            g.setColor(r.getOutlineColor());
            g.drawRect(
                    r.getLocation().x, 
                    r.getLocation().y, 
                    (int)r.getWidth(), 
                    (int)r.getHeight()
            );
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
            this.fillColor = Color.GRAY;
            this.outlineColor = Color.BLACK;
            
            setSize(d);
            setLocation(center.x - getSize().width / 2,
                    center.y - getSize().height / 2);
        }
                
        public CustomRectangle()
        {
            java.util.Random r = new java.util.Random();
            this.outlineColor = new Color(
                    r.nextInt(255), r.nextInt(255), r.nextInt(255));
            this.fillColor = new Color(
                    r.nextInt(255), r.nextInt(255), r.nextInt(255));
        }
        
        public Point getCenter() { return this.center; }
        public Color getOutlineColor() { return this.outlineColor; }
        public Color getFillColor() { return this.fillColor; }
        
        public void setCenter(Point center)
        {
            this.center = center;
            center.translate((int)(-getWidth() /2.0), (int)(-getHeight() /2.0));
            setLocation(center);
        }
        
        public void setFillColor(Color fillColor)
        {
            this.fillColor = fillColor;
        }
        
        public void setOutlineColor(Color outlineColor)
        {
            this.outlineColor = outlineColor;
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
        private Point currentPoint;
        private boolean stopDrag;
        private CustomRectangle selectedRect;
        
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
            currentPoint = startPoint;
            stopDrag = false;
            
            if (!(listenToMe instanceof JPanel))
            {
                Shape shapeAtMouse = ((DraggingSquaresPanel)listenToMe)
                        .getShapeAt(currentPoint);
                
                if (shapeAtMouse instanceof CustomRectangle)
                    selectedRect = (CustomRectangle)shapeAtMouse;
                else
                    selectedRect = null;
            }
            
            listenToMe.repaint();
        }
        
        /**
         * Draw shapes as you drag. Keep them some minimum distance away from
         * each other.
         * @param evt 
         */
        @Override
        public void mouseDragged(MouseEvent evt)
        {
            currentPoint = evt.getPoint();
            
            // Debugging.
            System.out.println("START POS: [" + startPoint.x + "," + startPoint.y + "] | DRAG POSITION: [" + currentPoint.x + "," + currentPoint.y + "] | INSIDE?: " + listenToMe.contains(currentPoint) + " | OBJ: " + selectedRect + " | DRAGGING?: " + (!stopDrag));
            
            if (!listenToMe.contains(currentPoint) || stopDrag)
                stopDrag = true;
            else if (selectedRect != null)
                selectedRect.setCenter(currentPoint);
            
            listenToMe.repaint();
        }
        
        /**
         * 
         */
        @Override
        public void mouseReleased(MouseEvent evt)
        {
            currentPoint = evt.getPoint();
            stopDrag = true;
        }
    }
    
    // Static methods.
    private static Dimension getScreenSize()
    {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
