package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Avenger;
import com.jimmie.domain.classes.Cleric;
import com.jimmie.domain.classes.Invoker;
import com.jimmie.domain.classes.Paladin;
import com.jimmie.domain.creatures.Deity;
import com.jimmie.domain.creatures.PlayerCharacter;

public class KordsFavor extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.KORDS_FAVOR;
	}

	@Override
	public String getName() {
		return "Kord's Favor";
	}

	@Override
	public String getBenefit() {
		return "Use Channel Divinity to invoke Kord's favor";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((Avenger.class.isAssignableFrom(pc.getDndClass().getClass())) ||
				(Cleric.class.isAssignableFrom(pc.getDndClass().getClass())) ||
				(Invoker.class.isAssignableFrom(pc.getDndClass().getClass())) ||
				(Paladin.class.isAssignableFrom(pc.getDndClass().getClass()))) {
			if (pc.getDeity() == Deity.KORD) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
