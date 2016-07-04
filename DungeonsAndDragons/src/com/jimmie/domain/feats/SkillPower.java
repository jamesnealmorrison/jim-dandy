package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class SkillPower extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SKILL_POWER;
	}

	@Override
	public String getName() {
		return "Skill Power";
	}

	@Override
	public String getBenefit() {
		return "Gain skill power of your level or lower";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getLevel() >= 2) {
			return false;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
