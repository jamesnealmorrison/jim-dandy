package com.jimmie.util.aspects;

import com.jimmie.domain.classes.Druid;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.DndCharacter;

public aspect GetImagePathAspect {
	public pointcut getImagePath(Creature creature) : execution(* com.jimmie.domain.creatures.*.getImagePath(..))
	&& target(creature);

	public pointcut getBloodiedImagePath(Creature creature) : execution(* com.jimmie.domain.creatures.*.getBloodiedImagePath(..))
	&& target(creature);

	String around(Creature creature) : getImagePath(creature) {
		if (DndCharacter.class.isAssignableFrom(creature.getClass())) {
			DndCharacter character = (DndCharacter) creature;
			if (character.getDndClass() != null) {
				if (Druid.class.isAssignableFrom(character.getDndClass().getClass())) {
					Druid druid = (Druid) character.getDndClass();
					if (druid.isInBeastForm()) {
						return druid.getBeastFormImagePath();
					}
				}
			}
		}
		
		return proceed(creature);
	}

	String around(Creature creature) : getBloodiedImagePath(creature) {
		if (DndCharacter.class.isAssignableFrom(creature.getClass())) {
			DndCharacter character = (DndCharacter) creature;
			if (character.getDndClass() != null) {
				if (Druid.class.isAssignableFrom(character.getDndClass().getClass())) {
					Druid druid = (Druid) character.getDndClass();
					if (druid.isInBeastForm()) {
						return druid.getBeastFormBloodiedImagePath();
					}
				}
			}
		}
		
		return proceed(creature);
	}
}
