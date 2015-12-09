package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Ranger;
import com.jimmie.domain.classes.Warlock;
import com.jimmie.domain.creatures.PlayerCharacter;

public class PrimeBurst extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.PRIME_BURST;
	}

	@Override
	public String getName() {
		return "Prime Burst";
	}

	@Override
	public String getBenefit() {
		return "Prime Shot benefit also applies to area burst attacks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Book says "Prime Shot".  From what I can see, Rangers and Warlocks are the classes that have this.
		if ((Ranger.class.isAssignableFrom(pc.getDndClass().getClass())) || (Warlock.class.isAssignableFrom(pc.getDndClass().getClass()))) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
