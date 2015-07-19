/**
 * RecursiveSum.java
 *
 * Problem 10
 *
 *
 * This program uses will calculate the sum
 * of each element in a given array
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

import java.util.Arrays;

class RecursiveSum
{
    public static void main ( String [] args )
    {
       //standard input
       int [] a = { 1, 2, 3, 5, 6, 7 };
       System.out.println( "The sum of " + Arrays.toString (a) + " is " + rSum(a,a.length-1) + ".");
     
       //case of 1 element
       int [] b = { 1 };
       System.out.println( "The sum of " + Arrays.toString (b) + " is " + rSum(b,b.length-1) + ".");
      
       //case of negative values
       int [] c = { 10, -100, 10, -10 };
       System.out.println( "The sum of " + Arrays.toString (c) + " is " + rSum(c,c.length-1) + ".");

       //case of zero values
       int [] d = { 0, 0, 0, 0 };
       System.out.println( "The sum of " + Arrays.toString (d) + " is " + rSum(d,d.length-1) + ".");
    }

   /**
    * rSum
    *
    * This method takes an array argument and recursively 
    * cacluculates the sum of each element.
    * 
    * @param1 represents the array given.
    * @param2 represents the size of the given array.
    */
    public static int rSum ( int [] array, int size )
    {
       // base case
       if ( size < 1 )
       {
           return array[size];
       }
       // if size is greater than zero add the last element for summing
       // and call the method again with this element removed. 
       else return array[size] + rSum(array, size - 1 );
    }
}
