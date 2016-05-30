package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import model.Entry;

/**
 * A list that can be used anywhere to display a list of 
 * 
 * Some information necessary for allowing word wrap in text areas inside of
 * JList are taken from StackOverFlow problem #7306295, solution by user
 * kleopatra.
 * 
 * @author Tabi
 */
@SuppressWarnings("serial")
public class JudgeEntryList extends JList<Entry> {

	private static final Color HUSKY_PURPLE = new Color(177, 144, 212);

	public JudgeEntryList() {
	      System.out.println("fffffffffffffffffffffffffffffffff");
		this.setCellRenderer(new EntryListCellRenderer());
		this.addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				setFixedCellHeight(10);
				setFixedCellHeight(-1);
			}

		});
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return true;
	}
	

	/**
	 * Makes a panel that aligns with the list cells if they are in the same
	 * container, showing the column names.
	 * 
	 * @return the header panel.
	 */
	public static Component getColumnTitleHeader() {
		JPanel dateContainer = new JPanel(new GridLayout(1, 2));
		dateContainer.setOpaque(false);
		JPanel container = new JPanel(new GridLayout(1, 2));
		container.add(new JLabel("Entry"));
		dateContainer.add(new JLabel("Name"));
		dateContainer.add(new JLabel("somthing"));
		container.add(dateContainer);
		return container;
	}

	/**
	 * Displays a cell in list of Contests with 1/2 the width to the contest's name, and
	 * 1/4 each the width for the contests start and end dates. Also has tool
	 * tip text for the Contest's description.
	 * 
	 * @author Tabi
	 */
	private class EntryListCellRenderer implements ListCellRenderer<Entry> {

		private final JPanel container;

		private final JTextArea nameLab;
		private final JLabel sDateLabel;
		private final JLabel eDateLabel;

		public EntryListCellRenderer() {
			JPanel dateContainer = new JPanel(new GridLayout(1, 2));
			JPanel namePanel = new JPanel(new BorderLayout());
			dateContainer.setOpaque(false);
			namePanel.setOpaque(false);

			container = new JPanel(new GridLayout(1, 2));
			nameLab = new JTextArea();
			nameLab.setLineWrap(true);
			nameLab.setWrapStyleWord(true);
			nameLab.setOpaque(false);
			sDateLabel = new JLabel();
			eDateLabel = new JLabel();

			namePanel.add(nameLab);
			dateContainer.add(sDateLabel);
			dateContainer.add(eDateLabel);

			container.add(namePanel);
			container.add(dateContainer);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends Entry> list, Entry value, int index,
				boolean isSelected, boolean cellHasFocus) {

			if (isSelected) {
				container.setBackground(HUSKY_PURPLE);
			} else if (cellHasFocus) {
				container.setBackground(HUSKY_PURPLE.brighter().brighter());
			} else {
				container.setBackground(Color.WHITE);
			}
			list.setToolTipText("" + value.getCardNumber());
			sDateLabel.setText(value.getFilePath());
			eDateLabel.setText(value.getEntryName());

			/*
			 * The following two statements are from StackOverFlow problem
			 * #7306295, solution by user kleopatra. Necessary for allowing text
			 * area to be multi-line.
			 */
			int width = list.getWidth();
			if (width > 0)
				nameLab.setSize(width / 2, Short.MAX_VALUE);

			nameLab.setText("hello");
            System.out.println("fffffffffffffffffffffffffffffffff");
			return container;
		}

	}


}
