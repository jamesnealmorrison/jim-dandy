package com.jimmie.domain.items;

import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.util.Utils;

public class PotionOfHealing extends Potion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Price getPrice() {
		return new Price(50,CoinType.GOLD_PIECE);
	}

	@Override
	public int getWeight() {
		return 0;
	}

	@Override
	public GearType getGearType() {
		return GearType.POTION_OF_HEALING;
	}


	@Override
	public String getName() {
		return "Potion Of Healing";
	}

	@Override
	public void quaff(Creature user) {
		if (DndCharacter.class.isAssignableFrom(user.getClass())) {
			DndCharacter dndChar = (DndCharacter) user;
			// Make sure they can spend a healing surge.
			if (dndChar.getCurrentSurgeUses() >= dndChar.getHealingSurgesPerDay()) {
				Utils.print("Drinking this potion did nothing since " + user.getName() + " can't spend a healing surge.");
			} else {
				dndChar.setCurrentSurgeUses(dndChar.getCurrentSurgeUses() + 1);
				dndChar.heal(10);
			}
		}
	}

}
