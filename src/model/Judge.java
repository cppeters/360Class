package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Casey
 */
public class Judge extends User {

    // Instance Fields
    private Map<Integer, List<Entry>> myContests;
    private int myContestNumber;
	
    public Judge(int theCardNumber, String theName, int theAge, String theLoginCredential,
                 String theType) {
        super(theCardNumber, theName, theAge, theLoginCredential, theType);
    	myContests = new HashMap<>();
        myContestNumber = -1;
    }

    public void setMyContestNumber(int theContestNumber) {
        myContestNumber = theContestNumber;
    }

    public void buildJudge(int theFirstPlace, int theSecondPlace, int theThirdPlace,
                    EntryDatabaseManager theEntryDB) {
        // Build List
        List<Entry> theEntryJudgedList = new ArrayList<>();
        theEntryJudgedList.add(theEntryDB.getMap().get(theFirstPlace));
        theEntryJudgedList.add(theEntryDB.getMap().get(theSecondPlace));
        theEntryJudgedList.add(theEntryDB.getMap().get(theThirdPlace));

        // Add to Map
        myContests.put(myContestNumber, theEntryJudgedList);
    }


    public void addContestJudged(int theFirstPlace, int theSecondPlace,
                                 int theThirdPlace, EntryDatabaseManager theEntryDB,
                                 JudgeDatabaseManager theJudgeDB) throws Exception {
        // Build the Judge
        buildJudge(theFirstPlace, theSecondPlace,
        theThirdPlace, theEntryDB);

        // Add to JudgeDB
        theJudgeDB.addJudge(this, myContestNumber);
    }

    public void updateContestJudged(Entry theFirst, Entry theSecond, Entry theThird,
                                    JudgeDatabaseManager theJudgeDB) throws Exception {
        // Build List
        List<Entry> theEntryJudgedList = new ArrayList<>();
        theEntryJudgedList.add(theFirst);
        theEntryJudgedList.add(theSecond);
        theEntryJudgedList.add(theThird);

        // Update Map
        for (int i : myContests.keySet()) {
            if (i == myContestNumber) {
                myContests.replace(i, theEntryJudgedList);
            }
        }

        // Update JudgeDB
        theJudgeDB.updateJudge(this, myContestNumber);
    }

    public Map<Integer, List<Entry>> getContestsJudged() {
        return myContests;
    }


    public int getContestNumber() {
        return myContestNumber;
    }

}
