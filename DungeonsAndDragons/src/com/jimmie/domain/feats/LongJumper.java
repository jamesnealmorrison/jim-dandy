package com.jimmie.domain.feats;

import com.jimmie.domain.SkillType;
import com.jimmie.domain.creatures.PlayerCharacter;

public class LongJumper extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.LONG_JUMPER;
	}

	@Override
	public String getName() {
		return "Long Jumper";
	}

	@Override
	public String getBenefit() {
		return "Make standing jumps as if from a running start, +1 to athletics";
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
