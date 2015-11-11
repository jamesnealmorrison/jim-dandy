package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.armor.ArmorGroup;

public class ArmorProficiencyScale extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ARMOR_PROFICIENCY_SCALE;
	}

	@Override
	public String getName() {
		return "Armor Proficiency Scale";
	}

	@Override
	public String getBenefit() {
		return "Training with scale armor";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getStrength() >= 13) && (pc.getConstitution() >= 13) && (pc.getArmorGroupProficiencies().contains(ArmorGroup.CHAINMAIL))) {
			if (!pc.getArmorGroupProficiencies().contains(ArmorGroup.SCALE)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
