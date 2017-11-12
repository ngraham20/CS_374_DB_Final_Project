/*
 * Project created by Nathaniel Graham and Sam Holzer
 */
package ocean_mine;

/**
 *
 * @author ngraham20
 */
public class Achievement {
    
    String name;
    boolean unlocked;
    
    Achievement(String name)
    {
        this.name = name;
        unlocked = false;
    }

    public void unlock()
    {
        this.unlocked = true;
        
        // TODO print achievement unlocked banner
    }
}
