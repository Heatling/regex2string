package dk.heatless.regex2string.conditions;

import static org.mockito.Mockito.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import dk.heatless.regex2string.GenerationState;
import static org.testng.Assert.*;

public class DynamicConditionTest {
	
	@Test
	public void initial(){
		/*
		 * Test that the dependency is null after intialization
		 */
		DynamicCondition<Integer> d = new DynamicCondition<Integer>() {
			
			@Override
			protected boolean acceptDependency(GenerationState state) {
				return getDependency() == null;
			}
		};
		
		assertTrue(d.accept(mock(GenerationState.class)));
	}
	
	@Test
	public void setDependency(){
		/*
		 * Test setting the dependency
		 */
		final Integer i = new Integer(4);
		DynamicCondition<Integer> d = new DynamicCondition<Integer>() {
			
			@Override
			protected boolean acceptDependency(GenerationState state) {
				return getDependency() == i;
			}
		};
		d.setDependency(i);
		assertTrue(d.accept(mock(GenerationState.class)));
	}
	
	@Test
	public void nullGenerationState(){
		/*
		 * Test that accept() return false, if the generation state is null,
		 * even though acceptDependency returns true
		 */
		DynamicCondition<Integer> d = new DynamicCondition<Integer>() {
			
			@Override
			protected boolean acceptDependency(GenerationState state) {
				return true;
			}
		};
		assertFalse(d.accept(null));
	}
	
}
