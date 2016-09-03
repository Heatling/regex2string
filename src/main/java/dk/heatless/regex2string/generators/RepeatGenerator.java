package dk.heatless.regex2string.generators;

import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

/**
 * A generator that uses another generator repeatedly to generate a string.
 */
public class RepeatGenerator implements Generator {
	
	private Generator toRepeat;
	
	/**
	 * Constructs a generator that uses the given generator to repeatedly
	 * to generate a string.
	 * @param toRepeat
	 */
	public RepeatGenerator(Generator toRepeat) {
		this.toRepeat = toRepeat;
	}
	
	/**
	 * Uses the given generator to repeatedly generate substrings, then returns 
	 * the concatenated result.
	 */
	@Override
	public String generate(GenerationState state) {
		state = state.copyToRootState();
		GenerationState next = state.apply(toRepeat);
		while(next != null){
			state = next;
			next = state.apply(toRepeat);
		}
		return state.getGenerated();
	}

}
