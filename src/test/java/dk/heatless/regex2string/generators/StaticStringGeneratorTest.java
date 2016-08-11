package dk.heatless.regex2string.generators;

import static org.mockito.Mockito.*;

import org.testng.annotations.*;
import static org.testng.Assert.*;

import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

public class StaticStringGeneratorTest {
	
	@Test
	public void success(){
		/*
		 * Test that given a valid string, the generator will generate it.
		 */
		String text = "some text";
		GenerationState mockG = mock(GenerationState.class);
		
		when(mockG.step(text)).thenReturn(mockG);
		
		Generator g = new StaticStringGenerator(text);
		
		assertEquals(g.generate(mockG), text);
	}
	
	@Test
	public void failure(){
		/*
		 * Test that given an invalid string, the generator will return null.
		 */
		String text = "some text";
		GenerationState mockG = mock(GenerationState.class);
		
		when(mockG.step(text)).thenReturn(null);
		
		Generator g = new StaticStringGenerator(text);
		
		assertEquals(g.generate(mockG), null);
	}
}
