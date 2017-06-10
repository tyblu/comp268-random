package Chapter06;

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

 import javax.swing.JPanel;
 import javax.swing.JFrame;
 import java.awt.GridLayout;
 
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 
 import javax.swing.JButton;
 import javax.swing.JLabel;
 import javax.swing.JCheckBox;
 import javax.swing.JTextField;
 import javax.swing.JPasswordField;
 import javax.swing.JTextArea;
 import javax.swing.JScrollPane;
 import javax.swing.JSlider;
 
 import java.awt.Color;
 
/**
 * Example GUI.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 9, 2017
 * Version      0.1
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 6: Introduction to GUI Programming</a>
 */
public class PracticeGUI extends JPanel
{
/* -------------------------------------------------------------------------- */
    public static void call()
    {
        call( new String[] { "" } );
    }
    
    public static void call(String[] args)
    {
        JFrame window = new JFrame("JFrame window");
        
        PracticeGUI panel = new PracticeGUI();
        panel.setLayout( new GridLayout(0,1) );
        
        JButton button1 = new JButton("JButton button1");
        panel.add(button1);
        
        JLabel label1 = new JLabel("JLabel label1");
        panel.add(label1);
        
        JCheckBox checkbox1 = new JCheckBox("JCheckBox checkbox1");
        panel.add(checkbox1);
        
        JTextField textfield1 = new JTextField("JTextField textfield1");
        panel.add(textfield1);
        
        JPasswordField passwordfield1 = 
                new JPasswordField("JPasswordField passwordfield1");
        panel.add(passwordfield1);
        
        JTextArea textarea1 = new JTextArea("JTextArea textarea1");
        JScrollPane scroller1 = new JScrollPane(textarea1);
        panel.add(scroller1);
        
        JSlider slider1 = new JSlider(0, 7, 5);
        panel.add(slider1);
        
        
        window.setContentPane(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(400,400);
        window.setSize(800,800);
        window.setVisible(true);
    }
/* -------------------------------------------------------------------------- */
    
    // Constants.
    
    // Instance variables.
    
    // Constructor.
    public PracticeGUI()
    {
        setBackground(Color.WHITE);
        
        addMouseListener( new MouseAdapter()
                {
                    public void mousePressed(MouseEvent evt)
                    {
                        requestFocusInWindow();
                    }
                }
        );
        
        
    }
    
    // Getters.
    
    // Setters.
    
    // Methods.
}