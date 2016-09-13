package dk.heatless.regex2string.generators;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

/**
 * A generator that uses a given generator repeatedly as
 * long as a given condition hold.<br>
 * <br>
 * The class can be likened to a {@code while}-loop. If the loop is never entered
 * (the condition is false from the start) {@code null} is returned. If the loop is entered, 
 * but the generator generates {@code null} {@code null} is returned.
 * Otherwise all successful iterations of the loop (condition holds, followed by successful generation)
 * concatenate their generations and that is returned.<br>
 * The loop terminates when either the condition is false or the generator returns {@code null}.
 */
public class WhileGenerator implements Generator {
	
//fields
	/**
	 * Condition to hold at each iteration start.
	 */
	private Condition condition;
	
	/**
	 * What to try generating each iteration.
	 */
	private Generator doGenerate;
	
//Constructors
	
	/**
	 * Constructs a generator that acts as a {@code while}-loop with the given
	 * condition and using the given generator in each iteration.
	 * @param condition
	 * Condition of the loop.
	 * @param doGenerate
	 * What to try generating in each iteration.
	 */
	public WhileGenerator(Condition condition, Generator doGenerate){
		if(condition == null){
			throw new IllegalArgumentException("Condition was null");
		}
		if(doGenerate == null){
			throw new IllegalArgumentException("Generator was null");
		}
		this.condition = condition;
		this.doGenerate = doGenerate;
	}
	
//methods
	@Override
	public GenerationState generate(GenerationState state) {
		GenerationState mark = state, prev = state, next = state;
		while(condition.accept(prev)){
			next = prev.apply(doGenerate);
			if(next == null){
				break;
			}else{
				prev = next;
			}
		}
		
		return (prev == mark)? null : prev;
	}
}
