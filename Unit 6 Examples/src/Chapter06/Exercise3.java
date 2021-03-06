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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
 * Version      1.0
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

    // Constants.
    private static final boolean PRINT_TO_STD_OUT = false;
    
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
        private Dice dice1, dice2;
        
        // Constructor.
        public RollingDicePanel()
        {
            this.dice1 = new Dice();
            this.dice2 = new Dice();
            
            setSize(0.5, 0.5);

            setBackground(Color.WHITE);
            
            setLayout( new GridBagLayout() );
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(256/4, 256/4, 256/4, 256/4);
            add(dice1, c);
            add(dice2, c);
            
            dice1.resetRollCount();
            dice2.resetRollCount();
            roll();
            
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
            dice1.roll();
            dice2.roll();
            
            if (PRINT_TO_STD_OUT)
                System.out.printf("%nRoll #%d: %d & %d gives %d%n",
                        dice1.getRollCount(),
                        dice1.getValue(),
                        dice2.getValue(),
                        (dice1.getValue() + dice2.getValue())
                );
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
    }
    
    private class Dice extends JPanel
    {
        // Instance variables.
        private int value;
        private int rollCount;
        private Random r;
        
        // Constructors.
        public Dice()
        {
            this.r = new Random();
            this.value = r.nextInt(6) + 1;
            this.rollCount = 1;
            
            setPreferredSize(new Dimension(256, 256));
        }
        
        // Methods.
        public int roll()
        {
            setValue(r.nextInt(6) + 1);
            rollCount++;
            
            repaint();
            
            return getValue();
        }
        
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            g.setColor(Color.BLACK);
            g.fill3DRect(0, 0, 256, 256, true);
            g.setColor(Color.WHITE);
            g.fill3DRect(16, 16, 256-2*16, 256-2*16, false);
            drawSpots(g, value);
        }
        
        private void drawSpots(Graphics g, int value)
        {
            switch (value)
            {
            case 1:
                drawSpot(g, 4);
                break;
            case 2:
                drawSpot(g, 0);
                drawSpot(g, 8);
                break;
            case 3:
                drawSpots(g, 2);
                drawSpot(g, 4);
                break;
            case 4:
                drawSpots(g, 2);
                drawSpot(g, 2);
                drawSpot(g, 6);
                break;
            case 5:
                drawSpots(g, 4);
                drawSpot(g, 4);
                break;
            case 6:
                drawSpots(g, 4);
                drawSpot(g, 3);
                drawSpot(g, 5);
                break;
            default:    // All spots. Never happens for actual dice values 1-6.
                drawSpots(g, 6);
                drawSpot(g, 1);
                drawSpot(g, 4);
                drawSpot(g, 7);
                break;
            }
        }
        
        /**
         *  _______
         * | 0 1 2 |
         * | 3 4 5 |
         * |_6_7_8_|
         * 
         * dice width 256, border 8, edge gap 16, spot diameter 32, gap computed
         */
        private void drawSpot(Graphics g, int spotNumber)
        {
            int gap = (256 - 2*8 - 2*16 - 3*32)/2;  // = 56
            int[] col = new int[] {
                        256/2 - gap - 32/2,
                        256/2 - 32/2,
                        256/2 + gap - 32/2
                    };
            int[] row = col;
            
            g.setColor(Color.BLACK);
            switch (spotNumber)
            {
            case 0:
                g.fillOval(col[0], row[0], 32, 32);
                break;
            case 1:
                g.fillOval(col[1], row[0], 32, 32);
                break;
            case 2:
                g.fillOval(col[2], row[0], 32, 32);
                break;
            case 3:
                g.fillOval(col[0], row[1], 32, 32);
                break;
            case 4:
                g.fillOval(col[1], row[1], 32, 32);
                break;
            case 5:
                g.fillOval(col[2], row[1], 32, 32);
                break;
            case 6:
                g.fillOval(col[0], row[2], 32, 32);
                break;
            case 7:
                g.fillOval(col[1], row[2], 32, 32);
                break;
            case 8:
                g.fillOval(col[2], row[2], 32, 32);
                break;
            default:
                break;
            }
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
    
    // Methods
    private static Dimension getScreenSize()
    {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}
