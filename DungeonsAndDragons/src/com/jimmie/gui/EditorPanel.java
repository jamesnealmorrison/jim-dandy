package com.jimmie.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class EditorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SelectedPartPanel selectedPartPanel; 
	private EditorButtonsPanel editorButtonsPanel;

	private BattlefieldCreator battlefieldCreator;

	public BattlefieldCreator getBattlefieldCreator() {
		return battlefieldCreator;
	}

	public void setBattlefieldCreator(BattlefieldCreator battlefieldCreator) {
		this.battlefieldCreator = battlefieldCreator;
	}

	public void init() {
		selectedPartPanel = new SelectedPartPanel();
		editorButtonsPanel = new EditorButtonsPanel();
		LayoutManager borderLayout = new BorderLayout();
		Dimension dimension = new Dimension(500,400);
		setPreferredSize(dimension);
		setLayout(borderLayout);
		selectedPartPanel.init();
		editorButtonsPanel.init();
		editorButtonsPanel.setEditorPanel(this);
		
//		Dimension selectedPartScrollPaneDimension = new Dimension(400,400);
		
		add(selectedPartPanel, BorderLayout.NORTH);
		add(editorButtonsPanel, BorderLayout.SOUTH);
	}

	public void refreshAll() {
		if (selectedPartPanel != null) {
			selectedPartPanel.repaint();
		}
	}

}
