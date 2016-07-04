package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Githzerai;

public class MiryathsFirstStrike extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.MIRYATHS_FIRST_STRIKE;
	}

	@Override
	public String getName() {
		return "Miryath's First Strike";
	}

	@Override
	public String getBenefit() {
		return "Extra damage against creature that has not yet acted";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Githzerai.class.isAssignableFrom(pc.getRace().getClass())) {
			if (pc.getWisdom() >= 13) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
