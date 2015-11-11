package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Tiefling;

public class HellfireBlood extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.HELLFIRE_BLOOD;
	}

	@Override
	public String getName() {
		return "Hellfire Blood";
	}

	@Override
	public String getBenefit() {
		return "+1 attack and damage with fire and fear powers";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Tiefling.class.isInstance(pc.getRace())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
