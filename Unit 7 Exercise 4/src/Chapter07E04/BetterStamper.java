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
package Chapter07E04;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * <h1>Eck v7, Chapter 7, Exercise #4</h1>
 * 
 * Revise the program SimpleStamperWithDrag from Exercise #1 in chapter 6 such
 * that the panel stores paint stroke information in an ArrayList and redraws
 * them correctly.
 * 
 * Depends on {@link Chapter07E04.GUITools}, {@link Chapter07E04.Seer}, 
 * and {@link Chapter07E04.RunBetterStamper}.
 *
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 24, 2017
 * Version      1.0
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 7: Arrays and ArrayLists</a>
 */
public class BetterStamper
{
/* -------------------------------------------------------------------------- */
    public static void main(String[] args) { new BetterStamper(); }
/* -------------------------------------------------------------------------- */
    
    // Instance variables.
    private final JFrame window;
    private final JPanel content;
    private final ArrayList<Shape> shapes;
    
    // Constants.
    private static final double SHAPE_W = 300;
    private static final double SHAPE_H = 200;
    private static final Color FILL_ELLIPSE = Color.BLUE;
    private static final Color FILL_RECT = Color.RED;
    
    /**
     * Constructor.
     */
    public BetterStamper()
    {
        this.window = new JFrame("BetterStamper");
        
        this.content = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                
                for (Shape shape : shapes)
                {
                    if (shape instanceof Rectangle2D.Double)
                    {
                        Rectangle2D.Double rect = (Rectangle2D.Double)shape;
                        g.setColor(FILL_RECT);
                        g.fillRect((int)rect.getX(), (int)rect.getY(), 
                                (int)rect.getWidth(), (int)rect.getHeight());
                        g.setColor(Color.BLACK);
                        g.drawRect((int)rect.getX(), (int)rect.getY(), 
                                (int)rect.getWidth(), (int)rect.getHeight());
                    }
                    else if (shape instanceof Ellipse2D.Double)
                    {
                        Ellipse2D.Double rect = (Ellipse2D.Double)shape;
                        g.setColor(FILL_ELLIPSE);
                        g.fillOval((int)rect.getX(), (int)rect.getY(), 
                                (int)rect.getWidth(), (int)rect.getHeight());
                        g.setColor(Color.BLACK);
                        g.drawOval((int)rect.getX(), (int)rect.getY(), 
                                (int)rect.getWidth(), (int)rect.getHeight());
                    }
                    else
                        System.out.println("Unknown shape: " + shape);  // debug
                }
            }
        };
        content.setPreferredSize(new Dimension(
                (int)(GUITools.getScreenSize().width * 0.8),
                (int)(GUITools.getScreenSize().height * 0.8)
        ));
        content.setBackground(Color.WHITE);
        content.setOpaque(true);
        window.setContentPane(content);
        
        JMenuBar menuBar = new JMenuBar();
        (new BetterStamperMenu()).addJComponentsTo(menuBar);
        window.setJMenuBar(menuBar);
        
        this.shapes = new ArrayList<>();
        
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
        window.pack();
        window.setLocation(
                (GUITools.getScreenSize().width - window.getWidth()) / 2, 
                (GUITools.getScreenSize().height - window.getHeight()) / 2
        );
        window.setVisible(true);
        
        MouseDraw l = new MouseDraw();
        content.addMouseListener(l);
        content.addMouseMotionListener(l);
    }
    
    // Methods.
    /**
     * Clears JPanel.
     */
    public void clear()
    {
        shapes.clear();
        content.repaint();
    }
    
    // Nested classes.
    /**
     * Custom MouseListener, an extension of MouseAdapter.
     */
    private class MouseDraw extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent evt)
        {
            if (evt.isShiftDown())      // shift key -> clear screen
                shapes.clear();
            else if (evt.isMetaDown())
                shapes.add(new Ellipse2D.Double(
                        evt.getX() - SHAPE_W / 2, 
                        evt.getY() - SHAPE_H / 2,
                        SHAPE_W, SHAPE_H));
            else
                shapes.add(new Rectangle2D.Double(
                        evt.getX() - SHAPE_W / 2,
                        evt.getY() - SHAPE_H / 2,
                        SHAPE_W, SHAPE_H));
            
            content.repaint();
        }
        
        @Override
        public void mouseDragged(MouseEvent evt)
        {
            if (evt.isShiftDown())      // shift key -> clear screen
                shapes.clear();
            else if (evt.isMetaDown())
                shapes.add(new Ellipse2D.Double(
                        evt.getX() - SHAPE_W / 2, 
                        evt.getY() - SHAPE_H / 2,
                        SHAPE_W, SHAPE_H));
            else
                shapes.add(new Rectangle2D.Double(
                        evt.getX() - SHAPE_W / 2, 
                        evt.getY() - SHAPE_H / 2,
                        SHAPE_W, SHAPE_H));
            
            content.repaint();
        }
    }
    
    
    /**
     * Custom JMenuBar.
     */
    private class BetterStamperMenu
    {
        // Instance variables.
        ArrayList<JComponent> items;
        
        /**
        * Constructor.
        */
       public BetterStamperMenu()
       {
           this.items = new ArrayList<>();
           
           /* "edit" JMenu */
           JMenu edit = new JMenu("edit");
           
           JMenuItem undo = new JMenuItem("undo");
//           undo.addActionListener(evt -> undo()); // undo() not implemented
           edit.add(undo);
           
           items.add(edit);
           /* end "edit" JMenu */
           
           JButton clear = new JButton("clear");
           clear.addActionListener(evt -> clear());
           items.add(clear);
       }
       
       // Methods.
       public void addJComponentsTo(JMenuBar jMenuBar)
       {
           items.forEach(item -> jMenuBar.add(item));
       }
    }
}
