package com.jimmie.domain.feats;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.util.Utils;

public class SkillTraining extends Feat {
	private String trainedSkill;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SKILL_TRAINING;
	}

	@Override
	public String getName() {
		return "Skill Training";
	}

	@Override
	public String getBenefit() {
		return "Gain training in one skill";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		Utils.print("Choose a skill to train in:");

		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Acrobatics");
		choices.add("Arcana");
		choices.add("Athletics");
		choices.add("Bluff");
		choices.add("Diplomacy");
		choices.add("Dungeoneering");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("History");
		choices.add("Insight");
		choices.add("Intimidate");
		choices.add("Nature");
		choices.add("Perception");
		choices.add("Religion");
		choices.add("Stealth");
		choices.add("Streetwise");
		choices.add("Thievery");
		Utils.printValidStringChoices(choices);
		Utils.print("Your choice:");
		
		trainedSkill = Utils.getValidInput(choices);
	}

}
