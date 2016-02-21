package com.jimmie.powers;

import java.util.List;

import com.jimmie.domain.AmmunitionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DefenseType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.creatures.AmmunitionUser;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public abstract class GenericAttackPower extends AttackPower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public boolean process(Creature user) {

		// Even though the meetsRequirementsToUsePower should have already been called, call it again now.
		if (meetsRequirementsToUsePower(user)) {

			List<AttackTarget> targets = null;
			// Build the target list.
			if (getAttackType() == AttackType.MELEE_NUMBER) {
				targets = Encounter.getEncounter().chooseMeleeTargetInRange(user, getRangeNumber1());
			} else if (getAttackType() == AttackType.RANGED_NUMBER) {
				targets = Encounter.getEncounter().chooseRangedTarget(user, getRangeNumber1(), getRangeNumber2(), getAttackType());
			} else { // TODO: Other attack types here.

			}

			if ((targets != null) && !(targets.isEmpty())) {
				// Attack each target.
				// Defining the damage outside of the loop because there should be just one damage roll.
				int damage = -1; // Defaulting to -1 as an indicator that it hasn't been set yet.
				for (AttackTarget target : targets) {
					Creature cTarget = null;
					if (Creature.class.isAssignableFrom(target.getClass())) {
						cTarget = (Creature) target;
					}
					// If uses ammo
					if (getAmmunitionType() != AmmunitionType.NONE) {
						if (AmmunitionUser.class.isAssignableFrom(user.getClass())) {
							AmmunitionUser ammoUser = (AmmunitionUser) user;
							if (ammoUser.roundsLeft(getAmmunitionType()) > 0) {
								ammoUser.expendRounds(1, getAmmunitionType());
							} else {
								Utils.print("Sorry. " + user.getName() + " is out of ammo.");
								return false;
							}
						} else {
							Utils.print("Sorry. This power takes ammunition, but " + user.getName() + " is not a creature with ammunition.");
							return false;
						}
					}
					
					int targetDefense = 0;
					String defense = null;

					switch (getTargetDefense()) {
					case ARMOR_CLASS :
						targetDefense = target.getArmorClass(user);
						defense = "an Armor Class";
						break;
					case WILL :
						targetDefense = target.getWill(user);
						defense = "a Will";
						break;
					case REFLEX :
						targetDefense = target.getReflex(user);
						defense = "a Reflex";
						break;
					case FORTITUDE :
						targetDefense = target.getFortitude(user);
						defense = "a Fortitude";
						break;
					}

					Utils.print(target.getName() + " has " + defense + " of " + targetDefense);

					int roll = user.attackRoll(getAttackModifier()+user.getOtherAttackModifier(target));
					Utils.print("Your total with modifiers is " + roll + ".");

					if (roll >= targetDefense) {
						/* A HIT! */
						Utils.print("You successfully hit " + target.getName());

						// If this is the first hit, roll for damage.
						if (damage == -1) {
							// Using 0 for the attributeBonus because I'm only doing monsters now.
							damage = Utils.rollForDamage(getDamageRolls(), getDamageDiceType(), getDamageBonus(), 0, user);
							// If somehow damage is negative, just set it to 0.
							if (damage < 0) {
								damage = 0;
							}
						}

						target.hurt(damage, getDamageType(), true, user, getAttackType());
						
						// Does this attack add any ongoing damage?
						if (hasOngoingDamage()) {
							if (cTarget != null) {
								cTarget.setTemporaryOngoingDamage(getOngoingDamage(), cTarget.getCurrentTurn(), getOngoingDamageDurationType(), user, TemporaryEffectType.ONGOING_DMG, TemporaryEffectReason.GENERIC_ATTACK, getOngoingDamageType());
							}
						}
					} else {
						Utils.print("You missed " + target.getName());

						// Cause half damage if necessary.
						if (missCausesHalfDamage()) {
							target.hurt(damage/2, getDamageType(), false, user, getAttackType());
						}

						// Some targets have powers/effects that happen when they are missed.
						target.miss(user, this);
					} // missed
				} // for each target
				return true;
			} // targets found
			return false;
		} else { // Doesn't meet requirements to use power.
			Utils.print("Not sure how you got here, but " + user.getName() + " actually can't use " + getName() + " now.");
			return false;
		}
	}

	public DamageType getOngoingDamageType() {
		return null;
	}

	public DurationType getOngoingDamageDurationType() {
		return null;
	}

	public int getOngoingDamage() {
		return 0;
	}

	public boolean hasOngoingDamage() {
		return false;
	}

	public abstract AmmunitionType getAmmunitionType();

	public abstract boolean missCausesHalfDamage();

	public abstract int getDamageBonus();

	public abstract DiceType getDamageDiceType();

	public abstract int getDamageRolls();

	public abstract DefenseType getTargetDefense();

	public abstract int getAttackModifier();
	
	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		// If daily or encounter, see if it's already been used.
		if ((PowerUsage.ENCOUNTER == getPowerUsage()) || (PowerUsage.DAILY == getPowerUsage())) {
			if (getTimesUsed() == 0) {
				// TODO: Check if there are any possible targets.
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	
}
