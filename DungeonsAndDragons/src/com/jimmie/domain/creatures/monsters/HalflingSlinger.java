package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.AmmunitionType;
import com.jimmie.domain.creatures.AmmunitionUser;
import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.HalflingSlingerDagger;
import com.jimmie.powers.HalflingSlingerSling;
import com.jimmie.powers.HalflingSlingerStoneRain;
import com.jimmie.util.Utils;

public class HalflingSlinger extends Halfling implements AmmunitionUser {
	private int bullets;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HalflingSlinger() {
		setSize(Size.SMALL);
		setInitiative(4);
		setMaxHitPoints(22);
		setCurrentHitPoints(22);
		setArmorClass(15);
		setFortitude(12);
		setReflex(15);
		setWill(13);
		setBaseSpeed(6);
		// TODO: +5 against fear effects...setTemporaryEffect(5, 0, DurationType.SPECIAL, this, TemporaryEffectType.S, reason);
		setStrength(12);
		setConstitution(10);
		setDexterity(18);
		setIntelligence(10);
		setWisdom(11);
		setCharisma(14);
		addPower(new HalflingSlingerDagger());
		addPower(new HalflingSlingerSling());
		addPower(new HalflingSlingerStoneRain());
		bullets = 20;
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\HalflingSlinger.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\HalflingSlingerBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\HalflingSlingerBattleCard.jpg");
	}

	public int getStrengthModifier() {
		return 1;
	}

	public int getConstitutionModifier() {
		return 0;
	}

	public int getDexterityModifier() {
		return 4;
	}

	public int getIntelligenceModifier() {
		return 0;
	}
	
	public int getWisdomModifier() {
		return 0;
	}
	
	public int getCharismaModifier() {
		return 2;
	}

	@Override
	public void expendRounds(int count, AmmunitionType ammunitionType) {
		// Since bullets are all I have, then I'm not going to look at ammunitionType.
		bullets -= count;
		if (bullets < 0) {
			Utils.print("Somehow " + getName() + " used more ammunition than they have.");
			bullets = 0;
		}
	}

	@Override
	public int roundsLeft(AmmunitionType ammunitionType) {
		return bullets;
	}
}
