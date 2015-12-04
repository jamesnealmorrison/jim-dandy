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
import com.jimmie.domain.classes.Psion;
import com.jimmie.domain.creatures.Character;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class TelekineticAnchor extends AttackPower {
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
		return "Telekinetic Anchor";
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
		return PowerSource.PSIONIC;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.IMPLEMENT;
	}

	@Override
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.FORCE);
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
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public void process(Encounter encounter, Creature user) {		
		if (timesUsed == 0) {
			timesUsed++;
			
			Character c = null;
			if (Character.class.isAssignableFrom(user.getClass())) {
				c = (Character) user;
			}		
			
			List<AttackTarget> targets = new ArrayList<AttackTarget>();
			Utils.print("Please enter the X coordinate of the burst (within range of 10). No validation is done here, so do it right!");
			int x = Utils.getValidIntInputInRange(1, 50);

			Utils.print("Please enter the Y coordinate of the burst (within range of 10). No validation is done here, so do it right!");
			int y = Utils.getValidIntInputInRange(1, 50);

			/* Got to do this wierd conversion between creatures and attack targets. */
			List<Creature> creatureTargets = encounter.getAllCreaturesInAreaBurst(x, y, 1);
			for (Creature creature : creatureTargets) {
				targets.add(creature);
			}

			Utils.print("Since this might affect multiple targets, rolling for damage first.");
			int damageRolls = 3;

			Dice damageDice = new Dice(DiceType.SIX_SIDED);
			int damage = 0;

			for (int rolls = 0; rolls < damageRolls; rolls++) {
				damage = damage + damageDice.basicRoll() ;
			}
			damage = damage + user.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE);

			for (AttackTarget target : targets) {
				Dice d = new Dice(DiceType.TWENTY_SIDED);
				int diceRoll = d.attackRoll(user, target, encounter, user.getCurrentPosition());
				int roll = diceRoll + user.getAbilityModifierPlusHalfLevel(AbilityType.INTELLIGENCE) + c.getImplementAttackBonus() + user.getOtherAttackModifier(targets, encounter);

				Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);

				int targetFortitude = target.getFortitude();
				Utils.print("Your target has an Fortitude of " + targetFortitude);

				if (roll >= targetFortitude) {
					/* A HIT! */
					Utils.print("You successfully hit " + target.getName());

					/* See if this target was hit by Stirring Shout. */
					if (target.isHitByStirringShout()) {
						Utils.print("You hit a target that was previously hit by Stirring Shout (bard power). You get " + target.getStirringShoutCharismaModifier() + " hit points.");
						user.heal(target.getStirringShoutCharismaModifier());
					}

					target.hurt(damage, DamageType.FORCE, encounter, true);
				} else {
					Utils.print("Sorry.  You missed " + target.getName());
				}

				/* Whether it hits or not, the target will take 5 force damage if it moves on its next turn. */
				target.hitByTelekineticAnchor();
			}
		} else {
			Utils.print("Sorry, but " + user.getName() + " has already used Telekinetic Anchor today.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though.");
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
		classes.add(Psion.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		return true;
	}

}
