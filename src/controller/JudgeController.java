package controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Contest;
import model.ContestDatabaseManager;
import model.Entry;
import model.EntryDatabaseManager;
import model.User;
import view.JudgeContestListView;
import view.JudgeEntryListView;
import view.View;
import view.Viewable;

public class JudgeController {
	
	private final View myView;
	private final ContestDatabaseManager myContestDBManager; 
	private final EntryDatabaseManager myEntryDBManager; 
	
	/**List of all views that have been displayed to this user since this controller
	 * was created.*/
	private final LinkedList<Viewable> viewHistory;
	
	public JudgeController(User theUser, ContestDatabaseManager theContestDatabaseManager, 
							EntryDatabaseManager theEntryDatabaseManager, View theView) {
		myView = theView;
		myContestDBManager = theContestDatabaseManager;
		myEntryDBManager = theEntryDatabaseManager;
		viewHistory = new LinkedList<>();
		setupBackFunctionality();
		setupListView();
	}
	
	@SuppressWarnings("serial")
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

	/** Updates history
	* @param theViewable - view that has been used
	*/
	private void addToHistory(Viewable theViewable) {
		viewHistory.add(theViewable);
		myView.setBackButtonEnabled(true);
	}

	/** Creates the view*/
	private void setupListView() {
		final JudgeContestListView ClistView = myView.getJugdgeContestListView();
		ClistView.setContestList(allContests());
		ClistView.addContestListListener(new ListSelectionListener() {

			// Show the entries from the contest
			@Override
			public void valueChanged(ListSelectionEvent Event) {
				if (!Event.getValueIsAdjusting()) {
					JudgeEntryListView ElistView = myView.getJugdgeEntryListView();
					@SuppressWarnings("unchecked")
					Contest seclectedContest = ((JList<Contest>) Event.getSource()).getSelectedValue();
					if (seclectedContest != null) {
						ElistView.setEntryList(getEntries(seclectedContest.getContestNumber()),seclectedContest);
						ElistView.addEntryListListener(new ListSelectionListener() {
							@Override
							public void valueChanged(ListSelectionEvent Event) {
								if (!Event.getValueIsAdjusting()) {
									@SuppressWarnings("unchecked")
									Entry seclectedEntry = ((JList<Entry>) Event.getSource()) .getSelectedValue();
									ElistView .addPreview(seclectedEntry);
									}
								}
							});
						myView.showPage(ElistView);
						addToHistory(ClistView);
						ClistView.clearSelection();
					}
				}
			}
		});
		myView.showPage(ClistView);
	}

	/** Gets all the contest that have been submitted
	 * @return the contests*/
	private Contest[] allContests() {
		List<Contest> contests = myContestDBManager.getAllItems();
		return contests.toArray(new Contest[contests.size()]);
	}

	/** What entries have been submitted
	 * @return gets all entries that have been submitted*/
    private Entry[] getEntries(int theContestNumber) {
        List<Entry> entries = new ArrayList<>();
        for(int index = 0; index < myEntryDBManager.getAllItems().size(); index++){
            if(myEntryDBManager.getAllItems().get(index).getContest() == theContestNumber){
                entries.add(myEntryDBManager.getAllItems().get(index));
            }
        }
        return entries.toArray(new Entry[entries.size()]);
    }
}

		
		
	

