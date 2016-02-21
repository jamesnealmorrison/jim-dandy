package com.jimmie.domain.creatures;

import com.jimmie.domain.DamageType;
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

		Utils.print("Adding Common Language.  Choose two other languages:");
		for (int i = 0; i < 2; i++) {
			Utils.print("1. Deep Speech");
			Utils.print("2. Draconic");
			Utils.print("3. Dwarven");
			Utils.print("4. Elven");
			Utils.print("5. Giant");
			Utils.print("6. Goblin");
			Utils.print("7. Primordial");
			Utils.print("8. Supernal");
			Utils.print("9. Abyssal");

			Utils.print("Your choice:");
			int choice = Utils.getValidIntInputInRange(1, 9);
			
			switch (choice) {
			case 1 :
				pc.addLanguage("DeepSpeech");
				break;
			case  2:
				pc.addLanguage("Draconic");
				break;
			case  3:
				pc.addLanguage("Dwarven");
				break;
			case  4:
				pc.addLanguage("Elven");
				break;
			case  5:
				pc.addLanguage("Giant");
				break;
			case  6:
				pc.addLanguage("Goblin");
				break;
			case  7:
				pc.addLanguage("Primordial");
				break;
			case  8:
				pc.addLanguage("Supernal");
				break;
			case  9:
				pc.addLanguage("Abyssal");
				break;
			}
		
		}
		Utils.print("As a Deva, you get +2 History and Religion.");
		Skill history = pc.getSkill(SkillType.HISTORY);
		history.setMisc(history.getMisc()+2);
		
		Skill religion = pc.getSkill(SkillType.RELIGION);
		religion.setMisc(religion.getMisc()+2);
		
		// Astral Resistance.
		// TODO: Need to implement the "+1/2 your level", but that can wait until my character is level 2.
		pc.addDamageResistance(DamageType.NECROTIC, 5);
		pc.addDamageResistance(DamageType.RADIANT, 5);
		
		pc.addPower(new MemoryOfAThousandLifetimes());
		
		pc.setOrigin(Origin.IMMORTAL);
	}

	@Override
	public void makeRacialAbilityScoreAdjustments(PlayerCharacter pc,
			DndClass dndClass) {
		Utils.print("As a Deva you get +2 to Intelligence and Wisdom.");
		setIntelligenceBonus(getIntelligenceBonus()+2);
		setWisdomBonus(getWisdomBonus()+2);
	}

	@Override
	public String getRaceFeaturesText1() {
		return "Astral Majesty: +1 bonus to all defenses";
	}

	@Override
	public String getRaceFeaturesText2() {
		return "against attacks made by bloodied creatures.";
	}

	@Override
	public String getRaceFeaturesText3() {
		return "Astral Resistance: Resist necrotic and radiant";
	}

	@Override
	public String getRaceFeaturesText4() {
		return "damage equal to 5 + 1/2 level";
	}

	@Override
	public String getRaceFeaturesText5() {
		return "Immortal Origin: Considered an immortal creature.";
	}

	@Override
	public String getRaceFeaturesText6() {
		return "I have the Memory of a Thousand Lifetimes power";
	}

	@Override
	public String getRaceFeaturesText7() {
		return "which allows me to add 1d6 to certain rolls.";
	}
}

