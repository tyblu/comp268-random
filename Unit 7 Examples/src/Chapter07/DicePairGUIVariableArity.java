package Chapter07;

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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
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
 * Version      2.1
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 6: Introduction to GUI Programming</a>
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 7: Arrays and ArrayLists</a>
 */
public class DicePairGUIVariableArity
{
/* -------------------------------------------------------------------------- */
    public static void call() { call( new String[] { "" } ); }
    
    public static void call(String[] args)
    {
        new DicePairGUIVariableArity();
    }
/* -------------------------------------------------------------------------- */

    // Constants.
    private static final boolean PRINT_TO_STD_OUT = true;
    private static final int DICE_COUNT = 7;
    
    /**
     * Constructor - Non-static context so I can run the program with objects.
     */
    public DicePairGUIVariableArity()
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
            super("Rolling A Bunch Of Dice");
            setContentPane(new RollingDicePanel());
            setJMenuBar(new RollingDiceMenuBar());
            setMinimumSize(new Dimension(256 + 256/8, 256 + 256/8 + getTaskbarHeight()));
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
        private Dice[] dice;
        private int diceCount;
        
        // Constructor.
        public RollingDicePanel()
        {
            this.diceCount = 2;
            this.dice = new Dice[diceCount];
            for (int i=0; i<diceCount; i++)
                this.dice[i] = new Dice();

            setBackground(Color.WHITE);
            
            setLayout( new FlowLayout(FlowLayout.CENTER, 256/16, 256/16) );
            add(dice);
            
            resetRollCounts();
            roll();
            
            addMouseListener( new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent evt) 
                    { 
                        if (evt.isMetaDown())   // Right-click.
                            resetRollCounts();
                        roll(); 
                    }
            });
        }
        
        // Methods
        /**
         * Do a new dice roll.
         */
        private void roll()
        {
            for (Dice d : this.dice)
                d.roll();
            
            if (PRINT_TO_STD_OUT)
            {
                System.out.printf("Roll #%d: ", dice[0].getRollCount());
                for (int i=0; i<this.dice.length; i++)
                {
                    System.out.print(this.dice[i].getValue());
                    if (i+2<this.dice.length)
                        System.out.print(", ");
                    else if (i+1<this.dice.length)
                    {
                        if (this.dice.length > 2)
                            System.out.print(",");
                        System.out.print(" and ");
                    }
                    else
                        System.out.printf(" gives %d%n", getDiceSum());
                }
            }
        }
        
        private int getDiceSum()
        {
            int sum = 0;
            for (Dice d : dice)
                sum += d.getValue();
            return sum;
        }
        
        private void add(Dice... dice)
        {
            for (Dice d : dice)
                add(d);
        }
        
        private void resetRollCounts()
        {
            for (Dice d : this.dice)
                d.resetRollCount();
        }
        
        public void addDice() 
        {
            System.out.println("Adding dice...");
            
            setDiceCount(getDiceCount() + 1);
            Dice[] temp = this.dice;
            this.dice = new Dice[getDiceCount()];
            System.arraycopy(temp, 0, this.dice, 0, temp.length);
            dice[dice.length-1] = new Dice();
            add(dice[dice.length-1]);
            
            revalidate();
//            repaint();
        }
        
        public void removeDice() 
        {
            System.out.println("Removing dice...");
            
            setDiceCount(getDiceCount() - 1);
            Dice[] temp = this.dice;
            remove(dice[dice.length-1]);
            this.dice = new Dice[getDiceCount()];
            System.arraycopy(temp, 0, dice, 0, dice.length);
            
            revalidate();
            repaint();
        }
        
        public int getDiceCount() { return this.diceCount; }
        
        private void setDiceCount(int diceCount){ this.diceCount = diceCount; }
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
                drawSpot(g, 0, 8);
                break;
            case 3:
                drawSpot(g, 0, 4, 8);
                break;
            case 4:
                drawSpot(g, 0, 2, 6, 8);
                break;
            case 5:
                drawSpot(g, 0, 2, 4, 6, 8);
                break;
            case 6:
                drawSpot(g, 0, 2, 3, 5, 6, 8);
                break;
            default:    // All spots. Never happens for actual dice values 1-6.
                drawSpot(g, 0, 1, 2, 3, 4, 5, 6, 7, 8);
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
        private void drawSpot(Graphics g, int... spots)
        {
            int gap = (256 - 2*8 - 2*16 - 3*32)/2;  // = 56
            int[] col = new int[] {
                        256/2 - gap - 32/2,
                        256/2 - 32/2,
                        256/2 + gap - 32/2
                    };
            int[] row = col;
            
            g.setColor(Color.BLACK);
            
            for (int spot : spots)
            {
                switch (spot)
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
    
    private class RollingDiceMenuBar extends JMenuBar
    {
        public RollingDiceMenuBar()
        {
            JButton addDiceButton = new JButton("Add Dice");
            addDiceButton.addActionListener( new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent evt)
                        {
                            ((RollingDicePanel)getParent().getComponent(0))
                                    .addDice();
                        }
                    });
            add(addDiceButton);

            JButton removeDiceButton = new JButton("Remove Dice");
            removeDiceButton.addActionListener( new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent evt)
                        {
                            ((RollingDicePanel)getParent().getComponent(0))
                                    .removeDice();
                        }
                    });
            add(removeDiceButton);
        }
    }
    
    // Methods
    private static Dimension getScreenSize()
    {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
    
    private static int getTaskbarHeight()
    {
        return getScreenSize().height - 
                (GraphicsEnvironment
                        .getLocalGraphicsEnvironment()
                        .getMaximumWindowBounds()
                ).height;
    }
}
