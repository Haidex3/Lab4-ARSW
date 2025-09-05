package edu.eci.arsw.springdemo;

import org.springframework.stereotype.Component;

@Component
public interface SpellChecker {

	public String checkSpell(String text);
	
}
