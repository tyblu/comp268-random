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
import javax.swing.JCheckBoxMenuItem;
                          
import java.awt.Color;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
                           
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
                                  
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.Timer;
                                      


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
    // Nearly-global instance variables, constants, and Observers.
    private static final Rectangle GRID_RECT = new Rectangle(25,25);
    final Seer enki = new Seer(GRID_RECT);
    
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
            super("Conways Game of Life... now with more life!");
            
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
            this.bounds = GRID_RECT;    // 30x30, top-left (0,0)
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
                
                cell.addObserver(enki);
            }
            
            // Add Cell Observables to JMenuBar Observer.
            
            // Make Cells Observable in random order.
            for (Point p : getShuffledIteratorOver(bounds))
                cellMap.get(p).toggleIsObservable();
        }
        
        public void reset()
        {
            cellMap.values().forEach(cell -> cell.killTimers());
            removeAll();
            revalidate();
            this.cellMap = new HashMap<>(bounds.x * bounds.y);
            enki.reset();
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
    
    private class GameMenuBar extends JMenuBar implements Observer
    {
        // Instance variables.
        int alive, total;
        JButton resetButton;
        private double fAlive;
        
        // Constructor.
        public GameMenuBar()
        {
            this.alive = 0;
            this.total = 0;
            this.fAlive = (double)0;
            
            JMenu menu1 = new JMenu("menu1");
            JMenuItem reset = new JMenuItem("reset");
            JMenuItem pause = new JMenuItem("pause");
            JCheckBoxMenuItem jitter = new JCheckBoxMenuItem("extra life");
            
            JPanel statusbar = new JPanel()
            {
                @Override
                public void paintComponent(Graphics g)
                {
                    super.paintComponent(g);
                    
                    Dimension dim = this.getSize();
                    
                    g.setColor(Color.WHITE);
                    g.fillRect(2, 2, dim.width-4, dim.height-4);
                    
                    g.setColor(Color.BLACK);
                    g.fillRect(4, 4, dim.width-8, dim.height-8);
                    
                    g.setColor(Color.WHITE.darker().darker());
                    g.fillRect(6, 6, dim.width-12, dim.height-12);
                    
                    g.setColor(Color.WHITE.darker());
                    g.fillRect(6, 6, (int)(fAlive * (dim.width-12)), dim.height-12);
                }
            };
            statusbar.setPreferredSize(new Dimension(
                    (int)(0.5 * this.getWidth()),
                    (int)(0.5 * getTaskbarHeight())
            ));
            add(statusbar);
            
            this.resetButton = new JButton("reset");
            resetButton.addActionListener(evt ->
                    ((GameGrid)getParent().getComponent(1)).reset());
            add(resetButton);
            
            enki.addObserver(this);
        }
        
        // Methods.
        @Override
        public void update(Observable o, Object arg)
        {
            if (o instanceof Seer)
            {
                this.fAlive = (double)arg;
                repaint();
            }
        }
    }
    
    private class Cell extends Observable implements Observer
    {
        private boolean isAlive;
        private int aliveCount;
        private boolean isObservable;
        private final Edge edge;
        private final JPanel panel;
        private Timer tNotify, tNotifyWithJitter, tAnimate;
        private Timer[] timers;
        private ArrayList<Cell> neighbours;
        private Integer ID;
        
        // Constructor.
        public Cell(Edge edge, boolean isAlive)
        {
            this.isAlive = isAlive;
            this.panel = new JPanel();
            this.edge = Edge.NONE;
            this.isObservable = false;
            final int rate = 500;
            this.tNotify = new Timer(rate, e -> notifyObservers());
            this.tNotifyWithJitter = new Timer(
                    rate/2 + (new Random()).nextInt(rate/2),
                    e -> notifyObservers()
            );
            this.tAnimate = new Timer(rate/2, e -> animate());
            this.neighbours = new ArrayList<>();
            this.ID = hashCode();
            
            this.timers = new Timer[] { tNotify, tNotifyWithJitter, tAnimate };
            for (Timer t : timers)
                t.setRepeats(false);
            
            panel.setOpaque(true);
            panel.setPreferredSize(new Dimension(40,40));
            panel.setVisible(true);
            
            if (isAlive)
                panel.setBackground(Color.WHITE);
            else
                panel.setBackground(Color.BLACK);
            
            panel.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        }
        
        // Methods.
        /**
         * Shows transitions between alive and dead Cells.
         */
        private void animate()
        {
            // TODO.
            if (isAlive)
                panel.setBackground(Color.WHITE);
            else
                panel.setBackground(Color.BLACK);
        }
        
        /* Observable */
        public void kill()
        { 
            if (!isAlive)
                return;
            
            isAlive = false;
            tAnimate.start();
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
            tAnimate.start();
            setChanged();
            
            if (isObservable)
                tNotify.start();
            
            getPanel().repaint();
        }
        
        @Override       // To neighbour Cells.
        public void notifyObservers()
        {
            tNotify.stop();
            super.notifyObservers(ID);
        }
        /* end Observable */
        
        /* Observer */
        @Override
        public void update(Observable o, Object arg)
        {
            if (arg != null)
            {
                countLivingNeighbours();
                
                if (!isAlive() && aliveCount == 3)
                    revive();
                else if (isAlive() && aliveCount != 2 && aliveCount != 3)
                    kill();
            }
        }
        /* end Observer */
        
        public boolean isAlive() { return isAlive; }
        
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
        
        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            
            sb.append("{ 0x");
            sb.append(Integer.toHexString(hashCode()));
            
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
        
        public void killTimers()
        {
            for (Timer t : timers)
            {
                t.stop();
                t = null;
            }
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
    
    private class Seer extends Observable implements Observer
    {
        // Instance variables.
        private final Timer tRefresh;
        private final double total;
        private Map<Integer,Boolean> states;
        
        // Constructor.
        public Seer(Rectangle grid)
        {
            this.total = (double)(grid.width * grid.height);
            this.states = new HashMap<>((int)total);
            
            this.tRefresh = 
                    new Timer(250, e -> notifyObservers(getLiving()));
            tRefresh.setInitialDelay(1000);
            tRefresh.start();
        }
        
        // Methods.
        /* Observer */
        @Override
        public void update(Observable cell, Object ID) 
        {
            if (cell instanceof Cell)
                states.put((Integer)ID, ((Cell)cell).isAlive());
            else
                System.out.println("[Seer] WHO ARE YOU?: " + cell + " " + ID);
        }
        /* end Observer */
        
        /* Observable */
        @Override
        public void notifyObservers(Object arg)
        {
            setChanged();
            super.notifyObservers(arg);
        }
        /* end Observable */
        
        private double getLiving()
        {
            final int[] count = {0};    // reference is final, not value
            states.values().forEach(isAlive -> { if (isAlive) count[0]++; });
            return count[0] / total;
            
//            states.values().removeIf(isAlive -> isAlive == false);
//            return states.size()/total;
        }
        
        public void reset() { this.states = new HashMap<>((int)total); }
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
    
    
     
                                                     
                                                          
     
}