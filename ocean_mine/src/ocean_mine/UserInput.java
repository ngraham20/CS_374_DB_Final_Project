/*
 * Project created by Nathaniel Graham and Sam Holzer
 */
package ocean_mine;
import java.util.Scanner;
/**
 *
 * @author ngraham20
 */

// this class organizes the user interface and is responsible for user input
public class UserInput {
    
    private Response response;
    private final Scanner sc;
    private String input;
    private String values[];
    
    UserInput()
    {
        sc = new Scanner(System.in);
    }
    
    // prompts the user for input
    public void promptUser()
    {
        System.out.print(">>");
        input = sc.next();
        
        // loops through the list of correct values desired for this prompt
        for (String value : values) {
            // if we find a value listed
            if (input.toLowerCase().equals(value.toLowerCase()))
            {
                // return out of the method and respond with VALID
                response = Response.VALID;
                return;
            }
        }
        // if no correct values are found, respond with INVALID
        response = Response.INVALID;
        System.out.println("[System]: Invalid Response \"" + input + "\"");
    }
    
    public void setExpectedValues(String values[])
    {
        this.values = values;
    }

    public Response getResponse()
    {
        return response;
    }
    
    public String getInput()
    {
        return input;
    }
    
}
