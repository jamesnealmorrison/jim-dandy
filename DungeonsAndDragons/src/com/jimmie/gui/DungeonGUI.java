package com.jimmie.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import com.jimmie.DungeonConfig;
import com.jimmie.KoboldLairOutsideEncounterConfig;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.IntegratedCommandConsole;
import com.jimmie.util.Utils;

@ContextConfiguration(classes=DungeonConfig.class)
@Component
public class DungeonGUI extends JPanel {
	static JFrame frame = null;
	private static final long serialVersionUID = 1234567890L;
	IntegratedCommandConsole console;

	JPanel topPanel = null;
	@Autowired
	private PartyPanel partyPanel = null;
	public PartyPanel getPartyPanel() {
		return partyPanel;
	}

	public void setPartyPanel(PartyPanel partyPanel) {
		this.partyPanel = partyPanel;
	}

	@Autowired
	private ConsolePanel consolePanel;
	public ConsolePanel getConsolePanel() {
		return consolePanel;
	}

	public void setConsolePanel(ConsolePanel consolePanel) {
		this.consolePanel = consolePanel;
	}

	public BattlefieldPanel getBattlefieldPanel() {
		return battlefieldPanel;
	}

	public void setBattlefieldPanel(BattlefieldPanel battlefieldPanel) {
		this.battlefieldPanel = battlefieldPanel;
	}

	@Autowired
	private BattlefieldPanel battlefieldPanel;
	private JScrollPane battleMapScrollPane;
	private JScrollPane creatureScrollPane;
	@Autowired
	private Encounter encounter;

	public Encounter getEncounter() {
		return encounter;
	}

	public void setEncounter(Encounter encounter) {
		this.encounter = encounter;
	}

	public void init() {
		topPanel = new JPanel();
		LayoutManager borderLayout = new BorderLayout();
		Dimension preferredSize = new Dimension();
		preferredSize.setSize(1250, 975);
		topPanel.setPreferredSize(preferredSize);
		topPanel.setLayout(borderLayout);

		/* Set up the encounter. */
		// encounter = new KoboldLairOutsideEncounter();

		Encounter.setEncounter(encounter);
		encounter.init();
		partyPanel.init(encounter.getCreatures());
		battlefieldPanel.init(encounter.getMap(), encounter.getCreatures());
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
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(KoboldLairOutsideEncounterConfig.class);
		
		frame = new JFrame("Dungeon GUI");      

		DungeonGUI newDungeonGUI = context.getBean(DungeonGUI.class);
		newDungeonGUI.init();
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
