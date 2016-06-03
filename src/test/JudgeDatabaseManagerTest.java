package test;

import model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by cppeters on 6/2/2016.
 * @author Casey
 */
public class JudgeDatabaseManagerTest {

    // Instance fields
    private static final String TEST_JUDGE_FILE = "TestJudge.csv";
    private static final String TEST_ENTRY_FILE = "TestEntries.csv";
    private static final String TEST_USER_FILE = "User.csv";
    private JudgeDatabaseManager myJudgeDB;
    private EntryDatabaseManager myEntryDB;
    private UserDatabaseManager myUserDB;
    private Judge myJudge;

    /**
     * Setup method for testing.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        myEntryDB = new EntryDatabaseManager(TEST_ENTRY_FILE);
        myEntryDB.readCsvFile();

        myUserDB = new UserDatabaseManager(TEST_USER_FILE, myEntryDB);
        myUserDB.readCsvFile();

        myJudgeDB = new JudgeDatabaseManager(TEST_JUDGE_FILE, myEntryDB);
        myJudgeDB.readCsvFile();

        String newJudgeName = "Jeff John";
        int theCardNumber = myUserDB.getItemCount() + 1;
        for (int i : myUserDB.getMap().keySet()) {
            User aUser = myUserDB.getMap().get(i);
            if (newJudgeName.equals(aUser.getName())) {
                theCardNumber = aUser.getCardNumber();
            }
        }
        myJudge = new Judge(theCardNumber, newJudgeName, 28, "123", "JUDGE",
                4, 7, 8, 9);
    }


    /**
     * Test method for {@link model.JudgeDatabaseManager#addJudge(Judge, int)}}.
     * @throws Exception
     */
    @Test
    public void addJudge() throws Exception {

        // Initial count
        int total = myJudgeDB.getItemCount();

        // Add one entry
        myJudgeDB.addJudge(myJudge, myJudge.getCardNumber());

        // Check if items incremented
        assertEquals(total + 1, myJudgeDB.getItemCount());

        // Check if theEntry was added to myJudgeDB Map
        assertEquals(myJudge, myJudgeDB.getMap().get(myJudge.getCardNumber()));

        // Check if theEntry was added to TEST_JUDGE_FILE
        myJudgeDB.readCsvFile();
        assertEquals(myJudge, myJudgeDB.getMap().get(myJudge.getCardNumber()));
    }

    /*
     * Test method for {@link model.JudgeDatabaseManager#updateJudge(Judge, int)}.
     * @throws Exception
     */
    @Test
    public void updateJudge() throws Exception {
        Judge theOldJudge = new Judge(myJudge);     // Use copy constructor
        myJudge.setMyContestNumber(4);
        myJudge.setMyFirst(90);
        myJudge.setMySecond(91);
        myJudge.setMyThird(92);


        // Update the Judge
        myJudgeDB.updateJudge(myJudge, myJudge.getContestNumber());

        // Check if theOldJudge is still in the Judge List
        for (Judge j : myJudgeDB.getAllItems()) {
            assertNotEquals(theOldJudge, j);
        }

        // Check if theOldJudge is still in Judge Map
        for (int i : myJudgeDB.getMap().keySet()) {
            assertNotEquals(theOldJudge, myJudgeDB.getMap().get(i));
        }

        // Check if theNewJudge was inserted in place of theOldJudge in Map
        for (int i : myJudgeDB.getMap().keySet()) {
            if (myJudgeDB.getMap().get(i).getCardNumber() == myJudge.getCardNumber() &&
                    myJudgeDB.getMap().get(i).getContestNumber() == myJudge.getContestNumber())
                assertEquals(myJudge, myJudgeDB.getMap().get(i));
        }

    }

/**** Test Suite for readCsv() ******//*




    /**
     * Test method for {@link model.JudgeDatabaseManager#readCsvFile(int, EntryDatabaseManager)} .
     * @throws Exception
     */
    @Test(expected = FileNotFoundException.class)
    public void testReadCsvFile1() throws Exception {
        // Test No File Found
        JudgeDatabaseManager theJudgeDB = new JudgeDatabaseManager("Not a file name", myEntryDB);
        theJudgeDB.readCsvFile();
    }

    /**
     * Test method for {@link model.JudgeDatabaseManager#readCsvFile(int, EntryDatabaseManager)} .
     * @throws Exception
     */
    @Test(expected = NumberFormatException.class)
    public void testReadCsvFile2() throws Exception {
        // Test Bad File
        JudgeDatabaseManager theJudgeDB = new JudgeDatabaseManager("superman.jpeg", myEntryDB);
        theJudgeDB.readCsvFile();
    }

    /**
     * Test method for {@link model.JudgeDatabaseManager#readCsvFile(int, EntryDatabaseManager)} .
     * @throws FileNotFoundException
     */
    @Test
    public void testReadCsvFile3() throws Exception {
        // Test Good File
        JudgeDatabaseManager theJudgeDB = new JudgeDatabaseManager(TEST_JUDGE_FILE, myEntryDB);
        theJudgeDB.readCsvFile();
    }




/**** Test Suite for writeCsv() ******//*




/**
     * Test method for {@link model.JudgeDatabaseManager#writeCsvFile(int)} .
     * @throws FileNotFoundException
     */
    @Test(expected = FileNotFoundException.class)
    public void testWriteCsvFile1() throws Exception {
        // Test No File Found
        JudgeDatabaseManager theJudgeDB = new JudgeDatabaseManager("not a file", myEntryDB);
        theJudgeDB.writeCsvFile();
    }

    /**
     * Test method for {@link model.JudgeDatabaseManager#writeCsvFile(int)} .
     * @throws NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void testWriteCsvFile2() throws Exception {
        // Test Bad File
        JudgeDatabaseManager theJudgeDB = new JudgeDatabaseManager("superman.jpeg", myEntryDB);
        theJudgeDB.writeCsvFile();
    }


    /**
     * Test method for {@link model.JudgeDatabaseManager#writeCsvFile(int)} .
     * @throws FileNotFoundException
     */
    @Test
    public void testWriteCsvFile3() throws Exception {
        // Test Good File
        JudgeDatabaseManager theJudgeDB = new JudgeDatabaseManager(TEST_JUDGE_FILE, myEntryDB);
        theJudgeDB.readCsvFile();
        theJudgeDB.writeCsvFile();
    }
}