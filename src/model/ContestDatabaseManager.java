package model;

/**
 * @author liz
 */
public class ContestDatabaseManager extends DatabaseManager<Contest>{

    /** The type for the database*/
    // ContestDatabaseManager is type 1
    private final static int DATABASE_TYPE = 1;

    /** Constructor
     *
     * Precondition: The File name should be a valid string for a file path.
     *
     * @param theFileName - name of the file
     * @throws Exception*/
    public ContestDatabaseManager(String theFileName) throws Exception {
        super(theFileName);
    }

    /** reads in the file
     * @throws Exception*/
    public void readCsvFile() throws Exception {
        super.readCsvFile(DATABASE_TYPE, null);
    }

    /** Writes back to the file
     * @throws Exception*/
    public void writeCsvFile() throws Exception {
        super.writeCsvFile(DATABASE_TYPE);
    }

    /**
     * Creates and adds a Contest to the database, unless there was an existing contest by the same name.
     *
     * Precondition: startDate must land before endDate.
     *
     * @author Liz, Tabi
     * @param contestName Name of the Contest.
     * @param contestDescription Description of the Contest.
     * @param startDate Start Date for the Contest.
     * @param endDate End Date for the Contest.
     * @return true on success; false otherwise.
     */
    public boolean addContest(String contestName, String contestDescription, String startDate,
                              String endDate) throws Exception {
        if (contestNameIsUnique(contestName)) {
            int contestCounter = super.getItemCount();
            Contest contest = new Contest(++contestCounter, contestName, contestDescription, startDate, endDate);
            super.getMap().put(contest.getContestNumber(), contest);
            super.getAllItems().add(contest);
            writeCsvFile();
            return true;
        } else {
            return false;
        }
    }

    /**
     * @author Tabi
     * @param contestName Name of the Contest.
     * @return true if there is no existing contest with the given name; false otherwise.
     */
    private boolean contestNameIsUnique(String contestName) {
    	for (Contest c : super.getMap().values()) {
    		if (c.getName().equals(contestName)) {
    			return false;
    		}
    	}
    	return true;
    }
}
