package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.items.weapons.WeaponType;
import com.jimmie.powers.ElvenAccuracy;
import com.jimmie.util.Utils;

public class Elf extends Race {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int getRacialDamageBonus() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void initializeForEncounter() {
	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeRaceChoices(PlayerCharacter pc, DndClass dndClass) {
		Utils.print("What is your character's name? From the book it suggests the following male and female names:");
		Utils.print("Male Names: Adran, Aelar, Beiro, Carric, Erdan, Gennal, Heian, Lucan, Peren, Rolen, Theren, Varis");
		Utils.print("Female Names: Adrie, Birel, Chaedi, Dara, Enna, Faral, Irann, Keyleth, Lia, Mialee, Shava, Thia, Valna");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 5' 4\" - 6' 0\" (64\" - 72\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 130 - 170 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 7.");
		pc.setBaseSpeed(7);
		
		Utils.print("Adding low-light vision to senses.");
		pc.addSense(new Sense(SenseType.LOWLIGHT_VISION));
		
		pc.addLanguage("Common");
		pc.addLanguage("Elven");
		
		Utils.print("As an Elf, you get +2 Nature and Perception");
		Skill nature = pc.getSkill(SkillType.NATURE);
		nature.setMisc(nature.getMisc()+2);
		
		Skill perception = pc.getSkill(SkillType.PERCEPTION);
		perception.setMisc(perception.getMisc()+2);
		
		Utils.print("As an Elf, you get a weapon proficiency in longbow and shortbow.");
		
		pc.addWeaponTypeProficiency(WeaponType.LONGBOW);
		pc.addWeaponTypeProficiency(WeaponType.SHORTBOW);

		pc.addPower(new ElvenAccuracy());
		
		pc.setOrigin(Origin.FEY);
		
	}

	@Override
	public void makeRacialAbilityScoreAdjustments(PlayerCharacter pc,
			DndClass dndClass) {
		Utils.print("As an Elf you get +2 to Dexterity and Wisdom.");
		setDexterityBonus(getDexterityBonus()+2);
		setWisdomBonus(getWisdomBonus()+2);
	}

	@Override
	public String getRaceFeaturesText1() {
		return "Elven Weapon Proviciency: Longbow and Shortbow.";
	}

	@Override
	public String getRaceFeaturesText2() {
		return "Fey Origin: Considered a Fey Creature.";
	}

	@Override
	public String getRaceFeaturesText3() {
		return "Group Awareness: I grant non-elf allies a +1";
	}

	@Override
	public String getRaceFeaturesText4() {
		return "bonus to Perception when within 5 squares.";
	}

	@Override
	public String getRaceFeaturesText5() {
		return "Wild Step: I ignore difficult terrain when";
	}

	@Override
	public String getRaceFeaturesText6() {
		return "I shift.";
	}

	@Override
	public String getRaceFeaturesText7() {
		return "Elven Accuracy power: Reroll an attack roll.";
	}
}
