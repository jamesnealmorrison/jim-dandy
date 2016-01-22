package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.jimmie.domain.AbilityType;
import com.jimmie.domain.ImplementType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.powers.WildShape;
import com.jimmie.rituals.AnimalMessenger;
import com.jimmie.rituals.Ritual;
import com.jimmie.util.RitualMaster;
import com.jimmie.util.Utils;

public class Druid extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrimalAspect primalAspect;
	private boolean inBeastForm = false;
	private String beastFormImagePath;
	private String beastFormBloodiedImagePath;
	
	@Override
	public void initializeForEncounter() {
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeForNewDay(DndCharacter dndCharacter) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Add automatic trained skill(s).
		trainedSkills.add("Nature");
		Utils.print("Automatically trained in Nature.");
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Arcana");
		choices.add("Athletics");
		choices.add("Diplomacy");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("History");
		choices.add("Insight");
		choices.add("Perception");
		
		Utils.print("Choose 3 of the following");
		for (int i = 0; i < 3; i++) {
			Utils.printValidStringChoices(choices);
			Utils.print("Your choice:");
			String choice = Utils.getValidInput(choices);
			trainedSkills.add(choice);
			choices.remove(choice);
		}
		
		return trainedSkills;
	}

	@Override
	public void makeClassChoicesBeforeAbilityScores(PlayerCharacter pc) {
		Utils.print("Setting role to Controller.");
		pc.setRole(Role.CONTROLLER);
		
		Utils.print("Setting power Source to Primal.");
		pc.setPowerSource(PowerSource.PRIMAL);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Simple Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		
		Utils.print("Adding Implement Proficiencies: Staff, Totem");
		pc.addImplementProficiency(ImplementType.STAFF);
		pc.addImplementProficiency(ImplementType.TOTEM);

		Utils.print("Adding bonus of +1 Reflex, +1 Will");
		setReflexBonus(getReflexBonus() + 1);
		setWillBonus(getWillBonus() + 1);

		Utils.print("Setting hit points per level gained = 5");
		pc.setHitPointsPerLevelGained(5);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Guardian Druid: You are a protector of the land and those who rely on it.");
		Utils.print("2. Predator Druid: You hunt down and destroy those who despoil the natural world.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Wisdom your primary score and Constitution your secondary score.");
			Utils.print("Suggested Class Feature: Primal Guardian");
			Utils.print("Suggested Feat: Primal Instinct");
			Utils.print("Suggested Skills: Arcana, Heal, Insight, Nature");
			Utils.print("Suggested At-Will Powers: Call of the Beast, Chill Wind, Grasping Claws");
			Utils.print("Suggested Encounter Power: Frost Flash");
			Utils.print("Suggested Daily Power: Fires of Life");
		} else {
			Utils.print("Make Wisdom your primary score and Deterity your secondary score.");
			Utils.print("Suggested Class Feature: Primal Predator");
			Utils.print("Suggested Feat: Primal Fury");
			Utils.print("Suggested Skills: Arcana, Athletics, Nature, Perception");
			Utils.print("Suggested At-Will Powers: Flame Seed, Pounce, Savage Rend");
			Utils.print("Suggested Encounter Power: Darting Bite");
			Utils.print("Suggested Daily Power: Savage Frenzy");
		}
		
		Utils.print("Choose between the following Primal Aspects:");
		Utils.print("1. Primal Guardian.");
		Utils.print("2. Primal Predator.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setPrimalAspect(PrimalAspect.PRIMAL_GUARDIAN);
		} else {
			setPrimalAspect(PrimalAspect.PRIMAL_PREDATOR);
		}
		
		pc.addPower(new WildShape());

		Utils.print("As a Druid, you have Ritual Casting.  Make sure you purchase a Ritual book.  They are 50gp, so you might want to purchase it before making your other purchases.");
		Utils.print("Adding Animal Messenger ritual.");
		pc.addRitual(new AnimalMessenger());

		// Choose one more ritual
		List<Ritual> allRituals = RitualMaster.getFullListOfRituals();

		int i = 0;		
		HashMap<Integer, Ritual> choices = new HashMap<Integer, Ritual>();
		Utils.print("Choose another Ritual:");
		for (Ritual ritual : allRituals) {
			if (ritual.canBeSelected(pc)) {
				i++;
				choices.put(i, ritual);
				Utils.print(i + ". " + ritual.getName());
			}
		}
			
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, i);
		Ritual ritual = choices.get(choice);
		pc.addRitual(ritual);
		
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 12 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 7 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}

	public PrimalAspect getPrimalAspect() {
		return primalAspect;
	}

	public void setPrimalAspect(PrimalAspect primalAspect) {
		this.primalAspect = primalAspect;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
	public boolean isInBeastForm() {
		return inBeastForm;
	}
	public void setInBeastForm(boolean inBeastForm) {
		this.inBeastForm = inBeastForm;
	}

	public String getBeastFormImagePath() {
		return beastFormImagePath;
	}

	public void setBeastFormImagePath(String beastFormImagePath) {
		this.beastFormImagePath = beastFormImagePath;
	}

	public String getBeastFormBloodiedImagePath() {
		return beastFormBloodiedImagePath;
	}

	public void setBeastFormBloodiedImagePath(String beastFormBloodiedImagePath) {
		this.beastFormBloodiedImagePath = beastFormBloodiedImagePath;
	}

}
