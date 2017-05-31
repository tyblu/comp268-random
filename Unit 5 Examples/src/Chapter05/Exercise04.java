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

import java.util.Random;
import TextIO.TextIO;

/**
 *              Textbook Chapter 5 Exercise 4
 * Class:       Exercise04.java
 * Purpose:     Plays one round of blackjack. Deals a random number number of
 *              cards from 2 to 6, tells prints what cards are in the hand and
 *              what they are worth. The user can then play another hand if
 *              they want to continue. Uses the same deck without putting the
 *              cards from the last hand back in until there are less than six
 *              cards left in the deck.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 26, 2017
 * Version     1.0
 * 
 * Based on and References:
 * @see Introduction to Programming Using Java Version 7, by Eck, David J., 
 *      2014: Chapter 5, Exercise 4
 */
public class Exercise04 {
    
    public static void callExercise04()
    {
        System.out.println(intro());
        
        Deck deck = new Deck();
        Random rand = new Random();
        boolean keepPlaying = true;
        
        deck.shuffle();
        
        while (keepPlaying)
        {
            System.out.println(gameIntro());
            
            if (deck.cardsLeft() < 6)
            {
                deck.shuffle();
                System.out.println("Dealer reset the deck. We ran out of cards.");
            }
            
            BlackjackHand hand = new BlackjackHand();
            
            System.out.println("Here is how this terrible hand went down...");
            System.out.println();
            
            int numCards = rand.nextInt(4) + 2;
            for (int i=0; i<numCards; i++)
            {
                hand.addCard(deck.dealCard());
            }
            
            // Print hand
            for (int i=0; i<numCards; i++)
            {
                System.out.println(
                        "Card " + (i+1) + ": " + hand.getCard(i).toString());
            }
            
            // Print hand value.
            int handValue = hand.getBlackjackValue();
            System.out.print(
                    "The hand is worth " + handValue + " points.");
            if (handValue > 21)
                System.out.println(" Bust!");
            else if (handValue > 17)
                System.out.println(" Sweet!");
            else
                System.out.println();
            
            // Keep playing?
            System.out.println();
            System.out.print(deck.cardsLeft() + " cards remaining in deck. ");
            System.out.print("Keep playing? (Y/N) ");
            keepPlaying = TextIO.getlnBoolean();
        }
        
        System.out.println("Thanks for playing!");
    }
    
    /**
     * Provides introductory text to whole program, to print once. No newline
     * at the very end.
     * @return String of introductory text. Less than 80-char wide. No newline.
     */
    private static String intro()
    {
        String s = new String();
        
//        s += 
        
        return s;
    }
    
    /**
     * Provides introductory text to a new game or hand, to print once per game
     * or hand. No newline at the very end.
     * @return String of introductory text. Less than 80-char wide. No newline.
     */
    private static String gameIntro()
    {
        String s = new String();
        
//        s +=
        
        return s;
    }
}