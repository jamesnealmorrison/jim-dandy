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
import com.jimmie.domain.EffectType;
import com.jimmie.domain.Position;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.classes.Druid;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class ChillWind extends AttackPower {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.AREA_BURST;
	}

	@Override
	public String getName() {
		return "Chill Wind";
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
		return PowerSource.PRIMAL;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.IMPLEMENT;
	}

	@Override
	public DamageType getDamageType() {
		return DamageType.COLD;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.STANDARD;
	}

	@Override
	public int getRangeNumber1() {
		return 1;
	}

	@Override
	public int getRangeNumber2() {
		return 10;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		timesUsed++;

		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		List<AttackTarget> sliders = new ArrayList<AttackTarget>();
		boolean sliding = false;
		Encounter.showCoordinateSystem(true);
		
		Utils.print("Please enter the X coordinate of the burst (within range of 10). No validation is done here, so do it right!");
		int x = Utils.getValidIntInputInRange(1, 50);

		Utils.print("Please enter the Y coordinate of the burst (within range of 10). No validation is done here, so do it right!");
		int y = Utils.getValidIntInputInRange(1, 50);
		Encounter.showCoordinateSystem(false);

		/* Got to do this weird conversion between creatures and attack targets. */
		List<Creature> creatureTargets = Encounter.getEncounter().getAllCreaturesInAreaBurst(new Position(x, y), 1);
		for (Creature creature : creatureTargets) {
			targets.add(creature);
		}

		Utils.print("Since this might affect multiple targets, rolling for damage first.");
		int damageRolls = 1;

		Dice damageDice = new Dice(DiceType.SIX_SIDED);
		int damage = 0;

		for (int rolls = 0; rolls < damageRolls; rolls++) {
			damage = damage + damageDice.roll(DiceRollType.DAMAGE_ROLL_MODIFICATION);
		}
		
		for (AttackTarget target : targets) {
			int targetFortitude = target.getFortitude(user);
			Utils.print("Your target, " + target.getName() + ", has a Fortitude of " + targetFortitude);

			int attackRoll = user.attackRoll(AbilityType.WISDOM, getAccessoryType(), target);

			if (attackRoll >= targetFortitude) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());
				Utils.print("Doing " + damage + " cold damage and you get to slide them 1 square.");
				target.hurt(damage, DamageType.COLD, true, user);
				sliders.add(target);
				sliding = true;
			} else {
				Utils.print("Sorry.  You missed " + target.getName());
				// Some targets have powers/effects that happen when they are missed.
				target.miss(user, this);
			}
		}
		
		if (sliding) {
			user.slideTargets(sliders, 1);
		}
		return true;
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
		classes.add(Druid.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		if (user.getDndClass() != null) {
			if (Druid.class.isAssignableFrom(user.getDndClass().getClass())) {
				// A druid must be in humanoid form to use this.
				Druid druid = (Druid) user.getDndClass();
				if (druid.isInBeastForm()) {
					return false;
				}
			}
		}
		return true;
	}

}
