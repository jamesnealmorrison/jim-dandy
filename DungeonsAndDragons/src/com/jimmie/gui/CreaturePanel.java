package com.jimmie.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.jimmie.domain.creatures.Creature;

public class CreaturePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Creature creature;
	private JLabel hitPointsLabel;
	public static final int WIDTH = 200;
	public static final int HEIGHT = 40;
	
	public CreaturePanel(Creature creature) {
		
		LayoutManager layout = new java.awt.GridLayout(2, 1);
		this.setLayout(layout);
		Dimension dimension = new Dimension(WIDTH,HEIGHT);
		this.setPreferredSize(dimension);
		this.creature = creature;
		JLabel nameLabel = new JLabel(creature.getName());
		add(nameLabel);
		hitPointsLabel = new JLabel("Hit Points: " + creature.getCurrentHitPoints());
		add(hitPointsLabel);
		
		Border border = new javax.swing.border.LineBorder(Color.BLACK);
		this.setBorder(border);
	}

	public void updateData() {
		hitPointsLabel.setText("Hit Points: " + creature.getCurrentHitPoints());
	}

}
