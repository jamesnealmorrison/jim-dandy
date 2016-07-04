package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Gnome;
import com.jimmie.domain.creatures.PlayerCharacter;

public class GroupStealth extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.GROUP_STEALTH;
	}

	@Override
	public String getName() {
		return "Group Stealth";
	}

	@Override
	public String getBenefit() {
		return "Nearby allies gain +2 to Stealth checks";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Gnome.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
