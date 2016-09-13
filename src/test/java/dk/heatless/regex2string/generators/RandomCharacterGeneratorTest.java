package dk.heatless.regex2string.generators;

import java.util.HashSet;
import java.util.Random;

import org.testng.annotations.*;

import dk.brics.automaton.State;
import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.TestUtilities;
import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class RandomCharacterGeneratorTest {
	
	Random mockRandom ;
	RandomCharacterGenerator g ;
	GenerationState genState ;
	
	@BeforeMethod
	public void setup(){
		mockRandom = mock(Random.class);
		g = new RandomCharacterGenerator(mockRandom);
	}
	
	@Test
	public void noTransitionsTest(){
		/*
		 * Test that if the state does not accept anything, null is returned.
		 */
		genState = TestUtilities.getGenerationStateFor("");
		assertEquals(g.generate(genState), null);
	}
	
	@Test
	public void oneTransitionMultiChar(){
		/*
		 * Test that if the state has 1 transition, which can choose between multiple chars,
		 * then the chars are chosen randomly
		 */
		when(mockRandom.nextInt('9'-'0')).thenReturn(3);
		
		genState = TestUtilities.getGenerationStateFor("[0-9]");
		assertEquals(g.generate(genState).getGenerated(), "3");
	}
	
	@Test
	public void oneTransitionSingleChar(){
		/*
		 * Test that if the state has 1 transition, which only has 1 char to choose from, that
		 * char is returned
		 */
		when(mockRandom.nextInt(1)).thenReturn(0);
		
		genState = TestUtilities.getGenerationStateFor("0");
		assertEquals(g.generate(genState).getGenerated(), "0");
	}
}
