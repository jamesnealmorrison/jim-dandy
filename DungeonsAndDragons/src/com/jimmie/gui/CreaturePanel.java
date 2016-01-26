package com.jimmie.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.jimmie.domain.Mark;
import com.jimmie.domain.TemporaryCombatAdvantage;
import com.jimmie.domain.TemporaryCondition;
import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.creatures.Creature;

public class CreaturePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Creature creature;
	private JLabel hitPointsLabel;
	private JLabel tempHitPointsLabel;
	private JLabel condition1Label;
	private JLabel condition2Label;
	private JLabel condition3Label;
	private JLabel condition4Label;
	private JLabel markLabel;
	public static final int WIDTH = 700;
	public static final int HEIGHT = 60;
	
	public CreaturePanel(Creature creature) {
		
		LayoutManager layout = new java.awt.GridLayout(4, 2);
		this.setLayout(layout);
		Dimension dimension = new Dimension(WIDTH,HEIGHT);
		this.setPreferredSize(dimension);
		this.creature = creature;
		JLabel nameLabel = new JLabel(creature.getName());
		add(nameLabel);
		condition1Label = new JLabel("1");
		condition2Label = new JLabel("2");
		condition3Label = new JLabel("3");
		condition4Label = new JLabel("4");
		add(condition1Label);
		hitPointsLabel = new JLabel("Hit Points: " + creature.getCurrentHitPoints() + "/" + creature.getMaxHitPoints());
		add(hitPointsLabel);
		add(condition2Label);
		tempHitPointsLabel = new JLabel("Temp HP: " + creature.getTemporaryHitPoints());
		add(tempHitPointsLabel);
		add(condition3Label);
		markLabel = new JLabel("");
		add(markLabel);
		add(condition4Label);
		
		Border border = new javax.swing.border.LineBorder(Color.BLACK);
		this.setBorder(border);
		invalidate();
	}

	public void updateData() {
		hitPointsLabel.setText("Hit Points: " + creature.getCurrentHitPoints() + "/" + creature.getMaxHitPoints());
		tempHitPointsLabel.setText("Temp HP: " + creature.getTemporaryHitPoints());
		int conditionIndex = 0;
		// See if the creature has any conditions.
		for (TemporaryEffect tempEffect : creature.getTemporaryEffects()) {
			conditionIndex++;
			String condString = null;
			if (TemporaryCondition.class.isAssignableFrom(tempEffect.getClass())) {
				condString = ((TemporaryCondition) tempEffect).getConditionType().toString();
			} else if (TemporaryCombatAdvantage.class.isAssignableFrom(tempEffect.getClass())) {
				condString = ((TemporaryCombatAdvantage) tempEffect).getTypeOfCombatAdvantage().toString();
			} else {
				condString = tempEffect.getEffectType().toString();
			}
			if (conditionIndex <= 4) {
				String modString = "";
				if (tempEffect.getModifier() != 0) {
					modString = " " + tempEffect.getModifier() + " ";
				}
				String text = modString + condString + " from " + tempEffect.getReason();
				if (conditionIndex == 1) {
					condition1Label.setText(text);
				}
				if (conditionIndex == 2) {
					condition2Label.setText(text);
				}
				if (conditionIndex == 3) {
					condition3Label.setText(text);
				}
				if (conditionIndex == 4) {
					condition4Label.setText(text);
				}
			}
		}
		if (conditionIndex == 0) {
			condition1Label.setText("");
			condition2Label.setText("");
			condition3Label.setText("");
			condition4Label.setText("");
		}
		if (conditionIndex == 1) {
			condition2Label.setText("");
			condition3Label.setText("");
			condition4Label.setText("");
		}
		if (conditionIndex == 2) {
			condition3Label.setText("");
			condition4Label.setText("");
		}
		if (conditionIndex == 3) {
			condition4Label.setText("");
		}
		// See if there are any marks on this creature
		if (creature.getMarks() != null) {
			for (Mark mark : creature.getMarks()) {
				markLabel.setText("Marked by " + mark.getMarker().getName() + ", " + mark.getMarkType());
			}
		}
	}
}
