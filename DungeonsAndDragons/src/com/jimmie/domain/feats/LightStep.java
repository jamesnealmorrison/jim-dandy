package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Elf;
import com.jimmie.domain.creatures.PlayerCharacter;

public class LightStep extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.LIGHT_STEP;
	}

	@Override
	public String getName() {
		return "Light Step";
	}

	@Override
	public String getBenefit() {
		return "Add to overland speed of group, +1 to Acrobatics and Stealth";
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
