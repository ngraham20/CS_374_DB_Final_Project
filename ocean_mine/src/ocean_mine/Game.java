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
    Player player;
    
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
        
        player = new Player();
        
        player.linkDatabase(database);
        
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
            System.out.println("                     ------------------                       ");
            System.out.println("                    ||Play||Help||Quit||                      ");
            System.out.println("                     ------------------                       ");
            System.out.println("--------------------------------------------------------------");
            
            
            do
            {
                String validResponses[] = {"Play", "Help", "Quit"};
                ui.setExpectedValues(validResponses);
                ui.promptUser();
            }
            while (ui.getResponse() == Response.INVALID);
            
            // presumably, by this point in the code, a correct response has been reached
            switch (ui.getInput().toLowerCase())
            {
                case "play": play();
                break;
                case "help":
                    break;
            }
    }
    public void start()
    {
        // on begining the game, connect to the database
        database.connect();
        
        // go to main menu
        mainMenu();
    }
    
    private void play()
    {
        do
        {
            // describe room
            System.out.println("You are in a room, with things in it");
            
            // prompt the player for action
            player.performPrimaryAction();
            
        } while (player.getResponse() == Response.CONTINUE);
    }

}
