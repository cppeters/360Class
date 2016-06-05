package view;

import javax.swing.event.ListSelectionListener;

import model.Contest;

/**
 * this is an interface class that extends Viewable.
 * @author Lan
 * Documened by Abdul.
 */
public interface JudgeContestListView extends Viewable {

	/**
	 * Sets the Contests to display for the admin.
	 * @param theContest
	 */
	public void setContestList(Contest[] theContest);
	
	/** Add a listener to the list of contests. */
	public void addContestListListener(ListSelectionListener theListener);
	/**
	 *  clears the selection
	 */
	public void clearSelection();
}
