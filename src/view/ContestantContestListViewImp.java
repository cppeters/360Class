package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

import model.Contest;

/**
 * A view displaying a list of all contests.
 * @author Tabi
 * @author Abdulkadir S Fiqi
 */
public class ContestantContestListViewImp implements ContestantContestListView {
	/** Top level container for this class. */
	private final JPanel myPanel;
	/**	The list of Contests that have already been submitted to.*/
	private final JList<Contest> mySubMadeList;
	/**	The list of Contests that have not been submitted to.*/
	private final JList<Contest> myNoSubMadeList;
	
	/**
	 * Constructor for ContestantContestListViewImp class 
	 * that initializes components and features of this class.
	 */
	public ContestantContestListViewImp() {
		
		myPanel = new JPanel(new BorderLayout()); 
		
		JPanel listPanel = new JPanel();
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
		mySubMadeList = new ContestList(); 
		myNoSubMadeList = new ContestList();
		
		
		
		
		
		mySubMadeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		myNoSubMadeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mySubMadeList.setBorder(new EmptyBorder(5,0,0,5));
		myNoSubMadeList.setBorder(new EmptyBorder(5,0,0,5));
		
		
		
		JLabel description = new JLabel("Contests You Have Not Submitted to");
		description.setForeground(Color.black);
		listPanel.add(description);

		
		listPanel.add(ContestList.getColumnTitleHeader());
		
		listPanel.add(new JScrollPane(myNoSubMadeList));
		
		
	
		
		JLabel description1 = new JLabel("Contests You Have Already Submitted to");
		description1.setForeground(Color.black);
		listPanel.add(description1);
		listPanel.add(new JScrollPane(mySubMadeList));
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
	/**
	 * take an array of contest and set the into the contest field.	
	 */
	@Override
	public void setSubmissionMadeList(Contest[] theContests) {
		mySubMadeList.setListData(theContests);
	}
	
	/**Adding Listeners	
	 */

	@Override
	public void addNoSubmissionMadeListener(ListSelectionListener theListener) {
		myNoSubMadeList.addListSelectionListener(theListener);
	}

	/**
	 * calls the action listener 
	 */
	@Override
	public void addSubmissionMadeListener(ListSelectionListener theListener) {
		mySubMadeList.addListSelectionListener(theListener);		
	}
	
	/** Getting selections	
	 */

	@Override
	public Contest getNoSubmissionMadeSelectedEntry() {
		return myNoSubMadeList.getSelectedValue();
	}
	
	/**
	 * returns the selected value.
	 */
	@Override
	public Contest getSubmissionMadeSelectedEntry() {
		return mySubMadeList.getSelectedValue();
	}


	/**		
	 * Clearing selections	
	 */

	@Override
	public void clearNoSubmissionMadeSelection() {
		myNoSubMadeList.clearSelection();
	}

	/**
	 * Clearing the selected field.
	 */

	@Override
	public void clearSubmissionMadeSelection() {
		mySubMadeList.clearSelection();
	}
	



}
