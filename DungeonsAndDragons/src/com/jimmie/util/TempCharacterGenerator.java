package com.jimmie.util;

import com.jimmie.domain.classes.DndClass;
import com.jimmie.domain.classes.Fighter;
import com.jimmie.domain.creatures.Elf;
import com.jimmie.domain.creatures.Gender;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.creatures.Character;
import com.jimmie.domain.creatures.Race;
import com.jimmie.domain.creatures.Size;
import com.jimmie.domain.items.armor.ClothArmor;
import com.jimmie.domain.items.weapons.ShortSword;

public class TempCharacterGenerator {

	public static void main(String[] args) {
		TempCharacterGenerator gen = new TempCharacterGenerator();
		gen.run();
	}
	
	public void run() {
		Race race = new Elf();
		DndClass dndClass = new Fighter();
		Character c1 = new PlayerCharacter(race, dndClass);
		
		c1.setAge(30);
		c1.addArmor(new ClothArmor());
		c1.setCharisma(10);
		c1.setConstitution(12);
		c1.setCurrentHitPoints(20);
		c1.setDexterity(16);
		c1.setDisplayName("Jim");
		c1.setGender(Gender.MALE);
		c1.setIntelligence(11);
		c1.setLevel(1);
		c1.setMaxHitPoints(20);
		c1.setName("Jim");
		c1.setReadiedWeapon(new ShortSword());
		c1.setSize(Size.MEDIUM);
		c1.setSpeed(6);
		c1.setStrength(14);
		c1.setWisdom(13);
		c1.setImagePath("c:\\GitRepositories\\jim-dandy\\DungeonsAndDragons\\resources\\ElfAvenger.jpg");
		
		Utils.saveCharacter(c1);
	}

}
