package dk.heatless.regex2string.rules;

import org.testng.annotations.*;

import dk.brics.automaton.State;
import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;
import dk.heatless.regex2string.TestUtilities;
import dk.heatless.regex2string.rules.Rule;

import static org.mockito.Mockito.*;

import static org.testng.Assert.*;

import org.mockito.ArgumentMatcher;

public class RuleTest {
	Condition mockPre, mockPost;
	Generator mockGen;
	
	Rule r;
	

	class NotObject extends ArgumentMatcher<GenerationState>{
		
		private Object o;
		public NotObject(Object o){
			this.o = o;
		}
		
		@Override
		public boolean matches(Object argument) {
			return o != argument;
		}
		
	}
	
		
	@BeforeMethod
	public void setup(){
		mockPre = mock(Condition.class);
		mockPost = mock(Condition.class);
		mockGen = mock(Generator.class);
		
		
		r = new Rule(mockPre, mockPost, mockGen);
	}
	
	@Test
	public void NullTest(){
		/*
		 * Test that the constructor does not accept null arguments
		 */
		try{
			new Rule(null, mockPost, mockGen);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals(err.getMessage(), "Precondition was null");
		}
		
		try{
			new Rule(mockPre, null, mockGen);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals(err.getMessage(), "Postcondition was null");
		}
		
		try{
			new Rule(mockPre, mockPost, null);
			fail("Should throw IllegalArgumentException");
		}catch(IllegalArgumentException err){
			assertEquals(err.getMessage(), "Generator was null");
		}
	}
	
	@Test
	public void testGetters(){
		/*
		 * Test getters
		 */
		assertTrue(r.getPrecondition() == mockPre);
		assertTrue(r.getPostcondition() == mockPost);
		assertTrue(r.getGenerator() == mockGen);
	}
	
	@Test
	public void testgenerateSuccess(){
		/*
		 * Test generate() where both conditions succeed
		 */
		GenerationState mockState = TestUtilities.getGenerationStateAcceptingAnything();
		
		//precondition must accept the current state
		Condition 	preCon = TestUtilities.getConditionAccepting(mockState),
		//Postcondition accepts any resulting state
		postCon = TestUtilities.getConditionAcceptingAnythingBut(null);
		
		//Generator successfully generates a string from the initial state
		String result = "generated string";
		Generator g = TestUtilities.getGenerator(result);
		
		r = new Rule(preCon, postCon, g);
		assertEquals(r.generate(mockState).getGenerated(), result);	
	}

	@Test
	public void testgeneratePreconditionFail(){
		/*
		 * Test generate() where the precondition fails
		 */
		GenerationState mockState = TestUtilities.getGenerationStateAcceptingAnything();
		
		//precondition cant accept the current state
		Condition 	preCon = TestUtilities.getConditionAcceptingAnythingBut(mockState),
				
		//Postcondition accepts any resulting state
		postCon = TestUtilities.getConditionAcceptingAnythingBut(null);
		
		//Generator successfully generates a string from the initial state
		String result = "generated string";
		Generator g = TestUtilities.getGenerator(result);
		
		r = new Rule(preCon, postCon, g);
		assertEquals(r.generate(mockState), null);	
	}
	
	@Test
	public void testgeneratePostconditionFail(){
		/*
		 * Test generate() where the postcondition fails
		 */
		GenerationState mockState = TestUtilities.getGenerationStateAcceptingAnything();
		
		//precondition must accept the current state
		Condition 	preCon = TestUtilities.getConditionAccepting(mockState),
				
		//Postcondition doesn't accept any resulting state
		postCon = TestUtilities.getConditionAccepting(null);
		
		//Generator successfully generates a string from the initial state
		String result = "generated string";
		Generator g = TestUtilities.getGenerator(result);
		
		r = new Rule(preCon, postCon, g);
		assertEquals(r.generate(mockState), null);	
	}
	
	@Test
	public void testgenerateGeneratorFail(){
		/*
		 * Test generate() where the generator fails to generate a string
		 */
		GenerationState mockState = TestUtilities.getGenerationStateFor("Correct");
		
		//precondition must accept the current state
		Condition 	preCon = TestUtilities.getConditionAccepting(mockState),
				
		//Postcondition accepts any resulting state
		postCon = TestUtilities.getConditionAcceptingAnythingBut(null);
		
		//Generator fails in generating
		Generator g = TestUtilities.getGenerator("Wrong");
		
		r = new Rule(preCon, postCon, g);
		assertEquals(r.generate(mockState), null);	
	}
	




























































}