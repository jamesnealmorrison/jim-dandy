package com.jimmie.domain.feats;

import com.jimmie.domain.classes.Invoker;
import com.jimmie.domain.creatures.PlayerCharacter;

public class InvokerDefense extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.INVOKER_DEFENSE;
	}

	@Override
	public String getName() {
		return "Invoker Defense";
	}

	@Override
	public String getBenefit() {
		return "+2 AC when you hit nearby enemy";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Invoker.class.isAssignableFrom(pc.getDndClass().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
