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
				Utils.print("I put the framework in place for Warden Font of Life.  As soon as I start coding any effects that need saving throws, implement font of life");
			}
		}
		
	}
}
