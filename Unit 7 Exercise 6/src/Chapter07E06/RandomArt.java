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

/**
 * Chapter 7, Exercise #6
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203, Athabasca University
 * Date:       25-Jun-2017
 * Version     1.0
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 6: Introduction to GUI Programming, 
 *      pp284-285</a>
 * @see Chapter06.RandomArt
 */

package Chapter07E06;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A RandomArtPanel draws random pictures which might be taken to have some 
 * vague resemblance to abstract art. A new picture is produced every few 
 * seconds. There are three types of pictures:  random lines, random circles, 
 * and random 3D rectangles.  The figures are drawn in random colors on a
 * background that is a random shade of gray.
 * 
 * @author Tyler Lucas <tyblu@live.com>
 */
public class RandomArt
{
/* -------------------------------------------------------------------------- */
    public static void main(String[] args) { new RandomArt(); }
/* -------------------------------------------------------------------------- */
    
    // Instance variables.
    private final JFrame window;
    private JPanel content;
    private Timer timer;
    private long time = System.currentTimeMillis();
    
    // Constants.
    
    /**
     * Constructor.
     */
    public RandomArt()
    {
        this.content = new JPanel();
        content.setPreferredSize(new Dimension(
                (int)(GUITools.getScreenSize().width * 0.8),
                (int)(GUITools.getScreenSize().height * 0.8)
        ));
        
        this.window = new JFrame ("Eck Chapter 7, Exercise #6: RandomArt");
        window.setContentPane(content);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.pack();
        window.setLocation(
                (GUITools.getScreenSize().width - window.getWidth()) / 2,
                (GUITools.getScreenSize().height - window.getHeight()) / 2
        );
        window.setVisible(true);
        
        this.timer = new Timer(1000, evt -> nextArt());
        timer.setInitialDelay(0);
        timer.start();
    }
    
    // Methods.
    private void nextArt()
    {
        switch ((new Random()).nextInt(3))
        {
        case 0: 
            window.remove(content);
            content = new RandomLines();
            window.setContentPane(content);
            window.revalidate();
            break;
        case 1:
            window.remove(content);
            content = new RandomCircles();
            window.setContentPane(content);
            window.revalidate();
            break;
        case 2: 
            window.remove(content);
            content = new RandomRectangles();
            window.setContentPane(content);
            window.revalidate();
            break;
        default: 
            break;
        }
    }
    
    // Nested classes.
    private abstract class Artwork extends JPanel
    {
        Artwork()
        {
            setPreferredSize(new Dimension(
                    (int)(GUITools.getScreenSize().width * 0.8),
                    (int)(GUITools.getScreenSize().height * 0.8)
            ));
            
            int cInt = (new Random()).nextInt(255);
            setBackground(new Color(cInt, cInt, cInt));         // random grey
        }
        
        public abstract void draw(Graphics g);
    }
    
    private class RandomLines extends Artwork
    {
        // Instance variables.
        ArrayList<Line> lines;

        // Constructor.
        public RandomLines()
        {
            System.out.printf("%31s [%d] %s%n", "RandomLines:", (System.currentTimeMillis()-time)/1000, this.getPreferredSize());
            RandomPlus r = new RandomPlus();

            Rectangle rect1, rect2;
            rect1 = r.nextRectWithin(this.getPreferredSize());
            rect2 = r.nextRectWithin(this.getPreferredSize());

            int count = 20 + r.nextInt(80);
            
            this.lines = new ArrayList<>(count);

            for (int i = 0; i < count; i++)
            {
                lines.add(new Line(
                        r.nextPointWithin(rect1),
                        r.nextPointWithin(rect2),
                        r.nextColor(),
                        1 + r.nextInt(7)
                ));
            }
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            draw(g);
        }

        @Override
        public void draw(Graphics g)
        {
            for (Line line : lines)
            {
                ((Graphics2D)g).setStroke(new BasicStroke(line.thickness));
                g.setColor(line.color);
                g.drawLine(line.p1.x, line.p1.y, line.p2.x, line.p2.y);
            }
        }
    }
        
    private class Line
    {
        // Instance variables.
        public final Point p1, p2;
        public final Color color;
        public final int thickness;

        public Line(Point p1, Point p2, Color color, int thickness)
        {
            this.p1 = p1;
            this.p2 = p2;
            this.color = color;
            this.thickness = thickness;
        }
    }
    
    private class RandomCircles extends Artwork
    {
        // Instance variables.
        ArrayList<Circle> circles;
        
        // Constructor.
        public RandomCircles()
        {
            System.out.printf("%31s [%d] %s%n", "RandomCircles:", (System.currentTimeMillis()-time)/1000, this.getPreferredSize());
            RandomPlus r = new RandomPlus();

            int count = 20 + r.nextInt(80);
            
            this.circles = new ArrayList<>(count);

            for (int i = 0; i < count; i++)
            {
                circles.add(new Circle(
                        r.nextPointWithin(this.getPreferredSize()),
                        20 + r.nextInt(this.getPreferredSize().height / 5),
                        r.nextColor(),
                        1 + r.nextInt(7)
                ));
            }
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            draw(g);
        }
        
        @Override
        public void draw(Graphics g)
        {
            for (Circle circle : circles)
            {
                ((Graphics2D)g).setStroke(new BasicStroke(circle.thickness));
                g.setColor(circle.color);
                g.drawArc(circle.p.x, circle.p.y, 
                        circle.diameter, circle.diameter, 0, 360);
            }
        }
    }
    
    private class Circle
    {
        // Instance variables.
        public Point p;
        public int diameter;
        public Color color;
        public int thickness;

        // Constructor.
        public Circle(Point p, int diameter, Color color, int thickness)
        {
            this.p = p;
            this.diameter = diameter;
            this.color = color;
            this.thickness = thickness;
        }
    }
    
    private class RandomRectangles extends Artwork
    {
        // Instance variables.
        ArrayList<RectangleWithFlair> rectangles;
        
        // Constructor.
        public RandomRectangles()
        {
            System.out.printf("%31s [%d] %s%n", "RandomRectangles:", (System.currentTimeMillis()-time)/1000, this.getPreferredSize());
            RandomPlus r = new RandomPlus();

            int count = 10 + r.nextInt(40);
            
            this.rectangles = new ArrayList<>(count);

            for (int i = 0; i < count; i++)
            {
                rectangles.add(new RectangleWithFlair(
                        r.nextRectWithin(this.getPreferredSize()),
                        r.nextColor(),
                        1 + r.nextInt(7)
                ));
            }
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            draw(g);
        }
        
        @Override
        public void draw(Graphics g)
        {
            for (RectangleWithFlair rect : rectangles)
            {
                ((Graphics2D)g).setStroke(new BasicStroke(rect.thickness));
                g.setColor(rect.color);
                g.drawRect(rect.p.x, rect.p.y, rect.width, rect.height);
            }
        }
    }
    
    private class RectangleWithFlair
    {
        // Instance variables.
        public Point p;
        public int width;
        public int height;
        public Color color;
        public int thickness;

        // Constructor.
        public RectangleWithFlair(Rectangle bounds, Color color, int thickness)
        {
            this.p = new Point(bounds.x, bounds.y);
            this.width = bounds.width;
            this.height = bounds.height;
            this.color = color;
            this.thickness = thickness;
        }
    }
}
