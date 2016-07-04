package com.jimmie.domain.feats;

import com.jimmie.domain.classes.CompanionSpirit;
import com.jimmie.domain.classes.Shaman;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ProtectorSpiritAdept extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.PROTECTOR_SPIRIT_ADEPT;
	}

	@Override
	public String getName() {
		return "Protector Spirit Adept";
	}

	@Override
	public String getBenefit() {
		return "Allies adjacent to spirit companion gain +1 Fort, Ref, Will";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Shaman.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Shaman) pc.getDndClass()).getCompanionSpirit() == CompanionSpirit.PROTECTOR) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {

	}

}
