package dk.heatless.regex2string.generators;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;


/**
 * A generator that uses a {@link Condition} to choose between which given generator to 
 * use for generating a string.<br>
 */
public class PriorityGenerator implements Generator{
	
//Fields
	private Condition selector;
	private Generator a, b;
	
//Constructors
	public PriorityGenerator(Condition selector, Generator a, Generator b ){
		this.selector = selector;
		this.a = a;
		this.b = b;
	}
	
//Methods
	@Override
	public GenerationState generate(GenerationState state){
		GenerationState result;
		boolean start = selector.accept(state);
		Generator tempR;
		//try once for each generator
		for(int i = 0; i<2; i++){
			tempR = select(start);
			result = tempR.generate(state);
			if(result != null){
				return result;
			}
			//Else generator inapplicable, try the other one.
			start = !start;
		}
		//Both generators failed
		return null;
	}
	
//Private methods
	
	private Generator select(boolean bool){
		if(bool){
			return a;
		}else{
			return b;
		}
	}
}
