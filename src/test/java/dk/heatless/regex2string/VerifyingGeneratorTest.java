package dk.heatless.regex2string;

import static org.testng.Assert.*;
import org.testng.annotations.*;

import static org.mockito.Mockito.*;

import dk.heatless.regex2string.generators.VerifiedGenerator;

public class VerifyingGeneratorTest {
	
	@Test
	public void subclassGeneratesValid(){
		/*
		 * Test that a subclass that generates a valid string also returns it correctly
		 */
		final String generated = "12345";
		GenerationState mockState = mock(GenerationState.class);
		when(mockState.step(generated)).thenReturn(mockState);
		
		VerifiedGenerator vGen = getVerifyingGeneratorInstance(generated);
				
		assertEquals(vGen.generate(mockState), generated);
	}
	
	@Test
	public void subclassGeneratesPrefix(){
		/*
		 *Test that a subclass that generates an invalid string return null.
		 */
		final String generated = "12345";
		GenerationState mockState = mock(GenerationState.class);
		when(mockState.step(generated)).thenReturn(null);
		
		VerifiedGenerator vGen = getVerifyingGeneratorInstance(generated);
				
		assertEquals(vGen.generate(mockState), null);
	}
	
	
	
	
	public VerifiedGenerator getVerifyingGeneratorInstance(final String generates){
		return new VerifiedGenerator() {

			@Override
			protected String generateUnverified(GenerationState state) {
				// TODO Auto-generated method stub
				return generates;
			}
		};
	}
}
