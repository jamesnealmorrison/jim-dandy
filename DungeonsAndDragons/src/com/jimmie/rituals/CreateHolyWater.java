package com.jimmie.rituals;

import java.util.List;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.Time;
import com.jimmie.domain.TimeType;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;
import com.jimmie.util.Utils;

public class CreateHolyWater extends Ritual {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Create Holy Water";
	}

	@Override
	public int getLevel() {
		return 1;
	}

	@Override
	public RitualCategory getCategory() {
		return RitualCategory.CREATION;
	}

	@Override
	public Time getTime() {
		return new Time(1, TimeType.HOURS);
	}

	@Override
	public Time getDurationTime() {
		return new Time(24, TimeType.HOURS);
	}

	@Override
	public SkillType getKeySkill() {
		return SkillType.RELIGION;
	}

	@Override
	public Price getComponentCostPrice() {
		return new Price(1, CoinType.SPECIAL);
	}

	@Override
	public Price getMarketPrice() {
		return new Price(50, CoinType.GOLD_PIECE);
	}

	@Override
	public void cast(PlayerCharacter user) {
		Utils.print("Sorry, but I haven't implemented this Ritual yet.");
	}

	@Override
	public boolean meetsPrerequisitesToChooseRitual(PlayerCharacter user) {
		return true;
	}

	@Override
	public boolean meetsRequirementsToCastRitual(PlayerCharacter user) {
		return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getClassesThatCanCastRitual() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getRacesThatCanCastRitual() {
		return null;
	}


}
