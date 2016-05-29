package view;

import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;

import model.Contest;
import model.Entry;

public class JudgeEntryListViewImp implements JudgeEntryListView {
	private final JPanel myPanel;
	private final JList<Entry> myList;   
	
	public JudgeEntryListViewImp() {
		myPanel = new JPanel();
		myList = new JudgeEntryList();	
		
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		
		JPanel listContainerPanel = new JPanel();
		listContainerPanel.setLayout(new BoxLayout(listContainerPanel, BoxLayout.Y_AXIS));	
		listContainerPanel.add(JudgeEntryList.getColumnTitleHeader());
//		listContainerPanel.add(new JScrollPane(myList));
		
		listContainerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		myPanel.add(listContainerPanel);
		
	}
	
	
	
	@Override
	public JPanel getView() {
		return myPanel;
	}

	@Override
	public void addEntryListListener(ListSelectionListener theListener) {
		myList.addListSelectionListener(theListener);
		
	}
	@Override
	public void setEntryList(Entry[] theEntry) {
		System.out.print(theEntry);
		System.out.print("sadfasdfasdf");
		myList.setListData(theEntry);

	}

}
