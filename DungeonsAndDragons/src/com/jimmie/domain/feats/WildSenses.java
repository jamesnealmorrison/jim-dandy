package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Shifter;
import com.jimmie.domain.creatures.PlayerCharacter;

public class WildSenses extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.WILD_SENSES;
	}

	@Override
	public String getName() {
		return "Wild Senses";
	}

	@Override
	public String getBenefit() {
		return "Roll twice when following tracks, +3 to initiative";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Shifter.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
