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
import java.lang.Integer;
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
import java.awt.Toolkit;
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
            GameGrid panel = new GameGrid(this, menuBar);
            
            setJMenuBar(menuBar);
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
    private class GameGrid extends JPanel implements Observer
    {
        // Instance variables.
        private final GameMenuBar menuBar;
        private final CenteredWindow window;
        private ArrayList<Cell> cells;
        private final Dimension gridDim;
        
        // Constructor.
        public GameGrid(CenteredWindow window, GameMenuBar menuBar)
        {
            this.window = window;
            this.menuBar = menuBar;
            gridDim = new Dimension(30,30);
            
            // layout
            setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            c.insets.set(5, 5, 5, 5);
//            c.weightx = (double)1;
//            c.weighty = (double)1;
            
            // create Cells and set dead or alive
            Random r = new Random();
            cells = new ArrayList<>();
            for (int i=0; i < gridDim.width; i++)
                for (int j=0; j < gridDim.height; j++)
                {
                    Edge edge;
                    if (i == 0)     // top
                    {
                        if (j == 0)
                            edge = Edge.TOP_LEFT;
                        else if (j == gridDim.height - 1)
                            edge = Edge.BOTTOM_LEFT;
                        else
                            edge = Edge.TOP;
                    }
                    else if (i == gridDim.width - 1)
                    {
                        if (j == 0)
                            edge = Edge.TOP_RIGHT;
                        else if (j == gridDim.height - 1)
                            edge = Edge.BOTTOM_RIGHT;
                        else
                            edge = Edge.BOTTOM;
                    }
                    else
                        edge = Edge.NONE;
                    
                    Cell nextCell = new Cell(i, j, r.nextBoolean(), edge);
                    cells.add(nextCell);
                }
            
            setBackground(Color.BLACK);
            
            // add cell observables to menu observer
        }
        
        // Methods.
        /* Observer */
        @Override
        public void update(Observable o, Object arg)
        {
            // find cell from (o, arg)
            
            // update JPanel representing Cell/Observable
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
        private final GridPoint position;
        private int aliveCount;
        private JPanel panel;
        private Cell[] neighbours;
        
        // Constructor.
        public Cell(int i, int j, boolean isAlive, Edge edge)
        {
            position = new GridPoint(i, j, edge);
            this.isAlive = isAlive;
            this.panel = new JPanel();
            
            panel.setPreferredSize(new Dimension(20,20));
            panel.setBackground(Color.WHITE);   // Corresponds to living Cell.
            panel.setOpaque(false);             // Corresponds to dead Cell.
            panel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            
            // Setup observers later, after all cells instantiated.
        }
        
        public Cell(int i, int j, boolean isAlive) 
        {
            this(i, j, isAlive, Edge.NONE); 
        }
        
        // Methods.
        /* from Observable */
        public boolean isAlive() { return isAlive; }
        
        public void kill()
        {
            if (!isAlive)
                return;
            
            isAlive = false;
            panel.setOpaque(false);
            setChanged();
            notifyObservers(isAlive);
        }
        
        public void revive()
        {
            if (isAlive)
                return;
            
            isAlive = true;
            setChanged();
            notifyObservers(isAlive);
        }
        /* end Observable */
        
        /* from Observer */
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
        
        /**
         * Should not have to call this. Consider putting it on a 
         * java.util.Timer to be called if there have been no Observable
         * updates() recently.
         */
        public void updateNeighbours()
        {
            int count = 0;
            
            for (Cell cell : getNeighbours())
                if (cell.isAlive())
                    count++;
            
            this.aliveCount = count;
            
            life();
        }
        
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
        
        /**
         * Add neighbour Cells as Observers.
         */
        public void addNeighbourObservers()
        {
            for (Cell cell : getNeighbours())
                addObserver(cell);
        }
        
        // Getters.
        public GridPoint getPosition() { return position; }
        public Cell[] getNeighbours() { return neighbours; }
        public JPanel getPanel() { return panel; }
        
        // Setters.
        public void setNeighbours(Cell[] neighbours)
        {
            this.neighbours = neighbours;
        }
    }
    
    enum Edge
    {
        NONE, 
        TOP, TOP_LEFT, TOP_RIGHT, 
        LEFT, LEFT_TOP, LEFT_BOTTOM,
        RIGHT, RIGHT_TOP, RIGHT_BOTTOM,
        BOTTOM, BOTTOM_LEFT, BOTTOM_RIGHT
    };
    
    private class GridPoint
    {
        // Instance variables.
        private final int i, j;
        private final Edge edge;
        
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
        /**
         * 0 1 2
         * 3 X 5
         * 6 7 8
         */
        public GridPoint[] getNeighbourPositions()
        {
            final int[][] pos = new int[][]
            {
                {   i-1,    j-1 },
                {   i,      j-1 },
                {   i+1,    j-1 },
                {   i-1,    j   },
                {   i,      j   },  // should never be referenced
                {   i+1,    j   },
                {   i-1,    j+1 },
                {   i,      j+1 },
                {   i+1,    j+1 }
            };
            
            switch (edge)
            {
            case NONE: 
                return getGridPoints(pos[0],pos[1],pos[2],pos[3],pos[5],pos[6],
                        pos[7],pos[8]);
            case TOP: 
                return getGridPoints(pos[3],pos[5],pos[6],pos[7],pos[8]);
            case RIGHT: 
                return getGridPoints(pos[0],pos[1],pos[3],pos[6],pos[7]);
            case LEFT: 
                return getGridPoints(pos[1],pos[2],pos[5],pos[7],pos[8]);
            case BOTTOM: 
                return getGridPoints(pos[0],pos[1],pos[2],pos[3],pos[5]);
            case TOP_LEFT: case LEFT_TOP:
                return getGridPoints(pos[5],pos[7],pos[8]);
            case TOP_RIGHT: case RIGHT_TOP:
                return getGridPoints(pos[3],pos[6],pos[7]);
            case BOTTOM_LEFT: case LEFT_BOTTOM:
                return getGridPoints(pos[1],pos[2],pos[5]);
            case BOTTOM_RIGHT: case RIGHT_BOTTOM:
                return getGridPoints(pos[0],pos[1],pos[3]);
            default:
                return null;
            }
        }
        
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
    }
    
/* -------------------------------------------------------------------------- */
    public static void call(String[] args) { new Life(); }
/* -------------------------------------------------------------------------- */
    
    // Methods.
    private GridPoint[] getGridPoints(int[]... pts)
    {
        GridPoint[] gridpoints = new GridPoint[pts.length];
        
        for (int i=0; i < pts.length; i++)
            gridpoints[i] = new GridPoint(pts[i]);
        
        return gridpoints;
    }
    
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