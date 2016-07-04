package com.jimmie.util.aspects;

import java.util.List;

import com.jimmie.domain.DiceRollType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.Deva;
import com.jimmie.domain.creatures.Elf;
import com.jimmie.encounters.Encounter;
import com.jimmie.powers.MemoryOfAThousandLifetimes;
import com.jimmie.powers.Power;
import com.jimmie.util.Dice;
import com.jimmie.util.SkillCheck;
import com.jimmie.util.Utils;

public aspect SkillCheckAspect {
	public pointcut skillCheck(Creature creature, SkillCheck skillCheck) : execution(int com.jimmie.domain.creatures.Creature.skillCheckRoll(SkillCheck))
	&& this(creature) && args(skillCheck);
	
	int around(Creature creature, SkillCheck skillCheck) : skillCheck(creature, skillCheck) {
		
		int result = proceed(creature, skillCheck);
		
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
								result += d.roll(DiceRollType.SKILL_CHECK_ROLL_MODIFICATION);
								power.setTimesUsed(power.getTimesUsed()+1);
							}
						}
					}
				}
			}
		}
		
		// Group Awareness - Elf
		if (skillCheck.getSkillType() == SkillType.PERCEPTION) {
			// See if this creature is non-elf
			if (!Elf.class.isAssignableFrom(creature.getClass())) {
				// See if they are within 5 squares of an  elf.
				List<Creature> allies = Encounter.getEncounter().getAlliesWithinRangeOf(creature, creature.getCurrentPosition(), 5);
				for (Creature ally : allies) {
					if (Elf.class.isAssignableFrom(ally.getRace().getClass())) {
						Utils.print(creature.getName() + " gets a +1 bonus to this perception check because they are within 5 squares of " + ally.getName() + ", an elf with Group Awareness.");
						result += 1;
					}
				}

			}
		}
		
		return result;
	}

}
