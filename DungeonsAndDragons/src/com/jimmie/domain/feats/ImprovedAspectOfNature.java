package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Wilden;

public class ImprovedAspectOfNature extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_ASPECT_OF_NATURE;
	}

	@Override
	public String getName() {
		return "Improved Aspect Of Nature";
	}

	@Override
	public String getBenefit() {
		return "Gain additional benefit from use of your racial power";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Wilden.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
