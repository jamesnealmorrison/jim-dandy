package com.jimmie.util.aspects;

import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.monsters.RatSwarm;
import com.jimmie.encounters.Encounter;
import com.jimmie.encounters.GoblinGuardRoomEncounter;

public aspect RatSwarmInvisibilityForGoblinGuardRoomAspect {
	public pointcut isInvisibleTo(Creature creature) : execution(boolean com.jimmie.domain.creatures.*.isInvisibleTo(..))
	&& target(creature);

	boolean around(Creature creature) : isInvisibleTo(creature) {
		if (GoblinGuardRoomEncounter.class.isAssignableFrom(Encounter.getEncounter().getClass())) {
			GoblinGuardRoomEncounter encounter = (GoblinGuardRoomEncounter) Encounter.getEncounter();
			if (RatSwarm.class.isAssignableFrom(creature.getClass())) {
				if (encounter.isPitTrapActivated()) {
					return proceed(creature);
				} else {
					return true;
				}
			}
		}
		return proceed(creature);
	}

}
