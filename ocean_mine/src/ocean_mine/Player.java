package ocean_mine;

import java.sql.*;

/**
 *
 * @author ngraham20, sholzer20
 */
public class Player implements Entity {
    
    // these variables allow fluid interaction with the DBMS
    private final int id;
    private int room_id;
    private int inventory_id;
    private int description_id;
    
    private Database database;
    private final Map map;
    private final Inventory inventory;
    private Response response;
    private final UserInput ui;
    
    private final Room room;
    
    private int damageCountdown;
    private int sicknessCountdown;
    
    public enum Direction
    {
        NORTH, SOUTH, EAST, WEST, UP, DOWN
    }
    
    Player()
    {
        id = 1;
        map = new Map();
        inventory = new Inventory();
        ui = new UserInput();
        response = Response.QUIT; // default is quit
        room = new Room();
        
        damageCountdown = 0;
        sicknessCountdown = 0;
    }
    
    public void linkDatabase(Database database)
    {
        this.database = database;
        map.linkDatabase(database);
        room.linkDatabase(database);
    }
    
    private void setDescriptionID()
    {
        // TODO implement
    }
    
    @Override
    public int getDescriptionID()
    {
        return description_id;
    }
    
    private void setInventoryID()
    {
        // TODO implement
    }
    
    @Override
    public int getInventoryID()
    {
        return inventory_id;
    }
    
    private void setID()
    {
        // TODO implement
    }
    
    @Override
    public int getID()
    {
        return id;
    }
    
    public void setRoomID(int id)
    {
        ResultSet rs;
        try
        {
            // query for the rooms connected to the current room
            database.getPlayerRoomID(id);
            rs = database.getQueryResults();
            
            if (rs.next())
            {
                room_id = rs.getInt("room_id");
            }
        }
        
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    public int getRoomID()
    {
        return room_id;
    }
    
//    public boolean performAction(Action action)
//    {
//        switch (action)
//        {
//            case WALK:
//                {
//                // prompt user for direction
//                String direction = "";
//                switch (direction)
//                    {
//                    case "north":
//                        move(Direction.NORTH);
//                        return true;
//                    case "south":
//                        move(Direction.SOUTH);
//                        return true;
//                    case "east":
//                        move(Direction.EAST);
//                        return true;
//                    case "west":
//                        move(Direction.WEST);
//                        return true;
//                    case "up":
//                        move(Direction.UP);
//                        return true;
//                    case "down":
//                        move(Direction.DOWN);
//                        return true;
//                    }
//                }
//        }
//        return false;
//    }
    
    public boolean performPrimaryAction()
    {
        if (sicknessCountdown > 0)
        {
            sicknessCountdown--;
            if (sicknessCountdown == 0)
            {
                System.out.println("The banana, which you had eaten only minutes before, causes you to keel over.\n"
                        + "Your face pales, and you begin to turn green. You groan before throwing up on the floor.");
            }
        }
        String expectedValues[] = {"Walk", "Talk", "Take", "Give", "Use", "Push", "Look", "Read", "Map", "Quit"};
        ui.setExpectedValues(expectedValues);
        
        System.out.print("||Walk||Talk||Take||\n||Give||Use "
                        +"||Push||\n||Look||Read||Map ||\n      ||Quit||\n");
        //System.out.print("||\n");
        
        // this should loop until a valid response is found
        do
        {
            ui.promptUser();
        } while (ui.getResponse() == Response.INVALID);
        
        // by this point, the input should be valid
        switch (ui.getInput().toLowerCase())
        {
            case "walk": performSAWalk(); // TODO walk
            break;
            case "talk": performSATalk(); // TODO talk
            break;
            case "take": performSATake();
            break;
            case "give": performSAGive(); // TODO give
            break;
            case "use": performSAUse(); // TODO use
            break;
            case "push": performSAPush(); // TODO push
            break;
            case "look": performSALook(); // TODO look
            break;
            case "read": performSARead(); // TODO read
            break;
            case "map": readMap();// TODO maybe change this to be read using the READ command Read->Map
            break;
            case "quit": response = Response.QUIT;
            return true;
        }
        response = Response.CONTINUE;
        return true; 
    }
    
    // The first SQL statement will find all locked doors and set boolean values to determine if the door is locked
    // The second SQL statement determines which direction the user wants to walk and then moves their position
    private boolean performSAWalk()
    {
        boolean northLocked = false;
        boolean southLocked = false;
        boolean eastLocked = false;
        boolean westLocked = false;
        
        ResultSet rs;
        // First SQL statement
        try
        {
            // query for the room's inventory for item names
            database.getRoomInventory(id);
            rs = database.getQueryResults();
            
            // Only need to worry about knowing the item name and type
            String item_name;
            String item_type;
            
            while (rs.next())
            {
                item_name = rs.getString("name");
                item_type = rs.getString("type");
                
                // Make sure each entity in the inventory is a locked door
                if (item_type != null && item_type.equals("DOOR"))
                {
                    // As long as the item is a door, then its name matches the blocked direction
                    switch (item_name) {
                        case "North":
                            northLocked = true;
                            break;
                        case "South":
                            southLocked = true;
                            break;
                        case "East":
                            eastLocked = true;
                            break;
                        case "West":
                            westLocked = true;
                            break;
                        default:
                            break;
                    }
                }
            }  
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
        
        // Second SQL statement
        System.out.println("Which direction would you like to walk?");
        try
        {
            // query for the rooms connected to the current room
            database.checkRooms(room_id);
            rs = database.getQueryResults();
            
            if (rs.next())
            {
            // print and store query results
            boolean n = rs.getBoolean("north");
            boolean s = rs.getBoolean("south");
            boolean e = rs.getBoolean("east");
            boolean w = rs.getBoolean("west");
            // Size needed for final array
            int count = 0;
            
            // Loading string values for each room that exists into temporary array
            String rooms[] = new String[4];
            if (n)
            {
                rooms[0] = "North";
                count++;
            }
            if (s)
            {
                rooms[1] = "South";
                count++;
            }
            if (e)
            {
                rooms[2] = "East";
                count++;
            }
            if (w)
            {
                rooms[3] = "West";
                count++;
            }
            
            // Make a valid input array with only non-null values to permanent array
            int index = 0;
            String validInputs[] = new String[count + 1];
            for (String tempRoom : rooms) {
                if (tempRoom != null) {
                    // Just to be sure we don't get an out of bounds error
                    if (index < validInputs.length) {
                        System.out.print("||" + tempRoom);
                        validInputs[index] = tempRoom;
                        index++;
                    }
                }
            }
            
            validInputs[index] = "cancel";
            System.out.print("|| <Cancel> ||\n");
            
            // get user input
            ui.setExpectedValues(validInputs);
            do
            {
                ui.promptUser();
                
            } while (ui.getResponse() == Response.INVALID);
            
            String input = ui.getInput();
            
            // assuming now that the input is valid, performs appropriate action
            if (input.toLowerCase().equals("cancel"))
                return false;
            else
            {
                // Each direction will first check that the door isn't locked
                switch (input.toLowerCase()) {
                    case "north":
                        if (northLocked)
                        {
                            System.out.println("No matter how hard you try, the door won't budge. Try finding a key!");
                            break;
                        }
                        database.movePlayerNorth(id);
                        break;
                    case "south":
                        if (southLocked)
                        {
                            System.out.println("No matter how hard you try, the door won't budge. Try finding a key!");
                            break;
                        }
                        database.movePlayerSouth(id);
                        break;
                    case "east":
                        if (eastLocked)
                        {
                            System.out.println("No matter how hard you try, the door won't budge. Try finding a key!");
                            break;
                        }
                        database.movePlayerEast(id);
                        break;
                    case "west":
                        if (westLocked)
                        {
                            System.out.println("No matter how hard you try, the door won't budge. Try finding a key!");
                            break;
                        }
                        database.movePlayerWest(id);
                        break;
                    default:
                        break;
                    }
                // Makes sure the Player object is current
                setRoomID(id);
                database.discoverRoom(id);
                
                }  
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
        room.describeCurrentRoom(id);
        
        return false;
    }
    
    private boolean performSATalk()
    {
        System.out.println("Talking");
        return false;
    }
    
    private boolean performSATake()
    {
        System.out.println("Which item would you like to take?");
        ResultSet rs;
        try
        {
            // query for the room's inventory for item names
            database.getRoomInventory(id);
            rs = database.getQueryResults();
            
            // print and store query results
            String item_name;
            String items[] = new String[25];
            String validInputs[];
            int index = 0;
            
            while (rs.next())
            {
                item_name = rs.getString("name");
                String item_type = rs.getString("type");
                
                if (item_type != null && item_type.equals("ITEM"))
                {
                    System.out.print("||");
                    items[index] = item_name;
                    System.out.print(item_name);
                    index++;
                }
            }
            items[index] = "cancel";
            
            // initializes the size of validInputs based on the number of item_names
            validInputs = new String[index+1];
            
            // now load validInputs with all the real values of item_names
            System.arraycopy(items, 0, validInputs, 0, index+1);
            
            System.out.print("|| <Cancel> ||\n");
            
            // get user input
            ui.setExpectedValues(validInputs);
            do
            {
                ui.promptUser();
                
            } while (ui.getResponse() == Response.INVALID);
            
            String input = ui.getInput();
            
            // assuming now that the input is valid, loop for the item
            if (input.toLowerCase().equals("cancel"))
                return false;
            else
            {
                for (int i = 0; i <= index; i++)
                {
                    if (input.toLowerCase().equals(validInputs[i].toLowerCase()))
                    {
                        database.takeItem(input, id);
                        return true;
                    }
                }
            }
        }
        
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
        return false;
    }
    
    private boolean performSAGive()
    {
        System.out.println("Giving");
        return false;
    }
    
    private boolean performSAUse()
    {        
        // query the database for the player's inventory
        try
        {
            ResultSet rs;
            database.getPlayerInventory(id);
            rs = database.getQueryResults();

            String[] item_names = new String[25];
            String[] item_uses = new String[25];
            String validInputs[];
            String validUses[];
            int index = 0;
            while (rs.next())
            {
                String item_name = rs.getString("name");
                String item_use = rs.getString("use");

                if (item_name != null && item_use != null)
                {
                    item_names[index] = item_name;
                    item_uses[index] = item_use;
                    index++;
                    System.out.print("||" + item_name);
                }
            }

            item_names[index] = "cancel";
            item_uses[index] = "CANCEL";

            // initializes the size of validInputs based on the number of item_names
            validInputs = new String[index+1];
            validUses = new String[index+1];

            // now load validInputs with all the real values of item_names
            System.arraycopy(item_names, 0, validInputs, 0, index+1);
            System.arraycopy(item_uses, 0, validUses, 0, index+1);

            System.out.print("|| <Cancel> ||\n");
            
            ui.setExpectedValues(validInputs);
            
            do
            {
            // check the item's usage to decide which path to take for usage
            ui.promptUser();
            
            } while (ui.getResponse() == Response.INVALID);

            // at this point, response should be a valid one
            String input = ui.getInput().toLowerCase();
            
            // loop through all valid inputs
            for (int i = 0; i < validInputs.length; i++)
            {
                if (input.equals(validInputs[i].toLowerCase()))
                {
                    switch (validUses[i])
                    {
                        case "UNLOCK": 
                            {   unlockDoor();
                                break;
                            }
                        case "COMBINE": System.out.println("[System]: Currently an unsupported action.\n");
                            break;
                        case "EAT": eat(input);
                            break;
                        case "CUDDLE": System.out.println("You cuddle the " + validInputs[i] + ". It is very soft.\n");
                            break;
                        case "BREAK": System.out.println("[System]: Currently an unsupported action.\n");
                            break;
                        case "CANCEL": 
                            break;
                    }
                }
            }

            // to do later
            //-------------------------------------------------------
            // if combine
                // list other item_names in the player's inventory
                // prompt the user to choose an ITEM or cancel
            //--------------------------------------------------------
            
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
        return false;
    }
    
    private void eat(String item_name)
    {
        if (item_name.equals("banana"))
        {
            System.out.println("You eat the banana... It leaves your stomach highly unsettled...");
            sicknessCountdown = 4;
        }
    }
    
    private void unlockDoor()
    {
        // retrieve all doors in the room
        try
        {
            ResultSet rs;
            database.getRoomInventory(id);
            rs = database.getQueryResults();
            
            String[] item_names = new String[25];
            String validInputs[];
            int index = 0;
            
            while(rs.next())
            {
                String name = rs.getString("name");
                String type = rs.getString("type");
                
                if(type != null && type.equals("DOOR"))
                {
                    item_names[index] = name;
                    System.out.print("||" + name);
                    index++;
                }
            }
            
            // add cancel to the possible choices
            item_names[index] = "cancel";
            
            
            // initializes the size of validInputs based on the number of item_names
            validInputs = new String[index+1];

            // now load validInputs with all the real values of item_names
            System.arraycopy(item_names, 0, validInputs, 0, index+1);

            System.out.print("|| <Cancel> ||\n");
            
            ui.setExpectedValues(validInputs);
            
            // prompt user for valid response
            do
            {
                ui.promptUser();
                
            } while (ui.getResponse() == Response.INVALID);
            
            // here, the response should be valid
            
            String input = ui.getInput().toLowerCase();
            
            // remove the chosen door from the room's inventory
            database.unlockDoor(input, id);
            
            // informs user that the door was unlocked
            if(!input.equals("cancel"))
            {
                System.out.println("*CLICK*");
            }
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    
    private boolean performSAPush()
    {
        System.out.println("Pushing");
        return false;
    }
    
    private boolean performSALook()
    {
        System.out.println("Looking");
        return false;
    }
    
    private boolean performSARead()
    {
        System.out.println("Reading");
        return false;
    }
    
    private boolean readMap()
    {
        
        System.out.println("Printing Map");
        try
        {
        map.update();
        }
        catch (SQLException e)
        {
            System.out.println(e);
            return false;
        }
        
        map.print(1);
        
        System.out.println("Type 'Close' to return to game.");
        String validResponses[]= new String[] {"Close"};
        
        ui.setExpectedValues(validResponses);
        ui.promptUser();
        
        return true;
    }
    
    public Response getResponse()
    {
        return response;
    }
    
    public boolean move(Direction direction)
    {
        
        switch (direction)
        {
            case NORTH:
                // sql North
                return true;
            case SOUTH:
                // sql South
                return true;
            case EAST:
                // sql East
                return true;
            case WEST:
                // sql West
                return true;
        }
        return false;
    }
    
}
