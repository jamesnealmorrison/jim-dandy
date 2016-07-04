package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.HumanRabbleClub;

public class HumanRabble extends Human {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public HumanRabble() {
		setSize(Size.MEDIUM);
		setInitiative(0);
		setMaxHitPoints(1);
		setCurrentHitPoints(1);
		setArmorClass(15);
		setFortitude(13);
		setReflex(11);
		setWill(11);
		setBaseSpeed(6);
		setStrength(14);
		setConstitution(12);
		setDexterity(10);
		setIntelligence(9);
		setWisdom(10);
		setCharisma(11);
		addPower(new HumanRabbleClub());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\HumanRabble.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\HumanRabbleBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\HumanRabbleBattleCard.jpg");
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
	
}
