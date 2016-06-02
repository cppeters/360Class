/**
 * 
 */
package model;


/**
 * @author Casey
 *
 */
public class JudgeDatabaseManager extends DatabaseManager <Judge>{

	// JudgeDatabaseManager is type 4
	private final static int DATABASE_TYPE = 4;

	public JudgeDatabaseManager(String theFileName) throws Exception {
		super(theFileName);
	}

	public void readCsvFile() throws Exception {
		super.readCsvFile(DATABASE_TYPE, null);
	}

	public void writeCsvFile() throws Exception {
		super.writeCsvFile(DATABASE_TYPE);
	}

	public void addJudge(Judge theJudge, int theContestNumber) throws Exception {
		// Add to the Map
		super.getMap().put(theContestNumber, theJudge);

		// Add to the List
		super.getAllItems().add(theJudge);

		// Save to file
		writeCsvFile();
	}

	public void updateJudge(Judge theJudge, int theContestNumber) throws Exception {
		// Update the Map
		super.getMap().replace(theContestNumber, theJudge);

		// Update the List
		super.getAllItems().set(super.getAllItems().indexOf(theJudge), theJudge);

		// Save changes
		writeCsvFile();
	}


}
