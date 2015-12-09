package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Shardmind;
import com.jimmie.domain.creatures.Wilden;

public class CleanseTheMadness extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.CLEANSE_THE_MADNESS;
	}

	@Override
	public String getName() {
		return "Cleanse the Madness";
	}

	@Override
	public String getBenefit() {
		return "+2 or more damage against aberrant creatures";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((Wilden.class.isAssignableFrom(pc.getRace().getClass()) || (Shardmind.class.isAssignableFrom(pc.getRace().getClass())))) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
