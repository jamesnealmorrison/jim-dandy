package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.armor.ArmorGroup;

public class ShieldProficiencyHeavy extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.SHIELD_PROFICIENCY_HEAVY;
	}

	@Override
	public String getName() {
		return "Shield Proficiency Heavy";
	}

	@Override
	public String getBenefit() {
		return "Proficiency with heavy shields";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getStrength() >= 15) && (pc.getArmorGroupProficiencies().contains(ArmorGroup.LIGHT_SHIELD))) {
			if (!pc.getArmorGroupProficiencies().contains(ArmorGroup.HEAVY_SHIELD)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
