package dk.heatless.regex2string.generators;

import static org.mockito.Mockito.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import dk.brics.automaton.State;
import dk.brics.automaton.Transition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.TestUtilities;

public class MinimalDigitGeneratorTest {
	
//Fields
	MinimalDigitGenerator g = new MinimalDigitGenerator();
	GenerationState genState;
	
//methods
	public void assertSequence(char start, char end, String result){
		genState = TestUtilities.getGenerationStateFor("["+start+"-"+end+"]");
		if(result == null){
			assertEquals(g.generate(genState), null);
		}else{
			assertEquals(g.generate(genState).getGenerated(), result);
		}
		
	}
	
//Test
	
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
