package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.armor.ArmorGroup;

public class ShieldProficiencyLight extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SHIELD_PROFICIENCY_LIGHT;
	}

	@Override
	public String getName() {
		return "Shield Proficiency (Light)";
	}

	@Override
	public String getBenefit() {
		return "Proficiency with light shields";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if (pc.getStrength() >= 13) {
			if (!pc.getArmorGroupProficiencies().contains(ArmorGroup.LIGHT_SHIELD)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
