/*
 * Project created by Nathaniel Graham and Sam Holzer
 */
package ocean_mine;

/**
 *
 * @author ngraham20, sholzer20
 */
public class Achievement {
    
    private final String name;
    private String description;
    private boolean unlocked;
    
    Achievement(String name)
    {
        this.name = name;
        unlocked = false;
    }

    public boolean isUnlocked()
    {
        return this.unlocked;
    }
    public void unlock()
    {
        if (unlocked == false)
        {
        this.unlocked = true;
        
        System.out.println("----------------------------------------------------");
        System.out.println("   <<Achievement Unlocked: " + name + ">>   ");
        System.out.println("----------------------------------------------------\n");
        }
    }
    
    public String getDescription()
    {
        return description;
    }
}
