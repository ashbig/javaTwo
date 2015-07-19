/**
 * Palindrome.java
 *
 * Problem 5
 *
 *
 * This program takes a user's input string and determines
 * if it is a palindrome via the isPalindrome method.
 * 
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

import java.util.Scanner;

class Palindrome
{
    public static void main ( String [] args )
    {
    	Scanner in = new Scanner ( System.in );
        System.out.print ( "Palindrome checker: ");
        String input = in.nextLine();
       
        
        if (isPalindrome (input))
        {
           System.out.println("\" " + input + " \" is a palindrome!");
        }
        else
            System.out.println("\" " + input + " \" is not a palindrome.");
    }

   /**
    * isPalindrome
    *
    * This method takes a string paramater and
    * determines if it is a literary palindrome.
    * 
    * @param1 represents the user's inputted string.
    */
    public static boolean isPalindrome ( String a )
    {
        //remove punctuation and case sensitivity
        String s = concatString ( a );

        //base case of is a palindrome
        if ( s.length() <= 1 ) return true;
       
        //check to see if the first character == last 
        if ( s.charAt(0) == s.charAt(s.length() -1) )
        { 
            //if so call the method again with a substring of the
            //first and last characters removed.
            return isPalindrome(s.substring(1, s.length ()- 1 ));
        }
        // if the string length is greater than 1 at this point
        // it cannot be considered a palindrome.
        return false;
    }

   /**
    * concatString
    *
    * This method takes a string paramater and removes
    * punctuation, white space, and case sensitivity.
    * 
    * @param1 represents the string to be concatenated.
    */
    public static String concatString ( String b )
    {
        String t = b.toLowerCase();

        //Create a string that we can append the same length as given user string
        StringBuilder str = new StringBuilder ( t.length() );
             
        //for length of the string all characters are read
        //and only letters are added to the new string.
        for ( int i = 0; i < t.length(); i++)
        {
            char ch = t.charAt(i);
            if ( Character.isLetter (ch) )
            {
               str.append(ch);
            }
        }
       return str.toString();
    }
}
