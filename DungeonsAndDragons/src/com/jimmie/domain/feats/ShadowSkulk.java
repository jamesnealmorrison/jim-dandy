package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Gnome;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ShadowSkulk extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SHADOW_SKULK;
	}

	@Override
	public String getName() {
		return "Shadow Skulk";
	}

	@Override
	public String getBenefit() {
		return "Stay hidden when you miss with area or ranged attack";
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
