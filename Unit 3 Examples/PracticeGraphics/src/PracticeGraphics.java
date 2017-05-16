/**
 *              Textbook Example Program
 * Class:       PracticeGraphics.java
 * Purpose:     Tests some of the Graphics class introduced in the textbook.
 *              Copied several examples into this example program then got them
 *              to run sequentially.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 16, 2017
 * Version     1.0
 * 
 * Based on:   Eck, pp 122-126
 * 
 * References:  Eck's RandomCircles, MovingRects, SimpleAnimationStarter.java
 *              
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public abstract class PracticeGraphics extends JPanel implements ActionListener {
    
    private static final boolean ENABLE1 = true;    // MovingRects
    private static final boolean ENABLE2 = true;    // RandomCircles
    private static final boolean ENABLE3 = true;    // SimpleAnimationStarter
    
    public static void main(String[] args) {
        
        if ( ENABLE1 ) {
            JFrame window = new JFrame("Infinite motion");

            MovingRects drawingArea = new MovingRects();
            drawingArea.setBackground(Color.WHITE);
            window.setContentPane(drawingArea);

            drawingArea.setPreferredSize(new Dimension(600,450));

            window.pack();
            window.setLocation(100,50);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(true);

            Timer frameTimer = new Timer(20,drawingArea);

            window.setVisible(true);
            frameTimer.start();   
        }
        
        if ( ENABLE2 ) {
           JFrame window = new JFrame("Random Disks");

           RandomCircles drawingArea = new RandomCircles();

           drawingArea.setBackground(Color.WHITE);
           window.setContentPane(drawingArea);
           drawingArea.setPreferredSize(new Dimension(500,500));

           window.pack();
           window.setLocation(100,50);
           window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           window.setResizable(false);

           Timer frameTimer = new Timer(3000,drawingArea);

           window.setVisible(true);
           frameTimer.start();
        }
        
        if ( ENABLE3 ) {
            JFrame window = new JFrame("Simple Animation");

            SimpleAnimationStarter drawingArea = new SimpleAnimationStarter();

            drawingArea.setBackground(Color.WHITE);
            window.setContentPane(drawingArea);

            drawingArea.setPreferredSize(new Dimension(600,450));

            window.pack();
            window.setLocation(100,50);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(true);

            Timer frameTimer = new Timer(20,drawingArea);

            window.setVisible(true);
            frameTimer.start();
        }

    }
}
