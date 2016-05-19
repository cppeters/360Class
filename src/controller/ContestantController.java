package controller;

import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Contest;
import model.ContestDatabaseManager;
import model.EntryDatabaseManager;
import model.User;
import view.ContestantContestListView;
import view.ContestantContestView;
import view.View;

/** Controls a session where a Contestant is logged in. */
public class ContestantController {
	
	private final User myUser;
	private final ContestDatabaseManager myContestDBManager;
	private final EntryDatabaseManager myEntryDBManager;
	private final View myView;
	

	/**
	 * 
	 * @param theUser				// TODO change this to a Contestant once we create that class.
	 * @param theContestDBManager	
	 * @param theEntryDBManager		
	 * @param theView				
	 */	
	public ContestantController(User theUser, ContestDatabaseManager theContestDBManager, EntryDatabaseManager theEntryDBManager, View theView) {
		myUser = theUser;
		myContestDBManager = theContestDBManager;
		myEntryDBManager = theEntryDBManager;
		myView = theView;
		setupListView();		
	}
	
	private void setupListView() {
		ContestantContestListView cclv = myView.getContestantContestListView();
		
		List<Contest> allContests = myContestDBManager.getAllContests();
		
		// TODO determine which contests myUser has made submissions to, and which they have not. Then add to correct list in view.
		cclv.setNoSubmissionMadeList(allContests.toArray(new Contest[allContests.size()]));
		cclv.addNoSubmissionMadeListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) { // http://stackoverflow.com/questions/12975460/listselectionlistener-invoked-twice
					setupEntryView(cclv.getNoSubmissionMadeSelectedEntry());
				}
			}
			
		});
		
		myView.showPage(cclv);
	}
	
	private void setupEntryView(Contest theContest) {
		System.out.println("You selected: " + theContest);
		ContestantContestView ccv = myView.getContestantContestView();
		ccv.setContestName(theContest.getName());
		myView.showPage(ccv);
	}
	
}
