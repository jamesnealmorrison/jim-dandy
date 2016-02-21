package com.jimmie.util.aspects;

import java.util.Iterator;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceRollType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.RunicState;
import com.jimmie.domain.TemporaryEffect;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.TemporaryOngoingDamage;
import com.jimmie.domain.classes.Runepriest;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.monsters.Gnome;
import com.jimmie.domain.creatures.monsters.GnomeSkulk;
import com.jimmie.domain.creatures.monsters.GoblinSharpshooter;
import com.jimmie.domain.creatures.monsters.HalflingSlinger;
import com.jimmie.domain.creatures.monsters.Zombie;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public aspect HurtAspect {
	public pointcut hurt(Creature hurtee, int damage, DamageType damageType, boolean hit, Object hurter, AttackType attackType) : execution(* com.jimmie.domain.creatures.Creature.hurt(..))
	&& args(damage, damageType, hit, hurter, attackType) && target(hurtee);

	void around(Creature hurtee, int damage, DamageType damageType, boolean hit, Object hurter, AttackType attackType) : hurt(hurtee, damage, damageType, hit, hurter, attackType) {
		Utils.print("Hurter = " + hurter);
		Utils.print("Hurtee = " + hurtee);
		
		// TODO: Gravehound, and other zombies
		if (Zombie.class.isInstance(hurtee)) {
			if (Encounter.getEncounter().isCriticalHit()) {
				Utils.print("Because of Zombie Weakness, this critical hit automatically reduces the zombie to 0 HP.");
				// Don't even call the hurt method, because they might have resistance that effects the HP result.  Just set current HP to 0 directly.
				hurtee.setCurrentHitPoints(0);
				return;
			}
		}

		if ((hurter != null) && (Creature.class.isAssignableFrom(hurter.getClass()))) {
			Creature cHurter = (Creature) hurter; 

			// Is there a temporary damage modifier to the hurtee?
			if (hurtee.getTemporaryEffects() != null) {
				for (Iterator<TemporaryEffect> it = hurtee.getTemporaryEffects().iterator(); it.hasNext();) {
					TemporaryEffect tempEffect = it.next();
					if (tempEffect.getEffectType() == TemporaryEffectType.RCV_DMG_MOD) {
						if (tempEffect.stillApplies()) {
							Utils.print("There is a temporary RECEIVING damage modifier of " + tempEffect.getModifier());
							damage += tempEffect.getModifier();
							Utils.print("Doing " + damage + " points worth of damage.");
							// Check for Word of Exchange.
							if (tempEffect.getReason() == TemporaryEffectReason.WORD_OF_EXCHANGE) {
								Utils.print(cHurter.getName() + " gets " + tempEffect.getModifier() + " temporary hit points because the creature was hit by Word of Exchange.");
								cHurter.setTemporaryHitPoints(tempEffect.getModifier());
							}
						} else {
							Utils.print("Temporary damage modifier no longer applies.  Removing it.");
							it.remove();
						}
					}
				}
			}
		}
		
		// Runepriest Rune Master
		// See if I'm adjacent to a runepriest in the Rune of Protection state
		List<Creature> allies = Encounter.getEncounter().getAdjacentAllies(hurtee);
		if (allies != null) {
			for (Creature ally : allies) {
				if (ally.getDndClass() != null) {
					if (Runepriest.class.isAssignableFrom(ally.getDndClass().getClass())) {
						Runepriest runepriest = (Runepriest) ally.getDndClass();
						if (runepriest.getRunicState() == RunicState.RUN_OF_PROTECTION) {
							Utils.print(hurtee.getName() + " gets a resist 2 to all damage because they are adjacent to a runepriest in the Rune of Protection state.");
							damage -= 2;
							if (damage < 0) {
								damage = 0;
							}
						}
					}
				}
			}
		}
		
		// Combat Advantage feature
		if ((hurter != null) && ((GnomeSkulk.class.isAssignableFrom(hurter.getClass()))
				|| (HalflingSlinger.class.isAssignableFrom(hurter.getClass()))
				|| (GoblinSharpshooter.class.isAssignableFrom(hurter.getClass())))) {
			if (Utils.hasCombatAdvantage((Creature) hurter, hurtee)) {
				Utils.print("Because of " + ((Creature) hurter).getName() + "'s Combat Advantage feature, they deal an extra 1d6 damage.");
				// TODO: Some creatures (halfling slinger) this only applies to ranged, others to melee, other to both.
				Dice d = new Dice(DiceType.SIX_SIDED);
				int extraDamage = d.roll(DiceRollType.DAMAGE_ROLL_MODIFICATION);
				damage += extraDamage;
			}
		}

		proceed(hurtee, damage, damageType, hit, hurter, attackType);
		
		// See if hurting the creature killed it for the Druid Fires of Life power
		if (hurtee.getCurrentHitPoints() <= 0) {
			// See if they still had the Fires of Life ongoing damage.
			for (TemporaryEffect tempEffect : hurtee.getTemporaryEffects()) {
				if (TemporaryOngoingDamage.class.isAssignableFrom(tempEffect.getClass())) {
					TemporaryOngoingDamage tempDamage = (TemporaryOngoingDamage) tempEffect;
					if (tempDamage.getReason() == TemporaryEffectReason.FIRES_OF_LIFE) {
						if (tempDamage.stillApplies()) {
							int hpGained = 5 + tempDamage.getSource().getAbilityModifier(AbilityType.CONSTITUTION);
							Utils.print(hurtee.getName() + " died without being able to save against the Fires Of Life ongoing damage.  An ally of " + tempDamage.getSource().getName() + " can gain " + hpGained + " hit points.");
							Creature ally = Encounter.getEncounter().chooseAllyWithinRangeOf(tempDamage.getSource(), hurtee.getCurrentPosition(), 5);
							if (ally != null) {
								ally.heal(hpGained);
							}
						}
					}
				}
			}
		}
		
		// Gnome Fade Away
		if (com.jimmie.domain.creatures.monsters.Gnome.class.isAssignableFrom(hurtee.getClass())) {
			Gnome gnome = (Gnome) hurtee;
			if (!gnome.isUsedFadeAway()) {
				Utils.print(gnome.getName() + " is a Gnome with Fade Away.  Would they like to turn invisible now (Y or N)?");
				if ("Y".equalsIgnoreCase(Utils.getYesOrNoInput())) {
					gnome.setTemporaryInvisibility(gnome, DurationType.SPECIAL, null, TemporaryEffectReason.FADE_AWAY);
				}				
			}
		}
		
	}

}
