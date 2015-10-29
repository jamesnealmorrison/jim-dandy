package com.jimmie.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.jimmie.domain.Position;
import com.jimmie.domain.classes.Avenger;
import com.jimmie.domain.classes.Bard;
import com.jimmie.domain.classes.Fighter;
import com.jimmie.domain.classes.Psion;
import com.jimmie.domain.classes.Warden;
import com.jimmie.domain.creatures.Creature;
import com.jimmie.domain.creatures.Elf;
import com.jimmie.domain.creatures.Gnome;
import com.jimmie.domain.creatures.Goliath;
import com.jimmie.domain.creatures.HalfOrc;
import com.jimmie.domain.creatures.Human;
import com.jimmie.domain.creatures.PlayerCharacter;
import com.jimmie.domain.items.ChainMail;
import com.jimmie.domain.items.ClothArmor;
import com.jimmie.domain.items.HeavyShield;
import com.jimmie.domain.items.HideArmor;
import com.jimmie.domain.items.LightShield;
import com.jimmie.domain.items.LongSword;
import com.jimmie.domain.items.Mace;
import com.jimmie.domain.items.NoShield;
import com.jimmie.domain.items.ScaleArmor;
import com.jimmie.domain.items.ShortSword;
import com.jimmie.domain.items.WarHammer;
import com.jimmie.util.IntegratedCommandConsole;

public class TestGUI extends JPanel {
	static JFrame frame = null;
	private static final long serialVersionUID = 1234567890L;
	IntegratedCommandConsole console;

	JPanel topPanel = null;
	PartyPanel partyPanel = null;
	//   PartyPanel badGuysPanel = null;

	/*   private static BeanFactory getBeanFactory() throws Exception
	   {
	      XmlBeanFactory factory = new XmlBeanFactory(new FileSystemResource("./resource/spring/beans.xml"));

	      return factory;
	   }
	 */
	public TestGUI()
	{

		//	      try
		//	      {
		//	         badGuys.load(new Long(2));
		/*         System.setProperty("hibernate.cglib.use_reflection_optimizer", "false");
	         BeanFactory factory = getBeanFactory();
	         LOTRService service = (LOTRService) factory.getBean("LOTRService");

	         service.getPartyDAO().startTransaction();
	         fellowship = service.getPartyDAO().getByName("Our Fellowship");
		 */
		// for now, just throw in some fell beasts:
		/* Now get fell beasts */

		/*       
	       FellBeast fb = new ForestOrc();
	       fb.setName("Forest Orc 1");
	       badGuys.addMember(fb);

	       fb = new ForestOrc();
	       fb.setName("Forest Orc 2");
	       badGuys.addMember(fb);

	       fb = new HillTroll();
	       fb.setName("Hill Troll");
	       badGuys.addMember(fb);

	      }
	      catch (Exception e)
	      {
	         System.out.println("Something went horribly wrong.");
	         e.printStackTrace();
	      }      
	      console = new IntegratedCommandConsole();
	      console.setFellowship(fellowship);
	      console.setBadGuys(badGuys);
	      Utils.setICC(console);
	      Utils.setGui(this);
		 */

		topPanel = new JPanel();
		LayoutManager borderLayout = new BorderLayout();
		Dimension preferredSize = new Dimension();
		preferredSize.setSize(1250, 710);
		topPanel.setPreferredSize(preferredSize);
		topPanel.setLayout(borderLayout);

		/* Set up the player characters */
		Avenger avenger = new Avenger();
		avenger.addPower(Avenger.BOND_OF_PURSUIT);
		avenger.addPower(Avenger.RADIANT_VENGEANCE);
		avenger.addPower(Avenger.ANGELIC_ALACRITY);
		avenger.addPower(Avenger.OATH_OF_ENMITY);
		avenger.addPower(Avenger.ABJURE_UNDEAD);
		avenger.addPower(Avenger.DIVINE_GUIDANCE);
		avenger.addPower(Avenger.ASPECT_OF_MIGHT);
		Elf elf = new Elf();
		PlayerCharacter elfAvenger = new PlayerCharacter(elf, avenger);
		avenger.setOwner(elfAvenger);
		elf.setOwner(elfAvenger);
		elfAvenger.setName("Elf Avenger");
		/* Setting to 30 temporarily so he comes first in testing. */		
		//			elfAvenger.setInitiative(3);
		elfAvenger.setInitiative(30);
		elfAvenger.setMaxHitPoints(23);
		elfAvenger.setCurrentHitPoints(23);
		elfAvenger.setFortitude(12);
		elfAvenger.setReflex(14);
		elfAvenger.setWill(15);
		elfAvenger.setSpeed(7);
		elfAvenger.setStrength(11);
		elfAvenger.setConstitution(12);
		elfAvenger.setDexterity(16);
		elfAvenger.setIntelligence(13);
		elfAvenger.setWisdom(18);
		elfAvenger.setCharisma(10);
		LongSword ls1 = new LongSword();
		elfAvenger.addWeaponProficiency(ls1.getWeaponId());
		elfAvenger.setReadiedWeapon(ls1);
		elfAvenger.setArmor(new ClothArmor());
		elfAvenger.setReadiedShield(new NoShield());
		elfAvenger.setCurrentPosition(new Position(2,1));

		Gnome gnome = new Gnome();
		Bard bard = new Bard();
		PlayerCharacter kellen = new PlayerCharacter(gnome, bard);
		bard.setOwner(kellen);
		gnome.setOwner(kellen);
		kellen.setName("Kellen");
		kellen.setInitiative(1);
		kellen.setMaxHitPoints(25);
		kellen.setCurrentHitPoints(25);
		kellen.setFortitude(11);
		kellen.setReflex(15);
		kellen.setWill(15);
		kellen.setSpeed(4);
		kellen.setStrength(10);
		kellen.setConstitution(13);
		kellen.setDexterity(12);
		kellen.setIntelligence(16);
		kellen.setWisdom(11);
		kellen.setCharisma(18);
		kellen.setCurrentPosition(new Position(2,2));
		kellen.setReadiedWeapon(new ShortSword());
		kellen.setReadiedShield(new LightShield());
		kellen.setArmor(new ChainMail());

		Warden warden = new Warden();
		warden.addPower(Warden.STRENGTH_OF_STONE);
		warden.addPower(Warden.EARTH_SHIELD_STRIKE);
		warden.addPower(Warden.WARDENS_FURY);
		warden.addPower(Warden.THUNDER_RAM_ASSAULT);
		warden.addPower(Warden.WARDENS_GRASP);
		warden.addPower(Warden.FORM_OF_THE_WILLOW_SENTINEL);
		Goliath goliath = new Goliath();
		goliath.addPower(Goliath.STONES_ENDURANCE);
		PlayerCharacter glock = new PlayerCharacter(goliath, warden);
		warden.setOwner(glock);
		goliath.setOwner(glock);
		glock.setName("Glock Elmhurst");
		glock.setInitiative(1);
		glock.setMaxHitPoints(33);
		glock.setCurrentHitPoints(33);
		glock.setFortitude(15);
		glock.setReflex(11);
		glock.setWill(13);
		glock.setSpeed(6);
		glock.setStrength(18);
		glock.setConstitution(16);
		glock.setDexterity(12);
		glock.setIntelligence(11);
		glock.setWisdom(10);
		glock.setCharisma(13);
		glock.setCurrentPosition(new Position(2,3));
		WarHammer warHammer = new WarHammer();
		glock.setReadiedWeapon(warHammer);
		glock.addWeaponProficiency(warHammer.getWeaponId());
		glock.setReadiedShield(new LightShield());
		glock.setArmor(new HideArmor());

		Fighter fighter = new Fighter();
		fighter.addPower(Fighter.SURE_STRIKE);
		fighter.addPower(Fighter.TIDE_OF_IRON);
		fighter.addPower(Fighter.COVERING_ATTACK);
		fighter.addPower(Fighter.COMEBACK_STRIKE);
		HalfOrc halfOrc = new HalfOrc();
		halfOrc.addPower(HalfOrc.FURIOUS_ASSAULT);
		halfOrc.addPower(HalfOrc.HALF_ORC_RESILIENCE);
		PlayerCharacter halfOrcFighter = new PlayerCharacter(halfOrc, fighter);
		fighter.setOwner(halfOrcFighter);
		fighter.setFighterWeaponTalent(Fighter.ONE_HANDED_WEAPON);
		halfOrc.setOwner(halfOrcFighter);
		halfOrcFighter.setName("Eleak Nightraider");
		//halfOrcFighter.setInitiative(3);
		/* Changing the initiative to 30 because I want him to go first for testing. */
		halfOrcFighter.setInitiative(3);
		halfOrcFighter.setMaxHitPoints(27);
		halfOrcFighter.setCurrentHitPoints(27);
		halfOrcFighter.setFortitude(16);
		halfOrcFighter.setReflex(15);
		halfOrcFighter.setWill(11);
		halfOrcFighter.setSpeed(5);
		halfOrcFighter.setStrength(18);
		halfOrcFighter.setConstitution(12);
		halfOrcFighter.setDexterity(16);
		halfOrcFighter.setIntelligence(10);
		halfOrcFighter.setWisdom(13);
		halfOrcFighter.setCharisma(11);
		LongSword ls = new LongSword();
		halfOrcFighter.setReadiedWeapon(ls);
		halfOrcFighter.setReadiedShield(new HeavyShield());
		halfOrcFighter.addWeaponProficiency(ls.getWeaponId());
		halfOrcFighter.setCurrentPosition(new Position(2,4));
		halfOrcFighter.setArmor(new ScaleArmor());
		halfOrcFighter.setHealingSurgesPerDay(10);
		halfOrcFighter.setHealingSurgeValue(6);

		Human human = new Human();
		Psion psion = new Psion();
		PlayerCharacter tane = new PlayerCharacter(human, psion);
		psion.setOwner(tane);
		human.setOwner(tane);
		tane.setName("Tane Wolfsbane");
		tane.setInitiative(0);
		tane.setMaxHitPoints(25);
		tane.setCurrentHitPoints(25);
		tane.setFortitude(12);
		tane.setReflex(15);
		tane.setWill(15);
		tane.setSpeed(6);
		tane.setStrength(12);
		tane.setConstitution(13);
		tane.setDexterity(10);
		tane.setIntelligence(18);
		tane.setWisdom(14);
		tane.setCharisma(11);
		tane.setReadiedWeapon(new Mace());
		tane.setReadiedShield(new NoShield());
		tane.setCurrentPosition(new Position(2,5));
		tane.setArmor(new ClothArmor());

		List<Creature> characters = new ArrayList<Creature>();
		characters.add(elfAvenger);
		characters.add(kellen);
		characters.add(glock);
		characters.add(halfOrcFighter);
		characters.add(tane);

		partyPanel = new PartyPanel(characters);
		/*      badGuysPanel = new PartyPanel(badGuys);
	      ConsolePanel consolePanel = new ConsolePanel();

	      console.setConsolePanel(consolePanel);

	      console.addPropertyChangeListener(consolePanel);
		 */      
		topPanel.add(partyPanel, BorderLayout.WEST);
		add(topPanel);
		/*
	      add(badGuysPanel);
	      add(consolePanel);
		 */

	}


	public static void main(String[] args)
	{
		frame = new JFrame("Test Application");      

		TestGUI newTestGUI = new TestGUI();
		newTestGUI.setOpaque(true);

		frame.setContentPane(newTestGUI);

		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				createAndShowGUI();
			}
		}
				);

		newTestGUI.run();
	}

	private static void createAndShowGUI()
	{
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void run()
	{
		//console.run();
	}
	public void refreshAll()
	{
	}

}
