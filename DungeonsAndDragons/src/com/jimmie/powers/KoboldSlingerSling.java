package com.jimmie.powers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackTarget;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.TemporaryEffectType;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.monsters.KoboldSlinger;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class KoboldSlingerSling extends AttackPower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Integer NORMAL_SHOT = 1;
	private static final Integer GLUEPOT = 2;
	private static final Integer FIREPOT = 3;
	private static final Integer STINKPOT = 4;

	@Override
	public String getName() {
		return "Sling";
	}

	@Override
	public int getLevel() {
		return 0;
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
		return AccessoryType.NONE;
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
		return 10;
	}

	@Override
	public int getRangeNumber2() {
		return 20;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		if (KoboldSlinger.class.isAssignableFrom(user.getClass())) {
			KoboldSlinger slinger = (KoboldSlinger) user;

			// Are there any rounds left?
			if ((slinger.getNormalShot() == 0) && (slinger.getGluepots() == 0) && (slinger.getFirepots() == 0) && (slinger.getStinkpots() == 0)) {
				Utils.print("Sorry, but " + slinger.getName() + " is out of ammo.");
				return false;
			}
			
			// What kind of ammo are they going to use?
			Utils.print("What kind of shot do you want to use?");
			HashMap<Integer, Integer> shotChoices = new HashMap<Integer, Integer>();
			int i = 0;
			if (slinger.getNormalShot() > 0) {
				i++;
				Utils.print(i + ". Normal Shot. " + slinger.getNormalShot() + " rounds left.");
				shotChoices.put(i, NORMAL_SHOT);
			}
			if (slinger.getGluepots() > 0) {
				i++;
				Utils.print(i + ". Gluepot. " + slinger.getGluepots() + " rounds left.");
				shotChoices.put(i, GLUEPOT);
			}
			if (slinger.getFirepots() > 0) {
				i++;
				Utils.print(i + ". Firepot. " + slinger.getFirepots() + " rounds left.");
				shotChoices.put(i, FIREPOT);
			}
			if (slinger.getStinkpots() > 0) {
				i++;
				Utils.print(i + ". Stinkpot. " + slinger.getStinkpots() + " rounds left.");
				shotChoices.put(i, STINKPOT);
			}
			Utils.print("Your choice:");
			int choice = Utils.getValidIntInputInRange(1, i);
			int shot = shotChoices.get(choice);

			List<AttackTarget> targets = Encounter.getEncounter().chooseRangedTarget(user, 10, 20);

			if ((targets != null) && !(targets.isEmpty())) {
				AttackTarget target = targets.get(0);
				int roll = user.attackRoll(6 + user.getOtherAttackModifier(target));

				Utils.print("You rolled a total of: " + roll);

				int targetArmorClass = target.getArmorClass(user);
				Utils.print("Your target has an AC of " + targetArmorClass);

				if (roll >= targetArmorClass) {
					/* A HIT! */
					Utils.print("You successfully hit " + target.getName());

					int damageRolls = 1;
					DiceType damageDiceType = DiceType.SIX_SIDED;

					int weaponBonus = 3;

					int attributeBonus = 0;

					target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, weaponBonus, attributeBonus, user), DamageType.NORMAL, true, user);
					
					if (Creature.class.isAssignableFrom(target.getClass())) {
						Creature cTarget = (Creature) target;
						if (shot == GLUEPOT) {
							Utils.print(target.getName() + " is immobilized (save ends) because of the gluepot.");
							cTarget.setTemporaryCondition(user, DurationType.SAVE_ENDS, CreatureConditionType.IMMOBILIZED, TemporaryEffectReason.KOBOLD_SLINGER_SLING, user.getCurrentTurn());
						}
						if (shot == FIREPOT) {
							Utils.print(target.getName() + " takes ongoing 2 fire damage (save ends) because of the firepot.");
							cTarget.setTemporaryOngoingDamage(2, user.getCurrentTurn(), DurationType.SAVE_ENDS, user, TemporaryEffectType.ONGOING_DMG, TemporaryEffectReason.KOBOLD_SLINGER_SLING, DamageType.FIRE);
						}
						if (shot == STINKPOT) {
							Utils.print(target.getName() + " takes ongoing a -2 penalty to attack rolls (save ends) because of the stinkpot.");
							cTarget.setTemporaryEffect(-2, user.getCurrentTurn(), DurationType.SAVE_ENDS, user, TemporaryEffectType.ATCK_ROLL_MOD, TemporaryEffectReason.KOBOLD_SLINGER_SLING);
						}
					}
				} else {
					Utils.print("You missed " + target.getName());
					// Some targets have powers/effects that happen when they are missed.
					target.miss(user, this);
				}
				// Reduce the number of rounds
				if (shot == NORMAL_SHOT) {
					slinger.setNormalShot(slinger.getNormalShot()-1);
				}
				if (shot == GLUEPOT) {
					slinger.setGluepots(slinger.getGluepots()-1);
				}
				if (shot == FIREPOT) {
					slinger.setFirepots(slinger.getFirepots()-1);
				}
				if (shot == STINKPOT) {
					slinger.setStinkpots(slinger.getStinkpots()-1);
				}
			}
		}
		return true;
	}

	@Override
	public boolean isBasicAttack() {
		return true;
	}

	@Override
	public AttackType getAttackType() {
		return AttackType.RANGED_NUMBER;
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
		if (KoboldSlinger.class.isAssignableFrom(user.getClass())) {
			KoboldSlinger slinger = (KoboldSlinger) user;
			// Are there any rounds left?
			if ((slinger.getNormalShot() == 0) && (slinger.getGluepots() == 0) && (slinger.getFirepots() == 0) && (slinger.getStinkpots() == 0)) {
				return false;
			}
			return true;
		}
		return false;
	}
}
