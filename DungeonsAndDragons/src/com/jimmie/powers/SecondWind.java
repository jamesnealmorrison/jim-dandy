package com.jimmie.powers;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AccessoryType;
import com.jimmie.domain.ActionType;
import com.jimmie.domain.DamageType;
import com.jimmie.domain.DurationType;
import com.jimmie.domain.EffectType;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.encounters.Encounter;
import com.jimmie.util.Utils;
import com.jimmie.domain.creatures.Character;

public class SecondWind extends Power {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Second Wind";
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
	public List<DamageType> getDamageType() {
		List<DamageType> damageTypes = new ArrayList<DamageType>();
		damageTypes.add(DamageType.NONE);
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
		// Second winds should only be available to characters
		if (!Character.class.isAssignableFrom(user.getClass())) {
			Utils.print(user.getDisplayName() + " is not a character.  Not sure how you got to this second wind power.");
		} else {
			Character player = (Character) user;
			if (timesUsed == 0) {
				timesUsed++;
				player.useHealingSurge();
				player.setTemporaryArmorClassBonus(2, player.getCurrentTurn(), DurationType.START_OF_NEXT_TURN, player);

				player.setTemporaryWillBonus(2, player.getCurrentTurn(), DurationType.START_OF_NEXT_TURN, player);
				player.setTemporaryReflexBonus(2, player.getCurrentTurn(), DurationType.START_OF_NEXT_TURN, player);
				player.setTemporaryFortitudeBonus(2, player.getCurrentTurn(), DurationType.START_OF_NEXT_TURN, player);
			} else {
				Utils.print("You have already used your second wind in this encounter.  I know it would have been nice if I mentioned that already.  Sorry!");
			}
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
		return null; // This power is automatically inserted, so it doesn't need the classes that can use power method to be implemented.
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
