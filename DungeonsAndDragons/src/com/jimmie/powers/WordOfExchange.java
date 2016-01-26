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
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.RunicState;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.Runepriest;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.items.weapons.ReadiedWeapon;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class WordOfExchange extends AttackPower {
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
		return "Word Of Exchange";
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
		effectTypes.add(EffectType.RUNIC);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		List<AttackTarget> targets = Encounter.getEncounter().chooseMeleeTarget(user, user.getReadiedWeapon().getWeapon());

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);

			int targetArmorClass = target.getArmorClass(user);
			Utils.print("Your target has an AC of " + targetArmorClass);

			int attackRoll = user.attackRoll(AbilityType.STRENGTH, getAccessoryType(), targets);

			if (attackRoll >= targetArmorClass) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				int damageRolls = user.getReadiedWeapon().getWeapon().getDamageRolls();
				DiceType damageDiceType = user.getReadiedWeapon().getWeapon().getDamageDice();

				target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, user.getReadiedWeapon().getWeapon().getDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), user), DamageType.NORMAL, true, user);
				if (Runepriest.class.isAssignableFrom(user.getDndClass().getClass())) {
					Utils.print("You are about to choose the Runic State.  Here is the info about them.");
					Utils.print("Destruction: Next ally attack of this target will deal extra damage and give the ally temp hit points.");
					Utils.print("Protection: Target will take -2 to defenses and next ally to hit target gets AC bonus.");
					RunicState runicState = ((Runepriest) user.getDndClass()).chooseRunicState();
					if (runicState == RunicState.RUNE_OF_DESTRUCTION) {
						if (Creature.class.isAssignableFrom(target.getClass())) {
							Creature cTarget = (Creature) target;
							int abilityModifier = user.getAbilityModifier(AbilityType.WISDOM);
							Utils.print("Adding " + abilityModifier + " damage bonus for the next ally to attack " + target.getName() + ".");
							Utils.print("That ally will also get " + abilityModifier + " temporary hit points.");
							cTarget.setTemporaryEffect(abilityModifier, user.getCurrentTurn(), DurationType.IMMEDIATE_BY_END_OF_NEXT_TURN, user, TemporaryEffectType.RCV_DMG_MOD, TemporaryEffectReason.WORD_OF_EXCHANGE);
						}
					} else {
						if (Creature.class.isAssignableFrom(target.getClass())) {
							Creature cTarget = (Creature) target;
							Utils.print("Adding a -2 penalty to " + target.getName() + " to all defenses until the end of next turn.");
							Utils.print("The next ally to attack " + target.getName() + " before the end of my next turn gets a " + user.getAbilityModifier(AbilityType.WISDOM) + " AC temporary bonus.");
							cTarget.setTemporaryEffect(-2, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.AC_MOD, TemporaryEffectReason.WORD_OF_EXCHANGE);
							cTarget.setTemporaryEffect(-2, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.FORT_MOD, TemporaryEffectReason.WORD_OF_EXCHANGE);
							cTarget.setTemporaryEffect(-2, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.WILL_MOD, TemporaryEffectReason.WORD_OF_EXCHANGE);
							cTarget.setTemporaryEffect(-2, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.REF_MOD, TemporaryEffectReason.WORD_OF_EXCHANGE);
						}
						
//						ally.setTemporaryEffect(abilityModifier, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.ARMOR_CLASS_MODIFIER, );
					}
				}
			} else {
				Utils.print("You missed " + target.getName());
				// Some targets have powers/effects that happen when they are missed.
				target.miss(user);
			}
			return true;
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
		classes.add(Runepriest.class);
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
