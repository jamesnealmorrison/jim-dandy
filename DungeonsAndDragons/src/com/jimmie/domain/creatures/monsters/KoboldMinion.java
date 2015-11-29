package com.jimmie.domain.creatures.monsters;

import com.jimmie.powers.KoboldMinionJavelin;
import com.jimmie.powers.KoboldShifty;

public class KoboldMinion extends Kobold {
	@Override
	public void startOfTurn() {
		super.startOfTurn();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public KoboldMinion() {
		setInitiative(3);
		setMaxHitPoints(1);
		setCurrentHitPoints(1);
		setArmorClass(15);
		setFortitude(11);
		setReflex(13);
		setWill(11);
		setBaseSpeed(6);
		setStrength(8);
		setConstitution(12);
		setDexterity(16);
		setIntelligence(9);
		setWisdom(12);
		setCharisma(10);
		addPower(new KoboldMinionJavelin());
		addPower(new KoboldShifty());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\KoboldMinion.JPG");
	}

	public int getStrengthModifier() {
		return -1;
	}

	public int getConstitutionModifier() {
		return 1;
	}

	public int getDexterityModifier() {
		return 3;
	}

	public int getIntelligenceModifier() {
		return -1;
	}
	
	public int getWisdomModifier() {
		return 1;
	}
	
	public int getCharismaModifier() {
		return 0;
	}
}
