package dk.heatless.regex2string.conditions;

import dk.heatless.regex2string.Condition;
import dk.heatless.regex2string.GenerationState;

/**
 * A condition that specifies how far the generation process is.
 */
public class AtGenerationIndex implements Condition {

//Fields
	/**
	 * The index in the final generated string of the next character to be generated.
	 */
	private final int index;
	
	/**
	 * Constructs a condition that requires the next character to be generated to
	 * have the given index in the generated string.<br>
	 * In other words, index-1 characters must have already been generated to meet the condition.
	 * @param index
	 */
	public AtGenerationIndex(int index){
		if(index < 0){
			throw new IllegalArgumentException("Index is negative");
		}
		
		this.index = index;
	}
	
	@Override
	public boolean accept(GenerationState state) {
		return state == null ? false : state.getLengthOfGenerated() == index;
	}

}
