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
     
    // TODO add methods to do specific queries
    // public void walk(DIRECTION) would be an example, with the SQL
    // statement specific to walking in a direction inside the method
    
    // Another statement could return data in the form of a ResultSet
    // public ResultSet getInventory() would be an example.
    
    public void queryMapData() throws SQLException
    {
        String query = "CALL queryMapData();";
        
        Statement st = conn.createStatement();
        rs = st.executeQuery(query);
    }
    

    public void discoverRoom(int roomId) throws SQLException
    {
        String query = "CALL discoverRoom(" + roomId + ");";
        
        Statement st = conn.createStatement();
        st.executeQuery(query);
    }
    
    public void resetRooms() throws SQLException
    {
        String query = "CALL resetRooms();";
        
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
    
    public void getPlayerRoomID(int player_id)
    {
        
    }
    
    public void queryCurrentRoomDescription(int player_id) throws SQLException
    {
        String query = "CALL getCurrentRoomDescription(" + player_id + ")";
        
        Statement st = conn.createStatement();
        rs = st.executeQuery(query);
    }
}
