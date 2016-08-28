package dk.heatless.regex2string;

import org.testng.annotations.*;

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
		 * Test applicatoinResult() where both conditions succeed
		 */
		GenerationState mockState = mockGenerationStateThatAcceptsAnythingAndReturnsItself();
		
		//precondition must accept the current state
		mockPreAcceptsState(mockState, true);
		
		//Postcondition accepts any resulting state
		mockPostAcceptsState(any(GenerationState.class), true);
		
		//Generator successfully generates a string from the initial state
		String result = "generated string";
		mockGenReturnsForState(mockState, result);
		
		assertEquals(r.generate(mockState), result);
		
	}

	@Test
	public void testgeneratePreconditionFail(){
		/*
		 * Test generate() where the precondition fails
		 */
		GenerationState mockState = mock(GenerationState.class);
		
		mockPreAcceptsState(mockState, false);
		
		assertEquals(r.generate(mockState), null);
	}
	
	@Test
	public void testgeneratePostconditionFail(){
		/*
		 * Test generate() where the postcondition fails
		 */
		GenerationState mockState = mock(GenerationState.class);
		
		mockPreAcceptsState(mockState, true);
		
		//Postcondition reject any resulting state
		mockPostAcceptsState(any(GenerationState.class), false);
		
		//Generator successfully generates a string from the initial state
		String result = "generated string";
		mockGenReturnsForState(mockState, result);
		
		assertEquals(r.generate(mockState), null);
	}
	
	@Test
	public void testgenerateGeneratorFail(){
		/*
		 * Test generate() where the generator fails to generate a string
		 */
		GenerationState mockState = mock(GenerationState.class);
		
		mockPreAcceptsState(mockState, true);
		
		//Generator fails to generate a string
		mockGenReturnsForState(mockState, null);
		
		assertEquals(r.generate(mockState), null);
	}
	
	

//Utility methods
	
	public void mockPreAcceptsState(GenerationState state, boolean accept) {
		when(mockPre.accept(state)).thenReturn(accept);
	}
	
	public void mockPostAcceptsState(GenerationState state, boolean accept) {
		when(mockPost.accept(state)).thenReturn(accept);
	}

	public void mockGenReturnsForState(GenerationState state, String toReturn){
		when(mockGen.generate(state)).thenReturn(toReturn);
	}

	public GenerationState mockGenerationStateThatAcceptsAnythingAndReturnsItself(){
		GenerationState g = mock(GenerationState.class);
		when(g.step(any(String.class))).thenReturn(g);
		when(g.step(null)).thenReturn(g);
		return g;
	}





























































}