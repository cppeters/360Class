package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;


import model.Contest;
import model.Entry;

public class JudgeEntryListViewImp implements JudgeEntryListView {
	private final JPanel myPanel;
	private final JLabel myContestLabel;
	private final JLabel noEntyLabel;
	private final JList<Entry> myList;  
	private JFrame popup ;
	private JScrollPane myScrollPane;
	
	public JudgeEntryListViewImp() {
		myPanel = new JPanel();
		myList = new JudgeEntryList();	
		myContestLabel = new JLabel();
		noEntyLabel = new JLabel();
		popup = new JFrame();
		myScrollPane = new JScrollPane();
		myContestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		noEntyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		
		JPanel listContainerPanel = new JPanel();
		
		listContainerPanel.setLayout(new BoxLayout(listContainerPanel, BoxLayout.Y_AXIS));	
		listContainerPanel.add(JudgeEntryList.getColumnTitleHeader());
		listContainerPanel.add(new JScrollPane(myList));
		myPanel.add(myContestLabel);
		myPanel.add(noEntyLabel);
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
	public void setEntryList(Entry[] theEntry, Contest theContest) {
		String subString = "Entries from ";
		myList.setListData(theEntry);
		myContestLabel.setToolTipText(subString + theContest.getName());
		if(theContest.getName().length() > 30){
			myContestLabel.setText(subString + theContest.getName().substring(0, 30) + "...");
		}else {
			myContestLabel.setText(subString + theContest.getName());
		}

		if(theEntry.length == 0){
			noEntyLabel.setText("(No Entry)");
			noEntyLabel.setForeground(Color.RED);
		}else if (theEntry.length == 1) {
			noEntyLabel.setText(theEntry.length + " Entry");
		}
		else{
			noEntyLabel.setText(theEntry.length + " Entries");
		}
		myContestLabel.setForeground( new Color(65, 40, 118));
		myContestLabel.setFont(myContestLabel.getFont().deriveFont (15.0f));
	}

	@Override
	public void addPreview(Entry theEntry) {
		ImageIcon imageIcon = new ImageIcon(theEntry.getFilePath());
		JLabel logoLabel = new JLabel(imageIcon);
		myScrollPane.setViewportView(logoLabel);
		popup.setMinimumSize(new Dimension(500,500));
		popup.add(myScrollPane);
		popup.setVisible(true);
	}

}
