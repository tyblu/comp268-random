/**
 *              Textbook Example Program
 * Class:       GuessingGame.java
 * Purpose:     Plays a guessing game with the user. Computer chooses a number
 *              between 1 and 100, and the user will try to guess it. The
 *              computer tells the user whether the guess is high or low or
 *              correct. If the user gets the number after six guesses or fewer,
 *              the user wins the game. After each game, the user has the option
 *              of continuing with another game.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 17, 2017
 * Version     1.0
 * 
 * Based on:   Eck pp 140-143
 * 
 * References:
 * 
 */

public class GuessingGame {

    public static void main(String[] args) {
        System.out.println("Let\'s play a game. I'll pick a number between");
        System.out.println("1 and 100, and you try to guess it.");
        
        boolean playAgain;
        
        do{
            playGame();
            
            System.out.print("Would you like to play again? (Y/N) ");
            
            playAgain = TextIO.getlnBoolean();
        } while(playAgain);
        
        System.out.println("Thanks for playing.  Goodbye.");
    }
    
    static void playGame() {
        int numberComputer;
        int numberUserGuess;
        int guessCount;
        
        numberComputer = (int)( 100 * Math.random() ) + 1;
        guessCount = 0;
        
        System.out.println();
        System.out.print("What is your first guess? ");
        
        while (true) {
            numberUserGuess = TextIO.getlnInt();
            guessCount++;
            
            if (numberUserGuess == numberComputer) {
                System.out.println("You got it in " + guessCount + " guesses!");
                System.out.println("My number was " + numberComputer + ".");
                break;
            }
            
            if (guessCount == 6) {
                System.out.println("You didn\'t get the number in 6 guesses.");
                System.out.println("You lose. My number was " + numberComputer + ".");
                break;
            }
            
            if (numberUserGuess < numberComputer) {
                System.out.print("That\'s too low. Try again: ");
            }
            else if (numberUserGuess > numberComputer) {
                System.out.print("That\'s too high. Try again: ");
            }
        }
        System.out.println();
    }
}
