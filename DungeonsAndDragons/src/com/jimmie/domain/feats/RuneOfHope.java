package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Runepriest;
import com.jimmie.domain.creatures.PlayerCharacter;

public class RuneOfHope extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RUNE_OF_HOPE;
	}

	@Override
	public String getName() {
		return "Rune Of Hope";
	}

	@Override
	public String getBenefit() {
		return "Rune of mending target also gains temporary hp";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Runepriest.class.isAssignableFrom(pc.getDndClass().getClass())) {
			// All Runepriests have it.
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
