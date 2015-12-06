package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Barbarian;
import com.jimmie.domain.classes.FeralMight;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedRagebloodVigor extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_RAGEBLOOD_VIGOR;
	}

	@Override
	public String getName() {
		return "Improved Rageblood Vigor";
	}

	@Override
	public String getBenefit() {
		return "Gain 5 extra temporary hp from Rageblood Vigor";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Barbarian.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Barbarian) pc.getDndClass()).getFeralMight() == FeralMight.RAGEBLOOD_VIGOR) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
