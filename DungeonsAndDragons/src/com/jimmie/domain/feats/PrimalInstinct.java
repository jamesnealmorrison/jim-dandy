package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Druid;
import com.jimmie.domain.classes.PrimalAspect;
import com.jimmie.domain.creatures.PlayerCharacter;

public class PrimalInstinct extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.PRIMAL_INSTINCT;
	}

	@Override
	public String getName() {
		return "Primal Instinct";
	}

	@Override
	public String getBenefit() {
		return "Ally can reroll initiative";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Druid.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Druid) pc.getDndClass()).getPrimalAspect() == PrimalAspect.PRIMAL_GUARDIAN) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
