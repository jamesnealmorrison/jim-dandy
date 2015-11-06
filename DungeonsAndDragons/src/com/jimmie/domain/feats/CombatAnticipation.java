package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class CombatAnticipation extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.COMBAT_ANTICIPATION;
	}

	@Override
	public String getName() {
		return "Combat Anticipation";
	}

	@Override
	public String getBenefit() {
		return "+1 to defenses against ranged, area, close attacks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
