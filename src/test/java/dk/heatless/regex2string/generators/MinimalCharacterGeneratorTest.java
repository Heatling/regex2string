package dk.heatless.regex2string.generators;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.*;

import dk.brics.automaton.State;
import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.TestUtilities;

public class MinimalCharacterGeneratorTest {
	
	MinimalCharacterGenerator g = new MinimalCharacterGenerator();
	GenerationState genState;
	
	@Test
	public void generateFromSingleSequence(){
		/*
		 * Test that if the state accepts 1 sequence, the minimum is returned.
		 */
		genState = TestUtilities.getGenerationStateFor("[a-z]");
		String result = g.generate(genState);
		
		assertEquals(result, "a");
	}
	
	@Test
	public void generateFromTwoSequences(){
		/*
		 * Test that if the state accepts 2 sequences, the minimum of the two is chosen.
		 */
		genState = TestUtilities.getGenerationStateFor("([B-R]|[a-c])");
		
		String result = g.generate(genState);
		
		assertEquals(result, "B");
	}
	
	@Test
	public void generateFromNoTransitions(){
		/*
		 * Test that if a state accepts no string
		 */
		genState = TestUtilities.getGenerationStateFor("");
		assertEquals(g.generate(genState), null);
	}
	
	
}
