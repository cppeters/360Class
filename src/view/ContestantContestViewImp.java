package view;

import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Contest;
import model.Entry;
import model.EntryDatabaseManager;
import model.User;

/**A view for submitting an entry to a certain contest.*/
public class ContestantContestViewImp implements ContestantContestView {
	
	private final JPanel myPanel;
	private final JButton myBrowseButton;
	private final JButton mySubmitButton;
	private final JLabel myContestName;
	private final JLabel myEntrylbl;
	private final JLabel myFilelbl;
	private final JLabel myEntrySubmitted;
	private final JTextField myEntryFileName;
	private final JTextField myEntryText;
	private File myEntryFile;
	
	public ContestantContestViewImp() {
		myPanel = new JPanel();
		myEntryFile = null;
		myBrowseButton = new JButton("Browse...");
		mySubmitButton = new JButton("Submit Entry");
		myEntrylbl = new JLabel("Entry Name");
		myFilelbl = new JLabel("File Name");
		myContestName = new JLabel();
		myEntrySubmitted = new JLabel();
		myEntrySubmitted.setVisible(false);
		myEntryFileName = new JTextField(25);
		myEntryFileName.setEditable(false);
		myEntryText = new JTextField(25);
		//myPanel.add(myContestName);
		myPanel.add(myEntrylbl);
		myPanel.add(myEntryText);
		myPanel.add(myBrowseButton);
		myPanel.add(myFilelbl);
		myPanel.add(myEntryFileName);
		myPanel.add(mySubmitButton);
		myPanel.add(myEntrySubmitted);
	}
	
	

	@Override
	public JPanel getView() {
		return myPanel;
	}



	@Override
	public void addBrowseButtonListener(AbstractAction theAction) {
		myBrowseButton.addActionListener(theAction);
	}
	
	
	@Override
	public void addSubmitButtonListener(AbstractAction theAction) {
		mySubmitButton.addActionListener(theAction);
		
	}


	@Override
	public void setContestName(String theContestName) {
		myContestName.setText(theContestName);
		
	}
	
	
	@Override
	public String getEntryFileName() 
	{
		JFileChooser jfc = new JFileChooser();
		if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{
			myEntryFile = jfc.getSelectedFile();
			myEntryFileName.setText(myEntryFile.getAbsolutePath());
		}
		return myEntryFileName.getText();
	}

	@Override
	public void submitNewEntry(User theUser, EntryDatabaseManager theEntryDataBaseManager,
			Contest theContest) 
	{
		Entry e = new Entry(theEntryDataBaseManager.getTotalEntries() + 1, 
				theUser.getCardNumber(), myEntryFileName.getText(), 
				theContest.getContestNumber(), myEntryText.getText());
		
		theUser.addEntry(e, theEntryDataBaseManager);
		myEntrySubmitted.setText("Entry Submitted!");
		myEntrySubmitted.setVisible(true);
		System.out.println(theUser.getEntries());
		theEntryDataBaseManager.writeCsvFile();
	}
}
