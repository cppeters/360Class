package view;

import javax.swing.event.ListSelectionListener;

/**
 * 
 * @author Lan
 *
 */
public interface JudgeContestListView extends Viewable {

	/** Add a listener to the list of contests. */
	public void addContestListListiner(ListSelectionListener theListener);
	
}
