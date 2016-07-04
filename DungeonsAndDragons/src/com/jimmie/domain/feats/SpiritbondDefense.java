package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Seeker;
import com.jimmie.domain.classes.SeekerBond;
import com.jimmie.domain.creatures.PlayerCharacter;

public class SpiritbondDefense extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SPIRITBOND_DEFENSE;
	}

	@Override
	public String getName() {
		return "Spiritbond Defense";
	}

	@Override
	public String getBenefit() {
		return "Use second wind and grant bonus to defense to adjacent ally";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Seeker.class.isAssignableFrom(pc.getDndClass().getClass())) {
			if (((Seeker) pc.getDndClass()).getSeekerBond() == SeekerBond.SPIRITBOND) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
