package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.AmmunitionType;
import com.jimmie.domain.creatures.AmmunitionUser;
import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.GoblinSharpshooterHandCrossbow;
import com.jimmie.powers.GoblinSharpshooterShortSword;

public class GoblinSharpshooter extends Goblin implements AmmunitionUser {
	// TODO: Sniper
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int crossbowBolts;

	public GoblinSharpshooter() {
		setSize(Size.SMALL);
		setInitiative(5);
		setMaxHitPoints(31);
		setCurrentHitPoints(31);
		setArmorClass(16);
		setFortitude(12);
		setReflex(14);
		setWill(11);
		setBaseSpeed(6);
		setStrength(14);
		setConstitution(13);
		setDexterity(18);
		setIntelligence(8);
		setWisdom(13);
		setCharisma(8);
		addPower(new GoblinSharpshooterShortSword());
		addPower(new GoblinSharpshooterHandCrossbow());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GoblinSharpshooter.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GoblinSharpshooterBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GoblinSharpshooterBattleCard.jpg");
		crossbowBolts = 20;
	}

	public int getStrengthModifier() {
		return 3;
	}

	public int getConstitutionModifier() {
		return 2;
	}

	public int getDexterityModifier() {
		return 5;
	}

	public int getIntelligenceModifier() {
		return 0;
	}
	
	public int getWisdomModifier() {
		return 2;
	}
	
	public int getCharismaModifier() {
		return 0;
	}

	@Override
	public void expendRounds(int count, AmmunitionType ammunitionType) {
		crossbowBolts -= count;
	}

	@Override
	public int roundsLeft(AmmunitionType ammunitionType) {
		if (ammunitionType == AmmunitionType.CROSSBOW_BOLTS) {
			return crossbowBolts;
		}
		return 0;
	}
}
