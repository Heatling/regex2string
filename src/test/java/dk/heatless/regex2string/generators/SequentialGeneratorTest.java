package dk.heatless.regex2string.generators;

import org.testng.annotations.*;

import dk.brics.automaton.State;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;
import dk.heatless.regex2string.TestUtilities;
import static org.testng.Assert.*;
import static org.mockito.Mockito.*;

public class SequentialGeneratorTest {
	
	@Test
	public void nullTest(){
		/*
		 * Test that the constructor does not accept null
		 */
		try{
			new SequentialGenerator(null);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals("Array was null", err.getMessage());
		}
	}
	
	@Test
	public void emptyArray(){
		/*
		 * Test that if the given array is empty, null is returned.
		 */
		SequentialGenerator g = new SequentialGenerator(new Generator[0]);
		
		assertEquals(g.generate(new GenerationState(mock(State.class))), null);
	}
	
	@Test
	public void singleGeneratorSuccess(){
		/*
		 * Test that if the given array contains a single generator, which succeeds in generating,
		 * then the generated string is returned.
		 */
		Generator mockG = TestUtilities.getGenerator("Yes");
		GenerationState genState = TestUtilities.getGenerationStateFor("Yes");
		SequentialGenerator g = new SequentialGenerator(new Generator[]{mockG});
		
		assertEquals(g.generate(genState).getGenerated(), "Yes");
		
	}

	@Test
	public void singleGeneratorFaliure(){
		/*
		 * Test that if the given array contains a single generator, which fails in generating,
		 * then null is returned.
		 */
		Generator mockG = TestUtilities.getGenerator("Yes");
		GenerationState genState = TestUtilities.getGenerationStateFor("No");
		SequentialGenerator g = new SequentialGenerator(new Generator[]{mockG});
		
		assertEquals(g.generate(genState), null);
	}
	
	@Test
	public void twoGeneratorsSuccess(){
		/*
		 * Test that if the given array contains two generator, which succeed in generating,
		 * then the correct string is returned.
		 */
		Generator 	mockG1 = TestUtilities.getGenerator("1Yes1"),
					mockG2 = TestUtilities.getGenerator("2Yes2");
		GenerationState genState = TestUtilities.getGenerationStateFor("1Yes12Yes2");
		
		SequentialGenerator g = new SequentialGenerator(new Generator[]{mockG1, mockG2});
		
		assertEquals(g.generate(genState).getGenerated(), "1Yes12Yes2");
	}
	
	@Test
	public void twoGeneratorsFirstFail(){
		/*
		 * Test that if the given array contains two generator, where the first fails in generating,
		 * then null is returned.
		 */
		Generator 	mockG1 = TestUtilities.getGenerator("1Yes1"),
					mockG2 = TestUtilities.getGenerator("2Yes2");
		GenerationState genState = TestUtilities.getGenerationStateFor("No2Yes2");
		
		SequentialGenerator g = new SequentialGenerator(new Generator[]{mockG1, mockG2});
		
		assertEquals(g.generate(genState), null);
	}
	
	@Test
	public void twoGeneratorsSecondFail(){
		/*
		 * Test that if the given array contains two generator, where the second fails in generating,
		 * then null is returned.
		 */
		Generator 	mockG1 = TestUtilities.getGenerator("1Yes1"),
					mockG2 = TestUtilities.getGenerator("2Yes2");
		GenerationState genState = TestUtilities.getGenerationStateFor("1Yes1No");
		
		SequentialGenerator g = new SequentialGenerator(new Generator[]{mockG1, mockG2});
		
		assertEquals(g.generate(genState), null);
	}
	
	@Test
	public void multipleGeneratorsSuccess(){
		/*
		 * Test that if the given array contains 5 generator, which succeed in generating,
		 * then the correct string is returned.
		 */
		Generator 	mockG1 = TestUtilities.getGenerator("1Yes1"),
					mockG2 = TestUtilities.getGenerator("2Yes2"),
					mockG3 = TestUtilities.getGenerator("3Yes3"),
					mockG4 = TestUtilities.getGenerator("4Yes4"),
					mockG5 = TestUtilities.getGenerator("5Yes5");
		GenerationState genState = TestUtilities.getGenerationStateFor("1Yes12Yes23Yes34Yes45Yes5");
		
		SequentialGenerator g = new SequentialGenerator(new Generator[]{mockG1, mockG2, mockG3, mockG4, mockG5});
		
		assertEquals(g.generate(genState).getGenerated(), "1Yes12Yes23Yes34Yes45Yes5");
	}
}
