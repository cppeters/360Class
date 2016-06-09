package controller;
import java.awt.EventQueue;
import java.io.File;

import model.*;

/**
 * Created by lizmiller on 4/29/16.
 */


public class Main {
	
	/*		File Names  	*/
	private static final String DB_DIRECTORY = "database" + File.separator;
	private static final String USER_FILE = DB_DIRECTORY  + "User.csv";
	private static final String CONTEST_FILE = DB_DIRECTORY + "Contests.csv";
	private static final String ENTRY_FILE = DB_DIRECTORY + "Entries.csv";
	private static final String JUDGE_FILE = DB_DIRECTORY + "Judge.csv";
	
    public static void main(String args[]) throws Exception {
    	startController();
    }

    /**
     * Start up the Controller which starts the GUI.
     * @author Tabi
     * @throws Exception
     */
    private static void startController() throws Exception {

        final ContestDatabaseManager contestDatabaseManager = new ContestDatabaseManager(CONTEST_FILE);
        contestDatabaseManager.readCsvFile();

        final EntryDatabaseManager entryDatabaseManager = new EntryDatabaseManager(ENTRY_FILE);
        entryDatabaseManager.readCsvFile();

        final UserDatabaseManager userDatabaseManager = new UserDatabaseManager(USER_FILE, entryDatabaseManager);
        userDatabaseManager.readCsvFile();

        final JudgeDatabaseManager judgeDatabaseManager = new JudgeDatabaseManager(JUDGE_FILE, entryDatabaseManager);
        judgeDatabaseManager.readCsvFile();

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainController(userDatabaseManager, contestDatabaseManager,
                        entryDatabaseManager, judgeDatabaseManager);
            }

        });

    }
}
