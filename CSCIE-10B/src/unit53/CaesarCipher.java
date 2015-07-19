 /*
 * CaesarCipher.java
 *
 * PROBLEM 7
 *
 * This program takes a file and can cipher
 * or decipher the text depending on user input. 
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

import java.util.*;
import java.io.*;

class CaesarCipher {

    // I apologize as running short on time I could not implement either of your suggestions per our email. 
    // I understand fully why your approach is more refined, but after two failed attemps
    // I had to move on. I do understand the concept of integers as the charcters
    // values and if I wrote this again that would be the approach. 
    private static final char [] alpha = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K' , 'L' , 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main ( String [] args ) {
       
        Scanner in = new Scanner (System.in );
        int action = 0;
        int shift;
        String input, output;
        
        // I tried to implement two seperate try catches as you suggested, but my variables
        // are local within my first try so I did have to group it. 
        while ( action != -1 ) {
           try {
                System.out.println ( "Enter 1 to encipher, or 2 to decipher (-1 to exit): " );
                action = in.nextInt();
                // Check first if the user would like to exit before recieveing more input.
                if ( action == -1 ) {
                    System.out.println ("Thank you for using CaesarCipher. Goodbye." );
                    System.exit (1);
                }
                System.out.println ( "What shift should I use?" );
                shift = in.nextInt();
                System.out.println ( "What is the input file name?" );
                input = in.next();
                System.out.println ( "What is the output file name?" );
                output = in.next();
                
                PrintStream out = new PrintStream ( output );
                if ( action == 1 ) {
                    out.println ( caesarEncipher ( input, shift ) );
                    System.out.println ("Done!\n");
                } else if ( action == 2 ){
                    out.println ( caesarDecipher ( input, shift ) );
                    System.out.println ("Done!\n");
                // this will catch any integers that are not -1, 1 or 2. 
                } else {
                  System.out.println ( "Please use the specified inputs. Goodbye" );
                }            
            // This will catch any incorrect input by the user such as chars.   
           } catch ( InputMismatchException e ) {
                System.out.println( "I'm sorry please use the specified inputs. Goodbye." );
                System.exit(1);
           
            // This will catch not being able to create the output file. 
           } catch ( FileNotFoundException e ) {
                System.out.println ( "I'm sorry I could not create that file try again. Goodbye." );
                System.exit(1);
           }
        }
    }

   /**
    * caesarEncipher
    *
    * This method will shift characters and return
    * a string containg the cipher. 
    *
    * @param1 Represents the file name to be read. 
    * @param2 Represents the character shift to be performed.
    */
    public static String caesarEncipher ( String input, int shift ){
        StringBuilder str = new StringBuilder ();
        try{
            Scanner inFile = new Scanner ( new File ( input) );
            
            while ( inFile.hasNextLine() ) {
                String message = inFile.nextLine();
                for ( int i = 0; i < message.length(); i ++ ){
                   
                   // Using this tracker will tell us if we need to print
                   // a non uppercase letter.  
                   int tracker = 1;
          
                   // compare every character in our string to our final uppercase
                   // array. If there is a match use encipher alogarthim to add
                   // appropriate index to our string. 
                   for ( int j = 0; j < alpha.length; j ++){
                       if ( message.charAt (i) == alpha [j] ){

                          // If we set A-Z as 0-26 as we have in our final array
                          // adding the shift and getting the remainder of / 26
                          // will append the string with the appropriate ciphered letter. 
                          str.append (alpha [ ( j + shift ) % 26 ]);
                          tracker = 0;
                       }
                   }if ( tracker == 1 ){
                       str.append (message.charAt(i));
                   }
                }
            }
        }catch ( FileNotFoundException e ){
            System.out.println ("I'm sorry I could not locate your input file.");
        }return str.toString();     
    }

   /**
    * caesarEncipher
    *
    * This method will use a shift key to decipher
    * text. 
    *
    * @param1 Represents the name of the file.  
    * @param2 Represents the character shift to be undone.
    */
    public static String caesarDecipher ( String input, int shift ){
        StringBuilder str = new StringBuilder ();
        try{
            Scanner inFile = new Scanner ( new File ( input) );
            
            while ( inFile.hasNextLine() ){
                String message = inFile.nextLine();
                for ( int i = 0; i < message.length(); i ++ ){ 
                   int tracker = 1;
                   for ( int j = 0; j < alpha.length; j ++){
                       if ( message.charAt (i) == alpha [j] ){

                          // Same principle as enciper only we subract the shift. 
                          str.append (alpha [ ( j - shift ) % 26 ]);
                          tracker = 0;
                       }
                   }if ( tracker == 1 ){
                       str.append (message.charAt(i));
                   }
                }
            }
        }catch ( FileNotFoundException e ){
            System.out.println ("I'm sorry I could not locate your input file.");
        }return str.toString();     
    }
}

