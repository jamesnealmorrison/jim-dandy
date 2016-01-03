package com.jimmie.util.aspects;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.Runepriest;
import com.jimmie.domain.classes.RunicArtistry;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.util.Utils;

public aspect MissedAttackAspect {
	public pointcut missAttack(Creature misser, Creature target) : execution(* com.jimmie.domain.creatures.Creature.miss(..))
	&& args(misser) && target(target);

	after(Creature misser, Creature target) : missAttack(misser, target) {
		// See if target is a Runepriest with Defiant Word
		if (target.getDndClass() != null) {
			if (Runepriest.class.isAssignableFrom(target.getDndClass().getClass())) {
				Runepriest runepriest = (Runepriest) target.getDndClass();
				if (runepriest.getRunicArtistry() == RunicArtistry.DEFIANT_WORD) {
					Utils.print("Because " + misser.getName() + " missed " + target.getName() + " and " + target.getName() + " is a Runepriest with Defiant Word,");
					int abilityModifier = target.getAbilityModifier(AbilityType.WISDOM);
					Utils.print(target.getName() + " will get a " + abilityModifier + " bonus to attacks against " + misser.getName() + " until the end of the next turn.");
					target.setTargetedTemporaryEffect(abilityModifier, target.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, target, TemporaryEffectType.ATTACK_ROLL_MODIFIER, TemporaryEffectReason.DEFIANT_WORD, misser);
				}
			}
		}
	}

}