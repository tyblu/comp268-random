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
 * A SimpleTrackMousePanel is a panel that displays information about mouse
 * events on the panel, including the type of event, the position of the mouse,
 * a list of modifier keys that were down when the event occurred, and an 
 * indication of which mouse button was involved, if any.
 * 
 * Edited to increase window and font sizes, and changed main() to call() for
 * compatibility with MainCaller.
 */
public class SimpleTrackMouse extends JPanel
{
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
    
    /**
     * Holds current (or last) mouse event details.
     */
    private CustomEvent currentEvent = new CustomEvent();
    
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
        private EventType event = EventType.NONE;
        private ModifierKey[] keys;
        private MouseButton button = MouseButton.NONE;
        private Point xy;
       
        public CustomEvent(EventType event, ModifierKey[] keys,
                MouseButton button, Point xy)
        {
            this.event = event;
            this.keys = keys;
            this.button = button;
            this.xy = xy;
        }
        
        public CustomEvent(EventType event, ModifierKey key, 
                MouseButton button, Point xy)
        {
            this(event, new ModifierKey[]{key}, button, xy);
        }

        public CustomEvent(EventType event, Point xy)
        {
            this(event, ModifierKey.NONE, xy);
        }
        
        public CustomEvent(ModifierKey key, Point xy)
        {
            this(EventType.NONE, key, xy);
        }
        
        public CustomEvent(MouseButton button, Point xy)
        {
            this(EventType.NONE, button, xy);
        }
        
        public CustomEvent(EventType event, ModifierKey key, Point xy)
        {
            this(event, key, MouseButton.NONE, xy);
        }
        
        public CustomEvent(EventType event, MouseButton button, Point xy)
        {
            this(event, ModifierKey.NONE, button, xy);
        }
        
        public CustomEvent(ModifierKey key, MouseButton button, Point xy)
        {
            this(EventType.NONE, key, button, xy);
        }
        
        public CustomEvent(ModifierKey[] keys, Point xy)
        {
            this(keys, MouseButton.NONE, xy);
        }
        
        public CustomEvent(ModifierKey[] keys, MouseButton button, Point xy)
        {
            this(EventType.NONE, keys, button, xy);
        }
        
        public CustomEvent()
        {
            this(EventType.NONE, new Point(0,0));
        }

        // Getters
        public EventType getEvent()
        {
            return this.event;
        }

        public ModifierKey[] getKeys()
        {
            return this.keys;
        }

        public MouseButton getButton()
        {
            return this.button;
        }
        
        public Point getXY()
        {
            return this.xy;
        }
        
        // Setters
        public void setEvent(EventType event)
        {
            this.event = event;
        }
        
        public void setKeys(ModifierKey[] keys)
        {
            this.keys = keys;
        }
        
        public void setKeys(ModifierKey key)
        {
            setKeys( new ModifierKey[]{ key } );
        }
        
        public void setXY(Point xy)
        {
            this.xy = xy;
        }
        
        public void setButton(MouseButton button)
        {
            this.button = button;
        }
        
        // Methods
        public void addKey(ModifierKey key)
        {
            if (getKeys().length < 1)
                setKeys(key);
            
            setKeys( addToModifierKeyArray(key, getKeys()) );
        }
        
        private ModifierKey[] addToModifierKeyArray(
                ModifierKey newElement, ModifierKey[] array)
        {
            ModifierKey[] newArray = new ModifierKey[array.length + 1];
            for (int i=0; i<array.length; i++)
            {
                newArray[i] = array[i];
            }
            newArray[newArray.length - 1] = newElement;
            
            return newArray;
        }
    }
    
    /**
     * The paintComponent() method displays information about the most recent
     * mouse event on the panel (as set by the setInfo() method).
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
}

