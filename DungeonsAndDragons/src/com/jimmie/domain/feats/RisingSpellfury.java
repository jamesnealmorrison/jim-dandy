package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Sorcerer;
import com.jimmie.domain.creatures.PlayerCharacter;

public class RisingSpellfury extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.RISING_SPELLFURY;
	}

	@Override
	public String getName() {
		return "Rising Spellfury";
	}

	@Override
	public String getBenefit() {
		return "+1 to attack after hitting single enemy with at-will power";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Sorcerer.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
