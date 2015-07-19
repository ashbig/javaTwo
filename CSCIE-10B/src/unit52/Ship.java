/**
 * Ship.java
 *
 * Problem 5
 *
 * This abstract class is used for creating 
 * ship objects.
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */


public abstract class Ship
{
   //appropriate data fields
   String shipName;
   String shipYear;
   enum Engine { STEAM_ENGINE, STEAM_TURBINE, GAS_TURBINE, DIESEL, ELECTRIC, WIND, HUMAN_FORCE }
   Engine engineType;

   //constructor
   public Ship ( String name, String year, Engine type )
   {
       this.shipName = name;
       this.shipYear = year;
       this.engineType = type; 
   }
 
  /**
    * getShipName
    *
    * This accessor method enables a ship name
    * to be retrieved outside of this class
    * 
    */
   public String getShipName ()
   {
       return shipName;
   }

  /**
    * getShipYear
    *
    * This accessor method enables a ship year
    * to be retrieved outside of this class.
    * 
    */
    public String getShipYear ()
    {
        return shipYear;
    }

  /**
    * getEngineType
    *
    * This mutator method enables an engine type
    * to be retrieved outside of this class.
    * 
    */
    public Engine getEngineType ( )
    {
         return engineType;
    }

  /**
    * setShipName
    *
    * This mutator method enables a ship name
    * to be set outside of this class.
    * 
    */
    public void setShipName ( String name )
    {
       this.shipName = name;
    }
   
  /**
    * setShipYear
    *
    * This mutator method enables a ship year
    * to be set outside of this class.
    * 
    */
    public void setShipYear ( String year )
    {
        this.shipYear = year;
    }

    /**
    * setEngineType
    *
    * This mutator method enables an engine type
    * to be set outside of this class.
    * 
    */
    public void setShipYear ( Engine type )
    {
        this.engineType = type;
    }

  /**
    * toString
    *
    * This to string method of the super class
    * will print the ship name, year and engine type.
    *
    */
   public String toString ( )
   {
       String str =  "The ship name is: " + getShipName() + " and the ship was launched in " + getShipYear() + " with a " + getEngineType() + " engine.";

       return str;
   }
}

       
