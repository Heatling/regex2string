package dk.heatless.regex2string.special;

import static org.mockito.Mockito.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.TestUtilities;
import dk.heatless.regex2string.generators.RepeatGenerator;
import dk.heatless.regex2string.*;

import static org.testng.Assert.*;


public class OptionalGeneratorTest {
	
	@Test
	public void initTest(){
		/*
		 * Test that the constructor does not accept null.
		 */
		try{
			new OptionalGenerator(null);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals("Generator was null", err.getMessage());
		}
	}
	
	@Test
	public void generatorSucceeds(){
		/*
		 * Test that if the given generator returns a string, that is the result.
		 */
		Generator option = TestUtilities.getGenerator("Correct");
		GenerationState state = TestUtilities.getGenerationStateFor("Correct");
		
		OptionalGenerator op = new OptionalGenerator(option);
		
		assertEquals(op.generate(state).getGenerated(), "Correct");
	}
	
	@Test
	public void generatorFails(){
		/*
		 * Test that if the given generator returns null, the empty string is returned.
		 */
		Generator option = TestUtilities.getGenerator("Wrong");
		GenerationState state = TestUtilities.getGenerationStateFor("Correct");
		
		OptionalGenerator op = new OptionalGenerator(option);
		
		assertTrue(op.generate(state) == state);
	}
}
