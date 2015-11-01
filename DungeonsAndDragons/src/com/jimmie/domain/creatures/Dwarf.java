package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.items.weapons.WeaponType;
import com.jimmie.util.Utils;

public class Dwarf extends Race {

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
		// TODO Auto-generated method stub

	}

	@Override
	public void initializeForNewDay() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processAfterHurtEffects(Creature creature) {
		// TODO Auto-generated method stub

	}

	@Override
	public void makeRaceChoices(PlayerCharacter pc, DndClass dndClass) {
		Utils.print("What is your character's name? From the book it suggests the following male and female names:");
		Utils.print("Male Names: Adrik, Baern, Berend, Darrak, Eberk, Fargrim, Gardain, Harbek, Kildrak, Morgran, Orsik, Rangrim, Thoradin, Thorfin, Tordek, Travok, Vondal");
		Utils.print("Female Names: Artin, Bardryn, Diesa, Eldeth, Falkrunn, Gurdis, Helja, Kathra, Kristryd, Bardred, Riswynn, Torbera, Vistra");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 4' 3\" - 4' 9\" (51\" - 57\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 160 - 220 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("As a Dwarf you get +2 to Constitution and Wisdom.");
		pc.setConstitution(pc.getConstitution() + 2);
		pc.setWisdom(pc.getWisdom() + 2);
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 5.");
		pc.setSpeed(5);
		
		Utils.print("Adding low-light vision to senses.");
		pc.addSense(new Sense(SenseType.LOWLIGHT_VISION));
		
		pc.addLanguage("Common");
		pc.addLanguage("Dwarven");
		
		Utils.print("As a Dwarf, you get +2 Dungeoneering and Endurance");
		Skill dungeoneering = pc.getSkill(SkillType.DUNGEONEERING);
		dungeoneering.setMisc(dungeoneering.getMisc()+2);
		
		Skill endurance = pc.getSkill(SkillType.ENDURANCE);
		endurance.setMisc(endurance.getMisc()+2);
		
		Utils.print("As a Dwarf, you get a weapon proficiency in warhammer and throwing hammer.");
		
		pc.addWeaponTypeProficiency(WeaponType.WARHAMMER);
		pc.addWeaponTypeProficiency(WeaponType.THROWING_HAMMER);
		
		// TODO: Cast-iron stomach, dwarven resilience, encumbered speed, stand your ground.
		Utils.print("NOTE: I have not yet coded cast-iron stomach, dwarven resilience, encumbered speed or stand your ground.");
	}

}
