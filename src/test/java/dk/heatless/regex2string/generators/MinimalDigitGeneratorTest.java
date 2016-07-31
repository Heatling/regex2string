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
	
	@Test
	public void generateFromSingleNumber(){
		/*
		 * Test that if the regular expression accepts only 1 number, that one is returned.
		 */
		char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		Transition t;
		for(char d : digits){
			t = new Transition(d,d,mockState);
			initState = new State();
			initState.addTransition(t);
			genState = new GenerationState(initState);
			
			assertEquals(g.generate(genState), Character.toString(d));
		}
	}
	
	@Test
	public void generateFromTwoChoices(){
		/*
		 * Test that if a state accept 2 digits, the smallest is chosen
		 */
		Transition t1 = new Transition('3', '3', mockState);
		Transition t2 = new Transition('5', '5', mockState);
		initState.addTransition(t1);
		initState.addTransition(t2);
		
		String result = g.generate(genState);
		
		assertEquals(result, "3");
	}
	
	@Test
	public void generateFromNonDigitChoices(){
		/*
		 * Test that non digit transitions are ignored
		 */
		Transition t = new Transition('5', '9', mockState);
		initState.addTransition(t);
		initState.addTransition(new Transition('a','z', mockState));
		
		String result = g.generate(genState);
		
		assertEquals(result, "5");
	}
	
	@Test
	public void generateFromNoDigits(){
		/*
		 * Test that if the state does not accept digits, null is returned
		 */
		Transition t = new Transition('a', 'z', mockState);
		initState.addTransition(t);
		
		String result = g.generate(genState);
		
		assertEquals(result, null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
