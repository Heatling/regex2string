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

//Fields
	State mockState;
	GenerationState genState;
	Rule rule;
	
//methods
	
	public State getInitialStateOfRegex(String regex){
		return (new RegExp(regex)).toAutomaton().getInitialState();
	}
	
	
	@BeforeMethod
	public void setup(){
		
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
	public void applySuccessful(){
		/*
		 * Test that can apply success
		 */
		String string = "Correct";
		State startState = getInitialStateOfRegex(string);
		State endState = StateOperations.stringStep(startState, string);
		GenerationState genState = new GenerationState(startState);
				
		assertTrue(genState.getCurrentState() == startState);
		
		Generator generator = TestUtilities.getGenerator(string);
		GenerationState resultState = genState.apply(generator);

		assertEquals(resultState.getGenerated(), string);
		assertTrue(resultState.getCurrentState() == endState);
	}

	@Test
	public void applyUnsuccessfully(){
		/*
		 * Test that if the generator does not generate the correct string, then nothing is generated
		 */
		String string = "Correct";
		State startState = getInitialStateOfRegex(string);
		GenerationState genState = new GenerationState(startState);
				
		assertTrue(genState.getCurrentState() == startState);
		
		Generator generator = TestUtilities.getGenerator("Wrong");
		GenerationState resultState = genState.apply(generator);

		assertEquals(resultState, null);
	}

	@Test
	public void applyRuleMultipleTimes(){
		/*
		 * Test can call apply multiple times, and the result is a summation
		 */
		String 	word1 = "generated ", 
				word2 = "string", 
				result = word1+word2;
		
		Generator generator1 = TestUtilities.getGenerator(word1);
		Generator generator2 = TestUtilities.getGenerator(word2);
		
		State startState = getInitialStateOfRegex(result);
		GenerationState startGenState = new GenerationState(startState);
		
		//1. apply
		GenerationState midGenState = startGenState.apply(generator1);
		assertEquals(midGenState.getGenerated(), word1);
		
		//2. apply
		GenerationState endGenState = midGenState.apply(generator2);
		assertEquals(endGenState.getGenerated(), result);		
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
	public void stepStringSuccess(){
		/*
		 * Test can step a valid string directly
		 */
		String string = "Correct";
		State startState = getInitialStateOfRegex(string);
		State endState = StateOperations.stringStep(startState, string);
		GenerationState genState = new GenerationState(startState);
		
		genState = genState.step(string);
		
		assertEquals(genState.getGenerated(), string);
		assertTrue(genState.getCurrentState() == endState);
	}

	@Test
	public void stepStringFailure(){
		/*
		 * Test will not step an invalid string directly
		 */
		String string = "Correct";
		State startState = getInitialStateOfRegex(string);
		GenerationState genState = new GenerationState(startState);
		
		genState = genState.step("Wrong");
		
		assertEquals(genState, null);
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
		GenerationState currentState = new GenerationState(getInitialStateOfRegex("(.)*"));
		
		String text = "some string";
		currentState = currentState.step(text);
		
		assertEquals(currentState.getLengthOfGenerated(), text.length());
	}
	





















}
