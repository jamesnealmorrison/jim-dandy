package com.jimmie.util.aspects;

import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.util.Utils;

public aspect StartOfTurnAspect {
	public pointcut startOfTurn(Creature creature) : execution(* com.jimmie.domain.creatures.*.startOfTurn(..))
	&& target(creature);
	
	before(Creature creature) : startOfTurn(creature) {
		// Warden Font Of Life:
		if (creature.getDndClass() != null) {
			if (Warden.class.isAssignableFrom(creature.getDndClass().getClass())) {
				Utils.print("As a warden, with Font of Life, you can make one saving throw at the start of your turn.");
				creature.performSavingThrows(1, 0);
			}
		}		
	}
}
