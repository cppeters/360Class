package view;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Contest;

/**A view for submitting an entry to a certain contest.*/
public class ContestantContestViewImp implements ContestantContestView {
	
	private final JPanel myPanel;
	private final JButton myBrowseButton;
	
	public ContestantContestViewImp() {
		myPanel = new JPanel();
		myBrowseButton = new JButton("Browse...");
		myPanel.add(myBrowseButton);
	}
	
	

	@Override
	public JPanel getView() {
		return myPanel;
	}



	@Override
	public void addBrowseButtonListener(AbstractAction theAction) {
		myBrowseButton.addActionListener(theAction);
	}



	@Override
	public void setContestName(String theContestName) {
		// TODO Auto-generated method stub
		
	}

}
