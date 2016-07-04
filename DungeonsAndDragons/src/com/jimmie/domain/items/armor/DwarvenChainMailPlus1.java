package com.jimmie.domain.items.armor;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.items.CoinType;
import com.jimmie.domain.items.Price;
import com.jimmie.powers.DwarvenArmorHealing;
import com.jimmie.powers.Power;

// TODO: Endurance bonus.
public class DwarvenChainMailPlus1 extends Armor {

	private DwarvenArmorHealing dwarvenArmorHealing = new DwarvenArmorHealing();

	@Override
	public List<Power> getPowers() {
		List<Power> powers = new ArrayList<Power>();
		if (super.getPowers() != null) {
			powers.addAll(super.getPowers());
		}
		powers.add(dwarvenArmorHealing);
		return powers;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getBonus() {
		return 7;
	}

	@Override
	public boolean isLightArmor() {
		return false;
	}

	@Override
	public int getSkillPenalty() {
		return -1;
	}

	@Override
	public int getMinimumEnhancementBonus() {
		return 0;
	}

	@Override
	public int getSpeedPenalty() {
		return -1;
	}

	@Override
	public int getWeight() {
		return 40;
	}

	@Override
	public ArmorType getArmorType() {
		return ArmorType.CHAINMAIL;
	}

	@Override
	public ArmorGroup getArmorGroup() {
		return ArmorGroup.CHAINMAIL;
	}

	@Override
	public Price getPrice() {
		return new Price(520, CoinType.GOLD_PIECE);
	}

}
