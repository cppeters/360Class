package view;

import javax.swing.AbstractAction;

import model.Contest;

public interface ContestantContestView extends Viewable {
	
	/**Set the action for when the browse for files button is clicked.*/
	public void addBrowseButtonListener(AbstractAction theAction);
	
	/**Sets a name for the contest to display.*/
	public void setContestName(String theContestName);
	
	// TODO method for opening and getting input from file chooser ...
	
}
