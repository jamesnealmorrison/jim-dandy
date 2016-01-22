package com.jimmie.util.aspects;

import java.util.Iterator;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.Sorcerer;
import com.jimmie.domain.classes.SorcererSpellSource;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.powers.Power;
import com.jimmie.util.Utils;

public aspect DamageRollAspect {
	public pointcut damageRoll(int damageRolls, DiceType damageDiceType, int weaponDamageBonus, int attributeModifier, Creature roller) : call(int com.jimmie.util.Utils.roll*Damage(int, DiceType, int, int, Creature))
	&& args(damageRolls, damageDiceType, weaponDamageBonus, attributeModifier, roller);	
	
	int around(int damageRolls, DiceType damageDiceType, int weaponDamageBonus, int attributeModifier, Creature roller) : damageRoll(damageRolls, damageDiceType, weaponDamageBonus, attributeModifier, roller) {		
		int result = proceed(damageRolls, damageDiceType, weaponDamageBonus, attributeModifier, roller);
		
		// See if this call is coming from an arcane power.
		if (Power.class.isAssignableFrom(thisJoinPoint.getThis().getClass())) {
			if (((Power) thisJoinPoint.getThis()).getPowerSource() == PowerSource.ARCANE) {
				if (roller.getDndClass() != null) {
					if (Sorcerer.class.isAssignableFrom(roller.getDndClass().getClass())) {
						Sorcerer sorcerer = (Sorcerer) roller.getDndClass();
						if (sorcerer.getSpellSource() == SorcererSpellSource.WILD_MAGIC) {
							int abilityModifier = roller.getAbilityModifier(AbilityType.DEXTERITY);
							Utils.print("As a Sorcerer with Wild Magic, " + roller.getName() + " gets a " + abilityModifier + " modifier to damage rolls.");
							result += abilityModifier;
						}
					}
				}
			}
		}
		
		// Is there a temporary damage modifier?
		if (roller.getTemporaryEffects() != null) {
			for (Iterator<TemporaryEffect> it = roller.getTemporaryEffects().iterator(); it.hasNext();) {
				TemporaryEffect tempEffect = it.next();
				if (tempEffect.getEffectType() == TemporaryEffectType.DAMAGE_MODIFIER) {
					if (tempEffect.stillApplies()) {
						Utils.print("There is a temporary damage modifier of " + tempEffect.getModifier());
						result += tempEffect.getModifier();
					} else {
						Utils.print("Temporary damage modifier no longer applies.  Removing it.");
						it.remove();
					}
				}
			}
		}

		return result;
	}

}
