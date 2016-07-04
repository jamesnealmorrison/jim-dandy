package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.HalfOrc;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ThirstForBattle extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.THIRST_FOR_BATTLE;
	}

	@Override
	public String getName() {
		return "Thirst For Battle";
	}

	@Override
	public String getBenefit() {
		return "+3 to initiative and one additional healing surge";
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
