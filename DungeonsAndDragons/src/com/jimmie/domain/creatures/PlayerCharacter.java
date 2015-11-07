package com.jimmie.domain.creatures;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jimmie.domain.EpicDestiny;
import com.jimmie.domain.MagicItem;
import com.jimmie.domain.ParagonPath;
import com.jimmie.domain.PowerId;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.feats.Feat;
import com.jimmie.domain.feats.FeatType;
import com.jimmie.util.AtWillPower;
import com.jimmie.util.DailyPower;
import com.jimmie.util.EncounterPower;
import com.jimmie.util.FeatMaster;
import com.jimmie.util.FreeAction;
import com.jimmie.util.MinorAction;
import com.jimmie.util.StandardAction;
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

		// TODO: Just add the feat.  Worry about choosing the same feat multiple times later.
		addFeat(chosenFeat);
	}

	private void addFeat(Feat chosenFeat) {
		if (feats == null) {
			feats = new ArrayList<Feat>();
		}
		feats.add(chosenFeat);
	}

	public boolean hasFeat(FeatType featType) {
		if (feats != null) {
			for (Feat feat : feats) {
				if (feat.getType() == featType) {
					return true;
				}
			}
		}
		return false;
	}

	public void choosePowers() {
		if (dndClass != null) {
			// Choose two At Will powers 
			HashMap<Integer, PowerId> choices = new HashMap<Integer, PowerId>();
			int i = 1;
			Utils.print("Choose two At Will Powers:");
			for (Method method : dndClass.getClass().getMethods()) {
				if (method.isAnnotationPresent(AtWillPower.class)) {
					PowerId powerId = null;
					// Grab the power id.  This could come from Standard, Free, Minor annotation
					if (method.isAnnotationPresent(StandardAction.class)) {
						powerId = method.getAnnotation(StandardAction.class).powerId();
					} else if (method.isAnnotationPresent(FreeAction.class)) {
						powerId = method.getAnnotation(FreeAction.class).powerId();
					} else if (method.isAnnotationPresent(MinorAction.class)) {
						powerId = method.getAnnotation(MinorAction.class).powerId();
					}
					choices.put(i, powerId);
					Utils.print(i + ". " + powerId);
					i++;
				}
			}
			
			if (i < 2) {
				Utils.print("There are less than two At Will powers to choose from.  Something went wrong.");
			} else {
				for (int choiceIndex = 0; choiceIndex < 2; choiceIndex++) {
					Utils.print("Your choice: (Note: I don't have anything to prevent you from choosing the same thing twice.  Just don't do it, please.)");
					int choice = Utils.getValidIntInputInRange(1, i);
					PowerId powerId = choices.get(choice);
					addPower(powerId);
				}
			}

			// Choose one encounter power 
			choices = new HashMap<Integer, PowerId>();
			i = 1;
			Utils.print("Choose an Encounter Power:");
			for (Method method : dndClass.getClass().getMethods()) {
				if (method.isAnnotationPresent(EncounterPower.class)) {
					PowerId powerId = null;
					// Grab the power id.  This could come from Standard, Free, Minor annotation
					if (method.isAnnotationPresent(StandardAction.class)) {
						powerId = method.getAnnotation(StandardAction.class).powerId();
					} else if (method.isAnnotationPresent(FreeAction.class)) {
						powerId = method.getAnnotation(FreeAction.class).powerId();
					} else if (method.isAnnotationPresent(MinorAction.class)) {
						powerId = method.getAnnotation(MinorAction.class).powerId();
					}
					choices.put(i, powerId);
					Utils.print(i + ". " + powerId);
					i++;
				}
			}
			
			if (i < 1) {
				Utils.print("There are no encounter powers to choose from.  Something went wrong.");
			} else {
				Utils.print("Your choice:");
				int choice = Utils.getValidIntInputInRange(1, i);
				PowerId powerId = choices.get(choice);
				addPower(powerId);
			}

			// Choose one daily power 
			choices = new HashMap<Integer, PowerId>();
			i = 1;
			Utils.print("Choose a Daily Power:");
			for (Method method : dndClass.getClass().getMethods()) {
				if (method.isAnnotationPresent(DailyPower.class)) {
					PowerId powerId = null;
					// Grab the power id.  This could come from Standard, Free, Minor annotation
					if (method.isAnnotationPresent(StandardAction.class)) {
						powerId = method.getAnnotation(StandardAction.class).powerId();
					} else if (method.isAnnotationPresent(FreeAction.class)) {
						powerId = method.getAnnotation(FreeAction.class).powerId();
					} else if (method.isAnnotationPresent(MinorAction.class)) {
						powerId = method.getAnnotation(MinorAction.class).powerId();
					}
					choices.put(i, powerId);
					Utils.print(i + ". " + powerId);
					i++;
				}
			}
			
			if (i < 1) {
				Utils.print("There are no daily powers to choose from.  Something went wrong.");
			} else {
				Utils.print("Your choice:");
				int choice = Utils.getValidIntInputInRange(1, i);
				PowerId powerId = choices.get(choice);
				addPower(powerId);
			}
		}
	}
}
