/**
 * Rabbits.java
 *
 * Problem 7
 *
 *
 * This program uses Fibonacci's sequence to determine
 * mature rabbit pairs after 1 year of breeding. 
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */


class Rabbits
{
    public static void main ( String [] args )
    {
      for ( int i = 1; i <= 12; i ++)
      {
          System.out.println("At the end of month #" + i + ", there are " + fibo(i) + " mature rabbits pairs." );
      }
    }

   /**
    * fibo
    *
    * This method takes an integer argument and reursively 
    * caclulates the number of expected mature rabbit pairs.
    * 
    * @param1 represents the month given.
    */
    public static int fibo ( int a )
    {
        //base case of 0 mature rabbits pairs
        if ( a <= 1 )
        {
            return 0;
        }
        //this will stop the recursion at 1 mature rabbit pair
        else if ( a == 2)
        {
            return 1;
        }
        //else there is more than 1 mature rabbit pair and the provided
        //recursive formula is needed.
        else return fibo( a - 1 ) + fibo ( a - 2 );
        
    }
}
