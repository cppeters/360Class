package view;

import java.io.IOException;

import javax.swing.AbstractAction;

import model.Contest;
import model.EntryDatabaseManager;
import model.User;

/** 
 * @author Casey
 */
public interface ContestantContestView extends Viewable {
	
	/**Set the action for when the browse for files button is clicked.*/
	void addBrowseButtonListener(AbstractAction theAction);
	
	/** Set the action for then the submit entry button is clicked. */
	void addSubmitButtonListener(AbstractAction theAction);
	
	/**Sets a name for the contest to display.*/
	void setContestName(String theContestName);
	
	/** Opening and getting input from file chooser for entry submission 
	 * @return Boolean True if successful.
	 * @throws IOException
	 */
	Boolean setEntryFileName() throws IOException;
	
	/** Submit Entry to Contest
	 * Precondition: theUser, theContest, and theEntryDatabaseManager must not be null.
	 * @return Boolean True if a successful submission.
	 */
	Boolean submitNewEntry(User theUser, EntryDatabaseManager theEntryDataBaseManager,
			Contest theContest);

	/** If submission has been made for contest update CCV with pertinent info. 	 *
	 * Precondition: theUser, theContest must not be null.	 *
	 * @throws IOException */
	void subMade(User theUser, Contest theContest) throws IOException;
	
}
