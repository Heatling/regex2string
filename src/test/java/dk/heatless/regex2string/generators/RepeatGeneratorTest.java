package dk.heatless.regex2string.generators;

import static org.mockito.Mockito.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;
import dk.heatless.regex2string.TestUtilities;
import dk.heatless.regex2string.utilities.StateOperations;

public class RepeatGeneratorTest {
	
	@Test
	public void nullTest(){
		/*
		 * Test that the constructor does not accept null
		 */
		try{
			new RepeatGenerator(null);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals("Generator was null", err.getMessage());
		}
	}
	
	@Test
	public void noSuccess(){
		/*
		 * Test that if the given generator never succeeds, null is returned.
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("No");
		Generator mockG = TestUtilities.getGenerator("Yes");
		
		RepeatGenerator g = new RepeatGenerator(mockG);
		
		assertEquals(g.generate(genState), null);
	}
	
	@Test
	public void emptyRegex(){
		/*
		 * Test that if the regex is empty, null is returned.
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("");
		Generator mockG = TestUtilities.getGenerator("Yes");
		
		RepeatGenerator g = new RepeatGenerator(mockG);
		
		assertEquals(g.generate(genState), null);
	}
	
	@Test
	public void oneSuccess(){
		/*
		 * Test that if the given generator succeeds only once, its output is returned.
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("Yes");
		Generator mockG = TestUtilities.getGenerator("Yes");
				
		RepeatGenerator g = new RepeatGenerator(mockG);
		
		assertEquals(g.generate(genState), "Yes");
	}
	
	@Test
	public void oneSuccessThenFail(){
		/*
		 * Test that if the given generator succeeds only once, its output is returned.
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("YesNo");
		Generator mockG = TestUtilities.getGenerator("Yes");
				
		RepeatGenerator g = new RepeatGenerator(mockG);
		
		assertEquals(g.generate(genState), "Yes");
	}
	
	@Test
	public void twoSuccesses(){
		/*
		 * Test that if the given generator succeeds twice, its accumulated output is returned.
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("YesYes");
		Generator mockG = TestUtilities.getGenerator("Yes");
		
		RepeatGenerator g = new RepeatGenerator(mockG);
		
		assertEquals(g.generate(genState), "YesYes");
	}
	
	@Test
	public void twoSuccessesThenFail(){
		/*
		 * Test that if the given generator succeeds twice, its accumulated output is returned.
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("YesYesNo");
		Generator mockG = TestUtilities.getGenerator("Yes");
		
		RepeatGenerator g = new RepeatGenerator(mockG);
		
		assertEquals(g.generate(genState), "YesYes");
	}	
}
