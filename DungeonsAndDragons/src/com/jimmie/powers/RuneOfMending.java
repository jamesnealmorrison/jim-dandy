package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
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
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.feats.FeatType;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;

public class RuneOfMending extends AttackPower {
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
		return "Rune Of Mending";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		return PowerUsage.ENCOUNTER_SPECIAL;
	}

	@Override
	public PowerSource getPowerSource() {
		return PowerSource.DIVINE;
	}

	@Override
	public AccessoryType getAccessoryType() {
		return AccessoryType.NONE;
	}

	@Override
	public DamageType getDamageType() {
		return DamageType.NONE;
	}

	@Override
	public ActionType getActionType() {
		return ActionType.MINOR;
	}

	@Override
	public int getRangeNumber1() {
		return 5;
	}

	@Override
	public int getRangeNumber2() {
		return 0;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.HEALING);
		effectTypes.add(EffectType.RUNIC);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		if (timesUsed < 2) {
			Utils.print("Would you like to apply this power to yourself or an ally?");
			Utils.print("1. Self");
			Utils.print("2. Ally");
			Utils.print("Your choice:");
			int choice = Utils.getValidIntInputInRange(1, 2);
			Creature target = null;
			if (choice == 1) {
				target = user;
			} else {
				target = Encounter.getEncounter().chooseAllyWithinRangeOf(user, user.getCurrentPosition(), 5);
			}
			
			if (DndCharacter.class.isAssignableFrom(target.getClass())) {
				((DndCharacter) target).useHealingSurge(); 
			}
			
			// Look for Rune of Hope feat
			if (PlayerCharacter.class.isAssignableFrom(user.getClass())) {
				PlayerCharacter pc = (PlayerCharacter) user;
				if (pc.hasFeat(FeatType.RUNE_OF_HOPE)) {
					int runeFeats = pc.getNumberOfRuneFeats();
					Utils.print("Because " + user.getName() + " has the Rune of Hope feat, they get " + runeFeats + " temporary hit points.");
					pc.setTemporaryHitPoints(runeFeats);
				}
			}
			
			if (Runepriest.class.isAssignableFrom(user.getDndClass().getClass())) {
				Utils.print("You are about to choose the Runic State.  Here is the info about them.");
				Utils.print("Destruction: +2 bonus to damage rolls for me and allies within 5 range.");
				Utils.print("Protection: +1 bonus to all defenses for me and allies within 5 range.");
				RunicState runicState = ((Runepriest) user.getDndClass()).chooseRunicState();
				if (runicState == RunicState.RUNE_OF_DESTRUCTION) {
					List<Creature> allies = Encounter.getEncounter().getAlliesWithinRangeOf(user, user.getCurrentPosition(), 5);
					for (Creature ally : allies) {
						Utils.print("Adding 2 damage bonus to " + ally.getName() + " until the end of next turn.");
						ally.setTemporaryEffect(2, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.DMG_MOD, TemporaryEffectReason.RUNE_OF_MENDING);
					}
				} else {
					List<Creature> allies = Encounter.getEncounter().getAlliesWithinRangeOf(user, user.getCurrentPosition(), 5);
					for (Creature ally : allies) {
						Utils.print("Adding 1 defense bonus to " + ally.getName() + " until the end of next turn.");
						ally.setTemporaryEffect(1, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.AC_MOD, TemporaryEffectReason.RUNE_OF_MENDING);
						ally.setTemporaryEffect(1, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.FORT_MOD, TemporaryEffectReason.RUNE_OF_MENDING);
						ally.setTemporaryEffect(1, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.WILL_MOD, TemporaryEffectReason.RUNE_OF_MENDING);
						ally.setTemporaryEffect(1, user.getCurrentTurn(), DurationType.END_OF_NEXT_TURN, user, TemporaryEffectType.REF_MOD, TemporaryEffectReason.RUNE_OF_MENDING);
					}
					
				}
			}
			
			timesUsed++;
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
		classes.add(Runepriest.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		// This can be used twice per encounter.
		// TODO - but I didn't implement "once per round" yet.
		if (timesUsed > 1) {
			return false;
		}
		return true;
	}

}
