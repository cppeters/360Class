package view;

import javax.swing.event.ListSelectionListener;

import model.Contest;

public interface ContestantContestListView extends Viewable {
	
	/**Sets the list of Contests to display as contests to which the
	 * current user has not made a submission.*/
	public void setNoSubmissionMadeList(Contest[] theContests);
	
	/**Sets a listener for when user selects a Contest from the list
	 * of Contests they have not made a submission to.*/
	public void addNoSubmissionMadeListener(ListSelectionListener theListener);

	
	/**Sets the list of Contests to display as contests to which the
	 * current user has already made a submission.*/
	public void setSubmissionMadeList(Contest[] theContests);
	
	/**Sets a listener for when user selects a Contest from the list
	 * of Contests they have already made a submission to.*/
	public void addSubmissionMadeListener(ListSelectionListener theListener);
	
	/**Returns the contest that the User has selected from no submissions list, 
	 * or null if none are selected.*/
	public Contest getNoSubmissionMadeSelectedEntry();
	
	/**Returns the contest that the User has selected from submitted to list, 
	 * or null if none are selected.*/
	public Contest getSubmissionMadeSelectedEntry();
	
	/**Clears the list selection in the no submissions made list.*/
	public void clearNoSubmissionMadeSelection();
	
	/**Clears the list selection in the submissions made list.*/
	public void clearSubmissionMadeSelection();


}
