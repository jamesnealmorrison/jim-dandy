package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.HalfOrc;
import com.jimmie.domain.creatures.PlayerCharacter;

public class AngerUnleashed extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ANGER_UNLEASHED;
	}

	@Override
	public String getName() {
		return "Anger Unleashed";
	}

	@Override
	public String getBenefit() {
		return "+2 attack for 1 round after becoming bloodied";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (HalfOrc.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {

	}

}
