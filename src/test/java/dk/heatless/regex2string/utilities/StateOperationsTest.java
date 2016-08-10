package dk.heatless.regex2string.utilities;

import org.testng.annotations.*;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.State;
import dk.heatless.regex2string.GenerationState;

import static org.testng.Assert.*;

import static org.mockito.Mockito.*;
public class StateOperationsTest {
	
//stringStep(State, String)
	@Test
	public void stringStepNullArguments(){
		/*
		 * Test that the stringStep method does not accept null
		 */
		try{
			StateOperations.stringStep(null, "");
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals(err.getMessage(), "State was null");
		}
		try{
			StateOperations.stringStep(mock(State.class), null);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals(err.getMessage(), "String was null");
		}
	}
	
	@Test
	public void StringStepEmptyString(){
		/*
		 * Test that the stringStep method returns the same state
		 * if the given string is empty
		 */
		State s = mock(State.class);
		
		assertTrue(StateOperations.stringStep(s, "") == s);
	}
	
	@Test
	public void StringStepSingleChar(){
		/*
		 * Test that the stringStep method returns the right state
		 * when the string is only 1 character long
		 */
		State s1 = mock(State.class);
		State s2 = mock(State.class);
		when(s1.step('c')).thenReturn(s2);
		
		assertTrue(StateOperations.stringStep(s1, "c") == s2);
	}
	
	@Test
	public void StringStepMultiChar(){
		/*
		 * Test that the stringStep method returns the right state
		 * when the string is more than 1 character long
		 */
		State s1 = mock(State.class);
		State s2 = mock(State.class);
		State s3 = mock(State.class);
		State s4 = mock(State.class);
		when(s1.step('c')).thenReturn(s2);
		when(s2.step('a')).thenReturn(s3);
		when(s3.step('n')).thenReturn(s4);
		
		assertTrue(StateOperations.stringStep(s1, "can") == s4);
	}
	
	@Test
	public void StringStepWrongChar(){
		/*
		 * Test that the stringStep method returns null, if the
		 * string does not match the states
		 */
		State s1 = mock(State.class);
		State s2 = mock(State.class);
		State s3 = mock(State.class);
		State s4 = mock(State.class);
		when(s1.step('c')).thenReturn(s2);
		when(s2.step('a')).thenReturn(s3);
		when(s3.step('n')).thenReturn(s4);
		
		assertTrue(StateOperations.stringStep(s1, "cam") == null);
	}
	
//StringStep(State, String, int, int)
	@Test
	public void longStringStepNullArguments(){
		/*
		 * Test that the stringStep method does not accept null
		 * or invalid arguments
		 */
		try{
			StateOperations.stringStep(null, "c", 0, 0);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals(err.getMessage(), "State was null");
		}
		try{
			StateOperations.stringStep(mock(State.class), null, 0, 0);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals(err.getMessage(), "String was null");
		}
	}
	
	@Test
	public void longStringStepEmptyString(){
		/*
		 * Test that the stringStep method returns the same state
		 * if the given string is empty
		 */
		State s = mock(State.class);
		
		assertTrue(StateOperations.stringStep(s, "", 0, 0) == s);
	}
	
	@Test
	public void longStringStepSingleChar(){
		/*
		 * Test that the stringStep method returns the right state
		 * when the string is only 1 character long
		 */
		State s1 = mock(State.class);
		State s2 = mock(State.class);
		when(s1.step('c')).thenReturn(s2);
		
		assertTrue(StateOperations.stringStep(s1, "c", 0,1) == s2);
	}
	
	@Test
	public void longStringStepMultiChar(){
		/*
		 * Test that the stringStep method returns the right state
		 * when the string is more than 1 character long
		 */
		State s1 = mock(State.class);
		State s2 = mock(State.class);
		State s3 = mock(State.class);
		State s4 = mock(State.class);
		when(s1.step('c')).thenReturn(s2);
		when(s2.step('a')).thenReturn(s3);
		when(s3.step('n')).thenReturn(s4);
		
		assertTrue(StateOperations.stringStep(s1, "can", 0, 3) == s4);
	}
	
	@Test
	public void longStringStepWrongChar(){
		/*
		 * Test that the stringStep method returns null, if the
		 * string does not match the states
		 */
		State s1 = mock(State.class);
		State s2 = mock(State.class);
		State s3 = mock(State.class);
		State s4 = mock(State.class);
		when(s1.step('c')).thenReturn(s2);
		when(s2.step('a')).thenReturn(s3);
		when(s3.step('n')).thenReturn(s4);
		
		assertTrue(StateOperations.stringStep(s1, "cam", 0, 3) == null);
	}
	
	@Test
	public void longStringStepBounded(){
		/*
		 * Test that the stringStep method returns the right state
		 * when the string is bounded by the indeces
		 */
		State s1 = mock(State.class);
		State s2 = mock(State.class);
		State s3 = mock(State.class);
		State s4 = mock(State.class);
		when(s1.step('c')).thenReturn(s2);
		when(s2.step('a')).thenReturn(s3);
		when(s3.step('n')).thenReturn(s4);
		
		assertTrue(StateOperations.stringStep(s1, "boundcanbound", 5, 8) == s4);
	}
	
	@Test
	public void longStringStepBoundedWrong(){
		/*
		 * Test that the stringStep method returns null
		 * when the string is bounded in a non-matching way
		 */
		State s1 = mock(State.class);
		State s2 = mock(State.class);
		State s3 = mock(State.class);
		State s4 = mock(State.class);
		when(s1.step('c')).thenReturn(s2);
		when(s2.step('a')).thenReturn(s3);
		when(s3.step('n')).thenReturn(s4);
		
		assertTrue(StateOperations.stringStep(s1, "boundcanbound", 1, 5) == null);
	}

//getInitialGenerationState(RegExp)
	@Test
	public void getInitialGenerationStateTest(){
		/*
		 * Test that the getInitialGenerationState method returns
		 * the right State
		 */
		RegExp r = mock(RegExp.class);
		Automaton a = mock(Automaton.class);
		State s = mock(State.class);
		
		when(r.toAutomaton()).thenReturn(a);
		when(a.getInitialState()).thenReturn(s);
		
		
		GenerationState result = StateOperations.getInitialGenerationState(r);
		
		assertTrue(result.getCurrentState() == s);
	}
	
	
	
	
	
	
	
	
	
	
}
