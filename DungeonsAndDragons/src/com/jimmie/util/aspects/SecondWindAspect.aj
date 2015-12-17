package com.jimmie.util.aspects;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.GuardianMight;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.Creature;

import com.jimmie.util.Utils;

public aspect SecondWindAspect {
	public pointcut secondWind(Creature creature) : execution(* com.jimmie.powers.SecondWind.process(..))
	&& args(creature);
	
	after(Creature creature) : secondWind(creature) {
		// Warden Earthstrength:
		if (creature.getDndClass() != null) {
			if (Warden.class.isAssignableFrom(creature.getDndClass().getClass())) {
				if (((Warden) creature.getDndClass()).getGuardianMight() == GuardianMight.EARTHSTRENGTH) {
					Utils.print("Because you have earthstrength, giving a temporary bonus of " + creature.getAbilityModifier(AbilityType.CONSTITUTION) + " to your AC.");
					creature.setTemporaryEffect(creature.getAbilityModifier(AbilityType.CONSTITUTION), creature.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, creature, TemporaryEffectType.ARMOR_CLASS_MODIFIER);
				}
			}
		}
	}
}
