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
 * This class is creates the components of Admin Contest List 
 * and implementing Admin contest list. 
 * @author Tabi
 * Document by Abdul.
 */
public class AdminContestListViewImp implements AdminContestListView {
	// field
	private final JPanel myPanel;
	private final JList<Contest> myList;
	private final JButton newContestButton;
	
	/**
	 * this is the constructor of this class.
	 */
	public AdminContestListViewImp() {
		myPanel = new JPanel();
		//myList = new JList<>();
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
	/**
	 * Sets the pass data into myList.
	 */
	@Override
	public void setContestList(Contest[] theContest) {
		myList.setListData(theContest);
	}
	/**
	 * adds an action listener.
	 */
	@Override
	public void addNewContestButtonListener(AbstractAction theAction) {
		newContestButton.addActionListener(theAction);

	}
	/**
	 * adds an action listener.
	 */
	@Override
	public void addEditContestListener(ListSelectionListener theListener) {
		myList.addListSelectionListener(theListener);
	}
	/**
	 * returns the getView.
	 */

	@Override
	public JPanel getView() {
		return myPanel;
	}
	/**
	 * this create a new contest From.
	 */
	@Override
	public NewContestForm createNewContestForm() {
		return new NewContestFormImp();
	}
	


}
