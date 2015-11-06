package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Elf;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ElvenPrecision extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ELVEN_PRECISION;
	}

	@Override
	public String getName() {
		return "Elven Precision";
	}

	@Override
	public String getBenefit() {
		return "+2 to reroll with elven accuracy";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Elf.class.isInstance(pc.getRace())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
