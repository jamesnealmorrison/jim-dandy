package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.util.Utils;

public class Runepriest extends DndClass {

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
		trainedSkills.add("Religion");
		Utils.print("Automatically trained in Religion.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Arcana");
		choices.add("Athletics");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("History");
		choices.add("Insight");
		choices.add("Thievery");
		
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
