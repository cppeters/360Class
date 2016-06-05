package view;

import java.awt.Component;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

import model.Contest;

public class AdminContestListViewImp implements AdminContestListView {

	/** Panel that will be used for the admin view*/
	private final JPanel myPanel;
	/** Holds the list of contest*/
	private final JList<Contest> myList;
	/** Creates a new contests when clicked*/
	private final JButton newContestButton;
	
	public AdminContestListViewImp() {
		myPanel = new JPanel();
		//myList = new JList<>();
		myList = new ContestList();	
		
		newContestButton = new JButton("Create new contest");
		
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		
		JPanel listContainerPanel = new JPanel();
		listContainerPanel.setLayout(new BoxLayout(listContainerPanel, BoxLayout.Y_AXIS));	
		listContainerPanel.add(ContestList.getColumnTitleHeader());
		listContainerPanel.add(new JScrollPane(myList));
		
		newContestButton.setAlignmentX(Component.LEFT_ALIGNMENT);
		listContainerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		myPanel.add(listContainerPanel);
		myPanel.add(newContestButton);
		
	}

	@Override
	public void setContestList(Contest[] theContest) {
		myList.setListData(theContest);
	}

	@Override
	public void addNewContestButtonListener(AbstractAction theAction) {
		newContestButton.addActionListener(theAction);

	}

	@Override
	public void addEditContestListener(ListSelectionListener theListener) {
		myList.addListSelectionListener(theListener);
	}


	@Override
	public JPanel getView() {
		return myPanel;
	}

	@Override
	public NewContestForm createNewContestForm() {
		return new NewContestFormImp();
	}
	
//	/**
//	 * Information taken from are from StackOverFlow problem #7306295, solution by user kleopatra
//	 * Necessary for allowing word wrap in text areas inside of JList.
//	 * @author Tabi
//	 */
//	private class ContestList extends JList<Contest>{
//		
//        ComponentListener l = new ComponentAdapter() {
//
//            @Override
//            public void componentResized(ComponentEvent e) {
//                setFixedCellHeight(10);
//                setFixedCellHeight(-1);
//            }
//
//        };
//
//		public ContestList() {
//			this.addComponentListener(l);
//		}
//
//        @Override
//        public boolean getScrollableTracksViewportWidth() {
//            return true;
//        }
//
//
//	}

}
