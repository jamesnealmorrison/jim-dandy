package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Runepriest;
import com.jimmie.domain.creatures.PlayerCharacter;

public class RuneOfEloquence extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RUNE_OF_ELOQUENCE;
	}

	@Override
	public String getName() {
		return "Rune of Eloquence";
	}

	@Override
	public String getBenefit() {
		return "Bonus to Bluff and Diplomacy equal to your number of rune feats";
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
		// TODO Auto-generated method stub

	}

}
