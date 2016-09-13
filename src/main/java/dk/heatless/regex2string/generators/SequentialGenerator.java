package dk.heatless.regex2string.generators;

import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

/**
 * Generates a string by concatenating the output of an array of generators.<br>
 * For this generator to generate a string, the given generators must be able to
 * generate their strings following each other.
 */
public class SequentialGenerator implements Generator {
	
//Fields
	/**
	 * The sequence of generators to use to generate the final string.<br>
	 * sequence[0] is called first, then sequence[1] and so on.
	 */
	private Generator[] sequence;
	
//Constructors
	/**
	 * Constructs a generator that uses the given ordered list of
	 * generators to generate a string.
	 * @param sequence
	 * Ordered list, where sequence[0] is called first, followed by sequence[1] and so on.
	 */
	public SequentialGenerator(Generator[] sequence){
		if(sequence == null){
			throw new IllegalArgumentException("Array was null");
		}
		this.sequence = sequence;
	}
	
//Methods
	/**
	 * To return a non-{@code null} result, the first generator must be applicable
	 * to the given state. The second generator must then be applicable to the resulting state, and so on.
	 */
	@Override
	public GenerationState generate(GenerationState state) {
		GenerationState mark = state;
		GenerationState next = state;
		for(int i = 0; i<sequence.length; i++){
			next = next.apply(sequence[i]);
			if(next == null){
				return null;
			}
		}
		return (next == mark)? null: next;
	}

}
