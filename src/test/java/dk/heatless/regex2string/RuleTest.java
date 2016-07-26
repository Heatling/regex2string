package dk.heatless.regex2string;

import org.testng.annotations.*;

import dk.heatless.regex2string.core.Generator;

import static org.mockito.Mockito.*;

import static org.testng.Assert.*;

import org.mockito.ArgumentMatcher;
import org.mockito.Matchers;

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
	public void setupTest(){
		mockPre = mock(Condition.class);
		mockPost = mock(Condition.class);
		mockGen = mock(Generator.class);
		
		
		r = new Rule(mockPre, mockPost, mockGen);
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
	public void testApplicationResultSuccess(){
		/*
		 * Test applicatoinResult() where both conditions succeed
		 */
		GenerationState mockState = mock(GenerationState.class);
		
		//precondition must accept the current state
		when(mockPre.accept(mockState)).thenReturn(true);
		
		//Postcondition accepts any resulting state
		when(mockPost.accept(any(GenerationState.class))).thenReturn(true);
		
		String result = "generated string";
		when(mockGen.generate()).thenReturn(result);
		
		assertEquals(r.applicationResult(mockState), result);
		
	}

	@Test
	public void testApplicationResultPreconditionFail(){
		/*
		 * Test applicationResult() where the precondition fails
		 */
		GenerationState mockState = mock(GenerationState.class);
		
		when(mockPre.accept(mockState)).thenReturn(false);
		
		assertEquals(r.applicationResult(mockState), null);
	}
	
	@Test
	public void testApplicationResultPostconditionFail(){
		/*
		 * Test applicationResult() where the postcondition fails
		 */
		GenerationState mockState = mock(GenerationState.class);
		
		//precondition must accept the current state
		when(mockPre.accept(mockState)).thenReturn(true);
		
		//Postcondition reject any resulting state
		when(mockPost.accept(any(GenerationState.class))).thenReturn(false);
		
		String result = "generated string";
		when(mockGen.generate()).thenReturn(result);
		
		assertEquals(r.applicationResult(mockState), null);
	}
	
	@Test
	public void testApplicationResultPostConditionFailsOnTempState(){
		/*
		 * Test that the postcondition does not accept the temporary state created
		 * by temporarily applying the generated.
		 */
		GenerationState mockState = mock(GenerationState.class);
		GenerationState mockTempState = mock(GenerationState.class);
		
		//precondition must accept the current state
		when(mockPre.accept(mockState)).thenReturn(true);
		
		//Postcondition reject any resulting state
		when(mockPost.accept(mockTempState)).thenReturn(false);
				
		when(mockPost.accept(argThat(new NotObject(mockTempState)))).thenReturn(true);
		
		String result = "generated string";
		when(mockGen.generate()).thenReturn(result);
		
		//Using apply() on the current state returns a temporary state
		when(mockState.step(result)).thenReturn(mockTempState);
		
		assertEquals(r.applicationResult(mockState), null);
	}
	
	//@Test
	public void testApplicationResultPostConditionAcceptsTempState(){
		/*
		 * Test that the postcondition does not accept the temporary state created
		 * by temporarily applying the generated.
		 */
		GenerationState mockState = mock(GenerationState.class);
		GenerationState mockTempState = mock(GenerationState.class);
		
		//precondition must accept the current state
		when(mockPre.accept(mockState)).thenReturn(true);
		
		//Postcondition reject any resulting state
		when(mockPost.accept(mockTempState)).thenReturn(true);
				
		when(mockPost.accept(argThat(new NotObject(mockTempState)))).thenReturn(false);
		
		String result = "generated string";
		when(mockGen.generate()).thenReturn(result);
		
		//Using apply() on the current state returns a temporary state
		when(mockState.step(result)).thenReturn(mockTempState);
		
		assertEquals(r.applicationResult(mockState), result);
	}
	








































































}