package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Minotaur;
import com.jimmie.domain.creatures.PlayerCharacter;

public class BrutalFerocity extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BRUTAL_FEROCITY;
	}

	@Override
	public String getName() {
		return "Brutal Ferocity";
	}

	@Override
	public String getBenefit() {
		return "+2 attack and damage with attack granted by Ferocity";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Minotaur.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
