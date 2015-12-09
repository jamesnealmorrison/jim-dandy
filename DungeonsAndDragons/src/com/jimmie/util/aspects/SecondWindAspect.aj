package com.jimmie.util.aspects;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.classes.GuardianMight;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public aspect SecondWindAspect {
	public pointcut secondWind(Encounter encounter, Creature creature) : execution(* com.jimmie.powers.SecondWind.process(..))
	&& args(encounter, creature);
	
	after(Encounter encounter, Creature creature) : secondWind(encounter, creature) {
		// Warden Earthstrength:
		if (creature.getDndClass() != null) {
			if (Warden.class.isAssignableFrom(creature.getDndClass().getClass())) {
				if (((Warden) creature.getDndClass()).getGuardianMight() == GuardianMight.EARTHSTRENGTH) {
					Utils.print("Because you have earthstrength, giving a temporary bonus of " + creature.getAbilityModifier(AbilityType.CONSTITUTION) + " to your AC.");
					creature.setTemporaryArmorClassBonus(creature.getAbilityModifier(AbilityType.CONSTITUTION), creature.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, creature);
				}
			}
		}
	}
}
