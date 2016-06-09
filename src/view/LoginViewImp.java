package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/** An implementation of LoginView. 
 * @author Tabi
 */
public class LoginViewImp implements LoginView {

	/** Panel for the login*/
	private final JPanel myPanel;
	/** Place to allow user to enter card number*/
	private final JTextField myCardNoField;

	/** Place to allow user to enter password*/
	private final JTextField myPassField;
	/** Button used to login*/
	private final JButton myLoginButton;
	/** Error when logging in*/
	private final JLabel myLoginFailMessage;

	/** Creates a new concrete implementation of LoginView.*/
	public LoginViewImp() {
		myPanel = new JPanel(new GridBagLayout());		

		myCardNoField = new JTextField();
		myPassField = new JPasswordField();
		myLoginButton = new JButton("Login");
		myLoginFailMessage = new JLabel();
		myLoginFailMessage.setForeground(Color.red);
		
		JPanel fields = new JPanel();
		fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
	
		
		fields.add(createLoginComPanel());
		myLoginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		myLoginFailMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		fields.add(myLoginButton);
		fields.add(myLoginFailMessage);
		fields.setBorder(BorderFactory.createTitledBorder("Login"));
		
		GridBagConstraints c = new GridBagConstraints();		
		// make contents fill width of panel:
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1; 
		c.weighty= 0;
		myPanel.add(fields, c);
		
		// Filler component to push login panel to top
		c.weighty= 1;
		c.gridy = 1;
		myPanel.add(new JPanel(new BorderLayout()), c);
	}

	/** Creates the login panel. */
	private JPanel createLoginComPanel() {
		JPanel loginPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JTextField[] fields = new JTextField[]{myCardNoField, myPassField};
		JLabel[] labels = new JLabel[]{new JLabel("Card No. ", JLabel.RIGHT), new JLabel("PIN ", JLabel.RIGHT)};
		
		for (int i = 0; i < fields.length; i++) {
			c.weightx = 0; c.gridx = 0; c.gridy = i; c.anchor = GridBagConstraints.LINE_END;
			loginPanel.add(labels[i], c);
			c.gridx = 1; c.weightx = 1; c.anchor = GridBagConstraints.LINE_START;
			loginPanel.add(fields[i], c);
		}		
		
		return loginPanel;
	}
	

	@Override
	public JPanel getView() {
		return myPanel;
	}

	@Override
	public void addLoginButtonListener(AbstractAction theAction) {
		myLoginButton.addActionListener(theAction);
	}

	@Override
	public String getCardNumber() {
		String cardNo = myCardNoField.getText();
		return cardNo != null ? cardNo : "";
	}

	@Override
	public String getPin() {
		String pin = myPassField.getText();
		return pin != null ? pin : "";
	}


	@Override
	public void addLoginFailText(String theText) {
		myLoginFailMessage.setText(theText);
	}
	
}
