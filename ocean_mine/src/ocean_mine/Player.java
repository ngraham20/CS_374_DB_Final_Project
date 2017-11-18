package ocean_mine;

import java.sql.SQLException;

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
    
    private final Map map;
    private final Inventory inventory;
    private Response response;
    
    
    public enum Direction
    {
        NORTH, SOUTH, EAST, WEST, UP, DOWN
    }
    
    Player()
    {
        map = new Map();
        inventory = new Inventory();
        response = Response.QUIT; // default is quit
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
        UserInput ui = new UserInput();
        String expectedValues[] = {"Walk", "Talk", "Take", "Give", "Use", "Push", "Look", "Read", "Map", "Quit"};
        ui.setExpectedValues(expectedValues);
        
        // this should loop until a valid response is found
        do
        {
            ui.promptUser();
        } while (ui.getResponse() == Response.INVALID);
        
        // by this point, the input should be valid
        switch (ui.getInput().toLowerCase())
        {
            case "walk": performSAWalk();
            break;
            case "talk": performSATalk();
            break;
            case "take": performSATake();
            break;
            case "give": performSAGive();
            break;
            case "use": performSAUse();
            break;
            case "push": performSAPush();
            break;
            case "look": performSALook();
            break;
            case "read": performSARead();
            break;
            case "map": readMap(); // TODO change this to be read using the READ command Read->Map
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
        System.out.println("Taking");
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
//        try
//        {
//        map.update();
//        map.print(0);
//        }
//        catch (SQLException e)
//        {
//            System.out.println(e);
//            return false;
//        }
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
