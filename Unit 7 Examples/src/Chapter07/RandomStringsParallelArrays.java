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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Example on pp341-344.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 15, 2017
 * Version      2.1
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 7: Arrays and ArrayLists</a>
 */
public class RandomStringsParallelArrays
{
    public static void call(String[] args) { new RandomStringsParallelArrays(); }
    
    private ObservableFlags flags = new ObservableFlags();
    // Instance variables and constants.
    private class ObservableFlags
    {
        public final Flags doClear = new Flags();
        public final Flags doRandomBg = new Flags();
        public final Flags doReset = new Flags();
        public final Flags doResetBg = new Flags();
        public final Flags ignoreNextBg = new Flags();
    }
    
    private final String[] quotes = new String[]
    {
        "The average, healthy, well-adjusted adult gets up at seven-thirty"
            + "\n in the morning feeling just plain terrible.\n - Jean Kerr",
        "The thing that impresses me the most about America is the way"
            + "\n parents obey their children.\n - King Edward VIII (1894 -"
            + " 1972)",
        "The only man who is really free is the one who can turn down an"
            + "\n invitation to dinner without giving an excuse.\n - Jules"
            + " Renard (1864 - 1910)",
        "Any war that requires the suspension of reason as a necessity for"
            + "\n support is a bad war.\n - Norman Mailer (1923 - 2007),"
            + " Armies Of The Night",
        "To believe is to know you believe, and to know you believe is not"
            + "\n to believe.\n - Jean-Paul Sartre (1905 - 1980)",
        "I would visualize things coming to me. It would just make me feel"
            + "\n better. Visualization works if you work hard. That's the"
            + "\n thing. You can't just visualize and go eat a sandwich.\n -"
            + " Jim Carrey, Oprah Winfrey Show, 1997",
        "Those who can laugh without cause have either found the true"
            + "\n meaning of happiness or have gone stark raving mad.\n -"
            + " Norm Papernick",
        "The end of the human race will be that it will eventually die of"
            + "\n civilization.\n - Ralph Waldo Emerson (1803 - 1882)",
        "We must never forget that art is not a form of propaganda; it is"
            + "\n a form of truth.\n - John F. Kennedy (1917 - 1963), October"
            + " 26, 1963",
        "I have noticed that the people who are late are often so much"
            + "\n jollier than the people who have to wait for them.\n - E. V."
            + " Lucas",
        "I love quotations because it is a joy to find thoughts one might"
            + "\n have, beautifully expressed with much authority by someone"
            + "\n recognized wiser than oneself.\n - Marlene Dietrich (1901 -"
            + " 1992)",
        "When I meet a man I ask myself, \'Is this the man I want my"
            + "\n children to spend their weekends with?\'\n - Rita Rudner",
        "The only time to buy these is on a day with no \'y\' in it."
            + "\n - Warren Buffett (1930 - )",
        "Thought is only a flash between two long nights, but this flash"
            + "\n is everything.\n - Henri Poincare (1854 - 1912)",
        "Fish is the only food that is considered spoiled once it smells"
            + "\n like what it is.\n - P. J. O\'Rourke (1947 - )",
        "Insanity in individuals is something rare - but in groups,"
            + "\n parties, nations and epochs, it is the rule.\n - Friedrich"
            + " Nietzsche (1844 - 1900)",
        "The follies which a man regrets most, in his life, are those"
            + "\n which he didn't commit when he had the opportunity."
            + "\n - Helen Rowland (1876 - 1950), A Guide to Men, 1922",
        "Have the courage to be ignorant of a great number of things, in"
            + "\n order to avoid the calamity of being ignorant of"
            + "\n everything.\n - Sydney Smith (1771 - 1845)",
        "We\'re here for a reason. I believe a bit of the reason is to"
            + "\n throw little torches out to lead people through the dark."
            + "\n - Whoopi Goldberg",
        "You find yourself refreshed by the presence of cheerful people."
            + "\n Why not make an honest effort to confer that pleasure on"
            + "\n others? Half the battle is gained if you never allow"
            + "\n yourself to say anything gloomy.\n - Lydia M. Child"
    };
    
    // Constructor.
    public RandomStringsParallelArrays()
    {
        CenteredWindow window = new CenteredWindow();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.requestFocusInWindow();
    }
    
    private static class StringData
    {
        public String s;
        public Point p;        // String coords in container
        public Color color;
        public Font font;
        
        public StringData(String s, Point p, Color color, Font font)
        {
            this.s = s;
            this.p = p;
            this.color = color;
            this.font = font;
        }
        
        // Methods.
        public void paint(Graphics g)
        {
            g.setColor(color);
            g.setFont(font);
            g.drawString(s, p.x, p.y);
        }
        
        @Override
        public String toString()
        {
            StringBuilder sb = new StringBuilder();
            sb.append("Chapter07.RandomStringsParallelArrays.StringData\n\"");
            sb.append(s);
            sb.append("\"\nColor: ");
            sb.append(color.toString().substring(9));
            sb.append("\nFont: ");
            sb.append(font);
            
            return sb.toString();
        }
    }
    
    private class CenteredWindow extends JFrame
    {
        public CenteredWindow()
        {
            super("Too Many Stupid Quotes");
            
            
            RandomStringsPanel content = new RandomStringsPanel();
            setContentPane(content);
            setJMenuBar(new RandomStringsMenuBar());
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
    
    private class RandomStringsPanel extends JPanel implements Observer
    {
        // Instance variables.
        private final int maximumDisplayedStringsCount;
        private StringData[] displayedStrings;
        private final Rectangle stringCoordRectangle;
        
        private final Random r;
        
        
        // Constructor.
        public RandomStringsPanel()
        {
            this.maximumDisplayedStringsCount = 8;
            
            this.r = new Random();
            
//            this.displayedStrings = new StringDataArray();
//            displayedStrings.setMaximumArraySize(maximumDisplayedStringsCount);

            setBackground(Color.WHITE);
            setPreferredSize(scaleDimension(getScreenSize(), 0.8, 0.8));
            
            this.stringCoordRectangle = new Rectangle(
                   (int)(0.05 * getPreferredSize().width),
                   (int)(0.05 * getPreferredSize().height),
                   (int)(0.3 * getPreferredSize().width),
                   (int)(0.9 * getPreferredSize().height)
           );
            
            (new Timer(1000, (ActionEvent evt) -> addString() )).start();
            
            addThisObserverToObservables(flags.doClear, flags.doRandomBg,
                    flags.doReset, flags.doResetBg);
        }
        
        // Methods.
        private void addThisObserverToObservables(Observable... observables)
        {
            for (Observable o : observables)
                o.addObserver(this);
        }
        
        private void addString()
        {
            if (displayedStrings == null)
                displayedStrings = new StringData[] { nextStringData() };
            else
            {
                if (displayedStrings.length + 1 > maximumDisplayedStringsCount)
                    displayedStrings = Arrays.copyOfRange(
                            displayedStrings, 1, displayedStrings.length + 1);
                else
                    displayedStrings = Arrays.copyOfRange(
                            displayedStrings, 0, displayedStrings.length + 1);
                
                displayedStrings[displayedStrings.length-1] = nextStringData();
            }
            
            repaint();
        }
        
        private void clear()
        {
            displayedStrings = null;
            repaint();
        }
        
        private void reset()
        {
            setBackground(Color.WHITE);
            clear();
        }
        
        private StringData nextStringData()
        {
            return new StringData(
                    nextQuote(),
                    nextPoint(stringCoordRectangle),
                    nextColor(),
                    nextFont()
            );
        }
        
        private String nextQuote()
        {
            return quotes[r.nextInt(quotes.length)];
        }
        
        private Point nextPoint(Rectangle bounds)
        {
            return new Point(
                    bounds.x + 1 + r.nextInt(bounds.width),
                    bounds.y + 1 + r.nextInt(bounds.height)
            );
        }
        
        private Point nextPoint()
        {
            return new Point(
                    1 + r.nextInt(this.getWidth()),
                    1 + r.nextInt(this.getHeight())
            );
        }
        
        private Color nextColor()
        {
            return new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
        }
        
        private Font nextFont()
        {
            return new Font(
                    getRandomFontFamilyName(),
                    getRandomFontStyle(),
                    20 + r.nextInt(150)
            );
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            if (displayedStrings != null && displayedStrings.length > 0)
                for (StringData dat : displayedStrings)
                    dat.paint(g);
        }
        
        // Getters.
        private String getRandomFontFamilyName()
        {
            String[] names =  GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getAvailableFontFamilyNames(Locale.CANADA);
            return names[r.nextInt(names.length)];
        }
        
        private int getRandomFontStyle()
        {
            final int[] styles = new int[]
                    { 
                        Font.PLAIN, 
                        Font.BOLD, 
                        Font.ITALIC, 
                        Font.BOLD | Font.ITALIC 
                    };
            return styles[r.nextInt(styles.length)];
        }

        @Override
        public void update(Observable o, Object arg)
        {
            if (o.equals(flags.doClear))
                clear();
            else if (o.equals(flags.doRandomBg) 
                    && flags.ignoreNextBg.getIsFlagTrue())
                flags.ignoreNextBg.changeIsFlagTrue(false); // Ignored - toggle.
            else if (o.equals(flags.doRandomBg)
                    && !flags.ignoreNextBg.getIsFlagTrue())
                setBackground(nextColor());
            else if (o.equals(flags.doReset))
                reset();
            else if (o.equals(flags.doResetBg))
                setBackground(Color.WHITE);
        }
    }
    
    private class Flags extends Observable
    {
        private boolean isFlagTrue;
        
        public boolean getIsFlagTrue() { return isFlagTrue; }
        
        public void changeIsFlagTrue(boolean isFlagTrue)
        {
            this.isFlagTrue = isFlagTrue;
            setChanged();
            notifyObservers(isFlagTrue);
        }
    }
    
    private class RandomStringsMenuBar extends JMenuBar
    {
        public RandomStringsMenuBar()
        {
            JButton clear = new JButton("Clear");
            clear.addActionListener(evt
                    -> flags.doClear.changeIsFlagTrue(true));
            add(clear);
            
            JButton randBg = new JButton("Random Background");
            randBg.addActionListener(evt 
                    -> flags.doRandomBg.changeIsFlagTrue(true));
            randBg.addMouseListener(new MouseAdapter()
            {
                private java.util.Timer t;
                
                @Override
                public void mousePressed(MouseEvent evt)
                {
                    t = new java.util.Timer();
                    t.schedule(new TimerTask()
                    {
                        @Override
                        public void run()
                        {
                            flags.doResetBg.changeIsFlagTrue(true);
                            flags.ignoreNextBg.changeIsFlagTrue(true);
                        }
                    }, 1000, 500);
                }
                
                @Override
                public void mouseReleased(MouseEvent evt)
                {
                    if (t != null)
                        t.cancel();
                    t = null;
                }
            });
            add(randBg);
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
    
    private static Dimension scaleDimension(Dimension d, 
            double xRatio, double yRatio)
    {
        return new Dimension((int)(d.width * xRatio), (int)(d.height * yRatio));
    }
}
