/**
 * Tanker.java
 *
 * Problem 5
 *
 * This extends class is used for creating 
 * tanker ship objects.
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */


public class Tanker extends Ship
{
   // a protected variable is necessary as it must be accessed outside this class
   // but remain unchanged.
   protected double gallons;
      
   //constructor
   public Tanker ( double g, String name, String year, Engine type )
   {
       super( name, year, type);
       this.gallons = g;
   }
 
  /**
    * getGallons
    *
    * This accessor method enables a ship's capacity
    * in gallons to be retrieved outside of this class
    * 
    */
   public double getGallons ()
   {
       return gallons;
   }

  /**
    * setGallons
    *
    * This mutator method enables a ship's gallon
    * capacity to be set outside of this class.
    * 
    */
   public void setGallons ( double g )
   {
      this.gallons = g;
   }

  /**
    * toString
    *
    * This to string method of the tanker ship sub class
    * will print the ship name and gallon capacity.
    * 
    */
      
   public String toString ( )
   {
       return getShipName() + " and can hold " + getGallons() +" gallons.";
   }
}

