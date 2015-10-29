package com.jimmie.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jimmie.encounters.Encounter;
import com.jimmie.encounters.KoboldLairOutsideEncounter;
import com.jimmie.util.IntegratedCommandConsole;
import com.jimmie.util.Utils;

public class DungeonGUI extends JPanel {
	static JFrame frame = null;
	private static final long serialVersionUID = 1234567890L;
	IntegratedCommandConsole console;

	JPanel topPanel = null;
	PartyPanel partyPanel = null;
	ConsolePanel consolePanel;;
	private BattlefieldPanel battlefieldPanel;
	private JScrollPane battleMapScrollPane;
	private JScrollPane creatureScrollPane;
	private Encounter encounter;

	public DungeonGUI()
	{

		topPanel = new JPanel();
		LayoutManager borderLayout = new BorderLayout();
		Dimension preferredSize = new Dimension();
		preferredSize.setSize(1250, 975);
		topPanel.setPreferredSize(preferredSize);
		topPanel.setLayout(borderLayout);

		/* Set up the encounter. */
		encounter = new KoboldLairOutsideEncounter();

		partyPanel = new PartyPanel(encounter.getCreatures());
		battlefieldPanel = new BattlefieldPanel(encounter.getMap(), encounter.getCreatures());
		battleMapScrollPane = new JScrollPane();
		Dimension battleMapScrollPaneDimension = new Dimension(400,400);
		battleMapScrollPane.setPreferredSize(battleMapScrollPaneDimension);
		battleMapScrollPane.createHorizontalScrollBar();
		battleMapScrollPane.createVerticalScrollBar();
		battleMapScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		battleMapScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		battleMapScrollPane.add(battlefieldPanel);
		battleMapScrollPane.setViewportView(battlefieldPanel);

		creatureScrollPane = new JScrollPane();
		Dimension creatureScrollPaneDimension = new Dimension(200,400);
		creatureScrollPane.setPreferredSize(creatureScrollPaneDimension);
		creatureScrollPane.createHorizontalScrollBar();
		creatureScrollPane.createVerticalScrollBar();
		creatureScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		creatureScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		creatureScrollPane.add(partyPanel);
		creatureScrollPane.setViewportView(partyPanel);


		consolePanel = new ConsolePanel();
		console = new IntegratedCommandConsole();
		console.addPropertyChangeListener(consolePanel);
		console.setConsolePanel(consolePanel);

		topPanel.add(creatureScrollPane, BorderLayout.WEST);
		topPanel.add(battleMapScrollPane, BorderLayout.CENTER);
		topPanel.add(consolePanel, BorderLayout.SOUTH);
		add(topPanel);

		Utils.setICC(console);
		Utils.setGui(this);
	}


	public static void main(String[] args)
	{
		frame = new JFrame("Dungeon GUI");      

		DungeonGUI newDungeonGUI = new DungeonGUI();
		newDungeonGUI.setOpaque(true);

		frame.setContentPane(newDungeonGUI);

		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		}
				);

		newDungeonGUI.run();
	}

	private static void createAndShowGUI()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void run()
	{
		encounter.runEncounter();
	}

	public void refreshAll()
	{
		if (partyPanel != null)
		{
			partyPanel.refreshAll();
		}
		if (consolePanel != null)
		{
//			consolePanel.refreshAll();
		}
		if (battlefieldPanel != null)
		{
			battlefieldPanel.repaint();
		}
	}
}
