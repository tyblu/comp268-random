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

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 *              Textbook Example Program, Chapter 6
 * Class:       ClickableRandomStrings.java
 * Purpose:     Repaints the window in RandomStringsPanel with a mouse click.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 2, 2017
 * Version      1.0
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014: Chapter 6: Introduction to GUI Programming, pp273-275
 * @see RandomStringsPanel
 * @see RepaintOnClick
 * 
 */
public class ClickableRandomStrings {
    
    public static void call()
    {
        JFrame window = new JFrame("Click me to redraw!");
        RandomStringsPanel content = new RandomStringsPanel();
        
        content.addMouseListener(new RepaintOnClick());
        
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(4*120,4*70);
        window.setSize(4*350,4*250);
        window.setVisible(true);
    }
    
    /**
    *              Textbook Example Program, Chapter 6
    * Class:       RepaintOnClick
    * Purpose:     An object of type RepaintOnClick is a MouseListener that will
    *              respond to a mousePressed event by calling the repaint() method
    *              of the source of the event. That is, a RepaintOnClick object
    *              can be added as a mouse listener to any Component; when the
    *              user clicks that component, the component will be repainted.
    * 
    * @author:     Tyler Lucas
    * Student ID:  3305203
    * Date:        June 2, 2017
    * Version      1.0
    * 
    * Based on and References:
    * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
    *      2014: Chapter 6: Introduction to GUI Programming, pp273-275
    * @see RandomStringsPanel
    * 
    */
    public static class RepaintOnClick implements MouseListener
    {
        public void mousePressed(MouseEvent evt)
        {
            ((Component)evt.getSource()).repaint();
        }

        public void mouseClicked(MouseEvent evt) {}
        public void mouseReleased(MouseEvent evt) {}
        public void mouseEntered(MouseEvent evt) {}
        public void mouseExited(MouseEvent evt) {}
    }
}
