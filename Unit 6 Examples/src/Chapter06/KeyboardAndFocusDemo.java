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

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

/**
 *              Textbook Example Program, Chapter 6
 * Class:       KeyboardAndFocusDemo.java
 * Purpose:     Demonstrates keyboard input to a GUI program, and input focus.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 8, 2017
 * Version      1.0
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 6: Introduction to GUI Programming</a>
 * 
 */
public class KeyboardAndFocusDemo extends JPanel
{
/* -------------------------------------------------------------------------- */
    /**
     * Similar to a {@code main(String[] args)} routine, is called by
     * {@link Chapter06.MainCaller}, as are all {@code call()} routines in most
     * example and exercise classes. Requires setting the appropriate boolean
     * variables in MainCaller in order to activate.
     */
    public static void call()
    {
        JFrame window = new JFrame("Try using keys: R G B K up down left right");
        KeyboardAndFocusDemo content = new KeyboardAndFocusDemo();
        
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(4*100,4*100);
        window.setSize(4*400,4*400);
        window.setVisible(true);
        
        content.requestFocusInWindow();
    }
/* -------------------------------------------------------------------------- */

    // Instance variables
    private int squareTop, squareLeft;
    private Color squareColor;
    
    // Constants
    private static int SQUARE_SIZE = 50;

    // Constructor
    KeyboardAndFocusDemo()
    {
        this.squareTop = ( getHeight() - SQUARE_SIZE ) / 2;
        this.squareLeft = ( getWidth() - SQUARE_SIZE ) / 2;
        this.squareColor = Color.RED;
        
        setBackground(Color.WHITE);
        
        Listener l = new Listener();
        addKeyListener(l);
        addFocusListener(l);
    }
    
    // Getters
    public int getSquareTop()
    {
        return this.squareTop;
    }
    
    public int getSquareLeft()
    {
        return this.squareLeft;
    }
    
    public Color getSquareColor()
    {
        return this.squareColor;
    }
    
    // Setters
    public void setSquareTop(int squareTop)
    {
        this.squareTop = squareTop;
    }
    
    public void setSquareLeft(int squareLeft)
    {
        this.squareLeft = squareLeft;
    }
    
    public void setSquareColor(Color squareColor)
    {
        this.squareColor = squareColor;
    }
    
    // Methods
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);    // clears screen, better redraw.
        
        if (hasFocus())
            g.setColor(Color.CYAN);
        else
            g.setColor(Color.LIGHT_GRAY);
        
        int width = getSize().width;    // width of panel
        int height = getSize().height;  // height of panel
        
        /* Define square. */
        g.drawRect(0, 0, width-1, height-1);
        g.drawRect(1, 1, width-3, height-3);
        g.drawRect(2, 2, width-5, height-5);
        
        /* Draw the square. */
        g.setColor(squareColor);
        g.fillRect(squareLeft, squareTop, SQUARE_SIZE, SQUARE_SIZE);
        
        /* Print msg depending on panel focus. */
        g.setColor(Color.MAGENTA);
        if (hasFocus())
        {
            g.drawString("Arrow keys move square", 7, 20);
            g.drawString("K, R, G, B change colour", 7, 40);
        }
        else
            g.drawString("Click to activate", 7, 20);
    }
    
/* -------------------------------------------------------------------------- */
    
    /**
     * Defines a listener object that listens for both focus and key events.
     */
    private class Listener implements KeyListener, FocusListener
    {
        // KeyListener implementation.
        
        /**
         * This is called each time the user presses a key while the panel has the
         * input focus. If the key pressed was one of the arrow keys, the square is
         * moved (except that it is not allowed to move off the edge of the panel,
         * allowing for a 3-pixel border).
         */
        @Override
        public void keyPressed(KeyEvent evt)
        {
            int key = evt.getKeyCode();
            
            if (key == KeyEvent.VK_LEFT)
            {
                squareLeft -= 8;
                if (squareLeft < 3)
                    squareLeft = 3;
                repaint();
            }
            else if (key == KeyEvent.VK_RIGHT)
            {
                squareLeft += 8;
                int maxRight = getWidth() - 3 - SQUARE_SIZE;
                if (squareLeft > maxRight)
                    squareLeft = maxRight;
                repaint();
            }
            else if (key == KeyEvent.VK_UP)
            {
                squareTop -=8;
                if (squareTop < 3)
                    squareTop = 3;
                repaint();
            }
            else if (key == KeyEvent.VK_DOWN)
            {
                squareTop += 8;
                int maxDown = getHeight() - 3 - SQUARE_SIZE;
                if (squareTop > maxDown)
                    squareTop = maxDown;
                repaint();
            }
        }
        
        /**
         * Changes the colour of the square when the user types R, G, B, or K, or
         * their lowercase equivalents, as follows.
         *<ul><li>R, r - red</li>
         *    <li>G, g - green</li>
         *    <li>B, b - blue</li>
         *    <li>K, k - black</li></ul>
         */
        @Override
        public void keyTyped(KeyEvent evt)
        {
            char c = evt.getKeyChar();
            
            switch (c)
            {
            case 'R': case 'r':
                setSquareColor(Color.RED);
                break;
            case 'G': case 'g':
                setSquareColor(Color.GREEN);
                break;
            case 'B': case 'b':
                setSquareColor(Color.BLUE);
                break;
            case 'K': case 'k':
                setSquareColor(Color.BLACK);
                break;
            case '!':
                // set to random colour
                break;
            }
            
            repaint();
        }
        
        @Override
        public void keyReleased(KeyEvent evt) { }
        
        // FocusListener implementation.
        
        @Override
        public void focusGained(FocusEvent evt) { repaint(); }
        
        @Override
        public void focusLost(FocusEvent evt) { repaint(); }
    }
/* -------------------------------------------------------------------------- */
}
