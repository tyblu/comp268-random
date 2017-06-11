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
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.Component;
import javax.swing.JComponent;
import javax.swing.text.JTextComponent;
import javax.swing.AbstractButton;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.Random;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

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
    public static void call() { call( new String[] { "" } ); }
    
    public static void call(String[] args)
    {
        JFrame window = new JFrame("JFrame window");
        
        PracticeGUI panel = new PracticeGUI();
        
        window.setContentPane(panel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(400,400);
        window.setSize(800,800);
        window.setVisible(true);
    }
/* -------------------------------------------------------------------------- */
    
    // Constants.
    
    // Instance variables.
    private JButton button1;
    private JLabel label1;
    private JCheckBox checkbox1;
    private JTextField textfield1;
    private JPasswordField passwordfield1;
    private JTextArea textarea1;
    private JScrollPane scroller1;
    private JSlider slider1;
    
    // Constructor.
    public PracticeGUI()
    {
        setLayout( new GridLayout(0,1) );
        
        setBackground(Color.WHITE);
        
        // PracticeGUI (JPanel) Listeners.
//        addMouseListener( new MouseAdapter()
//               {
//                    public void mousePressed(MouseEvent evt)
//                    {
//                        requestFocusInWindow();
//                    }
//                }
//        );
        addMouseListener((MousePressedListener)evt -> requestFocusInWindow() );
        
////        Timer t = new Timer( 1500, (ActionEvent evt) -> { randomize(); } );
//        Timer t = new Timer( 5000, new ActionListener()
//                {
//                    public void actionPerformed(ActionEvent evt)
//                    {
//                        randomize();
//                    }
//                }
//        );
//        t.start();
        (new Timer(5000, (ActionEvent evt) -> { randomize(); } )).start();
        
        this.button1 = new JButton("JButton button1");
//        this.button1.addActionListener( new ActionListener()
//                {
//                    public void actionPerformed(ActionEvent evt)
//                    {
//                        randomize();
//                    }
//                }
//        );
        this.button1.addActionListener( (ActionEvent evt) -> { randomize(); } );
        add(button1);
        
        this.label1 = new JLabel("JLabel label1");
        add(label1);
        
        this.checkbox1 = new JCheckBox("JCheckBox checkbox1");
        add(checkbox1);
        
        this.textfield1 = new JTextField("JTextField textfield1");
        add(textfield1);
        
        this.passwordfield1 = new JPasswordField("JPasswordField passwordfield1");
        add(passwordfield1);
        
        this.textarea1 = new JTextArea("JTextArea textarea1");
        this.scroller1 = new JScrollPane(this.textarea1);
        add(scroller1);
        
        this.slider1 = new JSlider(0, 7, 5);
        add(slider1);
    }
    
    // Getters.
    
    // Setters.
    
    // Methods.
    private void randomize()
    {
        RandomizeJAttributes randJ;
        RandomPlus r = new RandomPlus();
        
        JComponent[] allJComponents = new JComponent[]
                {
                    this.button1, 
                    this.label1, 
                    this.checkbox1, 
                    this.textfield1, 
                    this.passwordfield1,
                    this.textarea1,
                    this.scroller1,
                    this.slider1
                };
        
        for ( JComponent comp : allJComponents)
        {
            randJ = new RandomizeJAttributes();
            
            comp.setFont(randJ.getFont());
            comp.setBackground(randJ.getColor());
            comp.setForeground(randJ.getColor2());
            comp.setOpaque(r.nextBoolean());
            comp.setVisible(r.nextBoolean(1 - 1 / (double)16));
            comp.setEnabled(r.nextBoolean(1 - 1 / (double)8));
            comp.setBorder(randJ.getBorder());
            comp.setToolTipText(randJ.getString());
            
            if ( comp instanceof JTextComponent )
            {
                ((JTextComponent)comp).setText(randJ.getString());
                ((JTextComponent)comp).setMargin(randJ.getInsets());
            }
            
            if (comp instanceof AbstractButton)
            {
                ((AbstractButton)comp).setText(randJ.getString());
                ((AbstractButton)comp).setMargin(randJ.getInsets());
            }
            
            if (comp instanceof JLabel)
            {
                ((JLabel)comp).setText(randJ.getString());
            }
        }
        
        repaint();
    }
    
    private class RandomizeJAttributes
    {
        private RandomPlus r;
        
        private Font font;
        private Color color, color2;
        private String string;
        private Insets insets;
        private Border border;
        
        // Constructor
        public RandomizeJAttributes()
        {
            this.r = new RandomPlus();
            
            this.font = randFont();
            this.color = randColor();
            this.color2 = randColor();
            this.string = randString();
            this.insets = randInsets();
            this.border = randBorder();
        }
        
        // Private methods to help constructor
        private Font randFont()
        {
            String fontName;
            int fontStyle;
            int fontSize;
            
            switch(r.nextInt(3))
            {
            case 0:
                fontName = Font.SANS_SERIF;
                break;
            case 1:
                fontName = Font.SERIF;
                break;
            case 2:
            default:
                fontName = Font.MONOSPACED;
                break;
            }
            
            fontStyle = Font.PLAIN;
            if (r.nextBoolean())
                fontStyle += Font.BOLD;
            if (r.nextBoolean())
                fontStyle += Font.ITALIC;
            
            fontSize = r.nextInt(60) + 12;
            
            return new Font(fontName, fontStyle, fontSize);
        }
        
        private Color randColor()
        {
            return new Color(r.nextFloat(), r.nextFloat(), r.nextFloat());
        }
        
        private String randString()
        {
            switch(r.nextInt(7))
            {
            case 0: return "Life is a beautiful struggle.";
            case 1: return "Every moment matters.";
            case 2: return "Don\'t be obtuse.";
            case 3: return "No rain, no flowers.";
            case 4: return "C\'est la vie.";
            case 5: return "Take every chance.";
            case 6: return "Drop every fear.";
            default: return "DEFAULT";
            }
        }
        
        private Insets randInsets()
        {
            return new Insets(
                    r.nextInt(20), 
                    r.nextInt(20), 
                    r.nextInt(20), 
                    r.nextInt(20)
            );
        }
        
        private Border randBorder()
        {
            int[] m = new int[4];
            for (int i=0; i<4; i++)
                m[i] = r.nextInt(20);
            
            switch(r.nextInt(7))
            {
            case 0:
                return BorderFactory.createEmptyBorder(m[0], m[1], m[2], m[3]);
            case 1:
                return BorderFactory.createEtchedBorder();
            case 2:
                return BorderFactory.createLineBorder(randColor(), 
                        r.nextInt(20), r.nextBoolean());
            case 3:
                return BorderFactory.createLoweredSoftBevelBorder();
            case 4:
                return BorderFactory.createMatteBorder(m[0], m[1], m[2], m[3], 
                        randColor());
            case 5:
                return BorderFactory.createRaisedSoftBevelBorder();
            case 6:
            default:
                return BorderFactory.createTitledBorder(randString());
            }
        }

        // Getters
        public Font getFont() { return this.font; }
        public Color getColor() { return this.color; }
        public Color getColor2() { return this.color2; }
        public String getString() { return this.string; }
        public Insets getInsets() { return this.insets; }
        public Border getBorder() { return this.border; }
    }
    
    private class RandomPlus extends Random
    {
        // Methods
        public boolean nextBoolean(double probability)
        {
            return nextDouble() <= probability;
        }
    }
    
    /**
     * Note that {@code mousePressed(MouseEvent)} is missing, allowing the use
     * of lambdas to implement this interface.
     *
     * {@code component.addMouseListener((MousePressedListener)evt -> foo() );}
     */
    interface MousePressedListener extends MouseListener
    {
        @Override
        default void mouseClicked(MouseEvent evt) {}
        @Override
        default void mouseReleased(MouseEvent evt) {}
        @Override
        default void mouseEntered(MouseEvent evt) {}
        @Override
        default void mouseExited(MouseEvent evt) {}
    }
}
