package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.util.Utils;

public class Ranger extends DndClass {

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
		List<String> choices = new ArrayList<String>();
		choices.add("Dungeoneering");
		choices.add("Nature");
		Utils.print("Choose one:");
		Utils.printValidStringChoices(choices);
		Utils.print("Your choice:");
		String choice = Utils.getValidInput(choices);
		if ("Dungeoneering".equalsIgnoreCase(choice)) {
			trainedSkills.add("Dungeoneering");
		} else {
			trainedSkills.add("Nature");
		}
		choices.remove(choice);
		
		// Now make selections.
		choices.add("Acrobatics");
		choices.add("Athletics");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("Perception");
		choices.add("Stealth");
		
		Utils.print("Choose 4 of the following");
		for (int i = 0; i < 4; i++) {
			Utils.printValidStringChoices(choices);
			Utils.print("Your choice:");
			choice = Utils.getValidInput(choices);
			trainedSkills.add(choice);
			choices.remove(choice);
		}
		
		return trainedSkills;
	}

}
