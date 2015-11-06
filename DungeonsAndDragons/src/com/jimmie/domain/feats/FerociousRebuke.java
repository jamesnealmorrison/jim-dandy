package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Tiefling;

public class FerociousRebuke extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.FEROCIOUS_REBUKE;
	}

	@Override
	public String getName() {
		return "Ferocious Rebuke";
	}

	@Override
	public String getBenefit() {
		return "Push 1 square with infernal wrath";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Tiefling.class.isInstance(pc.getRace())) {
			// The book says that Infernal Wrath is a prerequisite, but all Tieflings have it, so I'm not checking for it.
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
