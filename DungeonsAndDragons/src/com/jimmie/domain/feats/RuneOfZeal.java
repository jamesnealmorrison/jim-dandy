package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Runepriest;
import com.jimmie.domain.creatures.PlayerCharacter;

public class RuneOfZeal extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RUNE_OF_ZEAL;
	}

	@Override
	public String getName() {
		return "Rune Of Zeal";
	}

	@Override
	public String getBenefit() {
		return "Bonus to Athletics and Endurance equal to your number of rune feats";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Runepriest.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
