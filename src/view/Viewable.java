package view;

import javax.swing.JPanel;

/**
 * Classes implementing this interface can be displayed in
 * View by overriding the getView() method.
 * @author Tabi
 */
public interface Viewable {

	/** Returns the JPanel for displaying this Viewable. */
	public JPanel getView();
	
}
