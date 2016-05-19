package view;

import javax.swing.AbstractAction;

public interface LoginView extends Viewable {

	/**Add a listener for when user selects to log in.*/
	public void addLoginButtonListener(AbstractAction theAction);
	
	/**Returns card number entered by user or the empty string if nothing was entered.*/
	public String getCardNumber();
	
	/**Returns pin entered by user or the empty string if nothing was entered.*/
	public String getPin();
	
	/**Sets the text to display when login failed.*/
	public void addLoginFailText(String theText);
	
}
