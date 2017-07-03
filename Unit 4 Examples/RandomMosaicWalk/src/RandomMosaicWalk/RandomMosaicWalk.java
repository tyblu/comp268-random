package RandomMosaicWalk;

/**
 *              Textbook Example Program
 * Class:       RandomMosaicWalk.java
 * Purpose:     This program opens a window full of randomly coloured squares.
 *              A “disturbance” moves randomly around in the window, randomly 
 *              changing the colour of each square that it visits. The program 
 *              runs until the user closes the window.
 * 
 *      v1.1    With named constants, as per Eck pp178-180.
 * 
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       May 19, 2017
 * Version     1.1
 * 
 * Based on:   Eck pp 168-175
 * 
 * References:
 * 
 */

public class RandomMosaicWalk {
    
    final static int ROWS = 20;
    final static int COLUMNS = 30;
    final static int SQUARE_SIZE = 15;

    static int currentRow;
    static int currentColumn;
    
    /**
     * The main program creates the window, fills it with random colours, and
     * then moves the disturbance in a random walk around the window as long as
     * the window is open.
     * @param args
     */
    public static void main(String[] args) {
        
        Mosaic.open(ROWS, COLUMNS, SQUARE_SIZE, SQUARE_SIZE);
        fillWithRandomColors();
        
        currentRow = ROWS / 2;
        currentColumn = COLUMNS / 2;
        
        while (Mosaic.isOpen()) {
            changeToRandomColor(currentRow, currentColumn);
            randomMove();
            Mosaic.delay(1);
        }
    }
    
    /**
     * Fills the window with randomly coloured squares.
     * Precondition:    The mosaic window is open.
     * Postcondition:   Each square has been set to a random colour.
     */
    static void fillWithRandomColors() {
        for ( int row=0; row<ROWS; row++ ){
            for ( int col=0; col<COLUMNS; col++ ) {
                changeToRandomColor(row,col);
            }
        }
    }
    
    /**
     * Changes one square to a new randomly selected colour.
     * Precondition:    The specified rowNum and colNum are in the valid range
     *                  of row and column numbers.
     * Postcondition:   The square in the specified row and column has been set
     *                  to a random colour.
     * @param   rowNum      the row number of the square, counting rows down
     *                      from 0 at the top
     * @param   colNum      the column number of the square, counting columns
     *                      over from 0 at the left
     */
    static void changeToRandomColor( int rowNum, int colNum ) {
        int red = (int)( 256 * Math.random() );
        int green = (int)( 256 * Math.random() );
        int blue = (int)( 256 * Math.random() );
        
        Mosaic.setColor(rowNum, colNum, red, green, blue);
    }
    
    /**
     * Move the disturbance.
     * Precondition:    The globals currentRow and currentColumn are within the
     *                  legal range of row and column numbers.
     * Postcondition:   currentRow and currentColumn is changed to one of the
     *                  neighbouring positions in the grid -- up, down, left, or
     *                  right -- from the current position. If this moves the
     *                  position outside of the grid, then it is moved to the 
     *                  opposite edge of the grid.
     */
    static void randomMove() {
        int directionNum;
        directionNum = (int)( 4 * Math.random() );
        
        switch (directionNum) {
            case 0: // move up
                currentRow--;
                if (currentRow<0) { currentRow = ROWS - 1; }
                break;
            case 1: // move right
                currentColumn++;
                if (currentColumn >=20) { currentColumn = 0; }
                break;
            case 2: // move down
                currentRow++;
                if (currentRow >= 16) { currentRow = 0; }
                break;
            case 3: // move left
                currentColumn--;
                if (currentColumn < 0) { currentColumn = COLUMNS - 1; }
                break;
        }
    }
}
