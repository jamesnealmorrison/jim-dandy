package com.jimmie.domain.creatures.monsters;

import com.jimmie.powers.KoboldShifty;
import com.jimmie.powers.KoboldSkirmisherSpear;

public class KoboldSkirmisher extends Kobold {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public KoboldSkirmisher() {
		setInitiative(5);
		setMaxHitPoints(27);
		setCurrentHitPoints(27);
		setArmorClass(15);
		setFortitude(11);
		setReflex(14);
		setWill(13);
		setBaseSpeed(6);
		setStrength(8);
		setConstitution(11);
		setDexterity(16);
		setIntelligence(6);
		setWisdom(10);
		setCharisma(15);
		addPower(new KoboldSkirmisherSpear());
		addPower(new KoboldShifty());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\KoboldSkirmisher.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\KoboldSkirmisherBloodied.jpg");
	}
	
	public int getStrengthModifier() {
		return -1;
	}

	public int getConstitutionModifier() {
		return 0;
	}

	public int getDexterityModifier() {
		return 3;
	}

	public int getIntelligenceModifier() {
		return -2;
	}
	
	public int getWisdomModifier() {
		return 0;
	}
	
	public int getCharismaModifier() {
		return 2;
	}
}
