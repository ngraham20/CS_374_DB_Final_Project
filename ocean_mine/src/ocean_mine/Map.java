package ocean_mine;
import java.sql.*;
/**
 *
 * @author ngraham20
 */
public class Map {
    
    ResultSet rs;
    DBAccess database;
    
    String map[][][][];
    
    Map()
    {
        //  {Z Y R X : data is stored at X }
        map  = new String[5][10][10][3];
    }
    
    public void linkDatabase(DBAccess database)
    {
        this.database = database;
    }
    
    // will rebuild the entire map array from scratch
    public void update() throws SQLException
    {
        // send query to database to ask for a list of all discovered rooms
        database.queryMapData();
        
        // redraw map listings to account for the new information
        rs = database.getQueryResults();
        
        int id;
        int z;
        int y;
        int x;
        boolean n;
        boolean s;
        boolean e;
        boolean w;
        
        while (rs.next())
        {
            id = rs.getInt("id");
            n = rs.getBoolean("north");
            s = rs.getBoolean("south");
            e = rs.getBoolean("east");
            w = rs.getBoolean("west");
            
            if (id > 0) // if there's at least one digit
                x = id%10;
            else
                x = 0;
            if (id >= 10) // if there's at least two digits
                y = (id/10)%10;
            else
                y = 0;
            if (id >= 100) // if there's at least three digits
                z = (id/100)%10;
            else z = 0;
            
            if (n)
            {
                map[z][y][x][0] = "--|--";
            }
            else 
                map[z][y][x][0] = "-----";
            if (w)
            {
                map[z][y][x][1] = "-   ";
            }
            else
                map[z][y][x][1] = "|   ";
            if (e)
            {
                map[z][y][x][1] += "-";
            }
            else
                map[z][y][x][1] += "|";
            if (s)
            {
                map[z][y][x][2] = "--|--";
            }
            else
                map[z][y][x][2] = "-----";
        }
    }
    
    public void print()
    {
        // loops through the map system, printing it out
        for (int z = 0; z < 5; z++) // for each floor
        {
            for (int y = 0; y < 10; y++) // for each column y
                
            {
                for (int row = 0; row < 3; row++) // for every subrow
                {
                    for (int x = 0; x < 10; x++) // for every datablock
                    {
                        if(map[z][y][x][row] != null)
                            System.out.print(map[z][y][x][row]);
                        else
                            System.out.print("     ");
                        System.out.print(" ");
                    }
                    System.out.print("\n"); // return after each sub row
                }
//                if (map[z][y] != null)
//                    System.out.print("\n"); // return after each full row
            }
        }
    }
}
