package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Githzerai;

public class IronResolveOfZerthadlun extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.IRON_RESOLVE_OF_ZERTHADLUN;
	}

	@Override
	public String getName() {
		return "Iron Resolve Of Zerthadlun";
	}

	@Override
	public String getBenefit() {
		return "+2 to saving throws if you have at least 1 power point";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (Githzerai.class.isAssignableFrom(pc.getRace().getClass())) {
			return true;
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
