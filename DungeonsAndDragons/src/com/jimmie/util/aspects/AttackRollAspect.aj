package com.jimmie.util.aspects;

import com.jimmie.domain.DiceRollType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.Sorcerer;
import com.jimmie.domain.classes.SorcererSpellSource;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.Deva;
import com.jimmie.domain.creatures.Elf;
import com.jimmie.powers.ElvenAccuracy;
import com.jimmie.powers.MemoryOfAThousandLifetimes;
import com.jimmie.powers.Power;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public aspect AttackRollAspect {
	public pointcut attackRoll(Creature creature) : execution(int com.jimmie.domain.creatures.Creature.attackRoll(..))
	&& this(creature);

	public pointcut rawAttackRoll(Creature creature) : execution(int com.jimmie.domain.creatures.Creature.rawAttackRoll(..))
	&& this(creature);
	
	int around(Creature creature) : attackRoll(creature) {
		
		int result = proceed(creature);

		// Deva Memory of a Thousand Lifetimes
		if (creature.getRace() != null) {
			if (Deva.class.isAssignableFrom(creature.getRace().getClass())) {
				// Look for the "Memory of a Thousand Lifetimes" power
				for (Power power : creature.getPowers()) {
					if (MemoryOfAThousandLifetimes.class.isAssignableFrom(power.getClass())) {
						if (power.getTimesUsed() == 0) {
							Utils.print("You are a Deva with the Memory of a Thousand Lifetimes power.  Would you like to add 1d6 to this roll?");
							Utils.print("Your choice (Y or N):");
							if ("Y".equalsIgnoreCase(Utils.getYesOrNoInput())) {
								Dice d = new Dice(DiceType.SIX_SIDED);
								result += d.roll(DiceRollType.ATTACK_ROLL_MODIFICATION);
								power.setTimesUsed(power.getTimesUsed()+1);
							}
						}
					}
				}
			}
		
			// Elven Accuracy
			if (Elf.class.isAssignableFrom(creature.getRace().getClass())) {
				// Look for the "Elven Accuracy" power
				for (Power power : creature.getPowers()) {
					if (ElvenAccuracy.class.isAssignableFrom(power.getClass())) {
						if (power.getTimesUsed() == 0) {
							Utils.print("You are an Elf with the Elven Accuracy power.  Would you like to reroll?");
							Utils.print("Your choice (Y or N):");
							if ("Y".equalsIgnoreCase(Utils.getYesOrNoInput())) {
								// Just reroll
								result = proceed(creature);
								power.setTimesUsed(power.getTimesUsed()+1);
							}
						}
					}
				}
			}
		}

		return result;
	}


	int around(Creature creature) : rawAttackRoll(creature) {
		
		int result = proceed(creature);
		
		if (creature.getDndClass() != null) {
			if (Sorcerer.class.isAssignableFrom(creature.getDndClass().getClass())) {
				Sorcerer sorcerer = (Sorcerer) creature.getDndClass();
				if (sorcerer.getSpellSource() == SorcererSpellSource.WILD_MAGIC) {
					// Chaos Burst
					if (sorcerer.getFirstAttackRoll() == 0) {
						if ((result & 1) == 0) {
							Utils.print("Your first attack roll of the round is even.  As a sorcerer with wild magic, you get a +1 AC bonus until the start of your next turn.");
							creature.setTemporaryEffect(1, creature.getCurrentTurn(), DurationType.START_OF_NEXT_TURN, creature, TemporaryEffectType.ARMOR_CLASS_MODIFIER, TemporaryEffectReason.CHAOS_BURST);
						} else {
							Utils.print("Your first attack roll of the round is odd.  As a sorcerer with wild magic, you get to make a saving throw now.");
							Utils.print("This will be useless if you don't have a condition where save ends.");
							creature.performSavingThrows(1, 0);
						}
						sorcerer.setFirstAttackRoll(result);
					}
					
					// Unfettered Power
					if (result == 20) {
						Utils.print("You rolled a natural 20.  Your Unfettered Power will slide your target and knowck it prone.");
						sorcerer.setUnfetteredPower(20);
					} else if (result == 1) {
						Utils.print("You rolled a natural 1. Your Unfettered Power will push each creature within 5 squares of you 1 square." );
						sorcerer.setUnfetteredPower(1);
					} else {
						sorcerer.setUnfetteredPower(0);
					}

					// Store whether it's even or not because several powers use that info.
					if ((result & 1) == 0) {
						sorcerer.setLastAttackRollEven(true);
					} else {
						sorcerer.setLastAttackRollEven(false);
					}
					
				}				
			}
		}
		
		return result;
	}
}
