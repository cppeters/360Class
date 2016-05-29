package view;

import javax.swing.event.ListSelectionListener;

public interface JudgeEntryListView extends Viewable {

	/**Adds a listener for the list of entries.*/
	public void addEntryListListiner(ListSelectionListener theListener);
	
}
