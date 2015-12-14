package com.jimmie.util.aspects;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.classes.GuardianMight;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.util.Utils;

public aspect ArmorAbilityBonusAspect {
	public pointcut getArmorAbilityBonus(DndCharacter c) : execution(* com.jimmie.domain.creatures.*.getArmorAbilityBonus(..))
	&& target(c);
	
	int around(DndCharacter c) : getArmorAbilityBonus(c) {
		// Warden Guardian Might
		if (Warden.class.isAssignableFrom(c.getDndClass().getClass())) {
			// Earthstrength
			if (((Warden) c.getDndClass()).getGuardianMight() == GuardianMight.EARTHSTRENGTH) {
				if (c.getReadiedArmor().isLightArmor()) {
					int constitution = c.getAbilityModifier(AbilityType.CONSTITUTION);
					int dexterity = c.getAbilityModifier(AbilityType.DEXTERITY);
					int intelligence = c.getAbilityModifier(AbilityType.INTELLIGENCE);
					
					if ((constitution > dexterity) && (constitution > intelligence)) {
						return constitution;
					}
				}
			} else if (((Warden) c.getDndClass()).getGuardianMight() == GuardianMight.WILDBLOOD) {
				if (c.getReadiedArmor().isLightArmor()) {
					int wisdom = c.getAbilityModifier(AbilityType.WISDOM);
					int dexterity = c.getAbilityModifier(AbilityType.DEXTERITY);
					int intelligence = c.getAbilityModifier(AbilityType.INTELLIGENCE);
					
					if ((wisdom > dexterity) && (wisdom > intelligence)) {
						return wisdom;
					}
				}
			}
		}
		int returnVal = proceed(c);
		Utils.print("In ArmorAbility aspect. Returning " + returnVal);
		return returnVal;
	}
}
