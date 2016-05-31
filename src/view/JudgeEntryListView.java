package view;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Contest;
import model.Entry;

public interface JudgeEntryListView extends Viewable {

	/**Adds a listener for the list of entries.*/
	public void addEntryListListener(ListSelectionListener theListener);


	public void setEntryList(Entry[] theEntry, Contest contest);

	public void addPreview(Entry seclectedEntry);
	
}
