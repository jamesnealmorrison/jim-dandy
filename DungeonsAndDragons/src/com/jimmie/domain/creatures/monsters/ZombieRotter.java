package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.ZombieRotterSlam;

public class ZombieRotter extends AbstractZombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ZombieRotter() {
		setSize(Size.MEDIUM);
		setInitiative(-2);
		setMaxHitPoints(1);
		setCurrentHitPoints(1);
		setArmorClass(13);
		setFortitude(13);
		setReflex(9);
		setWill(10);
		setBaseSpeed(4);
		setStrength(14);
		setConstitution(10);
		setDexterity(6);
		setIntelligence(1);
		setWisdom(8);
		setCharisma(3);
		addPower(new ZombieRotterSlam());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\ZombieRotter.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\ZombieRotterBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\ZombieRotterBattleCard.jpg");
		addSense(new Sense(SenseType.DARKVISION));
		// TODO Immunity disease/poison
	}

	public int getStrengthModifier() {
		return 2;
	}

	public int getConstitutionModifier() {
		return 0;
	}

	public int getDexterityModifier() {
		return -2;
	}

	public int getIntelligenceModifier() {
		return -5;
	}
	
	public int getWisdomModifier() {
		return -1;
	}
	
	public int getCharismaModifier() {
		return -4;
	}
}
