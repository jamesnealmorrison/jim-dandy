package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.HalfOrc;
import com.jimmie.domain.creatures.PlayerCharacter;

public class SavageAssault extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SAVAGE_ASSAULT;
	}

	@Override
	public String getName() {
		return "Savage Assault";
	}

	@Override
	public String getBenefit() {
		return "Target of furious assault takes -1 to defenses";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (HalfOrc.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
