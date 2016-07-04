package com.jimmie.util.aspects;

import com.jimmie.domain.MovementType;
import com.jimmie.domain.Position;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.encounters.Encounter;
import com.jimmie.encounters.GoblinGuardRoomEncounter;
import com.jimmie.util.Utils;

public aspect MovementForGoblinGuardRoomEncounterAspect {
	public pointcut moveCreature(String direction, MovementType movementType, Creature creature) : execution(* com.jimmie.domain.creatures.*.moveCreature(..))
	&& args(direction, movementType) && target(creature);

	Position previousPosition = null;
	
	// Before they move, record where they were.
	before(String direction, MovementType movementType, Creature creature) : moveCreature(direction, movementType, creature) {
		previousPosition = new Position(creature.getCurrentPosition().getX(), creature.getCurrentPosition().getY());
	}
		
	// This will determine if one of the player characters has passed beyond the trees and needs to make the monsters visible.
	after(String direction, MovementType movementType, Creature creature) : moveCreature(direction, movementType, creature) {
		if (GoblinGuardRoomEncounter.class.isAssignableFrom(Encounter.getEncounter().getClass())) {
			GoblinGuardRoomEncounter encounter = (GoblinGuardRoomEncounter) Encounter.getEncounter();
			if (isInPitTrap(creature.getCurrentPosition()) && !isInPitTrap(previousPosition)) {
				Utils.print("YOU FELL IN THE PIT TRAP!!!  HA HA HA!!!!!");
				encounter.setPitTrapActivated(true);
				creature.knockProne();
			}
		}
	}

	private boolean isInPitTrap(Position position) {
		if ((position.getX() >= 10) && (position.getX() <= 11) && (position.getY() >= 16) && (position.getY() <= 17)) {
			return true;
		}
		return false;
	}
}
