package dk.heatless.regex2string;

import dk.heatless.regex2string.conditions.AtGenerationIndex;
import dk.heatless.regex2string.conditions.ValidStringHasBeenGenerated;

/**
 * A collection of standard conditions, ready to be used.
 */
public class Conditions {
	
	public static final Condition 
		/**
		 * To meet this condition, the generation process must be in its initial state.
		 * IE, it must not have generated anything yet.
		 */
		START_OF_GENERATION = new AtGenerationIndex(0),
		/**
		 * To meet this condition, the generation process must be completed.
		 */
		VALID_STRING_HAS_BEEN_GENERATED = new ValidStringHasBeenGenerated()
	;
	
}
