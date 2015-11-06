package com.jimmie.domain.creatures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jimmie.domain.EpicDestiny;
import com.jimmie.domain.MagicItem;
import com.jimmie.domain.ParagonPath;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.feats.Feat;
import com.jimmie.domain.feats.FeatType;
import com.jimmie.util.FeatMaster;
import com.jimmie.util.Utils;

public class PlayerCharacter extends Character implements Serializable {
	private static final long serialVersionUID = 1L;
	private ParagonPath paragonPath;
	private EpicDestiny epicDestiny;
	private int experiencePoints;
	private String playerName;
	private List<Feat> feats;
	private List<MagicItem> magicItems;
	public int getHitPointsPerLevelGained() {
		return hitPointsPerLevelGained;
	}

	public void setHitPointsPerLevelGained(int hitPointsPerLevelGained) {
		this.hitPointsPerLevelGained = hitPointsPerLevelGained;
	}

	private PowerSource powerSource;
	private int hitPointsPerLevelGained = 0;

	public PowerSource getPowerSource() {
		return powerSource;
	}

	public void setPowerSource(PowerSource powerSource) {
		this.powerSource = powerSource;
	}

	public PlayerCharacter(Race r, DndClass c) {
		race = r;
		dndClass = c;
	}

	public ParagonPath getParagonPath() {
		return paragonPath;
	}
	public void setParagonPath(ParagonPath paragonPath) {
		this.paragonPath = paragonPath;
	}
	public EpicDestiny getEpicDestiny() {
		return epicDestiny;
	}
	public void setEpicDestiny(EpicDestiny epicDestiny) {
		this.epicDestiny = epicDestiny;
	}
	public int getExperiencePoints() {
		return experiencePoints;
	}
	public void setExperiencePoints(int experiencePoints) {
		this.experiencePoints = experiencePoints;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public List<Feat> getFeats() {
		return feats;
	}
	public void setFeats(List<Feat> feats) {
		this.feats = feats;
	}
	public List<MagicItem> getMagicItems() {
		return magicItems;
	}
	public void setMagicItems(List<MagicItem> magicItems) {
		this.magicItems = magicItems;
	}

	/*
	 * Use this method to learn a new feat.
	 */
	public void selectFeat() {
		HashMap<Integer, Feat> choices = new HashMap<Integer, Feat>();

		List<Feat> allFeats = FeatMaster.getFullListOfFeats();
		
		/* Add allFeats to the HashMap of choices, based on if they qualify or not. */
		// TODO: Add a method to Feat to see if they already have this particular feat, and if they can choose multiple times. */
		int i = 1;
		Utils.print("Please select one of the following Feats:");
		for (Feat feat : allFeats) {
			if (feat.meetsPrerequisites(this)) {
				choices.put(i, feat);
				Utils.print(i + ". " + feat.getName());
				Utils.print("     " + feat.getBenefit());
				i++;
			}
		}
		
		Utils.print("Your choice:");
		List<Integer> validChoices = new ArrayList<Integer>();
		validChoices.addAll(choices.keySet());
		int choice = Utils.getValidIntInput(validChoices);
		Feat chosenFeat = choices.get(choice);
		chosenFeat.makeFeatChoices(this);

		// TODO: Add chosenFeat to the playercharacter.
	}

	public boolean hasFeat(FeatType featType) {
		for (Feat feat : feats) {
			if (feat.getType() == featType) {
				return true;
			}
		}
		return false;
	}
}
