package controller;
import java.awt.EventQueue;
import java.util.Map;

import model.ContestDatabaseManager;
import model.EntryDatabaseManager;
import model.User;
import model.UserDatabaseManager;

/**
 * Created by lizmiller on 4/29/16.
 */




public class Main {
	
	/*		File Names  	*/
	private static final String USER_FILE = "User.csv";
	private static final String CONTEST_FILE = "Contests.csv";
	private static final String ENTRY_FILE = "Entries.csv";
	
    public static void main(String args[]) {

    	modelTests(); 
    	System.out.println("\nStarting controller....");
    	startController();

    }
    
    /**
     * Start up the Controller which starts the GUI.
     */
    private static void startController() {
    	
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager(USER_FILE);
        userDatabaseManager.readCsvFile(); // should this happen automatically in userDatabaseManager constructor?

        ContestDatabaseManager contestDatabaseManager = new ContestDatabaseManager(CONTEST_FILE);
        contestDatabaseManager.readCsvFile();
        
        EntryDatabaseManager entryDatabaseManager = new EntryDatabaseManager(ENTRY_FILE);
        entryDatabaseManager.readCsvFile();
        
    	EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainController(userDatabaseManager, contestDatabaseManager, entryDatabaseManager);
			}
    		
    	});

    }
    
    /** all of Liz's tests, moved to a separate function
     */
    private static void modelTests() {
        /*
        * every time a new entry is added update the map and increment the counter for the id of the entry
        * when we need to display the entries to the screen us the updated map
        * ONLY WRITE TO THE FILE ONCE THE PROGRAM IS ENDED?????
        *
        * */



        //Creating the User
        String userFile = "User.csv";
        UserDatabaseManager userDatabaseManager = new UserDatabaseManager(userFile);
        userDatabaseManager.readCsvFile();
        System.out.println("Users");
        Map<Integer, User> userMap = userDatabaseManager.getUserMap();
        System.out.println("Contestant or Admin " + userMap);
        System.out.println("Get the user that belongs to cardnumber 3 " + userMap.get(3));

        System.out.println("\n\n");

        String contestFile = "Contests.csv";
        ContestDatabaseManager contestDatabaseManager = new ContestDatabaseManager(contestFile);
        contestDatabaseManager.readCsvFile();
        System.out.println("Contests");
        System.out.println(contestDatabaseManager.getContestMap());

        System.out.println("\n\n");

        String entryFile = "Entries.csv";
        EntryDatabaseManager entryModel = new EntryDatabaseManager(entryFile);
        entryModel.readCsvFile();
        System.out.println("Entries");





        //checking if user exists
        //card number: 5
        //pin: pin123
        User findUser = userDatabaseManager.checkCredientals(5,"pin123");
        System.out.println("The user found " + findUser.getName());





//
//        //EXAMPLE ON HOW TO ADD NOW CONTEST
////        entryModel.addEntry("PATH",1,"EntryName!!");
//        System.out.println(entryModel.getEntryMap());


        //WRITING TO FILE WILL REWRITE MAP TO THE FILE
        //TO DISPLAY CONTESTS CAN USE THE MAP
        entryModel.writeCsvFile();
    }
    
}
