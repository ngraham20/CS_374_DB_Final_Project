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
        System.out.println("Action required: Username and Password required to access Desktop Database.");
        System.out.print("Username: ");
        username = sc.nextLine();
        System.out.print("Password: ");
        password = sc.nextLine();
        
        conn = DriverManager.getConnection("jdbc:mariadb://25.12.183.131:3306/ocean_mining_facility", username, password);
            System.out.println("Connected to Server");
            return true;
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return false;
    }
}
