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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *              A panel that displays information about mouse events, including
 *              the type of event, the position of the mouse, a list of 
 *              modifier keys that were active when the event occurred, and an
 *              indication of which mouse button was involved, if any.
 *              
 *              This class has a routine, {@code call()}, that allows it to run
 *              as an application when called from {@link MainCaller}.
 * 
 *              <h1>Textbook Example Program, Chapter 6</h1>
 *              <h1>SimpleTrackMouse.java</h1>
 * <ul>  
 * <li> v1.1    <ul>
 *      <li>    Implemented {@code paintComponent()} and related methods
 *              instead of drawing in {@code mousePressed()} and needing to
 *              fetch the graphics component.</li>
 *      <li>    Implemented nested classes {@code CustomMouseListener} and
 *              {@code CustomComponentListener} to better organize code and to
 *              detect and redraw window when the user resizes it.</li>
 *      <li>    Implemented private methods for constructor to reference
 *              instead of over-rideable public ones from JPanel.</li>
 *      <li>    Implemented setters and getters.</li>
 *              </ul></li>
 * <li> v1.2    <ul>
 *      <li>    Added MIT license. Eck wrote it originally under 
 *              <a href="https://creativecommons.org/licenses/by-nc-sa/3.0/">
 *              CC BY-NC-SA 3.0</a>. Not sure how re-licensing it works.</li>
 *      <li>    Improved comments on everything.</li>
 *      <li>    Re-organized code.</li>
 *      <li>    Added constructor 
 *              {@code CustomEvent(EventType, ModifierKey[], Point)}. 
 *              It was missing.</li>
 *      <li>    Made helper methods {@code removeNONEFromKeys()} and
 *              {@code removeFromModifierKeyArray(int, ModifierKey[])}, in
 *              {@code CustomEvent}, to get rid of {@code ModifierKey.NONE}
 *              element values when other keys are active. Implemented by 
 *              adding {@code removeNONEFromKeys()} to 
 *              {@code setKeys(ModifierKey[])} in {@code CustomEvent}.</li>
 *              </ul></li>
 * </ul>
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 2, 2017
 * Version      1.2
 * 
 * Based on and References:
 * @see <a href="http://math.hws.edu/javanotes/">
 *      <cite>Introduction to Programming Using Java, Seventh Edition</cite>,
 *      by Eck, David J., 2014: Chapter 6: Introduction to GUI Programming, 
 *      pp275-278</a>
 * 
 */
public class SimpleTrackMouse extends JPanel
{
/* -------------------------------------------------------------------------- */
    /**
     * Similar to a {@code main(String[] args)} routine, is called by
     * {@link Chapter06.MainCaller}, as are all {@code call()} routines in most
     * example and exercise classes. Requires setting the appropriate boolean
     * variables in MainCaller in order to activate.
     */
    public static void call()
    {
        JFrame window = new JFrame("Click Me to Redraw");
        SimpleTrackMouse content = new SimpleTrackMouse();
        window.setContentPane(content);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(4*120,4*70);
        window.setSize(4*450,4*350);
        window.setVisible(true);
    }
/* -------------------------------------------------------------------------- */
    
/* -------------------------------------------------------------------------- */
    // Instance variable.
    /**
     * Holds current (or last) mouse event details.
     */
    private CustomEvent currentEvent = new CustomEvent();
    
/* -------------------------------------------------------------------------- */
    
    // Constructor.
    /**
     * Constructor creates a mouse listener object and sets it to listen for
     * mouse events and mouse motion events on the panel.
     */
    public SimpleTrackMouse()
    { 
        // Set background color and arrange for the panel to listen for mouse events.
        setBackground(Color.WHITE);
        MouseHandler listener = new MouseHandler();
        addMouseListener(listener);        // Register mouse listener.
        addMouseMotionListener(listener);  // Register mouse motion listener.
    }
    
/* -------------------------------------------------------------------------- */
    
    // Enums, inline/nested classes.
    
    /**
    * Gives the type of the most recent mouse event.
    */
    private enum EventType
    { 
        NONE, PRESSED, RELEASED, CLICKED, ENTERED, EXITED, MOVED, DRAGGED;
    
        @Override
        public String toString() { return super.toString().toLowerCase(); }
    }

    /**
    * Gives special keys that are held down.
    */
    private enum ModifierKey {
        NONE, SHIFT, CTRL, META, ALT;
    
        @Override
        public String toString() { return super.toString().toLowerCase(); }
    }

    /**
    * Information about which mouse button was used.
    */
    private enum MouseButton { 
        NONE, LEFT, MIDDLE, RIGHT;
        
        @Override
        public String toString() { return super.toString().toLowerCase(); }
    }
    
    /**
     * Framework to store, set, and retrieve mouse (with some keys) event data.
     * Large range of [overloaded] constructors means you can create it with
     * any combination of EventType, ModifierKey, MouseButton, and Point data.
     * Requires Point (x,y) data for every constructor except blank
     * @code{CustomEvent()}.
     */
    private class CustomEvent
    {
        // Instance variables.
        private EventType event = EventType.NONE;
        private ModifierKey[] keys;
        private MouseButton button = MouseButton.NONE;
        private Point xy;
        
        // Constructors
        /**
         * Full-parameter constructor, referenced by all other [overloaded]
         * constructors. Reduced-parameter constructors set missing enums to
         * their respective {@code .NONE} value, missing mouse coordinates
         * ({@code Point xy}) to {@literal (0,0)}. An alternative to using one
         * of the overloaded constructors is to create an incomplete (e.g.
         * empty constructor) object, then set variables with 
         * {@link #setEvent(Chapter06.SimpleTrackMouse.EventType)}, 
         * {@link #setKeys(Chapter06.SimpleTrackMouse.ModifierKey[])}, 
         * {@link #setKeys(Chapter06.SimpleTrackMouse.ModifierKey)}, 
         * {@link #setButton(Chapter06.SimpleTrackMouse.MouseButton)}, and 
         * {@link #addKey(Chapter06.SimpleTrackMouse.ModifierKey)}.
         * 
         * <p>The following parameter combinations are permissable:
         * <ul>
         * <li>EventType, ModifierKey[], MouseButton, Point</li>
         * <li>EventType, ModifierKey, MouseButton, Point</li>
         * <li>EventType, ModifierKey[], Point</li>
         * <li>EventType, ModifierKey, Point</li>
         * <li>EventType, MouseButton, Point</li>
         * <li>EventType, Point</li>
         * <li>ModifierKey[], MouseButton, Point</li>
         * <li>ModifierKey, MouseButton, Point</li>
         * <li>ModifierKey[], Point</li>
         * <li>ModifierKey, Point</li>
         * <li>MouseButton, Point</li>
         * <li>Point</li>
         * <li>[none] (empty constructor)</li>
         * </ul>
         * @param event EventType enum. Defaults to {@code EventType.NONE} when
         *          not specified.
         * @param keys Array of ModifierKey enums. Set to
         *          {@code { event }} (single-element array) when parameter is
         *          not an array, just a value. Defaults to 
         *          {@code { EventType.NONE }} (single-element array) when not
         *          specified.
         * @param button MouseButton enum. Defaults to {@code MouseButton.NONE}
         *          when not specified.
         * @param xy Point class coordinate of mouse event. Defaults to
         *          {@literal (0,0)} in empty constructor.
         */
        public CustomEvent(EventType event, ModifierKey[] keys,
                MouseButton button, Point xy)
        {
            this.event = event;
            this.keys = keys;
            this.button = button;
            this.xy = xy;
            
            removeNONEFromKeys();
        }
        
        /**
         * Alternate-parameter constructor that calls
         * {@link CustomEvent(EventType, ModifierKey[], MouseButton, Point)}.
         * @param event EventType enum value.
         * @param key ModifierKey enum value.
         * @param button MouseButton enum value.
         * @param xy Point class object.
         */
        public CustomEvent(EventType event, ModifierKey key, 
                MouseButton button, Point xy)
        {
            this(event, new ModifierKey[]{key}, button, xy);
        }
        
        /**
         * Reduced-parameter constructor that calls
         * {@link CustomEvent(EventType, ModifierKey[], MouseButton, Point)}.
         * @param event EventType enum value.
         * @param keys Array of ModifierKey enum values.
         * @param xy Point class object.
         */
        public CustomEvent(EventType event, ModifierKey[] keys, Point xy)
        {
            this(event, keys, MouseButton.NONE, xy);
        }
        
        /**
         * Reduced-parameter constructor that calls
         * {@link CustomEvent(EventType, ModifierKey[], MouseButton, Point)}.
         * @param event EventType enum value.
         * @param xy Point class object.
         */
        public CustomEvent(EventType event, Point xy)
        {
            this(event, ModifierKey.NONE, xy);
        }
        
        /**
         * Reduced-parameter constructor that calls
         * {@link CustomEvent(EventType, ModifierKey[], MouseButton, Point)}.
         * @param key ModifierKey enum value.
         * @param xy Point class object.
         */
        public CustomEvent(ModifierKey key, Point xy)
        {
            this(EventType.NONE, key, xy);
        }
        
        /**
         * Reduced-parameter constructor that calls
         * {@link CustomEvent(EventType, ModifierKey[], MouseButton, Point)}.
         * @param button MouseButton enum value.
         * @param xy Point class object.
         */
        public CustomEvent(MouseButton button, Point xy)
        {
            this(EventType.NONE, button, xy);
        }
        
        /**
         * Reduced-parameter constructor that calls
         * {@link CustomEvent(EventType, ModifierKey[], MouseButton, Point)}.
         * @param event EventType enum value.
         * @param key ModifierKey enum value.
         * @param xy Point class object.
         */
        public CustomEvent(EventType event, ModifierKey key, Point xy)
        {
            this(event, key, MouseButton.NONE, xy);
        }
        
        /**
         * Reduced-parameter constructor that calls
         * {@link CustomEvent(EventType, ModifierKey[], MouseButton, Point)}.
         * @param event EventType enum value.
         * @param button MouseButton enum value.
         * @param xy Point class object.
         */
        public CustomEvent(EventType event, MouseButton button, Point xy)
        {
            this(event, ModifierKey.NONE, button, xy);
        }
        
        /**
         * Reduced-parameter constructor that calls
         * {@link CustomEvent(EventType, ModifierKey[], MouseButton, Point)}.
         * @param key ModifierKey enum value.
         * @param button MouseButton enum value.
         * @param xy Point class object.
         */
        public CustomEvent(ModifierKey key, MouseButton button, Point xy)
        {
            this(EventType.NONE, key, button, xy);
        }
        
        /**
         * Reduced-parameter constructor that calls
         * {@link CustomEvent(EventType, ModifierKey[], MouseButton, Point)}.
         * @param keys Array of ModifierKey enum values.
         * @param xy Point class object.
         */
        public CustomEvent(ModifierKey[] keys, Point xy)
        {
            this(keys, MouseButton.NONE, xy);
        }
        
        /**
         * Reduced-parameter constructor that calls
         * {@link CustomEvent(EventType, ModifierKey[], MouseButton, Point)}.
         * @param keys Array of ModifierKey enum values.
         * @param button MouseButton enum value.
         * @param xy Point class object.
         */
        public CustomEvent(ModifierKey[] keys, MouseButton button, Point xy)
        {
            this(EventType.NONE, keys, button, xy);
        }
        
        /**
         * Empty constructor. Calls
         * {@link CustomEvent(EventType, ModifierKey[], MouseButton, Point)}.
         */
        public CustomEvent()
        {
            this(EventType.NONE, new Point(0,0));
        }

        // Getters
        /**
         * Getter method that returns value of {@code EventType event}.
         * @return value of {@code EventType event}
         */
        public EventType getEvent()
        {
            return this.event;
        }

        /**
         * Getter method that returns the array {@code ModifierKey[] keys}, the
         * keyboard modifier keys (alt, control, meta, shift) pressed at the
         * time of the mouse event.
         * @return value of {@code ModifierKey[] keys}
         */
        public ModifierKey[] getKeys()
        {
            return this.keys;
        }

        /**
         * Getter method that returns value of {@code MouseButton button}, the
         * mouse button pressed at the time of the mouse event.
         * @return value of {@code MouseButton button}
         */
        public MouseButton getButton()
        {
            return this.button;
        }
        
        /**
         * Getter method that returns value of {@code Point xy}, the mouse
         * coordinates at the time of the mouse event.
         * @return value of {@code Point xy}
         */
        public Point getXY()
        {
            return this.xy;
        }
        
        // Setters
        /**
         * Setter method that sets the value of {@code EventType event}.
         * @param event value to set {@code EventType event}
         */
        public void setEvent(EventType event)
        {
            this.event = event;
        }
        
        /**
         * Setter method that sets the array {@code ModifierKey[] keys}.
         * @param keys array of {@code ModifierKey} values
         */
        public void setKeys(ModifierKey[] keys)
        {
            this.keys = keys;
            
            // Remove ModifierKey.NONE from array
            removeNONEFromKeys();
        }
        
        /**
         * Setter method that sets the array {@code ModifierKey[] keys} to the 
         * singular element {@code { key }}, using 
         * {@link setKeys(ModifierKey[])}.
         * @param key value to set singular element of {@code ModifierKey[] keys}
         */
        public void setKeys(ModifierKey key)
        {
            setKeys( new ModifierKey[]{ key } );
        }
        
        /**
         * Setter method that sets the value of {@code Point xy}, the mouse
         * coordinates at the time of a mouse event.
         * @param xy value of {@code Point xy}
         */
        public void setXY(Point xy)
        {
            this.xy = xy;
        }
        
        /**
         * Setter method that sets the value of {@code MouseButton button}.
         * @param button value to set {@code MouseButton button}
         */
        public void setButton(MouseButton button)
        {
            this.button = button;
        }
        
        // Methods
        /**
         * Appends a {@code Modifierkey} enum value to existing array, 
         * {@code ModifierKey[] keys}. Works if {@literal keys} is empty or not 
         * yet set.
         * @param key value to append to array {@code ModifierKey[] keys}
         */
        public void addKey(ModifierKey key)
        {
            if (getKeys().length < 1)
                setKeys(key);
            
            setKeys( addToModifierKeyArray(key, getKeys()) );
        }
        
        private void removeNONEFromKeys()
        {
            ModifierKey[] currentKeys = getKeys();
            
            if (currentKeys.length < 2)
                return;
            
            ModifierKey[] newKeys = null;
            int count = 0;
            for (int i=0; i<currentKeys.length; i++)
            {
                if (currentKeys[i] == ModifierKey.NONE)
                {
                    count++;
                    newKeys = removeFromModifierKeyArray(i, currentKeys);
                }
            }
            
            if (count > 0)
                setKeys(newKeys);
        }
        
        /**
         * Helper method for {@link #addKey(ModifierKey)}. Appends a 
         * {@code Modifierkey} enum value to existing array, 
         * {@code ModifierKey[] keys}.
         * @param newElement new {@code Modifierkey} enum value (element) to 
         *          append to {@code Modifierkey[]} array.
         * @param array {@code Modifierkey[]} array to which to append a new
         *          element.
         * @return {@code Modifierkey[]} array with new {@code Modifierkey} 
         *          element appended, its size one element larger than previous.
         */
        private ModifierKey[] addToModifierKeyArray(
                ModifierKey newElement, ModifierKey[] array)
        {
            ModifierKey[] newArray = new ModifierKey[array.length + 1];
            System.arraycopy(array, 0, newArray, 0, array.length);
            newArray[newArray.length - 1] = newElement;
            
            return newArray;
        }
        
        /**
         * Helper method for {@link #addKey(ModifierKey)}. Removes an 
         * {@code Modifierkey} enum element from existing array at {@code index}, 
         * returning a {@code ModifierKey[]} array with one fewer element.
         * @param index position of element to remove, starting from 0.
         * @param array {@code Modifierkey[]} array from which to remove the 
         *          element.
         * @return {@code Modifierkey[]} array with {@literal index}-specified 
         *          {@code Modifierkey} element removed, its size one element
         *          smaller than the input array.
         */
        private ModifierKey[] removeFromModifierKeyArray(
                int index, ModifierKey[] array)
        {
            if (index < 0 || index > array.length)
                throw new ArrayIndexOutOfBoundsException(
                        "Index (" + index + ") does not map to array (length "
                        + array.length + ").");
            
            if (array.length < 2)
                return new ModifierKey[]{ null };
            
            if (array.length == 2)
            {
                if (index == 0)
                    return new ModifierKey[]{ array[1] };
                else
                    return new ModifierKey[]{ array[0] };
            }
            
            ModifierKey[] newArray = new ModifierKey[array.length - 1];
            
            if (index > 0)
                System.arraycopy(array, 0, newArray, 0, index);
            
            if (index < array.length - 1)
                System.arraycopy(array, index + 1, newArray, index,
                        array.length - (index + 1));
            
            return newArray;
        }
    }

    /**
     * An object belonging to class MouseHandler listens for mouse events
     * on the panel.  (Listening is set up in the constructor for the
     * SimpleTrackMousePanel class.)  When a mouse event occurs, the listener
     * simply calls the setInfo() method in the SimpleMouseTrackPanel class
     * with information about the mouse event that has occurred.
     */
    private class MouseHandler implements MouseListener, MouseMotionListener {

        @Override
        public void mousePressed(MouseEvent evt) {
            setInfo(evt, EventType.PRESSED);
        }

        @Override
        public void mouseReleased(MouseEvent evt) {
            setInfo(evt, EventType.RELEASED);
        }

        @Override
        public void mouseClicked(MouseEvent evt) {
            setInfo(evt, EventType.CLICKED);
        }

        @Override
        public void mouseEntered(MouseEvent evt) {
            setInfo(evt, EventType.ENTERED);
        }

        @Override
        public void mouseExited(MouseEvent evt) {
            setInfo(evt, EventType.EXITED);
        }

        @Override
        public void mouseMoved(MouseEvent evt) {
            setInfo(evt, EventType.MOVED);
        }

        @Override
        public void mouseDragged(MouseEvent evt) {
            setInfo(evt, EventType.DRAGGED);
        }
        
        /**
        * Records information about a mouse event on the panel.  This method is 
        * called by the mouse handler object whenever a mouse event occurs.
        * @param evt the MouseEvent object for the event.
        * @param eventType a description of the type of event, such as "mousePressed".
        */
       private void setInfo(MouseEvent evt, EventType evtType)
       {
           CustomEvent newEvent = new CustomEvent(evtType, evt.getPoint());

           if (evt.isShiftDown())
               newEvent.addKey(ModifierKey.SHIFT);

           if (evt.isControlDown())
               newEvent.addKey(ModifierKey.CTRL);

           if (evt.isMetaDown())
               newEvent.addKey(ModifierKey.META);

           if (evt.isAltDown())
               newEvent.addKey(ModifierKey.ALT);

           switch (evt.getButton())
           {
               case MouseEvent.BUTTON1:
                   newEvent.setButton(MouseButton.LEFT);
                   break;
               case MouseEvent.BUTTON2:
                   newEvent.setButton(MouseButton.MIDDLE);
                   break;
               case MouseEvent.BUTTON3:
                   newEvent.setButton(MouseButton.RIGHT);
                   break;
               default:
                   newEvent.setButton(MouseButton.NONE);
                   break;
           }

           currentEvent = newEvent;

           repaint();
       }
    }
    
/* -------------------------------------------------------------------------- */
    
    // Methods
    /**
     * The paintComponent() method displays information about the most recent
     * mouse event on the panel.
     * This method is not meant to be called by the system after being signaled by another method such as {@code repaint()}, not the user
     * (or programmer) 
     */
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);  // Fills panel with background color.

        /* No mouse event yet, don't show any info. */
        if (currentEvent.getEvent() == EventType.NONE) { return; }

        g.setColor(Color.RED);  // Display information about the mouse event.
        g.setFont(g.getFont().deriveFont(36.0F));
        
        g.drawString("Mouse event type:  "
                + currentEvent.getEvent(), 4*6, 4*18);
        
        StringBuilder sbModifierKeys = new StringBuilder("Modifier keys:  ");
        ModifierKey[] keys = currentEvent.getKeys();
        for (int i=0; i < keys.length; i++)
        {
            sbModifierKeys.append(keys[i]);
            if (i < keys.length - 1)
                sbModifierKeys.append(", ");
        }
        g.drawString(sbModifierKeys.toString(), 4*6, 4*38);
        
        g.drawString("Button used:  " + currentEvent.getButton(), 4*6, 4*58);
        
        g.setColor(Color.BLACK);
        
        int mouseX = (int)currentEvent.getXY().getX();
        int mouseY = (int)currentEvent.getXY().getY();
        g.drawString("(" + mouseX + "," + mouseY + ")", mouseX, mouseY);
    }
}

