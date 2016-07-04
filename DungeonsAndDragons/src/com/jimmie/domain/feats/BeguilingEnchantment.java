package com.jimmie.domain.feats;

import com.jimmie.domain.creatures.PlayerCharacter;

public class BeguilingEnchantment extends Feat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public FeatType getType() {
		return FeatType.BEGUILING_ENCHANTMENT;
	}

	@Override
	public String getName() {
		return "Beguiling Enchantment";
	}

	@Override
	public String getBenefit() {
		return "Enemy -2 to attacks after you hit it with charm power";
	}

	@Override
	public boolean meetsPrerequisites(PlayerCharacter pc) {
		return true;
	}

	@Override
	public void makeFeatChoices(PlayerCharacter pc) {
	}

}
