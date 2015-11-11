package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Halfling;
import com.jimmie.domain.creatures.PlayerCharacter;

public class HalflingAgility extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.HALFLING_AGILITY;
	}

	@Override
	public String getName() {
		return "Halfling Agility";
	}

	@Override
	public String getBenefit() {
		return "Attacker takes a -2 penalty with second chance reroll";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Halfling.class.isInstance(pc.getRace())) {
			// The book says that Second Chance is a prerequisite, but all Halflings have it, so I'm not checking for it.
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
