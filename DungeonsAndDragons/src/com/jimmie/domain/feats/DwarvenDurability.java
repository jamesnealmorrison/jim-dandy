package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.Dwarf;
import com.jimmie.domain.creatures.PlayerCharacter;

public class DwarvenDurability extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.DWARVEN_DURABILITY;
	}

	@Override
	public String getName() {
		return "Dwarven Durability";
	}

	@Override
	public String getBenefit() {
		return "Increase number of healing surges, healing surge value";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		// Paragon tier
		if (pc.getLevel() >= 10) {
			if (Dwarf.class.isInstance(pc.getRace())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
