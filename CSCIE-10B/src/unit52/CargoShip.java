/**
 * CargoShip.java
 *
 * Problem 5
 *
 * This extends class is used for creating 
 * cargo ship objects.
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */


public class CargoShip extends Ship
{
   // a protected variable is necessary as it must be accessed outside this class
   // but remain unchanged.
   protected double capacity;
      
   //constructor
   public CargoShip ( double c, String name, String year, Engine type )
   {
       super( name, year, type );
       this.capacity = c;
   }
 
  /**
    * getCapacity
    *
    * This accessor method enables a ship's capacity
    * to be retrieved outside of this class
    * 
    */
   public double getCapacity ()
   {
       return capacity;
   }

  /**
    * setCapacity
    *
    * This mutator method enables a ship capacity
    * to be set outside of this class.
    * 
    */
   public void setCapacity ( double a )
   {
      this.capacity = a;
   }

  /**
    * toString
    *
    * This to string method of the cargo ship sub class
    * will print the ship name and capacity.
    * 
    */
      
   public String toString ( )
   {
       return getShipName() + " and has a capcity of " + getCapacity() +" tonnage.";
   }
}

       
