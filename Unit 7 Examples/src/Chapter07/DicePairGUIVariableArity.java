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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
            RollingDicePanel content = new RollingDicePanel();
            setContentPane(content);
            content.setWindow(this);
            setJMenuBar(new RollingDiceMenuBar());
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
        private CenteredWindow window;
        
        // Constructor.
        public RollingDicePanel()
        {
            this.diceCount = 2;
            
            this.dice = new Dice[diceCount];
            for (int i=0; i<diceCount; i++)
                this.dice[i] = new Dice();

            setBackground(Color.WHITE);
            
            setLayout(new FlowLayout(FlowLayout.CENTER, 16, 16));
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
            
//            resizeMinimumWindow();
            
            revalidate();
            window.pack();
            
        }
        
        public void removeDice() 
        {
            System.out.println("Removing dice...");
            
            if (getDiceCount() > 1)
            {
                setDiceCount(getDiceCount() - 1);
                Dice[] temp = this.dice;
                remove(dice[dice.length-1]);
                this.dice = new Dice[getDiceCount()];
                System.arraycopy(temp, 0, dice, 0, dice.length);

//                resizeMinimumWindow();

                revalidate();
                window.pack();
                repaint();
            }
            else    // Spaz out.
            {
                Graphics g = dice[0].getGraphics();
                dice[0].drawSpots(g, 7);
                g.dispose();
            }
        }
        
//        private void resizeWindow()
//        {
//            window.setMinimumSize(new Dimension(
//                    getDiceCount() * diceW * 17/16 + diceW * 2/16,
//                    rows * diceW * 17/16 + diceW * 2/16 + getTaskbarHeight()
//            ));
//        }
        
        private void toggleCrazyColors()
        {
            //
        }
        
        public int getDiceCount() { return this.diceCount; }
        
        private void setDiceCount(int diceCount)
        {
            if (diceCount > 1)
                this.diceCount = diceCount;
            else
                this.diceCount = 1;
        }
        
        public void setWindow(CenteredWindow window) { this.window = window; }

        private void setDiceAttributes(String attribute, Object value)
        {
            for (Dice d : dice)
                d.setDiceAttributes(attribute, value);
        }
    }
    
    private class Dice extends JPanel
    {
        // Instance variables.
        private int value;
        private int rollCount;
        private Random r;
        private DiceSizes diceSizes;
        
        // Constructors.
        public Dice()
        {
            super();
            
            this.diceSizes = new DiceSizes(256);
            
            this.r = new Random();
            this.value = r.nextInt(6) + 1;
            this.rollCount = 1;
            
            setOpaque(false);
            setPreferredSize(diceSizes.getDim());
            setMinimumSize((new DiceSizes(0)).getDim());
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
            
            int size1 = getWidth();
            int size2 = size1 - 2*16;
            
            g.setColor(Color.BLACK);
            g.fillRoundRect(0, 0, size1, size1, size1/4, size1/4);
            g.setColor(Color.WHITE);
            g.fillRoundRect(16, 16, size2, size2, size2/4, size2/4);
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
            int spotDiam = diceSizes.getSpotDiam();
            int border = diceSizes.getBorder();
            int edgeGap = diceSizes.getEdgeGap();
            int spotGap = diceSizes.getSpotGap();
            int diceSize = diceSizes.getSize();
            int spotSpacing = (diceSize - spotDiam) /2 - border - edgeGap;
            int[] col = new int[] {
                        diceSize/2 - spotSpacing - spotDiam /2,
                        diceSize/2 - spotDiam /2,
                        diceSize/2 + spotSpacing - spotDiam /2
                    };
            int[] row = col;
            
            g.setColor(Color.BLACK);
            
            for (int spot : spots)
            {
                if (spots.length > 6) { g.setColor(getRandomColor()); }
                
                switch (spot)
                {
                case 0:
                    g.fillOval(col[0], row[0], spotDiam, spotDiam);
                    break;
                case 1:
                    g.fillOval(col[1], row[0], spotDiam, spotDiam);
                    break;
                case 2:
                    g.fillOval(col[2], row[0], spotDiam, spotDiam);
                    break;
                case 3:
                    g.fillOval(col[0], row[1], spotDiam, spotDiam);
                    break;
                case 4:
                    g.fillOval(col[1], row[1], spotDiam, spotDiam);
                    break;
                case 5:
                    g.fillOval(col[2], row[1], spotDiam, spotDiam);
                    break;
                case 6:
                    g.fillOval(col[0], row[2], spotDiam, spotDiam);
                    break;
                case 7:
                    g.fillOval(col[1], row[2], spotDiam, spotDiam);
                    break;
                case 8:
                    g.fillOval(col[2], row[2], spotDiam, spotDiam);
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
        private Color getRandomColor()
        {
            return new Color(
                    r.nextFloat(), r.nextFloat(), r.nextFloat()
            );
        }
        
        // Setters.
        private void setValue(int value) { this.value = value; }
        private void setRollCount(int rollCount) { this.rollCount = rollCount; }

        public void setDiceAttributes(String attribute, Object value)
        {
            switch (attribute)
            {
            case "border":
                diceSizes.setBorder((int)value);
                break;
            case "edgeGap":
                diceSizes.setEdgeGap((int)value);
                break;
            case "spotDiam":
                diceSizes.setSpotDiam((int)value);
                break;
            case "spotGap":
                diceSizes.setSpotGap((int)value);
                break;
            case "size":
                diceSizes.setSize((int)value);
                break;
            default:
                break;
            }
        }
    }
    
    private class RollingDiceMenuBar extends JMenuBar
    {
        public RollingDiceMenuBar()
        {
            RollingDicePanel panel = 
                    (RollingDicePanel)getParent().getComponent(0);
            
            JButton addDiceButton = new JButton("Add Dice");
            addDiceButton.addActionListener( new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent evt)
                        {
                            panel.addDice();
                        }
                    });
            add(addDiceButton);

            JButton removeDiceButton = new JButton("Remove Dice");
            removeDiceButton.addActionListener( new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent evt)
                        {
                            panel.removeDice();
                        }
                    });
            add(removeDiceButton);
            
            /* Just a test for Eck p336. */
            add(createMenu("test", "1", null, "2 after separator", "3"));
            /* End test. Remove the above as well as createMenu(...), later. */
            
            JMenu dropDownMenu = new JMenu("OPTIONS");
            add(dropDownMenu);
            
            JCheckBoxMenuItem crazyCheckbox = 
                    new JCheckBoxMenuItem("cR@zeE", false);
            crazyCheckbox.addActionListener( new ActionListener()
                    {
                        @Override
                        public void actionPerformed(ActionEvent evt)
                        {
                            panel.toggleCrazyColors();
                        }
                    });
            dropDownMenu.add(crazyCheckbox);
            
            JSlider diceSizeSlider = 
                    new JSlider(0, getScreenSize().height /2, 256);
            diceSizeSlider.addChangeListener( new ChangeListener()
                    {
                        @Override
                        public void stateChanged(ChangeEvent e)
                        {
                            panel.setDiceAttributes("size",
                                    ((JSlider)e.getSource()).getValue()
                            );
                        }
                    });
            dropDownMenu.add(diceSizeSlider);
            
            
        }
        
        /**
         * Creates a JMenu. The names for the JMenuItems in the menu are given
         * as an array of strings.
         * @param menuName the name for the JMenu that is to be created.
         * @param itemNames an array holding the text that appears in each
         *      JMenuItem. If a null value appears in the array, the
         *      corresponding JMenuItem will be a separator.
         * @return the menu that has been created and filled with items.
         */
        public JMenu createMenu(String menuName, String... itemNames)
        {
            JMenu menu = new JMenu(menuName);
            
            for (String itemName : itemNames)
            {
                if (itemName == null)
                    menu.addSeparator();
                else
                {
                    JMenuItem item = new JMenuItem(itemName);
                    menu.add(item);
                }
            }
            
            return menu;
        }
    }
    
    private class DiceSizes
    {
        // Constants.
        private static final int MIN_BORDER = 4;
        private static final int MIN_EDGEGAP = 4;
        private static final int MIN_SPOTDIAM = 8;
        private static final int MIN_SPOTGAP = 4;
        
        // Instance variables.
        private int border;
        private int edgeGap;
        private int spotDiam;
        private int spotGap;
        private int size;       // = 2*(border+edgeGap+spotGap)+3*spotDiam
        private Dimension dim;  // = size x size
        
        // Constructor.
        public DiceSizes(int border, int edgeGap, int spotDiam, int spotGap)
        {
            this.border = Math.max(border, MIN_BORDER);
            this.edgeGap = Math.max(edgeGap, MIN_EDGEGAP);
            this.spotDiam = Math.max(spotDiam, MIN_SPOTDIAM);
            this.spotGap = Math.max(spotGap, MIN_SPOTGAP);
            
            recalculate();
        }
        
        public DiceSizes(int size) { setSize(size); }
        
        // Methods.
        private void recalculate()
        {
            this.size = 3 * spotDiam + 2 * (border + edgeGap + spotGap);
            this.dim = new Dimension(size, size);
        }
        
        // Setters
        public void setBorder(int border)
        {
            this.border = Math.min(border, 4);
            recalculate();
        }
        
        public void setEdgeGap(int edgeGap)
        {
            this.edgeGap = Math.min(edgeGap, 4);
            recalculate();
        }
        
        public void setSpotDiam(int spotDiam)
        {
            this.spotDiam = Math.min(spotDiam, 8);
            recalculate();
        }
        
        public void setSpotGap(int spotGap)
        {
            this.spotGap = Math.min(spotGap, 4);
            recalculate();
        }
        
        public void setSize(int size)
        {
            this.border = (int)((size * 8) / (double)256);
            this.edgeGap = (int)((size * 16) / (double)256);
            this.spotDiam = (int)((size * 48) / (double)256);
            this.spotGap = size 
                    - (int)((size * 8) / (double)256)
                    - (int)((size * 16) / (double)256)
                    - (int)((size * 48) / (double)256);
            recalculate();
        }
        
        // Getters.
        public int getBorder() { return this.border; }
        public int getEdgeGap() { return this.edgeGap; }
        public int getSpotDiam() { return this.spotDiam; }
        public int getSpotGap() { return this.spotGap; }
        public int getSize() { return this.size; }
        public Dimension getDim() { return this.dim; }
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
