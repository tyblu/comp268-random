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
package Chapter07E04;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 * CenteredWindow
 * 
 * Needs {@link GUITools}.
 *
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 24, 2017
 * Version      1.0
 * 
 * Based on and References:
 * 
 */
public class CenteredWindow extends JFrame
{
    // Instance variables.
    
    // Constructors.
    /**
     * Constructor.
     * 
     * @param title
     * @param content
     * @param menu
     */
    public CenteredWindow(String title, JPanel content, JMenuBar menu)
    {
        super(title);
        setContentPane(content);
        setJMenuBar(menu);
        pack();
        resetLocation();
    }
    public CenteredWindow() { this(null); }
    public CenteredWindow(String title) { this(title, null); }
    public CenteredWindow(String title, JPanel content) { this(title, content, null); }

    // Methods
    private void resetLocation()
    {
        setLocation(
                (GUITools.getScreenSize().width - getWidth()) / 2,
                (GUITools.getScreenSize().height - getHeight()) / 2
        );
    }
}
