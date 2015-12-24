package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
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

public class WordOfBinding extends AttackPower {
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
		return "Word Of Binding";
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
	public void process(Creature user) {
		List<AttackTarget> targets = Encounter.getEncounter().chooseMeleeTarget(user, user.getReadiedWeapon().getWeapon());

		if ((targets != null) && !(targets.isEmpty())) {
			AttackTarget target = targets.get(0);

			int targetArmorClass = target.getArmorClass(user);
			Utils.print("Your target has an AC of " + targetArmorClass);

			int attackRoll = user.attackRoll(AbilityType.STRENGTH, getAccessoryType(), targets);

			if (attackRoll >= targetArmorClass) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				target.hurt(user.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), DamageType.NORMAL, true, user);

				if (Runepriest.class.isAssignableFrom(user.getDndClass().getClass())) {
					Utils.print("You are about to choose the Runic State.  Here is the info about them.");
					Utils.print("Destruction: Next attack by ally to target will deal extra damage.");
					Utils.print("Protection: One ally will gain AC bonus.  Ally must be adjacent to me or target.");
					RunicState runicState = ((Runepriest) user.getDndClass()).chooseRunicState();
					if (runicState == RunicState.RUNE_OF_DESTRUCTION) {
						if (Creature.class.isAssignableFrom(target.getClass())) {
							Creature cTarget = (Creature) target;
							int abilityModifier = user.getAbilityModifier(AbilityType.WISDOM);
							Utils.print("Adding " + abilityModifier + " damage bonus for the next ally to attack " + target.getName() + ".");
							cTarget.setTemporaryEffect(abilityModifier, user.getCurrentTurn(), DurationType.IMMEDIATE_BY_END_OF_NEXT_TURN, user, TemporaryEffectType.RECEIVING_DAMAGE_MODIFIER, TemporaryEffectReason.WORD_OF_BINDING);
						}
						
					} else {
						Creature ally = Encounter.getEncounter().chooseAllyAdjacentToEither(user, user.getCurrentPosition(), target.getCurrentPosition());
						int abilityModifier = user.getAbilityModifier(AbilityType.WISDOM);
						Utils.print("Adding " + abilityModifier + " AC bonus to " + ally.getName() + " until the end of next turn.");
						ally.setTemporaryEffect(abilityModifier, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.ARMOR_CLASS_MODIFIER, TemporaryEffectReason.WORD_OF_BINDING);
					}
				}

			} else {
				Utils.print("You missed " + target.getName());
				// Some targets have powers/effects that happen when they are missed.
				target.miss(user);
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
