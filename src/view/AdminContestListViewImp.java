package view;

import java.awt.Component;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

import model.Contest;

/**
 * A concrete implementation of a view for admins to look at all contests.
 * @author Tabi
 */
public class AdminContestListViewImp implements AdminContestListView {

	/** Panel that will be used for the admin view*/
	private final JPanel myPanel;
	/** Holds the list of contest*/
	private final JList<Contest> myList;
	/** Creates a new contests when clicked*/
	private final JButton newContestButton;
	
	/**
	 * Constructs a new AdminContestListViewImp.
	 */
	public AdminContestListViewImp() {
		myPanel = new JPanel();
		myList = new ContestList();	
		
		newContestButton = new JButton("Create new contest");
		
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		
		JPanel listContainerPanel = new JPanel();
		listContainerPanel.setLayout(new BoxLayout(listContainerPanel, BoxLayout.Y_AXIS));	
		listContainerPanel.add(ContestList.getColumnTitleHeader());
		listContainerPanel.add(new JScrollPane(myList));
		
		newContestButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		listContainerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		myPanel.add(listContainerPanel);
		myPanel.add(newContestButton);
		
	}

	@Override
	public void setContestList(Contest[] theContest) {
		myList.setListData(theContest);
	}

	@Override
	public void addNewContestButtonListener(AbstractAction theAction) {
		newContestButton.addActionListener(theAction);

	}

	@Override
	public void addEditContestListener(ListSelectionListener theListener) {
		myList.addListSelectionListener(theListener);
	}


	@Override
	public JPanel getView() {
		return myPanel;
	}

	@Override
	public NewContestForm createNewContestForm() {
		return new NewContestFormImp();
	}


}
