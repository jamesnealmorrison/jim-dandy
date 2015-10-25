package com.jimmie.domain.creatures;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbilityScoreTest {

	@Test
	public void testGetModifier() {
		AbilityScore ability = new AbilityScore();
	
		ability.setScore(1);
		assertEquals(-5,ability.getModifier());
		
		ability.setScore(14);
		assertEquals(2,ability.getModifier());
		
		ability.setScore(15);
		assertEquals(2,ability.getModifier());
		
		ability.setScore(35);
		assertEquals(12,ability.getModifier());
	}

}
