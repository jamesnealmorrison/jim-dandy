package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Shifter;
import com.jimmie.domain.creatures.ShifterType;

public class GorebruteCharge extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.GOREBRUTE_CHARGE;
	}

	@Override
	public String getName() {
		return "Gorebrute Charge";
	}

	@Override
	public String getBenefit() {
		return "+3 damage on charge attacks during longtooth shifting";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Shifter.class.isAssignableFrom(pc.getRace().getClass())) {
			if (((Shifter) pc.getRace()).getShifterType() == ShifterType.LONGTOOTH) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
