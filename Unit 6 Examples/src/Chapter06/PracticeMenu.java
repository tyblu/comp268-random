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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Example GUI with a menu. Version 0.21 is edited to include a {code main()}
 * method so someone can run it as a program with only this .java file.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 15, 2017
 * Version      0.21
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 6: Introduction to GUI Programming</a>
 */
public class PracticeMenu extends JPanel
{
/* -------------------------------------------------------------------------- */
//    public static void call() { call( new String[] { "" } ); }
    
    public static void main(String[] args)
    {
        JFrame window = new JFrame("JFrame window");
        
        PracticeMenu panel = new PracticeMenu();
        
        window.setJMenuBar(panel.getMenuBar());
        window.setContentPane(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(400,200);
        window.setSize(800,600);
        window.setVisible(true);
    }
/* -------------------------------------------------------------------------- */
    
    // Constants.
    
    // Instance variables.
    private final RandomPlus r;
    
    // components
    private JMenuBar menuBar;
    
    // Constructor.
    public PracticeMenu()
    {
        this.r = new RandomPlus();
        
        reset();
    }
    
    // Methods.
    private void reset()
    {
        removeAll();
        
        setBackground(Color.WHITE);
        
        JMenu menuTools = new JMenu("Tools");
        
        JMenuItem randomizeCommand = new JMenuItem("randomize");
        randomizeCommand.addMouseListener((ML.Pressed)evt -> randomize());
        menuTools.add(randomizeCommand);
        
        JMenuItem resetCommand = new JMenuItem("reset");
        resetCommand.addMouseListener((ML.Pressed)evt -> reset());
        menuTools.add(resetCommand);
        
        JMenuItem rolloverCommand = new JMenuItem("rollover");
        rolloverCommand.addMouseListener((ML.Entered)evt -> rollOn());
        rolloverCommand.addMouseListener((ML.Exited)evt -> rollOff());
        rolloverCommand.addMouseListener((ML.Pressed)evt -> reset());
        menuTools.add(rolloverCommand);
        
        setMenuBar(new JMenuBar());
        menuBar.add(menuTools);
    }
    
    private void randomize()
    {
        setBackground(this.r.nextColor());
    }
    
    private void rollOn()
    {
        randomize();
    }
    
    private void rollOff()
    {
        randomize();
    }
    
    // Getters.
    /**
     * Used to send JMenuBar item to main routine so it can be set.
     * @return JMenuBar menuBar
     */
    public JMenuBar getMenuBar()
    {
        return this.menuBar;
    }
    
    // Setters.
    private void setMenuBar(JMenuBar menuBar)
    {
        this.menuBar = menuBar;
    }
    
    // Nested Classes, Interfaces
    
    /**
     * An interface that gives "deaf" default listener methods (Java 8).
     * It makes writing multiple functional interfaces for MouseListener
     * easier, as you don't have to define the default implementations for the
     * functions you aren't using.
     */
    interface MouseDeafListener extends MouseListener
    {
        @Override
        default void mouseClicked(MouseEvent e) {}
        @Override
        default void mouseEntered(MouseEvent e) {}
        @Override
        default void mouseExited(MouseEvent e) {}
        @Override
        default void mousePressed(MouseEvent e) {}
        @Override
        default void mouseReleased(MouseEvent e) {}
    }
    
    /**
     * Collection of functional interfaces extending MouseListener, using
     * {@link MouseDeafListener} to shorten them, for use with lambdas (Java 8).
     */
    interface ML
    {
        interface Pressed extends MouseDeafListener
        {
            @Override
            abstract void mousePressed(MouseEvent e);
        }

        interface Clicked extends MouseDeafListener
        {
            @Override
            abstract void mouseClicked(MouseEvent e);
        }

        interface Entered extends MouseDeafListener
        {
            @Override
            abstract void mouseEntered(MouseEvent e);
        }

        interface Exited extends MouseDeafListener
        {
            @Override
            abstract void mouseExited(MouseEvent e);
        }

        interface Released extends MouseDeafListener
        {
            @Override
            abstract void mouseReleased(MouseEvent e);
        }
    }

    class RandomPlus extends Random
    {
        // Methods.
        public Color nextColor()
        {
            return new Color(nextFloat(), nextFloat(), nextFloat());
        }
    }
}