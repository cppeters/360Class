package view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import model.Contest;

/**
 * A view displaying a list of all contests.
 * @author Tabi
 *
 */
public class ContestantContestListViewImp implements ContestantContestListView {
	/** Top level container for this class. */
	private final JPanel myPanel;
	/**	The list of Contests that have already been submitted to.*/
	private final JList<Contest> mySubMadeList;
	/**	The list of Contests that have not been submitted to.*/
	private final JList<Contest> myNoSubMadeList;
	
	public ContestantContestListViewImp() {
		myPanel = new JPanel(new BorderLayout()); 
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		mySubMadeList = new JList<>(); 
		myNoSubMadeList = new JList<>(); 
		mySubMadeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		myNoSubMadeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listPanel.add(new JLabel("Contests You Have Not Submitted to:"));
		listPanel.add(myNoSubMadeList);
		listPanel.add(new JLabel("Contests You Have Already Submitted to:"));
		listPanel.add(mySubMadeList);
		
		myPanel.add(listPanel);
	}
	
	

	@Override
	public JPanel getView() {
		return myPanel;
	}
	
	/*		Setting list data	*/
	
	@Override
	public void setNoSubmissionMadeList(Contest[] theContests) {
		myNoSubMadeList.setListData(theContests);
		
	}	
		
	@Override
	public void setSubmissionMadeList(Contest[] theContests) {
		mySubMadeList.setListData(theContests);
	}
	
	/*		Adding Listeners	*/

	@Override
	public void addNoSubmissionMadeListener(ListSelectionListener theListener) {
		myNoSubMadeList.addListSelectionListener(theListener);
	}

	
	@Override
	public void addSubmissionMadeListener(ListSelectionListener theListener) {
		mySubMadeList.addListSelectionListener(theListener);		
	}
	
	/*		Getting selections	*/

	@Override
	public Contest getNoSubmissionMadeSelectedEntry() {
		return myNoSubMadeList.getSelectedValue();
	}
	
	@Override
	public Contest getSubmissionMadeSelectedEntry() {
		return mySubMadeList.getSelectedValue();
	}


	/*		Clearing selections	*/

	@Override
	public void clearNoSubmissionMadeSelection() {
		myNoSubMadeList.clearSelection();
	}



	@Override
	public void clearSubmissionMadeSelection() {
		mySubMadeList.clearSelection();
	}
	



}
