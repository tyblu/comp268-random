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
import java.awt.Component;
import java.awt.Container;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
            + " in the morning feeling just plain terrible. - Jean Kerr",
        "The thing that impresses me the most about America is the way"
            + " parents obey their children. - King Edward VIII (1894 -"
            + " 1972)",
        "The only man who is really free is the one who can turn down an"
            + " invitation to dinner without giving an excuse. - Jules"
            + " Renard (1864 - 1910)",
        "Any war that requires the suspension of reason as a necessity for"
            + " support is a bad war. - Norman Mailer (1923 - 2007),"
            + " Armies Of The Night",
        "To believe is to know you believe, and to know you believe is not"
            + " to believe. - Jean-Paul Sartre (1905 - 1980)",
        "I would visualize things coming to me. It would just make me feel"
            + " better. Visualization works if you work hard. That's the"
            + " thing. You can't just visualize and go eat a sandwich. -"
            + " Jim Carrey, Oprah Winfrey Show, 1997",
        "Those who can laugh without cause have either found the true"
            + " meaning of happiness or have gone stark raving mad. -"
            + " Norm Papernick",
        "The end of the human race will be that it will eventually die of"
            + " civilization. - Ralph Waldo Emerson (1803 - 1882)",
        "We must never forget that art is not a form of propaganda; it is"
            + " a form of truth. - John F. Kennedy (1917 - 1963), October"
            + " 26, 1963",
        "I have noticed that the people who are late are often so much"
            + " jollier than the people who have to wait for them. - E. V."
            + " Lucas",
        "I love quotations because it is a joy to find thoughts one might"
            + " have, beautifully expressed with much authority by someone"
            + " recognized wiser than oneself. - Marlene Dietrich (1901 -"
            + " 1992)",
        "When I meet a man I ask myself, \'Is this the man I want my"
            + " children to spend their weekends with?\' - Rita Rudner",
        "The only time to buy these is on a day with no \'y\' in it."
            + " - Warren Buffett (1930 - )",
        "Thought is only a flash between two long nights, but this flash"
            + " is everything. - Henri Poincare (1854 - 1912)",
        "Fish is the only food that is considered spoiled once it smells"
            + " like what it is. - P. J. O\'Rourke (1947 - )",
        "Insanity in individuals is something rare - but in groups,"
            + " parties, nations and epochs, it is the rule. - Friedrich"
            + " Nietzsche (1844 - 1900)",
        "The follies which a man regrets most, in his life, are those"
            + " which he didn't commit when he had the opportunity."
            + " - Helen Rowland (1876 - 1950), A Guide to Men, 1922",
        "Have the courage to be ignorant of a great number of things, in"
            + " order to avoid the calamity of being ignorant of"
            + " everything. - Sydney Smith (1771 - 1845)",
        "We\'re here for a reason. I believe a bit of the reason is to"
            + " throw little torches out to lead people through the dark."
            + " - Whoopi Goldberg",
        "You find yourself refreshed by the presence of cheerful people."
            + " Why not make an honest effort to confer that pleasure on"
            + " others? Half the battle is gained if you never allow"
            + " yourself to say anything gloomy. - Lydia M. Child"
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
        public void paint(Graphics g, Container container)
        {
            g.setColor(color);
            g.setFont(font);
            
            float[] sizeRatioSteps = new float[]{ 1.0f, 0.8f, 0.6f, 0.4f, 0.2f };
            float minFontSize = (float)12;
            int maxWrapLength = 100;
            double maxX = container.getBounds().getWidth();
            
            Font newFont = null;
            
            String[] lines = new String[]{ s };
            
            for (float ratio : sizeRatioSteps)
            {
                newFont = font.deriveFont(font.getSize2D() * ratio);
                
                if (fitsInContainer(g, newFont, p, container, lines))
                    break;
                
                int wrapLength = (int)(maxWrapLength * ratio);
                String wrappedString = wrap(s, wrapLength);
                lines = wrappedString.split("\n");
                
                if (fitsInContainer(g, newFont, p, container, lines))
                    break;
            }
            
            if (newFont == null)
                newFont = font;
            
            g.setFont(newFont);
            
            int yNext = p.y;
            for (String line : lines)
            {
                g.drawString(line, p.x, yNext);
                yNext += g.getFontMetrics().getHeight();
            }
        }
        
        private boolean fitsInContainer(
                Graphics g, Font f, Point p, Container cont, String... lines)
        {
            double maxWidth = getMaxWidth(g, f, lines);
            return p.getX() + maxWidth < cont.getBounds().getWidth();
        }
        
        private double getMaxWidth(Graphics g, Font f, String... lines)
        {
            double maxWidth = (double)0;
            for (String line : lines)
            {
                double width = g.getFontMetrics(f).getStringBounds(line, g)
                        .getWidth();
                if (width > maxWidth) { maxWidth = width; }
            }
            return maxWidth;
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
            this.maximumDisplayedStringsCount = 4;
            
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
                    12 + r.nextInt(100)
            );
        }
        
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            if (displayedStrings != null && displayedStrings.length > 0)
                for (StringData dat : displayedStrings)
                    dat.paint(g, this);
        }
        
        // Getters.
        private String getRandomFontFamilyName()
        {
            String[] names =  GraphicsEnvironment.getLocalGraphicsEnvironment()
                    .getAvailableFontFamilyNames(Locale.getDefault());
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
    
    /**
     * @see https://commons.apache.org/proper/commons-text/javadocs/api-release/org/apache/commons/text/WordUtils.html
     */
    private static String wrap(final String str, int wrapLength)
    {
        if (str == null) { return null; }
        if (wrapLength < 1) { wrapLength = 1; }

        String newLineStr = System.lineSeparator();
        String wrapOn = " ";
        final Pattern patternToWrapOn = Pattern.compile(wrapOn);

        final int inputLineLength = str.length();
        int offset = 0;
        final StringBuilder wrappedLine = 
                new StringBuilder(inputLineLength + 32);

        while (offset < inputLineLength)
        {
            int spaceToWrapAt = -1;
            Matcher matcher = patternToWrapOn.matcher(str.substring(offset, 
                    Math.min(offset + wrapLength + 1, inputLineLength)));

            if (matcher.find())
            {
                if (matcher.start() == 0)
                {
                    offset += matcher.end();
                    continue;
                }
                spaceToWrapAt = matcher.start() + offset;
            }

            // only last line without leading spaces is left
            if(inputLineLength - offset <= wrapLength) { break; }

            while(matcher.find()) { spaceToWrapAt = matcher.start() + offset; }

            if (spaceToWrapAt >= offset)
            {
                // normal case
                wrappedLine.append(str.substring(offset, spaceToWrapAt));
                wrappedLine.append(newLineStr);
                offset = spaceToWrapAt + 1;
            }
            else
            {
                // wrap really long word one line at a time
                wrappedLine.append(str.substring(offset, wrapLength + offset));
                wrappedLine.append(newLineStr);
                offset += wrapLength;
            }
        }

        // Whatever is left in line is short enough to just pass through
        wrappedLine.append(str.substring(offset));

        return wrappedLine.toString();
    }
    
    private static Font scaleFont(Graphics g, Rectangle rect, String... lines)
    {
        float fontSize = 20.0f;
        int maxWidth = 0;
        
        Font font = g.getFont().deriveFont(fontSize);
        
        for (String line : lines)
        {
            int width = g.getFontMetrics(font).stringWidth(line);
            if (width > maxWidth)
                maxWidth = width;
        }
        
        fontSize = 0.99F * fontSize * (rect.width / (float)maxWidth);
        
        return g.getFont().deriveFont(fontSize);
    }
}
