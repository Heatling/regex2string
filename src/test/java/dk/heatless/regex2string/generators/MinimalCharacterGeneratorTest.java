package dk.heatless.regex2string.generators;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.*;

import dk.brics.automaton.State;
import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;

public class MinimalCharacterGeneratorTest {
	
	MinimalCharacterGenerator g;
	GenerationState genState;
	State initState, mockState ;
	@BeforeMethod
	public void setup(){
		
		g = new MinimalCharacterGenerator();

		
		mockState = mock(State.class);
		initState = new State();
		genState = new GenerationState(initState);
	}
	
	@Test
	public void generateFromSingleSequence(){
		/*
		 * Test that if the state accepts 1 sequence, the minimum is returned.
		 */
		Transition t = new Transition('a', 'z', mockState);
		initState.addTransition(t);
		
		String result = g.generate(genState);
		
		assertEquals(result, "a");
	}
	
	@Test
	public void generateFromTwoSequences(){
		/*
		 * Test that if the state accepts 2 sequences, the minimum of the two is chosen.
		 */
		initState.addTransition(new Transition('B', 'R', mockState));
		initState.addTransition(new Transition('a', 'c', mockState));
		
		String result = g.generate(genState);
		
		assertEquals(result, "B");
	}
	
	@Test
	public void generateFromNoTransitions(){
		/*
		 * Test that if a state has no outgoing transitions, null is returned
		 */
		
		assertEquals(g.generate(genState), null);
	}
	
	
}
