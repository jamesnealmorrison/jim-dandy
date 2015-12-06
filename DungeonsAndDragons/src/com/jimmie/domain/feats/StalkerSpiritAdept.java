package com.jimmie.domain.feats;

import com.jimmie.domain.classes.CompanionSpirit;
import com.jimmie.domain.classes.Shaman;
import com.jimmie.domain.creatures.PlayerCharacter;

public class StalkerSpiritAdept extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.STALKER_SPIRIT_ADEPT;
	}

	@Override
	public String getName() {
		return "Stalker Spirit Adept";
	}

	@Override
	public String getBenefit() {
		return "Allies adjacent to spirit companion can shift as a free action";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Shaman.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Shaman) pc.getDndClass()).getCompanionSpirit() == CompanionSpirit.STALKER) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
