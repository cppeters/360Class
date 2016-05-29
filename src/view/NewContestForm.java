package view;

import javax.swing.AbstractAction;

import model.Contest;

public interface NewContestForm extends Viewable {

	/** Returns an array containing each of 4 strings from the form text
	 * fields as follows: {contest name, description, start date, end date}.
	 * Dates should be formatted as mm/dd/yyyy, unless something went wrong.*/
	public String[] getFormInfo();
	
	/**Add a listener for when the user submits the form.*/
	public void addSubmitListener(AbstractAction theAction);
	
	/**Sets an error message to display.*/
	public void setMessage(String theString);
	
	
	
}
