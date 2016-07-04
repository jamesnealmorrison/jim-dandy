package com.jimmie.domain.classes;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.AbilityType;
import com.jimmie.domain.ImplementType;
import com.jimmie.domain.creatures.DndCharacter;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.PowerSource;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.items.armor.ArmorGroup;
import com.jimmie.domain.items.weapons.WeaponCategory;
import com.jimmie.powers.Distract;
import com.jimmie.powers.FarHand;
import com.jimmie.powers.ForcefulPunch;
import com.jimmie.powers.SendThoughts;
import com.jimmie.util.Utils;

public class Psion extends DndClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int powerPoints;
	private PsionDiscipline disciplineFocus;

	@Override
	public void initializeForEncounter() {
		/* TODO: More power points at higher levels. */
		setPowerPoints(2);
	}

	@Override
	public void initializeForNewDay(DndCharacter dndCharacter) {
	}


	public int getPowerPoints() {
		return powerPoints;
	}

	public void setPowerPoints(int powerPoints) {
		this.powerPoints = powerPoints;
	}

	@Override
	public List<String> selectInitialSkills() {
		List<String> trainedSkills = new ArrayList<String>();
		
		// Now make selections.
		List<String> choices = new ArrayList<String>();
		choices.add("Arcana");
		choices.add("Bluff");
		choices.add("Diplomacy");
		choices.add("Dungeoneering");
		choices.add("History");
		choices.add("Insight");
		choices.add("Intimidate");
		choices.add("Perception");
		
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
		Utils.print("Setting role to Controller.");
		pc.setRole(Role.CONTROLLER);
		
		Utils.print("Setting power Source to Psionic.");
		pc.setPowerSource(PowerSource.PSIONIC);

		Utils.print("Adding Armor Proficiencies: Cloth");
		pc.addArmorGroupProficiency(ArmorGroup.CLOTH);
		
		Utils.print("Adding Weapon Proficiencies: Simple Melee, Simple Ranged");
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_MELEE);
		pc.addWeaponCategoryProficiency(WeaponCategory.SIMPLE_RANGED);

		Utils.print("Adding Implement Proficiencies: Orbs, Staffs");
		pc.addImplementProficiency(ImplementType.ORB);
		pc.addImplementProficiency(ImplementType.STAFF);
		
		Utils.print("Adding bonus of +2 Will");
		setWillBonus(getWillBonus() + 2);

		Utils.print("Setting hit points per level gained = 4");
		pc.setHitPointsPerLevelGained(4);
		
		Utils.print("Next, you will take a look at the suggested build options.  These are only suggestions.  You can ignore them completely.");
		Utils.print("Which build option would you like to see?");
		Utils.print("1. Telekinetic Psion: You prefer to use mental force on creatures and objects.");
		Utils.print("2. Thaneborn Barbarian: Telepathic Psion: You focus on influencing your enemies' minds.");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			Utils.print("Make Intelligence your primary score and Wisdom your secondary score.");
			Utils.print("Suggested Class Feature: Telekinesis Focus");
			Utils.print("Suggested Feat: Controlling Advantage");
			Utils.print("Suggested Skills: Arcana, Dungeoneering, Insight, Perception");
			Utils.print("Suggested At-Will Powers: Kinetic Trawl, Force Punch");
			Utils.print("Suggested Daily Power: Telekinetic Anchor");
		} else {
			Utils.print("Make Intelligence your primary score and Charisma your secondary score.");
			Utils.print("Suggested Class Feature: Telepathy Focus");
			Utils.print("Suggested Feat: Precise Mind");
			Utils.print("Suggested Skills: Arcana, Bluff, Diplomacy, Insight");
			Utils.print("Suggested At-Will Powers: Memory Hole, Mind Thrust");
			Utils.print("Suggested Daily Power: Mental Trauma");
		}
		
		Utils.print("Choose between the following DisciplineFocuses:");
		Utils.print("1. Telekinesis Focus.");
		Utils.print("2. Telepathy Focus.");
		Utils.print("Your choice:");
		choice = Utils.getValidIntInputInRange(1, 2);
		if (choice == 1) {
			setDisciplineFocus(PsionDiscipline.TELEKINESIS_FOCUS);
			pc.addPower(new FarHand());
			pc.addPower(new ForcefulPunch());
		} else {
			setDisciplineFocus(PsionDiscipline.TELEPATHY_FOCUS);
			pc.addPower(new Distract());
			pc.addPower(new SendThoughts());
		}
		
		// TODO: Psionic Augmentation, Ritual Casting
		Utils.print("NOTE: I have not yet coded Psionic Augmentation, Ritual Casting.");
	}

	@Override
	public void makeClassChoicesAfterAbilityScores(PlayerCharacter pc) {
		int hp = 12 + pc.getConstitution();
		Utils.print("Setting hit points to " + hp);
		pc.setMaxHitPoints(hp);
		pc.setCurrentHitPoints(hp);

		int healingSurgesPerDay = 6 + pc.getAbilityModifier(AbilityType.CONSTITUTION);
		Utils.print("Setting healing surges per day = " + healingSurgesPerDay);
		pc.setHealingSurgesPerDay(healingSurgesPerDay);
	}

	public PsionDiscipline getDisciplineFocus() {
		return disciplineFocus;
	}

	public void setDisciplineFocus(PsionDiscipline disciplineFocus) {
		this.disciplineFocus = disciplineFocus;
	}

	@Override
	public int getArmorClassBonus() {
		// TODO Auto-generated method stub
		return 0;
	}
}
