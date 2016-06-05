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

/** LogingViewIm implements LoginView interface class.
 *  It is also creates the components and frame of 
 *  login panel. That has logging Text field and password.
 * 	@author Tabi
 * 	Documented by Abdul.
 * 
 */
public class LoginViewImp implements LoginView {
	// fields.
	private final JPanel myPanel;
	private final JTextField myCardNoField;
	private final JTextField myPassField;
	private final JButton myLoginButton;
	private final JLabel myLoginFailMessage;

	// constructors.
	public LoginViewImp() {
		// initializing components.
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
	
	/**
	 * this method create the labels and returns a 
	 * label components and its layout.
	 * @return
	 */
	private JPanel createLoginComPanel() {
		JPanel loginPanel = new JPanel();
		JPanel cardLogin = new JPanel();
		JPanel pinLogin = new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
		cardLogin.setLayout(new BorderLayout());
		pinLogin.setLayout(new BorderLayout());
		cardLogin.add(new JLabel("Card#: "), BorderLayout.WEST);
		pinLogin.add(new JLabel("PIN:      "),BorderLayout.WEST);
		cardLogin.add(myCardNoField);
		pinLogin.add(myPassField);
		loginPanel.add(cardLogin, BorderLayout.WEST);
		loginPanel.add(pinLogin, BorderLayout.WEST);
		return loginPanel;
	}
	/**
	 * Returns myPanel.
	 */
	@Override
	public JPanel getView() {
		return myPanel;
	}
	/**
	 * Calls a listener method to do action clicking login button.
	 */
	@Override
	public void addLoginButtonListener(AbstractAction theAction) {
		myLoginButton.addActionListener(theAction);
	}
	/**
	 * getCarnumber returns string of data from the 
	 * myCarNoField text field if the Card No is not null.
	 */
	@Override
	public String getCardNumber() {
		String cardNo = myCardNoField.getText();
		return cardNo != null ? cardNo : "";
	}
	/**
	 * getPin returns string of data from the 
	 * myPassField text field if the Card No is not null.
	 */
	@Override
	public String getPin() {
		String pin = myPassField.getText();
		return pin != null ? pin : "";
	}
	
	/**
	 * this methods pop up message if the login process fails.
	 */
	@Override
	public void addLoginFailText(String theText) {
		myLoginFailMessage.setText(theText);
	}
	
}
