package view;

import javax.swing.event.ListSelectionListener;

import model.Contest;
import model.Entry;

public interface JudgeEntryListView extends Viewable {

	/**Adds a listener for the list of entries.*/
	public void addEntryListListener(ListSelectionListener theListener);

	/**
	 * @param theEntry - the entry that will be added
	 * @param contest - the contest that the entry will be added to*/
	public void setEntryList(Entry[] theEntry, Contest contest);

	/**
	 * @param seclectedEntry - the entry that is selected*/
	public void addPreview(Entry seclectedEntry);
	
}
