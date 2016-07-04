package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Shifter;
import com.jimmie.domain.creatures.ShifterType;

public class BlurringClaws extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BLURRING_CLAWS;
	}

	@Override
	public String getName() {
		return "Blurring Claws";
	}

	@Override
	public String getBenefit() {
		return "+2 damage with combat advantage during razorclaw shifting";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Shifter.class.isAssignableFrom(pc.getRace().getClass())) {
			if (((Shifter) pc.getRace()).getShifterType() == ShifterType.RAZORCLAW) {
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
