package view;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Contest;
import model.Entry;
/**
 * this is judgeEntryListView interface.
 * @author Tabi
 *	Documented by Abdul.
 */
public interface JudgeEntryListView extends Viewable {

	/**Adds a listener for the list of entries.*/
	public void addEntryListListener(ListSelectionListener theListener);

	/**
	 * sets the entry list and contest object.
	 * @param theEntry
	 * @param contest
	 */
	public void setEntryList(Entry[] theEntry, Contest contest);
	/**
	 * Displays the selected Entry.
	 * @param seclectedEntry
	 */
	public void addPreview(Entry seclectedEntry);
	
}
