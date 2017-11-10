package ocean_mine;
import java.sql.*;
/**
 *
 * @author ngraham20
 */
public class Map {
    
    DBAccess database;
    
    Map()
    {
        
    }
    
    // links to the Game's database to work with the same DB connection systemwide
    public void assimilateDatabase(DBAccess database)
    {
        this.database = database;
    }
    
    public void update()
    {
        
    }
    public void print()
    {
        
    }
}
