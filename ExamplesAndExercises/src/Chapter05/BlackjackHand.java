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
package Chapter05;

/**
 *
 * @author Tyler Lucas <tyblu@live.com>
 */
public class BlackjackHand extends Hand 
{
    /**
     * @returns The Computed value of this hand in the game of Blackjack.
     */
    public int getBlackjackValue()
    {
        int valHand;        // Computed value of the hand.
        boolean hasAce;     // Does this hand have an Ace?
        int cardCount;      // Number of cards in the hand.
        
        valHand = 0;
        hasAce = false;
        cardCount = getCardCount(); // method in class Hand
        
        for (int i=0; i<cards; i++)
        {
            // Add value of i-th card in the hand.
            Card card;      // the i-th card
            int cardVal;    // Blackjack value of i-th card
        }
    }
    
}
