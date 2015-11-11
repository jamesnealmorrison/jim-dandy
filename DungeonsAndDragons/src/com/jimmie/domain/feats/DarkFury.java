package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class DarkFury extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DARK_FURY;
	}

	@Override
	public String getName() {
		return "Dark Fury";
	}

	@Override
	public String getBenefit() {
		return "+1 damage with necrotic or psychic power";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getConstitution() >= 13) && (pc.getWisdom() >= 13)) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
