package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.util.Utils;

public class Wizard extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void initializeForEncounter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Add automatic trained skill(s).
		trainedSkills.add("Arcana");
		Utils.print("Automatically trained in Arcana.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Diplomacy");
		choices.add("Dungeoneering");
		choices.add("History");
		choices.add("Insight");
		choices.add("Nature");
		choices.add("Religion");
		
		Utils.print("Choose 3 of the following");
		for (int i = 0; i < 3; i++) {
			Utils.printValidStringChoices(choices);
			Utils.print("Your choice:");
			String choice = Utils.getValidInput(choices);
			trainedSkills.add(choice);
			choices.remove(choice);
		}
		
		return trainedSkills;
	}

}
