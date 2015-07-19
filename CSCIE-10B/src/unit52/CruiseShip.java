/**
 * Cruise Ship.java
 *
 * Problem 5
 *
 * This extends class is used for creating 
 * cruise ship objects.
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

public class CruiseShip extends Ship
{
   // a protected variable is necessary as it must be accessed outside this class
   // but remain unchanged.
   protected int passengers;
   Engine type;
   //constructor
   public CruiseShip ( int p, String name, String year, Engine type )
   {
       super( name, year, type);
       this.passengers = p;
       
   }
 
  /**
    * getPassengers
    *
    * This accessor method enables a ship's passengers
    * to be retrieved outside of this class
    * 
    */
   public int getPassengers ()
   {
       return passengers;
   }

  /**
    * setPassengers
    *
    * This mutator method enables a ship passengers
    * to be set outside of this class.
    * 
    */
   public void setPassengers ( int a )
   {
      this.passengers = a;
   }

  /**
    * toString
    *
    * This to string method of the cruise ship sub class
    * will print the ship name and passengers.
    * 
    */
      
   public String toString ( )
   {
       return getShipName() + " can hold " + getPassengers() +" passengers.";
   }
}

       
