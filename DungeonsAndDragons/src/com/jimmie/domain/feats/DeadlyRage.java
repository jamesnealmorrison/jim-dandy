package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Barbarian;
import com.jimmie.domain.creatures.PlayerCharacter;

public class DeadlyRage extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DEADLY_RAGE;
	}

	@Override
	public String getName() {
		return "Deadly Rage";
	}

	@Override
	public String getBenefit() {
		return "+1 damage while raging";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Barbarian.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
