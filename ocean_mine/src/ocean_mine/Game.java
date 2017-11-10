package ocean_mine;

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
        map.assimilateDatabase(database);
    }
    public void menu()
    {
    }
    public void start()
    {
        database.connect();
    }
}
