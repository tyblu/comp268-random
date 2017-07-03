package CreateProfile;

/**
 *             Textbook Example Program
 * Class:      CreateProfile.java
 * Purpose:    Computes interest earned on an investment over time. Initial 
 *             amount and interest rate input by the user. This improves on the
 *             book's example in the same way as Interest.java v1.1.
 *
 * @author:    Tyler Lucas
 * Student ID: 3305203
 * Date:       April 10, 2017
 * Version     1.1
 * 
 * Based on:   Eck, pp 43-44
 */

public class CreateProfile {
    public static void main(String[] args) {
        
        String name;        // user's name
        String email;       // user's email address
        double salary;      // user's yearly salary
        String favColour;   // user's favourite colour
        
        TextIO.putln("Good afternoon! This program will create");
        TextIO.putln("your profile file, if you will just answer");
        TextIO.putln("a few simple questions.");
        TextIO.putln();
        
        /* Gather responses from the user. */
        TextIO.put("What is your name?             ");
        name = TextIO.getln();
        TextIO.put("What is your email address?    ");
        email = TextIO.getln();
        TextIO.put("What is your yearly income?    ");
        salary = TextIO.getlnDouble();
        TextIO.put("What is your favourite colour? ");
        favColour = TextIO.getln();
                
        /* Have user select file. */
        boolean didSelectFile;
        didSelectFile = TextIO.writeUserSelectedFile();
        if ( !didSelectFile ) {
            TextIO.writeStandardOutput();
            TextIO.putln("No file selected. Exiting...");
            System.exit(1);
        }
        
        /* Write the user's information to the file named profile.txt. */
        TextIO.putln("Name:              " + name);
        TextIO.putln("Email:             " + email);
        TextIO.putln("Favorite Colour:   " + favColour);
        TextIO.putf( "Yearly Income:     %,1.2f%n", salary);
        
        /* Print a final message to standard output. */
        TextIO.writeStandardOutput();
        TextIO.putln("Thank you. Your profile has been written to file.");
    }
}
