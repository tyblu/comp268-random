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
package Chapter06;

import java.awt.*;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *              Textbook Example Program, Chapter 6
 * Class:       RandomStringsPanel.java
 * Purpose:     This panel displays 25 copies of a message. The colour and
 *              position of each message is selected at random. The font of
 *              each message is randomly chosen from among five possible fonts.
 *              The messages are displayed on a black background.
 *              Note: The style of drawing used here is poor because every time
 *              the paintComponent() method is called, new random values are
 *              used. This means that a different picture will be drawn each
 *              time.
 * 
 *      v1.1    Added call() so I could keep everything in one file, as per
 *              Eck's suggestion (p270).
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 2, 2017
 * Version      1.1
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014: Chapter 6: Introduction to GUI Programming, pp267-270
 * 
 */
public class RandomStringsPanel extends JPanel
{
    private String message;
    private Font[] fonts = new Font[5];
    
    public RandomStringsPanel()
    {
        this(null);
    }
    
    public RandomStringsPanel(String msgString)
    {
        if (msgString == null)
            this.message = "Java!";
        else
            this.message = msgString;
        
        this.fonts[0] = new Font("Serif", Font.BOLD, 14);
        this.fonts[1] = new Font("SansSerif", Font.BOLD + Font.ITALIC, 24);
        this.fonts[2] = new Font("Monospaced", Font.PLAIN, 30);
        this.fonts[3] = new Font("Dialog", Font.PLAIN, 36);
        this.fonts[4] = new Font("Serif", Font.ITALIC, 48);
        
        setBackground(Color.BLACK);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint( 
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        
        int width = getWidth();
        int height = getHeight();
        
        Random rand = new Random();
        
        for (int i=0; i<25; i++)
        {
            g.setFont(this.fonts[rand.nextInt(5)]);
            
            g.setColor(Color.getHSBColor(rand.nextFloat(), 1.0F, 1.0F));
            
            int positionRandomX = rand.nextInt(width + 40) - 50;
            int positionRandomY = rand.nextInt(height + 20);
            g.drawString(this.message, positionRandomX, positionRandomY);
        }
    }
    
    public static void call()
    {
        JFrame window = new JFrame("Java!");
        RandomStringsPanel content = new RandomStringsPanel();
        
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(4*120,4*70);
        window.setSize(4*350,4*250);
        window.setVisible(true);
    }
}
