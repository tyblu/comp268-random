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
import java.awt.event.ActionEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A RandomArtPanel draws random pictures which might be taken to have some 
 * vague resemblance to abstract art.  A new picture is produced every few
 * seconds.  There are three types of pictures:  random lines, random circles, 
 * and random 3D rectangles.  The figures are drawn in random colors on a 
 * background that is a random shade of gray.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 6, 2017
 * Version      1.3
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 6: Introduction to GUI Programming, 
 *      pp284-285</a>
 * @see Chapter06.ClickableRandomStrings
 * @see Chapter06.RandomStringsPanel
 * @see Chapter06.RandomStrings
 * 
 */
public class RandomArt extends JPanel
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
        JFrame window = new JFrame("Random Art ??");
        RandomArt content = new RandomArt();
        window.setContentPane(content);
        window.setSize(4*400,4*400);
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);
    }
/* -------------------------------------------------------------------------- */

    /**
     * The constructor creates a timer with a delay time of a few seconds, and
     * with an anonymous inner class object as its ActionListener, implemented
     * with a lambda expression. It also starts the timer running.
     * 
     * Calls the {@code repaint()} method of this panel each time its
     * {@code actionPerformed()} method is called.  An object of this type is
     * used as an action listener for a Timer that generates an ActionEvent
     * every few seconds.  The result is that the panel is redrawn every few
     * seconds.
     */
    public RandomArt()
    {
        Timer timer = new Timer(
                (new Random()).nextInt(1000)+1000,
                (ActionEvent evt) -> repaint()
        );
        timer.start();
    }

    /**
     * The paintComponent() method fills the panel with a random shade of
     * gray and then draws one of three types of random "art".  The type
     * of art to be drawn is chosen at random.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        /**
         * Note:  Since the next three lines fill the entire panel with gray,
         * there is no need to call super.paintComponent(g), since any drawing
         * that it does will only be covered up anyway.
         */
        
        Random rand = new Random();

        Color randomGray = Color.getHSBColor( 1.0F, 0.0F, rand.nextFloat() );
        g.setColor(randomGray);
        g.fillRect( 0, 0, getWidth(), getHeight() );

        int artType = rand.nextInt(3);

        switch (artType) {
        case 0:     // Randomly coloured and sized lines.
            for (int i = 0; i < 500; i++) {
                int x1 = rand.nextInt(getWidth());
                int y1 = rand.nextInt(getHeight());
                int x2 = rand.nextInt(getWidth());
                int y2 = rand.nextInt(getHeight());
                Color randomHue = 
                        Color.getHSBColor( rand.nextFloat(), 1.0F, 1.0F);
                g.setColor(randomHue);
                g.drawLine(x1,y1,x2,y2);
            }
            break;
        case 1:     // Randomly coloured circles.
            for (int i = 0; i < 200; i++) {
                int centerX = rand.nextInt(getWidth());
                int centerY = rand.nextInt(getHeight());
                Color randomHue = 
                        Color.getHSBColor( rand.nextFloat(), 1.0F, 1.0F);
                g.setColor(randomHue);
                g.drawOval(centerX - 50, centerY - 50, 100, 100);
            }
            break;
        case 2:     // Randomly coloured and sized squares.
        default:
            for (int i = 0; i < 25; i++) {
                int centerX = rand.nextInt(getWidth());
                int centerY = rand.nextInt(getHeight());
                int size = 30 + rand.nextInt(170);
                Color randomColor = new Color(rand.nextInt(256), 
                        rand.nextInt(256), rand.nextInt(256) );
                g.setColor(randomColor);
                g.fill3DRect(centerX - size/2, centerY - size/2, size, size, true);
            }
            break;
        }
    }
}

