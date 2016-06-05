package controller;

import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;

import model.Contest;
import model.ContestDatabaseManager;
import model.User;
import view.AdminContestListView;
import view.NewContestForm;
import view.View;
import view.Viewable;

/**
 * An admin can view contests and add new ones.
 * @author Tabi
 *
 */
public class AdminController {

	/** Which view to use*/
	private final View myView;
	private final User myUser;
	/** The database for the contests*/
	private final ContestDatabaseManager myContestDBManager; 
	
	/**List of all views that have been displayed to this user since this controller
	 * was created.*/
	private final LinkedList<Viewable> viewHistory;

	/** Constructor()
	 * @param theUser - which user logged in
	 * @param theConDatMan - the contest database
	 * @param theView - which view to use*/
	public AdminController(User theUser, ContestDatabaseManager theConDatMan,  View theView) {
		myView = theView;
		myUser = theUser;
		myContestDBManager = theConDatMan; 
		viewHistory = new LinkedList<>();
		setupBackFunctionality();
		setupListView();
	}

	/** Adding the back button functionality*/
	private void setupBackFunctionality() {
		myView.addBackButtonListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("Clicked back.");
				if (!viewHistory.isEmpty() && viewHistory.getLast() != null) {
					myView.showPage(viewHistory.pop());	
				//	System.out.println("Swapped page.");
				}
				
				if (viewHistory.isEmpty()) {
					myView.setBackButtonEnabled(false);
				}
			}			
		});
		
		if (viewHistory.isEmpty()) {
			myView.setBackButtonEnabled(false);
		}
	}

	/** Adding the history of the view
	 * @param theViewable - what has been viewed*/
	private void addToHistory(Viewable theViewable) {
		viewHistory.add(theViewable);
		myView.setBackButtonEnabled(true);
	}

	/** Setting up the list for the view*/
	private void setupListView() {
		final AdminContestListView listView = myView.getAdminContestListView();
		listView.setContestList(allContests());
		listView.addNewContestButtonListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onAddContestView(listView);
				addToHistory(listView);
			}
			
		});
		myView.showPage(listView);
	}

	/** What are the contests?
	 * @return all the contests*/
	private Contest[] allContests() {
		List<Contest> contests = myContestDBManager.getAllItems();
		return contests.toArray(new Contest[contests.size()]);
	}
	
	/**
	 * Adding a new view
	 * @param theListView - the list of views that have been used
	 */
	private void onAddContestView(final AdminContestListView theListView) {
		final NewContestForm theForm = theListView.createNewContestForm();
		theForm.addSubmitListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					onAddingContest(theForm, theListView);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		myView.showPage(theForm);
	}

	
	
	/**
	 * To be called when the user submits a new contest form.
	 * Adds new contest to db and updates list view.
	 * @param theForm - form of the contest
	 * @param theListView - list of the views seen
	 */
	private void onAddingContest(NewContestForm theForm, AdminContestListView theListView) throws Exception {
		String[] formInfo = theForm.getFormInfo();
		if (formInfo.length >= 4 && addContest(formInfo[0], formInfo[1], formInfo[2], formInfo[3])) {
			theForm.setMessage("Contest added!");
			// update view
			theListView.setContestList(allContests());
		} else {
			theForm.setMessage("Please fill all fields.");
		}
	}
	
	
	/**
	 * Confirms that none of the strings are empty; if they are, returns false. Otherwise,
	 * adds contest to db and returns true.
	 * @param name - name of the contest
	 * @param description - describing the contest
	 * @param startDate - start date for the contest
	 * @param endDate - end date for the contest
	 *
	 * @return if a contest has been successfully added
	 * @exception Exception
	 */
	private boolean addContest(String name, String description, String startDate, String endDate) throws Exception {
		boolean added = false;
		if (name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null
			&& !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
			added = myContestDBManager.addContest(name, description, startDate, endDate);	
		} 	
		//System.out.println(added);
		return added;		
	}
	
}
