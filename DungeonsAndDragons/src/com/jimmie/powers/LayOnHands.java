package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.AttackType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.classes.Paladin;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.feats.FeatType;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;


public class LayOnHands extends AttackPower {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean usedThisRound = false;

	@Override
	public AttackType getAttackType() {
		return AttackType.MELEE_TOUCH;
	}

	@Override
	public String getName() {
		return "Lay on Hands";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public PowerUsage getPowerUsage() {
		// TODO: I don't think there is really a need to implement an "At-Will (Special)"
		return PowerUsage.AT_WILL;
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
		return 0;
	}

	@Override
	public int getRangeNumber2() {
		return 0;
	}

	@Override
	public List<EffectType> getEffectTypes() {
		List<EffectType> effectTypes = new ArrayList<EffectType>();
		effectTypes.add(EffectType.HEALING);
		return effectTypes;
	}

	@Override
	public boolean process(Creature user) {
		Creature ally = Encounter.getEncounter().chooseAllyAdjacentTo(user, user.getCurrentPosition());
		DndCharacter cUser = null;
		DndCharacter cAlly = null;
		
		if ((DndCharacter.class.isAssignableFrom(user.getClass())) && (DndCharacter.class.isAssignableFrom(ally.getClass()))) {
			 cUser = (DndCharacter) user;
			 cAlly = (DndCharacter) ally;
		}
		timesUsed++;
		usedThisRound = true;
		// Give an extra healing surge to the ally (Because useHealingSurge will deduct one.
		cAlly.setCurrentSurgeUses(cAlly.getCurrentSurgeUses()-1);
		cAlly.useHealingSurge();
		// Remove one from mine.
		cUser.setCurrentSurgeUses(cUser.getCurrentSurgeUses()+1);
		// Check for Healing Hands Feat
		if (PlayerCharacter.class.isAssignableFrom(user.getClass())) {
			PlayerCharacter pc = (PlayerCharacter) user;
			if (pc.getFeats() != null) {
				if (pc.hasFeat(FeatType.HEALING_HANDS)) {
					int charisma = user.getAbilityModifier(AbilityType.CHARISMA);
					Utils.print("Because " + user.getName() + " has the Healing Hands feat, " + cAlly.getName() + " gains an additional " + charisma);
					cAlly.heal(charisma);
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
		List<Class> classes = new ArrayList<Class>();
		classes.add(Paladin.class);
		return classes;
	}

	@Override
	public boolean meetsPrerequisitesToChoosePower(Creature user) {
		return true;
	}

	@Override
	public void initializeForStartOfTurn() {
		usedThisRound = false;
	}

	@Override
	public boolean meetsRequirementsToUsePower(Creature user) {
		if ((timesUsed <= user.getAbilityModifier(AbilityType.WISDOM)) && (!usedThisRound )) {
			if (DndCharacter.class.isAssignableFrom(user.getClass())) {
				if (((DndCharacter) user).getCurrentSurgeUses() < ((DndCharacter) user).getHealingSurgesPerDay()) {
					return true;
				}
			}
		}
		return false;
	}
}
