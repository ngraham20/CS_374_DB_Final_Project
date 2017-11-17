/*
 * Project created by Nathaniel Graham and Sam Holzer
 */
package ocean_mine;

import java.sql.SQLException;

/**
 *
 * @author ngraham20, sholzer20
 */
public class Game {
    // create a database connection for this game
    DBAccess database;
    Map map;
    
    UserInput ui;
    
    Achievement helpMe;
    Achievement takingInventory;
    Achievement dropTheBass;
    Achievement whereAmI;
    
    Game()
    {
        // initializes the database
        database = new DBAccess();
        
        // initializes the map system
        map = new Map();
        
        // initializes the user input
        ui = new UserInput();
        
        // links the map system to the system database
        map.linkDatabase(database);
        
        helpMe = new Achievement("Help Me");
        takingInventory = new Achievement("Taking Inventory");
        dropTheBass = new Achievement("Drop the Bass");
        whereAmI = new Achievement("Where Am I?");
                
    }
    public void mainMenu()
    {
        // run the main menu
            System.out.println("--------------------------------------------------------------");
            System.out.println("                   Ocean Mining Facility                      ");
            System.out.println("--------------------------------------------------------------");
            System.out.println("                                                              ");
            System.out.println("                     |Play||Help||Quit|                       ");
            System.out.println("                                                              ");
            System.out.println("--------------------------------------------------------------");
            
            
            do
            {
                String validResponses[] = {"Play", "Help", "Quit"};
                ui.setExpectedValues(validResponses);
                ui.promptUser();
            }
            while (ui.getResponse() == Response.INVALID);
    }
    public void start()
    {
        database.connect();
        
        mainMenu();
        
        
        try
        {
        map.update();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
        map.print(1);
        if (!whereAmI.isUnlocked())
        {
            whereAmI.unlock();
        }
    }
    
    private void play()
    {
        
    }

}
