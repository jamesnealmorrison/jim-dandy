package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Druid;
import com.jimmie.domain.classes.PrimalAspect;
import com.jimmie.domain.creatures.PlayerCharacter;

public class PrimalFury extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.PRIMAL_FURY;
	}

	@Override
	public String getName() {
		return "Primal Fury";
	}

	@Override
	public String getBenefit() {
		return "+1 to attacks with primal powers against bloodied enemies";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Druid.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Druid) pc.getDndClass()).getPrimalAspect() == PrimalAspect.PRIMAL_PREDATOR) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
