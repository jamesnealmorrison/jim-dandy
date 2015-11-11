package com.jimmie.domain.feats;

import com.jimmie.domain.SkillType;
import com.jimmie.domain.creatures.PlayerCharacter;

public class RitualCaster extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RITUAL_CASTER;
	}

	@Override
	public String getName() {
		return "Ritual Caster";
	}

	@Override
	public String getBenefit() {
		return "Master and perform rituals";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getSkill(SkillType.ARCANA).isTrained()) || (pc.getSkill(SkillType.RELIGION).isTrained())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
