/*
 * Project created by Nathaniel Graham and Sam Holzer
 */
package ocean_mine;
import java.util.Scanner;
/**
 *
 * @author ngraham20, sholzer20
 */

// this class organizes the user interface and is responsible for user input
public class UserInput {
    
    Response response;
    Scanner sc;
    String input;
    
    UserInput()
    {
        response = Response.CONTINUE;
        sc = new Scanner(System.in);
    }
    
    public String getInput()
    {
        input = sc.next();
        if (input.equals("quit"))
        {
            response = Response.QUIT;
        }
        return input;
    }

    public Response getResponse()
    {
        return response;
    }
    
}
