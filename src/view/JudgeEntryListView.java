package view;

import javax.swing.AbstractAction;
import javax.swing.event.ListSelectionListener;

import model.Contest;
import model.Entry;
import model.Judge;
import model.JudgeDatabaseManager;

/**
 * Interface of a entries view for Judge.
 * 
 * @author Casey
 * @author Lan
 *
 */
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

	/** Set the action for then the submit entry button is clicked. */
	public void addSubmitButtonListener(AbstractAction theAction);

	/** Add the contest judged */
	public void addJudged(Judge theJudge, JudgeDatabaseManager theJudgeDB, Contest theContest) throws Exception;

	/** Add the contest judged */
	public void updateJudged(Judge theJudge, JudgeDatabaseManager theJudgeDB, Contest theContest) throws Exception;

	/** If a Contest has already been judged by theJudge set vaules */
	void setJudgedContest(Judge theJudge, Contest theContest, Entry[] theEntries) throws Exception;
}
