package dk.heatless.regex2string;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import dk.brics.automaton.RegExp;
import dk.heatless.regex2string.RegexGenerator;
import dk.heatless.regex2string.core.Generator;
import dk.heatless.regex2string.core.StaticStringGenerator;

public class RegexGeneratorTest {
	
	
	
	@Test
	public void subclassGeneratesValid(){
		/*
		 * Test that a subclass that generates a valid string also returns it correctly
		 */
		final String generated = "12345";
		RegexGenerator regGen = getRegGenInstance(new RegExp("[0-5]{5}"), generated);
		
		
		assertEquals(regGen.generate(), generated);
	}
	
	@Test
	public void subclassGeneratesPrefix(){
		/*
		 *Test that a subclass that generates only a prefix of a valid string returns null 
		 */
		final String generated = "12345";
		RegexGenerator regGen = getRegGenInstance(new RegExp("[0-5]{5}[a-z][3]"), generated);
		
		assertEquals(regGen.generate(), null);
	}
		
	@Test
	public void subclassGeneratesPostfix(){
		/*
		 * Test that a subclass that generates only a postfix of a valid string return null
		 */
		final String generated = "abc";
		RegexGenerator regGen = getRegGenInstance(new RegExp("[0-5]{5}[a-z][3]"), generated);
		
		assertEquals(regGen.generate(), null);
	}
	
	@Test
	public void subclassGeneratesSubstring(){
		/*
		 * Test that a subclass that generates a substring of a valid string that is neither
		 * pre- or postfix returns null
		 */
		final String generated = "45a";
		RegexGenerator regGen = getRegGenInstance(new RegExp("[0-5]{5}[a-z][3]"), generated);
		
		assertEquals(regGen.generate(), null);
	}
	
	@Test
	public void subclassGeneratesTooLong(){
		/*
		 * Test that a subclass that generates a string who's prefix is a valid string
		 * return null
		 */
		final String generated = "12345abc67";
		RegexGenerator regGen = getRegGenInstance(new RegExp("[0-5]{5}[a-z][3]"), generated);
		
		assertEquals(regGen.generate(), null);
	}
	
	
	
	public RegexGenerator getRegGenInstance(RegExp constructorParam, final String generates){
		return new RegexGenerator(constructorParam) {
			
			Generator g = new StaticStringGenerator(generates);
			
			@Override
			protected Generator createGeneratorForRegex(RegExp r) {
				return g;
			}
		};
	}
}
