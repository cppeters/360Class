package model;

import java.util.*;

/**
 * @author Casey
 */
public class Judge extends User {

    // Instance Fields
    /** Map that holds all the contests*/
    private Map<Integer, List<Integer>> myContests;         //Map<ContestNumber, List of Judged Entries>

    /** List of entry numbers*/
    List<Integer> myEntryNumbers;

    /** The contest numbers*/
    private int myContestNumber;

    /** First place*/
    private int myFirst;

    /** Second place*/
    private int mySecond;

    /** Third place*/
    private int myThird;

    /** Constructor()
     * @param theAge - age of the contestant
     * @param theCardNumber - card number for the User
     * @param theContestNumber - contest number entry belongs to
     * @param theName - name of User
     * @param theLoginCredential - credential log in
     * @param theFirst - first place
     * @param theSecond - second place
     * @param theThird - third place
     * @param theType - type of User*/
    public Judge(int theCardNumber, String theName, int theAge, String theLoginCredential,
                 String theType, int theContestNumber, int theFirst, int theSecond, int theThird) {
        super(theCardNumber, theName, theAge, theLoginCredential, theType);
    	myContests = new HashMap<>();
        myContestNumber = theContestNumber;
        myFirst = theFirst;
        mySecond = theSecond;
        myThird = theThird;
        myEntryNumbers = new ArrayList<>();
       // myContests.put(myContestNumber, myEntryNumbers);
       // addToList();
    }

    // Copy Constructor
    /** Constructor()
     * @param theOtherJudge - the judge to copy
     * */
    public Judge(Judge theOtherJudge) {
        super(theOtherJudge.getCardNumber(), theOtherJudge.getName(), theOtherJudge.getAge(),
                theOtherJudge.getLoginCredential(), theOtherJudge.getType());
        this.myContests = theOtherJudge.getContestsJudged();
        this.myContestNumber = theOtherJudge.getContestNumber();
        this.myFirst = theOtherJudge.getMyFirst();
        this.mySecond = theOtherJudge.getMySecond();
        this.myThird = theOtherJudge.getMyThird();
        this.myEntryNumbers = new ArrayList<>();
        //this.myContests.put(myContestNumber, myEntryNumbers);
      //  addToList();
    }

    /**
     * Adding places to the list
     * */
    private void addToList() {
        // Clear and Add to List
        myEntryNumbers.clear();
        myEntryNumbers.add(myFirst);
        myEntryNumbers.add(mySecond);
        myEntryNumbers.add(myThird);
    }

    /**
     * Adding a contest that has been judged. Must use appropriate entry and contest setters before calling this method.
     * @param theJudgeDB - creating map of entries that have been judged
     * @exception Exception*/
    public void addContestJudged(JudgeDatabaseManager theJudgeDB) throws Exception {

        // Add the new entries that have been set to List
        addToList();

        // Add to Map after contest number has been set
        myContests.put(myContestNumber, myEntryNumbers);

        // Add to JudgeDB
        theJudgeDB.addJudge(this, super.getCardNumber());
    }

    /**
     * Updating a contest that already has been judged
     * Must use appropriate entry and contest setters before calling this method.
     * @param theJudgeDB the database for the judged entries
     * */
    public void updateContestJudged(JudgeDatabaseManager theJudgeDB) throws Exception {

        // Add the new entries that have been set to List
        addToList();

        // Update Map
        myContests.replace(myContestNumber, myEntryNumbers);

        // Update JudgeDB
        theJudgeDB.updateJudge(this, myContestNumber);
    }

    /** What contests have been judged?
     * @return the contests that have been judged*/
    public Map<Integer, List<Integer>> getContestsJudged() {
        return myContests;
    }

    /** What is the contest number?
     * @return the number of the contest*/
    public int getContestNumber() {
        return myContestNumber;
    }

    /** Adding a contest number
     * @param theContestNumber - contest number that belongs to the judge*/
    public void setMyContestNumber(int theContestNumber) {
        myContestNumber = theContestNumber;
    }

    /** Adding my first choice
     * @param theFirst - first place*/
    public void setMyFirst(int theFirst) { myFirst = theFirst; }

    /** Adding my second choice
     * @param theSecond - second place*/
    public void setMySecond(int theSecond) { mySecond = theSecond; }

    /** Adding my third choice
     * @param theThird - third place*/
    public void setMyThird (int theThird) { myThird = theThird; }

    /** What is my first place choice?
     * @return first place*/
    public int getMyFirst() { return myFirst; }

    /** What is my second place choice?
     * @return second place*/
    public int getMySecond() { return mySecond; }

    /** What is my third place choice?
     * @return third place*/
    public int getMyThird () { return myThird; }

    /** Returns the list of contests ids
     * @return contest entry numbers*/
    public List<Integer> getEntryNumbers() {
        return myEntryNumbers;
    }


    @Override
    public boolean equals(Object theObject) {
        boolean result = false;
        if(theObject == null) result = false;
        final Judge theOther = (Judge) theObject;
        if (this.getClass().equals(theObject.getClass())  &&
                this.getName().equals(theOther.getName()) &&
                this.getCardNumber() == theOther.getCardNumber() &&
                this.getAge() == theOther.getAge() &&
                this.getLoginCredential().equals(theOther.getLoginCredential()) &&
                this.getType().equals(theOther.getType()) &&
                Arrays.equals(this.getEntries().toArray(), theOther.getEntries().toArray()) &&
                this.toString().equals(theObject.toString()))
            result = true;
        return result;
    }

    @Override
    public int hashCode(){
        int result = 0;
        result = (int) this.getCardNumber() / 11;
        return result;
    }

    @Override
    public String toString() {

        return  super.getCardNumber() + "," + super.getName() + "," + super.getAge() +
                "," + super.getLoginCredential() + "," + super.getType() + "," +
                myContestNumber + "," + myFirst + "," + mySecond + "," + myThird;
    }
}
