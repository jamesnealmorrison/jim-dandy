package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Wilden;

public class SecretOfReawakening extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SECRET_OF_REAWAKENING;
	}

	@Override
	public String getName() {
		return "Secret of Reawakening";
	}

	@Override
	public String getBenefit() {
		return "Change aspect and regain racial power after you drop to 0 hp";
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
