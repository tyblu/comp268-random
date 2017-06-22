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
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;

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
            this.bounds = new Rectangle(30, 30);    // 30x30, top-left (0,0)
            this.cellMap = new HashMap<>(bounds.x * bounds.y);
            
            initialize();
        }
        
        // Methods.
        private void initialize()
        {
            setLayout( new GridBagLayout() );
            
            /* Initialize Cells. */
            // Create cells and put in grid map.
            Random r = new Random();
            for (Point p : getShuffledGrid())
                cellMap.put(p, new Cell(determineEdge(p), r.nextBoolean()));
            
            // Add neighbour data to cells, including Observers.
            for (Point p : getShuffledGrid())
            {
                Cell cell = cellMap.get(p);
                
                ArrayList<Cell> neighbours = getNeighboursOf(p.x, p.y);
                cell.setNeighbours(neighbours);
                
                int livingNeighbours = 0;
                for (Cell neighbour : neighbours)
                {
                    if (neighbour.isAlive())
                        livingNeighbours++;
                    
                    cell.addObserver(neighbour);
                }
                cell.setAliveCount(livingNeighbours);
                
                cell.setObserverable()
            }
            
            // Make cells Observable in random order.
            
            
            for (int i=0; i < gridDim.width; i++)
            {
                for (int j=0; j < gridDim.height; j++)
                {
                    Edge edge;
                    
                    GridPoint nextGridPoint = new GridPoint(i, j, edge);
                    grid.add(nextGridPoint);
                    cellMap.put(nextGridPoint, new Cell());
                }
            }
            
            // Set initial state.
            for (GridPoint p : grid)
            {
                ArrayList<GridPoint> nGPs = p.getNeighbourPositions();
                
                ArrayList<Cell> nCells = new ArrayList<>();
                
                for (GridPoint nGP : nGPs)
                    nCells.add(cellMap.get(nGP));
                    
                Cell currentCell = cellMap.get(p);
                currentCell.setIsAlive(r.nextBoolean());
                currentCell.setNeighbours(nCells);
                currentCell.initiateObservers(this, menuBar);
            }
            
            setBackground(Color.BLACK);
            
            // add cell observables to menu observer
        }
        
        private ArrayList<Point> getShuffledGrid()
        {
            ArrayList<Point> shuff = new ArrayList<>(bounds.x * bounds.y);
            for (int i = bounds.x; i < bounds.width; i++)
            {
                for (int j = bounds.y; j < bounds.height; j++)
                {
                    shuff.add(new Point(i,j));
                }
            }
            Collections.shuffle(shuff);
            return shuff;
        }
        
        private ArrayList<Cell> getNeighboursOf(int x, int y)
        {
            ArrayList<Cell> neighbours = new ArrayList<>();
            for (int i = -1; i < 1; i++)
            {
                for (int j = -1; j < 1; j++)
                {
                    Point p = new Point(x + i, y + j);
                    if (bounds.contains(p) && !(i == 0 && j == 0)) // ...&& (i != 0 || j != 0))
                        neighbours.add(cellMap.get(p));
                }
            }
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
        JLabel aliveLabel, deadLabel;
        
        // Constructor.
        public GameMenuBar()
        {
            this.aliveLabel = new JLabel("Alive: 000");
            add(aliveLabel);
            
            this.deadLabel = new JLabel("Dead: 000");
            add(deadLabel);
        }
        
        // Methods.
        @Override
        public void update(Observable o, Object neighbourState)
        {
            if ((boolean)neighbourState)
                alive++;
            else
                alive--;
            
            update();
        }

        private void update()
        {
            aliveLabel.setText("Alive: " + alive);
            deadLabel.setText("Dead: " + (total - alive));
        }
        
        // Setters.
        public void setAlive(int alive) { this.alive = alive; }
        public void setTotal(int total) { this.total = total; }
    }
    
    private class Cell extends Observable implements Observer
    {
        private boolean isAlive;
        private int aliveCount;
        private ArrayList<Cell> neighbours;
        private boolean isObservable;
        private final Edge edge;
        private final JPanel panel;
        
        // Constructor.
        public Cell(Edge edge, boolean isAlive)
        {
            this.isAlive = isAlive;
            this.panel = new JPanel();
            this.edge = Edge.NONE;
            this.isObservable = false;
            
            panel.setPreferredSize(new Dimension(20,20));
            panel.setBackground(Color.WHITE);   // Corresponds to living Cell.
            panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            panel.setVisible(true);
            
            if (isAlive()) { setChanged(); }
        }
        
        // Methods.
        /* Observable */
        public void kill()
        {
            if (!isAlive)
                return;
            
            isAlive = false;
            panel.setBackground(Color.BLACK);
            
            setChanged();
            
    /* Can add delay, util.Timer or Swing, to slow down for us lowly humans. */
            if (isObservable)
                notifyObservers(isAlive);
            
            getPanel().repaint();
        }
        
        public void revive()
        {
            if (isAlive)
                return;
            
            isAlive = true;
            panel.setBackground(Color.WHITE);
            
            setChanged();
            
    /* Can add delay, util.Timer or Swing, to slow down for us lowly humans. */
            if (isObservable)
                notifyObservers(isAlive);
            
            getPanel().repaint();
        }
        /* end Observable */
        
        /* Observer */
        @Override
        public void update(Observable o, Object neighbourState)
        {
            if ((boolean)neighbourState)
                aliveCount++;
            else
                aliveCount--;
            
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
        
        
        // Getters.
        public JPanel getPanel() { return panel; }
        public Edge getEdge() { return edge; }
        
        // Setters.
        public void setNeighbours(ArrayList<Cell> neighbours)
        {
            this.neighbours = neighbours;
        }
        
        /**
         * Activates Observable properties. Should have already added Observers
         * with {@link Observerable#addObserver(Observer)}.
         */
        public void setIsObservable(boolean isObservable)
        {
            this.isObservable = isObservable;
        }
        
        public void setAliveCount(int aliveCount)
        {
            this.aliveCount = aliveCount;
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
    
    private class Grid
    {
        // Instance variables.
        private final Rectangle bounds;
        private ArrayList<Point> pts;
        
        // Constructor.
        public Grid(int col, int row)
        {
            this.bounds = new Rectangle(30, 30);
            this.pointMap = new HashMap<>();
        }
        
        // Methods.
        public void put(GridPoint gpt, int x, int y)
        {
            if (gpt.edge == Edge.NONE)
                gpt.setEdge(determineEdge(gpt));
            
            pointMap.put(new Point(x,y), gpt);
        }
        
        // Getters.
        public GridPoint get(int x, int y) { return get(new Point(x,y)); }
        private GridPoint get(Point p) { return pointMap.get(p); }
        
        public ArrayList<GridPoint> getNeighboursOf(int x, int y)
        {
            ArrayList<GridPoint> neighbours = new ArrayList<>();
            for (int i = -1; i < 1; i++)
            {
                for (int j = -1; j < 1; j++)
                {
                    Point p = new Point(x + i, y + j);
                    if (bounds.contains(p))
                        neighbours.add(get(p));
                }
            }
            
            return neighbours;
        }
        
        public ArrayList<GridPoint> getNeighboursOf(Point p)
        {
            return getNeighboursOf(p.x, p.y);
        }
        
        // Setters.
    }
    
    private class GridPoint
    {
        // Instance variables.
        private final int i, j;
        private Edge edge;
        
        // Constructor.
        public GridPoint(int i, int j, Edge edge)
        {
            this.i = i;
            this.j = j;
            this.edge = edge;
        }
        
        public GridPoint(int i, int j) { this(i, j, Edge.NONE); }
        public GridPoint(int[] ij) { this(ij[0], ij[1], Edge.NONE); }
        
        // Methods.
        @Override
        public String toString() { return "(" + i + "," + j + ")"; }
        
        public boolean isEqual(GridPoint p)
        {
            return p.getI() == this.getI() && p.getJ() == this.getJ();
        }
        
        // Getters.
        public int getI() { return i; }
        public int getJ() { return j; }
        public int[] getIJ() { return new int[] { i, j }; }
        
        // Setters.
        public void setEdge(Edge edge) { this.edge = edge; }
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
    
    private static ArrayList<Point> shuffle(ArrayList<Point> pts)
    {
        ArrayList<Point> shuffledPts = pts;
        Collections.shuffle(shuffledPts);
        return shuffledPts;
    }
    
    private static ArrayList<Point> getIteratorOver(Rectangle r)
    {
        ArrayList<Point> pts = new ArrayList<>(r.width * r.height);
        for (int i = r.x; i < r.width + r.x; i++)
            for (int j = r.y; j < r.height + r.y; j++)
                pts.add(new Point(i,j));
        
        return pts;
    }
    
    private static ArrayList<Point> getShuffledIteratorOver(Rectangle r)
    {
        ArrayList<Point> shuff = getIteratorOver(r);
        Collections.shuffle(shuff);
        return shuff;
    }
}