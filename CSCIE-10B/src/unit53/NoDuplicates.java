/**
 * NoDuplicates.java
 *
 * Problem 11
 *
 * EXTRA CREDIT
 *
 * This program takes a file with sorted integers and
 * removes duplicates out putting product to a new file.
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

import java.util.*;
import java.io.*;

class NoDuplicates {
    public static void main ( String [] args ) {

        //check for the correct argument length
        if ( args.length != 2 ) {
            System.out.println ( "You must give an input and output file." );
            System.exit(1);
        }
        
        //Set file names as strings
        String input = args [0];
        String output = args [1];
        
        // Make sure the input file can be read.
        // This check sort makes my catches rather useless
        // but it seemed important. 
        File fileIn = new File ( input );
        if(!fileIn.isFile () && ! fileIn.canRead() ) {
            System.out.println("The file cannot be read or does not exist. Goodbye.");
            System.exit(1);
        }
        Scanner in = null;
        try {
            //read the file in, exit if it does not exist. 
            in = new Scanner ( fileIn );
        } catch ( FileNotFoundException e ) {
            System.out.println ( "Unable to locate input file. Goodbye." );
            System.exit(1);
        }

        //Print the integers in a file and store them in an array.
        System.out.println ( "\nORIGINAL FILE: " + args[0] + " contains the values..." );
        ArrayList<Integer> numbers = new ArrayList<Integer> ();
        while ( in.hasNextInt () ) {
            int line = in.nextInt();
            System.out.println ( line );
            numbers.add ( line );
        }

        //goodbye duplicates
        removeDuplicates( numbers, output );

       
        // Read and print the ouput file. 
        try {
            in = new Scanner ( new File ( output ) );
            System.out.println ( "\nOUTPUT FILE: " + output + " contains the values..." );
            while ( in.hasNextInt () ){
               System.out.println ( in.nextInt() );
            }
        } catch ( FileNotFoundException e ) {
            System.out.println ( "Unable to locate your output file. Goodbye." );
            System.exit(1);
        }
    }

   /**
    * removeDuplicates
    *
    * 
    *
    * This method removes duplicates and writes 
    * all non-duplicates to the specified outfile.
    *
    * @param1 Represents the integers in an Arraylist from the input file.
    * @param2 Represents the output file name.
    */
    public static void removeDuplicates ( ArrayList <Integer> a, String output ) {
        // Create a new blank array to store non-duplicated values.
        ArrayList<Integer> list = new ArrayList<Integer> ();
        try {
            // Create the file we are writing to
            PrintStream out = new PrintStream ( output );

            // Wanted to use for each, did not think I was modifying it
            // using a.get ( i ), but compiler complained. Went with for loop. 
            for (int i = 0; i < a.size (); i++ ) {
                // Add the current value to the blank if it is not already present. 
                if ( !list.contains( a.get( i ) ) ) {
                    list.add( a.get( i ) );
                    out.println ( a.get( i ) );
                }
            }
        } catch (FileNotFoundException e ){
            System.out.println( "Could not write to the output file.");
            System.exit(1);
        }
    }
} 
