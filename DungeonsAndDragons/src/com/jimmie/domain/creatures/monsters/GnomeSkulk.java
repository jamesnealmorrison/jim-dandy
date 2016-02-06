package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.AmmunitionType;
import com.jimmie.domain.creatures.AmmunitionUser;
import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.GnomeSkulkHandCrossbow;
import com.jimmie.powers.GnomeSkulkWarPick;

public class GnomeSkulk extends Gnome implements AmmunitionUser {
	int crossbowBolts;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GnomeSkulk() {
		setSize(Size.SMALL);
		setInitiative(8);
		setMaxHitPoints(34);
		setCurrentHitPoints(34);
		setArmorClass(16);
		setFortitude(14);
		setReflex(14);
		setWill(12);
		setBaseSpeed(5);
		setStrength(8);
		setConstitution(16);
		setDexterity(17);
		setIntelligence(14);
		setWisdom(12);
		setCharisma(13);
		addPower(new GnomeSkulkWarPick());
		addPower(new GnomeSkulkHandCrossbow());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GnomeSkulk.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GnomeSkulkBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GnomeSkulkBattleCard.jpg");
		crossbowBolts = 20;
	}

	public int getStrengthModifier() {
		return 2;
	}

	public int getConstitutionModifier() {
		return 1;
	}

	public int getDexterityModifier() {
		return 0;
	}

	public int getIntelligenceModifier() {
		return -1;
	}
	
	public int getWisdomModifier() {
		return 0;
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
		return crossbowBolts;
	}
	
}
