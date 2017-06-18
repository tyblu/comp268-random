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
 * Version      1.0
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
    
    // Constants
    private static final boolean DEBUG_ON = false;
    
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
        CustomRectangle[] rectangles;
        
        /**
         * Constructor.
         */
        public DraggingSquaresPanel()
        {
            setSize(0.75, 0.75);
            
            setBackground(Color.WHITE);
            
            Dimension shapeSize = new Dimension(200,200);
            this.rectangles = new CustomRectangle[]{
                    new CustomRectangle(new Point(500, 500), shapeSize),
                    new CustomRectangle(new Point(1500, 500), shapeSize)
            };
            
            int i = 0;
            for (CustomRectangle r : rectangles)
            {
                if (i++ % 2 == 0)
                    r.setFillColor(Color.RED);
                else
                    r.setFillColor(Color.BLUE);

                r.setOutlineColor(Color.BLACK);
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
        
        public CustomRectangle getShapeAt(Point p)
        {
            for (CustomRectangle r : rectangles)
            {
                if (r.contains(p))
                    return r;
            }
            return null;
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            for (CustomRectangle r : rectangles)
                drawRect(r, g);
        }
        
        private void drawRect(CustomRectangle r, Graphics g)
        {
            try { new Debug(this, new Object[]{ r }, DEBUG_ON); }
            catch (NullPointerException e) { System.out.println(e); }
            
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
            
            try { new Debug(this, new Object[]{ center, fillColor, outlineColor }, DEBUG_ON); }
            catch (NullPointerException e) { System.out.println(e); }
        }
                
        public CustomRectangle()
        {
            java.util.Random r = new java.util.Random();
            this.outlineColor = new Color(
                    r.nextInt(255), r.nextInt(255), r.nextInt(255));
            this.fillColor = new Color(
                    r.nextInt(255), r.nextInt(255), r.nextInt(255));
        }
        
        // Methods
        /**
         * Makes rectangle invisible. Sets size to zero and color to white, the
         * same as the background.
         */
        public void erase()
        {
            setSize(new Dimension(0,0));
            setFillColor(Color.WHITE);
            setOutlineColor(Color.WHITE);
        }
        
        // Getters
        public Point getCenter() { return this.center; }
        public Color getOutlineColor() { return this.outlineColor; }
        public Color getFillColor() { return this.fillColor; }
        
        // Setters
        public void setCenter(Point center)
        {
            this.center = center;
            center.translate((int)(-getWidth() /2.0), (int)(-getHeight() /2.0));
            setLocation(center);
            
            try { new Debug(this, new Object[]{ this.center, center }, DEBUG_ON); }
            catch (NullPointerException e) { System.out.println(e); }
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
        private DraggingSquaresPanel listenToMe;
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
            listenToMe = (DraggingSquaresPanel)evt.getSource();
            startPoint = evt.getPoint();
            currentPoint = startPoint;
            stopDrag = false;
            
            selectedRect  = listenToMe.getShapeAt(currentPoint);
            
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
            
            try { new Debug(this, new Object[]{ selectedRect, startPoint, currentPoint, listenToMe, stopDrag }, DEBUG_ON); }
            catch (NullPointerException e) { System.out.println(e); }
            
            if (listenToMe != null && selectedRect != null)
            {
                if (!listenToMe.getBounds().intersects(selectedRect) || stopDrag)
                {
                    stopDrag = true;
                    selectedRect.erase();
                }
                else if (selectedRect != null)
                    selectedRect.setCenter(currentPoint);
            }
            
            listenToMe.repaint();
        }
        
        /**
         * 
         */
        @Override
        public void mouseReleased(MouseEvent evt)
        {
            currentPoint = evt.getPoint();
            selectedRect = null;
            stopDrag = true;
        }
    }
    
    private class Debug
    {
        /**
         * Prints custom debugging messages to Standard Out with println().
         * @param that Send 'this' from object with debug code.
         * @param these array of Object[] with info to send to debug.
         * <ul>
         * <li>
         * Debugging DraggingSquaresPanel#drawRect(CustomRectangle, Graphics)
         * <ul>
         * <li>these[0]: CustomRectangle r with WxH, Color, (x,y)</li>
         * </ul>
         * </li>
         * <li>
         * Debugging CustomRectangle#CustomRectangle(Point, Dimension)
         * <ul>
         * <li>these[0]: Point center</li>
         * <li>these[1]: Color fillColor</li>
         * <li>these[2]: Color outlineColor</li>
         * </ul>
         * </li>
         * <li>
         * Debugging CustomRectangle#setCenter(Point)
         * <ul>
         * <li>these[0]: Point p</li>
         * <li>these[1]: Point center</li>
         * </ul>
         * </li>
         * <li>
         * Debugging PanelMouseListener#mouseDragged(MouseEvent)
         * <ul>
         * <li>these[0]: CustomRectangle selectedRect</li>
         * <li>these[1]: Point startPoint</li>
         * <li>these[2]: Point currentPoint</li>
         * <li>these[3]: DraggingSquaresPanel listenToMe</li>
         * <li>these[4]: boolean stopDrag</li>
         * </ul>
         * </li>
         * </ul>
         * @param isDebugOn Turn debugging lines on or off.
         */
        public Debug(Object that, Object[] these, boolean isDebugOn)
        {
            if (!isDebugOn)
                return;

//            System.out.println("drawing " + (r.getWidth()) + "x" + (r.getWidth()) + " " + r.getFillColor() + " at [" + (r.getLocation().x) + "," + (r.getLocation().y) + "]");
            if (that instanceof DraggingSquaresPanel && these.length == 1)
            {
                CustomRectangle r = (CustomRectangle)these[0];
                StringBuilder sb = new StringBuilder();
                sb.append("drawing ");
                sb.append(r.getWidth());
                sb.append("x");
                sb.append(r.getHeight());
                sb.append(" ");
                sb.append(r.getFillColor().toString().substring(9));
                sb.append(" at (");
                sb.append(r.getLocation().x);
                sb.append(",");
                sb.append(r.getLocation().y);
                sb.append(")");

                System.out.println(sb);
            }

//            System.out.println("NEW RECT: [" + (center.x) + "," + (center.y) + "] | " + fillColor + " | " + outlineColor);
            if (that instanceof CustomRectangle && these.length == 3)
            {
                Point center = (Point)these[0];
                Color fillColor = (Color)these[1];
                Color outlineColor = (Color)these[2];

                StringBuilder sb = new StringBuilder();
                sb.append("NEW RECT: (");
                sb.append(center.x);
                sb.append(",");
                sb.append(center.y);
                sb.append(") | ");
                sb.append(fillColor.toString().substring(9));
                sb.append(" | ");
                sb.append(outlineColor.toString().substring(9));

                System.out.println(sb);
            }

                    // Debugging public void setCenter(Point center)
//            System.out.println("RECT MOVED to [" + (center.x) + "," + (center.y) + "] (center [" + (this.center.x) + "," + (this.center.y) + "])");
            if (that instanceof CustomRectangle && these.length == 2)
            {
                Point p = (Point)these[0];
                Point center = (Point)these[1];

                StringBuilder sb = new StringBuilder();
                sb.append("RECT MOVED to (");
                sb.append(p.x);
                sb.append(",");
                sb.append(p.y);
                sb.append(") | center (");
                sb.append(center.x);
                sb.append(",");
                sb.append(")");

                System.out.println(sb);
            }

                    // Debugging public void mouseDragged(MouseEvent evt)
//            System.out.println("Moving " + selectedRect + " from [" + startPoint.x + "," + startPoint.y + "] | DRAG POSITION: [" + currentPoint.x + "," + currentPoint.y + "] | INSIDE?: " + listenToMe.contains(currentPoint) + " | OBJ: " + selectedRect + " | DRAGGING?: " + (!stopDrag));
            if (that instanceof PanelMouseListener && these.length == 5)
            {
                CustomRectangle selectedRect = (CustomRectangle)these[0];
                Point startPoint = (Point)these[1];
                Point currentPoint = (Point)these[2];
                DraggingSquaresPanel listenToMe = (DraggingSquaresPanel)these[3];
                boolean stopDrag = (boolean)these[4];

                StringBuilder sb = new StringBuilder();
                sb.append("Moving ");
                sb.append(selectedRect);
                sb.append(" from (");
                sb.append(startPoint.x);
                sb.append(",");
                sb.append(startPoint.y);
                sb.append(") | DRAG POSITION: (");
                sb.append(currentPoint.x);
                sb.append(",");
                sb.append(currentPoint.y);
                sb.append(") | INSIDE?: ");
                sb.append(listenToMe.contains(currentPoint));
                sb.append(" | DRAGGING?: ");
                sb.append(!stopDrag);

                System.out.println(sb);
            }
        }
    }
    
    // Static methods.
    private static Dimension getScreenSize()
    {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
