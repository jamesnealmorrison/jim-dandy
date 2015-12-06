package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Sorcerer;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ArcaneSpellfury extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ARCANE_SPELLFURY;
	}

	@Override
	public String getName() {
		return "Arcane Spellfury";
	}

	@Override
	public String getBenefit() {
		return "+1 to attack rolls after hitting with sorcerer at-will attack";
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
