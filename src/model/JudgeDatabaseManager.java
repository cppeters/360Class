/**
 * 
 */
package model;


/**
 * @author Casey
 *
 */
public class JudgeDatabaseManager extends DatabaseManager <Judge>{

	/** The type for the judge db*/
	// JudgeDatabaseManager is type 4
	private final static int DATABASE_TYPE = 4;

	/** The entry database*/
	private EntryDatabaseManager myEntryDB;

	/** Constructor()
	 *
	 * Precondition: The File name should be a valid string for a file path. theEntryDatabase must not be null.
	 *
	 * @param theFileName - path for the file
	 * @param theEntryDatabase - The entries database
	 * @exception Exception*/
	public JudgeDatabaseManager(String theFileName, EntryDatabaseManager theEntryDatabase) throws Exception {
		super(theFileName);
		myEntryDB = theEntryDatabase;
	}

	/** Reads in the file to create the map
	 * @exception Exception*/
	public void readCsvFile() throws Exception {
		super.readCsvFile(DATABASE_TYPE, myEntryDB);
	}

	/** Writes new judged entries to the file
	 * @exception Exception*/
	public void writeCsvFile() throws Exception {
		super.writeCsvFile(DATABASE_TYPE);
	}

	/** Creating a new judge
	 * @param theJudge - who is judging
	 * @param theCardNumber - what is the judges card number*/
	public void addJudge(Judge theJudge, int theCardNumber) throws Exception {

		// Add to the Map
		super.getMap().put(theCardNumber, theJudge);

		// Add to the List
		super.getAllItems().add(theJudge);

		// Save to file
		writeCsvFile();
	}

	/** Updating the judges contest
	 * @param theJudge - the judge
	 * @param theContestNumber - for this contest*/
	public void updateJudge(Judge theJudge, int theContestNumber) throws Exception {

		// Update the Map
		for (int i : super.getMap().keySet()) {
			if (super.getMap().get(i).getCardNumber() == theJudge.getCardNumber() &&
					super.getMap().get(i).getContestNumber() == theContestNumber)
				super.getMap().replace(i, theJudge);
		}

		// Update the List
		for (int i = 0; i < super.getAllItems().size(); i++) {
			if (super.getAllItems().get(i).getCardNumber() == theJudge.getCardNumber() &&
					super.getAllItems().get(i).getContestNumber() == theContestNumber) {
				super.getAllItems().set(i, theJudge);
			}
		}

		// Save changes
		writeCsvFile();
	}
}
