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
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.classes.Druid;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class FiresOfLife extends AttackPower {
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
		return "Fires Of Life";
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
		return PowerSource.PRIMAL;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.IMPLEMENT;
	}

	@Override
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.FIRE);
		return damageTypes;
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
		effectTypes.add(EffectType.HEALING);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		if (timesUsed == 0) {
			timesUsed++;
			List<AttackTarget> targets = new ArrayList<AttackTarget>();
			Encounter.showCoordinateSystem(true);

			Utils.print("Please enter the X coordinate of the burst (within range of 10). No validation is done here, so do it right!");
			int x = Utils.getValidIntInputInRange(1, 50);

			Utils.print("Please enter the Y coordinate of the burst (within range of 10). No validation is done here, so do it right!");
			int y = Utils.getValidIntInputInRange(1, 50);
			Encounter.showCoordinateSystem(false);

			/* Got to do this weird conversion between creatures and attack targets. */
			targets = Encounter.getEncounter().getAllEnemiesInAreaBurst(user, new Position(x, y), 1);

			Utils.print("Since this might affect multiple targets, rolling for damage first.");
			int damageRolls = 1;

			Dice damageDice = new Dice(DiceType.SIX_SIDED);
			int damage = 0;

			for (int rolls = 0; rolls < damageRolls; rolls++) {
				damage = damage + damageDice.roll(DiceRollType.DAMAGE_ROLL_MODIFICATION);
			}

			for (AttackTarget target : targets) {
				int targetReflex = target.getReflex(user);
				Utils.print("Your target, " + target.getName() + ", has a Reflex of " + targetReflex);

				int attackRoll = user.attackRoll(AbilityType.WISDOM, getAccessoryType(), targets);

				if (attackRoll >= targetReflex) {
					/* A HIT! */
					Utils.print("You successfully hit " + target.getName());
					target.hurt(damage+user.getAbilityModifier(AbilityType.WISDOM), DamageType.FIRE, true, user);
					if (Creature.class.isAssignableFrom(target.getClass())) {
						Creature cTarget = (Creature) target;
						cTarget.setTemporaryOngoingDamage(5,user.getCurrentTurn(), DurationType.SAVE_ENDS, user, TemporaryEffectType.ONGOING_DMG, TemporaryEffectReason.FIRES_OF_LIFE, DamageType.FIRE);
					}
				} else {
					Utils.print("Sorry.  You missed " + target.getName());
					// Some targets have powers/effects that happen when they are missed.
					target.miss(user);
				}
			}
			
			// After Effect; one creature within 5 squares regains 2 HP.
			Utils.print("As an after effect, an ally within 5 squares regains 2 hit points.");
			Creature ally = Encounter.getEncounter().chooseAllyWithinRangeOf(user, user.getCurrentPosition(), 5);
			if (ally != null) {
				ally.heal(2);
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
		classes.add(Druid.class);
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
