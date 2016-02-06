package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.classes.Avenger;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.items.weapons.ReadiedWeapon;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class AspectOfMight extends AttackPower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.MELEE_WEAPON;
	}

	@Override
	public String getName() {
		return "Aspect Of Might";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.DAILY;
	}

	@Override
	public PowerSource getPowerSource() {
		return PowerSource.DIVINE;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.WEAPON;
	}

	@Override
	public DamageType getDamageType() {
		return DamageType.NORMAL;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.STANDARD;
	}

	@Override
	public int getRangeNumber1() {
		return 0;
	}

	@Override
	public int getRangeNumber2() {
		return 0;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.ILLUSION);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		if (timesUsed == 0) {
			timesUsed++;
			List<AttackTarget> targets = Encounter.getEncounter().chooseMeleeTarget(user, user.getReadiedWeapon().getWeapon());

			if ((targets != null) && !(targets.isEmpty())) {
				AttackTarget target = targets.get(0);
				int targetArmorClass = target.getArmorClass(user);
				Utils.print("Your target has an AC of " + targetArmorClass);

				int attackRoll = user.attackRoll(AbilityType.WISDOM, AccessoryType.WEAPON, target);

				if (attackRoll >= targetArmorClass) {
					/* A HIT! */
					Utils.print("You successfully hit " + target.getName());

					int damageRolls = user.getReadiedWeapon().getWeapon().getDamageRolls();
					DiceType damageDiceType = user.getReadiedWeapon().getWeapon().getDamageDice();

					damageRolls = damageRolls * 3;

					target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, user.getReadiedWeapon().getWeapon().getDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), user), DamageType.NORMAL, true, user);

				} else {
					Utils.print("You missed " + target.getName() + ".  But you still do half damage.");

					int damageRolls = user.getReadiedWeapon().getWeapon().getDamageRolls();
					DiceType damageDiceType = user.getReadiedWeapon().getWeapon().getDamageDice();

					damageRolls = damageRolls * 3;

					target.hurt(Utils.rollForHalfDamage(damageRolls, damageDiceType, user.getReadiedWeapon().getWeapon().getDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), user), DamageType.NORMAL, false, user);

					// Some targets have powers/effects that happen when they are missed.
					target.miss(user, this);
				}
				Utils.print("Hit or miss, you get a bonus to the end of the encounter to speed, damage and athletics.");
				if (Avenger.class.isAssignableFrom(user.getDndClass().getClass())) {
					((Avenger) user.getDndClass()).setAspectOfMightEncounterBonus(true);
				}
			}
			return true;
		} else {
			Utils.print("Sorry, but " + user.getName() + " has already used Aspect of Might today.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			user.setUsedStandardAction(false);
		}
		return false;
	}

	@Override
	public boolean isBasicAttack() {
		return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getRacesThatCanUsePower() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getClassesThatCanUsePower() {
		List<Class> classes = new ArrayList<Class>();
		classes.add(Avenger.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;		
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		// Has it been used during this encounter already?
		if (timesUsed > 0) {
			return false;
		}
		for (ReadiedWeapon readiedWeapon : user.getReadiedWeapons().values()) {
			if (readiedWeapon.getWeapon().isMeleeWeapon()) {
				return true;
			}
		}
		return false;
	}
}
