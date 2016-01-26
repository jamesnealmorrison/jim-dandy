package com.jimmie.domain.creatures.monsters;

import com.jimmie.domain.creatures.Size;
import com.jimmie.powers.KoboldShifty;
import com.jimmie.powers.KoboldSlingerDagger;
import com.jimmie.powers.KoboldSlingerSling;

public class KoboldSlinger extends Kobold {
	private int normalShot;
	private int firepots;
	private int gluepots;
	private int stinkpots;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public KoboldSlinger() {
		setSize(Size.SMALL);
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
		setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\KoboldSlinger.jpg");
		setBloodiedImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\KoboldSlingerBloodied.jpg");
		setBattleCardImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\DungeonTiles\\Monsters\\KoboldSlingerBattleCard.jpg");
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

	public void initializeForEncounter(int normalShot, int firepots, int gluepots, int stinkpots) {
		this.normalShot = normalShot;
		this.firepots = firepots;
		this.gluepots = gluepots;
		this.stinkpots = stinkpots;
	}

	public int getNormalShot() {
		return normalShot;
	}

	public void setNormalShot(int normalShot) {
		this.normalShot = normalShot;
	}

	public int getFirepots() {
		return firepots;
	}

	public void setFirepots(int firepots) {
		this.firepots = firepots;
	}

	public int getGluepots() {
		return gluepots;
	}

	public void setGluepots(int gluepots) {
		this.gluepots = gluepots;
	}

	public int getStinkpots() {
		return stinkpots;
	}

	public void setStinkpots(int stinkpots) {
		this.stinkpots = stinkpots;
	}
}
