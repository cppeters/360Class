package view;

import java.io.IOException;

import javax.swing.AbstractAction;

import model.Contest;
import model.EntryDatabaseManager;
import model.User;

public interface ContestantContestView extends Viewable {
	
	/**Set the action for when the browse for files button is clicked.*/
	public void addBrowseButtonListener(AbstractAction theAction);
	
	/** Set the action for then the submit entry button is clicked. */
	public void addSubmitButtonListener(AbstractAction theAction);
	
	/**Sets a name for the contest to display.*/
	public void setContestName(String theContestName);	
	
	/** Opening and getting input from file chooser for entry submission 
	 * @throws IOException */
	public void setEntryFileName() throws IOException;
	
	/** Submit Entry to Contest 
	 * @return */
	public Boolean submitNewEntry(User theUser, EntryDatabaseManager theEntryDataBaseManager,
			Contest theContest);
	
}
