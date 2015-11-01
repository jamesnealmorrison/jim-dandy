package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.util.Utils;

public class Rogue extends DndClass {

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
		trainedSkills.add("Stealth");
		trainedSkills.add("Thievery");
		Utils.print("Automatically trained in Stealth and Thievery.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Acrobatics");
		choices.add("Athletics");
		choices.add("Bluff");
		choices.add("Dungeoneering");
		choices.add("Insight");
		choices.add("Intimidate");
		choices.add("Perception");
		choices.add("Streetwise");
		
		Utils.print("Choose 4 of the following");
		for (int i = 0; i < 4; i++) {
			Utils.printValidStringChoices(choices);
			Utils.print("Your choice:");
			String choice = Utils.getValidInput(choices);
			trainedSkills.add(choice);
			choices.remove(choice);
		}
		
		return trainedSkills;
	}

}
