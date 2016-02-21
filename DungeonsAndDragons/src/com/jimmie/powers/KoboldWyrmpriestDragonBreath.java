package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceRollType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class KoboldWyrmpriestDragonBreath extends AttackPower {
	private DamageType damageType;

	public KoboldWyrmpriestDragonBreath(DamageType damageType) {
		this.damageType = damageType;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public AttackType getAttackType() {
		return AttackType.CLOSE_BLAST;
	}

	@Override
	public String getName() {
		return "Dragon Breath";
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
		return PowerSource.NONE;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.NONE;
	}

	@Override
	public DamageType getDamageType() {
		return damageType;
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
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		if (timesUsed == 0) {
			timesUsed++;

			int lowerLeftX = 0;
			int lowerLeftY = 0;

			Encounter.showCoordinateSystem(true);
			Utils.print("Please enter the lower left X coordinate of the blast. No validation is done here, so do it right!");
			lowerLeftX = Utils.getValidIntInputInRange(1, 50);

			Utils.print("Please enter the lower left Y coordinate of the blast. No validation is done here, so do it right!");
			lowerLeftY = Utils.getValidIntInputInRange(1, 50);
			Encounter.showCoordinateSystem(false);

			List<Creature> blastTargets = Encounter.getEncounter().getAllCreaturesInBlast(lowerLeftX, lowerLeftY, 3);

			Dice dice = new Dice(DiceType.TEN_SIDED);
			int damage = dice.roll(DiceRollType.DAMAGE_ROLL) + 3;

			if (blastTargets != null) {
				for (Creature target : blastTargets) {
					List<AttackTarget> targets = new ArrayList<AttackTarget>();
					int targetFortitude = target.getFortitude(user);
					Utils.print(target.getName() + "has an fortitude of " + targetFortitude);

					targets.add(target);
					int attackRoll = user.attackRoll(7 + user.getOtherAttackModifier(target));

					if (attackRoll >= targetFortitude) {
						/* A HIT! */
						Utils.print("You successfully hit " + target.getName());

						target.hurt(damage, damageType, true, user, getAttackType());
					} else {
						Utils.print("Sorry.  You missed " + target.getName() + ". doing half damage.");
						target.hurt(damage/2, damageType, false, user, getAttackType());
						// Some targets have powers/effects that happen when they are missed.
						target.miss(user, this);
					}
				}
			}
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
		return null;
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
