package ocean_mine;

/**
 *
 * @author ngraham20
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DBAccess dataBase = new DBAccess();
        dataBase.connect();
    }
}
