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
        map = new Map();
        map.linkDatabase(database);
                
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
        
        map.print();
    }
}
