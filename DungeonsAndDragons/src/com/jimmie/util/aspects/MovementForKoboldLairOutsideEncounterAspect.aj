package com.jimmie.util.aspects;

import com.jimmie.domain.MovementType;
import com.jimmie.domain.Position;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.encounters.Encounter;
import com.jimmie.encounters.KoboldLairOutsideEncounter;

public aspect MovementForKoboldLairOutsideEncounterAspect {
	public pointcut moveCreature(String direction, MovementType movementType) : execution(* com.jimmie.domain.creatures.*.moveCreature(..))
	&& args(direction, movementType);

	// This will determine if one of the player characters has passed beyond the trees and needs to make the monsters visible.
	after(String direction, MovementType movementType) : moveCreature(direction, movementType) {
		if (KoboldLairOutsideEncounter.class.isAssignableFrom(Encounter.getEncounter().getClass())) {
			if (!Encounter.areMonstersVisible()) {
				Object o = thisJoinPoint.getThis();
				Creature creature = null;
				if (Creature.class.isAssignableFrom(o.getClass())) {
					creature = (Creature) o;
					Position p = creature.getCurrentPosition();
					switch (p.getY()) {
					case 1 :
						if (p.getX() >= 0) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 2 :
						if (p.getX() >= 0) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 3 :
						if (p.getX() >= 0) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 4 :
						if (p.getX() >= 2) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 5 :
						if (p.getX() >= 3) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 6 :
						if (p.getX() >= 4) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 7 :
						if (p.getX() >= 5) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 8 :
						if (p.getX() >= 5) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 9 :
						if (p.getX() >= 6) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 10 :
						if (p.getX() >= 5) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 11 :
						if (p.getX() >= 5) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 12 :
						if (p.getX() >= 5) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 13 :
						if (p.getX() >= 6) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 14 :
						if (p.getX() >= 7) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 15 :
						if (p.getX() >= 10) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 16 :
						if (p.getX() >= 11) {
							Encounter.setMonstersVisible(true);
						}
						break;
					case 17 :
						if (p.getX() >= 14) {
							Encounter.setMonstersVisible(true);
						}
						break;
					}
				}			
			}
		}
	}
}
