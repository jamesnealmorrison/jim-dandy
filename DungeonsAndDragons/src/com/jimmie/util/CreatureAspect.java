package com.jimmie.util;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class CreatureAspect {
/*   	@Before("execution(* com.jimmie.domain.*.*(..))")
	public void doSomething() {
		Utils.print("I JUST DID SOMETHING!!!");
	}
	*/
	@Before("execution(* com.jimmie.domain.creatures.*.hurt(..))")
	public void doSomethingBeforeHurt() {
		Utils.print("I JUST DID SOMETHING BEFORE hurt()!!!");
	}
	@After("execution(* com.jimmie.domain.creatures.*.hurt(..))")
	public void doSomethingAfterHurt() {
		Utils.print("I JUST DID SOMETHING AFTER jim()!!!");
	}

//	@Before("execution(* com.jimmie.domain.creatures.monsters.KoboldMinion.startOfTurn(..))")
	@Before("execution(* com.jimmie.domain.creatures.monsters.KoboldMinion.startOfTurn(..))")
	public void doSomethingBeforeStartOfTurn() {
		Utils.print("I am a " + this.getClass());
		Utils.print("I JUST DID SOMETHING BEFORE startOfTurn()!!!");
	}
//	@After("execution(* com.jimmie.domain.creatures.monsters.KoboldMinion.startOfTurn(..))")
	@Before("execution(* *.moveCreature(..))")
	public void doSomethingBeforeMoveCreature() {
		Utils.print("I JUST DID SOMETHING BEFORE MOVECREATURE()!!!");
	}

/*
	@After("execution(* com.jimmie.encounters.KoboldLairOutsideEncounter.tryThis(..))")
	public void doSomethingAfterTryThis() {
		Utils.print("I JUST DID SOMETHING AFTER TryThis()!!!");
	}

	@Before("execution(* com.jimmie.encounters.*.getMap(..))")
	public void doSomethingBeforeGetMap() {
		Utils.print("I JUST DID SOMETHING BEFORE getMap()!!!");
	}
*/
}
