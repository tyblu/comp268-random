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
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;



/**
 * A simple panel where the user can sketch curves in a variety of colors.
 * A color palette is shown along the right edge of the panel. The user can
 * select a drawing color by clicking on a color in the palette. Under the
 * colors is a <em>"Clear"</em> button that the user can click to clear the
 * sketch. The user draws by clicking and dragging in a large white area that
 * occupies most of the panel. The user's drawing is not persistent. It is lost
 * whenever the panel is repainted for any reason.
 * <p>The drawing that is done in this example violates the rule
 * that all drawing should be done in the {@code paintComponent()} method.
 * Although it works, it is NOT good style.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 6, 2017
 * Version      1.1
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 6: Introduction to GUI Programming, 
 *      pp275-278</a>
 * 
 */
public class SimplePaint 
        extends JPanel 
        implements MouseListener, MouseMotionListener
{
/* -------------------------------------------------------------------------- */
    /**
     * Similar to a {@code main(String[] args)} routine. It is called by
     * {@link MainCaller}, as are all {@code call()} routines in most
     * example and exercise classes. Requires setting the appropriate boolean
     * variables in {@link MainCaller} in order to activate.
     */
    public static void call()
    {
        JFrame window = new JFrame("Simple Paint");
        SimplePaint content = new SimplePaint();
        window.setContentPane(content);
        window.setSize(600,480);
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);
    }
/* -------------------------------------------------------------------------- */

    /**
     * Some constants to represent the color selected by the user.
     */
    private final static int BLACK = 0,
            RED = 1,     
            GREEN = 2,   
            BLUE = 3, 
            CYAN = 4,   
            MAGENTA = 5,
            YELLOW = 6;

    /**
     * The currently selected drawing color, coded as one of the above constants.
     */
    private int currentColor = BLACK;

    /* The following variables are used when the user is sketching a
         curve while dragging a mouse. */
    private int prevX, prevY;   // Previous location of the mouse.
    private boolean dragging;   // Set to true while the user is drawing.

    /** 
     * A graphics context for the panel that is used to draw the user's curve.
     */
    private Graphics graphicsForDrawing;

    /**
     * Constructor for SimplePaintPanel class sets the background color to be
     * white and sets it to listen for mouse events on itself.
     */
    SimplePaint()
    {
        setBackgroundToWhite();
        addMouseListenerToThis();
        addMouseMotionListenerToThis();
    }
    
    /*  Helper methods to stop {@code this} leaks and calling overrideable
        methods, in constructor. */
    private void setBackgroundToWhite() { setBackground(Color.WHITE); }
    private void addMouseListenerToThis() { super.addMouseListener(this); }
    private void addMouseMotionListenerToThis() { super.addMouseMotionListener(this); }

    /**
     * Draw the contents of the panel.  Since no information is saved about 
     * what the user has drawn, the user's drawing is erased whenever this
     * routine is called.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);  // Fill with background color (white).

        int width = getWidth();    // Width of the panel.
        int height = getHeight();  // Height of the panel.

        int colorSpacing = (height - 56) / 7;
        // Distance between the top of one colored rectangle in the palette
        // and the top of the rectangle below it.  The height of the
        // rectangle will be colorSpacing - 3.  There are 7 colored rectangles,
        // so the available space is divided by 7.  The available space allows
        // for the gray border and the 50-by-50 CLEAR button.

        /* Draw a 3-pixel border around the panel in gray.  This has to be
             done by drawing three rectangles of different sizes. */
        g.setColor(Color.GRAY);
        g.drawRect(0, 0, width-1, height-1);
        g.drawRect(1, 1, width-3, height-3);
        g.drawRect(2, 2, width-5, height-5);

        /* Draw a 56-px wide gray rectangle along the right edge of the panel.
             The color palette and Clear button will be drawn on top of this.
             (This covers some of the same area as the border I just drew. */
        g.fillRect(width - 56, 0, 56, height);

        /* Draw the "Clear button" as a 50x50 white rectangle in the lower right
            corner of the panel, allowing for a 3-pixel border. */
        g.setColor(Color.WHITE);
        g.fillRect(width-53,  height-53, 50, 50);
        g.setColor(Color.BLACK);
        g.drawRect(width-53, height-53, 49, 49);
        g.drawString("CLEAR", width-48, height-23); 

        /* Draw the seven color rectangles. */
        g.setColor(Color.BLACK);
        g.fillRect(width-53, 3 + 0*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.RED);
        g.fillRect(width-53, 3 + 1*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.GREEN);
        g.fillRect(width-53, 3 + 2*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.BLUE);
        g.fillRect(width-53, 3 + 3*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.CYAN);
        g.fillRect(width-53, 3 + 4*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.MAGENTA);
        g.fillRect(width-53, 3 + 5*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.YELLOW);
        g.fillRect(width-53, 3 + 6*colorSpacing, 50, colorSpacing-3);

        /* Draw a 2-pixel white border around the color rectangle
             of the current drawing color. */
        g.setColor(Color.WHITE);
        g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
        g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
    }

    /**
     * Change the drawing color after the user has clicked the
     * mouse on the color palette at a point with y-coordinate y.
     * (Note that I can't just call repaint and redraw the whole
     * panel, since that would erase the user's drawing!)
     */
    private void changeColor(int y)
    {
        int width = getWidth();           // Width of panel.
        int height = getHeight();         // Height of panel.
        int colorSpacing = (height - 56) / 7;  // Space for one color rectangle
        int newColor = y / colorSpacing;    // Which color number was clicked?
        
        // Make sure the color number is valid.
        if (newColor < 0 || newColor > 6)
            return;

        /* Remove the highlight from the current color, by drawing over it in gray.
             Then change the current drawing color and draw a highlight around the
             new drawing color.  */
        Graphics g = getGraphics();
        g.setColor(Color.GRAY);
        g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
        g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
        currentColor = newColor;
        g.setColor(Color.WHITE);
        g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
        g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
        g.dispose();
    }

    /**
     * This routine is called in mousePressed when the user clicks on the
     * drawing area. It sets up the graphics context, graphicsForDrawing, to be
     * used to draw the user's sketch in the current color.
     */
    private void setUpDrawingGraphics()
    {
        graphicsForDrawing = getGraphics();
        
        switch (currentColor) {
            case RED:
                graphicsForDrawing.setColor(Color.RED);
                break;
            case GREEN:
                graphicsForDrawing.setColor(Color.GREEN);
                break;
            case BLUE:
                graphicsForDrawing.setColor(Color.BLUE);
                break;
            case CYAN:
                graphicsForDrawing.setColor(Color.CYAN);
                break;
            case MAGENTA:
                graphicsForDrawing.setColor(Color.MAGENTA);
                break;
            case YELLOW:
                graphicsForDrawing.setColor(Color.YELLOW);
                break;
            case BLACK:
            default:
                graphicsForDrawing.setColor(Color.BLACK);
                break;
        }
    }

    /**
     * This is called when the user presses the mouse anywhere in the panel.  
     * There are three possible responses, depending on where the user clicked:  
     * Change the current color, clear the drawing, or start drawing a curve.  
     * (Or do nothing if user clicks on the border.)
     */
    @Override
    public void mousePressed(MouseEvent evt)
    {
        int x = evt.getX();   // x-coordinate where the user clicked.
        int y = evt.getY();   // y-coordinate where the user clicked.

        int width = getWidth();    // Width of the panel.
        int height = getHeight();  // Height of the panel.

        if (dragging == true)  // Ignore mouse presses that occur
            return;            //    when user is already drawing a curve.
                               //    (This can happen if the user presses
                               //    two mouse buttons at the same time.)

        if (x > width - 53)
        {
                // User clicked to the right of the drawing area.
                // This click is either on the clear button or
                // on the color palette.
            if (y > height - 53)
                repaint();       //  Clicked on "CLEAR button".
            else
                changeColor(y);  // Clicked on the color palette.
        }
        else if (x > 3 && x < width - 56 && y > 3 && y < height - 3)
        {
                // The user has clicked on the white drawing area.
                // Start drawing a curve from the point (x,y).
            prevX = x;
            prevY = y;
            dragging = true;
            setUpDrawingGraphics();
        }
    }

    /**
     * Called whenever the user releases the mouse button. If the user was 
     * drawing a curve, the curve is done, so we should set drawing to false
     * and get rid of the graphics context that we created to use during the
     * drawing.
     */
    @Override
    public void mouseReleased(MouseEvent evt)
    {
        if (dragging == false)
            return;  // Nothing to do because the user isn't drawing.
        dragging = false;
        graphicsForDrawing.dispose();
        graphicsForDrawing = null;
    }

    /**
     * Called whenever the user moves the mouse while a mouse button is held
     * down. If the user is drawing, draw a line segment from the previous
     * mouse location to the current mouse location, and set up {@code prevX}
     * and {@code prevY} for the next call. Note that in case the user drags
     * outside of the drawing area, the values of {@code x} and {@code y} are
     * <em>"clamped"</em> to lie within this area.  This avoids drawing on the
     * color palette or clear button.
     */
    @Override
    public void mouseDragged(MouseEvent evt)
    {
        if (dragging == false)
            return;  // Nothing to do because the user isn't drawing.

        int x = evt.getX();   // x-coordinate of mouse.
        int y = evt.getY();   // y-coordinate of mouse.

        if (x < 3)                          // Adjust the value of x,
            x = 3;                           //   to make sure it's in
        if (x > getWidth() - 57)       //   the drawing area.
            x = getWidth() - 57;

        if (y < 3)                          // Adjust the value of y,
            y = 3;                           //   to make sure it's in
        if (y > getHeight() - 4)       //   the drawing area.
            y = getHeight() - 4;

        graphicsForDrawing.drawLine(prevX, prevY, x, y);  // Draw the line.

        prevX = x;  // Get ready for the next line segment in the curve.
        prevY = y;

    }

    @Override
    public void mouseEntered(MouseEvent evt) { }
    @Override
    public void mouseExited(MouseEvent evt) { }
    @Override
    public void mouseClicked(MouseEvent evt) { }
    @Override
    public void mouseMoved(MouseEvent evt) { }
}
