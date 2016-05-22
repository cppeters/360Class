package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Contest;
import model.ContestDatabaseManager;
import model.Entry;
import model.EntryDatabaseManager;
import model.User;
import view.ContestantContestListView;
import view.ContestantContestView;
import view.View;

/** Controls a session where a Contestant is logged in. 
 * @author Tabi*/
public class ContestantController {
	
	private final User myUser;
	private final ContestDatabaseManager myContestDBManager;
	private final EntryDatabaseManager myEntryDBManager;
	private final View myView;
	

	/**
	 * Calling this constructor triggers all the appropriate listeners to be added to the view,
	 * so it is not necessary to save a reference to it in the calling class. By swapping out
	 * to a different view that this controller does not control and removing all listeners to
	 * the view's back button, all references to this class will be gone and it will be garbage
	 * collected as desired.
	 * @param theUser
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
		
		List<Contest> allSubmittedTo = new ArrayList<>();
		List<Contest> allNotSubmittedTo = new ArrayList<>();
		distributeContests(allSubmittedTo, allNotSubmittedTo);
		
		cclv.setSubmissionMadeList(allSubmittedTo.toArray(new Contest[allSubmittedTo.size()]));
		cclv.addSubmissionMadeListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO add view showing that contest has already been submitted to, but let them change it from there.
			}
			
		});
		
		cclv.setNoSubmissionMadeList(allNotSubmittedTo.toArray(new Contest[allNotSubmittedTo.size()]));
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
	
	/**
	 * Distributes the contests that the user has submitted to to the submittedTo
	 * list, and those he hasn't to the notSubmittedTo list.
	 * @param allSubmittedTo
	 * @param allNotSubmittedTo
	 */
	private void distributeContests(List<Contest> allSubmittedTo, List<Contest> allNotSubmittedTo) {
    	allSubmittedTo.clear();
		allNotSubmittedTo.clear();
    	
    	// store ref to all contests
    	Map<Integer,Contest> allContests = myContestDBManager.getContestMap();    	

		// get all of User's entries
    	List<Entry> testUsersEntries = myUser.getEntries();
    	
    	// Put all contests into contestsNotSubmtitedTo
    	allNotSubmittedTo.addAll(myContestDBManager.getContestMap().values());   
    	
    	// add all contests whose key matches that in the User's entries to list, removing
    	// from not submitted to list.
    	for (Entry e : testUsersEntries) {
    		Contest submittedTo = allContests.get(e.getContest());
    		if (submittedTo != null) {
    			allSubmittedTo.add(submittedTo);
    			allNotSubmittedTo.remove(submittedTo);
    		}
    	}
	}
	
	private void setupEntryView(Contest theContest) {
		System.out.println("You selected: " + theContest);
		ContestantContestView ccv = myView.getContestantContestView();
		ccv.setContestName(theContest.getName());
		myView.showPage(ccv);
	}
	
}
