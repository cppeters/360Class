package controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model.*;
import view.LoginView;
import view.View;

/**
 * Controls login/logout functionality and instantiates controllers
 * depending on the type of user that logs in.
 * @author Tabi
 */
public class MainController {


	private static final int INIT_JUDGE = -1;

	/**A reference to the UserDatabaseManager that this controller will use
	 * to look up user information.*/
	private final UserDatabaseManager myUserDBManager;
	
	/**A reference to the ContestDatabaseManager that this controller will use
	 * to look up contest information.*/
	private final ContestDatabaseManager myContestDBManager;
	
	/**A reference to the EntryDatabaseManager that this controller will use
	 * to look up entry information.*/
	private final EntryDatabaseManager myEntryDBManager;

	private final JudgeDatabaseManager myJudgeDBManager;
	
	/** A reference to the View that this controller controls. */
	private final View myView;

	/**
	 * Creates a new controller that starts up and listens to a GUI using the given models.
	 *
	 * Precondition: All DBManager params must not be null.
	 *
	 * @param theUserDBManager The User database.
	 * @param theContestDBManager The Contest database.
	 * @param theEntryDBManager The Entry database.
	 * @param theJudgeDBManager The Judge database.
     */
	public MainController(UserDatabaseManager theUserDBManager, ContestDatabaseManager theContestDBManager,
						  EntryDatabaseManager theEntryDBManager, JudgeDatabaseManager theJudgeDBManager) {
		myUserDBManager = theUserDBManager;
		myContestDBManager = theContestDBManager;
		myEntryDBManager = theEntryDBManager;
		myJudgeDBManager = theJudgeDBManager;
		
		myView = new View();
		onLoginScreen();
	}
	
	/**
	 * Controls user input and output from login screen. Upon successful login, calls
	 * the {@link #onLogin(User)} method.
	 */
	private void onLoginScreen() {		
		final LoginView aLoginView = myView.getLoginView();
		aLoginView.addLoginButtonListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				aLoginView.addLoginFailText("");
				String cardNo = aLoginView.getCardNumber();
				String pass = aLoginView.getPin();				
				
				//check if both fields have stuff in 'em
				if (cardNo.length() < 1 || !tryParseInt(cardNo)) {
					aLoginView.addLoginFailText("Please enter a valid card number. (Digits only)");
				} else if (pass.length() < 1) { // username is ok. But is the password?
					aLoginView.addLoginFailText("Please enter a password.");
				} else { // both username and password are ok.
					User aUser = myUserDBManager.checkCredentials(Integer.parseInt(cardNo), pass);
					if (aUser != null) {
						onLogin(aUser);
					} else {
						aLoginView.addLoginFailText("Invalid credentials.");
					}
				}
				
			}			
		});
		myView.showPage(aLoginView);
	}
	
	
	/**Returns true if tryToParse can be parsed into an integer.
	 * @param tryToParse - parses the string to an integer*/
	private boolean tryParseInt(String tryToParse) {
		try {
			Integer.parseInt(tryToParse);
			return true;
		} catch(NumberFormatException e) {
			return false;
		}
	}
	
	
	/**Call this when a user successfully logs in. Right now it redirects all Users
	 * to the Contestant controller, but once we have different types of users
	 * we want to send them to their appropriate controller.
	 * If somehow theUser doesn't have a type or the controller was not implemented for
	 * that type, the user is automatically logged out.
	 *
	 * Precondition: theUser must not be null.
	 *
	 * @param theUser - Checks to see which user has logged in*/
	private void onLogin(User theUser) {
		System.out.println("Success! Hello, " + theUser.getName() + "!");	
		
		myView.addLogoutButtonListener(new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				onLogout();
			}			
		});
		
		switch (UserType.checkType(theUser.getType())) {
		case ADMIN:
			new AdminController(myContestDBManager, myView);
			break;
		case CONTESTANT:
			new ContestantController(theUser, myContestDBManager, myEntryDBManager, myView);
			break;
		case JUDGE:
			 Judge aJudge = new Judge(theUser.getCardNumber(), theUser.getName(), theUser.getAge(),
					 theUser.getLoginCredential(), theUser.getType(), INIT_JUDGE, INIT_JUDGE, INIT_JUDGE,
						INIT_JUDGE);
			// If this Judge already has Contests Judge copy to newest iteration
			 for (int i : myJudgeDBManager.getMap().keySet()) {
				 if (aJudge.getCardNumber() == myJudgeDBManager.getMap().get(i).getCardNumber()) {
					 aJudge = new Judge(myJudgeDBManager.getMap().get(i));
				 }
			 }

			 new JudgeController(aJudge, myContestDBManager, myEntryDBManager, myJudgeDBManager, myView);

			break;
		default:
			onLogout(); // immediately logout because something went wrong if User didn't have one of those types.
			break;		
		}
		
	}
	
	/**Logs the user out by removing listeners to the back and logout buttons and swapping the view,
	 * because if the current view is listening to listeners from one of the user controllers, this 
	 * is how we garbage collect those listeners.*/
	private void onLogout() {
		myView.removeBackButtonListeners();
		myView.removeLogoutButtonListeners();
		onLoginScreen();
	}

	
}
