package com.jimmie.domain.creatures.monsters;


import com.jimmie.powers.KoboldDragonshieldShortSword;
import com.jimmie.powers.KoboldShifty;
import com.jimmie.util.Utils;

public class KoboldDragonshield extends Kobold {
	private boolean usedDragonshieldTactics;

	@Override
	public void startOfTurn() {
		super.startOfTurn();
		Utils.print("In KoboldDragonshield.startOfTurn");
		usedDragonshieldTactics = false;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public KoboldDragonshield() {
		setInitiative(4);
		setMaxHitPoints(36);
		setCurrentHitPoints(36);
		setArmorClass(18);
		setFortitude(14);
		setReflex(13);
		setWill(13);
		setBaseSpeed(6);
		setStrength(14);
		setConstitution(12);
		setDexterity(13);
		setIntelligence(9);
		setWisdom(12);
		setCharisma(10);
		addPower(new KoboldDragonshieldShortSword());
		addPower(new KoboldShifty());
		usedDragonshieldTactics = false;
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\KoboldDragonshield.JPG");
	}

	public boolean isUsedDragonshieldTactics() {
		return usedDragonshieldTactics;
	}

	public void setUsedDragonshieldTactics(boolean usedDragonshieldTactics) {
		this.usedDragonshieldTactics = usedDragonshieldTactics;
	}

	public int getStrengthModifier() {
		return 3;
	}

	public int getConstitutionModifier() {
		return 2;
	}

	public int getDexterityModifier() {
		return 2;
	}

	public int getIntelligenceModifier() {
		return 0;
	}
	
	public int getWisdomModifier() {
		return 2;
	}
	
	public int getCharismaModifier() {
		return 1;
	}

	public void useDragonshieldTactics() {
		Utils.print(getName() + " can use dragonshield tactics to shift 1 square.  Do you want to?");
		String choice = Utils.getYesOrNoInput();
		if ("Y".equalsIgnoreCase(choice)) {
			shift(1, true);
			usedDragonshieldTactics = true;
		}
		
	}
}
