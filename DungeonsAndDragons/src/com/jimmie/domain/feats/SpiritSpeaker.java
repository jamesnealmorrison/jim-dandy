package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Shaman;
import com.jimmie.domain.creatures.PlayerCharacter;

public class SpiritSpeaker extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SPIRIT_SPEAKER;
	}

	@Override
	public String getName() {
		return "Spirit Speaker";
	}

	@Override
	public String getBenefit() {
		return "Speak with spirits grants skill bonus to ally";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Shaman.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
