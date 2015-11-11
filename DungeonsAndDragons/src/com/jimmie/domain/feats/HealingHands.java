package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Paladin;
import com.jimmie.domain.creatures.PlayerCharacter;

public class HealingHands extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.HEALING_HANDS;
	}

	@Override
	public String getName() {
		return "Healing Hands";
	}

	@Override
	public String getBenefit() {
		return "Add Cha modifier to damage healed with lay on hands";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Paladin.class.isInstance(pc.getDndClass())) {
			// The book says that Lay On Hands power is a prerequisite, but all Paladins have it, so I'm not checking for it.
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
