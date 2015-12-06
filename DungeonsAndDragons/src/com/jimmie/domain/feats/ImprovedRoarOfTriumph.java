package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Barbarian;
import com.jimmie.domain.classes.FeralMight;
import com.jimmie.domain.creatures.PlayerCharacter;

public class ImprovedRoarOfTriumph extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IMPROVED_ROAR_OF_TRIUMPH;
	}

	@Override
	public String getName() {
		return "Improved Roar of Triumph";
	}

	@Override
	public String getBenefit() {
		return "Roar of triumph burst is larger grants you +2 damage";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Barbarian.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Barbarian) pc.getDndClass()).getFeralMight() == FeralMight.THANEBORN_TRIUMPH) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
