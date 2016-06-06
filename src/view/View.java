package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The main View class. Responsible for swapping panels in central area and providing
 * controls for navigating page, as well as a static header that remains the same across pages.
 * Also has getter methods for retrieving concrete implementations of various top-level pages that
 * this View can display.
 * @author Tabi
 */
public class View {
	
	
	private final JFrame myFrame;
	/**A panel containing everything.*/
	private final JPanel contentPanel;
	/**A panel displaying persisting information at the top of the screen.*/
	private final JPanel headRegion;
	/**A button for page navigation that goes in the headRegion.*/
	private final JButton backButton;
	/**A button for logging out that goes in the headRegion.*/
	private final JButton logoutButton;
	/**The panel where the current page is displayed.*/
	private final JPanel pageRegion;


	public View() {		
		myFrame = new JFrame();
		contentPanel = new JPanel(new BorderLayout());
		headRegion = new JPanel(new BorderLayout());
		backButton = new JButton("Back");
		logoutButton = new JButton("Log out");
		pageRegion = new JPanel(new BorderLayout());
		createGui();
	}

	/** Creates the GUI for the user*/
	private void createGui() {		
		myFrame.setLocationRelativeTo(null);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPanel.add(headRegion, BorderLayout.NORTH);
		setupHeader();
		contentPanel.add(pageRegion, BorderLayout.CENTER);
		myFrame.setContentPane(contentPanel);		
		myFrame.setPreferredSize(new Dimension(500,300));
		myFrame.setResizable(false);
		myFrame.setTitle("Library Contest App");
		myFrame.setVisible(true);
	}
	
	
	/**Adds a listener for when the back button is clicked, and enables the button.*/
	public void addBackButtonListener(Action theAction) {
		backButton.addActionListener(theAction);
		backButton.setEnabled(true);
	}
	
	
	/**Removes the listeners for when the back button is clicked.*/
	public void removeBackButtonListeners() {
		for (ActionListener theAction : backButton.getActionListeners()) {
			backButton.removeActionListener(theAction);
		}
		backButton.setEnabled(false);
	}
	
	/**Enables/Disables the back button*/
	public void setBackButtonEnabled(boolean enable) {
		backButton.setEnabled(enable);
	}
	
	/**Adds a listener for when logout button is clicked, and enables the button, and enables the button
	 * and makes it visible.*/
	public void addLogoutButtonListener(Action theAction) {
		logoutButton.addActionListener(theAction);
		logoutButton.setEnabled(true);
		logoutButton.setVisible(true);
	}
	
	/**Removes the listeners for when logout button is clicked, and disables the button and
	 * makes it invisible.*/
	public void removeLogoutButtonListeners() {
		for (ActionListener theAction : logoutButton.getActionListeners()) {
			logoutButton.removeActionListener(theAction);
		}
		logoutButton.setEnabled(false);
		logoutButton.setVisible(false);
	}
	
	
	/**Displays the given Page in the pageRegion.*/
	public void showPage(Viewable thePage) {
		pageRegion.removeAll();
		pageRegion.add(thePage.getView(), BorderLayout.CENTER);
		pageRegion.revalidate();
		pageRegion.repaint();
		myFrame.pack();
	}
	
	
	/**Returns a LoginView object so the controller may interact with it.
	 * @return the login view*/
	public LoginView getLoginView() {
		return new LoginViewImp();
	}
	
	/** Returns a ContestantContestListView object so the controller may interact with it.
	 * @return the contest view*/
	public ContestantContestListView getContestantContestListView() {
		return new ContestantContestListViewImp();
	}
	
	/** Returns a ContestantContestView object so the controller may interact with it.
	 * @return the contestant view*/
	public ContestantContestView getContestantContestView() {
		return new ContestantContestViewImp();
	}

	/** Returns the admin view
	 * @return the admin view*/
	public AdminContestListView getAdminContestListView() {
		return new AdminContestListViewImp();
	}

	/** Returns the judges view
	 * @return the view judges will see*/
	public JudgeContestListView getJugdgeContestListView() {
		return new JudgeContestListViewImp();
	}

	/** Return judge view
	 * @return the judge view when they create an entry*/
	public JudgeEntryListView getJugdgeEntryListView() {
		return new JudgeEntryListViewImp();
	}

	
	/**Sets up a panel that persists at the top of the view with a back button
	 * for viewing previous pages visited.*/
	private void setupHeader() {		
		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));	
		
		// Setup banner
		JLabel appBanner = new JLabel("Clark County Libraries Contest App");
		appBanner.setHorizontalAlignment(SwingConstants.CENTER);
		appBanner.setFont(appBanner.getFont().deriveFont (20.0f));
		
		// Setup Logo
		JLabel logoLabel = new JLabel(setupLogo());
		westPanel.add(logoLabel);
		
		// Setup buttons
		backButton.setEnabled(false);
		westPanel.add(backButton);
		
		logoutButton.setEnabled(false);
		logoutButton.setVisible(false);
		eastPanel.add(logoutButton);
		
		//Add everything to the header
		headRegion.add(appBanner, BorderLayout.CENTER);
		headRegion.add(westPanel, BorderLayout.WEST);
		headRegion.add(eastPanel, BorderLayout.EAST);
	}
	
	/**
	 * Create and resize the logo.
	 * @author Lan
	 * @author Tabi
	 * @return ImageIcon the logo, resized to 62 x 62 pixels.
	 */
	private ImageIcon setupLogo() {
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("icons/logo.png"));
		imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(62, 62, Image.SCALE_DEFAULT));
		return imageIcon;
	}

	/** Resize the dimensions for the panel
	 * @param theDimension - creates dimension for the view*/
	public void reSize(Dimension theDimension){
		myFrame.setResizable(true);
		myFrame.setSize(theDimension);
		myFrame.setResizable(false);
	}
}
