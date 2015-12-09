package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Runepriest;
import com.jimmie.domain.creatures.PlayerCharacter;

public class RuneOfVengeance extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RUNE_OF_VENGEANCE;
	}

	@Override
	public String getName() {
		return "Rune Of Vengeance";
	}

	@Override
	public String getBenefit() {
		return "Bonus to damage equal to your number of rune feats after you are first bloodied";
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
