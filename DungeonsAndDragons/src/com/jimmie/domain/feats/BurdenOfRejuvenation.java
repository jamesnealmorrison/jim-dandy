package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Wilden;

public class BurdenOfRejuvenation extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BURDEN_OF_REJUVENATION;
	}

	@Override
	public String getName() {
		return "Burden of Rejuvenation";
	}

	@Override
	public String getBenefit() {
		return "Unconscious ally you can see heals when you use racial power";
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
