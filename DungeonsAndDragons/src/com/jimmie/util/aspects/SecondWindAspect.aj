package com.jimmie.util.aspects;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.GuardianMight;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.feats.FeatType;
import com.jimmie.util.Utils;

public aspect SecondWindAspect {
	public pointcut secondWind(Creature creature) : execution(* com.jimmie.powers.SecondWind.process(..))
	&& args(creature);
	
	after(Creature creature) : secondWind(creature) {
		// Warden Earthstrength:
		if (creature.getDndClass() != null) {
			if (Warden.class.isAssignableFrom(creature.getDndClass().getClass())) {
				if (((Warden) creature.getDndClass()).getGuardianMight() == GuardianMight.EARTHSTRENGTH) {
					int constitutionModifier = creature.getAbilityModifier(AbilityType.CONSTITUTION);
					Utils.print("Because you have earthstrength, giving a temporary bonus of " + constitutionModifier + " to your AC.");
					creature.setTemporaryEffect(constitutionModifier, creature.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, creature, TemporaryEffectType.ARMOR_CLASS_MODIFIER, TemporaryEffectReason.SECOND_WIND);

					if (PlayerCharacter.class.isAssignableFrom(creature.getClass())) {
						if (((PlayerCharacter) creature).hasFeat(FeatType.CRUSHING_EARTHSTRENGTH)) {
							Utils.print("Because " + creature.getName() + " has the Crushing Earthstrength feat, adding " + constitutionModifier + " damage bonus until the end of next turn.");
							creature.setTemporaryEffect(constitutionModifier, creature.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, creature, TemporaryEffectType.DAMAGE_MODIFIER, TemporaryEffectReason.RUNE_OF_MENDING);								
						}
					}
				}
			}
		}
	}
}
