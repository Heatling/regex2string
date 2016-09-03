package dk.heatless.regex2string.generators;

import static org.mockito.Mockito.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import dk.brics.automaton.State;
import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;

public class RepeatGeneratorTest {
	
	@Test
	public void generateFromFinalState(){
		/*
		 * Test that if generate() is called with a state that is final (does not accept any chars)
		 * then null is returned.
		 */
		State state = mock(State.class);
		when(state.step(anyChar())).thenReturn(null);
		
		GenerationState gT = new GenerationState(state);
		RepeatGenerator rG = new RepeatGenerator(new StaticStringGenerator("0"));
		
		assertEquals(rG.generate(gT), null);
	}
	
}
