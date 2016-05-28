package controller;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;

import model.Contest;
import model.ContestDatabaseManager;
import model.User;
import view.AdminContestListView;
import view.NewContestForm;
import view.View;

/**
 * An admin can view contests and add new ones.
 * @author Tabi
 *
 */
public class AdminController {
	
	private final View myView;
	private final User myUser;
	private final ContestDatabaseManager myContestDBManager; 
	
	

	public AdminController(User theUser, ContestDatabaseManager theConDatMan,  View theView) {
		myView = theView;
		myUser = theUser;
		myContestDBManager = theConDatMan; 
		setupListView();
	}
	
	private void setupListView() {
		AdminContestListView listView = myView.getAdminContestListView();
		listView.setContestList(allContests());
		listView.addNewContestButtonListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onAddContestView(listView);
			}
			
		});
		myView.showPage(listView);
	}
	
	private Contest[] allContests() {
		List<Contest> contests = myContestDBManager.getAllContests();
		return contests.toArray(new Contest[contests.size()]);
	}
	
	/**
	 * 
	 */
	private void onAddContestView(AdminContestListView theListView) {
		NewContestForm theForm = theListView.createNewContestForm();
		theForm.addSubmitListener(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				onAddingContest(theForm, theListView);
			}
			
		});
		myView.showPage(theForm);
	}

	
	
	/**
	 * To be called when the user submits a new contest form.
	 * Adds new contest to db and updates list view.
	 */
	private void onAddingContest(NewContestForm theForm, AdminContestListView theListView) {
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
	 * @return
	 */
	private boolean addContest(String name, String description, String startDate, String endDate) {
		boolean added = false;
		if (name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null
			&& !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
			added = myContestDBManager.addContest(name, description, startDate, endDate);	
		} 	
		System.out.println(added);
		return added;		
	}
	
}
