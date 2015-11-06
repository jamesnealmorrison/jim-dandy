package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class BurningBlizzard extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BURNING_BLIZZARD;
	}

	@Override
	public String getName() {
		return "Burning Blizzard";
	}

	@Override
	public String getBenefit() {
		return "+1 damage with acid or cold power";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getIntelligence() >= 13) && (pc.getWisdom() >= 13)) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
