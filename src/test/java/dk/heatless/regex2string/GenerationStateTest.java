package dk.heatless.regex2string;

import static org.mockito.Mockito.*;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.State;
import dk.heatless.regex2string.rules.Rule;
import dk.heatless.regex2string.utilities.StateOperations;

public class GenerationStateTest {
	
	State mockState;
	GenerationState genState;
	Rule rule;
	
	@BeforeMethod
	public void setupTest(){
		
		mockState = mock(State.class);
		when(mockState.step(anyChar())).thenReturn(mockState);
		rule = mock(Rule.class);
		
		genState = new GenerationState(mockState);
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
	public void nullTest(){
		/*
		 * Test that the constructors do not accept null States.
		 */
		try{
			new GenerationState(null);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals(err.getMessage(), "State was null");
		}
		
		try{
			new GenerationState(null, ' ', mock(GenerationState.class));
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals(err.getMessage(), "State was null");
		}
	}
	
	@Test
	public void testGetters(){
		/*
		 * Test getters at init
		 */
		assertTrue(genState.getCurrentState() == mockState);
		assertEquals(genState.getPrevious(), null);
	}
	
	@Test
	public void applyNull(){
		/*
		 * Test that apply() does not accept null
		 */
		try{
			new GenerationState(mockState).apply(null);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals(err.getMessage(), "Generator was null");
		}
	}
	
	@Test
	public void applyRuleSuccessful(){
		/*
		 * Test that can apply a rule that succeeds
		 */
		
		
		String result = "generated string";
		Automaton regex = (new RegExp(result + "some more")).toAutomaton();
		mockState = regex.getInitialState();
		State endState = StateOperations.stringStep(mockState, result);
		genState = new GenerationState(mockState);
		
		
		when(rule.generate(genState)).thenReturn(result);
		
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
		mockState = regex.getInitialState();
		genState = new GenerationState(mockState);
		
		when(rule.generate(genState)).thenReturn(null);
		
		
		
		assertEquals(genState.apply(rule), null);
		
		assertEquals(genState.getGenerated(), "");
		assertTrue(genState.getCurrentState() == mockState);
	}

	@Test
	public void applyRuleMultipleTimes(){
		/*
		 * Test can call applyRule() multiple times, and the result is a summation
		 */
		String 	word1 = "generated ", 
				word2 = "string", 
				result = word1+word2;
		
		when(rule.generate(genState)).thenReturn(word1);
		
		genState = genState.apply(rule);
		
		assertEquals(genState.getGenerated(), word1);
		
		when(rule.generate(genState)).thenReturn(null);
		
		assertEquals(genState.apply(rule), null);
		assertEquals(genState.getGenerated(), word1);
		
		when(rule.generate(genState)).thenReturn(word2);
		
		genState = genState.apply(rule);
		
		assertNotEquals(genState, null);
		assertEquals(genState.getGenerated(), result);
		
	}

	@Test
	public void stepStringNull(){
		/*
		 * Test that step(String) does not accept null
		 */
		try{
			new GenerationState(mockState).step(null);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals(err.getMessage(), "String was null");
		}
	}
	
	@Test
	public void applyStringSuccess(){
		/*
		 * Test can apply a valid string directly
		 */
		String result = "bleh";
		Automaton regex = (new RegExp(result + "some more")).toAutomaton();
		mockState = regex.getInitialState();
		State endState = StateOperations.stringStep(mockState, result);
		genState = new GenerationState(mockState);
		
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
		mockState = regex.getInitialState();
		genState = new GenerationState(mockState);
		
		assertEquals(genState.step(wrongString), null);
		
		assertEquals(genState.getGenerated(), "");
		assertTrue(genState.getCurrentState() == mockState);
	}
	
	@Test
	public void testCompleted(){
		/*
		 * Test that completed() return true when the current state is an acceptance state
		 */
		when(mockState.isAccept()).thenReturn(true);
		
		assertTrue(genState.completed());
		
		when(mockState.isAccept()).thenReturn(false);
		
		assertFalse(genState.completed());
	}

	@Test
	public void testGetLengthOfGeneratedInitial(){
		/*
		 * Test that getLengthOfGenerated returns 0 if the state is initial
		 */
		assertEquals(new GenerationState(mockState).getLengthOfGenerated(), 0);
	}
	
	@Test
	public void testGetLengthOfGeneratedIntermediate(){
		/*
		 * Test that getLengthOfGenerated return the correct length
		 */
		GenerationState currentState = new GenerationState(mockState);
		when(mockState.step(anyChar())).thenReturn(mockState);
		
		String text = "some string";
		currentState = currentState.step(text);
		
		assertEquals(currentState.getLengthOfGenerated(), text.length());
	}

	@Test
	public void testCopyToRootState(){
		/*
		 * Test that copyToRootState() creates a copy with no previous
		 */
		GenerationState o = new GenerationState(mockState, '0', new GenerationState(mockState));
		GenerationState c = o.copyToRootState();
		
		assertTrue(c.getCurrentState() == mockState);
		assertTrue(c.getPrevious() == null);
	}






















}
