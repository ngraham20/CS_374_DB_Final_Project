package ocean_mine;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ngraham20
 */
public class DBAccess {
    
    private Connection conn;
    
    DBAccess()
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
                + "/ocean_mining_facility", // specific schema
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
    
    // TODO add methods to do specific queries
    // public void walk(DIRECTION) would be an example, with the SQL
    // statement specific to walking in a direction inside the method
    
    // Another statement could return data in the form of a ResultSet
    // public List getInventory() would be an example.
    
    public ResultSet getDiscoveredRooms() throws SQLException
    {
        String query = ""; // this query should be specific to this method
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        return rs;
    }
}
