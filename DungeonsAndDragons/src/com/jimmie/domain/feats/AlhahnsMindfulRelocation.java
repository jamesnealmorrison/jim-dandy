package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Githzerai;
import com.jimmie.domain.creatures.PlayerCharacter;

public class AlhahnsMindfulRelocation extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ALHAHNS_MINDFUL_RELOCATION;
	}

	@Override
	public String getName() {
		return "Alhahn's Mindful Relocation";
	}

	@Override
	public String getBenefit() {
		return "Shift your speed when using Shifting Fortunes";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Githzerai.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
