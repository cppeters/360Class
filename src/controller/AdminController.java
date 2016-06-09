package controller;

import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;

import model.Contest;
import model.ContestDatabaseManager;
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

	/** The view to which this controller will display the admin's view. */
	private final View myView;
	/** The database from/to which this controller will retrieve/add contest information. */
	private final ContestDatabaseManager myContestDBManager; 
	
	/**List of all views that have been displayed to this user since this controller
	 * was created.*/
	private final LinkedList<Viewable> viewHistory;

	/** 
	 * Creates a new AdminController.
	 *
	 * Precondition: theConDatMan and theView must not be null.
	 *
	 * @param theConDatMan - the contest database
	 * @param theView - which view to use*/
	public AdminController(ContestDatabaseManager theConDatMan,  View theView) {
		myView = theView;
		myContestDBManager = theConDatMan; 
		viewHistory = new LinkedList<>();
		setupBackFunctionality();
		setupListView();
	}

	/** Adds the back button functionality. */
	private void setupBackFunctionality() {
		myView.addBackButtonListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!viewHistory.isEmpty() && viewHistory.getLast() != null) {
					myView.showPage(viewHistory.pop());
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

	/** Adds a page to the history of what has been viewed.
	 * @param theViewable - what has been viewed*/
	private void addToHistory(Viewable theViewable) {
		viewHistory.add(theViewable);
		myView.setBackButtonEnabled(true);
	}

	/** Sets up and displays the view of all Contests.*/
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

	/** Creates an array containing all Contests that exist in the DB.
	 * @return all the contests*/
	private Contest[] allContests() {
		List<Contest> contests = myContestDBManager.getAllItems();
		return contests.toArray(new Contest[contests.size()]);
	}
	
	/**
	 * Prepares and displays a new contest submission form. It will be notified
	 * of the submission to the form so the list it displays can be updated.
	 * 
	 * Precondition: theListView must not be null.
	 * Postcondition: theListView's contest list is updated to include an added Contest.
	 * 
	 * @param theListView - any AdminContestListView.
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
	 *
	 * Precondition: theForm and theListView must not be null.
	 * Postcondition: theListView's contest list is updated to include an added Contest.
	 *
	 * @param theForm - form of the contest
	 * @param theListView - list of the views seen
	 */
	private void onAddingContest(NewContestForm theForm, AdminContestListView theListView) throws Exception {
		String[] formInfo = theForm.getFormInfo();
		if (formInfo.length >= 4 && addContest(formInfo[0], formInfo[1], formInfo[2], formInfo[3])) {
			theForm.setMessage("Contest added!", false);
			// update view
			theListView.setContestList(allContests());
		} else {
			theForm.setMessage("Please fill all fields and use a unique contest name.", true);
		}
	}
	
	
	/**
	 * Confirms that none of the strings are empty; if they are, returns false. Otherwise,
	 * adds contest to db and returns true.
	 *
	 * Postcondition: a new contest is added to the DB if and only if true is returned.
	 *
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
		return added;		
	}
}
