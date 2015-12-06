package com.jimmie.domain.feats;

import com.jimmie.domain.SkillType;
import com.jimmie.domain.creatures.PlayerCharacter;

public class CombatMedic extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.COMBAT_MEDIC;
	}

	@Override
	public String getName() {
		return "Combat Medic";
	}

	@Override
	public String getBenefit() {
		return "Stabalize the dying as minor action, +2 to Heal checks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getSkill(SkillType.HEAL).isTrained()) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
