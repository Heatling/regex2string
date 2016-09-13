package dk.heatless.regex2string.generators;

import static org.mockito.Mockito.*;

import org.testng.annotations.*;

import static org.testng.Assert.*;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;
import dk.heatless.regex2string.TestUtilities;

public class StaticStringGeneratorTest {
	
	@Test
	public void initTest(){
		/*
		 * Test that the constructor does not take null or the empty string
		 */
		try{
			new StaticStringGenerator(null);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals(err.getMessage(), "String was null");
		}
		try{
			new StaticStringGenerator("");
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals(err.getMessage(), "String was empty");
		}
	}
	
	@Test
	public void success(){
		/*
		 * Test that given a valid string, the generator will generate it.
		 */
		String text = "some text";
		GenerationState mockG = TestUtilities.getGenerationStateFor(text);
		
		Generator g = new StaticStringGenerator(text);
		
		assertEquals(g.generate(mockG).getGenerated(), text);
	}
	
	@Test
	public void failure(){
		/*
		 * Test that given an invalid string, the generator will return null.
		 */
		String text = "some text";
		GenerationState mockG = TestUtilities.getGenerationStateFor("not the same text");
				
		Generator g = new StaticStringGenerator(text);
		
		assertEquals(g.generate(mockG), null);
	}
}
