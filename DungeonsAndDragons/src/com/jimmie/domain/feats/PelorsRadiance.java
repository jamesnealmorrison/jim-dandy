package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Avenger;
import com.jimmie.domain.classes.Cleric;
import com.jimmie.domain.classes.Invoker;
import com.jimmie.domain.classes.Paladin;
import com.jimmie.domain.creatures.Deity;
import com.jimmie.domain.creatures.PlayerCharacter;

public class PelorsRadiance extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.PELORS_RADIANCE;
	}

	@Override
	public String getName() {
		return "Pelor's Radiance";
	}

	@Override
	public String getBenefit() {
		return "Use Channel Divinity to invoke Pelor's radiance";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((Avenger.class.isAssignableFrom(pc.getDndClass().getClass())) ||
				(Cleric.class.isAssignableFrom(pc.getDndClass().getClass())) ||
				(Invoker.class.isAssignableFrom(pc.getDndClass().getClass())) ||
				(Paladin.class.isAssignableFrom(pc.getDndClass().getClass()))) {
			if (pc.getDeity() == Deity.PELOR) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
