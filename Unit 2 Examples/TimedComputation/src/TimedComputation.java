/**
 *             Textbook Example Program
 * Class:      TimedComputation.java
 * Purpose:    Performs some mathematical computations and displays the results.
 *             Also displays the value of the constant Math.PI. It then reports
 *             the number of seconds that the computer spent on this task.
 *
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       April 1, 2017
 * Version     1.0
 * 
 * Based on:   Eck, pp 32-33
 */
public class TimedComputation {
    public static void main(String[] args) {
        long startTime;     // Starting time of program [ms]
        long endTime;       // Time when computations are done [ms]
        double time;        // Time difference [s]
        
        startTime = System.currentTimeMillis();
        
        double width, height, hypotenuse;
        width = 42.0;
        height = 17.0;
        hypotenuse = Math.sqrt( width*width + height*height );
        System.out.print("A triangle with sides 42 and 17 has a hypotenuse ");
        System.out.println(hypotenuse);
        
        System.out.println("\nMathematically, sin(x)*sin(x) + "
                + "cos(x)*cos(x) - 1 should be 0.");
        System.out.println("Let's check for x = 1:");
        System.out.print("\tsin(1)*sin(1) + cos(1)*cos(1) - 1 is ");
        System.out.println( Math.sin(1)*Math.sin(1)
                + Math.cos(1)*Math.cos(1) -1 );
        System.out.println("(There can be round-off errors when"
                + " computing with real numbers!)");
        
        System.out.print("\nHere is a random number: ");
        System.out.println( Math.random() ); // Aren't we supposed to do something special with random numbers, according to the style guide?
        
        System.out.print("The value of Math.PI is ");
        System.out.println( Math.PI );
        
        endTime = System.currentTimeMillis();
        time = ( endTime - startTime ) / 1000.0;
        
        System.out.print("\nRun time in seconds was: ");
        System.out.println(time);
    }
}
