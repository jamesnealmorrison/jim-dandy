package com.jimmie.gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import com.jimmie.CryptOfShadowsEncounterConfig;
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
	private JPanel bufferBetweenMonitors;
	private JScrollPane creatureScrollPane;
	@Autowired
	private BattleCardPanel battleCardPanel;
	private JScrollPane battleCardsScrollPane;
	
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
		LayoutManager gridBagLayout = new GridBagLayout();
		Dimension preferredSize = new Dimension();
		preferredSize.setSize(3810, 975);
		topPanel.setPreferredSize(preferredSize);
		topPanel.setLayout(gridBagLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		/* Set up the encounter. */
		// encounter = new KoboldLairOutsideEncounter();

		Encounter.setEncounter(encounter);
		encounter.init();

		partyPanel.init(encounter.getCreatures());
		battlefieldPanel.init(encounter.getMap(), encounter.getCreatures());
		battleMapScrollPane = new JScrollPane();
		Dimension battleMapScrollPaneDimension = new Dimension(1055,975);
		battleMapScrollPane.setPreferredSize(battleMapScrollPaneDimension);
		battleMapScrollPane.createHorizontalScrollBar();
		battleMapScrollPane.createVerticalScrollBar();
		battleMapScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		battleMapScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		battleMapScrollPane.getVerticalScrollBar().setUnitIncrement(16);

		battleMapScrollPane.add(battlefieldPanel);
		battleMapScrollPane.setViewportView(battlefieldPanel);

		bufferBetweenMonitors = new JPanel();
		Dimension bufferDimension = new Dimension(10,975);
		bufferBetweenMonitors.setPreferredSize(bufferDimension);

		creatureScrollPane = new JScrollPane();
		Dimension creatureScrollPaneDimension = new Dimension(725,975);
		creatureScrollPane.setPreferredSize(creatureScrollPaneDimension);
		creatureScrollPane.createHorizontalScrollBar();
		creatureScrollPane.createVerticalScrollBar();
		creatureScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		creatureScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		creatureScrollPane.getVerticalScrollBar().setUnitIncrement(16);

		creatureScrollPane.add(partyPanel);
		creatureScrollPane.setViewportView(partyPanel);

		
		battleCardsScrollPane = new JScrollPane();
		Dimension battleCardsScrollPaneDimension = new Dimension(1170,975);
		battleCardsScrollPane.setPreferredSize(battleCardsScrollPaneDimension);
		battleCardsScrollPane.createHorizontalScrollBar();
		battleCardsScrollPane.createVerticalScrollBar();
		battleCardsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		battleCardsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		battleCardsScrollPane.getVerticalScrollBar().setUnitIncrement(16);

		battleCardPanel.init();
		battleCardsScrollPane.add(battleCardPanel);
		battleCardsScrollPane.setViewportView(battleCardPanel);
		

		console = new IntegratedCommandConsole();
		console.addPropertyChangeListener(consolePanel);
		console.setConsolePanel(consolePanel);

		topPanel.add(battleMapScrollPane, c);
		topPanel.add(consolePanel, c);
		topPanel.add(bufferBetweenMonitors, c);
		topPanel.add(creatureScrollPane, c);
		topPanel.add(battleCardsScrollPane, c);
		add(topPanel);

		Utils.setICC(console);
		Utils.setGui(this);

	}


	public static void main(String[] args)
	{
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(CryptOfShadowsEncounterConfig.class);
		
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
		if (battleCardPanel != null) {
			battleCardPanel.repaint();
		}
	}
}
