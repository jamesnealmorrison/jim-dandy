package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.armor.ArmorType;

public class BatteringShield extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BATTERING_SHIELD;
	}

	@Override
	public String getName() {
		return "Battering Shield";
	}

	@Override
	public String getBenefit() {
		return "Push or slide target 1 additional square when using shield";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		for (ArmorType armorType : pc.getArmorTypeProficiencies()) {
			if (armorType == ArmorType.HEAVY_SHIELD) {
				return true;
			}
		}
		for (ArmorGroup armorGroup : pc.getArmorGroupProficiencies()) {
			if (armorGroup == ArmorGroup.HEAVY_SHIELD) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
		// TODO Auto-generated method stub

	}

}
