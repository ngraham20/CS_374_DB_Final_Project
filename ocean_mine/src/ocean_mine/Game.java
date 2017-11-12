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
    
    
    Game()
    {
        // initializes the database
        database = new DBAccess();
        
        // initializes the map system
        map = new Map();
        
        // links the map system to the system database
        map.linkDatabase(database);
        
        // creates the achievement list for the game
        createAchievementList();
                
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
    }
    
    private void createAchievementList()
    {
        Achievement helpMe = new Achievement("Help Me");
        Achievement takingInventory = new Achievement("Taking Inventory");
        Achievement dropTheBass = new Achievement("Drop the Bass");
    }
}
