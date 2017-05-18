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
 *      v1.1    Added member variables suggested in Eck p143-145.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 17, 2017
 * Version     1.1
 * 
 * Based on:   Eck pp 140-143
 * 
 * References:
 * 
 */

public class GuessingGame {
    
    private static int gamesPlayed;
    private static int gamesWon;

    public static void main(String[] args) {
        System.out.println("Let\'s play a game. I'll pick a number between");
        System.out.println("1 and 100, and you try to guess it.");
        
        boolean playAgain;
        
        gamesPlayed = 0;
        gamesWon = 0;
        
        do{
            playGame();
            
            System.out.print("Would you like to play again? (Y/N) ");
            
            playAgain = TextIO.getlnBoolean();
        } while(playAgain);
        
        System.out.println("You played " + gamesPlayed + " games,");
        System.out.println(" and you won " + gamesWon + " of these games.");
        System.out.println("Thanks for playing.  Goodbye.");
    }
    
    static void playGame() {
        gamesPlayed++;
        
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
                
                gamesWon++;
                
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
