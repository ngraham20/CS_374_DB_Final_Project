/*
 * Project created by Nathaniel Graham and Sam Holzer
 */
package ocean_mine;
import java.sql.*;
/**
 *
 * @author ngraham20, sholzer20
 */
public class Map {
    
    ResultSet rs;
    Database database;
    
    String map[][][][];
    
    int currentX;
    int currentY;
    int currentZ;
    
    Map()
    {
        //  {Z Y R X : data is stored at X }
        map  = new String[5][10][10][3];
        
        currentX = 0;
        currentY = 0;
        currentZ = 0;
    }
    
    public void linkDatabase(Database database)
    {
        this.database = database;
    }
    
    // will rebuild the entire map array from scratch
    public void update() throws SQLException
    {
        
         currentX = 0;
         currentY = 0;
         currentZ = 0;
        
        
        // send query to database to ask for a list of all discovered rooms
        database.queryMapData();
        
        
        // redraw map listings to account for the new information
        rs = database.getQueryResults();
        
        while (rs.next())
        {
            int id;
        int z;
        int y;
        int x;
        int discovered;
        boolean n;
        boolean s;
        boolean e;
        boolean w;
        
        boolean player;
        
            id = rs.getInt("id");
            discovered = rs.getInt("discovered");
            n = rs.getBoolean("north");
            s = rs.getBoolean("south");
            e = rs.getBoolean("east");
            w = rs.getBoolean("west");
            player = rs.getBoolean("player");
            
            if (id > 0) // if there's at least one digit
            {
                x = id%10;
                currentX++;
            }
            else
                x = 0;
            if (id > 9) // if there's at least two digits
            {
                y = (id/10)%10;
                currentY++;
            }
            else
                y = 0;
            if (id > 99) // if there's at least three digits
            {
                z = (id/100)%10;
                currentZ++;
            }
            else z = 0;
            
            if (discovered == 1) // if the current room is discovered
            {
                // manipulate the current room
                if (n)
                {
                    map[z][y][x][0] = "--|--";
                }
                else 
                    map[z][y][x][0] = "-----";
                if (w)
                {
                    map[z][y][x][1] = "-";
                }
                else
                {
                    map[z][y][x][1] = "|";
                }
                if(player)
                {
                    map[z][y][x][1] += "<o>";
                }
                else
                    map[z][y][x][1] += "   ";
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
    }
    
    public void print(int floor)
    {
        if (floor <= 0)
            throw new UnsupportedOperationException("The floor number must be 1 or greater.");
        
    // loops through the map system, printing it out
        System.out.println("-----------------------------------------------------------");
        System.out.println("-    -    -    -    -    [Floor " + (floor) + "]    -    -    -    -    -");
        System.out.println("-----------------------------------------------------------");
        for (int y = 0; y < currentY-1; y++) // for each column y

        {
            for (int row = 0; row < 3; row++) // for every subrow
            {
                for (int x = 0; x < currentX; x++) // for every datablock
                {
                    if(map[floor-1][y][x][row] != null)
                        System.out.print(map[floor-1][y][x][row]);
                    else
                        System.out.print("     ");
                    System.out.print(" ");
                }
                System.out.print("\n"); // return after each sub row
            }
        }
        System.out.println("-----------------------------------------------------------");
    }
}
