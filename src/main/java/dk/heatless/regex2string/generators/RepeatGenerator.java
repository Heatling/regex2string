package dk.heatless.regex2string.generators;

import dk.heatless.regex2string.Conditions;
import dk.heatless.regex2string.GenerationState;
import dk.heatless.regex2string.Generator;

/**
 * A generator that uses another generator repeatedly to generate a string.<br>
 * The loop terminates when the generator is unable to generate a string. The result
 * is a concatenation of the previous iteration's generation.
 * 
 * @see
 * {@link WhileGenerator}, {@link Conditions#NO_CONDITION}
 */
public class RepeatGenerator extends WhileGenerator {
	
//Fields
	
//Constructors
	/**
	 * Constructs a generator that uses the given generator to repeatedly
	 * to generate a string.
	 * @param toRepeat
	 */
	public RepeatGenerator(Generator toRepeat) {
		super(Conditions.NO_CONDITION, toRepeat);
	}
}
