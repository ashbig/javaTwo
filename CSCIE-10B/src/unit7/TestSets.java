/**
*  TestSets.java
*
*  @version: Last Modified April 4, 2012
*  @author:  Henry Leitner
*/

import java.util.*;

public class TestSets
{
  static void menu()
  {
     System.out.println ();
     System.out.print ("Type 1 to CREATE SET A\n");
     System.out.print ("Type 2 to CREATE SET B\n");
     System.out.print ("Type 3 to CREATE INTERSECTION (A * B)\n");
     System.out.print ("Type 4 to CREATE UNION (A + B)\n");
     System.out.print ("Type 5 to CREATE DIFFERENCE (A - B)\n");
     System.out.print ("Type 6 to SHOW THE CARDINALITY OF SETS A and B\n");
     System.out.print ("Type 7 to SHOW ANY SUBSETS\n");
     System.out.print ("Type any OTHER # to EXIT PROGRAM \n\n");
     System.out.print ("Command: ");
  }
  
  public static void main (String [] args) 
  {
     Bitset setA = new Bitset (16);
     Bitset setB = new Bitset (8);
     int command;
    
     Scanner keyboard = new Scanner (System.in);
     do 
     {
         menu();
         
         switch (command = keyboard.nextInt ()) 
         {
            case 1:
              System.out.println ("Type some small integers, each < 16" 
                                 + ", and type DONE when all done!");
              setA.readSet(keyboard);
              System.out.print ("     SET A = " + setA);
              break;

            case 2:
              System.out.println ("Type some small integers, each < 8"
                                 + ", and type DONE when all done!");
              setB.readSet(keyboard);
              System.out.print ("     SET B = " + setB);
              break;

           case 3:
              System.out.print ("     Intersection (A * B) = ");
              System.out.print (setA.intersect(setB));
              break;
           
	       case 4:
              System.out.print ("     Union (A + B) = ");
              System.out.print (setA.union(setB));
              break;

           case 5:
              System.out.print ("     Difference (A - B) = ");
              System.out.print (setA.difference(setB));
              break;

           // Case 6 and 7 have been added by Ashkan Bigdeli ( ashbigdeli@ gmail.com );
           case 6:
              System.out.print ("     The cardinality of set A is " + setA.cardinality() + "\n" );
              System.out.print ("     The cardinality of set B is " + setB.cardinality());
              break;

           case 7:
              System.out.print ("     A a subset of B: " + setA.subset ( setB ) +  "\n" );
              System.out.print ("     B a subset of A: " + setB.subset ( setA ) );
              break;


           default:  System.exit(0);
        
         }
       } while (command > 0 && command < 8);
  }
}
