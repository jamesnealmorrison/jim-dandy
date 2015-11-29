package com.jimmie.domain.creatures.monsters;

import com.jimmie.powers.KoboldShifty;
import com.jimmie.powers.KoboldSlingerDagger;
import com.jimmie.powers.KoboldSlingerSling;
import com.jimmie.util.Utils;

public class KoboldSlinger extends Kobold {
	@Override
	public void startOfTurn() {
		super.startOfTurn();
		Utils.print("In KoboldSlinger.startOfTurn");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public KoboldSlinger() {
		setInitiative(3);
		setMaxHitPoints(24);
		setCurrentHitPoints(24);
		setArmorClass(13);
		setFortitude(12);
		setReflex(14);
		setWill(12);
		setBaseSpeed(6);
		setStrength(9);
		setConstitution(12);
		setDexterity(17);
		setIntelligence(9);
		setWisdom(12);
		setCharisma(10);
		addPower(new KoboldSlingerDagger());
		addPower(new KoboldSlingerSling());
		addPower(new KoboldShifty());
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\KoboldSlinger.JPG");
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
