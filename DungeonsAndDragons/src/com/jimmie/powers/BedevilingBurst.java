package com.jimmie.powers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.TemporaryEffectReason;
import com.jimmie.domain.classes.Sorcerer;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class BedevilingBurst extends AttackPower {
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
		return "Bedeviling Burst";
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
		return PowerSource.ARCANE;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.IMPLEMENT;
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
		List<AttackTarget> allTargets = Encounter.getEncounter().getAllEnemiesInAreaBurst(user, user.getCurrentPosition(), 3);
		List<AttackTarget> targets = new ArrayList<AttackTarget>();
		List<Creature> pushTargets = new ArrayList<Creature>();
		List<AttackTarget> slideTargets = new ArrayList<AttackTarget>();

		// If there are only 1 or 2 creatures returned, then just assume those are the ones to attack
		if (allTargets.size() <= 2) {
			targets.addAll(allTargets);
		} else {
			Utils.print("There are more than 2 creatures in the burst.  You must select which two to target.");
			int i = 0;
			for (i = 0; i < 2; i++) {
				Utils.print("Choose one:");
				int index = 0;
				HashMap<Integer, AttackTarget> validChoices = new HashMap<Integer, AttackTarget>();
				for (Iterator<AttackTarget> it = allTargets.iterator(); it.hasNext();) {
					AttackTarget target = it.next();
					index++;
					Utils.print(index + ". " + target.getName());
					validChoices.put(index, target);
				}
				Utils.print("Your choice: ");
				int choice = Utils.getValidIntInputInRange(1, index);
				targets.add(validChoices.get(choice));
			}
		}
		
		int damageRolls = 1;
		
		Utils.print("Since this might affect multiple targets, rolling for damage first.");
		Dice damageDice = new Dice(DiceType.TEN_SIDED);
 		int damage = 0;
 		
 		for (int rolls = 0; rolls < damageRolls; rolls++) {
 		    damage = damage + damageDice.roll(DiceRollType.DAMAGE_ROLL);
 		}
 		damage = damage + user.getAbilityModifierPlusHalfLevel(AbilityType.CHARISMA);

 		DndCharacter c = null;
		if (DndCharacter.class.isAssignableFrom(user.getClass())) {
			c = (DndCharacter) user;
		}
		
		Sorcerer sorcerer = null;
		if (Sorcerer.class.isAssignableFrom(c.getDndClass().getClass())) {
			sorcerer = (Sorcerer) c.getDndClass();
		}

		for (AttackTarget target : targets) {
			int targetWill = target.getWill(user);
			Utils.print("Your target has a Will of " + targetWill);
			
			int attackRoll = user.attackRoll(AbilityType.CHARISMA, getAccessoryType(), target);
			
			// Check for unfettered power.
			if (sorcerer.getUnfetteredPower() == 1) {
				Utils.print("Because of your unfettered power, you push all creatures within 5 squares.");
				pushTargets = Encounter.getEncounter().getAllCreaturesInAreaBurst(user.getCurrentPosition(), 5);
				user.pushTargets(pushTargets, 1);
			}

			if (attackRoll >= targetWill) {
				/* A HIT! */
				Utils.print("You successfully hit " + target.getName());

				target.hurt(damage, DamageType.PSYCHIC, true, user);
				
				Creature cTarget = null;
				
				if (sorcerer.getLastAttackRollEven()) {
					// Wild Magic.  Slide instead of push.
					Utils.print("Because of your even numbered roll, you will slide this target instead of pushing them.");
					slideTargets.add(target);
				} else {
					if (Creature.class.isAssignableFrom(target.getClass())) {
						cTarget = (Creature) target;
						pushTargets.add(cTarget);
					}
				}
				
				if (sorcerer.getUnfetteredPower() == 20) {
					Utils.print("Because of your unfettered power, you get to slide " + target.getName() + " 1 square and knock them prone.");
					user.slideTargets(targets, 1);
					if (Creature.class.isAssignableFrom(target.getClass())) {
						cTarget = (Creature) target;
						// TODO: I don't think I've implemented standing up from prone yet.
						cTarget.setTemporaryCondition(user, DurationType.SPECIAL, CreatureConditionType.PRONE, TemporaryEffectReason.BEDEVILING_BURST, user.getCurrentTurn());
					}
				}

			} else {
			    Utils.print("Sorry.  You missed " + target.getName());
				// Some targets have powers/effects that happen when they are missed.
				target.miss(user, this);
			}
		}
		
		if (!pushTargets.isEmpty()) {
			user.pushTargets(pushTargets, user.getAbilityModifier(AbilityType.DEXTERITY));
		}
		if (!slideTargets.isEmpty()) {
			user.slideTargets(slideTargets, user.getAbilityModifier(AbilityType.DEXTERITY));
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
		classes.add(Sorcerer.class);
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
