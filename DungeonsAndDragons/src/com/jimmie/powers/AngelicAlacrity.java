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
import com.jimmie.domain.classes.AvengersCensure;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.items.weapons.ReadiedWeapon;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class AngelicAlacrity extends AttackPower {

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
		return "Angelic Alacrity";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.ENCOUNTER;
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
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public void process(Encounter encounter, Creature user) {
		if (timesUsed == 0) {
			timesUsed++;
			int shiftDistance;
			boolean aspectOfMightEncounterBonus = false;
			AvengersCensure censure = null;
			if (Avenger.class.isAssignableFrom(user.getDndClass().getClass())) {
				aspectOfMightEncounterBonus = ((Avenger) user.getDndClass()).isAspectOfMightEncounterBonus();
				censure = ((Avenger) user.getDndClass()).getCensure();
			}


			// In this attack, you can shift 2 squares first (or 1 + dex mod if Censure of Pursuit).
			if (AvengersCensure.CENSURE_OF_PURSUIT.equals(censure)) {
				shiftDistance = 1 + user.getAbilityModifierPlusHalfLevel(AbilityType.DEXTERITY);
			} else {
				shiftDistance = 2;
			}
			Utils.print("You may shift " + shiftDistance + " before the attack.");
			user.shift(shiftDistance, true, encounter);

			AttackTarget target = encounter.chooseMeleeTarget(user, user.getReadiedWeapon().getWeapon());

			List<AttackTarget> targets = new ArrayList<AttackTarget>();
			targets.add(target);
			Dice d = new Dice(DiceType.TWENTY_SIDED);
			int diceRoll = d.attackRoll(user, target, encounter, user.getCurrentPosition());
			int roll = diceRoll + user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM) + user.getWeaponProficiencyBonus() + user.getOtherAttackModifier(targets, encounter);

			Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);

			int targetArmorClass = target.getArmorClass(user);
			Utils.print("Your target has an AC of " + targetArmorClass);

			if (roll >= targetArmorClass) {
				// A HIT!
				Utils.print("You successfully hit " + target.getName());

				// See if this target was hit by Stirring Shout.
				if (target.isHitByStirringShout()) {
					Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
					user.heal(target.getStirringShoutCharismaModifier());
				}

				int damageRolls = user.getReadiedWeapon().getWeapon().getDamageRolls();
				DiceType damageDiceType = user.getReadiedWeapon().getWeapon().getDamageDice();

				damageRolls = damageRolls * 2;

				if (aspectOfMightEncounterBonus == false) {
					target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, user.getReadiedWeapon().getWeapon().getDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), user.getRace()), DamageType.NORMAL, encounter, true);
				} else {
					target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, user.getReadiedWeapon().getWeapon().getDamageBonus()+2, user.getAbilityModifierPlusHalfLevel(AbilityType.WISDOM), user.getRace()), DamageType.NORMAL, encounter, true);
					Utils.print("You got an aspect of might bonus of two to this damage roll.");
				}

			} else {
				Utils.print("You missed " + target.getName());
			}
		} else {
			Utils.print("Sorry, but " + user.getName() + " has already used Angelic Alacrity in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
			user.setUsedStandardAction(false);
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
		classes.add(Avenger.class);
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
