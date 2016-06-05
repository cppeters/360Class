package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionListener;


import model.Contest;
import model.Entry;

public class JudgeEntryListViewImp implements JudgeEntryListView {
	/** Panel for the judge view*/
	private final JPanel myPanel;
	/** Label for the contest*/
	private final JLabel myContestLabel;
	/** Label for the result*/
	private final JLabel myEntryCountandResultlabel;
	/** Holding the list of judged entries*/
	private final JList<Entry> myList;
	/** List for first place*/
	JComboBox<String> my1stRankingPlace;
	/** List for second place*/
	JComboBox<String> my2ndRankingPlace;
	/** List for third place*/
	JComboBox<String> my3rdRankingPlace;
	/** Button used to submit judged entries*/
	JButton myJudgeSumissionButton;
	/** Pop up preview*/
	private JFrame myPreviewPopup ;
	/** Be able to scroll through entries*/
	private JScrollPane myScrollPane;

	/** Constructor()*/
	public JudgeEntryListViewImp() {
		myPanel = new JPanel();
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
		myList = new JudgeEntryList();	
		myScrollPane = new JScrollPane();
		
		myPreviewPopup = new JFrame();
		myContestLabel = new JLabel();
		myEntryCountandResultlabel = new JLabel();
		myContestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		myEntryCountandResultlabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		myJudgeSumissionButton = new JButton("Submit");
		myJudgeSumissionButton.setEnabled(false);
		
		my1stRankingPlace = new JComboBox<String>();
		my2ndRankingPlace = new JComboBox<String>();
		my3rdRankingPlace = new JComboBox<String>();
		my1stRankingPlace.setToolTipText("1st rank");
		my1stRankingPlace.setToolTipText("2st rank");
		my1stRankingPlace.setToolTipText("3st rank");
		
		JPanel listContainerPanel = new JPanel();
		JPanel judgingPanel = new JPanel();
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel1.setLayout(new GridLayout(2,1));
		panel2.setLayout(new GridLayout(2,1));
		panel3.setLayout(new GridLayout(2,1));
		
		panel1.add(new JLabel("1st place"));
		panel1.add(my1stRankingPlace);
		panel2.add(new JLabel("2nd place"));
		panel2.add(my2ndRankingPlace);
		panel3.add(new JLabel("3rd place"));
		panel3.add(my3rdRankingPlace);
	
		judgingPanel.setLayout(new GridLayout(1,4));
		judgingPanel.add(panel1);
		judgingPanel.add(panel2);
		judgingPanel.add(panel3);
		judgingPanel.add(myJudgeSumissionButton);
		
		
		listContainerPanel.setLayout(new BoxLayout(listContainerPanel, BoxLayout.Y_AXIS));	
		listContainerPanel.add(JudgeEntryList.getColumnTitleHeader());
		listContainerPanel.add(new JScrollPane(myList));
		myPanel.add(myContestLabel);
		myPanel.add(myEntryCountandResultlabel);
		myPanel.add(listContainerPanel);
		myPanel.add(judgingPanel);
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
			myEntryCountandResultlabel.setText("(No Entry)");
			myEntryCountandResultlabel.setForeground(Color.RED);
		}else if (theEntry.length == 1) {
			myEntryCountandResultlabel.setText(theEntry.length + " Entry");
		}
		else{
			myEntryCountandResultlabel.setText(theEntry.length + " Entries");
		}
		myContestLabel.setForeground( new Color(65, 40, 118));
		myContestLabel.setFont(myContestLabel.getFont().deriveFont (15.0f));
		setupDropdownSubmissionRanking(theEntry);
	}

	/** Creates the drop down option for judging
	 * @param theEntry - the entries that will be judged*/
	@SuppressWarnings("unchecked")
	private void setupDropdownSubmissionRanking(Entry[] theEntry) {
		@SuppressWarnings("rawtypes")
		JComboBox[] dropdownGroup = new JComboBox[]{my1stRankingPlace, 
				                                    my2ndRankingPlace, 
				                                    my3rdRankingPlace};
		String none = "None";
		for (int count = 0; count < dropdownGroup.length; count++) {
			dropdownGroup[count].addItem(none);
			for (int index = 0; index < theEntry.length; index++) {
				dropdownGroup[count].addItem(theEntry[index].getEntryName());
			}
			dropdownGroup[count].addActionListener(new ActionListener() {
				 
			    @Override
				public void actionPerformed(ActionEvent event) {
					myJudgeSumissionButton.setEnabled(true);
					if (my1stRankingPlace.getSelectedItem().equals(my2ndRankingPlace.getSelectedItem())
							&& my2ndRankingPlace.getSelectedItem().equals(my3rdRankingPlace.getSelectedItem())
							&& my1stRankingPlace.getSelectedItem() .equals("None")) {
						myJudgeSumissionButton.setEnabled(false);
					}
				}
			});
		}
		myJudgeSumissionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (my1stRankingPlace.getSelectedItem().equals(none)) {
					JOptionPane.showMessageDialog(myPanel, "1st place cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
				} else if (my1stRankingPlace.getSelectedItem().equals(my2ndRankingPlace.getSelectedItem())
						|| my2ndRankingPlace.getSelectedItem().equals(my3rdRankingPlace.getSelectedItem())
						|| my1stRankingPlace.getSelectedItem().equals(my3rdRankingPlace.getSelectedItem())) {
					if (my2ndRankingPlace.getSelectedItem().equals(none)) {
						if (my3rdRankingPlace.getSelectedItem().equals(none)) {
							myEntryCountandResultlabel.setText( myEntryCountandResultlabel.getText() + " JUDGED");
						} else {
							JOptionPane.showMessageDialog(myPanel, "One entry can't has two ranking", "Warning", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(myPanel, "One entry can't has two ranking", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} else if (my2ndRankingPlace.getSelectedItem().equals(none)
						&& !(my3rdRankingPlace.getSelectedItem().equals(none))
						&& !(my1stRankingPlace.getSelectedItem() .equals(none))) {
					JOptionPane.showMessageDialog(myPanel, "2nd place cannot be empty while 3rd place is not empty", "Warning", JOptionPane.WARNING_MESSAGE);
				} else {
					myEntryCountandResultlabel.setText( myEntryCountandResultlabel.getText() + " JUDGED");
				}
			}
		});
	}

	@Override
	public void addPreview(Entry theEntry) {
		ImageIcon imageIcon = new ImageIcon(theEntry.getFilePath());
		JLabel logoLabel = new JLabel(imageIcon);
		myScrollPane.setViewportView(logoLabel);
		myPreviewPopup.setMinimumSize(new Dimension(500,500));
		myPreviewPopup.add(myScrollPane);
		myPreviewPopup.setVisible(true);
	}
}
