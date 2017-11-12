package ocean_mine;

import java.sql.SQLException;

/**
 *
 * @author ngraham20, sholzer20
 */
public class Game {
    // create a database connection for this game
    DBAccess database;
    Map map;
    
    Achievement helpMe;
    Achievement takingInventory;
    Achievement dropTheBass;
    Achievement whereAmI;
    
    Game()
    {
        // initializes the database
        database = new DBAccess();
        
        // initializes the map system
        map = new Map();
        
        // links the map system to the system database
        map.linkDatabase(database);
        
        helpMe = new Achievement("Help Me");
        takingInventory = new Achievement("Taking Inventory");
        dropTheBass = new Achievement("Drop the Bass");
        whereAmI = new Achievement("Where Am I?");
                
    }
    public void menu()
    {
    }
    public void start()
    {
        database.connect();
        
        try
        {
        map.update();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
        map.print(1);
        if (!whereAmI.isUnlocked())
        {
            whereAmI.unlock();
        }
    }

}
