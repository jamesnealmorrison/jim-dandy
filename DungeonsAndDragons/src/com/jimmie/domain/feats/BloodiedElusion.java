package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Seeker;
import com.jimmie.domain.classes.SeekerBond;
import com.jimmie.domain.creatures.PlayerCharacter;

public class BloodiedElusion extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BLOODIED_ELUSION;
	}

	@Override
	public String getName() {
		return "Bloodied Elusion";
	}

	@Override
	public String getBenefit() {
		return "Shift 1 square when bloodied by any attack";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Seeker.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Seeker) pc.getDndClass()).getSeekerBond() == SeekerBond.BLOODBOND) {
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
