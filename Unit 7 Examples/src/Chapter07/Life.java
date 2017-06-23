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
package Chapter07;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.Color;
import java.util.Random;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;


/**
 * <h1>Conway's Game Of Life</h1>
 * <p>A cell can be "dead" or "alive". If the cell is alive, and has 2 or 3
 * living neighbours, then the cell remains alive; otherwise, it dies. (It dies 
 * of loneliness if it has 0 or 1 living neighbors, and of overcrowing if it
 * has more than 3 living neighbours.) If the cell is dead, and has 3 living 
 * neighbours, then the cell becomes alive.
 *
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 21, 2017
 * Version      1.0
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 7: Arrays and ArrayLists</a>
 */
public class Life
{
    // Nearly-global instance variables, and constants.
    private int nextCellIDNumber = 0x1000;
    private static final boolean ENABLE_DEBUG = true;
    
    // Constructor.
    public Life()
    {
        CenteredWindow window = new CenteredWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.requestFocusInWindow();
    }
    
    /**
     * Main window.
     */
    private class CenteredWindow extends JFrame
    {
        // Constructor.
        public CenteredWindow()
        {
            super("Conways Game of Life...");
            
            GameMenuBar menuBar = new GameMenuBar();
            setJMenuBar(menuBar);
            
            GameGrid panel = new GameGrid();
            setContentPane(panel);
            
            pack();
            resetLocation();
        }
        
        // Methods.
        private void resetLocation()
        {
            setLocation(
                    (getScreenSize().width - getWidth()) / 2,
                    (getScreenSize().height - getHeight()) / 2
            );
        }
    }
    
    /**
     * Window content and game setup.
     */
    private class GameGrid extends JPanel
    {
        // Instance variables.
        private final Rectangle bounds;
        private Map<Point, Cell> cellMap;
        
        // Constructor.
        public GameGrid()
        {
            this.bounds = new Rectangle(25, 25);    // 30x30, top-left (0,0)
            this.cellMap = new HashMap<>(bounds.x * bounds.y);
            
            initialize();
        }
        
        // Methods.
        private void initialize()
        {
            setLayout( new GridBagLayout() );
            
            setBackground(Color.LIGHT_GRAY);
            
            /* Initialize Cells. */
            // Create cells, put in grid map, and set layout.
            Random r = new Random();
            for (Point p : getIteratorOver(bounds))
            {   
                boolean toBeOrNotToBe = r.nextBoolean();
                Cell cell = new Cell(determineEdge(p), toBeOrNotToBe);
                
                // Layout.
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = p.x;
                c.gridy = p.y;
                c.insets = new Insets(5,5,5,5);
                c.anchor = GridBagConstraints.CENTER;
                add(cell.getPanel(), c);
                
                cellMap.put(p, cell);
            }
            
            // Add neighbour data to cells, including Observers.
            for (Point p : getIteratorOver(bounds))
            {
                Cell cell = cellMap.get(p);
                ArrayList<Cell> neighbours = getNeighboursOf(p);
                
                for (Cell neighbour : neighbours)
                    cell.addNeighbour(neighbour);
                
//                cell.addObserver((Observer)(((JFrame)getParent()).getJMenuBar()));
            }
            
            // Add Cell Observables to JMenuBar Observer.
            
            // Make Cells Observable in random order.
            for (Point p : getShuffledIteratorOver(bounds))
                cellMap.get(p).toggleIsObservable();
        }
        
        public void reset()
        {
            removeAll();
            revalidate();
            this.cellMap = new HashMap<>(bounds.x * bounds.y);
            initialize();
        }
        
        private ArrayList<Cell> getNeighboursOf(Point p)
        {
            ArrayList<Cell> neighbours = new ArrayList<>();
            Rectangle r = new Rectangle(p.x - 1, p.y -1, 3, 3);
            
            ArrayList<Point> points = getIteratorOver(r);
            points.remove(p);
            for (Point point : points)
                if (bounds.contains(point))
                    neighbours.add(cellMap.get(point));
            
            return neighbours;
        }
        
        private Edge determineEdge(Point p) throws IllegalArgumentException
        {
            if (!bounds.contains(p))
                throw new IllegalArgumentException(p + " not on grid.");
            
            if (p.x == 0)                       // LHS
            {
                if (p.y == 0)
                    return Edge.TOP_LEFT;
                else if (p.y == bounds.height - 1)
                    return Edge.BOTTOM_LEFT;
                else
                    return Edge.LEFT;
            }
            else if (p.x == bounds.width -1)    // RHS
            {
                if (p.y == 0)
                    return Edge.TOP_RIGHT;
                else if (p.y == bounds.height - 1)
                    return Edge.BOTTOM_RIGHT;
                else
                    return Edge.BOTTOM;
            }
            else
                return Edge.NONE;
        }
    }
    
    private class GameMenuBar extends JMenuBar //implements Observer
    {
        // Instance variables.
        int alive, total;
        JButton resetButton;
        
        // Constructor.
        public GameMenuBar()
        {
            this.resetButton = new JButton("reset");
            resetButton.addActionListener(evt ->
                    ((GameGrid)getParent().getComponent(1)).reset());
            add(resetButton);
        }
        
//        // Methods.
//        @Override
//        public void update(Observable o, Object neighbourState)
//        {
//            if ((boolean)neighbourState)
//                alive++;
//            else
//                alive--;
//            
//            update();
//        }
//
//        private void update()
//        {
//            aliveLabel.setText("Alive: " + alive);
//            deadLabel.setText("Dead: " + (total - alive));
//        }
//        
//        // Setters.
//        public void setAlive(int alive) { this.alive = alive; }
//        public void setTotal(int total) { this.total = total; }
    }
    
    private class Cell extends Observable implements Observer
    {
        private boolean isAlive;
        private int aliveCount;
        private boolean isObservable;
        private final Edge edge;
        private final JPanel panel;
        private final String ID;
        private Timer tNotify, tRegular, tAnimate, tBlack, tWhite;
        private ArrayList<Cell> neighbours;
        
        // Constructor.
        public Cell(Edge edge, boolean isAlive)
        {
            this.isAlive = isAlive;
            this.panel = new JPanel();
            this.edge = Edge.NONE;
            this.isObservable = false;
            this.ID = Integer.toHexString(nextCellIDNumber++);
            this.tNotify = new Timer(250, e -> notifyObservers());
            this.tAnimate = new Timer(20, e -> animate(0));
            this.tBlack = new Timer(150, e -> panel.setBackground(Color.BLACK));
            tBlack.setRepeats(false);
            this.tWhite = new Timer(150, e -> panel.setBackground(Color.WHITE));
            tWhite.setRepeats(false);
            this.tRegular = new Timer(1000, e -> update(null,null));
            this.neighbours = new ArrayList<>();
            
            panel.setOpaque(true);
            panel.setPreferredSize(new Dimension(40,40));
            panel.setVisible(true);
            
            if (isAlive)
                panel.setBackground(Color.WHITE);
            else
                panel.setBackground(Color.BLACK);
            
            panel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
            
//            tRegular.start();
        }
        
        // Methods.
        /* Observable */
        public void kill()
        { 
            if (!isAlive)
                return;
            
            isAlive = false;
            panel.setBackground(Color.RED);
            tBlack.start();

            setChanged();
            
            if (isObservable)
                tNotify.start();
            
            getPanel().repaint();
        }
        
        public void revive()
        {
            if (isAlive)
                return;
            
            isAlive = true;
            panel.setBackground(Color.GREEN);
            tWhite.start();
            
            setChanged();
            
            if (isObservable)
                tNotify.start();
            
            getPanel().repaint();
        }
        
        @Override
        public void notifyObservers()
        {
            tNotify.stop();
            super.notifyObservers();
        }
        /* end Observable */
        
        /* Observer */
        @Override
        public void update(Observable o, Object neighbourState)
        {
            countLivingNeighbours();
            life();
        }
        /* end Observer */
        
        public boolean isAlive() { return isAlive; }
        
        /**
         * The main rules. If a cell is alive and has less than 2 or more than
         * 3 living neighbours, it dies; if it's dead and has exactly 3 living
         * neighbours, it comes back to life.
         */
        private void life()
        {
            if (!isAlive() && aliveCount == 3)
                revive();
            else if (isAlive() && aliveCount != 2 && aliveCount != 3)
                kill();
        }
        
        private void countLivingNeighbours()
        {
            int count = 0;
            for (Cell neighbour : neighbours)
                if (neighbour.isAlive)
                    count++;
            this.aliveCount = count;
        }
        
        /**
         * Also adds Observer.
         */
        public void addNeighbour(Cell neighbour)
        {
            this.neighbours.add(neighbour);
            addObserver(neighbour);
        }
        
        private void animate(int step)
        {
            tAnimate.stop();
            
            final Color[] colours = new Color[]{ Color.RED, Color.ORANGE,
                    Color.YELLOW, Color.GREEN, Color.BLUE/*, Color.MAGENTA*/ };

            if (step >= 0 && step < colours.length)
            {
                panel.setBackground(colours[step]);
                tAnimate = new Timer(10, e -> animate(step + 1));
                tAnimate.start();
            }
            else if (isAlive())
                panel.setBackground(Color.WHITE);
            else
                panel.setBackground(Color.BLACK);
            
            tAnimate = new Timer(50, e -> animate(0));
            
            panel.repaint();
        }
        
        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            
            sb.append("{ 0x");
            sb.append(ID);
            
            if (isAlive())
                sb.append(" } [ alive | ");
            else
                sb.append(" } [ dead  | ");
            
            if (isObservable)
                sb.append("Observ.  ON | ");
            else
                sb.append("Observ. OFF | ");
            
            if (hasChanged())
                sb.append(" changed  | ");
            else
                sb.append("unchanged | ");
            
            sb.append(aliveCount);
            sb.append(" friends | ");
            
            if (panel.getBackground().getRGB() == Color.WHITE.getRGB())
                sb.append("WHITE | ");
            else if (panel.getBackground().getRGB() == Color.BLACK.getRGB())
                sb.append("BLACK | ");
            else
                sb.append(" ???  | ");
            
            sb.append(edge);
            sb.append(" ]");
            
            return sb.toString();
        }
        
        // Getters.
        public JPanel getPanel() { return panel; }
        
        // Setters.
        /**
         * Activates Observable properties. Should have already added Observers
         * with {@link Observerable#addObserver(Observer)}.
         */
        public void toggleIsObservable()
        {
            this.isObservable ^= true;
            
            setChanged();
            
            if (isObservable)
                notifyObservers();
        }
    }
    
    enum Edge
    {
        NONE, 
        TOP, TOP_LEFT, TOP_RIGHT, 
        LEFT,
        RIGHT,
        BOTTOM, BOTTOM_LEFT, BOTTOM_RIGHT;
    }
    
/* -------------------------------------------------------------------------- */
    public static void call(String[] args) { new Life(); }
/* -------------------------------------------------------------------------- */
    
    // Methods.
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
    
    private static ArrayList<Point> getIteratorOver(Rectangle r)
    {
        ArrayList<Point> points = new ArrayList<>(r.width * r.height);
        for (int i = r.x; i < r.width + r.x; i++)
            for (int j = r.y; j < r.height + r.y; j++)
                points.add(new Point(i,j));
        return points;
    }
    
    private static ArrayList<Point> getShuffledIteratorOver(Rectangle r)
    {
        ArrayList<Point> shuff = getIteratorOver(r);
        Collections.shuffle(shuff);
        return shuff;
    }
    
    private static boolean isPointOnRect(Rectangle r, Point p)
    {
        return p.x >= r.x && p.x <= r.x + r.width - 1
                && p.y >= r.y && p.y <= r.y + r.height -1;
    }
}