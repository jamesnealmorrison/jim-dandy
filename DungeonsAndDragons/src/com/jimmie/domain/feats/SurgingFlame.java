package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class SurgingFlame extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SURGING_FLAME;
	}

	@Override
	public String getName() {
		return "Surging Flame";
	}

	@Override
	public String getBenefit() {
		return "Fire-resistant target takes extra damage from fire powers";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
