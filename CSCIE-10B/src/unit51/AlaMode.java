/**
 * AlaMode.java
 *
 * Problem 6
 *
 *
 * This program takes an array and returns the mode.
 * Two modes and no elements are unaccounted for.
 * No mode displays as "0".
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

import java.util.Arrays;

class AlaMode
{
    public static void main ( String [] args )
    {
        //basic mode case
        int [] a  = { 1, 7, 7, 8, 9 };
        //negative not affecting mode
        int [] b = { 1, -7, 7, 7, 8};
        //single element
        int [] c = { 3 };
        //no mode will return zero
        int [] d = { 1, 2, 3, 4, 5, 6, 7 };
        //multiple repeating elements
        int [] e = { 1, 1, 1, 1, 2, 2, 2, 3, 3 };
             
  
            
        System.out.println("The mode of " + Arrays.toString(a) + " is " + mode (a)  + "!");
        System.out.println("The mode of " + Arrays.toString(b) + " is " + mode (b)  + "!");
        System.out.println("The mode of " + Arrays.toString(c) + " is " + mode (c)  + "!");
        System.out.println("The mode of " + Arrays.toString(d) + " is " + mode (d)  + "!");
        System.out.println("The mode of " + Arrays.toString(e) + " is " + mode (e)  + "!");

    }

   /**
    * mode
    *
    * This method takes an array arguement
    * and finds the mode by returning the
    * number found most frequently. 
    *
    * @param1 represents the given array.
    */
    public static int mode ( int [] array )
    {
        //if the array has a length of 1, just return the zero index
        if (array.length == 1 )
        {
            return array[0];
        }
        
        int count_final = 0;
        int mode = 0;
        int mode_final =0;
        
        
        for (int i = 0; i < array.length; i++)
        {
            //grab the first element and using "int j = i + 1;"
            //compare it to the next element and update counter.
            mode = array[i];
            int count = 0;
            for (int j = i + 1; j < array.length; j++)
            {
               if ( mode == array[j] )
               {
                    count++;
               }                   
            }
            //if we've seen this element the most in the area
            //place it an mode_final and update the occurences. 
            if ( count > count_final)
            {
                mode_final = mode;
                count_final = count;
            }
         }
         return mode_final;
    } 
}
