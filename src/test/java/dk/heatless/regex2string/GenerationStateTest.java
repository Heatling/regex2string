package dk.heatless.regex2string;

import static org.mockito.Mockito.*;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.State;
import dk.heatless.regex2string.utilities.StateOperations;

public class GenerationStateTest {
	
	State currentState;
	GenerationState genState;
	Rule rule;
	
	@BeforeMethod
	public void setupTest(){
		
		currentState = mock(State.class);
		when(currentState.step(anyChar())).thenReturn(currentState);
		rule = mock(Rule.class);
		
		genState = new GenerationState(currentState);
	}
	
	@Test
	public void initialTest(){
		/*
		 * Test initial configuration
		 */
		assertFalse(genState.completed());
		assertEquals(genState.getGenerated(), "");
	}
	
	@Test
	public void testGetters(){
		/*
		 * Test getters at init
		 */
		assertTrue(genState.getCurrentState() == currentState);
		assertEquals(genState.getPrevious(), null);
	}

	@Test
	public void applyRuleSuccessful(){
		/*
		 * Test that can apply a rule that succeeds
		 */
		
		
		String result = "generated string";
		Automaton regex = (new RegExp(result + "some more")).toAutomaton();
		currentState = regex.getInitialState();
		State endState = StateOperations.stringStep(currentState, result);
		genState = new GenerationState(currentState);
		
		
		when(rule.applicationResult(genState)).thenReturn(result);
		
		genState = genState.apply(rule);
		
		assertNotEquals(genState, null);
		
		assertEquals(genState.getGenerated(), "generated string");
		assertTrue(genState.getCurrentState() == endState);
	}

	@Test
	public void applyRuleUnsuccessfully(){
		/*
		 * Test that if the rule does not accept the state, then nothing is generated
		 */
		
		String result = "generated string";
		Automaton regex = (new RegExp(result + "some more")).toAutomaton();
		currentState = regex.getInitialState();
		genState = new GenerationState(currentState);
		
		when(rule.applicationResult(genState)).thenReturn(null);
		
		
		
		assertEquals(genState.apply(rule), null);
		
		assertEquals(genState.getGenerated(), "");
		assertTrue(genState.getCurrentState() == currentState);
	}

	@Test
	public void applyRuleMultipleTimes(){
		/*
		 * Test can call applyRule() multiple times, and the result is a summation
		 */
		String 	word1 = "generated ", 
				word2 = "string", 
				result = word1+word2;
		
		when(rule.applicationResult(genState)).thenReturn(word1);
		
		genState = genState.apply(rule);
		
		assertEquals(genState.getGenerated(), word1);
		
		when(rule.applicationResult(genState)).thenReturn(null);
		
		assertEquals(genState.apply(rule), null);
		assertEquals(genState.getGenerated(), word1);
		
		when(rule.applicationResult(genState)).thenReturn(word2);
		
		genState = genState.apply(rule);
		
		assertNotEquals(genState, null);
		assertEquals(genState.getGenerated(), result);
		
	}

	@Test
	public void applyStringSuccess(){
		/*
		 * Test can apply a valid string directly
		 */
		String result = "bleh";
		Automaton regex = (new RegExp(result + "some more")).toAutomaton();
		currentState = regex.getInitialState();
		State endState = StateOperations.stringStep(currentState, result);
		genState = new GenerationState(currentState);
		
		genState = genState.step(result);
		
		assertNotEquals(genState, null);
		
		assertEquals(genState.getGenerated(), result);
		assertTrue(genState.getCurrentState() == endState);
	}

	@Test
	public void applyStringFailure(){
		/*
		 * Test will not apply an invalid string directly
		 */
		String correctString = "bleh";
		String wrongString = "wrong";
		Automaton regex = (new RegExp(correctString + "some more")).toAutomaton();
		currentState = regex.getInitialState();
		genState = new GenerationState(currentState);
		
		assertEquals(genState.step(wrongString), null);
		
		assertEquals(genState.getGenerated(), "");
		assertTrue(genState.getCurrentState() == currentState);
	}
	
	@Test
	public void testCompleted(){
		/*
		 * Test that completed() return true when the current state is an acceptance state
		 */
		when(currentState.isAccept()).thenReturn(true);
		
		assertTrue(genState.completed());
		
		when(currentState.isAccept()).thenReturn(false);
		
		assertFalse(genState.completed());
	}


























}
