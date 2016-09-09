package dk.heatless.regex2string.generators;

import static org.mockito.Mockito.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.Conditions;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;
import dk.heatless.regex2string.TestUtilities;
import dk.heatless.regex2string.conditions.NotNullCondition;
import dk.heatless.regex2string.utilities.StateOperations;

public class WhileGeneratorTest {
	
	@Test
	public void nullTest(){
		/*
		 * Test that the constructor does not accept null
		 */
		try{
			new WhileGenerator(null, mock(Generator.class));
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals("Condition was null", err.getMessage());
		}
		try{
			new WhileGenerator(mock(Condition.class), null);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals("Generator was null", err.getMessage());
		}
	}
	
	@Test
	public void conditionFail(){
		/*
		 * Test that if the given condition fail the first time, null is returned
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("Yes");
		Generator mockG = TestUtilities.getGenerator("Yes");
		Condition mockC = TestUtilities.getConditionAcceptingAnythingBut(genState);
		
		WhileGenerator g = new WhileGenerator(mockC, mockG);
		
		assertEquals(g.generate(genState), null);
	}
	
	@Test
	public void generatorFial(){
		/*
		 * Test that if the generator fails the first time, null is returned.
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("No");
		Generator mockG = TestUtilities.getGenerator("Yes");
		Condition mockC = TestUtilities.getConditionAccepting(genState);
		
		WhileGenerator g = new WhileGenerator(mockC, mockG);
		
		assertEquals(g.generate(genState), null);
	}
	
	@Test
	public void oneSuccessThenConditionFail(){
		/*
		 * Test that if both succeed the first time, and the condition fails the second time,
		 * the first generation is returned.
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("YesYes");
		Generator mockG = TestUtilities.getGenerator("Yes");
		Condition mockC = Conditions.START_OF_GENERATION;
		
		WhileGenerator g = new WhileGenerator(mockC, mockG);
		
		assertEquals(g.generate(genState), "Yes");
	}
	
	@Test
	public void oneSuccessThenGeneratorFail(){
		/*
		 * Test that if both succeed the first time, and the generator fails the second time,
		 * the first generation is returned.
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("YesNo");
		Generator mockG = TestUtilities.getGenerator("Yes");
		Condition mockC = Conditions.NO_CONDITION;
		
		WhileGenerator g = new WhileGenerator(mockC, mockG);
		
		assertEquals(g.generate(genState), "Yes");
	}
	
	@Test
	public void twoSuccesses(){
		/*
		 * Test that if the condition and generator succeed twice, its accumulated output is returned.
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("YesYes");
		Generator mockG = TestUtilities.getGenerator("Yes");
		Condition mockC = Conditions.NO_CONDITION;
		
		WhileGenerator g = new WhileGenerator(mockC, mockG);
		
		assertEquals(g.generate(genState), "YesYes");
	}
	
	@Test
	public void twoSuccessesThenConditionFail(){
		/*
		 * Test that if the given generator succeeds twice, its accumulated output is returned.
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("YesYesYes");
		Generator mockG = TestUtilities.getGenerator("Yes");
		Condition mockC = new NotNullCondition(){
			private int i = -1;
			@Override
			protected boolean acceptNotNull(GenerationState state) {
				i++;
				return i<2;
			}
			
		};
		
		WhileGenerator g = new WhileGenerator(mockC, mockG);
		
		assertEquals(g.generate(genState), "YesYes");
	}
	
	@Test
	public void twoSuccessesThenGenerationFail(){
		/*
		 * Test that if the given generator succeeds twice, its accumulated output is returned.
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("YesYesNo");
		Generator mockG = TestUtilities.getGenerator("Yes");
		Condition mockC = Conditions.NO_CONDITION;
		
		WhileGenerator g = new WhileGenerator(mockC, mockG);
		
		assertEquals(g.generate(genState), "YesYes");
	}	
	
	@Test
	public void threeSuccesses(){
		/*
		 * Test that if the condition and generator succeed thrice, its accumulated output is returned.
		 */
		GenerationState genState = TestUtilities.getGenerationStateFor("YesYesYes");
		Generator mockG = TestUtilities.getGenerator("Yes");
		Condition mockC = Conditions.NO_CONDITION;
		
		WhileGenerator g = new WhileGenerator(mockC, mockG);
		
		assertEquals(g.generate(genState), "YesYesYes");
	}
}
