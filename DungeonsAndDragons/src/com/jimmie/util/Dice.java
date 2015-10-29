package com.jimmie.util;

import java.util.List;
import java.util.Random;

import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.Position;
import com.jimmie.domain.TemporaryAidAnotherBonus;
import com.jimmie.domain.classes.Avenger;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.classes.Warden;
import com.jimmie.encounters.Encounter;
import com.jimmie.domain.creatures.Character;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.Elf;
import com.jimmie.domain.creatures.Race;

public class Dice {
	private DiceType type;
	private int sides;
	public static final int USER_ENTERED = 1;
	public static final int AUTOMATED = 2;
	private Random random;
	private static int rollType = AUTOMATED;
	
	public static int getRollType() {
		return rollType;
	}

	public static void setRollType(int r) {
		rollType = r;
	}

	public Dice(DiceType type) {
		this.type = type;
		
		random = new Random(System.currentTimeMillis());
		
		switch (this.type) {
			case FOUR_SIDED :
				sides = 4;
				break;
			case SIX_SIDED :
				sides = 6;
				break;
			case EIGHT_SIDED :
				sides = 8;
				break;
			case TEN_SIDED :
				sides = 10;
				break;
			case TWELVE_SIDED :
				sides = 12;
				break;
			case TWENTY_SIDED :
				sides = 20;
				break;
		}
	}
	
	public int basicRoll() {
		if (Dice.rollType != USER_ENTERED) {
			/* The computer is so fast, it doesn't roll new numbers sometimes.  So take the 6th different number. */
			int index = 0;
			int currentRoll = 0;
			int lastRoll = 0;
		
			for (int i = 0; i < 10000; i++) {
				for (int j = 0; j < 10000; j++);
			}
			while (index < 6) {
				currentRoll = random.nextInt(sides)+1;
				if (currentRoll != lastRoll) {
					index++;
					lastRoll = currentRoll;
					random = new Random(System.currentTimeMillis());
				}
			}
			
			Utils.print("You rolled a " + currentRoll);
			return currentRoll;
		} else {
			Utils.print("You roll (1 to " + sides + "): " );
			return Utils.getValidIntInputInRange(1, sides);
		}
	}
	
	/* attackOriginSquare should never be null.  Just pass the attacker's current position if that's where the attack
	 * originates from. */
	public int attackRoll(Object attacker, AttackTarget target, Encounter encounter, Position attackOriginSquare) {
		int diceRoll1 = basicRoll();
		int diceRoll2 = 0;
		int modifier = 0;
		
		/* Check to see if this roll is because of an avenger attacking his oath of enmity target. */
		if (Character.class.isAssignableFrom(attacker.getClass())) {
			DndClass dndClass = ((Character) attacker).getDndClass();
			if (Avenger.class.isInstance(dndClass)) {
				if (((Avenger) dndClass).getOathOfEnmityTarget() != null) {
					/* Did you choose your oath of enmity target? */
					if (((Avenger) dndClass).getOathOfEnmityTarget().equals(target)) {
						/* If the oath of enmity target is the only one adjacent. */
						if (!encounter.areAnyOtherEnemiesAdjacentBesidesTarget((Creature)attacker, target)) {
							diceRoll2 = basicRoll();
							Utils.print("Because of your Oath Of Enmity power, you can roll twice in this instance.");
							Utils.print("You rolled a " + diceRoll1 + " and a " + diceRoll2 + ".  Using the bigger number.");
							if (diceRoll2 > diceRoll1) {
								diceRoll1 = diceRoll2;
							}
						}
					}
				}
			}
		}

		/* Check to see if this roll is because of anyone attacking an avenger's oath of enmity target. */
		/* If so, the avenger may be able to use their "Divine Guidance" power. */
		if (Character.class.isAssignableFrom(attacker.getClass())) {
			for (Character character : encounter.getCharacters()) {
				if (!character.equals(attacker)) {
					/* Need to be within 10 squares of avenger. */
					if (character.getCurrentPosition().isWithinReachOf(((Character)attacker).getCurrentPosition(), 10)) {
					DndClass dndClass = character.getDndClass();
					if (Avenger.class.isInstance(dndClass)) {
						if (((Avenger) dndClass).getOathOfEnmityTarget() != null) {
							/* Did you choose the oath of enmity target? */
							if (((Avenger) dndClass).getOathOfEnmityTarget().equals(target)) {
								/* If the avenger can use the Divine Guidance power. */
								if (!((Avenger) dndClass).isUsedChannelDivinity()) {
									/* Ask if they want to. */
									Utils.print(character.getName() + "'s oath of enmity target is being attacked, and they can use Divine Guidance to let you reroll.");
									Utils.print("Does the avenger want to use this power to let you reroll?");
									String choice = Utils.getYesOrNoInput();
									if ("Y".equals(choice)) {
										diceRoll2 = basicRoll();
										Utils.print("You rolled a " + diceRoll1 + " and a " + diceRoll2 + ".  Using the bigger number.");
										if (diceRoll2 > diceRoll1) {
											diceRoll1 = diceRoll2;
										}
										((Avenger)dndClass).setUsedChannelDivinity(true);
									}
								}
							}
						}
					}
				}
			}
		}
		}
		
		/* Check to see if the attacker is standing next to a Warden using the Form of the Willow Sentinel power. */
		/* If so, the Warden may be able to do an immediate interrupt. */
		if (Creature.class.isAssignableFrom(attacker.getClass())) {
			List<Creature> adjacentEnemies = encounter.getAdjacentEnemies((Creature)attacker);
			if (adjacentEnemies != null) {
			for (Creature adjacentEnemy : adjacentEnemies) {
				if (adjacentEnemy != target) {
				if (Character.class.isAssignableFrom(adjacentEnemy.getClass())) {
					DndClass dndClass = ((Character) adjacentEnemy).getDndClass();
					if (Warden.class.isInstance(dndClass)) {
						if (((Warden) dndClass).isUsingFormOfTheWillowSentinel()) {
							if (!((Warden) dndClass).isUsedFormOfTheWillowSentinelAttack()) {
								Utils.print(((Creature) attacker).getName() + " is currently adjacent to a Warden that is using Form of the Willow Sentinel who has not yet interrupted an attack.");
								Utils.print("Does " + adjacentEnemy.getName() + " want to use this attack now?");
								String choice = Utils.getYesOrNoInput();
								if ("Y".equalsIgnoreCase(choice)) {
									modifier = modifier + ((Warden) dndClass).formOfTheWillowSentinelAttack(encounter, (Creature) attacker);
								}
							}
						}
					}
				}
			}
			}
			}
		}		
		
		/* See if the attacker is an elf and can reroll the attack roll. */
		if (Creature.class.isAssignableFrom(attacker.getClass())) {
			Race race = ((Creature) attacker).getRace();
			if (Elf.class.isInstance(race)) {
				if (!((Elf) race).isUsedElvenAccuracy()) {
				Utils.print("This Elf has not yet used Elven Accuracy in this encounter.  They can reroll if they want.");
				Utils.print("Do you want to use this power?");
				String choice = Utils.getYesOrNoInput();
				if ("Y".equalsIgnoreCase(choice)) {
					((Elf) race).setUsedElvenAccuracy(true);
					/* Just call this method recursively. */
					diceRoll1 = attackRoll(attacker, target, encounter, attackOriginSquare);
				}
			}
		}
		}
		
		/* See if there is a temporary attack bonus due to the "Aid another" bonus. */
		if (Character.class.isAssignableFrom(attacker.getClass())) {
			TemporaryAidAnotherBonus temporaryAidAnotherBonus= ((Character) attacker).getTemporaryAidAnotherBonus();
		if ((temporaryAidAnotherBonus != null) && (temporaryAidAnotherBonus.getType() == TemporaryAidAnotherBonus.ATTACK)) {
			if (temporaryAidAnotherBonus.stillApplies() && (temporaryAidAnotherBonus.getTarget() == target)) {
				Utils.print("You are supposed to get a bonus of " + temporaryAidAnotherBonus.getBonus() + " to attack against " + target.getName() + ".");
					modifier = modifier + temporaryAidAnotherBonus.getBonus();
					Utils.print("Bonus still applies.");
					temporaryAidAnotherBonus = null;
					Utils.print("One time bonus so bonus no longer applies.  Resetting bonus.");
			} else {
				/* Bonus is over.  Reset the bonus. */
				temporaryAidAnotherBonus = null;
				Utils.print("Bonus no longer applies.  Resetting bonus.");
			}
		}
		}
		
		if (Creature.class.isAssignableFrom(attacker.getClass())) {
			if (encounter.isSacredCircle(((Creature) attacker).getCurrentPosition())) {
				modifier = modifier + 1;
			}
		}
		
		/* Does the target have cover? */
		if (attackOriginSquare != null) {
			modifier = modifier + encounter.getCoverPenalty(attackOriginSquare, target.getCurrentPosition());
		}
				
		return diceRoll1 + modifier;
	}
}
