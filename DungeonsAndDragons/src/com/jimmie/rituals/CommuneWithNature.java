package com.jimmie.rituals;

import java.util.List;

import com.jimmie.domain.SkillType;
import com.jimmie.domain.Time;
import com.jimmie.domain.TimeType;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;
import com.jimmie.util.Utils;

public class CommuneWithNature extends Ritual {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Commune With Nature";
	}

	@Override
	public int getLevel() {
		return 6;
	}

	@Override
	public RitualCategory getCategory() {
		return RitualCategory.DIVINATION;
	}

	@Override
	public Time getTime() {
		return new Time(30, TimeType.MINUTES);
	}

	@Override
	public Time getDurationTime() {
		return new Time(10, TimeType.MINUTES);
	}

	@Override
	public SkillType getKeySkill() {
		return SkillType.NATURE;
	}

	@Override
	public Price getComponentCostPrice() {
		return new Price(140, CoinType.GOLD_PIECE);
	}

	@Override
	public Price getMarketPrice() {
		return new Price(360, CoinType.GOLD_PIECE);
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
