package com.jimmie.domain.feats;

import com.jimmie.domain.SkillType;
import com.jimmie.domain.creatures.PlayerCharacter;

public class SureClimber extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SURE_CLIMBER;
	}

	@Override
	public String getName() {
		return "Sure Climber";
	}

	@Override
	public String getBenefit() {
		return "Climb at normal speed on any surface, +1 to Athletics";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getSkill(SkillType.ATHLETICS).isTrained()) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
