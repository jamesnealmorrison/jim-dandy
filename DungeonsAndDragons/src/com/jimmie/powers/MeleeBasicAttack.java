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
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class MeleeBasicAttack extends AttackPower {

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
		return "Melee Basic Attack";
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
		return PowerSource.NONE;
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
			Dice d = new Dice(DiceType.TWENTY_SIDED);
			int diceRoll = d.roll();
			int roll = diceRoll + user.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + user.getWeaponProficiencyBonus() + user.getOtherAttackModifier(targets, encounter);

			Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);

			int targetArmorClass = target.getArmorClass(user);
			Utils.print("Your target has an AC of " + targetArmorClass);

			if (roll >= targetArmorClass) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				int damageRolls = user.getReadiedWeapon().getWeapon().getDamageRolls();
				DiceType damageDiceType = user.getReadiedWeapon().getWeapon().getDamageDice();

				/* Book says at level 21 increase damage to 2[W]. */
				if (getLevel() >= 21) {
					damageRolls = damageRolls * 2;
				}
				int avengerBonus = 0;
				if (Avenger.class.isInstance(user.getDndClass())) {
					if (((Avenger) user.getDndClass()).isAspectOfMightEncounterBonus() == true) {
						avengerBonus = 2;
						Utils.print("Because of your Aspect Of Might bonus, you get a two bonus to this damage.  I'll add it for you!");
					}
				}
				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, user.getReadiedWeapon().getWeapon().getDamageBonus() + avengerBonus, user.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), user.getRace()), DamageType.NORMAL, encounter, true, user);
			} else {
				Utils.print("You missed " + target.getName());
			}
		}
	}

	@Override
	public boolean isBasicAttack() {
		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getRacesThatCanUsePower() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getClassesThatCanUsePower() {
		return null; // This power is automatically inserted, so it doesn't need the classes that can use power method to be implemented.
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
