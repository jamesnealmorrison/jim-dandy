package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceRollType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.Position;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.ZoneShape;
import com.jimmie.domain.ZoneType;
import com.jimmie.domain.classes.Runepriest;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class RuneOfTheUndeniableDawn extends AttackPower {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.CLOSE_BURST;
	}

	@Override
	public String getName() {
		return "Rune Of Undeniable Dawn";
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
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.RADIANT);
		return damageTypes;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.STANDARD;
	}

	@Override
	public int getRangeNumber1() {
		return 3;
	}

	@Override
	public int getRangeNumber2() {
		return 0;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.ZONE);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		if (timesUsed > 0) {
			Utils.print("You can't use this power.  Sorry.");
			return false;
		} else {
			List<Creature> cTargets = Encounter.getEncounter().getEnemiesWithinRangeOf(user, user.getCurrentPosition(), 3);
			List<AttackTarget> targets = new ArrayList<AttackTarget>();
			targets.addAll(cTargets);

			Utils.print("Since this might affect multiple targets, rolling for damage first.");
			DiceType damageDiceType = user.getReadiedWeapon().getWeapon().getDamageDice();
			Dice damageDice = new Dice(damageDiceType);
			int damageRolls = user.getReadiedWeapon().getWeapon().getDamageRolls();
			int damage = 0;

			for (int rolls = 0; rolls < damageRolls; rolls++) {
				damage = damage + damageDice.roll(DiceRollType.DAMAGE_ROLL_MODIFICATION);
			}
			damage = damage + user.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH);

			List<Creature> hitTargets = new ArrayList<Creature>();
			for (AttackTarget target : targets) {
				int targetAC = target.getArmorClass(user);
				Utils.print("Your target, " + target.getName() + ", has an AC of " + targetAC);

				int attackRoll = user.attackRoll(AbilityType.STRENGTH, getAccessoryType(), targets);

				if (attackRoll >= targetAC) {
					/* A HIT! */
					Utils.print("You successfully hit " + target.getName());

					target.hurt(damage, DamageType.RADIANT, true, user);

					hitTargets.add((Creature) target);
				} else {
					Utils.print("Sorry.  You missed " + target.getName() + ". Doing half damage.");

					target.hurt(damage/2, DamageType.RADIANT, false, user);

					target.miss(user);
				}
			}

			Position position = new Position(user.getCurrentPosition().getX(), user.getCurrentPosition().getY());
			Encounter.getEncounter().setZone(position, ZoneShape.BURST, 3, user, DurationType.END_OF_NEXT_TURN, user.getCurrentTurn(), ZoneType.RUNE_OF_THE_UNDENIABLE_DAWN);
			timesUsed++;
			return true;
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
		// Has it been used during this encounter already?
		if (timesUsed > 0) {
			return false;
		}
		return true;
	}

}
