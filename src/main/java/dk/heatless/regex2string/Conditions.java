package dk.heatless.regex2string;

import dk.heatless.regex2string.conditions.AtGenerationIndex;
import dk.heatless.regex2string.conditions.ValidStringHasBeenGenerated;

public class Conditions {
	
	public static final Condition 
		START_OF_GENERATION = new AtGenerationIndex(0),
		VALID_STRING_HAS_BEEN_GENERATED = new ValidStringHasBeenGenerated()
	;
	
}
