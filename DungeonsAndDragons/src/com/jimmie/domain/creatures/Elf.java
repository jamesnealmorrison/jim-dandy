package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.items.weapons.WeaponType;
import com.jimmie.util.Utils;

public class Elf extends Race {
	private boolean usedElvenAccuracy;

	public boolean isUsedElvenAccuracy() {
		return usedElvenAccuracy;
	}

	public void setUsedElvenAccuracy(boolean usedElvenAccuracy) {
		this.usedElvenAccuracy = usedElvenAccuracy;
	}

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
		usedElvenAccuracy = false;		
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
		
		Utils.print("As an Elf you get +2 to Dexterity and Wisdom.");
		pc.setDexterity(pc.getDexterity() + 2);
		pc.setWisdom(pc.getWisdom() + 2);
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 7.");
		pc.setSpeed(7);
		
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
		
		// TODO: Fey Origin, Group Awareness, Wild Step, Elven Accuracy
		Utils.print("NOTE: I have not yet coded Fey Origin, Group Awareness, Wild Step, Elven Accuracy.");
	}
}
