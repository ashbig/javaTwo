/**
 * ShipTest.java
 *
 * Problem 5
 *
 * This abstract class well test our abstract
 * ship class as well as our sub class.
 *
 * @author Ashkan Bigdeli
 * @email ashbigdeli@gmail.com
 */

public class ShipTest
{
    public static void main ( String [] args )
    {
        // Create our objects array 
        Ship [] vessels = new Ship [6];
        vessels [0] = new CruiseShip ( 20000, "Cruise1", "1984", Ship.Engine.HUMAN_FORCE );
        vessels [1] = new CruiseShip ( 2207, "Titantic", "1911", Ship.Engine.STEAM_ENGINE );
        vessels [2] = new CargoShip ( 500.25, "Cargo1", "1985", Ship.Engine.STEAM_TURBINE );
        vessels [3] = new CargoShip ( 250.32, "Cargo2", "2014", Ship.Engine.GAS_TURBINE );
        vessels [4] = new Tanker ( 2000.12, "Tanker1", "2000", Ship.Engine.WIND );
        vessels [5] = new Tanker ( 60000.34, "Tanker2", "1999", Ship.Engine.DIESEL );

       // Use a for each to print each entry in our array.
       for (Ship entry : vessels)
       {
           System.out.println(entry.toString());
       }
    }
}

        
