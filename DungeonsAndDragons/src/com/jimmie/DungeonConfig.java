package com.jimmie;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import com.jimmie.util.EncounterAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class DungeonConfig {
	@Bean
	public EncounterAspect encounterAspect() {
		return new EncounterAspect();
	}
}
