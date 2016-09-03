package dk.heatless.regex2string;

import dk.heatless.regex2string.generators.MinimalCharacterGenerator;
import dk.heatless.regex2string.generators.MinimalDigitGenerator;
import dk.heatless.regex2string.generators.RepeatGenerator;

public class Generators {
	
	/**
	 * Generates a string of the minimally possible characters.
	 * 
	 * @see
	 * {@link MinimalCharacterGenerator}
	 */
	public static final Generator MINIMAL_STRING_GENERATOR = new RepeatGenerator(new MinimalCharacterGenerator());
	
	/**
	 * Generates a string of the minimally possible integer.
	 * 
	 * @see
	 * {@link MinimalDigitGenerator}
	 */
	public static final Generator MINIMAL_INTEGER_GENERATOR = new RepeatGenerator(new MinimalDigitGenerator());
}
