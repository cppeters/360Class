package view;

import javax.swing.event.ListSelectionListener;

import model.Contest;

/**
 * Interface of a contests view for Judge.
 * 
 * @author Tabi
 */
public interface JudgeContestListView extends Viewable {

	/**
	 * Sets the Contests to display for the Judge.
	 * 
	 * @param theContest setup contest list.
	 */
	void setContestList(Contest[] theContest);
	
	/** 
	 * Add a listener to the list of contests .
	 */
	void addContestListListener(ListSelectionListener theListener);

	/** 
	 * Clearing the selected list.
	 */
	void clearSelection();
}
