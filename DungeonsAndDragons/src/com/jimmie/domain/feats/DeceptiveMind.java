package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Battlemind;
import com.jimmie.domain.classes.PsionicStudy;
import com.jimmie.domain.creatures.PlayerCharacter;

public class DeceptiveMind extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DECEPTIVE_MIND;
	}

	@Override
	public String getName() {
		return "Deceptive Mind";
	}

	@Override
	public String getBenefit() {
		return "+2 to all defenses when using battle resilience";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Battlemind.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Battlemind) pc.getDndClass()).getPsionicStudy() == PsionicStudy.BATTLE_RESILIENCE) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
