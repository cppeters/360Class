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

import model.Contest;

/**
 * A list that can be used anywhere to display a list of 
 * 
 * Some information necessary for allowing word wrap in text areas inside of
 * JList are taken from StackOverFlow problem #7306295, solution by user
 * kleopatra.
 * 
 * @author Tabi
 */
public class ContestList extends JList<Contest> {

	/**The color used for highlighting rows in list on mouseover.*/
	private static final Color HUSKY_PURPLE = new Color(177, 144, 212);

	/**
	 * Constructs a new, empty ContestList.
	 */
	public ContestList() {
		this.setCellRenderer(new ContestListCellRenderer());
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
		container.add(new JLabel("Name"));
		dateContainer.add(new JLabel("Start Date"));
		dateContainer.add(new JLabel("End Date"));
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
	private class ContestListCellRenderer implements ListCellRenderer<Contest> {

		private final JPanel container;

		private final JTextArea nameLab;
		private final JLabel sDateLabel;
		private final JLabel eDateLabel;

		public ContestListCellRenderer() {
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
		public Component getListCellRendererComponent(JList<? extends Contest> list, Contest value, int index,
				boolean isSelected, boolean cellHasFocus) {

			if (isSelected) {
				container.setBackground(HUSKY_PURPLE);
			} else if (cellHasFocus) {
				container.setBackground(HUSKY_PURPLE.brighter().brighter());
			} else {
				container.setBackground(Color.WHITE);
			}
			list.setToolTipText(value.getDescription());
			sDateLabel.setText(value.getStartDate());
			eDateLabel.setText(value.getEndDate());
			

			/*
			 * The following two statements are from StackOverFlow problem
			 * #7306295, solution by user kleopatra. Necessary for allowing text
			 * area to be multi-line.
			 */
			int width = list.getWidth();
			if (width > 0)
				nameLab.setSize(width / 2, Short.MAX_VALUE);

			nameLab.setText(value.getName());

			return container;
		}

	}


}
