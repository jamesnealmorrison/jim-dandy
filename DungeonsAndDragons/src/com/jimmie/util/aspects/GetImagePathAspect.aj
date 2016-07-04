package com.jimmie.util.aspects;

import com.jimmie.domain.classes.Druid;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.DndCharacter;

public aspect GetImagePathAspect {
	public pointcut getImagePath(Creature creature) : execution(* com.jimmie.domain.creatures.*.getImagePath(..))
	&& target(creature);

	public pointcut getBloodiedImagePath(Creature creature) : execution(* com.jimmie.domain.creatures.*.getBloodiedImagePath(..))
	&& target(creature);

	String around(Creature creature) : getImagePath(creature) {
		// Check for Wild Shape
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
		
		// Check for Form of the Willow Sentinel
		if (DndCharacter.class.isAssignableFrom(creature.getClass())) {
			DndCharacter character = (DndCharacter) creature;
			if (character.getDndClass() != null) {
				if (Warden.class.isAssignableFrom(character.getDndClass().getClass())) {
					Warden warden = (Warden) character.getDndClass();
					if (warden.isUsingFormOfTheWillowSentinel()) {
						return warden.getWillowSentinelImagePath();
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
		
		// Check for Form of the Willow Sentinel
		if (DndCharacter.class.isAssignableFrom(creature.getClass())) {
			DndCharacter character = (DndCharacter) creature;
			if (character.getDndClass() != null) {
				if (Warden.class.isAssignableFrom(character.getDndClass().getClass())) {
					Warden warden = (Warden) character.getDndClass();
					if (warden.isUsingFormOfTheWillowSentinel()) {
						return warden.getWillowSentinelBloodiedImagePath();
					}
				}
			}
		}

		return proceed(creature);
	}
}
