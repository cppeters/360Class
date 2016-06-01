package test;

import model.ContestDatabaseManager;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Created by cppeters on 6/1/2016.
 * @author Casey
 *
 * DatabaseManager is an abstract class so will be using ContestDatabaseManager to conduct updateCount(). All
 * readCsv() and writeCsv methods will be tested in the DatabaseManagers that extend DatabaseManager abstract.
 */
public class DatabaseManagerTest {

    // Instance Fields
    private static final String CONTEST_FILE = "Contests.csv";
    private ContestDatabaseManager myContestDB;

    /**
     * Setup method for testing.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        myContestDB = new ContestDatabaseManager(CONTEST_FILE);
        myContestDB.readCsvFile();
    }

    /**
     * Test method for {@link model.DatabaseManager#updateCount()}.
     * @throws Exception
     */
    @Test
    public void updateCount() throws Exception {

        int count = myContestDB.getItemCount();

        // Check entries size
        assertEquals(count, myContestDB.getItemCount());

        // Increment the counter by 1
        myContestDB.updateCount();

        // Check if count size increased
        assertEquals(count + 1, myContestDB.getItemCount());
    }
}