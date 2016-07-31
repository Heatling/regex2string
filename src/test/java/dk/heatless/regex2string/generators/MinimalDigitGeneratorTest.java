package dk.heatless.regex2string.generators;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import dk.brics.automaton.State;
import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;

public class MinimalDigitGeneratorTest {
	
	
	MinimalDigitGenerator g;
	GenerationState genState;
	State initState, mockState ;
	@BeforeMethod
	public void setup(){
		
		g = new MinimalDigitGenerator();

		
		mockState = mock(State.class);
		initState = new State();
		genState = new GenerationState(initState);
	}
	
	@Test
	public void generateFromAllNumbers(){
		/*
		 * Test that if the regular expression accepts all numbers, 0 is returned.
		 */
		//Create transition set that accepts 0..9
		Transition t = new Transition('0', '9', mockState);
		initState.addTransition(t);
		
		String result = g.generate(genState);
		
		assertEquals(result, "0");
	}
	
}
