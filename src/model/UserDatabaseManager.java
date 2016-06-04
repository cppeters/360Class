package model;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * @author liz
 */
public class UserDatabaseManager extends DatabaseManager<User>{

    /** The database type*/
    // UserDatabaseManager is type 3
    private final static int DATABASE_TYPE = 3;
    /** The database for the entries*/
    private EntryDatabaseManager myEntryDB;


    /** Constructor()
     * @param theFileName - file path
     * @param theEntryDatabase - database for the entries*/
    public UserDatabaseManager(String theFileName, EntryDatabaseManager theEntryDatabase) throws Exception {
        super(theFileName);
        myEntryDB = theEntryDatabase;
    }

    /** Reading in the csv file
     * @exception Exception*/
    public void readCsvFile() throws Exception {
        super.readCsvFile(DATABASE_TYPE, myEntryDB);
    }

    /** Checks to see of the user exists and if the pin matched the user
     * @param theCardNumber - card number for the user logging in
     * @param thePin - pin for the user*/
    public User checkCredentials(int theCardNumber, String thePin) {
        //check user map first then judge map
        User user = null;
        if(super.getMap().containsKey(theCardNumber)) {
            User foundUser = super.getMap().get(theCardNumber);
            if(foundUser.getLoginCredential().equals(thePin)) {
                user = foundUser;
            }
        }
        return user;
    }
}