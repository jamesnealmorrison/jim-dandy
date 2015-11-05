package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.util.Utils;

public class Minotaur extends Race {

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
		Utils.print("Male Names: Asteron, Bjorkus, Codrus, Foostus, Goeban, Jak, Minron, Noostoron, Podrus, Terios");
		Utils.print("Female Names: Duula, Esteru, Hester, Kuonu, Loodra, Oestra, Raastred, Seestra, Uovana, Weoren");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 7' 1\" - 7' 5\" (85\" - 89\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 320 - 350 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("As a Minotaur you get +2 to Strength.");
		pc.setStrength(pc.getStrength() + 2);

		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setSpeed(6);
		
		Utils.print("Adding normal vision to senses.");
		pc.addSense(new Sense(SenseType.NORMAL_VISION));
		
		pc.addLanguage("Common");

		// TODO: Language choice
		Utils.print("NOTE: I have not yet coded the language choice for Minotaurs.");
		
		Utils.print("As a Minotaur, you get +2 Nature and Perception");
		Skill nature = pc.getSkill(SkillType.NATURE);
		nature.setMisc(nature.getMisc()+2);
		
		Skill perception = pc.getSkill(SkillType.PERCEPTION);
		perception.setMisc(perception.getMisc()+2);
		
		// TODO: Vitality, Ferocity, Heedless Charge, Goring Charge."
		Utils.print("NOTE: I have not yet coded Vitality, Ferocity, Heedless Charge, Goring Charge.");
		
	}

	@Override
	public void makeRacialAbilityScoreAdjustments(PlayerCharacter pc,
			DndClass dndClass) {
		Utils.print("As a Minotaur, you get to choose to add +2 to Constitution or Wisdom.");
		Utils.print("1. Constitution");
		Utils.print("2. Wisdom");
		Utils.print("Your choice:");
		int choice = Utils.getValidIntInputInRange(1, 2);
		if (1 == choice) {
			pc.setConstitution(pc.getConstitution()+2);
		} else {
			pc.setWisdom(pc.getWisdom()+2);
		}
	}

}
