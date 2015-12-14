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
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.classes.GuardianMight;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.items.weapons.ReadiedWeapon;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Dice;
import com.jimmie.util.Utils;

public class ThunderRamAssault extends AttackPower {
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
		return "Thunder Ram Assault";
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
		return PowerSource.PRIMAL;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.WEAPON;
	}

	@Override
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.THUNDER);
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
		effectTypes.add(EffectType.NONE);
		return effectTypes;
	}

	@Override
	public void process(Encounter encounter, Creature user) {
		if (timesUsed == 0) {
			timesUsed++;
			List<AttackTarget> targets = encounter.chooseMeleeTarget(user, user.getReadiedWeapon().getWeapon());

			if ((targets != null) && !(targets.isEmpty())) {
				AttackTarget target = targets.get(0);
				Dice d = new Dice(DiceType.TWENTY_SIDED);
				int diceRoll = d.roll();
				int roll = diceRoll + user.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + user.getWeaponProficiencyBonus() + user.getOtherAttackModifier(targets, encounter);

				Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);

				int targetArmorClass = target.getArmorClass(user);
				Utils.print("Your target has an AC of " + targetArmorClass);

				if (roll >= targetArmorClass) {
					/* A HIT! */
					Utils.print("You successfully hit " + target.getName());

					int damageRolls = user.getReadiedWeapon().getWeapon().getDamageRolls();
					DiceType damageDiceType = user.getReadiedWeapon().getWeapon().getDamageDice();

					/* TODO: Supposed to be THUNDER damage.  Haven't implemented that yet. */
					target.hurt(Utils.rollForDamage(damageRolls, damageDiceType, user.getReadiedWeapon().getWeapon().getDamageBonus(), user.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH), user.getRace()), DamageType.THUNDER, encounter, true, user);

					DndClass dndClass = user.getDndClass();
					GuardianMight guardianMight = null;
					if (Warden.class.isAssignableFrom(dndClass.getClass())) {
						guardianMight = ((Warden) dndClass).getGuardianMight();
					}
					/* If you chose the Earth Strength build, you can push the primary target. */
					if (guardianMight == GuardianMight.EARTHSTRENGTH) {
						String pushDirection = encounter.getPushDirection(user.getCurrentPosition(), target.getCurrentPosition());
						for (int i = 0; i < user.getAbilityModifierPlusHalfLevel(AbilityType.CONSTITUTION); i++) {
							target.push(pushDirection);
						}
					}

					int lowerLeftX = 0;
					int lowerLeftY = 0;

					Utils.print("Please enter the lower left X coordinate of the blast. No validation is done here, so do it right!");
					lowerLeftX = Utils.getValidIntInputInRange(1, 50);

					Utils.print("Please enter the lower left Y coordinate of the blast. No validation is done here, so do it right!");
					lowerLeftY = Utils.getValidIntInputInRange(1, 50);

					List<Creature> blastTargets = encounter.getAllCreaturesInBlast(lowerLeftX, lowerLeftY, 3);

					Dice secondaryDice = new Dice(DiceType.SIX_SIDED);
					int secondaryDamage = secondaryDice.roll();

					for (Creature secondaryTarget : blastTargets) {
						List<AttackTarget> secondaryTargets = new ArrayList<AttackTarget>();
						secondaryTargets.add(secondaryTarget);
						d = new Dice(DiceType.TWENTY_SIDED);
						diceRoll = d.roll();
						roll = diceRoll + user.getAbilityModifierPlusHalfLevel(AbilityType.STRENGTH) + user.getWeaponProficiencyBonus() + user.getOtherAttackModifier(secondaryTargets, encounter);

						Utils.print("You rolled a " + diceRoll + " for a total of: " + roll);

						int secondaryTargetFortitude = secondaryTarget.getFortitude();
						Utils.print("Your secondary target has an fortitude of " + secondaryTargetFortitude);

						if (roll >= secondaryTargetFortitude) {
							/* A HIT! */
							Utils.print("You successfully hit " + secondaryTarget.getName());

							secondaryTarget.hurt(secondaryDamage, DamageType.THUNDER, encounter, true, user);

							/* If you chose the Earth Strength build, you can push the primary target. */
							String pushDirection = encounter.getPushDirection(user.getCurrentPosition(), secondaryTarget.getCurrentPosition());
							secondaryTarget.push(pushDirection);
						} else {
							Utils.print("Sorry.  You missed " + secondaryTarget.getName());
						}
					}

				} else {
					Utils.print("You missed " + target.getName());
				}
			}
		}else {
			Utils.print("Sorry, but " + user.getName() + " has already used Thunder Ram Assault in this encounter.");
			Utils.print("I know it would have been nice if I had told you that before you picked it, though");
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
		classes.add(Warden.class);
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
