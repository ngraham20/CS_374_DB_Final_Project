package ocean_mine;

import java.sql.*;

/**
 *
 * @author ngraham20, sholzer20
 */
public class Player implements Entity {
    
    // these variables allow fluid interaction with the DBMS
    private int id;
    private int room_id;
    private int inventory_id;
    private int description_id;
    
    private DBAccess database;
    private final Map map;
    private final Inventory inventory;
    private Response response;
    private final UserInput ui;
    
    
    public enum Direction
    {
        NORTH, SOUTH, EAST, WEST, UP, DOWN
    }
    
    Player()
    {
        map = new Map();
        inventory = new Inventory();
        ui = new UserInput();
        response = Response.QUIT; // default is quit
    }
    
    public void linkDatabase(DBAccess database)
    {
        this.database = database;
        map.linkDatabase(database);
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
    
    private void setRoomID()
    {
        // TODO implement
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
        String expectedValues[] = {"Walk", "Talk", "Take", "Give", "Use", "Push", "Look", "Read", "Map", "Quit"};
        ui.setExpectedValues(expectedValues);
        
        System.out.print("||Walk||Talk||Take||Give||Use||\n"
                        +"||Push||Look||Read||Map||Quit||\n");
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
            case "take": performSATake(); // TODO take
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
            case "map": readMap(); // TODO maybe change this to be read using the READ command Read->Map
            break;
            case "quit": response = Response.QUIT;
            return true;
        }
        response = Response.CONTINUE;
        return true; 
    }
    
    private boolean performSAWalk()
    {
        System.out.println("Walking");
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
            String items[] = new String[10];
            String validInputs[];
            int index = 0;
            while (rs.next())
            {
                System.out.print("||");
                item_name = rs.getString("name");
                items[index] = item_name;
                System.out.print(item_name);
                index++;
            }
            items[index] = "cancel";
            
            // initializes the size of validInputs based on the number of items
            validInputs = new String[index+1];
            
            // now load validInputs with all the real values of items
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
                    if (input.toLowerCase().equals(validInputs[i]))
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
        System.out.println("Using");
        return false;
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
