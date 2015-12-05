package com.jimmie.domain.creatures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.EpicDestiny;
import com.jimmie.domain.MagicItem;
import com.jimmie.domain.ParagonPath;
import com.jimmie.domain.PowerUsage;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.feats.Feat;
import com.jimmie.domain.feats.FeatType;
import com.jimmie.domain.items.armor.ClothArmor;
import com.jimmie.powers.Power;
import com.jimmie.util.FeatMaster;
import com.jimmie.util.PowerMaster;
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
		// The book says that all player characters start out with cloth armor (basic clothing).
		addArmor(new ClothArmor());
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
		List<Power> allPowers = PowerMaster.getFullListOfPowers();

		
		if (dndClass != null) {
			// Choose two At Will powers 
			HashMap<Integer, Power> choices = new HashMap<Integer, Power>();
			int i = 0;
			Utils.print("Choose two At Will Powers:");
			for (Power power : allPowers) {
				if ((power.getPowerUsage() == PowerUsage.AT_WILL) && (power.canBeSelected(this))) {
					i++;
					choices.put(i, power);
					Utils.print(i + ". " + power.getName());
				}
			}
			
			if (i < 2) {
				Utils.print("There are less than two At Will powers to choose from.  Something went wrong.");
			} else {
				for (int choiceIndex = 0; choiceIndex < 2; choiceIndex++) {
					Utils.print("Your choice: (Note: I don't have anything to prevent you from choosing the same thing twice.  Just don't do it, please.)");
					int choice = Utils.getValidIntInputInRange(1, i);
					Power power = choices.get(choice);
					addPower(power);
				}
			}

			// Choose one encounter power 
			choices = new HashMap<Integer, Power>();
			i = 0;
			Utils.print("Choose an Encounter Power:");
			for (Power power : allPowers) {
				if (((power.getPowerUsage() == PowerUsage.ENCOUNTER) || (power.getPowerUsage() == PowerUsage.ENCOUNTER_SPECIAL)) && (power.canBeSelected(this))) {
					i++;
					choices.put(i, power);
					Utils.print(i + ". " + power.getName());
				}
			}
			
			if (i < 1) {
				Utils.print("There are no encounter powers to choose from.");
			} else {
				Utils.print("Your choice:");
				int choice = Utils.getValidIntInputInRange(1, i);
				Power power = choices.get(choice);
				addPower(power);
			}

			// Choose one daily power 
			choices = new HashMap<Integer, Power>();
			i = 0;
			Utils.print("Choose a Daily Power:");
			for (Power power : allPowers) {
				if (((power.getPowerUsage() == PowerUsage.DAILY) || (power.getPowerUsage() == PowerUsage.DAILY_SPECIAL)) && (power.canBeSelected(this))) {
					i++;
					choices.put(i, power);
					Utils.print(i + ". " + power.getName());
				}
			}
			
			if (i < 1) {
				Utils.print("There are no daily powers to choose from.  Something went wrong.");
			} else {
				Utils.print("Your choice:");
				int choice = Utils.getValidIntInputInRange(1, i);
				Power power = choices.get(choice);
				addPower(power);
			}
		}
	}

	public int getFeatArmorClassBonus() {
		int base = 0;
		for (Feat feat : feats) {
			base = base + feat.getArmorClassBonus();
		}
		return 0;
	}

	@Override
	public int getBaseArmorClass() {
		return super.getBaseArmorClass() + getFeatArmorClassBonus();
	}

	public int getFeatFortitudeBonus() {
		int base = 0;
		for (Feat feat : feats) {
			base = base + feat.getFortitudeBonus();
		}
		return 0;
	}

	public int getFortitude() {
		return (10+getLevel()/2 + getFortitudeAbilityModifier() + dndClass.getFortitudeBonus() + getFeatFortitudeBonus() + race.getFortitudeBonus() + getFortitudeMisc1() + getFortitudeMisc2());
		// TODO: Enhancement????
	}
	
	public int getFortitudeAbilityModifier() {
		return Math.max(getAbilityModifier(AbilityType.STRENGTH), getAbilityModifier(AbilityType.CONSTITUTION));
	}

	public int getReflex() {
		return (10+getLevel()/2 + getReflexAbilityModifier() + dndClass.getReflexBonus() + getFeatReflexBonus() + race.getReflexBonus() + getReflexMisc1() + getReflexMisc2());
		// TODO: Enhancement????
	}
	
	public int getReflexAbilityModifier() {
		return Math.max(getAbilityModifier(AbilityType.DEXTERITY), getAbilityModifier(AbilityType.INTELLIGENCE));
	}

	public int getFeatReflexBonus() {
		int base = 0;
		for (Feat feat : feats) {
			base = base + feat.getReflexBonus();
		}
		return 0;
	}

	public int getWill() {
		return (10+getLevel()/2 + getWillAbilityModifier() + dndClass.getWillBonus() + getFeatWillBonus() + race.getWillBonus() + getWillMisc1() + getWillMisc2());
		// TODO: Enhancement????
	}
	
	public int getWillAbilityModifier() {
		return Math.max(getAbilityModifier(AbilityType.WISDOM), getAbilityModifier(AbilityType.CHARISMA));
	}

	public int getFeatWillBonus() {
		int base = 0;
		for (Feat feat : feats) {
			base = base + feat.getWillBonus();
		}
		return 0;
	}

	@Override
	public int getStrength() {
		return super.getStrength() + race.getStrengthBonus();
	}

	@Override
	public int getConstitution() {
		return super.getConstitution() + race.getConstitutionBonus();
	}

	@Override
	public int getDexterity() {
		return super.getDexterity() + race.getDexterityBonus();
	}

	@Override
	public int getIntelligence() {
		return super.getIntelligence() + race.getIntelligenceBonus();
	}

	@Override
	public int getWisdom() {
		return super.getWisdom() + race.getWisdomBonus();
	}

	@Override
	public int getCharisma() {
		return super.getCharisma() + race.getCharismaBonus();
	}
	
	@Override
	public int getSpeed() {
		// TODO: Item and misc.
		return super.getSpeed() + this.getReadiedArmor().getSpeedPenalty();
	}
}
