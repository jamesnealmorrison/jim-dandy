package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Gnome;

public class FeyTrickster extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.FEY_TRICKSTER;
	}

	@Override
	public String getName() {
		return "Fey Trickster";
	}

	@Override
	public String getBenefit() {
		// TODO: Probably have to modify these classes to add Gnome as a race that can have it.
		return "Gain mage hand and prestidigitation as encounter powers";
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
