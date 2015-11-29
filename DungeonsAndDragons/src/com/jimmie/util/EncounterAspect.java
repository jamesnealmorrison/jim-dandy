package com.jimmie.util;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;

//@Aspect
public class EncounterAspect {
	@Before("execution(* com.jimmie.domain.*.*(..))")
	public void doSomething() {
		Utils.print("I JUST DID SOMETHING!!!");
	}
	
	@Before("execution(* com.jimmie.encounters.KoboldLairOutsideEncounter+.*(..))")
	public void doSomethingBeforeTryThis() {
		Utils.print("I JUST DID SOMETHING BEFORE TryThis()!!!");
	}

	@After("execution(* com.jimmie.encounters.KoboldLairOutsideEncounter.tryThis(..))")
	public void doSomethingAfterTryThis() {
		Utils.print("I JUST DID SOMETHING AFTER TryThis()!!!");
	}

	@Before("execution(* com.jimmie.encounters.*.getMap(..))")
	public void doSomethingBeforeGetMap() {
		Utils.print("I JUST DID SOMETHING BEFORE getMap()!!!");
	}
}
