package view;

import javax.swing.AbstractAction;
import javax.swing.event.ListSelectionListener;

import model.Contest;

public interface AdminContestListView extends Viewable {

	/**
	 * Sets the Contests to display for the admin.
	 * @param theContest
	 */
	public void setContestList(Contest[] theContest);
	
	/**
	 * Sets the listener for the button for wanting to add a new contest.
	 * @param theAction 
	 */
	public void addNewContestButtonListener(AbstractAction theAction);
	
	/**
	 * For when user clicks on one of the existing contests.
	 * @param theListener
	 */
	public void addEditContestListener(ListSelectionListener theListener);
	
	
	/**
	 * Creates a new form for creating a contest.
	 * @return
	 */
	public NewContestForm createNewContestForm();
	
}
