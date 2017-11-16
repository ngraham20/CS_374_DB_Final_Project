package ocean_mine;

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
    
    
    Inventory inventory = new Inventory();
    
    
    public enum Action
    {
        WALK, USE, TALK, READ, LOOK, TAKE, GIVE
    }
    
    
    public enum Direction
    {
        NORTH, SOUTH, EAST, WEST, UP, DOWN
    }
    
    Player()
    {
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
    
    public boolean performAction(Action action)
    {
        switch (action)
        {
            case WALK:
                {
                // prompt user for direction
                String direction = "";
                switch (direction)
                    {
                    case "north":
                        move(Direction.NORTH);
                        return true;
                    case "south":
                        move(Direction.SOUTH);
                        return true;
                    case "east":
                        move(Direction.EAST);
                        return true;
                    case "west":
                        move(Direction.WEST);
                        return true;
                    case "up":
                        move(Direction.UP);
                        return true;
                    case "down":
                        move(Direction.DOWN);
                        return true;
                    }
                }
        }
        return false;
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
