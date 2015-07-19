/**
 * ExamAnalysis.java
 *
 * Problem 6
 *
 *
 * This program takes a file with student responses
 * and compares it with a user inputted key. 
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

import java.util.*;
import java.io.*;

class ExamAnalysis {

    // You will notice through this entire program that my main
    // is very small and the printing is done by methods.
    // I know this is poor form and wanted to avoided doing it,
    // but by the time I fell about formatting String builder 
    // I had already designed what you see below. If I wrote this
    // a second time I would have returned formatted strings :(
     

    public static void main ( String [] args ) {

        System.out.println ( "I hope you are ready to begin..." );
        System.out.println ( "Please Type the correct answers to the exam questions, one right after the other: " );
        Scanner in = new Scanner ( System.in );

        //Answer key
        String key = in.nextLine ( );
   
        // Countinously prompt the user for a file.
        Scanner input = null;        
        while ( input == null ){
            System.out.println ( "What is the name of the file containing each student's responses to the 10 questions? " );
            try {
                input = new Scanner ( new File ( in.nextLine ( ) ));
            } catch ( FileNotFoundException e ) {
                  System.out.println ( "You must input a valid file name." );
            }
        }
        
        //Print and store student answers
        ArrayList <String> answers = new ArrayList <String> ();
        studentResponses ( input, answers );

        //Analyze the answers
        ansAnalysis( answers, key );
  
        percentCorrect ( answers, key );
   }
   
   /**
    * studentResponses
    *
    * This program reads the file of student responses
    * and prints and stores their value in a String Array.
    *
    * @param1 Represents the file being read.
    * @param2 Represents the array list where answers will be stored.
    */
    public static void studentResponses ( Scanner input, ArrayList<String> ans ) {
        while ( input.hasNextLine () ) {
            //Loop Variables
            int studentNumber = 0;
            String line = input.nextLine( );
            //If the next string is not empty, print and store it. 
            if ( line.equals( "" ) ) {
                System.out.println ( "We have reached \"end of file!\"" );
            } else {
                ans.add ( line );
                studentNumber ++;
                System.out.println ( "Student #" + studentNumber + " 's responses: " + line );
            }
        }
        System.out.println ( "\nThank you for the student responses." );
    }
   /**
    * ansAnalysis
    *
    * This program determines how many answers 
    *
    * @param1 Represents the file being read.
    * @param2 Represents the array list where answers will be stored.
    */
    public static void ansAnalysis ( ArrayList<String> answers, String key) {
        System.out.printf ("\n%10s %10s %13s %10s\n", "Student #", "Correct", "Incorrect", "Blank" );
        System.out.printf ("\n%10s %10s %13s %10s\n", "~~~~~~~~~", "~~~~~~~", "~~~~~~~~~", "~~~~~" );

        //varibales needing to be tracked.
        int correct = 0; int incorrect =0;  int blank = 0;
        for ( int i = 0; i < answers.size( ); i++ ) {
             String response = answers.get ( i );
             for (int j = 0; j < key.length ( ); j ++ ) {
                 if (response.charAt ( j ) == key.charAt( j ) ) {
                     correct += 1;
                 }else if ( response.charAt ( j ) == ' ' ) {
                       blank += 1;
                 } else {
                       incorrect += 1;
                 }
             }
       System.out.printf ( "\n %4s %12s %12s %12s \n", i + 1, correct, incorrect, blank ); 
       //reset our variables. 
       correct = 0;
       incorrect = 0;
       blank = 0;        
       }

    }

   /**
    * ansAnalysis
    *
    * This method prints the correct answer, how many students
    * selected this answer as well as that percentage. 
    *
    * @param1 Represents the ArrayList of answers
    * @param2 Represents the answer key.
    */
    public static void percentCorrect ( ArrayList<String> answers, String key) {
        
        double size = answers.size ();
        System.out.println ( "\nQUESTION ANALYSIS (* marks the correct response)" );
        System.out.println ( "~~~~~~~~~~~~~~~~~" );
        for ( int i = 0; i < key.length (); i++ ) {
            System.out.println ( "Question #" + i + ":\n");
            System.out.println ( getHeader ( key ) );
 
        //varibales needing to be tracked for each question
        //and reset after counting. 
             int a = 0; int b =0; int c = 0; int d = 0; int e = 0; int blank = 0;
            for ( int j = 0; j < answers.size (); j ++) {
                String response = answers.get ( j );
                switch ( response.charAt( i ) ){
                    case 'A': a ++;
                        break;
                    case 'B': b ++;
                        break;
                    case 'C': c ++;
                        break;
                    case 'D': d ++;
                        break;
                    case 'E': e ++;
                        break;
                    case ' ': blank ++;
                        break;
               }
           }
             // This is awful looking and I searched for a better way. 
             // I mentioned my issue with returning fromatted strings earlier and
             // and I could not get a statement like %4s.1f%% to print so all the
             // padding was aligned. The api and book weren't helpful really. I searched
             // online but nonef my solutions seem to take :(
             System.out.printf("\n %4s %7s %8s %7s %7s %7s", a, b, c, d, e, blank );
             System.out.printf("\n  %.1f%%  ",( a/size ) * 100 );
             System.out.printf("  %.1f%%  ",( b/size ) * 100 ); 
             System.out.printf("  %.1f%%  ",( c/size ) * 100 ); 
             System.out.printf("  %.1f%%  ",( d/size ) * 100 ); 
             System.out.printf("  %.1f%%  ",( e/size ) * 100 );
             System.out.printf("  %.1f%%\n",( blank/size ) * 100 );         
     }
   }
   /**
    * getHeader
    *
    * This method will return the appropriate for the answer
    * when called. 
    *
    * @param1 Represents the answer key
    */
    public static String getHeader ( String key ) {

        // With the way my program turned out there was no need to make this final. 
        String [] HEADERS = { "    A*      B       C       D       E      Blank",
                              "    A       B*      C       D       E      Blank",
                              "    A       B       C*      D       E      Blank",
                              "    A       B       C       D*      E      Blank",
                              "    A       B       C       D       E*     Blank" };
        //blank string for returning.
        String s = "";
        for ( int i = 0; i < key.length( ); i ++ ) {
            switch ( key.charAt ( i ) ) {
                case 'A': s = HEADERS [0];
                    break;
                case 'B': s = HEADERS [1];
                    break;
                case 'C': s = HEADERS [2];
                    break;
                case 'D': s = HEADERS [3];
                    break;
                case 'E': s = HEADERS [4];
                    break;
               }
       }
       return s;
    }
}
