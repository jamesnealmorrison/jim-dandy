package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.DamageType;
import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.ZombieGrab;
import com.jimmie.powers.ZombieSlam;

public class Zombie extends AbstractZombie {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Zombie() {
		setSize(Size.MEDIUM);
		setInitiative(-1);
		setMaxHitPoints(40);
		setCurrentHitPoints(40);
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
		addPower(new ZombieSlam());
		addPower(new ZombieGrab());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\Zombie.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\ZombieBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\ZombieBattleCard.jpg");
		addDamageResistance(DamageType.NECROTIC, 10);
		addDamageVulnerability(DamageType.RADIANT, 5);
		addSense(new Sense(SenseType.DARKVISION));
		// TODO Immunity disease/poison
	}

	public int getStrengthModifier() {
		return 3;
	}

	public int getConstitutionModifier() {
		return 1;
	}

	public int getDexterityModifier() {
		return -1;
	}

	public int getIntelligenceModifier() {
		return -4;
	}
	
	public int getWisdomModifier() {
		return 0;
	}
	
	public int getCharismaModifier() {
		return -3;
	}
	
}
