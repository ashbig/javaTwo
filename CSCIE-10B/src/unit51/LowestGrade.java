/**
 * LowestGrade.java
 *
 * Problem 6
 *
 *
 * This program takes a students grades and removes
 * their lowest score. 
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

import java.util.Arrays;

class LowestGrade
{
    public static void main ( String [] args )
    {
        int [] a = removeLowest ( 23, 90, 47, 55, 88 );
        int [] b = removeLowest ( 85 );
        int [] c = removeLowest ( );
        int [] d = removeLowest ( 59, 92, 93, 47, 88, 47 );
    
        System.out.println("a = " + arrayPrint(a));
        System.out.println("b = " + arrayPrint(b));
        System.out.println("c = " + arrayPrint(c));
        System.out.println("d = " + arrayPrint(d));
    }

   /**
    * removeLowest
    *
    * This method takes a variable number of integer arguements and
    * removes the lowest non duplicate in arguements greater than one.
    * 
    * @param1 represents the grades given.
    */
    public static int [] removeLowest ( int ... grade )
    {
        int [] lowest = new int [ grade.length ];

        // put each integer arguement into an array
        for ( int i = 0; i < grade.length; i++ )
        {
            lowest [i] = grade[i];
        } 
        
        // remove the lowest score if there is more than one arguement
        if ( lowest.length > 1 )
        {
             // sorting will account for any duplicate low values
             // and we can copy every value but the first for output.
             Arrays.sort( lowest );
             lowest = Arrays.copyOfRange ( lowest, 1, lowest.length );
        }
        return lowest;
    }

   /**
    * arrayPrint
    *
    * This method takes an array arguement
    * and adds them to a returned string.
    *
    * @param1 represents the grades given.
    */
    public static String arrayPrint ( int [] array )
    {
        //StringBuilder would have been nice, but I did it this way
        //to avoid using any .toString methods
        

        // printing the brackets before and after accounts for our no input case
        String s = "[" ;
        for (int i = 0; i < array.length; i++)
        {
            s += array[i];
    
            //nested if for correct comma placement
            if ( i != array.length -1)
            {
                  s += ", ";
            }
        }
        s += "]";
        return s;
    }
}
