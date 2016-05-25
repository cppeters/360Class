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

/** An implementation of LoginView. */
public class LoginViewImp implements LoginView {
	
	private final JPanel myPanel;
	private final JTextField myCardNoField;
	private final JTextField myPassField;
	private final JButton myLoginButton;
	private final JLabel myLoginFailMessage;

	
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
	
	private JPanel createLoginComPanel() {
		JPanel loginPanel = new JPanel();
		JPanel loginTextPanel = new JPanel();
		JPanel loginFieldPanel = new JPanel();
		loginPanel.setLayout(new BorderLayout());
		loginTextPanel.setLayout(new BoxLayout(loginTextPanel, BoxLayout.Y_AXIS));
		loginFieldPanel.setLayout(new BoxLayout(loginFieldPanel, BoxLayout.Y_AXIS));
		loginTextPanel.add(new JLabel("Card#:"));
		loginTextPanel.add(new JLabel("PIN:"));
		loginFieldPanel.add(myCardNoField);
		loginFieldPanel.add(myPassField);
		loginPanel.add(loginTextPanel, BorderLayout.WEST);
		loginPanel.add(loginFieldPanel, BorderLayout.CENTER);
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
