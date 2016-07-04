package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.GuardDrakeBite;

public class GuardDrake extends Drake {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GuardDrake() {
		setSize(Size.SMALL);
		setInitiative(3);
		setMaxHitPoints(48);
		setCurrentHitPoints(48);
		setArmorClass(15);
		setFortitude(15);
		setReflex(13);
		setWill(12);
		setBaseSpeed(6);
		setStrength(16);
		setConstitution(18);
		setDexterity(15);
		setIntelligence(3);
		setWisdom(12);
		setCharisma(12);
		addPower(new GuardDrakeBite());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GuardDrake.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GuardDrakeBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\GuardDrakeBattleCard.jpg");
	}

	public int getStrengthModifier() {
		return -1;
	}

	public int getConstitutionModifier() {
		return 3;
	}

	public int getDexterityModifier() {
		return 1;
	}

	public int getIntelligenceModifier() {
		return 5;
	}
	
	public int getWisdomModifier() {
		return 1;
	}
	
	public int getCharismaModifier() {
		return 3;
	}

}
