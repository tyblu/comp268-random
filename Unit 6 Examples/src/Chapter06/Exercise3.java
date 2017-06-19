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
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * <h1>Chapter 6 - Exercise 3</h1>
 * 
 * Rolls and shows the resultant pair of dice values on a screen. Click the
 * window to roll again.
 * 
 * <h2>Problem Statement</h2>
 * <p>Write a program that shows a pair of dice. When the user clicks on the
 * panel in the program, the dice should be rolled (that is, the dice should be
 * assigned newly computed random values). Each die should be drawn as the
 * square showing from 1 to 6 dots. Since you have to draw two dice, it's a
 * good idea to write a subroutine like 
 * {@code void drawDie(Graphics g, int val, int x, int y)} to draw a die at the
 * specified {@code (x,y)} coordinates. The second parameter, @{code val},
 * specifies the value that is showing on the die. Assume that the size of the
 * panel is 100 by 100 pixels.
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
public class Exercise3
{
/* -------------------------------------------------------------------------- */
    public static void call() { call( new String[] { "" } ); }
    
    public static void call(String[] args)
    {
        new Exercise3();
    }
/* -------------------------------------------------------------------------- */

    /**
     * Constructor - Non-static context so I can run the program with objects.
     */
    public Exercise3()
    {
        CenteredWindow window = new CenteredWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.requestFocusInWindow();
    }
    
    // Nested classes
    private class CenteredWindow extends JFrame
    {
        /**
         * Constructor.
         */
        public CenteredWindow()
        {
            super("Chapter 6 - Exercise #3: Rolling A Pair Of Dice");
            setContentPane(new RollingDicePanel());
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
    
    private class RollingDicePanel extends JPanel
    {
        // Instance variables.
        private DiceAnimated dice1, dice2;
        
        // Constructor.
        public RollingDicePanel()
        {
            this.dice1 = new DiceAnimated();
            this.dice2 = new DiceAnimated();
            
            setSize(0.5, 0.5);

            setBackground(Color.WHITE);
            
            setLayout( new GridLayout(1, 0) );
            add(dice1);
            add(dice2);
            

            addMouseListener( new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent evt) { roll(); }
            });
            
        }
        
        // Methods
        /**
         * Do a new dice roll.
         */
        private void roll()
        {
            // 
        }
        
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
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            drawDice(g, new DiceAnimated[]{ dice1, dice2 });
        }
        
        private void drawDice(Graphics g, DiceAnimated[] diceArray)
        {
            // split up panel -- leave to LayoutManager?
            
            // draw dice
            for (DiceAnimated dice : diceArray)
            {
                
            }
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
    
    private class DiceAnimated extends Dice
    {
        // Instance variables.
        private DiceImage[] faces;
        private int sequenceIndex;
        private DiceFace currentFace;
        
    }
    
    private class Dice
    {
        // Instance variables.
        private int sides;
        private int value;
        private int rollCount;
        private Random r;
        
        // Constructors.
        public Dice(int sides)
        {
            this.sides = sides;
            this.r = new Random();
            this.value = r.nextInt(this.sides) + 1;
            this.rollCount = 1;
        }
        
        public Dice() { this(6); }
        
        // Methods.
        public int roll()
        {
            setValue(r.nextInt(sides) + 1);
            rollCount++;
            return getValue();
        }
        
        public void resetRollCount() { setRollCount(0); };
        public void incrementRollCount() { setRollCount(getRollCount() + 1); }
        public void decrementRollCount() { setRollCount(getRollCount() - 1); }
        
        // Getters.
        public int getValue() { return this.value; }
        public int getRollCount() { return this.rollCount; }
        
        // Setters.
        private void setValue(int value) { this.value = value; }
        private void setRollCount(int rollCount) { this.rollCount = rollCount; }
    }
    
    private class DiceFace extends JPanel
    {
        
    }
    
    private class DiceFaces extends RoundRectangle2D.Double
    {
        // Instance variables.
        private final int faceSize;
        private final int spotSize;
        private final int border;
        private final Point[] spotSpots;
        private Image[] faces;
        
        public DiceFaces()
        {
            this.faceSize = 256;
            this.spotSize = faceSize / 8;
            this.border = faceSize / 32;
            this.spotSpots = getSpotSpots();
            
            for (int i=0; i<6; i++)
                faces[i] = new BufferedImage(faceSize, faceSize,
                        BufferedImage.TYPE_INT_RGB);
        }
        
        // Methods.
        public Image drawFaces(int count)
        {
            Graphics g = faces[count-1].getGraphics();
            
            drawBorder(g);
            drawBackground(g);
            drawSpots(g);
            
            g.dispose();
            return faces[count-1];
        }
        
        private void drawBorder(Graphics g)
        {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, w, h);
        }
        
        private void drawBackground(Graphics g)
        {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, w - border, h - border);
        }
        
        private void drawSpots(Graphics g)
        {
            switch (count)
            {
            case 1:
                drawSpot(g, "3");
                break;
            case 2:
                drawSpot
            }
        }
        
        // Getters.
        /**
         * Computes spot locations based on face and spot sizes.     _________
         * @return array of Point[] for spot locations in order      | 0 1 2 |
         *      shown in the diagram (0-6).                          |   3   |
         *                                                           |_4_5_6_|
         */
        private Point[] getSpotSpots()
        {
            Point[] locs = new Point[6];
            
            // Point 3 is in the very center
            locs[3] = new Point(faceSize / 2, faceSize / 2);
            
            // Compute ideal gap between spots and edge
            int gap = (int)((faceSize - 2* border - 2* spotSize) / (double)4);
            
            // Points 1 and 5
            locs[1] = new Point(locs[3].x, locs[3].y + gap + spotSize / 2);
            locs[5] = new Point(locs[3].x, locs[3].y - gap - spotSize / 2);
            
            // Points 0, 2, 4, and 6
            locs[0] = new Point(locs[1].x - gap - spotSize / 2, locs[1].y);
            locs[2] = new Point(locs[1].x + gap + spotSize / 2, locs[1].y);
            locs[4] = new Point(locs[5].x - gap - spotSize / 2, locs[5].y);
            locs[6] = new Point(locs[5].x + gap + spotSize / 2, locs[5].y);
            
            return locs;
        }
        
    }
    
    // Methods
    private static Dimension getScreenSize()
    {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
