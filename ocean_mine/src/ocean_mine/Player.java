package ocean_mine;

/**
 *
 * @author loder
 */
public class Player implements Entity {
    
    @Override
    public int descriptionID()
    {
        return 0;
    }
    
    // these variables allow fluid interaction with the DBMS
    private int id;
    private int room_id;
    private int inventory_id;
    
    
    Inventory inventory = new Inventory();
    
    
    public enum Action
    {
        WALK, USE, TALK, READ, LOOK, TAKE, GIVE
    }
    
    
    public enum Direction
    {
        NORTH, SOUTH, EAST, WEST, UP, DOWN
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
