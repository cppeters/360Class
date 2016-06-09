package view;

import javax.swing.AbstractAction;

import model.Contest;

/**
 * An interface for interacting with a form for creating a new contest.
 * @author Tabi
 *
 */
public interface NewContestForm extends Viewable {

	/** Returns an array containing each of 4 strings from the form text
	 * fields as follows: {contest name, description, start date, end date}.
	 * Dates should be formatted as mm/dd/yyyy, unless something went wrong.*/
	public String[] getFormInfo();
	
	/**Add a listener for when the user submits the form.*/
	public void addSubmitListener(AbstractAction theAction);
	
	/**Sets an error message to display.
	 * @param theString the string to display.
	 * @param isError whether to display message as an error; 
	 * 			if true, display as error, if false, display as informative message.*/
	public void setMessage(String theString, boolean isError);
	
	
	
}
