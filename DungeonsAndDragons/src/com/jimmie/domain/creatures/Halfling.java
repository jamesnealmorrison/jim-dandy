package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.util.Utils;

public class Halfling extends Race {

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
		Utils.print("Male Names: Ander, Corrin, Dannad, Errich, Finnan, Garret, Lazam, Lindal, Merric, Nebin, Ostran, Perrin, Reed, Shardon, Ulmo, Wenner");
		Utils.print("Female Names: Andrey, Bree, Callie, Chenna, Eida, Kithri, Lidda, Nedda, Paela, Shaena, Tryn, Vani, Verna, Wella");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 3' 10\" - 4' 2\" (46\" - 50\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 75 - 85 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("Setting size to Small.");
		pc.setSize(Size.SMALL);
		
		Utils.print("Setting speed to 6.");
		pc.setSpeed(6);
		
		Utils.print("Adding normal vision to senses.");
		pc.addSense(new Sense(SenseType.NORMAL_VISION));
		
		pc.addLanguage("Common");
		
		// TODO: Choose another language.
		Utils.print("NOTE: I have not yet coded the choice of another language.");
		
		Utils.print("As a Halfling, you get +2 Acrobatics and Thievery");
		Skill acrobatics = pc.getSkill(SkillType.ACROBATICS);
		acrobatics.setMisc(acrobatics.getMisc()+2);
		
		Skill thievery = pc.getSkill(SkillType.THIEVERY);
		thievery.setMisc(thievery.getMisc()+2);
		
		// TODO: Bold, Nimble Reaction, Second Chance.
		Utils.print("NOTE: I have not yet coded Bold, Nimble Reaction, Second Chance.");
	}

	@Override
	public void makeRacialAbilityScoreAdjustments(PlayerCharacter pc,
			DndClass dndClass) {
		Utils.print("As a Halfling you get +2 to Dexterity and Charisma.");
		pc.setDexterity(pc.getDexterity() + 2);
		pc.setCharisma(pc.getCharisma() + 2);
	}

}
