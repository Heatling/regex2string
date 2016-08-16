package dk.heatless.regex2string.generators;

import java.util.HashSet;
import java.util.Random;

import org.testng.annotations.*;

import dk.brics.automaton.State;
import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;

import static org.testng.Assert.*;

import static org.mockito.Mockito.*;

public class RandomCharacterGeneratorTest {
	
	Random mockRandom ;
	RandomCharacterGenerator g ;
	State mockState ;
	GenerationState genState ;
	
	@BeforeMethod
	public void setup(){
		mockRandom = mock(Random.class);
		g = new RandomCharacterGenerator(mockRandom);
		mockState = mock(State.class);
		genState = new GenerationState(mockState);
	}
	
	@Test
	public void noTransitionsTest(){
		/*
		 * Test that if the state has no outgoing transitions, null is returned.
		 */
		when(mockRandom.nextInt(anyInt())).thenCallRealMethod();
		when(mockState.getTransitions()).thenReturn(new HashSet<Transition>());
		
		assertEquals(g.generate(genState), null);
	}
	
	@Test
	public void oneTransitionMultiChar(){
		/*
		 * Test that if the state has 1 transition, which can choose between multiple chars,
		 * then the chars are chosen randomly
		 */
		HashSet<Transition> s = new HashSet<Transition>();
		s.add(new Transition('0', '9', mockState));
		when(mockState.getTransitions()).thenReturn(s);
		when(mockRandom.nextInt('9'-'0')).thenReturn(3);
		when(mockState.step('3')).thenReturn(mockState);
		
		assertEquals(g.generate(genState), "3");
	}
	
	@Test
	public void oneTransitionSingleChar(){
		/*
		 * Test that if the state has 1 transition, which only has 1 char to choose from, that
		 * char is returned
		 */
		HashSet<Transition> s = new HashSet<Transition>();
		s.add(new Transition('0', '0', mockState));
		when(mockState.getTransitions()).thenReturn(s);
		when(mockRandom.nextInt(1)).thenReturn(0);
		when(mockState.step('0')).thenReturn(mockState);
		
		assertEquals(g.generate(genState), "0");
	}
}
