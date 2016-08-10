package dk.heatless.regex2string.generators;

import static org.mockito.Mockito.mock;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import dk.brics.automaton.State;
import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;

public class MinimalDigitGeneratorTest {
	
	
	MinimalDigitGenerator g;
	GenerationState genState;
	State initState, mockState ;
	@BeforeMethod
	public void setup(){
		
		g = new MinimalDigitGenerator();

		
		mockState = mock(State.class);
		initState = new State();
		genState = new GenerationState(initState);
	}
	
	@Test
	public void generateFromSequence1(){
		assertSequence('(',')', null);
	}
	@Test
	public void generateFromSequence2(){
		assertSequence('(','0', "0");
	}
	@Test
	public void generateFromSequence3(){
		assertSequence('(','5', "0");
	}
	@Test
	public void generateFromSequence4(){
		assertSequence('(','9', "0");
	}
	@Test
	public void generateFromSequence5(){
		assertSequence('(','l', "0");
	}
	@Test
	public void generateFromSequence6(){
		assertSequence('0','6', "0");
	}
	@Test
	public void generateFromSequence7(){
		assertSequence('0','9', "0");
	}
	@Test
	public void generateFromSequence8(){
		assertSequence('0','s', "0");
	}
	@Test
	public void generateFromSequence9(){
		assertSequence('1','3', "1");
	}
	@Test
	public void generateFromSequence10(){
		assertSequence('1','9', "1");
	}
	@Test
	public void generateFromSequence11(){
		assertSequence('3','q', "3");
	}
	@Test
	public void generateFromSequence12(){
		assertSequence('9','q', "9");
	}
	@Test
	public void generateFromSequence13(){
		assertSequence('A','q', null);
	}
	
	public void assertSequence(char start, char end, String result){
		Transition t = new Transition(start, end , mockState);
		initState.addTransition(t);
		assertEquals(g.generate(genState), result);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
