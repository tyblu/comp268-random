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

/**
 *              Textbook Exercise Program
 * Class:       GUITools.java
 * Purpose:     
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203, Athabasca University
 * Date:       24-Jun-2017
 * Version     1.0
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 2014
 * 
 */

package Chapter07E06;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 *
 * @author Tyler Lucas <tyblu@live.com>
 */
public class GUITools 
{
    // Public static methods.
    public static Dimension getScreenSize()
    {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
    
    public static int getTaskbarHeight()
    {
        return getScreenSize().height - 
                (GraphicsEnvironment
                        .getLocalGraphicsEnvironment()
                        .getMaximumWindowBounds()
                ).height;
    }
    
    public static Point getCenterBetween(Point p1, Point p2)
    {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }
    
    public static double getMinScaleFactorBetween(Rectangle rect1, Rectangle rect2)
    {
        System.out.println("getMinScaleFactorBetween(" + rect1 + ", " + rect2 + ") = " + Math.min(rect1.getWidth() / rect2.getWidth(),rect1.getHeight() / rect2.getHeight()));
        return Math.min(
                    rect1.getWidth() / rect2.getWidth(),
                    rect1.getHeight() / rect2.getHeight()
        );
    }
    
    public static double getMinScaleFactorBetween(Dimension dim1, Dimension dim2)
    {
        return getMinScaleFactorBetween(
                new Rectangle(dim1),
                new Rectangle(dim2)
        );
    }
            
    public static double getMinScaleFactorBetween(Rectangle rect1, Dimension dim2)
    {
        return getMinScaleFactorBetween(rect1.getSize(), new Rectangle(dim2));
    }
            
    public static double getMinScaleFactorBetween(Dimension dim1, Rectangle rect2)
    {
        return getMinScaleFactorBetween(new Rectangle(dim1), rect2);
    }
            
}
