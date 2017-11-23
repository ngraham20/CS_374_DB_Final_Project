package ocean_mine;

import java.sql.*;

/**
 *
 * @author ngraham20, sholzer20
 */
public class Room {
    
    Database database;
    
    Room()
    {
    }
    
    public void linkDatabase(Database database)
    {
        this.database = database;
    }
    
    public void describeCurrentRoom(int player_id)
    {
        try
        {
            // get room description
            database.queryCurrentRoomDescription(player_id);

            ResultSet rs = database.getQueryResults();
            
            String description;
            String entity;
            
            rs.first();
            
            description = rs.getString("room");
            System.out.print(description);
            System.out.print("\n");
            
            entity = rs.getString("entity");
            System.out.println(entity);
                
            while (rs.next())
            {
                System.out.println("\n");
                entity = rs.getString("entity");
                System.out.println(entity);
            }
            
            System.out.println();
            
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    
//    public void describeRoom(int room_id)
//    {
//       // get room description
//       database.getRoomDescription(room_id);
//       
//       // print the room description
//       
//       // print the other describable features of the room
//       // (NPCs, Items, DREs)
//    }
}
