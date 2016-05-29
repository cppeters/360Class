package view;

import javax.swing.event.ListSelectionListener;

import model.Contest;

/**
 * 
 * @author Lan
 *
 */
public interface JudgeContestListView extends Viewable {

	/**
	 * Sets the Contests to display for the admin.
	 * @param theContest
	 */
	public void setContestList(Contest[] theContest);
	
	
	/** Add a listener to the list of contests. */
	public void addContestListListener(ListSelectionListener theListener);
	
}
