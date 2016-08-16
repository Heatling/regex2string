package dk.heatless.regex2string;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import dk.brics.automaton.State;

import static org.mockito.Mockito.*;

public class ConditionsTest {

	@Test
	public void START_OF_GENERATION_root(){
		/*
		 * Test that the START_OF_GENERATION condition accepts
		 * a root GenerationState
		 */
		GenerationState g = new GenerationState(mock(State.class));
		
		assertTrue(Conditions.START_OF_GENERATION.accept(g));
	}
	
	@Test
	public void START_OF_GENERATION_nonRoot(){
		/*
		 * Test that the START_OF_GENERATION condition does not
		 * accept a non root
		 */
		GenerationState root = new GenerationState(mock(State.class));
		GenerationState g = new GenerationState(mock(State.class), ' ' , root);
		
		assertFalse(Conditions.START_OF_GENERATION.accept(g));
	}
	
	@Test
	public void START_OF_GENERATION_null(){
		/*
		 * Test that the START_OF_GENERATION condition does not accept
		 * a null
		 */
		assertFalse(Conditions.START_OF_GENERATION.accept(null));
	}

	@Test
	public void No_CONDITION_success(){
		/*
		 * Test that NO_CONDITION condition accepts any state
		 */
		assertTrue(Conditions.NO_CONDITION.accept(mock(GenerationState.class)));
	}
	
	@Test
	public void NO_CONDITION_null(){
		/*
		 * Test that NO_CONDITION condition does not accept null.
		 */
		assertFalse(Conditions.NO_CONDITION.accept(null));
	}
}
