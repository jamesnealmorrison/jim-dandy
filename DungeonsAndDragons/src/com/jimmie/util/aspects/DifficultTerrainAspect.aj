package com.jimmie.util.aspects;

import com.jimmie.domain.MovementType;
import com.jimmie.domain.Position;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.Elf;
import com.jimmie.util.Utils;

public aspect DifficultTerrainAspect {
	public pointcut isDifficultTerrain(Creature creature, Position position, MovementType movementType) : execution(boolean com.jimmie.domain.creatures.Creature.isDifficultTerrain(Position, MovementType))
	&& this(creature) && args(position, movementType);
	
	boolean around(Creature creature, Position position, MovementType movementType) : isDifficultTerrain(creature, position, movementType) {
		boolean difficultTerrain = proceed(creature, position, movementType);
	
		// Elf Wild Step
		// Are you shifting into difficult terrain?
		if (difficultTerrain) {
			if (MovementType.SHIFTING == movementType) {
				if (creature.getRace() != null) {
					if (Elf.class.isAssignableFrom(creature.getRace().getClass())) {
						Utils.print(creature.getName() + " is an Elf with Wild Step.  They ignore difficult terrain while shifting.");
						difficultTerrain = false;;
					}
				}
			}
		}
		return difficultTerrain;
	}
}
