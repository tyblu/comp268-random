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
 * Class:       RandomPlus.java
 * Purpose:     
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203, Athabasca University
 * Date:       25-Jun-2017
 * Version     1.0
 * 
 * Based on and References:
 * 
 */

package Chapter07E06;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Random;

public class RandomPlus extends Random
{
    /**
     * Returns {@link Rectangle} with random dimensions within the input
     * Rectangle. Returned Rectangle has a minimum height and width of 1, and
     * lies on or inside the input Rectangle bounds, including on the border.
     * 
     * @param bounds Rectangle of minimum width and height of 1, defining the
     * inclusive maximum bounds of the desired random Rectangle.
     * 
     * @return Rectangle with minimum height and width of 1, defined at a
     * random location and of random dimensions within the input Rectangle,
     * while being completely contained within the input Rectangle.
     */
    public Rectangle nextRectWithin(Rectangle bounds)
    {
        int rectW = 1 + nextInt(bounds.width - 1);
        int rectH = 1 + nextInt(bounds.height - 1);
        int rectX = nextInt(bounds.width - rectW);
        int rectY = nextInt(bounds.height - rectH);
        return new Rectangle(rectX, rectY, rectW, rectH);
    }
    
    /**
     * Returns {@link Rectangle} with random dimensions within the input
     * {@link Dimension}. Returned Rectangle has a minimum height and width of
     * 1, and lies on or inside the input Rectangle bounds, including on the
     * border.
     * 
     * @param bounds Dimension of minimum width and height of 1, defining the
     * inclusive maximum bounds of the desired random Rectangle.
     * 
     * @return Rectangle at location (0,0) with minimum height and width of 1,
     * maximum equal to input Dimension height and width.
     */
    public Rectangle nextRectWithin(Dimension bounds)
    {
        return nextRectWithin(new Rectangle(0, 0, bounds.width, bounds.height));
    }
    
    public Point nextPointWithin(Rectangle bounds)
    {
        return new Point(
                bounds.x + nextInt(bounds.width),
                bounds.y + nextInt(bounds.height)
        );
    }
    
    public Point nextPointWithin(Dimension bounds)
    {
        return nextPointWithin(new Rectangle(0, 0, bounds.width, bounds.height));
    }
    
    public Color nextColor()
    {
        return Color.getHSBColor(nextFloat(), 1.0f, 1.0f);
    }
}
