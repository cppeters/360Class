package test;

import model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by cppeters on 6/2/2016.
 * @author Casey
 */
public class JudgeTest {
    private static final String DB_DIRECTORY = "database" + File.separator;
    private static final String USER_FILE = DB_DIRECTORY  + "User.csv";
    private static final String ENTRY_FILE = DB_DIRECTORY + "Entries.csv";
    private static final String TEST_JUDGE_FILE = "TestJudge.csv";
    private JudgeDatabaseManager myJudgeDB;
    private Judge myJudge;

    @Before
    public void setUp() throws Exception {

        EntryDatabaseManager theEntryDB = new EntryDatabaseManager(ENTRY_FILE);
        theEntryDB.readCsvFile();

        UserDatabaseManager theUserDB = new UserDatabaseManager(USER_FILE, theEntryDB);
        theUserDB.readCsvFile();

        myJudgeDB = new JudgeDatabaseManager(TEST_JUDGE_FILE, theEntryDB);
        myJudgeDB.readCsvFile();
        String newJudgeName = "Steve Testman";
        int theCardNumber = theUserDB.getItemCount() + 1;
        myJudge = new Judge(theCardNumber, newJudgeName, 28, "steve", "JUDGE",
                8, 7, 8, 9);
    }
    @Test
    public void addContestJudged() throws Exception {
        // Total amount of contests by this Judge
        int total = myJudge.getContestsJudged().size();

        // Add a new Contest Judged by using setters like it would in GUI then call addContestJudged()
        myJudge.setMyContestNumber(10);
        myJudge.setMyFirst(100);
        myJudge.setMySecond(101);
        myJudge.setMyThird(102);
        myJudge.addContestJudged(myJudgeDB);

        // Check if Judged contests size increased
        assertEquals(total + 1, myJudge.getContestsJudged().size());

        List<Integer> intList = new ArrayList<>();
        intList.add(100);
        intList.add(101);
        intList.add(102);

        // Check if the update judged contest was updated to myJudge List
        assertEquals(intList, myJudge.getEntryNumbers());

        // Check if the judged contest was added to myJudge Map
        assertEquals(myJudge.getEntryNumbers(), myJudge.getContestsJudged().get(myJudge.getContestNumber()));

        // Check if the judged contest was added to myJudgeDB Map
        assertEquals(myJudge, myJudgeDB.getMap().get(myJudge.getCardNumber()));
    }

    @Test
    public void updateContestJudged() throws Exception {
        // Total amount of contests by this Judge
        int total = myJudge.getContestsJudged().size();

        // Update a Contest Judged by using setters like it would in GUI then call updateContestJudged()
        myJudge.setMyContestNumber(8);
        myJudge.setMyFirst(1000);
        myJudge.setMySecond(1010);
        myJudge.setMyThird(1020);
        myJudge.updateContestJudged(myJudgeDB);

        // Check if Judged contests size increased (Should have only replaced)
        assertNotEquals(total + 1, myJudge.getContestsJudged().size());

        List<Integer> intList = new ArrayList<>();
        intList.add(1000);
        intList.add(1010);
        intList.add(1020);

        // Check if the update judged contest was updated to myJudge List*/
        assertEquals(intList, myJudge.getEntryNumbers());

        // Check if the new judged entry numbers were inserted in place of the old
        assertEquals(myJudge.getEntryNumbers(), myJudge.getContestsJudged().get(myJudge.getContestNumber()));

        // Check if theNewJudge was updated in place of theOldJudge in JudgeDB Map
        for (int i : myJudgeDB.getMap().keySet()) {
            if (myJudgeDB.getMap().get(i).getCardNumber() == myJudge.getCardNumber() &&
                    myJudgeDB.getMap().get(i).getContestNumber() == myJudge.getContestNumber())
                assertEquals(myJudge, myJudgeDB.getMap().get(i));
        }
    }
}