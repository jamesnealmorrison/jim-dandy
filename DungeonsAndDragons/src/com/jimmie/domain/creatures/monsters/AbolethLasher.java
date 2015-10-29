package com.jimmie.domain.creatures.monsters;

import java.util.ArrayList;
import java.util.List;

import com.jimmie.domain.ActionType;
import com.jimmie.domain.AlternativeMovementMode;
import com.jimmie.domain.AlternativeMovementModeType;
import com.jimmie.domain.Attack;
import com.jimmie.domain.AttackPower;
import com.jimmie.domain.Aura;
import com.jimmie.domain.AuraEffect;
import com.jimmie.domain.AuraEffectType;
import com.jimmie.domain.ConditionEffect;
import com.jimmie.domain.DamageEffect;
import com.jimmie.domain.DefenseType;
import com.jimmie.domain.DiceType;
import com.jimmie.domain.Effect;
import com.jimmie.domain.EffecteeType;
import com.jimmie.domain.Power;
import com.jimmie.domain.PowerType;
import com.jimmie.domain.RechargeType;
import com.jimmie.domain.Sense;
import com.jimmie.domain.SenseType;
import com.jimmie.domain.TimeConstraint;
import com.jimmie.domain.creatures.CreatureConditionType;
import com.jimmie.domain.creatures.Origin;
import com.jimmie.domain.creatures.Role;
import com.jimmie.domain.creatures.Size;

public class AbolethLasher extends Aboleth {
	private static final long serialVersionUID = 1L;
	
	public AbolethLasher() {
		setName("Aboleth Lasher");
		setLevel(17);
		setRole(Role.BRUTE);
		setElite(false);
		setSolo(false);
		setLeader(false);
		setSize(Size.LARGE);
		setOrigin(Origin.ABERRANT);
		setType(type);
		keywords = new ArrayList<MonsterKeyword>();
		keywords.add(MonsterKeyword.AQUATIC);
		setExperiencePointValue(1600);
		setInitiative(11);
		senses = new ArrayList<Sense>();
		Sense perception = new Sense(SenseType.PERCEPTION, 14);
		senses.add(perception);
		Sense darkvision = new Sense(SenseType.DARKVISION);
		senses.add(darkvision);
		Aura mucusHaze = new Aura();
		mucusHaze.setRange(5);
		AuraEffect effect = new AuraEffect();
		effect.setWhenEffectOccurs(TimeConstraint.ALWAYS);
		effect.setWhoIsEffected(EffecteeType.ENEMIES);
		effect.setEffectType(AuraEffectType.DIFFICULT_TERRAIN);
		List<AuraEffect> effects = new ArrayList<AuraEffect>();
		effects.add(effect);
		mucusHaze.setEffects(effects);
		List<Aura> auras = new ArrayList<Aura>();
		auras.add(mucusHaze);
		setAuras(auras);
		setMaxHitPoints(200);
		setArmorClass(29);
		setFortitude(27);
		setReflex(25);
		setWill(25);
		setSpeed(5);
		List<AlternativeMovementMode> altMovementModes = new ArrayList<AlternativeMovementMode>();
		AlternativeMovementMode altMovementMode = new AlternativeMovementMode(AlternativeMovementModeType.SWIM, 10);
		altMovementModes.add(altMovementMode);
		setAlternativeMovementModes(altMovementModes);
		List<Power> powers = new ArrayList<Power>();
		AttackPower tentacle = new AttackPower();
		tentacle.setActionType(ActionType.STANDARD);
		tentacle.setBasic(true);
		tentacle.setName("Tentacle");
		tentacle.setPowerType(PowerType.MELEE);
		tentacle.setRechargeType(RechargeType.AT_WILL);
		tentacle.setReach(2);
		Attack attack = new Attack();
		attack.setBasicAttackModifier(20);
		attack.setDefenseType(DefenseType.ARMOR_CLASS);
		tentacle.setPrimaryAttack(attack);
		List<Effect> attackEffects = new ArrayList<Effect>();
		DamageEffect effect1 = new DamageEffect();
		effect1.setNumberOfDice(2);
		effect1.setDiceType(DiceType.EIGHT_SIDED);
		effect1.setModifier(8);
		List<EffecteeType> unaffectedTargets = new ArrayList<EffecteeType>();
		unaffectedTargets.add(EffecteeType.DAZED_TARGET);
		effect1.setUnaffectedTargets(unaffectedTargets);
		attackEffects.add(effect1);
		
		ConditionEffect effect2 = new ConditionEffect();
		effect2.setConditionToApply(CreatureConditionType.DAZED);
		effect2.setUnaffectedTargets(unaffectedTargets);
		attackEffects.add(effect2);
		
		DamageEffect effect3 = new DamageEffect();
		effect3.setNumberOfDice(4);
		effect3.setDiceType(DiceType.EIGHT_SIDED);
		effect3.setModifier(8);
		List<EffecteeType> affectedTargets = new ArrayList<EffecteeType>();
		affectedTargets.add(EffecteeType.DAZED_TARGET);
		effect3.setAffectedTargets(affectedTargets);
		attackEffects.add(effect3);
		
		
		//tentacle.setPrimaryHitEffects(primaryHitEffects);
		powers.add(tentacle);
		//setPowers(powers);
	}
}
