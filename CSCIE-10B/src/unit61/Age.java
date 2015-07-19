 /*
 * Age.java
 *
 * PROBLEM 1
 *
 * This program asks the users age through a dialog box.
 * The program determines if the input can be considered young or old. 
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

import javax.swing.*;

class Age {
    public static void main ( String [] args ) {

        // Create dialog box
        String input = JOptionPane.showInputDialog ( null, "What's your age, cowboy?" );
        int age;
        try {
            // store the integer input
            age = Integer.parseInt ( input );

            // test age
            if ( age < 40 ) {
               JOptionPane.showMessageDialog ( null, "Welcome youngin'!" );
            } 
            else {
               JOptionPane.showMessageDialog ( null, "Sorry, you're not fit to ride." );
            }
        } 
        catch ( NumberFormatException e ) {
            JOptionPane.showMessageDialog ( null, "That's not a number partner.", "Goodbye.", JOptionPane.ERROR_MESSAGE );
        }
    }
}
              
                   
