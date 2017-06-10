package TybluMinesweeper;

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
 * Starts a Game of minesweeper, keeping track of time spent playing, games
 * won/lost/played, player identity, automatically saves statistics, and allows
 * you to play again if a game ends.
 * 
 * @author:     Tyler Lucas
 * Student ID:  3305203
 * Date:        June 9, 2017
 * Version      0.1
 * 
 * Based on and References:
 * 
 */
public class Session
{
    // Constants.
    
    // Instance variables.
    private int gamesCount = 0;
    private int gamesWonCount = 0;
    
    // Constructor.
    public Session()
    {
        if (gamesCount == 0)
            System.out.println(getInitialGreeting());
    }
    
    // Getters.
    
    // Setters.
    
    // Methods.
    private static String getInitialGreeting()
    {
        StringBuilder sb = new StringBuilder();
        
        sb.append("Hello, welcome to Tyler\'s version of Minesweeper!");
        sb.append("\nLet\'s play a game!");
        sb.append("\nEnter \"Y\" for \"yes\" or \"N\" for \"no\": ");
        
        return sb.toString();
    }
}
