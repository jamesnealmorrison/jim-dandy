package com.jimmie.util.aspects;

import java.util.ArrayList;
import java.util.List;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceRollType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.MovementType;
import com.jimmie.domain.TurnTaker;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.encounters.CryptOfShadowsEncounter;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public aspect MovementForCryptOfShadowsAspect {
	private boolean cameFromTerrorRuneSquare;

	public pointcut moveCreature(String direction, MovementType movementType, Creature creature) : execution(* com.jimmie.domain.creatures.*.moveCreature(..))
	&& args(direction, movementType) && target(creature);

	
	// This will determine if a pc has entered the southern chamber or set off a terror rune.
	before(String direction, MovementType movementType, Creature creature) : moveCreature(direction, movementType, creature) {
		if (CryptOfShadowsEncounter.class.isAssignableFrom(Encounter.getEncounter().getClass())) {
			int terrorRune = isCreatureInTerrorRune(creature);
			if (terrorRune > 0) {
				// Just keep track of whether they were on a terror rune square before or not.
				cameFromTerrorRuneSquare = true;
			} else {
				cameFromTerrorRuneSquare = false;
			}
		}
		
	}
	
	// This will determine if a pc has entered the southern chamber or set off a terror rune.
	after(String direction, MovementType movementType, Creature creature) : moveCreature(direction, movementType, creature) {
		if (CryptOfShadowsEncounter.class.isAssignableFrom(Encounter.getEncounter().getClass())) {
			CryptOfShadowsEncounter encounter = (CryptOfShadowsEncounter) Encounter.getEncounter();
			if (!encounter.isPcHasSteppedIntoSouthernChamber()) {
				if (((creature.getCurrentPosition().getX()>=17) && (creature.getCurrentPosition().getX()<=18)) && (creature.getCurrentPosition().getY()==10)) {
					encounter.setPcHasSteppedIntoSouthernChamber(true);
				}
			}
			// Only activate a terror rune if they weren't on it already.
			if (cameFromTerrorRuneSquare == false) {
				int terrorRune = isCreatureInTerrorRune(creature);
				if (terrorRune > 0) {
					encounter.setRuneHasBeenActivated(true);
					Utils.print("A throat-tearing scream explodes from the flor. Low, hungry moans answer the dying scream from the north and south.");

					// Get all pcs in a 10 square radius.  For now, just checking within 10 of creature's position, not the entire rune.
					List<Creature> pcs = encounter.getCharactersWithinRangeOf(creature.getCurrentPosition(), 10);
					
					if (pcs != null) {
						Utils.print("Rolling for damage first.");
						Dice damageDice = new Dice(DiceType.FOUR_SIDED);
						int damage = damageDice.roll(DiceRollType.DAMAGE_ROLL) + 1;
						for (Creature pc : pcs) {
							if (!hasBeenEffectedByRune(pc,terrorRune)) {
								int targetWill = pc.getWill(null);
								Utils.print(pc.getName() + " has a Will of " + targetWill);

								Dice d = new Dice(DiceType.TWENTY_SIDED);
								int attackRoll = d.roll(DiceRollType.ATTACK_ROLL) + 7;
								Utils.print("Total roll = " + attackRoll);

								if (attackRoll >= targetWill) {
									markAsEffectedByRune(pc,terrorRune);
									Utils.print(pc.getName() + " has been terrorized by the throat tearing scream exploding from the floor.");								
									Utils.print("Doing " + damage + " necrotic damage to them.");
									pc.hurt(damage, DamageType.NECROTIC, true, null, AttackType.AREA_BURST);
									
									Utils.print(pc.getName() + " must run toward southeast chamber.");
									int squaresLeft = pc.getSpeed() + 2;
									
									// While they are running, they need to be set in the encounter as the current participant temporarily
									// so everything will be drawn from their perspective.
									TurnTaker currentParticipant = encounter.getCurrentParticipant();
									encounter.setCurrentParticipant(pc);
									
									// Temporarily light up the dungeon so I can see where they run to.  Because the person running isn't the current turntaker.
									while (squaresLeft > 0) {
										
										String runDirection = null;
										List<String> validChoices = new ArrayList<String>();
										validChoices.add("N");
										validChoices.add("S");
										validChoices.add("E");
										validChoices.add("W");
										validChoices.add("NE");
										validChoices.add("NW");
										validChoices.add("SE");
										validChoices.add("SW");
										Utils.print("Which way do you want to run (keep in mind you must go toward the south east chamber)?");
										Utils.print("N, S, E, W, NE, NW, SE, SW?");
										runDirection = Utils.getValidInput(validChoices);
										pc.moveCreature(runDirection, MovementType.RUNNING);
										squaresLeft--;
									}
									Utils.print("ANY MOVEMENT AFTER THIS IS NOT FROM THE TERROR OF THE RUNE!!!!!!!!");
									// Now set the currentParticipant back.
									encounter.setCurrentParticipant(currentParticipant);
								}
							}
						}
					}
				}
			}
		}
		
	}

	private void markAsEffectedByRune(Creature pc, int terrorRune) {
		if (CryptOfShadowsEncounter.class.isAssignableFrom(Encounter.getEncounter().getClass())) {
			CryptOfShadowsEncounter encounter = (CryptOfShadowsEncounter) Encounter.getEncounter();
			List<Creature> creatures = encounter.getTerrorRuneEffects().get(terrorRune);
			creatures.add(pc);
		}
		
	}


	private boolean hasBeenEffectedByRune(Creature pc, int terrorRune) {
		if (CryptOfShadowsEncounter.class.isAssignableFrom(Encounter.getEncounter().getClass())) {
			CryptOfShadowsEncounter encounter = (CryptOfShadowsEncounter) Encounter.getEncounter();
			List<Creature> creatures = encounter.getTerrorRuneEffects().get(terrorRune);
			if (!creatures.isEmpty()) {
				for (Creature creature : creatures) {
					if (pc == creature) {
						Utils.print(pc.getName() + " has already been effected by this rune today and is able to withstand the terror.");
						return true;
					}
				}
			}
		}
		return false;
	}


	private int isCreatureInTerrorRune(Creature creature) {
		// Runes only effect pc's.
		if (PlayerCharacter.class.isAssignableFrom(creature.getClass())) {
			// Check for each rune.
			if ((creature.getCurrentPosition().getX() >= 3) && (creature.getCurrentPosition().getX() <= 4) && 
					(creature.getCurrentPosition().getY() >= 24) && (creature.getCurrentPosition().getY() <= 25)) {
				return 1;
			}
			if ((creature.getCurrentPosition().getX() >= 11) && (creature.getCurrentPosition().getX() <= 12) && 
					(creature.getCurrentPosition().getY() >= 22) && (creature.getCurrentPosition().getY() <= 23)) {
				return 2;
			}
			if ((creature.getCurrentPosition().getX() >= 7) && (creature.getCurrentPosition().getX() <= 8) && 
					(creature.getCurrentPosition().getY() >= 20) && (creature.getCurrentPosition().getY() <= 21)) {
				return 3;
			}
			if ((creature.getCurrentPosition().getX() >= 19) && (creature.getCurrentPosition().getX() <= 20) && 
					(creature.getCurrentPosition().getY() >= 20) && (creature.getCurrentPosition().getY() <= 21)) {
				return 4;
			}
			if ((creature.getCurrentPosition().getX() >= 11) && (creature.getCurrentPosition().getX() <= 12) && 
					(creature.getCurrentPosition().getY() >= 14) && (creature.getCurrentPosition().getY() <= 15)) {
				return 5;
			}
		}
		return 0;
	}

}
