package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.powers.InspiringWord;
import com.jimmie.util.Utils;

public class Warlord extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CommandingPresence commandingPresence;

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
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Athletics");
		choices.add("Diplomacy");
		choices.add("Endurance");
		choices.add("Heal");
		choices.add("History");
		choices.add("Intimidate");
		
		Utils.print("Choose 4 of the following");
		for (int i = 0; i < 4; i++) {
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
		Utils.print("Setting role to Leader.");
		pc.setRole(Role.LEADER);
		
		Utils.print("Setting power Source to Martial.");
		pc.setPowerSource(PowerSource.MARTIAL);

		Utils.print("Adding Armor Proficiencies: Cloth, Leather, Hide, Chainmail, Light Shield");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		pc.addArmorGroupProficiency(ArmorGroup.LEATHER);
		pc.addArmorGroupProficiency(ArmorGroup.HIDE);
		pc.addArmorGroupProficiency(ArmorGroup.CHAINMAIL);
		pc.addArmorGroupProficiency(ArmorGroup.LIGHT_SHIELD);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Military Melee, Simple Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.MILITARY_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);
		
		Utils.print("Adding bonus of +1 Fortitude, +1 Will");
		setFortitudeBonus(getFortitudeBonus() + 1);
		setWillBonus(getWillBonus() + 1);
		
		Utils.print("Setting hit points per level gained = 5");
		pc.setHitPointsPerLevelGained(5);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Inspiring Warlord: You lead by exhortation, encouragement, and inspiration.");
		Utils.print("2. Tactical Warlord: Your leadership takes the form of quick commands, cunning strategies, and tactical superiority.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Strength your primary score and Charisma your secondary score.  Intelligence is third.");
			Utils.print("Suggested Feat: Inspired Recovery (Human feat: Toughness)");
			Utils.print("Suggested Skills: Athletics, Diplomacy, Heal, History");
			Utils.print("Suggested At-Will Powers: Commander's Strike, Furious Smash");
			Utils.print("Suggested Encounter Power: Guarding Attack");
			Utils.print("Suggested Daily Power: Bastion of Defense");
		} else {
			Utils.print("Make Strength your primary score and Intelligence your secondary score. Charisma is third.");
			Utils.print("Suggested Feat: Tactical Assault (Human feat: Weapon Focus)");
			Utils.print("Suggested Skills: Endurance, Heal, History, Intimidate");
			Utils.print("Suggested At-Will Powers: Viper's Strike, Wolf Pack Tactics");
			Utils.print("Suggested Encounter Power: Warlord's Favor");
			Utils.print("Suggested Daily Power: Lead the Attack");
		}
		
		Utils.print("Choose between the following Commanding Presence:");
		Utils.print("1. Inspiring Presence. When an ally spends an action point, thy regain hit points, too.");
		Utils.print("2. Tactical Presence. When an ally spends an action point, they get a bonus to the attack roll.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setCommandingPresence(CommandingPresence.INSPIRING_PRESENCE);
		} else {
			setCommandingPresence(CommandingPresence.TACTICAL_PRESENCE);
		}
		
		pc.addPower(new InspiringWord());
		
		// TODO: Combat Leader, Commanding Presence
		Utils.print("NOTE: I have not yet coded Combat Leader, Commanding Presence.");
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

	public CommandingPresence getCommandingPresence() {
		return commandingPresence;
	}

	public void setCommandingPresence(CommandingPresence commandingPresence) {
		this.commandingPresence = commandingPresence;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
