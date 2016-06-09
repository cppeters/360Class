/**
 * 
 */
package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import model.Contest;
import model.ContestDatabaseManager;
import model.Entry;
import model.EntryDatabaseManager;
import model.User;
import model.UserDatabaseManager;

/**
 * @author Casey
 *
 */
public class UserTest {
	private static final String DB_DIRECTORY = "database" + File.separator;
	private static final String USER_FILE = DB_DIRECTORY  + "User.csv";
	private static final String CONTEST_FILE = DB_DIRECTORY + "Contests.csv";
	private static final String ENTRY_FILE = "TestEntries.csv";
	private EntryDatabaseManager myEntryDB;
	private User myUser;
	private Contest myContest;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ContestDatabaseManager theContestDB = new ContestDatabaseManager(CONTEST_FILE);
		theContestDB.readCsvFile();
		myContest = theContestDB.getAllItems().get(1);
		
        myEntryDB = new EntryDatabaseManager(ENTRY_FILE);
        myEntryDB.readCsvFile();
        
        UserDatabaseManager theUserDB = new UserDatabaseManager(USER_FILE, myEntryDB);
		theUserDB.readCsvFile();
        myUser = theUserDB.getMap().get(1);
	}

	/**
	 * Test method for {@link model.User#addEntry(model.Entry, model.EntryDatabaseManager)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testAddEntry() throws Exception {
		// Size of myEntryDB
		int totalEntriesUser = myUser.getEntries().size();
		Entry theEntry = new Entry(totalEntriesUser + 1,
				myUser.getCardNumber(), "Path Test", 
				myContest.getContestNumber(), "Name Test");

		myUser.addEntry(theEntry, myEntryDB);

		// Check if entries size increased
		assertEquals(totalEntriesUser + 1, myUser.getEntries().size());
		
		// Check if theEntry was added to myUser entries list
		assertEquals(theEntry, myUser.getEntries().get(myUser.getEntries().size() - 1));
		
		// Check if theEntry was added to myEntryDB Map
		assertEquals(theEntry, myEntryDB.getMap().get(theEntry.getEntryNumber()));
	}

	/**
	 * Test method for {@link model.User#updateEntry(int, model.Entry, model.EntryDatabaseManager)}.
	 * @throws FileNotFoundException 
	 */
	@Test
	public void testUpdateEntry() throws Exception {
		Entry theOldEntry = new Entry(0, myUser.getCardNumber(), "Path Test 2",
				myContest.getContestNumber(), "Name Test 2");
		Entry theNewEntry = new Entry(0, 
				myUser.getCardNumber(), "Path Test 3", 
				myContest.getContestNumber(), "Name Test 3");
		myUser.addEntry(theOldEntry, myEntryDB);
		int theOldEntryIdx = myUser.getEntries().indexOf(theOldEntry);

		// Update the Entry
		myUser.updateEntry(theOldEntryIdx, theNewEntry, myEntryDB);
		
		// Check if theOldEntry is still in User List
		for (Entry e : myUser.getEntries()) {
			assertNotEquals(theOldEntry, e);
		}
		
		// Check if theNewEntry was inserted in place of theOldEntry
		assertEquals(theNewEntry, myUser.getEntries().get(theOldEntryIdx));
	}

	/**
	 * Test method for {@link model.User#addReadEntries(model.Entry)}.
	 */
	@Test
	public void testAddReadEntries() {
		Entry theEntry = new Entry(0, 
				myUser.getCardNumber(), "Path Test 4", 
				myContest.getContestNumber(), "Name Test 4");
		int total = myUser.getEntries().size();
		myUser.addReadEntries(theEntry);
		
		// Check if entries size increased
		assertEquals(total + 1, myUser.getEntries().size());
		
		// Check if myEntry was added to myUser entries list
		assertEquals(theEntry, myUser.getEntries().get(myUser.getEntries().size() - 1));		
	}
}
