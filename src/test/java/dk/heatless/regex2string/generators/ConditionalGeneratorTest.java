package dk.heatless.regex2string.generators;

import static org.mockito.Mockito.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;
import dk.heatless.regex2string.TestUtilities;
import static org.testng.Assert.*;

public class ConditionalGeneratorTest {
	
	@Test
	public void nullTest(){
		/*
		 * Test that the constructor does not accept null
		 */
		try{
			new ConditionalGenerator(null, mock(Generator.class),mock(Generator.class));
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals("Condition was null", err.getMessage());
		}
		
		try{
			new ConditionalGenerator(mock(Condition.class), null,mock(Generator.class));
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals("Generator 'a' was null", err.getMessage());
		}
		
		try{
			new ConditionalGenerator(mock(Condition.class), mock(Generator.class),null);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals("Generator 'b' was null", err.getMessage());
		}
	}
	
	@Test
	public void trueCondition(){
		/*
		 * Test that when the condition is true, generator a is used.
		 */
		GenerationState mockGenState = TestUtilities.getGenerationStateFor("(.)*");
		Generator 	a = TestUtilities.getGenerator("aGa"), 
					b = TestUtilities.getGenerator("bGb");
		
		
		Condition trueC = TestUtilities.getConditionAccepting(mockGenState);
		
		ConditionalGenerator ConGen = new ConditionalGenerator(trueC, a, b);
		assertEquals(ConGen.generate(mockGenState).getGenerated(), "aGa");
	}
	
	@Test
	public void falseCondition(){
		/*
		 * Test that when the condition is false, generator b is used.
		 */
		GenerationState mockGenState = TestUtilities.getGenerationStateFor("(.)*");
		Generator 	a = TestUtilities.getGenerator("aGa"), 
					b = TestUtilities.getGenerator("bGb");
		
		
		Condition falseC = TestUtilities.getConditionAcceptingAnythingBut(mockGenState);
		
		ConditionalGenerator ConGen = new ConditionalGenerator(falseC, a, b);
		assertEquals(ConGen.generate(mockGenState).getGenerated(), "bGb");
	}
	
}
