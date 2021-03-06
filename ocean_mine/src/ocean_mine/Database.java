/*
 * Project created by Nathaniel Graham and Sam Holzer
 */
package ocean_mine;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ngraham20, sholzer20
 */
public class Database {
    
    private Connection conn;
    private ResultSet rs;
    
    Database()
    {
        
    }
    
    public boolean connect()
    {
        try {
            // creates a scanner to receive user input through the console
        Scanner sc = new Scanner(System.in);
        String username;
        String password;
        System.out.println("Action required: Enter Username and Password to access Desktop Database.");
        System.out.print("Username: ");
        username = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
        
        conn = DriverManager.getConnection(
                  "jdbc:mariadb:" // jdbc driver
                + "//25.12.183.131:3306" // ip address of the database
                + "/ocean_mine", // specific schema
                username, // username
                password); // password
            System.out.println("Server Connection Sucessful.");
            return true;
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean disconnect()
    {
        try
        {
        conn.close();
        System.out.println("Connection Closed.");
        return true;
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
    
     public ResultSet getQueryResults() throws SQLException
    {
        return this.rs;
    }
    
    public void queryMapData() throws SQLException
    {
        String query = "CALL queryMapData();";
        
        Statement st = conn.createStatement();
        rs = st.executeQuery(query);
    }
    

    public void discoverRoom(int player_id) throws SQLException
    {
        String query = "CALL discoverRoom(" + player_id + ");";
        
        Statement st = conn.createStatement();
        st.executeQuery(query);
    }
    
    public void gameSetup(int player_id) throws SQLException
    {
        String query = "CALL gameSetup(" + player_id + ");";
        
        Statement st = conn.createStatement();
        st.executeQuery(query);
    }
    
    public void checkRooms(int roomId) throws SQLException
    {
        String query = "CALL checkRooms(" + roomId + ");";
        
        Statement st = conn.createStatement();
        rs = st.executeQuery(query);
    }
        
    public void resetRooms() throws SQLException
    {
        String query = "CALL resetRooms();";
        
        Statement st = conn.createStatement();
        st.executeQuery(query);
    }
    
    public void movePlayerNorth(int id) throws SQLException
    {
        String query = "CALL movePlayerNorth(" + id + ");";
        
        Statement st = conn.createStatement();
        st.executeQuery(query);
    }
    
    public void movePlayerSouth(int id) throws SQLException
    {
        String query = "CALL movePlayerSouth(" + id + ");";
        
        Statement st = conn.createStatement();
        st.executeQuery(query);
    }
    
    public void movePlayerEast(int id) throws SQLException
    {
        String query = "CALL movePlayerEast(" + id + ");";
        
        Statement st = conn.createStatement();
        st.executeQuery(query);
    }
    
    public void movePlayerWest(int id) throws SQLException
    {
        String query = "CALL movePlayerWest(" + id + ");";
        
        Statement st = conn.createStatement();
        st.executeQuery(query);
    }
    
    public void takeItem(String item_name, int player_id) throws SQLException
    {
        String query = "CALL takeItem(\"" + item_name + "\", " + player_id + ")";
        
        Statement st = conn.createStatement();
        st.executeQuery(query);
    }
    
    public void getRoomInventory(int player_id) throws SQLException
    {
        String query = "CALL getRoomInventory(" + player_id + ")";
        
        Statement st = conn.createStatement();
        rs = st.executeQuery(query);
    }
    
    public void getPlayerInventory(int player_id) throws SQLException
    {
        String query = "CALL getPlayerInventory(" + player_id + ")";
        
        Statement st = conn.createStatement();
        rs = st.executeQuery(query);
    }
    
    public void getPlayerRoomID(int player_id) throws SQLException
    {
        String query = "Call playerLocation(" + player_id + ")";
        
        Statement st = conn.createStatement();
        rs = st.executeQuery(query);
    }
    
    public void queryCurrentRoomDescription(int player_id) throws SQLException
    {
        String query = "CALL getCurrentRoomDescription(" + player_id + ")";
        
        Statement st = conn.createStatement();
        rs = st.executeQuery(query);
    }
    
    public void unlockDoor(String door, int player_id) throws SQLException
    {
        String query = "CALL unlockDoor(\"" + door + "\", " + player_id + ")";
        
        Statement st = conn.createStatement();
        rs = st.executeQuery(query);
    }
}
