package com.jimmie.util.aspects;

import com.jimmie.domain.Position;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.encounters.Encounter;
import com.jimmie.encounters.KoboldLairOutsideEncounter;
import com.jimmie.util.Utils;

public aspect AttackRollForKoboldLairOutsideEncounterAspect {
	public pointcut attackRoll(Creature creature) : execution(int com.jimmie.domain.creatures.Creature.attackRoll(..))
	&& this(creature);

	int around(Creature creature) : attackRoll(creature) {
		int result = proceed(creature);		

		if (KoboldLairOutsideEncounter.class.isAssignableFrom(Encounter.getEncounter().getClass())) {
			// See if the character is standing in the Sacred Circle
			Position p = creature.getCurrentPosition();
			if ((p.getX() >= 6) && (p.getX() <= 8) && (p.getY() >= 10) && (p.getY() <= 12)) {
				Utils.print(creature.getName() + " is standing in the sacred circle and gets a +1 bonus to the attack roll.");
				result++;
			}
		}
		return result;
	}	
}
