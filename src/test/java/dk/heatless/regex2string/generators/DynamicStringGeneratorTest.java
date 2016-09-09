package dk.heatless.regex2string.generators;

import static org.mockito.Mockito.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import dk.brics.automaton.State;
import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;
import dk.heatless.regex2string.TestUtilities;

public class DynamicStringGeneratorTest {
	
	@Test
	public void initial(){
		/*
		 * Test that after initialization null is generated
		 */
		Generator g = new DynamicStringGenerator();
		
		assertEquals(g.generate(mock(GenerationState.class)), null);
	}
	
	@Test
	public void success(){
		/*
		 * Test that the generator generates what it is configured to
		 * if accepted by the state
		 */
		DynamicStringGenerator g = new DynamicStringGenerator();
		g.setToGenerate("Correct");
		GenerationState mockGenStateStart = TestUtilities.getGenerationStateAcceptingAnything();
		
		assertEquals(g.generate(mockGenStateStart), "Correct");
	}
	
	@Test
	public void fail(){
		/*
		 * Test that the generator returns null, if the state reject what it is configured
		 * to generate
		 * 
		 */
		DynamicStringGenerator g = new DynamicStringGenerator();
		g.setToGenerate("Wrong");
		GenerationState mockGenStateStart = TestUtilities.getGenerationStateFor("Correct");
		
		assertEquals(g.generate(mockGenStateStart), null);
	}
}
