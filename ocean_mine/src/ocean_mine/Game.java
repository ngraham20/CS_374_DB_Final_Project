/*
 * Project created by Nathaniel Graham and Sam Holzer
 */
package ocean_mine;

/**
 *
 * @author ngraham20, sholzer20
 */
public class Game {
    // create a database connection for this game
    Database database;
    Map map;
    
    UserInput ui;
    Player player;
    Room room;
    
    Achievement helpMe;
    Achievement takingInventory;
    Achievement dropTheBass;
    Achievement whereAmI;
    
    Response response;
    
    Game()
    {
        // initializes the database
        database = new Database();
        
        // initializes the user input
        ui = new UserInput();
        
        room = new Room();
        room.linkDatabase(database);
        
        player = new Player();
        player.linkDatabase(database);
        
        // initializes the map system
        map = new Map();
        map.linkDatabase(database);
        
        
        response = Response.CONTINUE;
        
        helpMe = new Achievement("Help Me");
        takingInventory = new Achievement("Taking Inventory");
        dropTheBass = new Achievement("Drop the Bass");
        whereAmI = new Achievement("Where Am I?");
                
    }
    public void mainMenu()
    {
        // Might move this; awkward to set because database needs to be connected and that only happens in Game class after all initializations
        player.setRoomID(player.getID());
        
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
                case "play": play(); response = Response.CONTINUE;
                break;
                case "help":
                break;
                case "quit": response = Response.QUIT;
            }
    }
    public void start()
    {
        // on begining the game, connect to the database
        database.connect();
        
        do
        {
        // go to main menu
            mainMenu();
        } while (response == Response.CONTINUE);
    }
    
    private void play()
    {
        do
        {
            // describe room
            room.describeCurrentRoom(player.getID());
            
            // prompt the player for action
            player.performPrimaryAction();
            
        } while (player.getResponse() == Response.CONTINUE);
    }

}
