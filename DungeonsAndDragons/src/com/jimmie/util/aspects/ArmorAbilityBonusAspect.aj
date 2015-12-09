package com.jimmie.util.aspects;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.classes.GuardianMight;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.Character;

public aspect ArmorAbilityBonusAspect {
	public pointcut getArmorAbilityBonus(Character c) : execution(* com.jimmie.domain.creatures.*.getArmorAbilityBonus(..))
	&& target(c);
	
	int around(Character c) : getArmorAbilityBonus(c) {
		// Warden Guardian Might
		if (Warden.class.isAssignableFrom(c.getDndClass().getClass())) {
			if (((Warden) c.getDndClass()).getGuardianMight() == GuardianMight.EARTHSTRENGTH) {
				if (c.getReadiedArmor().isLightArmor()) {
					int constitution = c.getAbilityModifier(AbilityType.CONSTITUTION);
					int dexterity = c.getAbilityModifier(AbilityType.DEXTERITY);
					int intelligence = c.getAbilityModifier(AbilityType.INTELLIGENCE);
					
					if ((constitution > dexterity) && (constitution > intelligence)) {
						return constitution;
					}
				}
			}
		}
		return c.getArmorAbilityBonus();
	}
}
