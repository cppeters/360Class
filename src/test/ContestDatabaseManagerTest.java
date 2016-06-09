package test;

import model.Contest;
import model.ContestDatabaseManager;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by cppeters on 6/1/2016.
 * @author Casey
 */
public class ContestDatabaseManagerTest {

    // Instance fields
    private static final String TEST_CONTEST_FILE = "TestContests.csv";
    private static final String DB_DIRECTORY = "database" + File.separator;
    private static final String CONTEST_FILE = DB_DIRECTORY + "Contests.csv";
    private ContestDatabaseManager myContestDB;

    @Before
    public void setUp() throws Exception {
        myContestDB = new ContestDatabaseManager(CONTEST_FILE);
        myContestDB.readCsvFile();
    }

    /**
     * Test method for {@link model.ContestDatabaseManager#addContest(String, String, String, String)} ()}.
     * Also test for {@link model.ContestDatabaseManager#contestNameIsUnique(String)}.
     * @throws Exception
     */
    @Test
    public void addContest() throws Exception {

        // Pull a contest from Contests.csv list
        Contest c = myContestDB.getAllItems().get(1);

        // Get it's name for testing
        String aContestName = c.getName();

        /** These two assertions will also test contestNameIsUnique(). */
        // Test to add a contest with a name already in DB.
        assertFalse(myContestDB.addContest(aContestName, "Test Contest", "1/1/1111", "2/2/2222"));

        // Test to add a contest without a name already in DB.
        ContestDatabaseManager theContestDB = new ContestDatabaseManager(TEST_CONTEST_FILE);
        assertTrue(theContestDB.addContest("New Contest", "Test Contest 2", "1/1/1111", "2/2/2222"));
    }


    /**** Test Suite for readCsv() ******/


    /**
     * Test method for {@link model.ContestDatabaseManager#readCsvFile()}.
     * @throws Exception
     */
    @Test(expected = FileNotFoundException.class)
    public void testReadCsvFile1() throws Exception {
        // Test No File Found
        ContestDatabaseManager theContestDB = new ContestDatabaseManager("Not a file name");
        theContestDB.readCsvFile();
    }

    /**
     * Test method for {@link model.ContestDatabaseManager#readCsvFile()}.
     * @throws Exception
     */
    @Test(expected = NumberFormatException.class)
    public void testReadCsvFile2() throws Exception {
        // Test Bad File
        ContestDatabaseManager theUserDB = new ContestDatabaseManager("superman.jpeg");
        theUserDB.readCsvFile();
    }

    /**
     * Test method for {@link model.ContestDatabaseManager#readCsvFile()}.
     * @throws FileNotFoundException
     */
    @Test
    public void testReadCsvFile3() throws Exception {
        // Test Good File
        ContestDatabaseManager theContestDB = new ContestDatabaseManager(CONTEST_FILE);
        theContestDB.readCsvFile();
    }



    /**** Test Suite for writeCsv() ******/


    /**
     * Test method for {@link model.ContestDatabaseManager#writeCsvFile()}.
     * @throws FileNotFoundException
     */
    @Test(expected = FileNotFoundException.class)
    public void testWriteCsvFile1() throws Exception {
        // Test No File Found
        ContestDatabaseManager theContestDB = new ContestDatabaseManager("Not a file name");
        theContestDB.writeCsvFile();
    }

    /**
     * Test method for {@link model.ContestDatabaseManager#writeCsvFile()}.
     * @throws NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void testWriteCsvFile2() throws Exception {
        // Test Bad File
        ContestDatabaseManager theUserDB = new ContestDatabaseManager("superman.jpeg");
        theUserDB.writeCsvFile();
    }

    /**
     * Test method for {@link model.ContestDatabaseManager#writeCsvFile()}.
     * @throws FileNotFoundException
     */
    @Test
    public void testWriteCsvFile3() throws Exception {
        // Test Good File
        ContestDatabaseManager theContestDB = new ContestDatabaseManager(TEST_CONTEST_FILE);
        theContestDB.readCsvFile();
        theContestDB.writeCsvFile();
    }
}