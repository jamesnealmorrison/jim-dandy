package com.jimmie.domain.creatures;

import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.Skill;
import com.jimmie.domain.SkillType;
import com.jimmie.domain.classes.DndClass;
import com.jimmie.powers.MemoryOfAThousandLifetimes;
import com.jimmie.util.Utils;

public class Deva extends Race {

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
	public void makeRaceChoices(PlayerCharacter pc,
			DndClass dndClass) {
		Utils.print("What is your character's name? From the book it suggests the following male and female names:");
		Utils.print("Male Names: Adiah, Ansis, Ayab, Bavak, Beriah, Eben, Elyas, Galad, Gamal, Hiyal, Iannes, Kerem,");
		Utils.print("            Mahar, Marach, Mathas, Natan, Nehem, Oris, Raham, Ronen, Samel, Sered, Tavar, Vered, Zachar");
		Utils.print("Female Names: Abea, Adara, Asha, Chana, Danel, Darah, Davi, Elka, Erenah, Hania, Hava, Idria, Isa,");
		Utils.print("              Jael, Kana, Kayah, Lihi, Mahel, Marek, Noma, Navah, Paziah, Ravah, Riya, Sada, Shara, Tirah");
		Utils.print("Your name?");
		pc.setName(Utils.getInput());
		
		Utils.print("What Height is your character?  The book suggests 6' 1\" - 6' 6\" (73\" - 78\")");
		Utils.print("Your choice (in inches):");
		pc.setHeight(Utils.getValidIntInputInRange(0, 200));
		
		Utils.print("What Weight is your character?  The book suggests 175 - 280 lb");
		Utils.print("Your choice (in pounds):");
		pc.setWeight(Utils.getValidIntInputInRange(0, 1000));
		
		Utils.print("Setting size to Medium.");
		pc.setSize(Size.MEDIUM);
		
		Utils.print("Setting speed to 6.");
		pc.setBaseSpeed(6);
		
		Utils.print("Adding normal vision to senses.");
		pc.addSense(new Sense(SenseType.NORMAL_VISION));
		
		pc.addLanguage("Common");
		// TODO: Language choice.
		Utils.print("NOTE: I have not yet coded the two language choices.");
		
		Utils.print("As a Deva, you get +2 History and Religion.");
		Skill history = pc.getSkill(SkillType.HISTORY);
		history.setMisc(history.getMisc()+2);
		
		Skill religion = pc.getSkill(SkillType.RELIGION);
		religion.setMisc(religion.getMisc()+2);
		
		pc.addPower(new MemoryOfAThousandLifetimes());
		
		// TODO: Astral Majesty, Astral Resistance, Immortal Origin.
		Utils.print("NOTE: I have not yet coded Astral Majesty, Astral Resistance, Immortal Origin.");		
		
	}

	@Override
	public void makeRacialAbilityScoreAdjustments(PlayerCharacter pc,
			DndClass dndClass) {
		Utils.print("As a Dwarf you get +2 to Intelligence and Wisdom.");
		setIntelligenceBonus(getIntelligenceBonus()+2);
		setWisdomBonus(getWisdomBonus()+2);
	}

}
