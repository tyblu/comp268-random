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
            RandomPlus r = new RandomPlus();

            Rectangle rectA;
            ArrayList<Rectangle> rectBs = new ArrayList<>();
            rectA = r.nextRectWithin(this.getPreferredSize());
            for (int i = 0; i < 1 + r.nextInt(4); i++)
                rectBs.add(r.nextRectWithin(this.getPreferredSize()));

            int count = 20 + r.nextInt(80);
            
            this.lines = new ArrayList<>(count);

            for (Rectangle rectB : rectBs)
            {
                for (int i = 0; i < count; i++)
                {
                    lines.add(new Line(
                            r.nextPointWithin(rectA),
                            r.nextPointWithin(rectB),
                            r.nextColor(),
                            1 + r.nextInt(20)
                    ));
                }
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
            double scaleFactor = GUITools.getMinScaleFactorBetween(
                    g.getClipBounds(), this.getPreferredSize());
            
            for (Line line : lines)
            {
                Line scaled = line.scale(scaleFactor);
                ((Graphics2D)g).setStroke(new BasicStroke(scaled.thickness));
                g.setColor(scaled.color);
                g.drawLine(scaled.p1.x, scaled.p1.y, scaled.p2.x, scaled.p2.y);
            }
        }
    }
        
    class Line
    {
        // Instance variables.
        public Point p1, p2;
        public final Color color;
        public final int thickness;

        public Line(Point p1, Point p2, Color color, int thickness)
        {
            this.p1 = p1;
            this.p2 = p2;
            this.color = color;
            this.thickness = thickness;
        }
        
        public Line scale(double factor)
        {
            return new Line(p1,
                    new Point(
                            p1.x + (int)(factor * (p2.x - p1.x)),
                            p1.y + (int)(factor * (p2.y - p1.y))
                    ),
                    color, thickness
            );
        }
    }
    
    private class RandomCircles extends Artwork
    {
        // Instance variables.
        ArrayList<Circle> circles;
        
        // Constructor.
        public RandomCircles()
        {
            RandomPlus r = new RandomPlus();

            int count = 40 + r.nextInt(160);
            
            this.circles = new ArrayList<>(count);

            for (int i = 0; i < count; i++)
            {
                circles.add(new Circle(
                        r.nextPointWithin(this.getPreferredSize()),
                        20 + r.nextInt(this.getPreferredSize().height / 2),
                        r.nextColor(),
                        1 + r.nextInt(20)
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
            double scaleFactor = GUITools.getMinScaleFactorBetween(
                    g.getClipBounds(), getPreferredSize());
            
            for (Circle circle : circles)
            {
                Circle scaled = circle.scale(scaleFactor);
                ((Graphics2D)g).setStroke(new BasicStroke(scaled.thickness));
                g.setColor(scaled.color);
                g.drawArc(scaled.p.x, scaled.p.y, 
                        scaled.diameter, scaled.diameter, 0, 360);
            }
        }
    }
    
    class Circle
    {
        // Instance variables.
        public Point p;
        public int diameter;
        public final Color color;
        public final int thickness;

        // Constructor.
        public Circle(Point p, int diameter, Color color, int thickness)
        {
            this.p = p;
            this.diameter = diameter;
            this.color = color;
            this.thickness = thickness;
        }
        
        public Circle scale(double factor)
        {
            int x0 = p.x - diameter / 2;
            int y0 = p.y - diameter / 2;
            
            return new Circle(
                    new Point(x0 + (int)(factor * (p.x - x0)),
                            y0 + (int)(factor * (p.y - y0))
                    ),
                    (int)(factor * diameter),
                    color, thickness
            );
        }
    }
    
    private class RandomRectangles extends Artwork
    {
        // Instance variables.
        ArrayList<RectangleWithFlair> rectangles;
        
        // Constructor.
        public RandomRectangles()
        {
            RandomPlus r = new RandomPlus();

            int count = 10 + r.nextInt(40);
            
            this.rectangles = new ArrayList<>(count);

            for (int i = 0; i < count; i++)
            {
                rectangles.add(new RectangleWithFlair(
                        r.nextRectWithin(this.getPreferredSize()),
                        r.nextColor(),
                        1 + r.nextInt(20)
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
            double scaleFactor = GUITools.getMinScaleFactorBetween(
                    g.getClipBounds(), getPreferredSize());
            
            for (RectangleWithFlair rect : rectangles)
            {
                RectangleWithFlair scaled = rect.scale(scaleFactor);
                ((Graphics2D)g).setStroke(new BasicStroke(scaled.thickness));
                g.setColor(scaled.color);
                g.drawRect(scaled.p.x, scaled.p.y, scaled.width, scaled.height);
            }
        }
    }
    
    class RectangleWithFlair
    {
        // Instance variables.
        public final Point p;
        public int width;
        public int height;
        public final Color color;
        public final int thickness;

        // Constructor.
        public RectangleWithFlair(Rectangle bounds, Color color, int thickness)
        {
            this.p = new Point(bounds.x, bounds.y);
            this.width = bounds.width;
            this.height = bounds.height;
            this.color = color;
            this.thickness = thickness;
        }
        
        public RectangleWithFlair scale(double factor)
        {
            return new RectangleWithFlair(
                    new Rectangle(p.x, p.y,
                            (int)(factor * width), 
                            (int)(factor * height)
                    ),
                    color, thickness
            );
        }
    }
}