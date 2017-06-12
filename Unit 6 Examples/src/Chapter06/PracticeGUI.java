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

import javax.swing.SpringLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import java.awt.BorderLayout;

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

import javax.swing.SwingConstants;

/**
 * Example GUI.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 9, 2017
 * Version      0.3
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
    private JPasswordField passfield1;
    private JTextArea textarea1;
    private JScrollPane scroller1;
    private JSlider slider1;
    private JComponent[] allJComponents;
    
    // Constructor.
    public PracticeGUI()
    {
        // Add panel listeners.
        addMouseListener((MousePressedListener)evt -> requestFocusInWindow() );
        (new Timer(5000, (ActionEvent evt) -> { randomize(); } )).start();
        
        reset();
        
        setAllJComponents();
    }

    
    // Getters.
    public JComponent[] getAllJComponents() { return this.allJComponents; }
    
    // Setters.
    public void setButton1(JButton button1)
    {
        this.button1 = button1;
    }
    
    public void setLabel1(JLabel label1)
    {
        this.label1 = label1;
    }
    
    public void setCheckbox1(JCheckBox checkbox1)
    {
        this.checkbox1 = checkbox1;
    }
    
    public void setTextfield1(JTextField textfield1)
    {
        this.textfield1 = textfield1;
    }
    
    public void setPassfield1(JPasswordField passfield1)
    {
        this.passfield1 = passfield1;
    }
    
    public void setTextarea1(JTextArea textarea1)
    {
        this.textarea1 = textarea1;
    }
    
    public void setScroller1(JScrollPane scroller1)
    {
        this.scroller1 = scroller1;
    }
    
    public void setSlider1(JSlider slider1)
    {
        this.slider1 = slider1;
    }

    public void setAllJComponents(JComponent[] allJComponents)
    {
        this.allJComponents = allJComponents;
    }
        
    private void setAllJComponents(
            JButton button1,
            JLabel label1,
            JCheckBox checkbox1,
            JTextField textfield1,
            JPasswordField passfield1,
            JTextArea textarea1,
            JScrollPane scroller1,
            JSlider slider1
    )
    {
        setAllJComponents( new JComponent[]
                {
                    button1, 
                    label1, 
                    checkbox1, 
                    textfield1, 
                    passfield1,
                    textarea1,
                    scroller1,
                    slider1
                }
        );
    }
    
    private void setAllJComponents()
    {
        setAllJComponents(
                this.button1, 
                this.label1, 
                this.checkbox1, 
                this.textfield1, 
                this.passfield1,
                this.textarea1,
                this.scroller1,
                this.slider1
        );
    }
    
    // Methods.
    private void reset()
    {
        if (getComponentCount() > 1)
        {
            this.removeAll();
            this.revalidate();
        }
        
        setLayout( new GridLayout(0,1) );
        
        setBackground(Color.WHITE);

        // Re-initialize components.
        replaceJComponent(this.button1, 
                new JButton("JButton button1"));
        replaceJComponent(this.label1, 
                new JLabel("JLabel label1"));
        replaceJComponent(this.checkbox1, 
                new JCheckBox("JCheckBox checkbox1"));
        replaceJComponent(this.textfield1, 
                new JTextField("JTextField textfield1"));
        replaceJComponent(this.passfield1, 
                new JPasswordField("JPasswordField passfield1"));
        replaceJComponent(this.textarea1, 
                new JTextArea("JTextArea textarea1"));
        replaceJComponent(this.scroller1, 
                new JScrollPane(this.textarea1));
        replaceJComponent(this.slider1, 
                new JSlider(0, 7, 5));
        
        // Add component listeners.
        this.button1.addActionListener( (ActionEvent evt) -> { reset(); } );
        
        // Add components.
        // Need to change to add(button1, getLayoutPosition(button1));
        int pos = 0;
        add(button1, pos++);
        add(label1, pos++);
        add(checkbox1, pos++);
        add(textfield1, pos++);
        add(passfield1, pos++);
        add(scroller1, pos++);  // textarea1 inside scroller1
        add(slider1, pos++);
        
        this.repaint();
    }
        
    private void randomize()
    {
        RandomPlus r = new RandomPlus();
        
        // set to random layout and mix up positions: this.
        // set random background and foreground colours
        
        for ( JComponent comp : this.allJComponents)
        {
            comp.setFont(r.nextFont());
            comp.setBackground(r.nextColor());
            comp.setForeground(r.nextColor());
            comp.setOpaque(r.nextBoolean());
            comp.setVisible(r.nextBoolean(1 - 1 / (double)16));
            comp.setEnabled(r.nextBoolean(1 - 1 / (double)8));
            comp.setBorder(r.nextBorder());
            comp.setToolTipText(r.nextString());
            
            if (comp instanceof JTextComponent) // JTextField, JPasswordField, JTextArea
            {
                JTextComponent tc = (JTextComponent)comp;
                tc.setText(r.nextString());
                tc.setMargin(r.nextInsets());
                
                if (r.nextBoolean(1 - 1 / (double)4))
                    tc.select(r.nextInt(8), 8 + r.nextInt(8));
                else if (r.nextBoolean())
                    tc.selectAll();
                
                tc.setDisabledTextColor(r.nextColor());

//                tc.invalidate();
            }
            
            if (comp instanceof AbstractButton) // JButton and JCheckBox
            {
                AbstractButton ab = (AbstractButton)comp;
                ab.setText(r.nextString());
                ab.setMargin(r.nextInsets());
                ab.setContentAreaFilled(r.nextBoolean(1 - 1 / (double)8));
                ab.setVerticalAlignment(r.nextVerticalAlignment());
                ab.setHorizontalAlignment(r.nextHorizontalAlignment());
                ab.setVerticalTextPosition(r.nextVerticalAlignment());
                ab.setHorizontalTextPosition(r.nextHorizontalAlignment());
            }
            
            if (comp instanceof JLabel)
            {
                JLabel l = (JLabel)comp;
                l.setText(r.nextString());
            }
        }
        
        repaint();
    }
    
    private void replaceJComponent(JComponent currentComp, JComponent newComp)
    {
        if (currentComp != null)
        {
            remove(currentComp);
            revalidate();
        }
        
        setJComponent(newComp);
        setAllJComponents();
        add(newComp);
    }
    
    private void setJComponent(JComponent comp)
    {
        String stringJComponentClass = comp.getClass().toString()
                .substring(("class javax.swing.").length());
        
        switch (comp.getClass().toString().substring(18))
        {
        case "JButton":
            setButton1((JButton)comp);
            break;
        case "JLabel":
            setLabel1((JLabel)comp);
            break;
        case "JCheckBox":
            setCheckbox1((JCheckBox)comp);
            break;
        case "JTextField":
            setTextfield1((JTextField)comp);
            break;
        case "JPasswordField":
            setPassfield1((JPasswordField)comp);
            break;
        case "JTextArea":
            setTextarea1((JTextArea)comp);
            break;
        case "JScrollPane":
            setScroller1((JScrollPane)comp);
            break;
        case "JSlider":
            setSlider1((JSlider)comp);
            break;
        default:
            break;
        }
    }
    
    // Inner classes.
    private class RandomPlus extends Random
    {
        // Methods
        public Font nextFont()
        {
            String fontName;
            int fontStyle;
            int fontSize;
            
            switch(nextInt(3))
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
            if (nextBoolean())
                fontStyle += Font.BOLD;
            if (nextBoolean())
                fontStyle += Font.ITALIC;
            
            fontSize = nextInt(60) + 12;
            
            return new Font(fontName, fontStyle, fontSize);
        }
        
        public Color nextColor()
        {
            return new Color(nextFloat(), nextFloat(), nextFloat());
        }
        
        public String nextString()
        {
            switch(nextInt(7))
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
        
        public Insets nextInsets()
        {
            return new Insets(
                    nextInt(20), 
                    nextInt(20), 
                    nextInt(20), 
                    nextInt(20)
            );
        }
        
        public Border nextBorder()
        {
            int[] m = new int[4];
            for (int i=0; i<4; i++)
                m[i] = nextInt(20);
            
            switch(nextInt(7))
            {
            case 0:
                return BorderFactory.createEmptyBorder(m[0], m[1], m[2], m[3]);
            case 1:
                return BorderFactory.createEtchedBorder();
            case 2:
                return BorderFactory.createLineBorder(nextColor(), 
                        nextInt(20), nextBoolean());
            case 3:
                return BorderFactory.createLoweredSoftBevelBorder();
            case 4:
                return BorderFactory.createMatteBorder(m[0], m[1], m[2], m[3], 
                        nextColor());
            case 5:
                return BorderFactory.createRaisedSoftBevelBorder();
            case 6:
            default:
                return BorderFactory.createTitledBorder(nextString());
            }
        }
        
        public int nextVerticalAlignment()
        {
            switch(nextInt(3))
            {
            case 0: return SwingConstants.CENTER;
            case 1: return SwingConstants.TOP;
            case 3: default: return SwingConstants.BOTTOM;
            }
        }
        
        public int nextHorizontalAlignment()
        {
            switch(nextInt(5))
            {
            case 0: return SwingConstants.RIGHT;
            case 1: return SwingConstants.LEFT;
            case 2: return SwingConstants.CENTER;
            case 3: return SwingConstants.LEADING;
            case 4: default: return SwingConstants.TRAILING;
            }
        }
        
        public Object nextLayout()
        {
            switch(nextInt(8))
            {
            case 0:             // SpringLayout
                break;
            case 1:             // BoxLayout
                break;
            case 2:             // CardLayout
                break;
            case 3:             // FlowLayout
                break;
            case 4:             // GridBagLayout
                break;
            case 5: default:    // GridLayout
                break;
            case 6:             // GroupLayout
                break;
            case 7:             // BorderLayout
                break;
            }
            
            return null;
        }
        
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
