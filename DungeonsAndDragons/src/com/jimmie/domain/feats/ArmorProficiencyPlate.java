package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.armor.ArmorGroup;

public class ArmorProficiencyPlate extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.ARMOR_PROFICIENCY_PLATE;
	}

	@Override
	public String getName() {
		return "Armor Proficiency Plate";
	}

	@Override
	public String getBenefit() {
		return "Training with plate armor";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		if ((pc.getStrength() >= 15) && (pc.getConstitution() >= 15) && (pc.getArmorGroupProficiencies().contains(ArmorGroup.SCALE))) {
			if (!pc.getArmorGroupProficiencies().contains(ArmorGroup.PLATE)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
