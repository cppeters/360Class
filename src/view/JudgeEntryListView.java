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
 * @author Lan
 * @author Casey
 */
public interface JudgeEntryListView extends Viewable {

	/** Adds a listener for the list of entries.*/
	void addEntryListListener(ListSelectionListener theListener);

	/**
	 * Sets the entry list.
	 *
	 * Precondition: contest must not be null.
	 *
	 * @param theEntry - The entry that will be added
	 * @param theContest - the contest that the entry will be added to. */
	void setEntryList(Entry[] theEntry, Contest theContest);

	/**
	 * Adds the selectedEntry to a preview.
	 * Precondition: theSelectedEntry must not be null.	 *
	 * @param theSelectedEntry - The entry that is selected. */
	void addPreview(Entry theSelectedEntry);

	/** Set the action for then the submit entry button is clicked. */
	void addSubmitButtonListener(AbstractAction theAction);

	/**
	 * Add the contest judged.
	 *
	 * Precondition: theJudge, theJudgeDB, theContest must not be null.
	 *
	 * @param theJudge The Judge logged in.
	 * @param theJudgeDB The Judge database.
	 * @param theContest The contest to be judged.
	 * @throws Exception
     */
	void addJudged(Judge theJudge, JudgeDatabaseManager theJudgeDB, Contest theContest) throws Exception;

	/**
	 * Update the Contest Judged by this Judge.
	 *
	 * Precondition: theJudge, theJudgeDB, theContest must not be null.
	 *
	 * @param theJudge The Judge logged in.
	 * @param theJudgeDB The Judge database.
	 * @param theContest The Contest to be updated by theJudge.
	 * @throws Exception
     */
	void updateJudged(Judge theJudge, JudgeDatabaseManager theJudgeDB, Contest theContest) throws Exception;

	/**
	 * If a Contest has already been judged by theJudge gets and sets vaules.
	 *
	 * Precondition: theJudge, theContest, and theEntries must not be null.
	 *
	 * @param theJudge The Judge logged in.
	 * @param theContest The Contest to be judged.
	 * @param theEntries The previous entries for theJudge.
	 * @throws Exception
     */
	void setJudgedContest(Judge theJudge, Contest theContest, Entry[] theEntries) throws Exception;

	/**
	 * Check if there was submission success.
	 *
	 * @return True if submission success.
     */
	Boolean getSubmitSuccess();
}
