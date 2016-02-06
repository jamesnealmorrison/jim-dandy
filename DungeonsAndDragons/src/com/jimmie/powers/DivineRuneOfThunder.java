package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.CombatAdvantageType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.RunicState;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.classes.Runepriest;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.items.weapons.ReadiedWeapon;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class DivineRuneOfThunder extends AttackPower {
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
		return "Divine Rune Of Thunder";
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
		return DamageType.THUNDER;
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
		if (timesUsed < 1) {
			List<AttackTarget> targets = Encounter.getEncounter().chooseMeleeTarget(user, user.getReadiedWeapon().getWeapon());

			if ((targets != null) && !(targets.isEmpty())) {
				AttackTarget target = targets.get(0);

				int targetArmorClass = target.getArmorClass(user);
				Utils.print("Your target has an AC of " + targetArmorClass);

				int attackRoll = user.attackRoll(AbilityType.STRENGTH, getAccessoryType(), target);

				if (attackRoll >= targetArmorClass) {
					/* A HIT! */
					Utils.print("You successfully hit " + target.getName());

					int damageRolls = user.getReadiedWeapon().getWeapon().getDamageRolls();
					DiceType damageDiceType = user.getReadiedWeapon().getWeapon().getDamageDice();

					RunicState runicState = null;
					if (Runepriest.class.isAssignableFrom(user.getDndClass().getClass())) {
						Utils.print("You are about to choose the Runic State.  Here is the info about them.");
						Utils.print("Destruction: Wisdom bonus to damage roll and target grants combat advantage.");
						Utils.print("Protection: Push target and daze them");
						runicState = ((Runepriest) user.getDndClass()).chooseRunicState();
						if (runicState == RunicState.RUNE_OF_DESTRUCTION) {
							if (Creature.class.isAssignableFrom(target.getClass())) {
								Creature cTarget = (Creature) target;
								int abilityModifier = user.getAbilityModifier(AbilityType.WISDOM);
								Utils.print("Adding " + abilityModifier + " damage bonus to this roll and " + cTarget.getName() + " grants combat advantage until the end of my next turn.");
								cTarget.setTemporaryCombatAdvantage(user, DurationType.END_OF_NEXT_TURN, CombatAdvantageType.DIVINE_RUNE_OF_THUNDER, user.getCurrentTurn());
							}
						} else {
							if (Creature.class.isAssignableFrom(target.getClass())) {
								Creature cTarget = (Creature) target;
								int abilityModifier = user.getAbilityModifier(AbilityType.WISDOM);
								Utils.print("Pushing " + target.getName() + " " + abilityModifier + " squares and they are dazed until the end of my next turn.");
								String pushDirection = Encounter.getEncounter().getPushDirection(user.getCurrentPosition(), target.getCurrentPosition());
								for (int i = 0; i < abilityModifier; i++) {
									target.push(pushDirection);
								}
								cTarget.setTemporaryCondition(user, DurationType.END_OF_NEXT_TURN, CreatureConditionType.DAZED, TemporaryEffectReason.DIVINE_RUNE_OF_THUNDER, user.getCurrentTurn());
							}
						}
						if (runicState == RunicState.RUNE_OF_DESTRUCTION) {
							target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, user.getReadiedWeapon().getWeapon().getDamageBonus() + user.getAbilityModifier(AbilityType.WISDOM), user.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), user), DamageType.THUNDER, true, user);
						} else {
							target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, user.getReadiedWeapon().getWeapon().getDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), user), DamageType.THUNDER, true, user);						
						}

					}


				} else {
					Utils.print("You missed " + target.getName());
					// Some targets have powers/effects that happen when they are missed.
					target.miss(user, this);
				}
				timesUsed++;
				return true;
			}
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
