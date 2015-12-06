package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Deva;
import com.jimmie.domain.creatures.PlayerCharacter;

public class AuspiciousLineage extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.AUSPICIOUS_LINEAGE;
	}

	@Override
	public String getName() {
		return "Auspicious Lineage";
	}

	@Override
	public String getBenefit() {
		return "Roll d8 instead of d6 for memory of a thousand lifetimes";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Deva.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
