package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Shardmind;

public class PsychicFocus extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.PSYCHIC_FOCUS;
	}

	@Override
	public String getName() {
		return "Psychic Focus";
	}

	@Override
	public String getBenefit() {
		return "+2 or more damage with psychic powers";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Shardmind.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
