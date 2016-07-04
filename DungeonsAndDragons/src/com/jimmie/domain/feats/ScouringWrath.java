package com.jimmie.domain.feats;

import com.jimmie.domain.classes.DivineCovenant;
import com.jimmie.domain.classes.Invoker;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ScouringWrath extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SCOURING_WRATH;
	}

	@Override
	public String getName() {
		return "Scouring Wrath";
	}

	@Override
	public String getBenefit() {
		return "Armor of wrath gives target vulnerable 2";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Invoker.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Invoker) pc.getDndClass()).getDivineCovenant() == DivineCovenant.WRATH) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
