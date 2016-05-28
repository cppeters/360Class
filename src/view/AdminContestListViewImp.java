package view;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

import model.Contest;

public class AdminContestListViewImp implements AdminContestListView {
	
	private final JPanel myPanel;
	private final JList<Contest> myList;
	private final JButton newContestButton;
	
	public AdminContestListViewImp() {
		myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myList = new JList<>();
		newContestButton = new JButton("Create new contest");
		myPanel.add(new JScrollPane(myList));
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
