package com.jimmie.powers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.Mark;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.Paladin;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.items.weapons.ReadiedWeapon;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class EnfeeblingStrike extends AttackPower {
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
		return "Enfeebling Strike";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.AT_WILL;
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
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.NORMAL);
		return damageTypes;
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
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public void process(Encounter encounter, Creature user) {
		List<AttackTarget> targets = encounter.chooseMeleeTarget(user, user.getReadiedWeapon().getWeapon());

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);

			int targetArmorClass = target.getArmorClass(user);
			Utils.print("Your target has an AC of " + targetArmorClass);

			int attackRoll = user.attackRoll(AbilityType.CHARISMA, getAccessoryType(), targets, encounter);

			if (attackRoll >= targetArmorClass) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				int damageRolls = user.getReadiedWeapon().getWeapon().getDamageRolls();
				DiceType damageDiceType = user.getReadiedWeapon().getWeapon().getDamageDice();

				/* Book says at level 21 increase damage to 2[W]. */
				if (getLevel() >= 21) {
					damageRolls = damageRolls * 2;
				}
				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, user.getReadiedWeapon().getWeapon().getDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA), user.getRace()), DamageType.NORMAL, encounter, true, user);

				if (Creature.class.isAssignableFrom(target.getClass())) {
					Creature c = (Creature) target;
					if (c.isMarked()) {
						for (Iterator<Mark> it = c.getMarks().iterator(); it.hasNext();) {
							Mark mark = it.next();
							if (mark.getMarker() == user) {
								Utils.print(c.getName() + " will have a -2 attack roll penalty until the end of my next turn.");
								c.setTemporaryEffect(-2, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.ATTACK_ROLL_MODIFIER);
							}
						}
					}
				}
				
			} else {
				Utils.print("You missed " + target.getName());
			}
		}
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
		classes.add(Paladin.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		for (ReadiedWeapon readiedWeapon : user.getReadiedWeapons().values()) {
			if (readiedWeapon.getWeapon().isMeleeWeapon()) {
				return true;
			}
		}
		return false;
	}

}
